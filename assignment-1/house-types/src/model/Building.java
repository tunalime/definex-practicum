package model;

public abstract class Building {

    private double value;

    private double squareMeter;

    private int roomCount;

    private int saloonCount;

    public Building(double value, double squareMeter, int roomCount, int saloonCount) {
        this.value = value;
        this.squareMeter = squareMeter;
        this.roomCount = roomCount;
        this.saloonCount = saloonCount;
    }

    public double getValue() {
        return value;
    }
    public double getSquareMeter() {
        return squareMeter;
    }
    public int getRoomCount() {
        return roomCount;
    }
    public int getSaloonCount() {
        return saloonCount;
    }

    @Override
    public String toString(){
        String result = "Value: " + String.valueOf(this.value)
                + "      SquareMeters: " + String.valueOf(this.squareMeter)
                + "      RoomCount: " + String.valueOf(this.roomCount)
                + "      SaloonCount: " +String.valueOf(this.saloonCount)
                + "\n";
        return result;
    }
}
