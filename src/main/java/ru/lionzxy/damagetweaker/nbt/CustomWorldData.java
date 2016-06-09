package ru.lionzxy.damagetweaker.nbt;

import minetweaker.api.data.IData;
import minetweaker.mc1710.data.NBTConverter;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.SaveHandler;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by lionzxy on 6/8/16.
 */
public class CustomWorldData extends CustomData{
    private static CustomWorldData worldData = null;

    public CustomWorldData(){
        super();
        worldData = this;
    }

    public static File getWorldFolder() {
        MinecraftServer mcServerInstance = MinecraftServer.getServer();
        String folderName = mcServerInstance.getFolderName();
        ISaveFormat saveFormat = mcServerInstance.getActiveAnvilConverter();
        SaveHandler saveHandler = (SaveHandler) saveFormat.getSaveLoader(folderName, false); // You will have to typecast here
        return saveHandler.getWorldDirectory();
    }

    public static CustomWorldData getWorld() {
        if (worldData == null)
            worldData = new CustomWorldData();
        return worldData;
    }

    @Override
    File getFile() {
        return new File(getWorldFolder(),"MTUtilsData.json");
    }
}
