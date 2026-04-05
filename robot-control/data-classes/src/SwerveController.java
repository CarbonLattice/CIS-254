public final class SwerveController {
    public static void Swerve(robotStruct robot) {

        float x = robot.xVel;
        float y = robot.yVel;
        float yw = robot.yawVel;

        float chassisX = 1.0f;
        float chassisY  = 1.0f;


        //BR, FR, FL, BL
        //BR y1 = -L, x1 = W
	    //FR y2 = L, x2 = W
	    //FL y3 = L, x3 = -W
	    //BL y4 = -L, x4 = -W

        float ax = x + chassisY * -yw;
        float ay = y + chassisX * -yw;
        float backRightSpeedMatrix = (float) Math.sqrt((ax * ax) + (ay * ay));
        float backRightAngleMatrix = -(float) Math.atan2(ay, ax) / robotConstants.PI * 180.0f;
        if (backRightAngleMatrix < 0) backRightAngleMatrix += 360.0f;


        float bx = x - chassisY * -yw;
        float by = y + chassisX * -yw;
        float frontRightSpeedMatrix = (float) Math.sqrt((bx * bx) + (by * by));
        float frontRightAngleMatrix = -(float) Math.atan2(by, bx) / robotConstants.PI * 180.0f;
        if (frontRightAngleMatrix < 0) frontRightAngleMatrix += 360.0f;


        float cx = x - chassisY * -yw;
        float cy = y - chassisX * -yw;
        float frontLeftSpeedMatrix = (float) Math.sqrt((cx * cx) + (cy * cy));
        float frontLeftAngleMatrix = -(float) Math.atan2(cy, cx) / robotConstants.PI * 180.0f;
        if (frontLeftAngleMatrix < 0) frontLeftAngleMatrix += 360.0f;


        float dx = x + chassisY * -yw;
        float dy = y - chassisX * -yw;
        float backLeftSpeedMatrix = (float) Math.sqrt((dx * dx) + (dy * dy));
        float backLeftAngleMatrix = -(float) Math.atan2(dy, dx) / robotConstants.PI * 180.0f;
        if (backLeftAngleMatrix < 0) backLeftAngleMatrix += 360.0f;




        float backRightSpeed = backRightSpeedMatrix;
        float backLeftSpeed = backLeftSpeedMatrix;
        float frontRightSpeed = frontRightSpeedMatrix;
	    float frontLeftSpeed = frontLeftSpeedMatrix;


	    float backLeftAngle = backLeftAngleMatrix;
	    float backRightAngle = backRightAngleMatrix;
	    float frontLeftAngle = frontLeftAngleMatrix;
	    float frontRightAngle = frontRightAngleMatrix;

        float max = Math.max(
            Math.max(Math.abs(backRightSpeed), Math.abs(backLeftSpeed)),
            Math.max(Math.abs(frontRightSpeed), Math.abs(frontLeftSpeed))
        );


        if (max > 1.0f) {
            backRightSpeed /= max;
            backLeftSpeed /= max;
            frontRightSpeed /= max;
            frontLeftSpeed /= max;
        }

        robot.lastBLAngle = backLeftAngle;
        robot.lastBRAngle = backRightAngle;
        robot.lastFLAngle = frontLeftAngle;
        robot.lastFRAngle = frontRightAngle;

        robot.FL_P_CMD = SwerveMath.processGoalAngle(robot.FL_P_STATE.p, -frontLeftAngle * robotConstants.DEG2RAD);
        robot.FR_P_CMD = SwerveMath.processGoalAngle(robot.FR_P_STATE.p, -frontRightAngle * robotConstants.DEG2RAD);
        robot.BL_P_CMD = SwerveMath.processGoalAngle(robot.BL_P_STATE.p, -backLeftAngle * robotConstants.DEG2RAD);
        robot.BR_P_CMD = SwerveMath.processGoalAngle(robot.BR_P_STATE.p, -backRightAngle * robotConstants.DEG2RAD);

        robot.FL_W_CTRL.V_des = frontLeftSpeed * robot.FL_P_CMD.mult * robot.curMaxWheelSpd * (float) Math.cos(robot.FL_P_CMD.errUsed) * robotConstants.FL_FLIP;
        robot.FR_W_CTRL.V_des = frontRightSpeed * robot.FR_P_CMD.mult * robot.curMaxWheelSpd * (float) Math.cos(robot.FR_P_CMD.errUsed) * robotConstants.FR_FLIP;
        robot.BL_W_CTRL.V_des = backLeftSpeed * robot.BL_P_CMD.mult * robot.curMaxWheelSpd * (float) Math.cos(robot.BL_P_CMD.errUsed) * robotConstants.BL_FLIP;
        robot.BR_W_CTRL.V_des = backRightSpeed * robot.BR_P_CMD.mult * robot.curMaxWheelSpd * (float) Math.cos(robot.BR_P_CMD.errUsed) * robotConstants.BR_FLIP;

    System.out.printf(
    "BR: spd=%.3f ang=%.2f | FR: spd=%.3f ang=%.2f | FL: spd=%.3f ang=%.2f | BL: spd=%.3f ang=%.2f%n",
        backRightSpeed, backRightAngle,
        frontRightSpeed, frontRightAngle,
        frontLeftSpeed, frontLeftAngle,
        backLeftSpeed, backLeftAngle
    );

    }
    
}


