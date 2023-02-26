import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        System.out.println(calc(str));
    }

    public static String calc(String input) throws Exception {
        String res1;
        Roman2Arab Roman = new Roman2Arab();
        Arab2Roman Arabian = new Arab2Roman();
        int value;
        boolean isRoman = true;
        int operand1, operand2;
        String[] words = input.split(" ");
        if (words.length != 3) throw new Exception("incorrect formulae");
        if (Roman.convert(words[0]) != 200 && Roman.convert(words[2]) != 200) {
            operand1 = Roman.convert(words[0]);
            operand2 = Roman.convert(words[2]);
        } else {
            operand1 = Integer.parseInt(words[0]);
            operand2 = Integer.parseInt(words[2]);
            isRoman = false;
        }
        switch (words[1]) {
            case "+" -> value = operand1 + operand2;
            case "-" -> value = operand1 - operand2;
            case "*" -> value = operand1 * operand2;
            case "/" -> {
                if (operand2 == 0) throw new Exception("Divide by 0");
                value = operand1 / operand2;
            }
            default -> throw new Exception("incorrect operator");
        }
        if (isRoman) {
            if (value <= 0) throw new Exception("There are only positive roman numbers");
            res1 = Arabian.convert(value);
        } else {
            res1 = Integer.toString(value);
        }
        return res1;
    }
}
class Roman2Arab {
    int res = 200;
    int convert(String str1) {
        TreeMap<String, Integer> romans = new TreeMap<>();
        romans.put("I", 1);
        romans.put("II", 2);
        romans.put("III", 3);
        romans.put("IV", 4);
        romans.put("V", 5);
        romans.put("VI", 6);
        romans.put("VII", 7);
        romans.put("VIII", 8);
        romans.put("IX", 9);
        romans.put("X", 10);
        if (romans.containsKey(str1)) {
            res = romans.get(str1);
        }
        return res;
    }
}
class Arab2Roman {
    String str = "";
    int arab;
    String convert(int num) {
        TreeMap<Integer, String> arabians = new TreeMap<>();
        arabians.put(100, "C");
        arabians.put(90, "XC");
        arabians.put(80, "LXXX");
        arabians.put(70, "LXX");
        arabians.put(60, "LX");
        arabians.put(50, "L");
        arabians.put(40, "XL");
        arabians.put(30, "XXX");
        arabians.put(20, "XX");
        arabians.put(10, "X");
        arabians.put(9, "IX");
        arabians.put(8, "VIII");
        arabians.put(7, "VII");
        arabians.put(6, "VI");
        arabians.put(5, "V");
        arabians.put(4, "IV");
        arabians.put(3, "III");
        arabians.put(2, "II");
        arabians.put(1, "I");

            while (num != 0) {
                arab = arabians.floorKey(num);
                str += arabians.get(arab);
                num -= arab;
            }
            return str;
        }
}