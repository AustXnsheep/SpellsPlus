package austxnsheep.spellsplus.Data;

import austxnsheep.spellsplus.Core;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class Manamanager extends JavaPlugin {
    private static final Datamanager datamanager = new Datamanager();
    private final Manamanager manamanager = new Manamanager();
    private static final Core core = new Core();
    public NamespacedKey currentmanakey = new NamespacedKey(this, "currentMana");
    private static final PersistentDataType<Integer, Integer> STRING_TYPE = PersistentDataType.INTEGER;
    public Integer getCurrentMana(Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        if (container.has(currentmanakey, STRING_TYPE)) {
            return container.get(currentmanakey, STRING_TYPE);
        }
        return 0;
    }
    public void setCurrentMana(Player player, int newmana) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(currentmanakey, STRING_TYPE, newmana);
    }
    public int checkMana(int currentmana, int manacost) {
        if (currentmana < manacost) {
            return 1;
        }
        if (currentmana > manacost) {
            return 2;
        }
        return 3;
    }
}
