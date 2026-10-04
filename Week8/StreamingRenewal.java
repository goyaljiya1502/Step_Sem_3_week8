package Week8;

import java.time.LocalDate;
import java.util.Scanner;

abstract class Plan {
    protected LocalDate startDate;

    Plan(LocalDate startDate) {
        this.startDate = startDate;
    }

    abstract int getValidityDays();

    LocalDate getRenewalDate() {
        return startDate.plusDays(getValidityDays());
    }
}

class BasicPlan extends Plan {
    BasicPlan(LocalDate startDate) {
        super(startDate);
    }

    int getValidityDays() {
        return 30;
    }
}

class StandardPlan extends Plan {
    StandardPlan(LocalDate startDate) {
        super(startDate);
    }

    int getValidityDays() {
        return 90;
    }
}

class PremiumPlan extends Plan {
    PremiumPlan(LocalDate startDate) {
        super(startDate);
    }

    int getValidityDays() {
        return 365;
    }
}

public class StreamingRenewal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate startDate = LocalDate.parse(sc.next());
            Plan plan;

            if (type.equals("BASIC"))
                plan = new BasicPlan(startDate);
            else if (type.equals("STANDARD"))
                plan = new StandardPlan(startDate);
            else
                plan = new PremiumPlan(startDate);

            System.out.println(name + ": " + plan.getRenewalDate());
        }

        sc.close();
    }
}