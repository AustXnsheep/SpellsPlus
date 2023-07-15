package austxnsheep.spellsplus;

import austxnsheep.spellsplus.Customdimension.CustomChunkMaker;
import austxnsheep.spellsplus.Customdimension.CustomDimensionCore;
import austxnsheep.spellsplus.Data.ManaManager;
import austxnsheep.spellsplus.Entitys.SpellweaverSprite;
import austxnsheep.spellsplus.Listeners.*;
import austxnsheep.spellsplus.Commands.Spellsplus;
import austxnsheep.spellsplus.Data.DataManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;


public final class Main extends JavaPlugin implements Core {
    public static ManaRunnable runnable;
    public static Plugin instance;
    public String prefix = getConfig().getString("prefix");
    public static HashMap<String, Integer> spellcosts = new HashMap<>();
    public static HashMap<Integer, String> itemlores = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        CustomDimensionCore ccg = new CustomDimensionCore();
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        DataManager datamanager = new DataManager();
        datamanager.saveAllPlayerData();
        this.registerEvent(new PlayerLeave());
        this.registerEvent(new PlayerJoin());
        this.registerEvent(new HandSwitch());
        this.registerEvent(new PlayerDeath());
        this.registerEvent(new PlayerAttack());
        this.registerEvent(new PlayerInteract());
        Objects.requireNonNull(this.getCommand("spellsplus")).setExecutor(new Spellsplus());
        itemlores.put(1, "Decades of killing and mass murder allowed this\n" + "sword to take on a power of its own\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Heated Blade\n " + ChatColor.DARK_GRAY + "Lights your opponent on fire for 1 second");
        itemlores.put(2, "It's an axe...\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Rush\n " + ChatColor.DARK_GRAY + "The battle allows you to move faster");
        itemlores.put(3, "A electrifying-ly powerful blade\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Lightning\n " + ChatColor.DARK_GRAY + "Summons a lightning strike on your victim");
        itemlores.put(4, "Years of death surrounding this wand allowed it to undo that event\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Resurrection\n " + ChatColor.DARK_GRAY + "Respawns the closest recently killed player (Player must not have respawned yet.)");
        spellcosts.put("Test1", 10);
        spellcosts.put("Test2", 20);
        spellcosts.put("Test3", 30);
        spellcosts.put("Reflective Chaos", 40);
        Enable();
        ManaRunnable runnable = new ManaRunnable();
        CustomDimensionCore cdc = new CustomDimensionCore();
        if (!cdc.isWorldCreated("arcanum")) {
            cdc.createCustomDimension();
        }
        runnable.setRegenerationRate(10);
        runnable.setCompletedColor(ChatColor.GREEN);
        runnable.setUncompletedColor(ChatColor.GRAY);
        runnable.runTaskTimer(this, 0, runnable.getRegenerationRate());
    }

    @Override
    public void onDisable() {
        DataManager datamanager = new DataManager();
        datamanager.saveAllPlayerData();
        if (runnable != null) {
            runnable.cancel();
        }
        this.saveDefaultConfig();
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return (Main) instance;
    }

    void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
}
