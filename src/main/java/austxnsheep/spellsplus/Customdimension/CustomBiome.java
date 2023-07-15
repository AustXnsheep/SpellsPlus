package austxnsheep.spellsplus.Customdimension;


import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomBiome extends BiomeProvider {
    @Override
    public org.bukkit.block.@NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
        return null;
    }

    @Override
    public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
        return null;
    }
}
