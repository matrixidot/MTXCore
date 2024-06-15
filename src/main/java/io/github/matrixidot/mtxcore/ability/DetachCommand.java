package io.github.matrixidot.mtxcore.ability;

import io.github.matrixidot.mtxcore.MTXCore;
import io.github.matrixidot.mtxcore.ability.Abilities;
import io.github.matrixidot.mtxcore.ability.Ability;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DetachCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player send)) return false;

        if (!send.isOp()) return false;

        if (args.length == 0) {
            send.sendMessage(ChatColor.RED + "Please specify a player.");
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            send.sendMessage(ChatColor.RED + args[0] + " is not online or doesnt exist.");
            return false;
        }

        if (args.length == 1) {
            send.sendMessage(ChatColor.RED + "You need an ability name. Here are all of the abilities that " + player.getName() + " has:");
            send.sendMessage(MTXCore.abilities().attachedAbilitiesString(player));
            return false;
        }

        List<Ability> abilities = MTXCore.abilities().attachedAbilitiesList(player);

        Ability ab = MTXCore.abilities().getAbility(args[1]);
        if (ab == null) {
            send.sendMessage(ChatColor.RED + args[1] + " is not a valid ability.");
            return false;
        }

        if (!abilities.contains(ab)) {
            send.sendMessage(ChatColor.RED + player.getName() + " does not have ability: " +  args[1] + ".");
            return false;
        }

        ab.detach(player);
        send.sendMessage(ChatColor.GREEN + player.getName() + " detached from: " + ab.getName() + ".");
        return true;
    }
}
