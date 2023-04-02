package austxnsheep.spellsplus.execution;

import austxnsheep.spellsplus.Core;
import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.data.manaManager;
import austxnsheep.spellsplus.shapefunctions.shapedFunctions;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.NoteColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class spellExecutor {
    //How to use this function: ExecuteSpell(player, spell, manacost)
    public static void ExecuteSpell(Player player, String spell) {
        Main main = new Main();
        Vector playerDirection = player.getLocation().getDirection();
        Integer currentmana = player.getMetadata("CurrentMana").get(0).asInt();
        int manacost = getManaCost(spell);
        if(manaManager.checkMana(currentmana, manacost)) {
            player.sendMessage(Core.returnError("Not enough mana"));
            return;
        } //Checking to see if the player has enough mana
        //For testing the particles
        if(Objects.equals(spell, "Test1")) {
            Long startTime = System.currentTimeMillis();
            player.launchProjectile(Arrow.class, playerDirection);
            new ParticleBuilder(ParticleEffect.CRIT_MAGIC, player.getLocation())
                    .setParticleData(new NoteColor(1))
                    .display();
            Long endTime = System.currentTimeMillis();
            long elapsedtime = endTime - startTime;
            main.sendInfoToConsole("Took " + elapsedtime + " to execute spell: " + spell);
            return;
        }
        //For testing making entitys spawn
        if(Objects.equals(spell, "Test2")) {
            Long startTime = System.currentTimeMillis();
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection().normalize();
            SmallFireball fireball = player.launchProjectile(SmallFireball.class, dir.multiply(2));
            fireball.setIsIncendiary(false);
            fireball.setYield(0);
            Long endTime = System.currentTimeMillis();
            long elapsedtime = endTime - startTime;
            main.sendInfoToConsole("Took " + elapsedtime + " to execute spell: " + spell);
            return;
        }
        //For testing the soon to exist mana system
        if(Objects.equals(spell, "Test3")) {
            Long startTime = System.currentTimeMillis();
            World world = player.getWorld();
            Location loc = player.getTargetBlock(null, 50).getLocation();
            world.spawnEntity(loc, EntityType.valueOf("Creeper"));
            world.strikeLightning(loc);
            Long endTime = System.currentTimeMillis();
            long elapsedtime = endTime - startTime;
            main.sendInfoToConsole("Took " + elapsedtime + " to execute spell: " + spell);
            return;
        }
        if(Objects.equals(spell, "Reflective Chaos")) {
            Long startTime = System.currentTimeMillis();
            //Creates variables for later use
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            Location blockloc = player.getTargetBlock(null, 50).getLocation();
            Location loc = player.getLocation();
            shapedFunctions shapes = new shapedFunctions();
            Random random = new Random();
            //Creates the offsets that the next steps use to clone a new position
            int offsetX = player.getLocation().getBlockX() + random.nextInt(10);
            int offsetY = player.getLocation().getBlockY() + random.nextInt(10);
            int offsetZ = player.getLocation().getBlockZ() + random.nextInt(10);
            //Creates new variables with offsets
            Location newloc1 = loc.clone().add(offsetX, offsetY, offsetZ);
            Location newloc2 = newloc1.clone().add(offsetX, offsetY, offsetZ);
            Location newloc3 = newloc2.clone().add(offsetX, offsetY, offsetZ);
            //Adds all the locations in one list, so they all play at once
            List<Location> locations = new ArrayList<>();
            locations.addAll(shapes.drawLine(loc, newloc1, 50));
            locations.addAll(shapes.drawLine(newloc1, newloc2, 50));
            locations.addAll(shapes.drawLine(newloc2, newloc3, 50));
            locations.addAll(shapes.drawLine(newloc3, blockloc, 50));
            //Loops through all the locations and plays sound effects with a very slight delay
            for (Location location : locations) {
                scheduler.scheduleSyncDelayedTask(main, () -> {
                        location.getWorld().spawnParticle(Particle.HEART, location, 1);
                        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                    }, 10);
            }
            Long endTime = System.currentTimeMillis();
            long elapsedtime = endTime - startTime;
            main.sendInfoToConsole("Took " + elapsedtime + " to execute spell: " + spell);
        }
    }
    public static Integer getManaCost(String spellname) {
        if(Objects.equals(spellname, "Test1")) {
            return 10;
        }
        if(Objects.equals(spellname, "Test2")) {
            return 20;
        }
        if(Objects.equals(spellname, "Test3")) {
            return 30;
        }
        if(Objects.equals(spellname, "Reflective Chaos")) {
            return 50;
        }
        return 0;
    }
}
