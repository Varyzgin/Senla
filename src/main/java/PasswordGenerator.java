import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        String symbolsSet = "01346789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-.,@#$%^&*()!\";:|?+{}\\<>/[]";

        int minNum = 8;
        int maxNum = 12;
        int length = 10;
        StringBuilder password = new StringBuilder();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password length (" + minNum + "–" + maxNum + "): ");

        try {
            length = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Invalid input");
            return;
        }
        if(length < minNum || length > maxNum) {
            System.out.print("Length must be between 8 and 12");
            return;
        }
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            password.append(symbolsSet.charAt(random.nextInt(symbolsSet.length())));
        }
        System.out.println(password);
    }
}