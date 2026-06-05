package me.RTM.contextperks.listener;

import me.RTM.contextperks.manager.LuckPermsManager;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

 private final LuckPermsManager lp;

 public JoinListener(LuckPermsManager lp) {
  this.lp = lp;
 }

 @EventHandler
 public void onJoin(PlayerJoinEvent event) {

  String joinMessage =
          lp.meta(event.getPlayer(), "join_message");

  if (joinMessage != null) {

   joinMessage = joinMessage.replace(
           "%player%",
           event.getPlayer().getName()
   );

   event.joinMessage(
           LegacyComponentSerializer
                   .legacyAmpersand()
                   .deserialize(joinMessage)
   );
  }

  if (lp.bool(event.getPlayer(), "fly")) {
   event.getPlayer().setAllowFlight(true);
  }
 }
}