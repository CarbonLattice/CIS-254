public class robotStruct {
    public float roll, pitch, yaw;
    public float rollVel, pitchVel, yawVel;

    public int imuUpdateCounter;
    public int printCounter;

    public int isEnabled;

    public jointState FL_P_STATE, FR_P_STATE, BL_P_STATE, BR_P_STATE, FL_W_STATE, FR_W_STATE, BL_W_STATE, BR_W_STATE;
    public jointControl FL_P_CTRL, FR_P_CTRL, BL_P_CTRL, BR_P_CTRL, FL_W_CTRL, FR_W_CTRL, BL_W_CTRL, BR_W_CTRL;
    public pivotCmd FL_P_CMD, FR_P_CMD, BL_P_CMD, BR_P_CMD;
    public pwmCmd FL_W_PWM, FR_W_PWM, BL_W_PWM, BR_W_PWM;

    public int isHeadless;
    public float headlessAngle;
    
    public float lastBLAngle, lastBRAngle, lastFLAngle, lastFRAngle;

    public float xVel, yVel, yaw_Vel;

    public float yawGoal, lastYawErr, dYawErr, yawErr, yawVelCL;

    public float curMaxWheelSpd;

    public robotStruct() {

        // Pivot states
        FL_P_STATE = new jointState();
        FR_P_STATE = new jointState();
        BL_P_STATE = new jointState();
        BR_P_STATE = new jointState();

        FL_W_STATE = new jointState();
        FR_W_STATE = new jointState();
        BL_W_STATE = new jointState();
        BR_W_STATE = new jointState();

        // Controllers
        FL_P_CTRL = new jointControl();
        FR_P_CTRL = new jointControl();
        BL_P_CTRL = new jointControl();
        BR_P_CTRL = new jointControl();

        FL_W_CTRL = new jointControl();
        FR_W_CTRL = new jointControl();
        BL_W_CTRL = new jointControl();
        BR_W_CTRL = new jointControl();

        // Commands
        FL_P_CMD = new pivotCmd();
        FR_P_CMD = new pivotCmd();
        BL_P_CMD = new pivotCmd();
        BR_P_CMD = new pivotCmd();

        FL_W_PWM = new pwmCmd();
        FR_W_PWM = new pwmCmd();
        BL_W_PWM = new pwmCmd();
        BR_W_PWM = new pwmCmd();
    }

}