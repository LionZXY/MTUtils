package ru.lionzxy.damagetweaker.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import ru.lionzxy.damagetweaker.models.DropsObject;


/**
 * Created by nikit_000 on 04.10.2015.
 */
public class DropHandler {

    @SubscribeEvent
    public void onBlockDestroy(BlockEvent.HarvestDropsEvent event) {
        if (event.block != null && event.harvester != null && DropsObject.dropsObj != null) {
            ItemStack block = new ItemStack(event.block, 1, event.blockMetadata);
            for (DropsObject dropsObject : DropsObject.dropsObj)
                if (dropsObject.isBlockEqual(block)) {
                    event.drops.clear();
                    dropsObject.replaceDrop(event.drops, event.harvester.getCurrentEquippedItem());

                }
        }
    }
}
