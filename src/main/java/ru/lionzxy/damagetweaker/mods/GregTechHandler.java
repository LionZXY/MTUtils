package ru.lionzxy.damagetweaker.mods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gregapi.data.CS;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import minetweaker.MineTweakerAPI;
import minetweaker.api.formatting.IFormattedText;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import ru.lionzxy.damagetweaker.MTUtilsMod;
import ru.lionzxy.damagetweaker.utils.UndoableAction;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * ${PACKAGE_NAME} Created by LionZXY on 22.11.2015. DamageTweaker
 */
@ZenClass("mods.MTUtilsGT")
public class GregTechHandler {
	@ZenMethod
	public static void addFluidInput(ILiquidStack pGTusedFluid, ILiquidStack pOtherFluid) {
		ILiquidStack[][] mappedLiquids = new ILiquidStack[1][];
		mappedLiquids[0] = new ILiquidStack[] { pGTusedFluid, pOtherFluid };
		addFluidInput(mappedLiquids);
	}

	@ZenMethod
	public static void addFluidInput(ILiquidStack[][] pMappedFluids) {
		Map<FluidStack, FluidStack> fluidMap = new HashMap<FluidStack, FluidStack>();
		for (int i = 0; i < pMappedFluids.length; i++) {
			if (pMappedFluids[i].length != 2 || pMappedFluids[i][0] == null || pMappedFluids[i][1] == null) {
				MineTweakerAPI.logError("[MTUtilsGT] Fluid map must contain two fluids, null values are not allowed!");
				MineTweakerAPI.logError("[MTUtilsGT] Please check combination: " + i);
			} else {
				FluidStack gtFluid = MTUtilsMod.toFluid(pMappedFluids[i][0]);
				FluidStack otherFluid = MTUtilsMod.toFluid(pMappedFluids[i][1]);

				if (gtFluid == null) {
					MineTweakerAPI.logError(
							"[MTUtilsGT] GT fluid not found for name: " + pMappedFluids[i][0].getDisplayName());
				} else if (otherFluid == null) {
					MineTweakerAPI.logError(
							"[MTUtilsGT] other fluid not found for name: " + pMappedFluids[i][1].getDisplayName());
				} else {
					fluidMap.put(gtFluid, otherFluid);
				}
			}
		}

		final List<Runnable> applyRunnables = new LinkedList<Runnable>();
		final List<Runnable> undoRunnables = new LinkedList<Runnable>();

		for (Entry<String, RecipeMap> entry : Recipe.RecipeMap.RECIPE_MAPS.entrySet()) {
			RecipeMap map = entry.getValue();
			for (Recipe recipe : map.getNEIAllRecipes()) {
				if (recipe.mFluidInputs != null) {
					List<FluidStack> fluidInputs = Arrays.asList(recipe.mFluidInputs);
					List<FluidStack> newFluidInputs = new LinkedList<FluidStack>();
					int mappedFluids = 0;
					for (FluidStack fluid : fluidInputs) {
						FluidStack newFluidInput = getMappedFluid(fluidMap, fluid);
						if (newFluidInput != null) {
							newFluidInput = new FluidStack(newFluidInput.getFluid(), fluid.amount);
							mappedFluids++;
						} else {
							newFluidInput = new FluidStack(fluid.getFluid(), fluid.amount);
						}
						newFluidInputs.add(newFluidInput);
					}

					final boolean fakeRecipe = recipe.mFakeRecipe;
					final boolean hidden = recipe.mHidden;
					final String fluidText = mappedFluids == 1 ? "fluid" : String.valueOf(mappedFluids) + " fluids";
					final String key = entry.getKey();

					if (mappedFluids > 0) {
						final Recipe newRecipe = new Recipe(true, false, recipe.mInputs, recipe.mOutputs,
								recipe.mSpecialItems, recipe.mChances, newFluidInputs.toArray(new FluidStack[] {}),
								recipe.mFluidOutputs, recipe.mDuration, recipe.mEUt, recipe.mSpecialValue);
						final RecipeMap recipes = entry.getValue();

						applyRunnables.add(new Runnable() {
							@Override
							public void run() {
								recipes.addRecipe(newRecipe, true, fakeRecipe, hidden);

								String message = "[MTUtilsGT] Recipe with replaced " + fluidText + " for variable "
										+ key + " add!";
								System.out.println(message);
								MineTweakerAPI.logInfo(message);
							}
						});

						undoRunnables.add(new Runnable() {
							@Override
							public void run() {
								recipes.getNEIAllRecipes().remove(newRecipe);
							}
						});
					}
				}
			}
		}

		Runnable applyRunnable = new Runnable() {
			@Override
			public void run() {
				for (Runnable runnable : applyRunnables) {
					runnable.run();
				}
			}
		};

		Runnable undoRunnable = new Runnable() {
			@Override
			public void run() {
				for (Runnable runnable : undoRunnables) {
					runnable.run();
				}
			}
		};

		MineTweakerAPI.apply(new UndoableAction("Map fluids", applyRunnable, "Undo mapped fluids", undoRunnable));
	}

