package ru.lionzxy.damagetweaker.mods;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gregapi.data.CS;
import gregapi.recipes.Recipe;
import minetweaker.MineTweakerAPI;
import minetweaker.api.formatting.IFormattedText;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import ru.lionzxy.damagetweaker.MTUtilsMod;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * ${PACKAGE_NAME} Created by LionZXY on 22.11.2015. DamageTweaker
 */
@ZenClass("mods.MTUtilsGT")
public class GregTechHandler {
	private static GregTechHandler sHandler = new GregTechHandler();
	
	private Map<String, List<Recipe>> mAddedRecipes = new HashMap<String, List<Recipe>>();

	@ZenMethod
	public static void addCustomRecipe(String fieldName, boolean aOptimize, long aEUt, long aDuration, long[] aChances,
			IItemStack[] aInputs, ILiquidStack[] aFluidInputs, ILiquidStack[] aFluidOutputs, IItemStack... aOutputs) {
		try {
			sHandler.removeAddedRecipes();
			
			Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(fieldName);

			ItemStack[] inputs = MTUtilsMod.toStacks(aInputs);
			FluidStack[] fluidInputs = MTUtilsMod.toFluids(aFluidInputs);

			ItemStack[] outputs = MTUtilsMod.toStacks(aOutputs);
			FluidStack[] fluidOutputs = MTUtilsMod.toFluids(aFluidOutputs);

			Recipe recipe = new Recipe(aOptimize, true, true, inputs, outputs, CS.NI, aChances, fluidInputs,
					fluidOutputs, aDuration, aEUt, 0L);
			
			List<Recipe> recipes = sHandler.mAddedRecipes.get(fieldName);
			if (recipes == null){
				recipes = new LinkedList<Recipe>();
				sHandler.mAddedRecipes.put(fieldName, recipes);
			}
			recipes.add(recipe);
			
			recipeMap.addRecipe(recipe, false, false, false);
			System.out.println("[MTUtilsGT] Recipe for variable " + fieldName + " add!");
			MineTweakerAPI.logInfo("[MTUtilsGT] Recipe for variable " + fieldName + " add!");
		} catch (Exception e) {
			MineTweakerAPI.logError(
					"[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);
		}
	}

	private void removeAddedRecipes() {
		for(Entry<String, List<Recipe>> entry : mAddedRecipes.entrySet()){
			Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(entry.getKey());
			if (recipeMap != null){
				recipeMap.mRecipeList.removeAll(entry.getValue());
			}
		}
		mAddedRecipes.clear();
	}

	@ZenMethod
	public static void addCustomRecipe(String fieldName, boolean aOptimize, long aEUt, long aDuration, long[] aChances,
			IItemStack[] aInputs, ILiquidStack aFluidInput, ILiquidStack aFluidOutput, IItemStack... aOutputs) {
		addCustomRecipe(fieldName, aOptimize, aEUt, aDuration, aChances, aInputs, new ILiquidStack[] { aFluidInput },
				new ILiquidStack[] { aFluidOutput }, aOutputs);
	}

	@ZenMethod
	public static void addCustomRecipe(String fieldName, boolean aOptimize, long aEUt, long aDuration, long[] aChances,
			IItemStack[] aInputs, IItemStack... aOutputs) {
		addCustomRecipe(fieldName, aOptimize, aEUt, aDuration, aChances, aInputs, new ILiquidStack[] {},
				new ILiquidStack[] {}, aOutputs);
	}

	@ZenMethod
	public static void removeAllRecipes(String fieldName, IItemStack... output) {
		try {
			Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(fieldName);

			List<Recipe> recipes = recipeMap.getNEIRecipes(MTUtilsMod.toStacks(output));
			if (recipes != null) {
				recipeMap.mRecipeList.removeAll(recipes);
			}
		} catch (Exception e) {
			MineTweakerAPI.logError(
					"[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);

		}

	}

	@ZenMethod
	public static void removeRecipe(String fieldName, IItemStack[] aInputs, ILiquidStack[] aFluidInputs, IItemStack... output) {
		try {
			Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(fieldName);

			List<Recipe> recipes = recipeMap.getNEIRecipes(MTUtilsMod.toStacks(output));
			if (recipes != null) {
				for(Recipe recipe : recipes){
					ItemStack[] inputs = MTUtilsMod.toStacks(aInputs);
					FluidStack[] fluidInputs = MTUtilsMod.toFluids(aFluidInputs);

					if (recipe.isRecipeInputEqual(false, true, fluidInputs, inputs)){
						recipeMap.mRecipeList.remove(recipe);
					}
				}
			}
		} catch (Exception e) {
			MineTweakerAPI.logError(
					"[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);

		}

	}

	@ZenMethod
	public static void test(IFormattedText recipeName) {
	}
}
