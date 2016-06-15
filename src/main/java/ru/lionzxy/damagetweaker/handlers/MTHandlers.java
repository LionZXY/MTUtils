package ru.lionzxy.damagetweaker.handlers;


import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import minetweaker.MineTweakerAPI;
import minetweaker.api.formatting.IFormattedText;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.oredict.IOreDictEntry;
import minetweaker.api.tooltip.IngredientTooltips;
import minetweaker.mc1710.formatting.FormattedString;
import minetweaker.mc1710.formatting.IMCFormattedString;
import minetweaker.mc1710.item.MCItemStack;
import minetweaker.mc1710.oredict.MCOreDictEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.oredict.OreDictionary;
import ru.lionzxy.damagetweaker.MTUtilsMod;
import ru.lionzxy.damagetweaker.models.DropsObject;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by nikit on 10.09.2015.
 */

@ZenClass("mods.MTUtils.Utils")
public class MTHandlers {
    public static final HashMap<Item, String> itemToString = new HashMap<Item, String>();

    static {
        TicksHandler.addTasksAfterSererLoaded(new Runnable() {
            @Override
            public void run() {
                for (Object object : GameData.getItemRegistry().getKeys()) {
                    itemToString.put(GameData.getItemRegistry().getObject((String) object), (String) object);
                }
            }
        });
    }


