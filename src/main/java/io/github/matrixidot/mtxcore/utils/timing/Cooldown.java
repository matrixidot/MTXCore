package io.github.matrixidot.mtxcore.utils.timing;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Cooldown {
    public static List<Cooldown> cooldowns = new ArrayList<>();

    private final Player player;

    private final String identifier;

    private Instant time;

    private Cooldown(Player player, String identifier, Duration duration) {
        this.player = player;
        this.identifier = identifier;
        this.time = Instant.now().plus(duration);
    }

    public static void newCooldown(Player player, String identifier, Duration duration) {
        cooldowns.add(new Cooldown(player, identifier, duration));
    }

    public static Cooldown getCooldown(Player player, String identifier) {
        for (Cooldown cooldown : cooldowns) {
            if (cooldown.player.equals(player) && cooldown.identifier.equals(identifier))
                return cooldown;
        }
        return null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Instant getTime() {
        return this.time;
    }

    public void updateCooldown(Duration duration) {
        this.time = Instant.now().plus(duration);
    }

    public boolean isExpired() {
        return Instant.now().isAfter(this.time);
    }

    public Long getRemainingCooldown() {
        if (this.time != null && !isExpired())
            return Long.valueOf(Duration.between(Instant.now(), this.time).getSeconds());
        return Long.valueOf(0L);
    }

    public static boolean use(Player player, String identifier) {
        return useAndCreate(player, identifier, -1);
    }

    public static boolean useAndCreate(Player player, String identifier, long seconds) {
        Cooldown cd = Cooldown.getCooldown(player, identifier);
        if (cd == null) {
            if (seconds > 0)
                Cooldown.newCooldown(player, identifier, Duration.ofSeconds(seconds));
            return true;
        }
        if (cd.isExpired()) {
            cd.updateCooldown(Duration.ofSeconds(seconds));
            return true;
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "COOLDOWN | " + cd.getRemainingCooldown() + "s"));
        return false;
    }
}


