package OOD.ParkingLot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// each level has half of spot with Compact size and another half of spot with Large size
class Level {
    private final List<ParkingSpot> spots;

    Level(int spotNum) {
        List<ParkingSpot> spotList = new ArrayList<>(spotNum);
        int i = 0;
        for ( ; i < spotNum / 2; ++i) {
            spotList.add(new ParkingSpot(VehicleSize.Compact));
        }
        for ( ; i < spotNum; ++i) {
            spotList.add(new ParkingSpot(VehicleSize.Large));
        }
        // let the spots immutable
        spots = Collections.unmodifiableList(spotList);
    }

    boolean hasSpot(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(v)) {
                return true;
            }
        }
        return false;
    }

    ParkingSpot park(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(v)) {
                spot.park(v);
                return spot;
            }
        }
        return null;
    }

    boolean leave(Vehicle v) {
        for (ParkingSpot spot : spots) {
            if (spot.getVehicle() == v) {
                spot.leave();
                return true;
            }
        }
        return false;
    }

}
