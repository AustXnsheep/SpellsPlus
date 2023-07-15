package austxnsheep.spellsplus.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//"maxMana"
public class DataManager {

    private final String fileDir = "plugins/SpellsPlus/data";

    public File getPlayerDataFile(UUID uuid) {
        return new File(fileDir, uuid.toString() + ".yml");
    }

    private YamlConfiguration getPlayerDataConfig(UUID uuid) {
        return YamlConfiguration.loadConfiguration(getPlayerDataFile(uuid));
    }

    public void savePlayerData(Player player) {
        File file = getPlayerDataFile(player.getUniqueId());
        YamlConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(file);
        try {
            playerDataConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAllPlayerData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            savePlayerData(player);
        }
    }

    public void createPlayerDataFile(Player player) {
        UUID playerUUID = player.getUniqueId();
        String fileName = playerUUID + ".yml";
        File dataFolder = new File(fileDir);
        File playerDataFile = new File(dataFolder, fileName);
        try {
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            if (!playerDataFile.exists()) {
                playerDataFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerData(Player player, String key, Object value) {
        YamlConfiguration playerDataConfig = getPlayerDataConfig(player.getUniqueId());
        playerDataConfig.set(key, value);
    }

    public Object getPlayerData(Player player, String key) {
        YamlConfiguration playerDataConfig = getPlayerDataConfig(player.getUniqueId());
        return playerDataConfig.get(key);
    }
}
