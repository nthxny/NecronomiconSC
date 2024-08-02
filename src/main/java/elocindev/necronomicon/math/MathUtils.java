package elocindev.necronomicon.math;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MathUtils {
    //#if FABRIC==1
    public static Vec3 getLookingVec(LivingEntity entity) {
    //#else
    //$$ public static Vec3 getLookingVec(LivingEntity entity) {
    //#endif
        float yaw = entity.getYRot(); float pitch = entity.getXRot();
        
        float radian = 0.017453292F;
        float x = -Helper.sin(yaw * radian) * Helper.cos(pitch * radian);
        float y = -Helper.sin(pitch * radian);
        float z = Helper.cos(yaw * radian) * Helper.cos(pitch * radian);
        
        float m = Helper.sqrt(x * x + y * y + z * z);

        //#if FABRIC==1
        return new Vec3(
        //#else
        //$$ return new Vec3(
        //#endif
            x/m, y/m, z/m);
    }

    private class Helper {
        //#if FABRIC==1
        private static float sin(float f) {
            return Mth.sin(f);
        }
        private static float cos(float f) {
            return Mth.cos(f);
        }
        private static float sqrt(float f) {
            return Mth.sqrt(f);
        }
        //#else
        //$$ private static float sin(float f) {
        //$$     return Mth.sin(f);
        //$$ }
        //$$ private static float cos(float f) {
        //$$     return Mth.cos(f);
        //$$ }
        //$$ private static float sqrt(float f) {
        //$$     return Mth.sqrt(f);
        //$$ }
        //#endif
    }
}