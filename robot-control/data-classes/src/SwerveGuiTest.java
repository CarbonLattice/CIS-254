import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwerveGuiTest extends JPanel
        implements MouseMotionListener, MouseWheelListener {

    private final robotStruct robot = new robotStruct();

    public SwerveGuiTest() {
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        // repaint at ~60 Hz
        new Timer(16, e -> repaint()).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        int cy = h / 2;

        // run swerve
        SwerveController.Swerve(robot);

        // draw chassis
        g.setColor(Color.BLACK);
        g.drawRect(cx - 100, cy - 100, 200, 200);

        //drawWheel(g, cx - 80, cy - 80, robot.FL_P_STATE.p);
        //drawWheel(g, cx + 80, cy - 80, robot.FR_P_STATE.p);
        //drawWheel(g, cx - 80, cy + 80, robot.BL_P_STATE.p);
        //drawWheel(g, cx + 80, cy + 80, robot.BR_P_STATE.p);
        
        drawWheel(g, cx - 80, cy - 80, robot.FL_P_CMD.angle);
        drawWheel(g, cx + 80, cy - 80, robot.FR_P_CMD.angle);
        drawWheel(g, cx - 80, cy + 80, robot.BL_P_CMD.angle);
        drawWheel(g, cx + 80, cy + 80, robot.BR_P_CMD.angle);



    }

    private void drawWheel(Graphics g, int x, int y, float angleRad) {
        int len = 40;
        float a = angleRad;

        int x2 = x + (int) (Math.cos(a) * len);
        int y2 = y - (int) (Math.sin(a) * len);

        g.drawLine(x, y, x2, y2);
        g.fillOval(x - 4, y - 4, 8, 8);
    }

    // ---------------- Mouse input ----------------

    @Override
    public void mouseMoved(MouseEvent e) {
        int w = getWidth();
        int h = getHeight();

        // map mouse to [-1, 1]
        robot.xVel = (e.getX() - w / 2f) / (w / 2f);
        robot.yVel = -(e.getY() - h / 2f) / (h / 2f);

        clamp();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        robot.yawVel += e.getWheelRotation() * 0.1f;
        clamp();
    }

    private void clamp() {
        robot.xVel = Math.max(-1, Math.min(1, robot.xVel));
        robot.yVel = Math.max(-1, Math.min(1, robot.yVel));
        robot.yawVel = Math.max(-1, Math.min(1, robot.yawVel));
    }

    // ---------------- main ----------------

    public static void main(String[] args) {
        JFrame frame = new JFrame("Swerve Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setContentPane(new SwerveGuiTest());
        frame.setVisible(true);
    }
}
