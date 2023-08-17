package net.minecraft.client.settings;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;

public class HotbarSnapshot extends ArrayList<ItemStack>
{
    public static final int HOTBAR_SIZE = InventoryPlayer.getHotbarSize();

    public HotbarSnapshot()
    {
        this.ensureCapacity(HOTBAR_SIZE);

        for (int i = 0; i < HOTBAR_SIZE; ++i)
        {
            this.add(ItemStack.EMPTY);
        }
    }

    public NBTTagList createTag()
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < HOTBAR_SIZE; ++i)
        {
            nbttaglist.appendTag(this.get(i).writeToNBT(new NBTTagCompound()));
        }

        return nbttaglist;
    }

    public void fromTag(NBTTagList tag)
    {
        for (int i = 0; i < HOTBAR_SIZE; ++i)
        {
            this.set(i, new ItemStack(tag.getCompoundTagAt(i)));
        }
    }

    public boolean isEmpty()
    {
        for (int i = 0; i < HOTBAR_SIZE; ++i)
        {
            if (!this.get(i).isEmpty())
            {
                return false;
            }
        }

        return true;
    }
}
