package Week8;

import java.util.Scanner;

abstract class Room {
    protected int units;

    Room(int units) {
        this.units = units;
    }

    abstract double getBill();
}

class SingleRoom extends Room {
    SingleRoom(int units) {
        super(units);
    }

    double getBill() {
        return units * 8;
    }
}

class SharedRoom extends Room {
    private int occupants;

    SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    double getBill() {
        return (units * 6.0) / occupants;
    }
}

class ACRoom extends Room {
    ACRoom(int units) {
        super(units);
    }

    double getBill() {
        return units * 10 + 200;
    }
}

public class HostelElectricity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();
            Room room;

            if (type.equals("SINGLE")) {
                room = new SingleRoom(units);
            } else if (type.equals("SHARED")) {
                int occupants = sc.nextInt();
                room = new SharedRoom(units, occupants);
            } else {
                room = new ACRoom(units);
            }

            double bill = room.getBill();
            total += bill;

            System.out.printf("%s: %.2f%n", type, bill);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}