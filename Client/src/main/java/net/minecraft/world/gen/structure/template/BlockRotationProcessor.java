package net.minecraft.world.gen.structure.template;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockRotationProcessor implements ITemplateProcessor
{
    private final float chance;
    private final Random random;

    public BlockRotationProcessor(BlockPos pos, PlacementSettings settings)
    {
        this.chance = settings.getIntegrity();
        this.random = settings.getRandom(pos);
    }

    @Nullable
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn)
    {
        return this.chance < 1.0F && this.random.nextFloat() > this.chance ? null : blockInfoIn;
    }
}
