package austxnsheep.spellsplus.Listeners;

import austxnsheep.spellsplus.Data.Datamanager;
import austxnsheep.spellsplus.Data.Manamanager;
import austxnsheep.spellsplus.Shapefunctions.Shapedfunctions;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //drawPolygon(Integer sides, Location center, Integer radius)
        Player player = event.getPlayer();
        Datamanager dataManager = new Datamanager();
        Manamanager manamanager = new Manamanager();
        dataManager.createPlayerDataFile(event.getPlayer());

        List<Location> loclist = Shapedfunctions.drawCircle(5, player.getLocation(), 0, 0);
        manamanager.setCurrentMana(player, 100);
        dataManager.setPlayerData(player, "maxMana", 100);
        if(!player.hasPlayedBefore()) {
            dataManager.setPlayerData(player, "maxMana", 100);
        }
        for (Location location : loclist) {
            location.getWorld().spawnParticle(Particle.FLAME, location, 50, 1, 1, 1, 0.01);
        }
    }
}
