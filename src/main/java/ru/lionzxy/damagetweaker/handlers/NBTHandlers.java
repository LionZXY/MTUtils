package ru.lionzxy.damagetweaker.handlers;

import minetweaker.api.data.IData;
import minetweaker.api.formatting.IFormattedText;
import minetweaker.mc1710.data.NBTConverter;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lionzxy on 6/9/16.
 */
@ZenClass("mods.MTUtils.NBT")
public class NBTHandlers {

    @ZenMethod
    public static boolean isExists(IData nbt, String path) {
        return getTagByPath(nbt, path) != null;
    }

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
        return NBTConverter.from(getTagByPath(tagCompound, path).getTag(path.substring(path.lastIndexOf(".") + 1)), false);
    }

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
