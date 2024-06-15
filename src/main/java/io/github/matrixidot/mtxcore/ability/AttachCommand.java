package io.github.matrixidot.mtxcore.ability;

import io.github.matrixidot.mtxcore.MTXCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AttachCommand implements CommandExecutor {
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
            send.sendMessage(MTXCore.abilities().allAbilitiesList().toString());
            send.sendMessage(ChatColor.RED + "You need an ability name. Here are the abilities that " + player.getName() + " does not have:");
            send.sendMessage(MTXCore.abilities().detachedAbilitiesString(player));
            return false;
        }

        Ability ab = MTXCore.abilities().getAbility(args[1]);
        if (ab == null) {
            send.sendMessage(ChatColor.RED + args[1] + " is not a valid ability.");
            return false;
        }

        if (ab.isBound(player)) {
            send.sendMessage(ChatColor.RED + player.getName() + " is already bound to " + args[1] + ".");
            return false;
        }

        ab.attach(player);
        send.sendMessage(ChatColor.GREEN + player.getName() + " attached to: " + ab.getName() + ".");
        return true;
    }
}
