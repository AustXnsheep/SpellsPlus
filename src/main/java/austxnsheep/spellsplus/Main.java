package austxnsheep.spellsplus;

import austxnsheep.spellsplus.Customdimension.CustomChunkMaker;
import austxnsheep.spellsplus.Customdimension.CustomDimensionCore;
import austxnsheep.spellsplus.Listeners.HandSwitch;
import austxnsheep.spellsplus.Listeners.PlayerJoin;
import austxnsheep.spellsplus.Listeners.PlayerLeave;
import austxnsheep.spellsplus.Commands.Spellsplus;
import austxnsheep.spellsplus.Data.Datamanager;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_19_R3.generator.CustomChunkGenerator;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public final class Main extends JavaPlugin {
    public static Runnable runnable;
    public String prefix = getConfig().getString("prefix");
    public NamespacedKey currentmanakey = new NamespacedKey(this, "currentMana");
    public static HashMap<String, Integer> spellcosts = new HashMap<>();
    public static HashMap<Integer, String> itemlores = new HashMap<>();

    @Override
    public void onEnable() {
        Core core = new Core();
        CustomDimensionCore ccg = new CustomDimensionCore();
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        Datamanager datamanager = new Datamanager();
        datamanager.saveAllPlayerData();
        this.registerEvent(new PlayerLeave());
        this.registerEvent(new PlayerJoin());
        this.registerEvent(new HandSwitch());
        this.getCommand("spellsplus").setExecutor(new Spellsplus());
        Runnable runnable = new Runnable();
        runnable.setRegenerationRate(10);
        runnable.setCompletedColor(ChatColor.GREEN);
        runnable.setUncompletedColor(ChatColor.GRAY);
        runnable.runTaskTimer(this, 0, runnable.getRegenerationRate());
        registerCustomDimension();
        itemlores.put(1, "Decades of killing and mass murder allowed this\n" + "sword to take on a power of its own\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Heated Blade\n " + ChatColor.DARK_GRAY + "Lights your opponent on fire for 1 second");
        itemlores.put(2, "It's an axe...\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Rush\n " + ChatColor.DARK_GRAY + "The battle allows you to move faster");
        itemlores.put(3, "A electrifying-ly powerful blade\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Lightning\n " + ChatColor.DARK_GRAY + "Summons a lightning strike on your victim");
        itemlores.put(4, "Years of death surrounding this wand allowed it to undo that event\n" + ChatColor.YELLOW + "Ability: " + ChatColor.GOLD + "Resurrection\n " + ChatColor.DARK_GRAY + "Respawns the closest recently killed player (Player must not have respawned yet.)");
        spellcosts.put("Test1", 10);
        spellcosts.put("Test2", 20);
        spellcosts.put("Test3", 30);
        spellcosts.put("Reflective Chaos", 40);
        core.Enable();
    }

    @Override
    public void onDisable() {
        Datamanager datamanager = new Datamanager();
        datamanager.saveAllPlayerData();
        if (runnable != null) {
            runnable.cancel();
        }
        this.saveDefaultConfig();
        // Plugin shutdown logic
    }

    private void registerCustomDimension() {
        // Create a new world with a custom environment
        WorldCreator worldCreator = new WorldCreator("arcanum");
        worldCreator.environment(World.Environment.NORMAL);

        // Set the custom dimension type
        worldCreator.generator(new CustomChunkMaker());
        // Register the custom dimension
        World customDimension = worldCreator.createWorld();
        assert customDimension != null;
        customDimension.setKeepSpawnInMemory(false);
        customDimension.setGameRuleValue("doDaylightCycle", "false");
        customDimension.setGameRuleValue("doWeatherCycle", "false");
        customDimension.setKeepSpawnInMemory(false);
        getLogger().info("Custom dimension registered successfully!");
    }

    void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
}
