package com.mordonia.mordbooks;

import com.mordonia.mcore.MCore;
import com.mordonia.mcore.MCoreAPI;
import com.mordonia.mordbooks.commands.Commands;
import com.mordonia.mordbooks.copyright.BookRights;

import org.bukkit.plugin.java.JavaPlugin;

public final class MordBooks extends JavaPlugin {
    MCoreAPI mCoreAPI = MCore.getPlugin(MCore.class).getmCoreAPI();
    @Override
    public void onEnable() {
        this.getCommand("mb").setExecutor(new Commands(mCoreAPI));
        this.getServer().getPluginManager().registerEvents(new BookRights(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
