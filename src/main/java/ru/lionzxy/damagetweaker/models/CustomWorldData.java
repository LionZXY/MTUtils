package ru.lionzxy.damagetweaker.models;

import com.google.common.io.Files;
import cpw.mods.fml.common.Loader;
import minetweaker.api.data.IData;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.io.File;

/**
 * Created by lionzxy on 6/8/16.
 */
@ZenClass("mods.MTUtils.WorldData")
public class CustomWorldData extends CustomData {

    public static File getWorldFolder() {
        MinecraftServer mcServerInstance = MinecraftServer.getServer();
        String folderName = mcServerInstance.getFolderName();
        ISaveFormat saveFormat = mcServerInstance.getActiveAnvilConverter();
        SaveHandler saveHandler = (SaveHandler) saveFormat.getSaveLoader(folderName, false); // You will have to typecast here
        return saveHandler.getWorldDirectory();
    }

    public static CustomWorldData getWorld() {
        return new CustomWorldData();
    }

    @Override
    File getFile() {
        try {
            File worldTemplare = new File(Loader.instance().getConfigDir() + "/MTUtils/data/", "newworld.nbt");
            File worldNow = new File(getWorldFolder(), "MTUtilsData.nbt");
            if (!worldNow.exists())
                if (worldTemplare.exists())
                    Files.copy(worldTemplare, worldNow);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(getWorldFolder(), "MTUtilsData.nbt");
    }

    @ZenMethod
    public static IData get() {
        try {
            return getWorld().getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ZenMethod
    public static void set(IData data) {
        try {
            getWorld().setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ZenMethod
    public static boolean exists() {
        return getWorld().getFile().exists();
    }
}
