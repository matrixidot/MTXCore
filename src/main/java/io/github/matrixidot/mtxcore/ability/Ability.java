package io.github.matrixidot.mtxcore.ability;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Ability implements Listener {
    private final JavaPlugin plugin;
    private final ArrayList<UUID> players;
    private final String abilityName;
    private final String abilityDescription;
    public Ability(JavaPlugin plugin, String abilityName, String abilityDescription) {
        this.plugin = plugin;
        players = new ArrayList<>();
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        Abilities.addAbility(this, plugin);
    }

    public boolean isBound(Player player) {
        return players.contains(player.getUniqueId());
    }

    public boolean isBound(UUID uuid) {
        return players.contains(uuid);
    }

    void attach(Player player) {
        if (!players.contains(player.getUniqueId())) {
            players.add(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "Gained Ability: " + abilityName + "!\n" + ChatColor.AQUA + abilityDescription + ".");
            attached(player);
        }

    }
    public abstract void attached(Player player);

    void detach(Player player) {
        players.remove(player.getUniqueId());
        if (!player.isOnline()) return;

        player.sendMessage(ChatColor.RED + "Lost Ability: " + abilityName + ".");
        detached(player);
    }
    public abstract void detached(Player player);

    private void detachAll() {
        players.stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> {
            detach(p);
            if (p.isOnline())
                p.sendMessage(ChatColor.DARK_RED + "Ability was lost because it was deregistered.");
        });
        players.clear();
    }

    void onRegisteredInternal() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[MTXCore] " + abilityName + " registered successfully.");
    }

    void OnUnregisteredInternal() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[MTXCore] " + abilityName + " unregistered successfully.");
        detachAll();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent ev) {
        if (this instanceof IDisconnectorAbility)
            detach(ev.getPlayer());
    }

    public String getName() {
        return abilityName;
    }

    public String getDescription() {
        return abilityDescription;
    }

    @Override
    public String toString() {
        return abilityName;
    }
}
