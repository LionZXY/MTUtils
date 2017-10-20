import mods.MTUtilsGT;

//
// We have a server with gregtech and minechem mods.
// The minechem fluids are mapped to new gregtech fluids, 
// but without any usage in its recipes.
//
// We want to use also minechem fluids in greg's recipes.
// 
// To find all used fluids of all gregtech machines, use:
// Chat command:   "/MTUtils GTfluids"
// 
// The MTUtils command MTUtilsGT.addFluidInput(...) adds all recipes with completly replaced 
// fluids. The first fluid is the gregtech fluid, the second one the 
// minechem.
//
// All mapped fluids are combined in an array to reduce runtime.
// 

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
