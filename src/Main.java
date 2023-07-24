import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final RomanConverter converter = new RomanConverter();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)); // считываем строку с консоли
        System.out.println(calc(reader.readLine())); // выводим данные
        reader.close();
    }

    public static String calc(String input) throws Exception {
        String[] operators = {"+", "-", "/", "*"};
        String[] regOperators = {"\\+", "-", "/", "\\*"};

        int operatorIndex = -1;
        for (int i=0; i< operators.length; i++) {
            if (input.contains(operators[i])){
                operatorIndex = i;
                break;
            }
        } // определяем оператор

        if (operatorIndex==-1) throw new Exception("Введен некорректный оператор");

        String[] numbers = input.split(regOperators[operatorIndex]); // список входных чисел

        if (converter.isRoman(numbers[0]) == converter.isRoman(numbers[1])) {
            int a,b;

            boolean isRoman = converter.isRoman(numbers[0]); // определяем римские числа или арабские,
            // если в данных не числа, то выкинет ошибку при переводе


            if (isRoman) {
                a = converter.romanToInt(numbers[0]); // переводим римские к арабским, чтобы подсчитать
                b = converter.romanToInt(numbers[1]);

            } else {
                a = Integer.parseInt(numbers[0]); // переводим из String'a int'у
                b = Integer.parseInt(numbers[1]);
            }

            if (10<a || a<1 || 10<b || b<1) throw new Exception("Некорректно введено число");
            // если какое-то число не удовлетворяет условию выдаем ошибку

            String result;
            switch (operators[operatorIndex]) {
                case "+":
                    result = String.valueOf(a+b);
                    break;
                case "-":
                    result = String.valueOf(a-b);
                    break;
                case "*":
                    result = String.valueOf(a*b);
                    break;
                default:
                    result = String.valueOf(a/b);
                    break;
            }

            if (isRoman) {
                return converter.intToRoman(Integer.parseInt(result)); // т.к. считали в виде int,
                // переводим обратно к риским
            } else {
                return result;
            }
        }

        throw new Exception("некорректно введены данные");
    }

}