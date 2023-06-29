package austxnsheep.spellsplus.Listeners;

import austxnsheep.spellsplus.customItems.Customitemseffects;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {
    public Customitemseffects cie = new Customitemseffects();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        int modeldata = meta.getCustomModelData();
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            if (item.getLore().contains("Ability:")) {
                cie.executeNeutralItemEffect(player, modeldata);
                meta.getCustomModelData();
            }
        }
    }
}
