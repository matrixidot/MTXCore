package io.github.matrixidot.mtxcore.utils.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerUtils {
    public static void addItemSafe(Player player, ItemStack stack) {
        HashMap<Integer, ItemStack> unFitItems = player.getInventory().addItem(stack);
        if (unFitItems.isEmpty()) return;

        for (ItemStack s : unFitItems.values()) {
            player.getLocation().getWorld().dropItem(player.getLocation(), s);
        }
        player.sendMessage(ChatColor.AQUA + "Some items did not fit in your inventory, dropping them on the ground.");
    }

    public static void actionBar(Player player, String message) {
        actionBar(player, new TextComponent(message));
    }

    public static void actionBar(Player player, TextComponent comp) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, comp);
    }
}
