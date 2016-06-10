package ru.lionzxy.damagetweaker;

import com.google.common.io.Files;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import ru.lionzxy.damagetweaker.handlers.HandlerHelper;
import ru.lionzxy.damagetweaker.handlers.TicksHandler;
import ru.lionzxy.damagetweaker.models.CustomGlobalData;

import java.io.File;

/**
 * Created by nikit on 10.09.2015.
 */

@Mod(name = "MTUtils", modid = "MTUtils", version = "1.5", dependencies = "after:UndergroundBiomes;" +
        ";after:TabulaRasa")
public class MTUtilsMod {
    public static Configuration configuration;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configuration = new Configuration(
                new File(Loader.instance().getConfigDir() + "/MTUtils", "general.cfg"),
                "1.5");
        configuration.load();

        try {
            File globalTemplare = new File(Loader.instance().getConfigDir() + "/MTUtils/data/", "newglobal.nbt");
            File globalNow = new File(Minecraft.getMinecraft().mcDataDir, "MTUtilsData.nbt");
            if (!globalNow.exists())
                if (globalTemplare.exists())
                    Files.copy(globalTemplare, globalNow);
                else new CustomGlobalData().save();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        HandlerHelper.registerAllHandler();
        configuration.save();
    }



    @Mod.EventHandler
    public void onServerLoaded(FMLServerStartedEvent event) {

        TicksHandler.onServerLoaded(event);
    }


    public static ItemStack toStack(IItemStack iStack) {
        if (iStack == null) {
            return null;
        } else {
            Object internal = iStack.getInternal();
            if (!(internal instanceof ItemStack)) {
                MineTweakerAPI.logError("[Damage Tweaker] Not a valid item stack: " + iStack);
            }

            return (ItemStack) internal;
        }
    }

    public static Block toBlock(IItemStack input) {
        if (toStack(input).getItem() instanceof ItemBlock)
            return ((ItemBlock) toStack(input).getItem()).getBlock();
        return null;
    }

    public static ItemStack[] toStacks(IItemStack stack[]) {
        ItemStack toExit[] = new ItemStack[stack.length];
        for (int i = 0; i < stack.length; i++)
            toExit[i] = toStack(stack[i]);
        return toExit;
    }

    public static FluidStack toFluid(ILiquidStack iStack) {
        if (iStack == null) {
            return null;
        } else return FluidRegistry.getFluidStack(iStack.getName(), iStack.getAmount());
    }

    public static FluidStack[] toFluids(ILiquidStack[] iStack) {
        FluidStack[] stack = new FluidStack[iStack.length];
        for (int i = 0; i < stack.length; i++)
            stack[i] = toFluid(iStack[i]);
        return stack;
    }
}
