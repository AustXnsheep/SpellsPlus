package austxnsheep.spellsplus;
//My Imports
import austxnsheep.spellsplus.Listeners.HandSwitch;
import static austxnsheep.spellsplus.Core.Enable;
import austxnsheep.spellsplus.Listeners.PlayerJoin;
//Others
import austxnsheep.spellsplus.commands.spellsPlus;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.UUID;


public final class Main extends JavaPlugin {
    private int totalmana = 0;
    @Override
    public void onEnable() {
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
