import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;

public class SwerveSim extends JFrame {

    // --- Robot Constants (Ported from robot.h) ---
    private static final double PI = Math.PI;
    private static final double _2PI = 2.0 * Math.PI;
    private static final double DEG2RAD = 0.0174533;
    private static final double MAX_WHEEL_SPD = 200.0; 
    
    // Wheel Flips
    private static final double FL_FLIP = -1.0;
    private static final double FR_FLIP = 1.0;
    private static final double BL_FLIP = -1.0;
    private static final double BR_FLIP = 1.0;

    // --- Data Structures ---
    
    // Equivalent to pivot_cmd struct
    class PivotCmd {
        double mult = 1.0;
        double angle = 0.0;
        double errUsed = 0.0;
    }

    // Equivalent to Robot_Struct (Simplified for Sim)
    class RobotState {
        // Inputs
        double xVel, yVel, yawVel;
        boolean isHeadless = false;
        double headlessAngle = 0.0;
        double currentYaw = 0.0; // Simulated IMU yaw
        
        // State retention for "holding" angles when stopped
        double lastFLAngle = 0.0, lastFRAngle = 0.0, lastBLAngle = 0.0, lastBRAngle = 0.0;

        // Computed Outputs (Wheel Angles in Degrees, Speeds normalized)
        double flAngle, frAngle, blAngle, brAngle;
        double flSpeed, frSpeed, blSpeed, brSpeed;
        
        // Debug info
        PivotCmd flCmd = new PivotCmd();
        PivotCmd frCmd = new PivotCmd();
        PivotCmd blCmd = new PivotCmd();
        PivotCmd brCmd = new PivotCmd();
    }

    private RobotState robot = new RobotState();
    private RobotPanel visualizer;
    private JoystickPanel joystick;
    private JLabel statusLabel;

