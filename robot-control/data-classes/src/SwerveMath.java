public final class SwerveMath {
    public static pivotCmd processGoalAngle(float curAngleAbs, float goal) {
        pivotCmd cmd = new pivotCmd();

        float curAngle = curAngleAbs;
        while (curAngle < -robotConstants.TWO_PI) {
            curAngle += robotConstants.TWO_PI;
        }
        while (curAngle > 0.0f) {
            curAngle -= robotConstants.TWO_PI;
        }

        float err1 = goal - curAngle;
        float err2 = (err1 >= 0) ? err1 - robotConstants.TWO_PI
                                 : err1 + robotConstants.TWO_PI;


        float errUsed = Math.abs(err1) < Math.abs(err2) ? err1 : err2;
        

        if (errUsed < -robotConstants.PI / 2.0f) {
            errUsed += robotConstants.PI;
            cmd.mult = -1.0f;
        } else if (errUsed > robotConstants.PI / 2.0f) {
            errUsed -= robotConstants.PI;
            cmd.mult = -1.0f;
        } else {
            cmd.mult = 1.0f;
        }


        cmd.errUsed = errUsed;
        cmd.angle = curAngle + errUsed;
        return cmd;
    }
    
    public static float wrapError(float cur, float goal, float max, float min) {
        float err1 = goal - cur;
        float err2;

        if (err1 > 0) {
            err2 = -((cur - min) + (max -goal));
        } else {
            err2 = -((cur - max) + (min - goal));
        }
        return Math.abs(err1) < Math.abs(err2) ? err1 : err2;
    }


}
