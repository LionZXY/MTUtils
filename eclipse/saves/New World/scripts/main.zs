import minetweaker.data.IData;
import mods.MTUtils;
import mods.MTUtilsWrite;

var WorldData = MTUtils.getWorldNBT();
WorldData = MTUtilsWrite.writeToNBT(WorldData, "test.test", "TESTSTRING");
WorldData = MTUtilsWrite.writeToNBT(WorldData, "test.test", 51250124);
MTUtils.setWorldNBT(WorldData);