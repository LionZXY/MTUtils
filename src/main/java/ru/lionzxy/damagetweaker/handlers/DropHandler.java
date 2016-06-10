package ru.lionzxy.damagetweaker.handlers;


import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.lionzxy.damagetweaker.models.DropsObject;


/**
 * Created by nikit_000 on 04.10.2015.
 */
public class DropHandler {

    @SubscribeEvent
    public void onBlockDestroy(BlockEvent.HarvestDropsEvent event) {
        Block block = event.state.getBlock();
        if (block != null && event.harvester != null && DropsObject.dropsObj != null) {
            ItemStack blockIS = new ItemStack(block, 1, block.getMetaFromState(event.state));
            for (DropsObject dropsObject : DropsObject.dropsObj)
                if (dropsObject.isBlockEqual(blockIS)) {
                    event.drops.clear();
                    dropsObject.replaceDrop(event.drops, event.harvester.getCurrentEquippedItem());

                }
        }
    }
}
