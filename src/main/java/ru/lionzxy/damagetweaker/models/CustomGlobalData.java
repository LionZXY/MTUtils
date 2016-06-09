package ru.lionzxy.damagetweaker.models;

import minetweaker.api.data.IData;
import net.minecraft.client.Minecraft;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.io.File;

/**
 * Created by lionzxy on 6/8/16.
 */
@ZenClass("mods.MTUtils.GlobalData")
public class CustomGlobalData extends CustomData {
    private static CustomGlobalData instance = null;

    public static CustomGlobalData getInstance(){
        if(instance == null)
            instance = new CustomGlobalData();
        return instance;

    }

    @Override
    File getFile() {
        return new File(Minecraft.getMinecraft().mcDataDir, "MTUtilsData.nbt");
    }

    @ZenMethod
    public static IData get() {
        try {
            return getInstance().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ZenMethod
    public static void set(IData data) {
        try {
            getInstance().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ZenMethod
    public static boolean exists(){
        return getInstance().getFile().exists();
    }
}
