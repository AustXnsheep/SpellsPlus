package austxnsheep.spellsplus.Customdimension;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CustomChunkMaker extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        double frequency = 0.03;
        double amplitude = 10.0;
        //Will the server be able to with stand this many forloops???? I really have no Idea until I use it.
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 10; y++) {

                    int height = (int) (amplitude * Math.sin((chunkX * 16 + x) * frequency) + amplitude * Math.sin((chunkZ * 16 + z) * frequency));

                    for (y = 0; y < height; y++) {
                        chunkData.setBlock(x, y, z, Material.STONE);
                        chunkData.setBlock(x, y, z, Material.STONE);
                        chunkData.setBlock(x, y-1, z, Material.WATER);
                        if (random.nextDouble() < 0.1) {
                            generateTree(chunkData, x, y+1, z);
                        }
                    }
                }
            }
        }
        chunkData.setRegion(0, 0, 0, 16, 256, 16, Material.WATER);
        return chunkData;
    }
    //I almost feel like if this does work it will immedietly crash the server.
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
