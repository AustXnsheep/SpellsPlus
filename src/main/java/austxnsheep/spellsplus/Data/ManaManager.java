package austxnsheep.spellsplus.Data;

import austxnsheep.spellsplus.Core;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ManaManager implements Core {
    private static final DataManager datamanager = new DataManager();
    private static final PersistentDataType<Integer, Integer> STRING_TYPE = PersistentDataType.INTEGER;
    private Plugin plugin;

    public void setDefaultManaPlugin(Plugin newplugin) {
        plugin = newplugin;
    }

    public void setCurrentMana(Player player, int mana) {
        player.setMetadata("currentMana", new FixedMetadataValue(plugin, mana));
    }

    public int getCurrentMana(Player player) {
        MetadataValue metadataValue = getPlayerMetadataValue(player, "currentMana");
        if (metadataValue != null) {
            return metadataValue.asInt();
        }
        return 0;
    }

    private MetadataValue getPlayerMetadataValue(Player player, String key) {
        for (MetadataValue metadataValue : player.getMetadata(key)) {
            if (metadataValue.getOwningPlugin().equals(plugin)) {
                return metadataValue;
            }
        }
        return null;
    }
}
