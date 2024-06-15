package io.github.matrixidot.mtxcore;

import io.github.matrixidot.mtxcore.ability.AttachCommand;
import io.github.matrixidot.mtxcore.ability.AttachTab;
import io.github.matrixidot.mtxcore.ability.DetachCommand;
import io.github.matrixidot.mtxcore.ability.DetachTab;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MTXCore extends JavaPlugin {
    private static MTXCore core;
    @Override
    public void onEnable() {
        core = this;
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "===================");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "=MTXCore Activated=");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "===================");



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void postLoad() {
        core.getCommand("attach").setExecutor(new AttachCommand());
        core.getCommand("attach").setTabCompleter(new AttachTab());

        core.getCommand("detach").setExecutor(new DetachCommand());
        core.getCommand("detach").setTabCompleter(new DetachTab());
    }
}
