package ru.lionzxy.damagetweaker.nbt;

import minetweaker.api.data.IData;
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
        return getUnReadDataFromString(nbt, path).asMap();
    }

    @ZenMethod
    public static short readShortFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asShort();
    }

    @ZenMethod
    public static String readStringFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asString();
    }

    @ZenMethod
    public static int readIntFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asInt();
    }


    @ZenMethod
    public static int[] readIntArrayFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asIntArray();
    }


    @ZenMethod
    public static List readListFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asList();
    }


    @ZenMethod
    public static long readLongFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asLong();
    }

    @ZenMethod
    public static byte readByteFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asByte();
    }

    @ZenMethod
    public static byte[] readByteArrayFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asByteArray();
    }

    @ZenMethod
    public static double readDoubleFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asDouble();
    }

    @ZenMethod
    public static float readFloatFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asFloat();
    }

    @ZenMethod
    public static boolean readBoolFromNBT(IData nbt, String path) {
        return getUnReadDataFromString(nbt, path).asBool();
    }

    @ZenMethod
    public static IData getUnReadDataFromString(IData nbt, String path) {
        IData toExit = nbt;
        String[] pathToPoint = Write.spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }

        return toExit;
    }

}
