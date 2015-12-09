package ru.lionzxy.damagetweaker.mods.ubc;

import Zeno410Utils.MinecraftName;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import exterminatorJeff.undergroundBiomes.api.UBAPIHook;
import exterminatorJeff.undergroundBiomes.api.UBOreTexturizer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * Created by nikit_000 on 06.10.2015.
 */
public class UBC {
    public static File jsonFile = new File(Loader.instance().getConfigDir() + "/MTUtils", "ubc.json");

    public static void UBCLoad(FMLPreInitializationEvent event) {
        if (!jsonFile.canWrite())
            createNewFile();
        parseAndAdd(event);
    }

    static void parseAndAdd(FMLPreInitializationEvent event) {
        JsonObject jsonObject;
        try {
            JsonArray array = new JsonParser().parse(new JsonReader(new FileReader(jsonFile))).getAsJsonArray();

            StringBuilder translate = new StringBuilder();
            for (JsonElement obj : array) {
                jsonObject = obj.getAsJsonObject();
                String part[] = jsonObject.get("nameid").getAsString().split(":");
                Block oreBlock = GameRegistry.findBlock(part[0], part[1]);
                if (oreBlock != null) {
                    if (part.length == 3)
                        registerOreBlock(oreBlock, Integer.parseInt(part[2]), jsonObject.get("texturepath").getAsString(), jsonObject.get("name").getAsString(), event);
                    else if (part.length == 2)
                        registerOreBlock(oreBlock, 0, jsonObject.get("texturepath").getAsString(), jsonObject.get("name").getAsString(), event);
                    translate.append("ore." + jsonObject.get("name").getAsString() + ".name=" + jsonObject.get("name").getAsString() + "\n");

                } else FMLLog.bigWarning("Block " + jsonObject.get("name").getAsString() + " Not Found");
            }
            StringTranslate.inject(new ByteArrayInputStream(translate.toString().getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void createNewFile() {
        try {
            jsonFile.getParentFile().mkdirs();
            jsonFile.createNewFile();
            JsonArray array = new JsonArray();
            JsonObject exampleObject = new JsonObject();
            exampleObject.addProperty("name", "exampleblock");
            exampleObject.addProperty("nameid", "minecraft:block");
            exampleObject.addProperty("texturepath", "minecraft:block");
            array.add(exampleObject);

            FileOutputStream os = new FileOutputStream(jsonFile);
            os.write(array.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void registerOreBlock(Block block, int meta, String texturename, String name, FMLPreInitializationEvent event) throws Exception {
        ItemStack placeholder = new ItemStack(block, 1, meta);
        Method method = null;
        try {
            method = Class.forName("exterminatorJeff.undergroundBiomes.common.OreUBifyRequester").getDeclaredMethod("setupUBOre",
                    Block.class, int.class, String.class, MinecraftName.class, FMLPreInitializationEvent.class);
            method.setAccessible(true);
            method.invoke(UBAPIHook.ubAPIHook.ubOreTexturizer, block, meta, texturename,
                    (MinecraftName) new MinecraftUBCName(block.getUnlocalizedName(), name), event);
        } catch (Exception e) {
            if (e instanceof UBOreTexturizer.BlocksAreAlreadySet)
                FMLLog.warning(placeholder.getDisplayName() + " is already registered with UBC. Skipping");
            else {
                e.printStackTrace();
                throw new Exception();
            }
            placeholder = null;

        }
        if (placeholder != null)
            FMLLog.fine("Added " + placeholder.getDisplayName() + " for Underground Biomes Ore Texturization");
    }
}
