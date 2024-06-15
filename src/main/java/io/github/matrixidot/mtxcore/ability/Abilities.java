package io.github.matrixidot.mtxcore.ability;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Abilities {
    private ArrayList<Ability> abilities;

    public Abilities() {
        abilities = new ArrayList<>();
    }

    public void addAbility(Ability ability, JavaPlugin plugin) {
        if (!abilities.contains(ability)) {
            abilities.add(ability);
            Bukkit.getPluginManager().registerEvents(ability, plugin);
            ability.onRegisteredInternal();
        }
    }

    public void removeAbility(Ability ability) {
        abilities.remove(ability);
        ability.OnUnregisteredInternal();
    }

    public Ability getAbility(String name) {
        for (Ability a : abilities) {
            if (a.getName().equals(name))
                return a;
        }
        return null;
    }

    public <T extends Ability> Ability getAbility(Class<T> clazz) {
        for (Ability a : abilities) {
            if (a.getClass().equals(clazz))
                return a;
        }
        return null;
    }
    public List<Ability> allAbilitiesList() {
        return (List<Ability>) abilities.clone();
    }
    public String allAbilitiesString() {
        StringBuilder names = new StringBuilder();
        allAbilitiesList().forEach(o -> names.append(o.toString()).append("\n"));
        return names.toString().trim();
    }

    public List<Ability> attachedAbilitiesList(Player player) {
        return allAbilitiesList().stream().filter(a -> a.isBound(player)).toList();
    }
    public String attachedAbilitiesString(Player player) {
        StringBuilder names = new StringBuilder();
        attachedAbilitiesList(player).forEach(o -> names.append(o.toString()).append("\n"));
        return names.toString().trim();
    }

    public List<Ability> detachedAbilitiesList(Player player) {
        return allAbilitiesList().stream().filter(a -> !a.isBound(player)).toList();
    }

    public String detachedAbilitiesString(Player player) {
        StringBuilder names = new StringBuilder();
        detachedAbilitiesList(player).forEach(o -> names.append(o.toString()).append("\n"));
        return names.toString().trim();
    }
}
