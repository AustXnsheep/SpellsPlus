package austxnsheep.spellsplus.data;

import austxnsheep.spellsplus.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class manaManager extends JavaPlugin {
    private static File manaFile;
    private static YamlConfiguration manaData;
    private static final int regenerationRate = 10;
    private static dataManager datamanager = new dataManager();

    public static void startRegeneration(Player player) {
        Plugin This = new Main();
        new BukkitRunnable() {
            @Override
            public void run() {
                int maxMana = (int) datamanager.getPlayerData(player.getUniqueId(), "CurrentMana");
                MetadataValue currentMana = getCurrentMana(player);

                if (currentMana.asInt() < maxMana) {
                    int regeneratedMana = regenerationRate;
                    setCurrentMana(player, currentMana.asInt() + regeneratedMana);
                }
            }
        }.runTaskTimer(This, 20L, 20L); // regenerates every tick
    }

    public static MetadataValue getCurrentMana(Player player) {
        return player.getMetadata("CurrentMana").get(0);
    }

    public static void setCurrentMana(Player player, int mana) {
        Main plugin = new Main();
        player.setMetadata("CurrentMana", new FixedMetadataValue(plugin, mana));
    }
    public static void setMaxMana(Player player, int mana) {
        dataManager datamanager = new dataManager();
        File playerfile = datamanager.getPlayerDataFile(player.getUniqueId());
        datamanager.setPlayerData(player, "CurrentMana", mana);
    }
    public static int getMaxMana(Player player) {
        dataManager dataManager = new dataManager();
        File playerfile = dataManager.getPlayerDataFile(player.getUniqueId());
        int finalint = dataManager.readFile(playerfile);
        return finalint;
    }

    private static void saveManaFile(Player player) {
        datamanager.savePlayerData(player.getUniqueId());
    }

    public static boolean checkMana(Integer num1, Integer num2) {
        if (num1 > num2) {
            return true;
        }
        if (num1 < num2) {
            return false;
        }
        return true;
    }
}
