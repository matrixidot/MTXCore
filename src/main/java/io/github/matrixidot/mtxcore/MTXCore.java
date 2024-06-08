package io.github.matrixidot.mtxcore;

import io.github.matrixidot.mtxcore.ability.AttachCommand;
import io.github.matrixidot.mtxcore.ability.AttachTab;
import io.github.matrixidot.mtxcore.ability.DetachCommand;
import io.github.matrixidot.mtxcore.ability.DetachTab;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MTXCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "===================");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "=MTXCore Activated=");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "===================");


        getCommand("attach").setExecutor(new AttachCommand());
        getCommand("attach").setTabCompleter(new AttachTab());

        getCommand("detach").setExecutor(new DetachCommand());
        getCommand("detach").setTabCompleter(new DetachTab());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
