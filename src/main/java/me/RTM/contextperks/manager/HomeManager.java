package me.RTM.contextperks.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeManager {

 private final Map<UUID, Map<String, Location>> homes = new HashMap<>();

 public HomeManager(Plugin plugin) {
 }

 public int getHomeCount(Player player) {
  return homes.getOrDefault(player.getUniqueId(), new HashMap<>()).size();
 }

 public boolean hasHome(Player player, String name) {
  return homes.containsKey(player.getUniqueId())
          && homes.get(player.getUniqueId()).containsKey(name.toLowerCase());
 }
 public Map<String, Location> getHomes(Player player) {
  return homes.getOrDefault(player.getUniqueId(), new HashMap<>());
 }

 public void setHome(Player player, String name) {
  homes.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
          .put(name.toLowerCase(), player.getLocation());
 }

 public Location getHome(Player player, String name) {
  Map<String, Location> playerHomes = homes.get(player.getUniqueId());

  if (playerHomes == null) {
   return null;
  }

  return playerHomes.get(name.toLowerCase());
 }
}
