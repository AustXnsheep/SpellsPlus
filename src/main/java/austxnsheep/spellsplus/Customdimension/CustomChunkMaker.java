package austxnsheep.spellsplus.Customdimension;

import net.minecraft.world.level.biome.Biome;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
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
                for (int y = 0; y < 10; y++) {
                    chunkData.setBlock(x, y, z, Material.STONE);
                    chunkData.setBlock(x, 9, z, Material.WATER);
                    generateTree(chunkData, x, 11, z);
                }
            }
        }
        chunkData.setRegion(0, 0, 0, 16, 256, 16, Material.WATER);
        return chunkData;
    }
    private void generateTree(ChunkData chunkData, int x, int y, int z) {
        for (int i = 0; i < 5; i++) {
            chunkData.setBlock(x, y + i, z, Material.OAK_LOG);
        }
        for (int offsetY = 2; offsetY <= 6; offsetY++) {
            for (int offsetX = -2; offsetX <= 2; offsetX++) {
                for (int offsetZ = -2; offsetZ <= 2; offsetZ++) {
                    chunkData.setBlock(x + offsetX, y + 5 + offsetY, z + offsetZ, Material.OAK_LEAVES);
                }
            }
        }
    }
}
