public class Bus {
    private int busNo;
    private boolean isAC;
    private int capacity;

    public Bus(int busNo, boolean isAC, int capacity) {
        this.busNo = busNo;
        this.isAC = isAC;
        this.capacity = capacity;
    }

    public int getBusNo() {
        return busNo;
    }

    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public boolean isAC() {
        return isAC;
    }

    public void setAC(boolean AC) {
        isAC = AC;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