    //No java-style
    @ZenMethod public static void               setItemMaxDamage(IItemStack input, int damage){                             MTUtilsMod.toStack(input).getItem().setMaxDamage(damage);}
    @ZenMethod public static void               setBlockUnbreakable(IItemStack input) {                                     MTUtilsMod.toBlock(input).setBlockUnbreakable();}
    @ZenMethod public static void               setHardness(IItemStack input, float hardness) {                             MTUtilsMod.toBlock(input).setHardness(hardness);}
    @ZenMethod public static void               setLightLevel(IItemStack input, float ll) {                                 MTUtilsMod.toBlock(input).setLightLevel(ll);}
    @ZenMethod public static void               setLightOpacity(IItemStack input, int opacity) {                            MTUtilsMod.toBlock(input).setLightOpacity(opacity);}
    @ZenMethod public static void               setResistance(IItemStack input, float resistance) {                         MTUtilsMod.toBlock(input).setResistance(resistance);}
    @ZenMethod public static void               setTextureName(IItemStack input, String textureName) {                      MTUtilsMod.toBlock(input).setBlockTextureName(textureName);}
    @ZenMethod public static void               clearDrops() {                                                              DropsObject.dropsObj = new ArrayList<DropsObject>();}
    @ZenMethod public static int                getHarvestLevel(IItemStack input) {                                         return MTUtilsMod.toBlock(input).getHarvestLevel(MTUtilsMod.toStack(input).getItemDamage());}
    @ZenMethod public static String             getHarvestTool(IItemStack input) {                                          return MTUtilsMod.toBlock(input).getHarvestTool(MTUtilsMod.toStack(input).getItemDamage());}
    @ZenMethod public static String             getTextureName(IItemStack input, int side) {                                return MTUtilsMod.toBlock(input).getBlockTextureFromSide(side).getIconName();}
    @ZenMethod public static int                getItemDamage(IItemStack input) {                                           return MTUtilsMod.toStack(input).getItemDamage();}
    @ZenMethod public static int                getItemMaxDamage(IItemStack input) {                                        return MTUtilsMod.toStack(input).getItem().getMaxDamage();}
    @ZenMethod public static int                StringToInt(IFormattedText from) {                                          return Integer.parseInt(FormattedTextToString(from));}
    @ZenMethod public static int                StringToInt(String from) {                                                  return Integer.parseInt(from);}
    @ZenMethod public static int                FormattedTextToInt(IFormattedText from){                                    return StringToInt(FormattedTextToString(from));}
    @ZenMethod public static float              FormattedTextToFloat(IFormattedText from) {                                 return Float.parseFloat(FormattedTextToString(from));}
    @ZenMethod public static float              StringToFloat(String from) {                                                return Float.parseFloat(from);}
    @ZenMethod public static String             IntToString(int i) {                                                        return String.valueOf(i);}
    @ZenMethod public static String             FloatToString(float i) {                                                    return String.valueOf(i);}
    @ZenMethod public static String             FormattedTextToString(IFormattedText text) {                                return ((FormattedString) text).getTooltipString();}
    @ZenMethod public static String             FormattedStringToString(IMCFormattedString text) {                          return text.getTooltipString();}
    @ZenMethod public static int                randomInt(int from, int to) {                                               return randomInt(to - from) + from;}
    @ZenMethod public static int                randomInt(int range) {                                                      return new Random().nextInt(range);}
    @ZenMethod public static String             getItemStackID(IItemStack itemStack) {                                      return itemToString.get(MTUtilsMod.toStack(itemStack).getItem());}
    @ZenMethod public static String             getOreDictEntryID(IOreDictEntry entry) {                                    return entry.getName();}
    @ZenMethod public static boolean            isNull(Object object) {                                                     return object == null;}
    @ZenMethod public static boolean            isNull(IIngredient object) {                                                return object == null;}
    @ZenMethod public static boolean            isNull(IItemStack object) {                                                 return object == null;}
    @ZenMethod public static boolean            isNull(IOreDictEntry object) {                                              return object == null;}
    @ZenMethod public static IOreDictEntry      getOreDictEntryFromString(String in) {                                      return new MCOreDictEntry(OreDictionary.getOreID(in));}
    @ZenMethod public static IOreDictEntry      getOreDictEntryFromString(IFormattedText in) {                              return getOreDictEntryFromString(FormattedTextToString(in));}
    @ZenMethod public static IOreDictEntry      getOreDictEntryFromString(IMCFormattedString in) {                          return getOreDictEntryFromString(FormattedStringToString(in));}
    @ZenMethod public static IFormattedText     StringToFormattedText(String in) {                                          return new FormattedString(in);}
    @ZenMethod public static IMCFormattedString StringToFormattedString(String in) {                                        return new FormattedString(in);}
    @ZenMethod public static void               executeCommand(IFormattedText cmd) {                                        executeCommand(FormattedTextToString(cmd));}
    @ZenMethod public static void               executeCommand(final String cmd) {                                          TicksHandler.addTasksAfterSererLoaded(new Runnable() {@Override public void run() {MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), cmd);}});}
    @ZenMethod public static void               addMultilineShiftTooltip(IItemStack stack, String strs) {                   addMultilineShiftTooltip(stack, StringToFormattedString(strs));}
    @ZenMethod public static void               addMultilineShiftTooltip(IItemStack stack, String strs, String s) {         addMultilineTooltip(stack,StringToFormattedString(strs), s);}
    @ZenMethod public static void               addMultilineShiftTooltip(IItemStack stack, IFormattedText strs) {           addMultilineShiftTooltip(stack, strs, "\n");}
    @ZenMethod public static void               addMultilineShiftTooltip(IItemStack stack, IFormattedText strs, String s) { for (String str : split(FormattedTextToString(strs), s)) IngredientTooltips.addShiftTooltip(stack, StringToFormattedString(str));}
    @ZenMethod public static void               addMultilineTooltip(IItemStack stack, String strs, String s) {              addMultilineTooltip(stack, StringToFormattedString(strs),s);}
    @ZenMethod public static void               addMultilineTooltip(IItemStack stack, String strs) {                        addMultilineTooltip(stack, StringToFormattedString(strs));}
    @ZenMethod public static void               addMultilineTooltip(IItemStack stack, IFormattedText strs) {                addMultilineTooltip(stack, strs, "\n");}
    @ZenMethod public static void               addMultilineTooltip(IItemStack stack, IFormattedText strs, String s) {      for (String str : split(FormattedTextToString(strs), s)) IngredientTooltips.addTooltip(stack, StringToFormattedString(str));}



    @ZenMethod public static IItemStack[] getCrossMatch(IOreDictEntry... oreDictEntries) {
        List<ItemStack> oreDict = OreDictionary.getOres(oreDictEntries[0].getName());
        List<IItemStack> toExit = new ArrayList<IItemStack>();
        for (int i = 0; i < oreDict.size(); i++)
            if (contains(oreDict.get(i), oreDictEntries))
                toExit.add(new MCItemStack(oreDict.get(i)));
        IItemStack toExitArr[] = new IItemStack[toExit.size()];
        for (int i = 0; i < toExit.size(); i++)
            toExitArr[i] = toExit.get(i);
        return toExitArr;
    }

    @ZenMethod public static IIngredient getIngredientFromString(String in) {
        String items[] = in.split(":");
        if (items.length == 2)
            return new MCItemStack(new ItemStack(GameRegistry.findItem(items[0], items[1])));

        return new MCItemStack(new ItemStack(GameRegistry.findItem(items[0], items[1]), Integer.parseInt(items[2])));
    }

    @ZenMethod public static IItemStack getItemStackFromString(String in) {
        String items[] = in.split(":");
        if (items.length == 2)
            return new MCItemStack(new ItemStack(GameRegistry.findItem(items[0], items[1])));

        return new MCItemStack(new ItemStack(GameRegistry.findItem(items[0], items[1]), 1, Integer.parseInt(items[2])));
    }

    @ZenMethod public static void setBlockDrops(@Nullable IItemStack harvester, IItemStack block, IItemStack drops[], float quantiDrop[], IItemStack falseDrops[]) {

        if (DropsObject.dropsObj != null)
            if (block != null && MTUtilsMod.toBlock(block) != null) {
                DropsObject.dropsObj.add(new DropsObject(MTUtilsMod.toStack(harvester), MTUtilsMod.toStacks(drops),
                        quantiDrop, MTUtilsMod.toStacks(falseDrops), MTUtilsMod.toStack(block)));
            } else MineTweakerAPI.logError("BLOCK MUST NOT BE NULL!!!");
        else MineTweakerAPI.logError("Use MTUtils.clearDrop(); in start your script!!!");
    }

    @ZenMethod public static boolean eqItemStack(IItemStack... stacks) {
        ItemStack[] stacks1 = MTUtilsMod.toStacks(stacks);
        for (int i = 1; i < stacks1.length; i++)
            if (!stacks1[i].isItemEqual(stacks1[i - 1]))
                return false;
        return true;
    }

    @ZenMethod public static boolean eqString(String... strs) {
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].equals(strs[i - 1]))
                return false;
        return true;
    }

    @ZenMethod public static IFormattedText[] split(IFormattedText str, String separator){
        String[] tmpStr = split(FormattedTextToString(str),separator);
        IFormattedText[] toExit = new IFormattedText[tmpStr.length];
        for(int i = 0; i < toExit.length; i++)
            toExit[i] = StringToFormattedString(tmpStr[i]);
        return toExit;
    }

    @ZenMethod public static String[] split(String original, String split) {
        if (split.length() == 1)
            return split(original, split.charAt(0));
        return original.split(split);
    }

    @ZenMethod public static void setHarvestLevel(IItemStack input, String tooltip, int harvestLevel) {
        if (MTUtilsMod.toStack(input).getItemDamage() == 0)
            MTUtilsMod.toBlock(input).setHarvestLevel(tooltip, harvestLevel);
        else
            MTUtilsMod.toBlock(input).setHarvestLevel(tooltip, harvestLevel, MTUtilsMod.toStack(input).getItemDamage());
    }

    @ZenMethod public static IFormattedText concat(IFormattedText... strs) {
        String strings[] = new String[strs.length];
        for (int i = 0; i < strs.length; i++)
            strings[i] = FormattedTextToString(strs[i]);
        return StringToFormattedString(concat(strings));
    }

    @ZenMethod public static String concat(String... strs) {StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(str);
        return sb.toString();
    }

    public static boolean contains(ItemStack itemStack, IOreDictEntry... oreDictEntry) {
        for (IOreDictEntry oreDictEntry1 : oreDictEntry)
            if (!oreDictEntry1.contains(new MCItemStack(itemStack)))
                return false;
        return true;
    }
    public static String[] split(String original, char split) {
        return NBTHandlers.splitToByte(original, (byte) split);}

}
