package austxnsheep.spellsplus.Entitys;

import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.Pathfinding.PathFinderCore;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class SpellweaverSprite {
    private int health;
    private Player player;
    private EntityType type = EntityType.ALLAY;
    private Entity entity;

    public void initPathFinder() {
        PathFinderCore pathfinder = new PathFinderCore((Mob) entity);

        pathfinder.start();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void summon(Location loc) {
        this.entity = loc.getWorld().spawnEntity(loc, type);
        this.entity.setCustomName("Ally");
        this.entity.setCustomNameVisible(true);
        this.setHealth(100);
        this.initPathFinder();
    }
}

