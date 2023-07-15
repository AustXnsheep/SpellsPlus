package austxnsheep.spellsplus.Execution;

import austxnsheep.spellsplus.Core;
import austxnsheep.spellsplus.Main;
import austxnsheep.spellsplus.Data.ManaManager;
import austxnsheep.spellsplus.Shapefunctions.ShapedFunctions;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpellExecutor implements Core{
    //How to use this function: ExecuteSpell(player, spell, manacost)
    public static void ExecuteSpell(Player player, String spell) {
        ManaManager manamanager = new ManaManager();
        Vector playerDirection = player.getLocation().getDirection();
        Integer currentmana = manamanager.getCurrentMana(player);
        int manacost = Main.spellcosts.get(spell);
        if (currentmana < manacost) {
            player.sendMessage("Not enough mana!");
           return;
        }
        //For testing making entitys spawn
        if(spell.equals("Test2")) {
            Location loc = player.getEyeLocation();
            Vector dir = loc.getDirection().normalize();
            SmallFireball fireball = player.launchProjectile(SmallFireball.class, dir.multiply(2));
            fireball.setIsIncendiary(false);
            fireball.setYield(0);
            return;
        }
        //For testing the soon to exist mana system
        if(spell.equals("Test3")) {
            World world = player.getWorld();
            Location loc = player.getTargetBlock(null, 50).getLocation();
            world.spawnEntity(loc, EntityType.valueOf("Creeper"));
            world.strikeLightning(loc);
            return;
        }
        if(spell.equals("Reflective Chaos")) {
            //Creates variables for later use
            Location blockloc = player.getTargetBlock(null, 50).getLocation();
            Location loc = player.getLocation();
            ShapedFunctions shapes = new ShapedFunctions();
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
                location.getWorld().spawnParticle(Particle.HEART, location, 1);
                location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
            }
        }
        if(spell.equals("Colorful Line")) {
            //variables
            Location blockloc = player.getTargetBlock(null, 50).getLocation();
            Location loc = player.getLocation();
            //A class that allows me to get the locations as a list for a line for example.
            ShapedFunctions shapes = new ShapedFunctions();
            Particle[] particles = Particle.values();
            Random random = new Random();
            //A new list to put those lines
            //adds those locations to the new list of locations
            List<Location> locations = new ArrayList<>(shapes.drawLine(loc, blockloc, 50));
            //Loops through all locations in the new location list
            for (Location location : locations) {
                //Get a random particle out of the linked list of particles
                Particle particle = particles[random.nextInt(particles.length)];
                //spawn particle
                location.getWorld().spawnParticle(particle, location, 1);
            }
        }
        //Format.
        //if(spell.equals("Colorful Line")) {
        //  Do cool and epic stuff
        //
        //
        // }
    }
}