    public SwerveSim() {
        setTitle("Swerve Drive Simulator (STM32 Logic Port)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Visualization Panel (Center)
        visualizer = new RobotPanel();
        add(visualizer, BorderLayout.CENTER);

        // 2. Control Panel (Bottom)
        JPanel controlContainer = new JPanel(new BorderLayout());
        controlContainer.setPreferredSize(new Dimension(800, 200));
        controlContainer.setBorder(BorderFactory.createTitledBorder("Controls"));

        // Virtual Joystick
        joystick = new JoystickPanel();
        controlContainer.add(joystick, BorderLayout.CENTER);

        // Status & Buttons
        JPanel sideControls = new JPanel(new GridLayout(4, 1));
        
        JCheckBox headlessCb = new JCheckBox("Headless Mode");
        headlessCb.addActionListener(e -> {
            robot.isHeadless = headlessCb.isSelected();
            if(robot.isHeadless) robot.headlessAngle = robot.currentYaw;
        });
        
        JButton resetYawBtn = new JButton("Reset Gyro");
        resetYawBtn.addActionListener(e -> robot.currentYaw = 0);

        statusLabel = new JLabel("Status: Active");
        
        sideControls.add(new JLabel("Mouse: Move XY | Keys: A/D Rotate"));
        sideControls.add(headlessCb);
        sideControls.add(resetYawBtn);
        sideControls.add(statusLabel);

        controlContainer.add(sideControls, BorderLayout.EAST);
        add(controlContainer, BorderLayout.SOUTH);

        // 3. Keyboard Hooks for Rotation (Yaw)
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_A) robot.yawVel = 8.0; // Rotate Left
                if (e.getKeyCode() == KeyEvent.VK_D) robot.yawVel = -8.0; // Rotate Right
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) robot.yawVel = 0;
            }
            return false;
        });

        // 4. Simulation Loop (60Hz)
        Timer timer = new Timer(16, e -> loop());
        timer.start();
    }

    // --- Main Logic Loop ---
    private void loop() {
        // 1. Update Inputs from Joystick
        robot.xVel = joystick.getXInput() * 100; // Scale up for logic
        robot.yVel = joystick.getYInput() * 100; 
        
        // 2. Simulate IMU integration (Yaw changes based on YawVel)
        robot.currentYaw += (robot.yawVel * 0.05); // Simple integration
        if(robot.currentYaw > 360) robot.currentYaw -= 360;
        if(robot.currentYaw < 0) robot.currentYaw += 360;

        // 3. Run the Control Logic
        control(robot);

        // 4. Update UI
        visualizer.repaint();
        updateStatus();
    }

    // --- Ported C Logic ---

    // robot.c: control()
    private void control(RobotState r) {
        updateStates(r);
        swerve(r);
    }

    // robot.c: update_states() -> Modified for Java Logic
    private void updateStates(RobotState r) {
        // Headless Mode Logic
        if (r.isHeadless) {
            double curYawRad = Math.toRadians(r.currentYaw - r.headlessAngle);
            while(curYawRad < 0) curYawRad += _2PI;

            // Rotate vectors (Counter-rotate input based on gyro)
            double newY = r.xVel * Math.sin(curYawRad) + r.yVel * Math.cos(curYawRad);
            double newX = r.xVel * Math.cos(curYawRad) - r.yVel * Math.sin(curYawRad);
            
            r.xVel = newX;
            r.yVel = newY;
        }
    }

    // robot.c: process_goal_angle()
    private PivotCmd processGoalAngle(double curAngleAbs, double goal) {
        PivotCmd cmd = new PivotCmd();
        double curAngle = curAngleAbs;
        
        while (curAngle < -_2PI) curAngle += _2PI;
        while (curAngle > 0.0) curAngle -= _2PI;

        double err1 = goal - curAngle;
        double err2;
        
        if (err1 >= 0) err2 = err1 - _2PI;
        else err2 = err1 + _2PI;

        double errUsed;
        if (Math.abs(err1) < Math.abs(err2)) errUsed = err1;
        else errUsed = err2;

        if (errUsed < -PI / 2.0) {
            errUsed += PI;
            cmd.mult = -1.0;
        } else if (errUsed > PI / 2.0) {
            errUsed -= PI;
            cmd.mult = -1.0;
        } else {
            cmd.mult = 1.0;
        }

        cmd.errUsed = errUsed;
        cmd.angle = curAngleAbs + errUsed;
        return cmd;
    }

    // robot.c: swerve()
    private void swerve(RobotState r) {
        double x = r.xVel;
        double y = r.yVel;
        double yw = r.yawVel * 20.0; // Gain adjustment for sim feel

        double chassisY = 1;
        double chassisX = 1;

        // Vector Arithmetic
        double ax = x + chassisY * -yw;
        double ay = y + chassisX * -yw;
        double bx = x - chassisY * -yw;
        double by = y + chassisX * -yw;
        double cx = x - chassisY * -yw;
        double cy = y - chassisX * -yw;
        double dx = x + chassisY * -yw;
        double dy = y - chassisX * -yw;

        // Calculate Magnitude and Angle
        // Note: Using toDegrees and toRadians helper to match C code structure
        double brSpeed = Math.sqrt(ax * ax + ay * ay);
        double brAngle = -Math.toDegrees(Math.atan2(ay, ax)) - 90.0;
        if (brAngle < 0.0) brAngle += 360.0;

        double frSpeed = Math.sqrt(bx * bx + by * by);
        double frAngle = -Math.toDegrees(Math.atan2(by, bx)) - 90.0;
        if (frAngle < 0.0) frAngle += 360.0;

        double flSpeed = Math.sqrt(cx * cx + cy * cy);
        double flAngle = -Math.toDegrees(Math.atan2(cy, cx)) - 90.0;
        if (flAngle < 0.0) flAngle += 360.0;

        double blSpeed = Math.sqrt(dx * dx + dy * dy);
        double blAngle = -Math.toDegrees(Math.atan2(dy, dx)) - 90.0;
        if (blAngle < 0.0) blAngle += 360.0;

        // Normalize Speeds
        double max = 0.0;
        max = Math.max(max, Math.abs(brSpeed));
        max = Math.max(max, Math.abs(blSpeed));
        max = Math.max(max, Math.abs(frSpeed));
        max = Math.max(max, Math.abs(flSpeed));

        if (max > 1.0) { // Assuming 1.0 is max logic in relative terms, or scale down
             // In C code logic: if(max > 1.0f) ... divide. 
             // Since inputs are large (0-100), let's just normalize for display relative to max
             // For strict logic port, we keep it, but visualizer needs normalized 0-1
        }

        // Deadzone logic (Stop wheels moving if no input)
        if (x == 0.0 && y == 0.0 && Math.abs(yw) < 0.01) {
            blAngle = r.lastBLAngle;
            brAngle = r.lastBRAngle;
            flAngle = r.lastFLAngle;
            frAngle = r.lastFRAngle;
            brSpeed = 0; blSpeed = 0; frSpeed = 0; flSpeed = 0;
        }

        r.lastBLAngle = blAngle;
        r.lastBRAngle = brAngle;
        r.lastFLAngle = flAngle;
        r.lastFRAngle = frAngle;

        // Optimization Logic (Wheel Flipping)
        // Convert deg to rad for processing
        double FL_ANG_CMD = -flAngle * DEG2RAD;
        double FR_ANG_CMD = -frAngle * DEG2RAD;
        double BL_ANG_CMD = -blAngle * DEG2RAD;
        double BR_ANG_CMD = -brAngle * DEG2RAD;

        // We need to maintain a "current state" for the optimization to work 
        // (it compares against previous angle). In the C code, this is FL_P_STATE.p.
        // For simulation, we will feed the result back into the state for the next loop.
        
        r.flCmd = processGoalAngle(r.flCmd.angle, FL_ANG_CMD);
        r.frCmd = processGoalAngle(r.frCmd.angle, FR_ANG_CMD);
        r.blCmd = processGoalAngle(r.blCmd.angle, BL_ANG_CMD);
        r.brCmd = processGoalAngle(r.brCmd.angle, BR_ANG_CMD);

        // Calculate Final Wheel Speeds
        // Using arbitrary max speed for visual scaling
        double curMax = 1.0; 
        r.flSpeed = flSpeed * r.flCmd.mult * curMax * Math.cos(r.flCmd.errUsed);
        r.frSpeed = frSpeed * r.frCmd.mult * curMax * Math.cos(r.frCmd.errUsed);
        r.blSpeed = blSpeed * r.blCmd.mult * curMax * Math.cos(r.blCmd.errUsed);
        r.brSpeed = brSpeed * r.brCmd.mult * curMax * Math.cos(r.brCmd.errUsed);

        // Store final angles for display (convert back to degrees for drawing)
        r.flAngle = -Math.toDegrees(r.flCmd.angle);
        r.frAngle = -Math.toDegrees(r.frCmd.angle);
        r.blAngle = -Math.toDegrees(r.blCmd.angle);
        r.brAngle = -Math.toDegrees(r.brCmd.angle);
    }

    private void updateStatus() {
        DecimalFormat df = new DecimalFormat("#.0");
        statusLabel.setText("<html>Yaw: " + df.format(robot.currentYaw) 
            + "<br>FL: " + df.format(robot.flAngle) + "° | " + df.format(robot.flSpeed)
            + "<br>FR: " + df.format(robot.frAngle) + "° | " + df.format(robot.frSpeed) 
            + "</html>");
    }

    // --- UI Components ---

    // 1. Joystick Panel
    class JoystickPanel extends JPanel implements MouseMotionListener, MouseListener {
        private int joyX = 0, joyY = 0;
        private final int MAX_RANGE = 80;
        private final int CENTER_X = 150, CENTER_Y = 100;

        public JoystickPanel() {
            addMouseMotionListener(this);
            addMouseListener(this);
            setBackground(Color.DARK_GRAY);
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

        public double getXInput() { return (double)joyX / MAX_RANGE; }
        // Invert Y because screen coordinates are Y-down, but robot logic expects Y-up usually
        // However, standard swerve usually treats Forward as Y+. 
        // Mouse drag up decreases Y pixel, so we invert.
        public double getYInput() { return -(double)joyY / MAX_RANGE; }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.LIGHT_GRAY);
            g.drawOval(CENTER_X - MAX_RANGE, CENTER_Y - MAX_RANGE, MAX_RANGE * 2, MAX_RANGE * 2);
            
            g.setColor(Color.RED);
            g.fillOval(CENTER_X + joyX - 10, CENTER_Y + joyY - 10, 20, 20);
            
            g.setColor(Color.WHITE);
            g.drawString("Drag Red Dot", 10, 20);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - CENTER_X;
            int dy = e.getY() - CENTER_Y;
            double dist = Math.sqrt(dx*dx + dy*dy);
            
            if (dist > MAX_RANGE) {
                dx = (int)(dx * (MAX_RANGE / dist));
                dy = (int)(dy * (MAX_RANGE / dist));
            }
            joyX = dx; 
            joyY = dy;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            joyX = 0; joyY = 0;
            repaint();
        }

        // Unused overrides
        public void mouseMoved(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // 2. Robot Visualizer Panel
    class RobotPanel extends JPanel {
        public RobotPanel() { setBackground(new Color(30, 30, 30)); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;
            int chassisSize = 200;

            // Apply Global Robot Rotation (Simulate the robot turning in the world)
            AffineTransform old = g2.getTransform();
            g2.rotate(Math.toRadians(robot.currentYaw), cx, cy);

            // Draw Chassis
            g2.setColor(Color.GRAY);
            g2.fillRect(cx - chassisSize/2, cy - chassisSize/2, chassisSize, chassisSize);
            
            // Draw Heading Arrow
            g2.setColor(Color.ORANGE);
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(cx, cy, cx, cy - 80);
            g2.fillPolygon(new int[]{cx-10, cx+10, cx}, new int[]{cy-80, cy-80, cy-100}, 3);

            // Draw Wheels
            drawWheel(g2, cx - chassisSize/2, cy - chassisSize/2, robot.flAngle, robot.flSpeed, "FL"); // FL
            drawWheel(g2, cx + chassisSize/2, cy - chassisSize/2, robot.frAngle, robot.frSpeed, "FR"); // FR
            drawWheel(g2, cx - chassisSize/2, cy + chassisSize/2, robot.blAngle, robot.blSpeed, "BL"); // BL
            drawWheel(g2, cx + chassisSize/2, cy + chassisSize/2, robot.brAngle, robot.brSpeed, "BR"); // BR

            g2.setTransform(old);
            
            // Draw Info Text overlay
            g2.setColor(Color.WHITE);
            g2.drawString("Global Heading: " + (int)robot.currentYaw + "°", 10, 20);
        }

        private void drawWheel(Graphics2D g2, int x, int y, double angleDeg, double speed, String label) {
            AffineTransform old = g2.getTransform();
            g2.translate(x, y);
            // Swerve logic 0 degrees is usually "Forward". In Java 2D, 0 is right (East).
            // We rotate -90 to align 0 with North/Up.
            g2.rotate(Math.toRadians(angleDeg)); 

            // Wheel Body
            g2.setColor(Color.CYAN);
            g2.fillRect(-10, -20, 20, 40);
            
            // Direction Indicator (Vector)
            g2.setColor(speed >= 0 ? Color.GREEN : Color.RED);
            int vecLen = (int)(Math.abs(speed) / 5.0); // Scale for visual
            if(vecLen > 40) vecLen = 40;
            
            // Draw line representing speed/direction
            // If speed is negative, the code logic handles it via flipping, 
            // but visually we want to see which way the wheel "pulls".
            int dir = speed >= 0 ? -1 : 1; 
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(0, 0, 0, dir * (25 + vecLen));

            // Label
            g2.rotate(-Math.toRadians(angleDeg)); // Un-rotate for text
            g2.setColor(Color.WHITE);
            g2.drawString(label, -10, 0);
            
            g2.setTransform(old);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwerveSim().setVisible(true);
        });
    }
}