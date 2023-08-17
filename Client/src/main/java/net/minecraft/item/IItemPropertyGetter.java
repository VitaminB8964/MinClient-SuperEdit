package net.minecraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IItemPropertyGetter
{
    float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn);
}
