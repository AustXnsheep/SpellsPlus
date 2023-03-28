package austxnsheep.spellsplus.shapefunctions;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class shapedFunctions {
    //drawCircle(3, player.getLocation(), 3) <-This would make a circle that is 3 blocks wide and that has 3 points, It by default will have more
    public static List<Location> drawCircle(Integer radius, Location center, Integer numPoints) {
        double increment = 2 * Math.PI / numPoints;
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            double angle = i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            Location loc = new Location(center.getWorld(), x, center.getY(), z);
            locations.add(loc);
        }
        return locations;
    }
    //drawPolygon(3 player.getLocation(), 3)
    public List<Location> drawPolygon(Integer radius, Location center, Integer corners) {
        World world = center.getWorld();
        double angle = 2 * Math.PI / corners;
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < corners; i++) {
            double x = center.getX() + radius * Math.cos(i * angle);
            double z = center.getZ() + radius * Math.sin(i * angle);
            Location location = new Location(world, x, center.getY(), z);
            locations.add(location);
        }
        return locations;
    }
    //DrawLine(Location 1, Location 2, Amount of particles in the line)
    public List<Location> drawLine(Location start, Location end, int particles) {
        World world = start.getWorld();
        double distance = start.distance(end);
        double length = distance / particles;
        double xDiff = (end.getX() - start.getX()) / distance;
        double yDiff = (end.getY() - start.getY()) / distance;
        double zDiff = (end.getZ() - start.getZ()) / distance;

        List<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < particles; i++) {
            double x = start.getX() + xDiff * i * length;
            double y = start.getY() + yDiff * i * length;
            double z = start.getZ() + zDiff * i * length;
            Location location = new Location(world, x, y, z);
            locations.add(location);
        }
        return locations;
    }
}
