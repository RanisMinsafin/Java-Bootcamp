package model;

public class Car {
    private String model;
    private int horsePower;
    private double mileage;

    public Car() {
        this.model = "Default model";
        this.mileage = 0.0;
        this.horsePower = 0;
    }

    public Car(String model, int horsePower, double mileage) {
        this.model = model;
        this.horsePower = horsePower;
        this.mileage = mileage;
    }

    public double growMileage(double value){
        mileage += value;
        return mileage;
    }
    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", mileage=" + mileage +
                ", horsePower=" + horsePower +
                '}';
    }
}
