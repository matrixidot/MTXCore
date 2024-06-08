package io.github.matrixidot.mtxcore.ability;

import io.github.matrixidot.mtxcore.ability.Abilities;
import io.github.matrixidot.mtxcore.ability.Ability;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class AttachTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1)
            return new ArrayList<>(Bukkit.getOnlinePlayers().stream().map(Player::getName).toList());
        if (args.length == 2)
            return Abilities.detachedAbilitiesList(Bukkit.getPlayer(args[0])).stream().map(Ability::getName).toList();
        return new ArrayList<>();
    }
}
