package austxnsheep.spellsplus.customItems;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class customItemsEffects {
    public void executeItemEffect(Player attacker, Integer id, Entity victim) {
        if (id==1) {
            victim.setFireTicks(20);
        }
        if (id==2) {
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 4));
        }
        if (id==3) {
            victim.getWorld().strikeLightning(victim.getLocation());
        }
    }
}
