package ru.lionzxy.damagetweaker;

import minetweaker.api.data.*;
import minetweaker.api.formatting.IFormattedText;
import minetweaker.api.item.IItemStack;
import minetweaker.api.tooltip.IngredientTooltips;
import minetweaker.mc1710.item.MCItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ru.lionzxy.damagetweaker
 * Created by LionZXY on 09.02.2016.
 * DamageTweaker
 */
@ZenClass("mods.MTUtilsNBT")
public class NBTMT {

    @ZenMethod
    public static void addMultilineShiftTooltip(IItemStack stack, IFormattedText strs, String s) {
        for (String str : splitString(MTHandlers.getStringFromFormattedText(strs), s))
            IngredientTooltips.addShiftTooltip(stack, MTHandlers.getIFormatedTextFromString(str));
    }

    @ZenMethod
    public static void addMultilineTooltip(IItemStack stack, IFormattedText strs, String s) {
        for (String str : splitString(MTHandlers.getStringFromFormattedText(strs), s))
            IngredientTooltips.addTooltip(stack, MTHandlers.getIFormatedTextFromString(str));
    }

    @ZenMethod
    public static String[] splitString(String original, char split) {
        return spilitToByte(original, (byte) split);
    }

    @ZenMethod
    public static String[] splitString(String original, String split) {
        return original.split(split);
    }

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

    public static IData generateChangeData(IData d) {
        if (d instanceof DataMap) {
            IData toExit = new DataMap(new HashMap<String, IData>(), false);
            for (String s : d.asMap().keySet()) {
                toExit.memberSet(s, generateChangeData(d.asMap().get(s)));
            }
            return toExit;
        } else if (d instanceof DataList) {
            IData toExit = new DataList(new ArrayList<IData>(), false);
            for (IData item : d.asList()) {
                toExit.add(generateChangeData(item));
            }
            return toExit;
        } else return d;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, boolean b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataBool(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, IData d) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], d);
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, byte b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataByte(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, byte[] b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataByteArray(b, false));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, double b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataDouble(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, float b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataFloat(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, int b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataInt(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, int[] b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataIntArray(b, false));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, long b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataLong(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, short b) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataShort(b));
        return toExit;
    }

    @ZenMethod
    public static IData writeToNBT(IData data, String path, String s) {
        IData toExit = generateChangeData(data);
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length - 1; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }
        toExit.memberSet(pathToPoint[pathToPoint.length - 1], new DataString(s));
        return toExit;
    }

    @ZenMethod
    public static IData mergeNBT(IData... datas) {
        datas[datas.length - 1] = generateChangeData(datas[datas.length - 1]);
        for (int i = datas.length - 2; i >= 0; i--) {
            merge(datas[i], datas[datas.length - 1]);
        }
        return datas[datas.length - 1];
    }

    @ZenMethod
    public static IData getUnReadDataFromString(IData nbt, String path) {
        IData toExit = nbt;
        String[] pathToPoint = spilitToByte(path, (byte) '.');
        for (int i = 0; i < pathToPoint.length; i++) {
            toExit = toExit.asMap().get(pathToPoint[i]);
        }

        return toExit;
    }

    static boolean merge(IData d, IData d2) {
       /* if (d instanceof DataMap)
            for (String key : d.asMap().keySet()) {
                IData data = d.asMap().get(key);
                IData data1 = d2.asMap().get(key);
                if (data1 == null || !(data instanceof DataMap
                        || data1 instanceof DataMap) || !(
                        data instanceof DataList
                                || data1 instanceof DataList))
                    d2.memberSet(key, data);
                else merge(data, data1);
            }
        else if (d instanceof DataList)
            for (IData data :) {
            }*/
        return true;
    }


    public static String[] spilitToByte(String str, byte b) {
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
