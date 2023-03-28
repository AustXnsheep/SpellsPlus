package austxnsheep.spellsplus.Listeners;

import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.data.dataManager;
import austxnsheep.spellsplus.data.manaManager;
import austxnsheep.spellsplus.shapefunctions.shapedFunctions;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class PlayerJoin implements Listener {
    private final HashSet<UUID> JoinedPlayers = new HashSet<>();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //drawPolygon(Integer sides, Location center, Integer radius)
        dataManager datamanager = new dataManager();
        Plugin cls = new Main();
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        cls.getConfig().set(String.valueOf(player.getUniqueId()), 100);
        List<Location> loclist = shapedFunctions.drawCircle(5, player.getLocation(), 5);
        player.setMetadata("CurrentMana", new FixedMetadataValue((Plugin) this, 100));
        if(!player.hasPlayedBefore()) {
            manaManager.setMaxMana(player, 100);
        }
        for (Location location : loclist) {
            location.getWorld().spawnParticle(Particle.FLAME, location, 50, 1, 1, 1, 0.01);
        }
        loc.getWorld().spawnParticle(Particle.FLAME, (Location) shapedFunctions.drawCircle(5, player.getLocation(), 5), 50, 1, 1, 1, 0.01);
        manaManager.startRegeneration(player);
    }
}
