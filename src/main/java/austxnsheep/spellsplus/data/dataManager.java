package austxnsheep.spellsplus.data;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class dataManager {

    private File dataFolder;
    private YamlConfiguration playerData;

    public int readFile(File file) {
        Yaml yaml = new Yaml();
        FileInputStream inputStream;
        int returndata = 0;
        try {
            inputStream = new FileInputStream(file);
            Map<String, Object> loadedData = yaml.load(inputStream);
            returndata = (int) loadedData.get("CurrentMana");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returndata;
    }

    public File getPlayerDataFile(UUID uuid) {
        return new File(dataFolder, uuid + ".yml");
    }

    private void loadPlayerData(UUID uuid) {
        File playerDataFile = getPlayerDataFile(uuid);
        if (playerDataFile.exists()) {
            playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        } else {
            playerData = new YamlConfiguration();
        }
    }

    public void savePlayerData(UUID uuid) {
        File playerDataFile = getPlayerDataFile(uuid);
        try {
            playerData.save(playerDataFile);
        } catch (IOException e) {
            getLogger().info("Failed to save player data for " + uuid);
            e.printStackTrace();
        }
    }

    public void saveAllPlayerData() {
        for (Player player : getServer().getOnlinePlayers()) {
            this.savePlayerData(player.getUniqueId());
        }
    }

    public void setPlayerData(Player player, String key, Object value) {
        loadPlayerData(player.getUniqueId());
        playerData.set(key, value);
        savePlayerData(player.getUniqueId());
    }

    public Object getPlayerData(@NotNull UUID player, String key) {
        loadPlayerData(player);
        return playerData.get(key);
    }
}
