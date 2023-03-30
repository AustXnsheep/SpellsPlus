package austxnsheep.spellsplus;
//My Imports

import austxnsheep.spellsplus.Listeners.HandSwitch;
import austxnsheep.spellsplus.Listeners.PlayerJoin;
import austxnsheep.spellsplus.commands.spellsPlus;
import austxnsheep.spellsplus.data.dataManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static austxnsheep.spellsplus.Core.Enable;


public final class Main extends JavaPlugin {
    private int totalmana = 0;
    @Override
    public void onEnable() {
        dataManager datamanager = new dataManager();
        datamanager.saveAllPlayerData();
        this.reloadConfig();
        this.saveDefaultConfig();
        this.registerEvent(new PlayerJoin());
        this.registerEvent(new HandSwitch());
        this.registerCommand(new spellsPlus(), "spellPlus");
        Enable();
        for (OfflinePlayer player : getServer().getOfflinePlayers()) {
            totalmana = totalmana + this.getConfig().getInt(String.valueOf(player.getUniqueId()));
        }
        Bukkit.broadcastMessage("Total Mana server wide: " + totalmana);
    }

    @Override
    public void onDisable() {
        dataManager datamanager = new dataManager();
        datamanager.saveAllPlayerData();
        this.saveDefaultConfig();
        // Plugin shutdown logic
    }
    void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
    void registerCommand(CommandExecutor commandExecutor, String commandName) {
        Objects.requireNonNull(this.getCommand(commandName)).setExecutor(commandExecutor);
    }
}
