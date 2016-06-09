package ru.lionzxy.damagetweaker.nbt;

import minetweaker.api.data.*;
import minetweaker.mc1710.data.NBTConverter;
import net.minecraft.nbt.*;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ru.lionzxy.damagetweaker.nbt
 * Created by LionZXY on 23.02.2016.
 * DamageTweaker
 */
@ZenClass("mods.MTUtilsWrite")
public class Write {


    @ZenMethod
    public static IData writeToNBT(IData data, String path, boolean value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTConverter().fromBool(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, IData value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, NBTConverter.from(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, byte value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagByte(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, byte[] value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagByteArray(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, double value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagDouble(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, float value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagFloat(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, int value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagInt(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, int[] value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagIntArray(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, long value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagLong(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, short value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagShort(value));
        return NBTConverter.from(tagCompound, false);
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, String value) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        getTagByPath(tagCompound, path).setTag(path, new NBTTagString(value));
        return NBTConverter.from(tagCompound, false);
    }

    public static NBTTagCompound getTagByPath(NBTTagCompound tagCompound, String path) {
        if (path.contains(".")) {
            String key = path.substring(0, path.indexOf("."));
            if (tagCompound.getCompoundTag(key) == null)
                tagCompound.setTag(key, new NBTTagCompound());
            return getTagByPath(tagCompound.getCompoundTag(key), path.substring(path.indexOf(".") + 1));
        } else return tagCompound;
    }

    public static String[] splitToByte(String str, byte b) {
        List<String> toExit = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
            if ((byte) str.charAt(i) == b) {
                toExit.add(sb.toString());
                sb = new StringBuilder();
            } else sb.append(str.charAt(i));
        toExit.add(sb.toString());
        return toExit.toArray(new String[toExit.size()]);
    }

}
