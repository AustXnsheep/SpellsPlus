package austxnsheep.spellsplus.Listeners;

import austxnsheep.spellsplus.execution.spellExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class HandSwitch implements Listener {
    @EventHandler
    public void onHandSwitch(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        //       NBTItem nbti = new NBTItem(item);
        //       String spell = nbti.getString("spell");
        //       Integer manacost = nbti.getInteger("manacost");
        spellExecutor.ExecuteSpell(player, "Test2");
    }

}
