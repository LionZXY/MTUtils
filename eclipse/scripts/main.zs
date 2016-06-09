import minetweaker.data.IData;
import minetweaker.oredict.IOreDictEntry;
import mods.MTUtils.Utils;
import mods.MTUtils.WorldData;
import mods.MTUtils.NBT;

var WorldData = WorldData.get();
WorldData = NBT.writeToNBT(WorldData, "test.test", "TESTSTRING");
WorldData = NBT.writeToNBT(WorldData, "test.test", 51250124);
val dusts = Utils.getIOreDictEntryFromString("ironIngot") as IOreDictEntry;

Utils.executeCommand("/say test");
Utils.executeCommand("/toggledownfall");
