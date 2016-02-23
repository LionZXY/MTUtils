package ru.lionzxy.damagetweaker.mods;

import gregapi.recipes.Recipe;
import minetweaker.MineTweakerAPI;
import minetweaker.api.formatting.IFormattedText;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import ru.lionzxy.damagetweaker.DamageTweaker;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * ${PACKAGE_NAME}
 * Created by LionZXY on 22.11.2015.
 * DamageTweaker
 */
@ZenClass("mods.MTUtilsGT")
public class GregTechHandler {

    @ZenMethod
    public static void addCustomRecipe(String fieldName, boolean aOptimize, long aEUt, long aDuration, long[] aChances, IItemStack[] aInputs, ILiquidStack aFluidInput, ILiquidStack aFluidOutput, IItemStack... aOutputs) {
        try {
            Recipe.RecipeMap recipeMap = (Recipe.RecipeMap) Recipe.RecipeMap.class.getField(fieldName).get(null);
            recipeMap.addRecipeX(aOptimize, aEUt, aDuration, aChances, DamageTweaker.toStacks(aInputs), DamageTweaker.toFluid(aFluidInput), DamageTweaker.toFluid(aFluidOutput), DamageTweaker.toStacks(aOutputs));
            System.out.println("[MTUtilsGT] Recipe for variable " + fieldName + " add!");
            MineTweakerAPI.logInfo("[MTUtilsGT] Recipe for variable " + fieldName + " add!");
        } catch (Exception e) {
            MineTweakerAPI.logError("[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);
        }
    }

    @ZenMethod
    public static void removeCustomRecipe(String fieldName, IItemStack... output) {
        try {
            Recipe.RecipeMap recipeMap = (Recipe.RecipeMap) Recipe.RecipeMap.class.getField(fieldName).get(null);

            for (Recipe recipe : recipeMap.getNEIRecipes(DamageTweaker.toStacks(output))) {
                recipeMap.mRecipeList.remove(recipe);
            }
        } catch (Exception e) {
            MineTweakerAPI.logError("[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);

        }

    }

    @ZenMethod
    public static void test(IFormattedText recipeName) {
    }
}
