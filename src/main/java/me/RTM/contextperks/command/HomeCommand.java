package me.RTM.contextperks.command;

import me.RTM.contextperks.manager.HomeManager;
import me.RTM.contextperks.manager.LuckPermsManager;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.RTM.contextperks.ContextPerksPlugin;
import me.RTM.contextperks.util.MessageUtil;

public class HomeCommand implements CommandExecutor {

 private final HomeManager homes;
 private final LuckPermsManager lp;
 private final ContextPerksPlugin plugin;

 public HomeCommand(HomeManager homes,
                    LuckPermsManager lp,
                    ContextPerksPlugin plugin) {

  this.homes = homes;
  this.lp = lp;
  this.plugin = plugin;
 }

 @Override
 public boolean onCommand(CommandSender sender,
                          Command command,
                          String label,
                          String[] args) {

  if (!(sender instanceof Player player)) {
   return true;
  }

  if (args.length != 1) {
   player.sendMessage("§cUsage: /home <name>");
   return true;
  }

  Location home = homes.getHome(player, args[0]);

  if (home == null) {
   player.sendMessage("§cThat home does not exist.");
   return true;
  }

  int cooldown =
          lp.integer(player, "teleport-cooldown", 30);

  player.sendMessage(
          "§eTeleporting in "
                  + cooldown
                  + " seconds..."
  );

  Location start = player.getLocation().clone();

  new BukkitRunnable() {

   @Override
   public void run() {

    if (player.getLocation().distance(start) > 0.5) {

     player.sendMessage(
             "§cTeleport cancelled because you moved."
     );

     return;
    }

    player.teleport(home);


    player.sendMessage(
            MessageUtil.success(
                    "Teleported to " + args[0] + "."
            )
    );
   }

  }.runTaskLater(plugin, cooldown * 20L);

  return true;
 }
}
