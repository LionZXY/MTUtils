import mods.MTUtilsGT;

/*
 *  Remove all recipes, added with MTUtilsGT before. 
 *  It is necessary to get a correct function of minetweakers's reload.
 *  Use it only in one script at first position before adding new recipes.
 *  Otherwise it will remove to many recipes.
 */
MTUtilsGT.removeAddedRecipes();

//
//  Get a key for a GT machine
//
//  /MTUtils is the registered command, alias: /mtutils, /mtu
//
//  To get the key, use in game command "/MTUtils GTkey "Canner"". 
//  Double quotes are only necessary with spaces in GT machine name.
//  Name is case insensitive, regular expressions are allowed.
//  
//  To show all possible keys use "/MTUtils GTKeys [1...]" [optional select the first, second... block of ten keys]
//  First call uses only one input and one output liquid, second works with arrays.
//

/*  Add a recipe to the Canner.
 *  
 *  Fluids are optional, please note, "null" as fluids input AND "null" as fluids output dosn't work. 
 *  Use call without fluids in this case.
 */
MTUtilsGT.addCustomRecipe("gt.recipe.canner", false, 128, 128, [10000], [<minecraft:cobblestone>], <liquid:soda>*500, <liquid:water>*500, [<minecraft:diamond>]);
MTUtilsGT.addCustomRecipe("gt.recipe.canner", false, 128, 128, [10000], [<minecraft:cobblestone>], [<liquid:soda>*500], [<liquid:water>*500], [<minecraft:diamond>]);

/*
 *  Use alternate fluid in GT's recipes. First parameter is the current fluid, second the replacement.
 *  First fluid will be replaced in all GT's recipes.
 *  The result of the second call is the same like the first. But is possible to add more than one combination
 *  in one step.
 *  There is a little difference between first and second call. If there are more fluids to map in one
 *  recipe after the second only adds recipe with the completely mapped fluid.
 *  First calls would add mixed recipes too (one fluid mapped, second not).
 *
 *  Do not call multiple times the mapping of same fluid. It will add more than one same recipe.
 *  GT and MTUtilsGT do not check it.
 */
//MTUtilsGT.addFluidInput(<liquid:soda>,<liquid:mineralsoda>);
MTUtilsGT.addFluidInput([ 
  [<liquid:soda>,<liquid:mineralsoda>]
]);
