import java.util.Scanner;

class Main {
    public static void main(String[] args) throws WrongCasesHandler {
        Scanner read = new Scanner(System.in);
        String userInput = read.nextLine().toUpperCase();
        String a = calc(userInput);
        System.out.println(a);
    }

    //чекнуть необходимость фроу на числа вне диапазона 1-10

    public static String calc(String input) throws WrongCasesHandler {
        InputChecker inputChecker = new InputChecker(input);
        return inputChecker.getToCalc();
    }
}