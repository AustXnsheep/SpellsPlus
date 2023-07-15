package austxnsheep.spellsplus.customItems;

import austxnsheep.spellsplus.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public interface CustomItemsEffects extends Core {
    default void executeOffensiveItemEffect(Player attacker, Integer id, Entity victim) {
        if (id == null) {
            return;
        }
        switch (id) {
            case 1:
                victim.setFireTicks(20);
                break;
            case 2:
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 4));
                break;
            case 3:
                victim.getWorld().strikeLightning(victim.getLocation());
                break;
            default:
                attacker.sendMessage("Tried to execute a custom item effect but failed. (Most likely due to the item having an id that is higher than there is ids possible.)");
                break;
        }
    }
    default void executeDefenceItemEffect(Player player, int id) {
    }
    default void executeNeutralItemEffect(Player player, int id) {
        switch (id) {
            case 4:
                Location centerLocation = player.getLocation();
                double radius = 3;
                for (Entity entity : centerLocation.getWorld().getNearbyEntities(centerLocation, radius, radius, radius)) {
                    if (entity instanceof ArmorStand) {
                        for (Player loopedplayer : Bukkit.getServer().getOnlinePlayers()) {
                            if (loopedplayer.getName().equals(entity.getName())) {
                                loopedplayer.spigot().respawn();
                                loopedplayer.teleport(entity.getLocation());
                                loopedplayer.sendTitle(ChatColor.GOLD + "You've been respawned by: " + player.getName() + "!", "", 20, 40, 20);
                                loopedplayer.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);
                            }
                        }
                    }
                }
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);;
                break;
            default:
                player.sendMessage("Tried to execute a custom item effect but failed. (Send this error message to a developer, explain what you were attempting to do)");
                break;
        }

    }
}
