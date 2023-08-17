package net.minecraft.advancements.critereon;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.JsonUtils;

import javax.annotation.Nullable;

public class NBTPredicate
{
    /** The predicate that matches any NBT tag. */
    public static final NBTPredicate ANY = new NBTPredicate(null);
    @Nullable
    private final NBTTagCompound tag;

    public NBTPredicate(@Nullable NBTTagCompound tag)
    {
        this.tag = tag;
    }

    public boolean test(ItemStack item)
    {
        return this == ANY || this.test(item.getTagCompound());
    }

    public boolean test(Entity entityIn)
    {
        return this == ANY || this.test(CommandBase.entityToNBT(entityIn));
    }

    public boolean test(@Nullable NBTBase nbt)
    {
        if (nbt == null)
        {
            return this == ANY;
        }
        else
        {
            return this.tag == null || NBTUtil.areNBTEquals(this.tag, nbt, true);
        }
    }

    public static NBTPredicate deserialize(@Nullable JsonElement json)
    {
        if (json != null && !json.isJsonNull())
        {
            NBTTagCompound nbttagcompound;

            try
            {
                nbttagcompound = JsonToNBT.getTagFromJson(JsonUtils.getString(json, "nbt"));
            }
            catch (NBTException nbtexception)
            {
                throw new JsonSyntaxException("Invalid nbt tag: " + nbtexception.getMessage());
            }

            return new NBTPredicate(nbttagcompound);
        }
        else
        {
            return ANY;
        }
    }
}
