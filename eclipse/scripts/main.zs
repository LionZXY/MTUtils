import minetweaker.data.IData;
import mods.MTUtils;
import mods.MTUtils.WorldData;
import mods.MTUtils.NBT;

var WorldData = WorldData.get();

WorldData = NBT.writeToNBT(WorldData, "test.test", "TESTSTRING");
WorldData = NBT.writeToNBT(WorldData, "test.test", 51250124);
