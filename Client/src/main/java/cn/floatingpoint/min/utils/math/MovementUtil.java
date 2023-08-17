package cn.floatingpoint.min.utils.math;

import static cn.floatingpoint.min.system.hyt.packet.CustomPacket.mc;

public final class MovementUtil {
    private static final double forward = mc.player.movementInput.moveForward;
    private static final double strafe = mc.player.movementInput.moveStrafe;
    private static final double yaw = mc.player.rotationYaw;
    private static final boolean movespeed = mc.player.getMoveSpeed() > 0;
    private static final double motionX = mc.player.motionX;
    private static final double motionY = mc.player.motionY;
    private static final double motionZ = mc.player.motionZ;
    public static boolean isMoving(){
        return  movespeed;
    }
    public static float getSpeed(){
        return (float) Math.sqrt(motionX * motionX + motionZ * motionZ);
    }
}
