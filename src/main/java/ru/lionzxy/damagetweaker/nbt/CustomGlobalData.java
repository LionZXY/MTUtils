package ru.lionzxy.damagetweaker.nbt;

import net.minecraft.client.Minecraft;

import java.io.File;

/**
 * Created by lionzxy on 6/8/16.
 */
public class CustomGlobalData extends CustomData {
    private static CustomGlobalData instance = null;

    public static CustomGlobalData getInstance(){
        if(instance == null)
            instance = new CustomGlobalData();
        return instance;

    }

    @Override
    File getFile() {
        return new File(Minecraft.getMinecraft().mcDataDir, "MTUtilsData.json");
    }
}
