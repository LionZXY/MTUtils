import mods.MTUtils.Utils;

Utils.executeCommand("/say " + Utils.getItemStackID(<minecraft:log>));

recipes.addShapeless(<minecraft:stone>, [<minecraft:stone>, <minecraft:cobblestone>], function(output, inputs, crafting) {
  Utils.executeCommand("/say Your craft stone!  ");
  return output;
});