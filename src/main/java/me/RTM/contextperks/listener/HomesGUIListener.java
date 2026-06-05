package me.RTM.contextperks.listener;

import me.RTM.contextperks.manager.HomeManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;

public class HomesGUIListener implements Listener {

    private final HomeManager homes;

    public HomesGUIListener(HomeManager homes) {
        this.homes = homes;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals("Your Homes")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        String homeName = event.getCurrentItem()
                .getItemMeta()
                .getDisplayName()
                .replace("§a", "");

        Location home = homes.getHome(player, homeName);

        if (home != null) {
            player.teleport(home);
            player.sendMessage("§aTeleported to " + homeName);
        }

        player.closeInventory();
    }
}
