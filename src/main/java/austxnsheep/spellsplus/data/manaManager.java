package austxnsheep.spellsplus.data;

import austxnsheep.spellsplus.Core;
import austxnsheep.spellsplus.Main;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class manaManager extends JavaPlugin {
    private static final int regenerationRate = 10;
    private static final dataManager datamanager = new dataManager();
    private static final Core core = new Core();

    public static void startRegeneration(Player player) {
        Plugin This = new Main();
        new BukkitRunnable() {
            @Override
            public void run() {
                //getProgressBar(int current, int max, int totalBars, String barChar, String completedColor, String notCompletedColor)
                int maxMana = getMaxMana(player);
                File file = datamanager.getPlayerDataFile(player.getUniqueId());
                MetadataValue currentMana = getCurrentMana(player);
                int maxmana = datamanager.readFile(file);
                String progressbar = core.getProgressBar(currentMana.asInt(), maxmana, 40, "-", "ChatColor.GREEN", "ChatColor.GRAY");
                player.sendActionBar(progressbar);
                if (currentMana.asInt() < maxMana) {
                    setCurrentMana(player, currentMana.asInt() + regenerationRate);
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
        datamanager.setPlayerData(player, "CurrentMana", mana);
        saveManaFile(player);
    }
    public static int getMaxMana(Player player) {
        dataManager dataManager = new dataManager();
        File playerfile = dataManager.getPlayerDataFile(player.getUniqueId());
        saveManaFile(player);
        return dataManager.readFile(playerfile);

    }

    private static void saveManaFile(Player player) {
        datamanager.savePlayerData(player.getUniqueId());
    }

    public static boolean checkMana(Integer num1, Integer num2) {
        if (num1 > num2) {
            return true;
        }
        return num1 >= num2;
    }
}
