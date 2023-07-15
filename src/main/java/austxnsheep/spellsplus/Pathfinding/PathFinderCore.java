package austxnsheep.spellsplus.Pathfinding;

import austxnsheep.spellsplus.Main;
import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.EnumSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PathFinderCore implements Goal<Allay> {
    private final GoalKey<Allay> key;
    private final Mob mob;
    private Player closestPlayer;
    private int cooldown;
    private ScheduledExecutorService executor;

    public PathFinderCore(Mob mob) {
        this.key = GoalKey.of(Allay.class, new NamespacedKey(Main.getInstance(), "follow_players"));
        this.mob = mob;
    }

    @Override
    public boolean shouldActivate() {
        if (cooldown > 0) {
            --cooldown;
            return false;
        }
        closestPlayer = getClosestPlayer();
        if (closestPlayer == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean shouldStayActive() {
        return shouldActivate();
    }

    @Override
    public void start() {
        executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                tick();
            }
        };

        // Schedule the task to run every 1 second
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }


    @Override
    public void stop() {
        mob.getPathfinder().stopPathfinding();
        mob.setTarget(null);
        cooldown = 100;
    }

    @Override
    public void tick() {
        mob.setTarget(closestPlayer);
        if (mob.getLocation().distanceSquared(closestPlayer.getLocation()) < 6.25) {
            mob.getPathfinder().stopPathfinding();
        } else {
            mob.getPathfinder().moveTo(closestPlayer, 1.0D);
        }
    }

    @Override
    public GoalKey<Allay> getKey() {
        return key;
    }

    @Override
    public EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE, GoalType.LOOK);
    }

    private Player getClosestPlayer() {
        Collection<Player> nearbyPlayers = mob.getWorld().getNearbyPlayers(mob.getLocation(), 10.0, player ->
                !player.isDead() && player.getGameMode() != GameMode.SPECTATOR && player.isValid());
        double closestDistance = -1.0;
        Player closestPlayer = null;
        for (Player player : nearbyPlayers) {
            double distance = player.getLocation().distanceSquared(mob.getLocation());
            if (closestDistance != -1.0 && !(distance < closestDistance)) {
                continue;
            }
            closestDistance = distance;
            closestPlayer = player;
        }
        return closestPlayer;
    }

    private boolean isEmerald(ItemStack stack) {
        switch (stack.getType()) {
            case EMERALD:
            case EMERALD_BLOCK:
            case EMERALD_ORE:
                return true;
            default:
                return false;
        }
    }
}
