package ru.lionzxy.damagetweaker.handlers;


import minetweaker.MineTweakerAPI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import ru.lionzxy.damagetweaker.MTUtilsMod;
import ru.lionzxy.damagetweaker.models.CustomGlobalData;
import ru.lionzxy.damagetweaker.models.CustomWorldData;

/**
 * Created by lionzxy on 6/9/16.
 */
public class HandlerHelper {

    public static void registerAllHandler() {
        MinecraftForge.EVENT_BUS.register(new DropHandler());
        MineTweakerAPI.registerClass(NBTHandlers.class);
        MineTweakerAPI.registerClass(CustomGlobalData.class);
        MineTweakerAPI.registerClass(CustomWorldData.class);
        MineTweakerAPI.registerClass(MTHandlers.class);
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()
                && MTUtilsMod.configuration.get("general", "ThankMessage", true).getBoolean())
            MinecraftForge.EVENT_BUS.register(new FirstLoginHandler());
    }
}
