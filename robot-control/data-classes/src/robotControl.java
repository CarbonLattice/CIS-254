public final class robotControl {

    public static void control(robotStruct robot) {
        updateStates(robot);
        if (robot.isEnabled != 0) {
            SwerveController.Swerve(robot);
        }
    }

    public static void updateStates(robotStruct robot) {


        robot.FL_P_CTRL.kp = robotConstants.PIVOT_KP;
        robot.FL_P_CTRL.kd = robotConstants.PIVOT_KD;
        robot.FR_P_CTRL.kp = robotConstants.PIVOT_KP;
        robot.FR_P_CTRL.kd = robotConstants.PIVOT_KD;
        robot.BL_P_CTRL.kp = robotConstants.PIVOT_KP;
        robot.BL_P_CTRL.kd = robotConstants.PIVOT_KD;
        robot.BR_P_CTRL.kp = robotConstants.PIVOT_KP;
        robot.BR_P_CTRL.kd = robotConstants.PIVOT_KD;

        robot.FL_W_CTRL.kd = robotConstants.WHEEL_KD;
        robot.FR_W_CTRL.kd = robotConstants.WHEEL_KD;
        robot.BL_W_CTRL.kd = robotConstants.WHEEL_KD;
        robot.BR_W_CTRL.kd = robotConstants.WHEEL_KD;

        robot.curMaxWheelSpd = robotConstants.MAX_WHEEL_SPD;

    }
}
