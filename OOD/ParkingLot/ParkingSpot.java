package OOD.ParkingLot;

class ParkingSpot {
    private VehicleSize size;
    private Vehicle currentVehicle;

    ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }

    boolean park(Vehicle v) {
        currentVehicle = v;
        return true;
    }

    boolean leave() {
        currentVehicle = null;
        return true;
    }

    Vehicle getVehicle() {
        return currentVehicle;
    }


}
