package io.github.matrixidot.mtxcore.ability;

import io.github.matrixidot.mtxcore.utils.player.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ItemAbility extends Ability {
    private final ItemStack customItem;
    private final NamespacedKey itemKey;
    public ItemAbility(JavaPlugin plugin, String abilityName, String abilityDescription) {
        super(plugin, abilityName, abilityDescription);
        itemKey = new NamespacedKey(plugin, abilityName.replace(" ", "_"));
        customItem = configureItem(buildCustomItem());
    }

    public abstract ItemStack buildCustomItem();

    private ItemStack configureItem(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(itemKey, PersistentDataType.STRING, itemKey.getKey() + itemKey.getNamespace());
        stack.setItemMeta(meta);
        return stack;
    }

    public boolean checkItem(ItemStack stack) {
        if (stack == null) return false;
        if (!stack.hasItemMeta()) return false;
        ItemMeta meta = stack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.has(itemKey, PersistentDataType.STRING) && pdc.get(itemKey, PersistentDataType.STRING).equals(itemKey.getKey() + itemKey.getNamespace());
    }

    @Override
    void attach(Player player) {
        if (!getPlayers().contains(player.getUniqueId())) {
            getPlayers().add(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "Gained Ability: " + getName() + "!\n" + ChatColor.AQUA + getDescription() + ".");
            PlayerUtils.addItemSafe(player, customItem);
            attached(player);
        }
    }

    public ItemStack getItem() {
        return customItem;
    }

    public String abilityName(String name, String enableMethod) {
        return ChatColor.GOLD + "Item Ability: " + name + " " + ChatColor.YELLOW + ChatColor.BOLD + enableMethod.toUpperCase();
    }
    public String cooldownText(int time) {
        return ChatColor.GRAY + "Cooldown | " + ChatColor.RED + time + ChatColor.GRAY + "s";
    }
}
