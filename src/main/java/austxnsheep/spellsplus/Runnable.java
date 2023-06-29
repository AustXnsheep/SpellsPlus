package austxnsheep.spellsplus;

import austxnsheep.spellsplus.Data.Datamanager;
import austxnsheep.spellsplus.Data.Manamanager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Runnable extends BukkitRunnable {
    private static int regenerationRate = 10;

    private static ChatColor completedChatColor = ChatColor.GREEN;

    private static ChatColor uncompletedChatColor = ChatColor.GRAY;

    private static final Datamanager datamanager = new Datamanager();

    private static final Manamanager manamanager = new Manamanager();

    private static final Core core = new Core();

    private Player player;

    @Override
    public void run() {
        if (player==null) {
            this.cancel();
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            //getProgressBar(int current, int max, int totalBars, String barChar, String completedColor, String notCompletedColor)
            int maxMana = (int) datamanager.getPlayerData(player, "maxMana");
            int currentMana = manamanager.getCurrentMana(player);
            String progressbar = core.getProgressBar(currentMana, maxMana, 40, "-", this.getCompletedChatcolor().toString(), this.getUncompletedChatcolor().toString());
            player.sendActionBar(progressbar);
            if (currentMana < maxMana) {
                manamanager.setCurrentMana(player, currentMana + getRegenerationRate());
            }
        }

    }

    //setters
    public void setRegenerationRate(int speed) {
        this.regenerationRate = speed;
    }

    public void setCompletedColor(ChatColor color) {
        this.completedChatColor = color;
    }

    public void setUncompletedColor(ChatColor color) {
        this.uncompletedChatColor = color;
    }

    public void setPlayerRunnable(Player player) {
        this.player = player;
    }


    //getters
    public Player getPlayerRunnable() {
        return this.player;
    }

    public ChatColor getUncompletedChatcolor() {
        return this.uncompletedChatColor;
    }

    public ChatColor getCompletedChatcolor() {
        return this.completedChatColor;
    }

    public int getRegenerationRate() {
        return this.regenerationRate;
    }
}
