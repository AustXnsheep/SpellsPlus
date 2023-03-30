package austxnsheep.spellsplus.customItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class customItemsCore {
    public ItemStack getItem(int id) {
        if (id==1) {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 5);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Flaming Sword");
            meta.setLore(Collections.singletonList("Decades of killing and mass murder allowed this\n" + "sword to take on a power of its own"));
            meta.setCustomModelData(1);
            item.setItemMeta(meta);
            return item;
        }
        if (id==2) {
            ItemStack item = new ItemStack(Material.IRON_AXE);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 6);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Deadly Axe");
            meta.setLore(Collections.singletonList("It's an axe..."));
            meta.setCustomModelData(2);
            item.setItemMeta(meta);
            return item;
        }
        return null;
    }
}
