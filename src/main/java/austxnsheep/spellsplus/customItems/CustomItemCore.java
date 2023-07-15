package austxnsheep.spellsplus.customItems;

import austxnsheep.spellsplus.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public interface CustomItemCore {
    default ItemStack getItem(int id) {
        String finalLore = Main.itemlores.get(id);
        if (id==1) {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 5);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Flaming Sword");
            meta.setLore(Collections.singletonList(finalLore));
            meta.setCustomModelData(1);
            item.setItemMeta(meta);
            return item;
        }
        if (id==2) {
            ItemStack item = new ItemStack(Material.IRON_AXE);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 6);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.RED + "Deadly Axe");
            meta.setLore(Collections.singletonList(finalLore));
            meta.setCustomModelData(2);
            item.setItemMeta(meta);
            return item;
        }
        if (id==3) {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Thunderstrike Blade");
            meta.setLore(Collections.singletonList(finalLore));
            meta.setCustomModelData(3);
            item.setItemMeta(meta);
            return item;
        }
        if (id==4) {
            ItemStack item = new ItemStack(Material.BAMBOO);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Rune of Resurgence");
            meta.setLore(Collections.singletonList(finalLore));
            meta.setCustomModelData(4);
            item.setItemMeta(meta);
            return item;
        }
        return null;
    }
}
