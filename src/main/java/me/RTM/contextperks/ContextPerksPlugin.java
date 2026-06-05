package me.RTM.contextperks;

import me.RTM.contextperks.command.HomeCommand;
import me.RTM.contextperks.command.HomesCommand;
import me.RTM.contextperks.command.PerksCommand;
import me.RTM.contextperks.command.SetHomeCommand;
import me.RTM.contextperks.listener.HomesGUIListener;
import me.RTM.contextperks.listener.JoinListener;
import me.RTM.contextperks.manager.HomeManager;
import me.RTM.contextperks.manager.LuckPermsManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ContextPerksPlugin extends JavaPlugin {

    private LuckPermsManager lp;
    private HomeManager homes;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        lp = new LuckPermsManager();
        homes = new HomeManager(this);

        getServer().getPluginManager().registerEvents(
                new JoinListener(lp),
                this
        );

        getServer().getPluginManager().registerEvents(
                new HomesGUIListener(homes),
                this
        );

        getCommand("perks").setExecutor(
                new PerksCommand(lp)
        );

        getCommand("sethome").setExecutor(
                new SetHomeCommand(homes, lp)
        );

        getCommand("home").setExecutor(
                new HomeCommand(
                        homes,
                        lp,
                        this
                )
        );

        getCommand("homes").setExecutor(
                new HomesCommand(homes)
        );

        getLogger().info("ContextPerks enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ContextPerks disabled!");
    }
}
