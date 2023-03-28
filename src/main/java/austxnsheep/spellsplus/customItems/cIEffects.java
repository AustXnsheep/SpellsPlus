package austxnsheep.spellsplus.customItems;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class cIEffects {
    public void executeItemEffect(Player player, Integer id) {
        if (id==1) {
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 1.0f);
        }
    }
}
