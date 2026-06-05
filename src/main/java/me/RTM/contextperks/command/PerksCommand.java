package me.RTM.contextperks.command;

import me.RTM.contextperks.manager.LuckPermsManager;
import me.RTM.contextperks.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PerksCommand implements CommandExecutor {

 private final LuckPermsManager lp;

 public PerksCommand(LuckPermsManager lp) {
  this.lp = lp;
 }

 @Override
 public boolean onCommand(CommandSender sender,
                          Command command,
                          String label,
                          String[] args) {

  if (!(sender instanceof Player player)) {
   return true;
  }

  String badge = lp.meta(player, "badge");

  if (badge == null) {
   badge = "Member";
  }

  int homes =
          lp.integer(player, "homes", 1);

  int cooldown =
          lp.integer(
                  player,
                  "teleport-cooldown",
                  30
          );

  boolean flight =
          lp.bool(player, "fly");

  player.sendMessage("");
  player.sendMessage("§b§l✦ ContextPerks ✦");
  player.sendMessage("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
  player.sendMessage("§fBadge: §b" + badge);
  player.sendMessage("§fHomes: §b" + homes);
  player.sendMessage("§fTeleport Cooldown: §b" + cooldown + "s");
  player.sendMessage("§fFlight: " + (flight ? "§aEnabled" : "§cDisabled"));
  player.sendMessage("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
  player.sendMessage("");

  return true;
 }
}