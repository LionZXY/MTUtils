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

            for (JsonElement obj : array) {
                jsonObject = obj.getAsJsonObject();
                String part[] = jsonObject.get("nameid").getAsString().split(":");
                Block oreBlock = GameRegistry.findBlock(part[0], part[1]);
                String texturepath;
                String name;

                if (oreBlock != null) {
                    if (part.length == 3){

                        if(jsonObject.get("texturepath") != null)
                            texturepath = jsonObject.get("texturepath").getAsString();
                        else texturepath = oreBlock.getIcon(1,Integer.parseInt(part[2])).getIconName();

                        if(jsonObject.get("name") != null)
                            name = jsonObject.get("name").getAsString();
                        else name = new ItemStack(oreBlock,1,Integer.parseInt(part[2])).getDisplayName();

                        registerOreBlock(oreBlock, Integer.parseInt(part[2]), texturepath, name, event);
                    }
                    else if (part.length == 2){

                        if(jsonObject.get("texturepath") != null)
                            texturepath = jsonObject.get("texturepath").getAsString();
                        else texturepath = oreBlock.getIcon(1,0).getIconName();

                        if(jsonObject.get("name") != null)
                            name = jsonObject.get("name").getAsString();
                        else name = new ItemStack(oreBlock).getDisplayName();

                        registerOreBlock(oreBlock, 0, texturepath, name, event);
                    }

                } else FMLLog.bigWarning("Block " + jsonObject.get("nameid").getAsString() + " Not Found");
            }

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
