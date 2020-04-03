package OOD.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Test {
    // to enable assert
    // Add "-ea" in VM arguments in Run Configurations
    // assert keyword is equal to
    // assert cond ==
    // if (!cond) {
    //     Throw new AssertionError();
    // }
    public static void main(String[] args) {
        ParkingLot pl = new ParkingLot(4, 10);
        List<Vehicle> list = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
            list.add(v);
            boolean hasSpot = pl.hasSpot(v);
            if (i < 40) {
                assert hasSpot;
                assert pl.park(v);
            } else {
                // assert hasSpot;
                // assert pl.park(v);
            }
        }

        assert list.size() == 50;
        int i = 0;
        for(Vehicle v : list) {
            assert i < 50 || pl.leave(v);
            i++;
        }

    }
}
