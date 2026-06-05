package me.RTM.contextperks.manager;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.entity.Player;

public class LuckPermsManager {

    private final LuckPerms lp;

    public LuckPermsManager() {
        this.lp = LuckPermsProvider.get();
    }

    public String meta(Player player, String key) {

        User user = lp.getUserManager().getUser(player.getUniqueId());

        if (user == null) {
            return null;
        }

        QueryOptions queryOptions =
                lp.getContextManager().getQueryOptions(player);

        return user.getCachedData()
                .getMetaData(queryOptions)
                .getMetaValue(key);
    }

    public boolean bool(Player player, String key) {
        return Boolean.parseBoolean(meta(player, key));
    }

    public int integer(Player player, String key, int defaultValue) {

        String value = meta(player, key);

        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }
}