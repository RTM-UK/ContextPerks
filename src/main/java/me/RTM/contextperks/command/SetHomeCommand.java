package me.RTM.contextperks.command;

import me.RTM.contextperks.manager.HomeManager;
import me.RTM.contextperks.manager.LuckPermsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

 private final HomeManager hm;
 private final LuckPermsManager lp;

 public SetHomeCommand(HomeManager hm, LuckPermsManager lp) {
  this.hm = hm;
  this.lp = lp;
 }

 @Override
 public boolean onCommand(CommandSender sender, Command command,
                          String label, String[] args) {

  if (!(sender instanceof Player player)) {
   return true;
  }

  int maxHomes = lp.integer(player, "homes", 1);

  if (args.length != 1) {
   player.sendMessage("§cUsage: /sethome <name>");
   return true;
  }

  String homeName = args[0];

  if (!hm.hasHome(player, homeName)
          && hm.getHomeCount(player) >= maxHomes) {

   player.sendMessage("§cYou have reached your home limit (" + maxHomes + ")");
   return true;
  }

  hm.setHome(player, homeName);

  player.sendMessage("§aHome '" + homeName + "' set.");
  player.sendMessage("§7Homes used: "
          + hm.getHomeCount(player)
          + "/" + maxHomes);

  return true;
 }
}