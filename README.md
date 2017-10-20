# MTUtils
Hello! It's minetweaker plugin for Minecraft 1.7.10.
All script put in [MineTweaker](http://minetweaker3.powerofbytes.com/) script folder

Link to [Curse](https://minecraft.curseforge.com/projects/mtutils): https://minecraft.curseforge.com/projects/mtutils

# Command List

## Import:

```
import mods.MTUtils;
```

## Items:
```
MTUtils.setItemMaxDamage(itemstack, damage);
MTUtils.getItemMaxDamage(itemstack); (Return int)
MTUtils.getItemDamage(itemstack); (Return int)
```

## Blocks:
```
MTUtils.setHarvestLevel(itemstack, String tooltip, int harvestLevel);
MTUtils.setBlockUnbreakable(itemstack);
MTUtils.setHardness(itemstack, float hardness;
MTUtils.setLightLevel(itemstack, float lightLevel);
MTUtils.setLightOpacity(itemstack, int lightOpacity);
MTUtils.setResistance(itemstack, float resistance);
MTUtils.setTextureName(itemstack, String texturename);
MTUtils.getHarvestLevel(itemstack); (Return int)
MTUtils.getHarvestTool(itemstack); (Return String)
MTUtils.getTextureName(itemstack, int side); (Return String)
```

## Utils:
```
MTUtils.getIntFromString(string); (Return int)
MTUtils.getFloatFromString(string); (Return float)
MTUtils.getStringFromInt(int); (Return String)
MTUtils.getStringFromFloat(float); (Return String)
MTUtils.getStringFromFormattedText(IFormattedText text); (Return String)
MTUtils.getStringFromFormattedString(IMCFormattedString text); (Return String)
MTUtils.getCrossMatch(IOreDictEntry ... oreDictEntries); (Return IItemStack[])
MTUtils.getIngredientFromString(String in); (Return IIngredient)
MTUtils.getItemStackFromString(String in); (Return IItemStack)
MTUtils.getIOreDictEntryFromString(String in); (Return IOreDictEntry)
MTUtils.getIFormatedTextFromString(String in); (Return IFormattedText)
MTUtils.getIMCFormattedTextFromString(String in); (Return IMCFormattedString)
```

## Drops:
```
MTUtils.clearDrops(); // Recreated HashMap drops
MTUtils.setBlockDrops(@Nullable IItemStack harvester, IItemStack block, IItemStack drops[], float quantiDrop[], IItemStack falseDrops[]);
```

# Gregtech
Thanks for PR @zetti68

![](http://i.imgur.com/poc9kb1.png)

## Import
```
import mods.MTUtilsGT;
```

All added or removed recipes will reset after start of reload of MineTweaker.



Get a key for a GT machine

`/MTUtils` is the registered command, alias: `/mtutils`, `/mtu`

To get the key, use in game command "/MTUtils GTkey "Canner"". 
Double quotes are only necessary with spaces in GT machine name.
Name is case insensitive, regular expressions are allowed.

To show all possible keys use `/MTUtils GTKeys [1...]` [optional select the first, second... block of ten keys]
First call uses only one input and one output liquid, second works with arrays.

## Recipes

Add a recipe to the Canner.

Fluids are optional, please note, "null" as fluids input AND "null" as fluids output dosn't work. 
Use call without fluids in this case.

```
MTUtilsGT.addCustomRecipe("gt.recipe.canner", false, 128, 128, [10000], [<minecraft:cobblestone>], <liquid:soda>*500, <liquid:water>*500, [<minecraft:diamond>]);
MTUtilsGT.addCustomRecipe("gt.recipe.canner", false, 128, 128, [10000], [<minecraft:cobblestone>], [<liquid:soda>*500], [<liquid:water>*500], [<minecraft:diamond>]);
```

## Fluid

Use alternate fluid in GT's recipes. First parameter is the current fluid, second the replacement.
First fluid will be replaced in all GT's recipes.
The result of the second call is the same like the first. But is possible to add more than one combination
in one step.
There is a little difference between first and second call. If there are more fluids to map in one
recipe after the second only adds recipe with the completely mapped fluid.
First calls would add mixed recipes too (one fluid mapped, second not).

Do not call multiple times the mapping of same fluid. It will add more than one same recipe.
GT and MTUtilsGT do not check it.

```
MTUtilsGT.addFluidInput(<liquid:soda>,<liquid:mineralsoda>);

MTUtilsGT.addFluidInput([ 
  [<liquid:soda>,<liquid:mineralsoda>]
]);
```

## Minechem Gregtech


We have a server with gregtech and minechem mods.
The minechem fluids are mapped to new gregtech fluids, 
but without any usage in its recipes.

We want to use also minechem fluids in greg's recipes.

To find all used fluids of all gregtech machines, use:
Chat command:   `/MTUtils GTfluids`
 
The MTUtils command `MTUtilsGT.addFluidInput(...)` adds all recipes with completly replaced 
fluids. The first fluid is the gregtech fluid, the second one the 
minechem.

All mapped fluids are combined in an array to reduce runtime.

```
MTUtilsGT.addFluidInput([ 
  [<liquid:molten.aluminium>, <liquid:element.al>],
  [<liquid:molten.antimony>, <liquid:element.sb>],
  [<liquid:argon>, <liquid:element.ar>],
  [<liquid:molten.bismuth>, <liquid:element.bi>],
  [<liquid:molten.lead>, <liquid:element.pb>],
  [<liquid:bromine>, <liquid:element.br>],
  [<liquid:butane>, <liquid:molecule.butene>],
  [<liquid:chlorine>, <liquid:element.cl>],
  [<liquid:molten.chromium>, <liquid:element.cr>],
  [<liquid:molten.cobalt>, <liquid:element.co>],
  [<liquid:molten.iron>, <liquid:element.fe>],
  [<liquid:bioethanol>, <liquid:molecule.ethanol>],
  [<liquid:fluorine>, <liquid:element.f>],
  [<liquid:molten.gold>, <liquid:element.au>],
  [<liquid:helium>, <liquid:element.he>],
  [<liquid:molten.iridium>, <liquid:element.ir>],
  [<liquid:carbondioxide>, <liquid:molecule.carbondioxide>],
  [<liquid:krypton>, <liquid:element.kr>],
  [<liquid:molten.copper>, <liquid:element.cu>],
  [<liquid:molten.magnesium>, <liquid:element.mg>],
  [<liquid:molten.manganese>, <liquid:element.mn>],
  [<liquid:neon>, <liquid:element.ne>],
  [<liquid:molten.nickel>, <liquid:element.ni>],
  [<liquid:molten.osmiumelemental>, <liquid:element.os>],
  [<liquid:molten.platinum>, <liquid:element.pt>],
  [<liquid:propane>, <liquid:molecule.propane>],
  [<liquid:radon>, <liquid:element.rn>],
  [<liquid:oxygen>, <liquid:element.o>],
  [<liquid:molten.silver>, <liquid:element.ag>],
  [<liquid:molten.titanium>, <liquid:element.ti>],
  [<liquid:molten.vanadium>, <liquid:element.v>],
  [<liquid:hydrogen>, <liquid:element.h>],
  [<liquid:hydrogenperoxide>, <liquid:molecule.hperox>],
  [<liquid:molten.tungsten>, <liquid:element.w>],
  [<liquid:xenon>, <liquid:element.xe>],
  [<liquid:molten.zinc>, <liquid:element.zn>],
  [<liquid:molten.tin>, <liquid:element.sn>]
]);
```


# Undeground Biomes Constructs API
In config file:
```
[
   {
      "name":"exampleblock",
      "nameid":"minecraft:iron_ore",
      "texturepath":"minecraft:iron_ore"
   }
]
```
![](http://i.imgur.com/QZaCuNf.jpg)