	@ZenMethod
	public static void addCustomRecipe(final String fieldName, boolean aOptimize, long aEUt, long aDuration,
			long[] aChances, IItemStack[] aInputs, ILiquidStack[] aFluidInputs, ILiquidStack[] aFluidOutputs,
			IItemStack... aOutputs) {
		try {
			final Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(fieldName);

			ItemStack[] inputs = MTUtilsMod.toStacks(aInputs);
			FluidStack[] fluidInputs = MTUtilsMod.toFluids(aFluidInputs);

			ItemStack[] outputs = MTUtilsMod.toStacks(aOutputs);
			FluidStack[] fluidOutputs = MTUtilsMod.toFluids(aFluidOutputs);

			final Recipe recipe = new Recipe(aOptimize, true, true, inputs, outputs, CS.NI, aChances, fluidInputs,
					fluidOutputs, aDuration, aEUt, 0L);

			Runnable applyRunnable = new Runnable() {
				@Override
				public void run() {
					recipeMap.addRecipe(recipe, false, false, false);

					System.out.println("[MTUtilsGT] Recipe for variable " + fieldName + " add!");
					MineTweakerAPI.logInfo("[MTUtilsGT] Recipe for variable " + fieldName + " add!");
				}
			};

			Runnable undoRunnable = new Runnable() {
				@Override
				public void run() {
					recipeMap.getNEIAllRecipes().remove(recipe);
				}
			};

			MineTweakerAPI.apply(new UndoableAction("Add recipe to " + fieldName, applyRunnable,
					"Undo added recipe from " + fieldName, undoRunnable));
		} catch (Exception e) {
			MineTweakerAPI.logError(
					"[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);
		}
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
			final Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(fieldName);

			final List<Recipe> recipes = recipeMap.getNEIRecipes(MTUtilsMod.toStacks(output));
			if (recipes != null && !recipes.isEmpty()) {
				Runnable applyRunnable = new Runnable() {
					@Override
					public void run() {
						recipeMap.mRecipeList.removeAll(recipes);
					}
				};

				Runnable undoRunnable = new Runnable() {
					@Override
					public void run() {
						recipeMap.mRecipeList.addAll(recipes);
					}
				};

				MineTweakerAPI.apply(new UndoableAction("Remove all recipes from " + fieldName, applyRunnable,
						"Add removed recipes to " + fieldName, undoRunnable));
			}
		} catch (Exception e) {
			MineTweakerAPI.logError(
					"[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);

		}

	}

	@ZenMethod
	public static void removeRecipe(String fieldName, IItemStack[] aInputs, ILiquidStack[] aFluidInputs,
			IItemStack... output) {
		try {
			final Recipe.RecipeMap recipeMap = Recipe.RecipeMap.RECIPE_MAPS.get(fieldName);
			final List<Recipe> toRemove = new LinkedList<Recipe>();

			List<Recipe> recipes = recipeMap.getNEIRecipes(MTUtilsMod.toStacks(output));
			if (recipes != null) {
				for (Recipe recipe : recipes) {
					ItemStack[] inputs = MTUtilsMod.toStacks(aInputs);
					FluidStack[] fluidInputs = MTUtilsMod.toFluids(aFluidInputs);

					if (recipe.isRecipeInputEqual(false, true, fluidInputs, inputs)) {
						toRemove.add(recipe);
					}
				}
			}

			if (!toRemove.isEmpty()) {
				Runnable applyRunnable = new Runnable() {
					@Override
					public void run() {
						for (Recipe recipe : toRemove) {
							recipeMap.getNEIAllRecipes().remove(recipe);
						}
					}
				};

				Runnable undoRunnable = new Runnable() {
					@Override
					public void run() {
						for (Recipe recipe : toRemove) {
							recipeMap.add(recipe);
						}
					}
				};

				MineTweakerAPI.apply(new UndoableAction("Remove recipes from " + fieldName, applyRunnable,
						"Add removed recipes to " + fieldName, undoRunnable));
			}
		} catch (Exception e) {
			MineTweakerAPI.logError(
					"[MTUtilsGT] Not found variable " + fieldName + " in gregapi.recipes.Recipe.RecipeMap\n", e);

		}

	}

	@ZenMethod
	public static void test(IFormattedText recipeName) {
	}

	private static FluidStack getMappedFluid(Map<FluidStack, FluidStack> fluidMap, FluidStack gtFluidToMap) {
		FluidStack newFluidInput = null;
		for (Entry<FluidStack, FluidStack> entry : fluidMap.entrySet()) {
			if (gtFluidToMap.isFluidEqual(entry.getKey())) {
				newFluidInput = new FluidStack(entry.getValue(), gtFluidToMap.amount);
				break;
			}
		}
		return newFluidInput;
	}

}
