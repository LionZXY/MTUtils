package ru.lionzxy.damagetweaker;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import exterminatorJeff.undergroundBiomes.api.NamedBlock;
import gregapi.data.CS;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import ru.lionzxy.damagetweaker.mods.GregTechHandler;
import ru.lionzxy.damagetweaker.mods.ubc.UBC;

/**
 * Created by nikit on 10.09.2015.
 */

@Mod(name = "MTUtils", modid = "MTUtils", dependencies = "after:UndergroundBiomes;" + "after:" + CS.ModIDs.API +
        ";after:TabulaRasa")
public class DamageTweaker {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (Loader.isModLoaded(NamedBlock.modid))
            UBC.UBCLoad(event);

    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new DropHandler());
        //FMLCommonHandler.instance().bus().register(new DropHandler());
        MineTweakerAPI.registerClass(MTHandlers.class);
        if (Loader.isModLoaded(CS.ModIDs.API))
            MineTweakerAPI.registerClass(GregTechHandler.class);
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
            return ((ItemBlock) toStack(input).getItem()).field_150939_a;
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
