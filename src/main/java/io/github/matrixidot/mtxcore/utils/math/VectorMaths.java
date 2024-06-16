package io.github.matrixidot.mtxcore.utils.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorMaths {
    /**
     * Returns if a location is within a specified radius of another.
     * @param origin The Origin location
     * @param bound The Location to check
     * @param radius The radius of the check
     * @return True if the distance from the origin to bound is less than or equal to radius.
     */
    public static boolean isWithin(Location origin, Location bound, double radius) {
        return origin.distanceSquared(bound) <= radius*radius;
    }

    /**
     * Returns a unit-vector pointing from the origin location to the bound location.
     * @param origin The Origin location
     * @param bound The location the vector points towards starting from the Origin
     * @return A unit-vector pointing from origin to bound.
     */
    public static Vector getVector(Location origin, Location bound) {
        return new Vector(origin.getX() - bound.getX(), origin.getY() - bound.getY(), origin.getZ() - bound.getZ()).normalize();
    }


}
