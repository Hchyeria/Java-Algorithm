package OOD.ParkingLot;


import java.util.HashMap;
import java.util.Map;

// multiple levels
// check vehicle size use enumerate
public class ParkingLot {
    private Level[] levels;
    private Map<Vehicle, Object[]> cache;

    public ParkingLot(int levelNum, int spotNum) {
        this.levels = new Level[levelNum];
        for (int i = 0; i < levelNum; ++i) {
            levels[i] = new Level(spotNum);
        }
        cache = new HashMap<>();
    }

    public boolean hasSpot(Vehicle v) {
        for (Level l : levels) {
            if (l.hasSpot(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean park(Vehicle v) {
        for (Level l : levels) {
            /*  don't write like this
                just the same with
                HashMap.contains with put
                if (l.has(v)) {
                    ParkingSpot spot = l.park(v);
                    cache.put(v, new Object[] {l, spot});
                    return true;
                }
                the next version is better
                if (l.park(v)) {
                    return true;
                }
            */
            ParkingSpot spot = l.park(v);
            if (spot != null) {
                cache.put(v, new Object[] {l, spot});
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        Object[] obj = cache.get(v);
        if (obj == null) {
            return false;
        }
        ParkingSpot spot = (ParkingSpot) obj[1];
        if (spot.leave()) {
            cache.remove(v);
            return true;
        }
    /*
        for (Level l : levels) {
                if (l.leave(v)) {
                    return true;
                }
        }
    */
        return false;
    }

    public Level getLevel(Vehicle v) {
        Object[] obj = cache.get(v);
        if (obj == null) {
            return null;
        }
        return (Level) obj[0];
    }
}
