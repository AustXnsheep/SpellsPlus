package austxnsheep.spellsplus.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerAttack implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Entity victim = event.getEntity();
            Player damager = (Player) event.getDamager();
            ItemStack item = damager.getItemInHand();
            ItemMeta meta = item.getItemMeta();
            if (meta.getCustomModelData()==1) {
                victim.setFireTicks(5 * 20);
            }
        }
    }
}