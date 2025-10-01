import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main() {
        Map<String, Double> cursBasedOnRub = new HashMap<>();
        cursBasedOnRub.put("RUB", 1.0);
        cursBasedOnRub.put("USD", 80.5);
        cursBasedOnRub.put("EUR", 94.8);
        cursBasedOnRub.put("GBP", 106.4);
        cursBasedOnRub.put("CHF", 145.9);

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Choose your currency:\n1. RUB\n2. USD\n3. EUR\n4. GBP\n5. CHF\nOr any key to exit: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input");
                return;
            }

            String currencyCode = switch(choice) {
                case 1 -> "RUB";
                case 2 -> "USD";
                case 3 -> "EUR";
                case 4 -> "GBP";
                case 5 -> "CHF";
                default -> null;
            };

            Double multiplier = cursBasedOnRub.get(currencyCode);

            System.out.println("Enter the amount of money you want to convert:");
            double money;
            try {
                money = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input");
                return;
            }

            System.out.println("\nYour " + money + " " + currencyCode + " in different currencies:");
            for (Map.Entry<String, Double> entry : cursBasedOnRub.entrySet()) {
                if (!entry.getKey().equals(currencyCode)) {
                    double converted = money * multiplier / entry.getValue();
                    System.out.printf("%s: %.2f%n", entry.getKey(), converted);
                }
            }
            System.out.println();
        }
    }
}
