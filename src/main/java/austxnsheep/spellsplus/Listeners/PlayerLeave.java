package austxnsheep.spellsplus.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Remove the variable from the player's metadata when they leave the server
    }
}
