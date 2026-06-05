package me.RTM.contextperks.command;

import me.RTM.contextperks.manager.HomeManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomesCommand implements CommandExecutor {

    private final HomeManager homes;

    public HomesCommand(HomeManager homes) {
        this.homes = homes;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player)) {
            return true;
        }

        Inventory gui = Bukkit.createInventory(
                null,
                27,
                "Your Homes"
        );

        int slot = 0;

        for (String homeName : homes.getHomes(player).keySet()) {

            ItemStack bed = new ItemStack(Material.RED_BED);

            ItemMeta meta = bed.getItemMeta();

            meta.setDisplayName("§a" + homeName);

            bed.setItemMeta(meta);

            gui.setItem(slot++, bed);
        }

        player.openInventory(gui);

        return true;
    }
}