package austxnsheep.spellsplus.Customdimension;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CustomChunkMaker extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        // Generate your custom chunk data here

        // Example: Fill the chunk with stone blocks
        ChunkData chunkData = createChunkData(world);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                biome.setBiome(x, z, Biome.CUSTOM);
                for (int y = 0; y < 256; y++) {
                    chunkData.setBlock(x, y, z, Material.STONE);
                }
            }
        }
        chunkData.setRegion(0, 0, 0, 16, 256, 16, Material.WATER);
        return chunkData;
    }
}
