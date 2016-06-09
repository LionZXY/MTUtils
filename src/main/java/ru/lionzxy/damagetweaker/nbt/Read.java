package ru.lionzxy.damagetweaker.nbt;

import minetweaker.api.data.IData;
import minetweaker.mc1710.data.NBTConverter;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.List;
import java.util.Map;

/**
 * ru.lionzxy.damagetweaker.nbt
 * Created by LionZXY on 23.02.2016.
 * DamageTweaker
 */
@ZenClass("mods.MTUtilsRead")
public class Read {

    @ZenMethod
    public static Map readMapFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asMap();
    }

    @ZenMethod
    public static short readShortFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asShort();
    }

    @ZenMethod
    public static String readStringFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asString();
    }

    @ZenMethod
    public static int readIntFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asInt();
    }


    @ZenMethod
    public static int[] readIntArrayFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asIntArray();
    }


    @ZenMethod
    public static List readListFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asList();
    }


    @ZenMethod
    public static long readLongFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asLong();
    }

    @ZenMethod
    public static byte readByteFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asByte();
    }

    @ZenMethod
    public static byte[] readByteArrayFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asByteArray();
    }

    @ZenMethod
    public static double readDoubleFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asDouble();
    }

    @ZenMethod
    public static float readFloatFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asFloat();
    }

    @ZenMethod
    public static boolean readBoolFromNBT(IData nbt, String path) {
        return getTagByPath(nbt, path).asBool();
    }

    public static IData getTagByPath(IData data, String path) {
        NBTTagCompound tagCompound = (NBTTagCompound) NBTConverter.from(data);
        return NBTConverter.from(Write.getTagByPath(tagCompound, path).getTag(path.substring(path.lastIndexOf(".") + 1)), false);
    }

}
