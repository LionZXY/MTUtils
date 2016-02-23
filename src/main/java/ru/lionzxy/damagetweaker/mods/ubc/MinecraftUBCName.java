package ru.lionzxy.damagetweaker.mods.ubc;

import Zeno410Utils.MinecraftName;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */
public class MinecraftUBCName extends MinecraftName {
    String local;
    public MinecraftUBCName(String unlocalized, String local) {
        super(unlocalized);
        this.local = local;
    }
    @Override
    public String localized() {
        return local;
        //return unlocalized();
    }

    public String unlocalized() {
        return super.unlocalized();
    }

    public boolean legit() {
        return true;
    }
}
