import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи арифметическое выражение: ");
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws IOException {
        String[] arab = new String[]{"10", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rome = new String[]{"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] arabian = new String[]{"20", "30", "40", "50", "60", "70", "80", "90", "100"};
        String[] rome_c = new String[]{"XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String A = "", B = "", res = "", flag = "arab";
        char operator = 0;
        int result = 0;

        String[] str = input.split("[-+*/]");

        if (str.length < 2) {
            throw new IOException("строка не является арифметической операцией");
        } else if (str.length > 2) {
            throw new IOException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else {
            for (int i = 0; i < rome.length; i++) {
                if (rome[i].equals(str[1])) {
                    B = arab[i];
                    flag = "rome";
                }
                if (rome[i].equals(str[0])) {
                    A = arab[i];
                    flag = "rome";
                }
            }
            if ((A == "" && B != "") || (A != "" && B == ""))
                throw new IOException("т.к. используются одновременно разные системы счисления или числа превышают 10");
            if (A == "" && B == "") {
                for (int i = 0; i < arab.length; i++) {
                    if (arab[i].equals(str[0])) {
                        A = arab[i];
                    }
                    if (arab[i].equals(str[1])) {
                        B = arab[i];
                    }
                }
            }
            if ((A == "" && B != "") || (A != "" && B == "") || (A == "" && B != ""))
                throw new IOException("т.к. числа меньше 0 или больше 10, либо они не являются целыми числами");
            else {
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == '-' || input.charAt(i) == '+' || input.charAt(i) == '*' || input.charAt(i) == '/') {
                        operator = input.charAt(i);
                    }
                }
                int num1 = Integer.parseInt(A);
                int num2 = Integer.parseInt(B);

                switch (operator) {
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                }
                if (flag == "arab") res = Integer.toString(result);

                else {
                    if (result <= 0) throw new IOException("В римской системе нет отрицательных чисел");
                    else {
                        boolean flag_of_find = false;
                        for (int i = 0; i < arab.length; i++) {
                            int num = Integer.valueOf(arab[i]);
                            if (result == num) {
                                res = rome[i];
                                flag_of_find = true;
                                break;
                            }
                        }
                        if (flag_of_find == false) {
                            int a, b;
                            String number = "";
                            a = result / 10 * 10;
                            b = result % 10;
                            if (a == 10) number = rome[0];
                            else {
                                for (int i = 0; i < arabian.length; i++) {
                                    if (a == Integer.valueOf(arabian[i])) {
                                        number = rome_c[i];
                                        break;
                                    }
                                }
                            }
                            for (int i = 0; i < arab.length; i++) {
                                if (b == Integer.valueOf(arab[i])) {
                                    res = number + rome[i];
                                    break;
                                }
                            }
                        }
                    }

                }
            }
        }
        return res;
    }
}
