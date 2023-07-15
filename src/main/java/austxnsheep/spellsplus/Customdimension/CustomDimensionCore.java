package austxnsheep.spellsplus.Customdimension;

import austxnsheep.spellsplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class CustomDimensionCore {
    public void createCustomDimension() {
        // Create a new world with a custom environment
        WorldCreator worldCreator = new WorldCreator("arcanum");
        worldCreator.environment(World.Environment.NORMAL);

        // Set the custom dimension type
        worldCreator.generator(new CustomChunkMaker());
        // Register the custom dimension
        World customDimension = worldCreator.createWorld();
        assert customDimension != null;
        customDimension.setStorm(true);
        customDimension.setGameRuleValue("doDaylightCycle", "false");
        customDimension.setGameRuleValue("doWeatherCycle", "false");
        customDimension.setKeepSpawnInMemory(false);
        Main.getInstance().getLogger().info("Custom dimension registered successfully!");
    }

    public boolean isWorldCreated(String worldName) {
        World world = Bukkit.getWorld(worldName);
        return world != null;
    }
}
