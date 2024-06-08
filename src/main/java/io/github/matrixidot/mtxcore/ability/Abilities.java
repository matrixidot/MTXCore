package io.github.matrixidot.mtxcore.ability;

import io.github.matrixidot.mtxcore.utils.DataUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Abilities {
    private static ArrayList<Ability> abilities = new ArrayList<>();

    public static void addAbility(Ability ability, JavaPlugin plugin) {
        DataUtils.addIfAbsent(abilities, ability);
        Bukkit.getPluginManager().registerEvents(ability, plugin);
        ability.onRegisteredInternal();
    }

    public static void removeAbility(Ability ability) {
        abilities.remove(ability);
        ability.OnUnregisteredInternal();
    }

    public static Ability getAbility(String name) {
        for (Ability a : abilities) {
            if (a.getName().equals(name))
                return a;
        }
        return null;
    }

    public static <T extends Ability> Ability getAbility(Class<T> clazz) {
        for (Ability a : abilities) {
            if (a.getClass().equals(clazz))
                return a;
        }
        return null;
    }
    public static List<Ability> allAbilitiesList() {
        return (List<Ability>) abilities.clone();
    }
    public static String allAbilitiesString() {
        return DataUtils.listElementFormat(allAbilitiesList(), "\n");
    }

    public static List<Ability> attachedAbilitiesList(Player player) {
        ArrayList<Ability> list = new ArrayList<>();
        UUID id = player.getUniqueId();
        for (Ability a : abilities) {
            if (a.isBound(id))
                list.add(a);
        }
        return list;
    }
    public static String attachedAbilitiesString(Player player) {
        return DataUtils.listElementFormat(attachedAbilitiesList(player), "\n");
    }

    public static List<Ability> detachedAbilitiesList(Player player) {
        List<Ability> ab = allAbilitiesList();
        ab.removeAll(attachedAbilitiesList(player));
        return ab;
    }

    public static String detachedAbilitiesString(Player player) {
        return DataUtils.listElementFormat(detachedAbilitiesList(player), "\n");
    }
}
