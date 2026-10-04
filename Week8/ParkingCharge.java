package Week8;

import java.util.Scanner;

abstract class Vehicle {
    protected int hours;

    Vehicle(int hours) {
        this.hours = hours;
    }

    abstract double getCharge();
}

class Bike extends Vehicle {
    Bike(int hours) {
        super(hours);
    }

    double getCharge() {
        return hours * 10;
    }
}

class Car extends Vehicle {
    Car(int hours) {
        super(hours);
    }

    double getCharge() {
        return 30 + (hours - 1) * 20;
    }
}

class Truck extends Vehicle {
    Truck(int hours) {
        super(hours);
    }

    double getCharge() {
        return Math.max(100, hours * 50);
    }
}

public class ParkingCharge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();
            Vehicle vehicle;

            if (type.equals("BIKE"))
                vehicle = new Bike(hours);
            else if (type.equals("CAR"))
                vehicle = new Car(hours);
            else
                vehicle = new Truck(hours);

            double charge = vehicle.getCharge();
            total += charge;

            System.out.printf("%s: %.2f%n", type, charge);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
