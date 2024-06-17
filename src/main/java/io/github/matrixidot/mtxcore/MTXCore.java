package io.github.matrixidot.mtxcore;

import io.github.matrixidot.mtxcore.ability.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MTXCore extends JavaPlugin {
    private static Abilities abilities;
    @Override
    public void onEnable() {
        abilities = new Abilities();
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
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==================");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "=MTXCore Disabled=");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "==================");
    }

    public static Abilities abilities() {
        return abilities;
    }
}
