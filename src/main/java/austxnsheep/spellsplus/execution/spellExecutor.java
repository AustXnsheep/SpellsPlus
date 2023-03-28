package austxnsheep.spellsplus.execution;

import austxnsheep.spellsplus.Core;
import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.data.manaManager;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.NoteColor;

import java.util.UUID;

public class spellExecutor {
    static Plugin cls = new Main();
    //How to use this function: ExecuteSpell(player, spell, manacost)
    public static void ExecuteSpell(Player player, String spell, Integer manacost) {
        Vector playerDirection = player.getLocation().getDirection();
        Integer currentmana = player.getMetadata("CurrentMana").get(0).asInt();
        if(manaManager.checkMana(currentmana, manacost)) {
            player.sendMessage(Core.returnError("Not enough mana"));
            return;
        } //Checking to see if the player has enough mana
        //For testing the particles
        if(spell=="Test1") {
            player.launchProjectile(Arrow.class, playerDirection);
            new ParticleBuilder(ParticleEffect.CRIT_MAGIC, player.getLocation())
                    .setParticleData(new NoteColor(1))
                    .display();
            return;
        }
        //For testing making entitys spawn
        if(spell=="Test2") {
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection().normalize();
            SmallFireball fireball = player.launchProjectile(SmallFireball.class, dir.multiply(2));
            fireball.setIsIncendiary(false);
            fireball.setYield(0);
            return;
        }
        //For testing the soon to exist mana system
        if(spell=="Test3") {
            World world = player.getWorld();
            Location loc = player.getTargetBlock(null, 50).getLocation();
            world.spawnEntity(loc, EntityType.valueOf("Creeper"));
            world.strikeLightning(loc);
            return;
        }
    }
}
