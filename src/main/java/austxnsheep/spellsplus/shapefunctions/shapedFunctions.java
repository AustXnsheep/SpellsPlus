package austxnsheep.spellsplus.shapefunctions;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class shapedFunctions {
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
    //drawPolygon(Integer sides, Location center, Integer radius)
    public List<Location> drawPolygon(Integer corners, Location center, Integer radius) {
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
