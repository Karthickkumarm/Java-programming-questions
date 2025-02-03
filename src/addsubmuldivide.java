public class addsubmuldivide {
    public static void main(String[] args) {
        int num1 = 6;
        int num2 = 4;
        
        System.out.println("Input: " + num1 + " and " + num2);
        System.out.println("Output:");
        System.out.println("Addition: " + num1 + " + " + num2 + " = " + add(num1, num2));
        System.out.println("Subtraction: " + num1 + " - " + num2 + " = " + subtract(num1, num2));
        System.out.println("Multiplication: " + num1 + " * " + num2 + " = " + multiply(num1, num2));
        System.out.println("Division: " + num1 + " / " + num2 + " = " + divide(num1, num2));
    }

    // Addition using bitwise operators
    public static int add(int a, int b) {
        while (b != 0) {
            int carry = a & b; // Calculate carry
            a = a ^ b;         // Sum without carry
            b = carry << 1;    // Shift carry to the left
        }
        return a;
    }

    // Subtraction using addition and negation
    public static int subtract(int a, int b) {
        return add(a, negate(b));
    }

    // Multiplication using repeated addition
    public static int multiply(int a, int b) {
        int result = 0;
        boolean negative = (a < 0) ^ (b < 0); // Determine sign
        
        a = Math.abs(a);
        b = Math.abs(b);

        for (int i = 0; i < b; i++) {
            result = add(result, a);
        }
        
        return negative ? negate(result) : result;
    }

    // Division using repeated subtraction
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        
        int result = 0;
        boolean negative = (a < 0) ^ (b < 0); // Determine sign
        
        a = Math.abs(a);
        b = Math.abs(b);
        
        while (a >= b) {
            a = subtract(a, b);
            result = add(result, 1);
        }
        
        return negative ? negate(result) : result;
    }

    // Negate a number using bitwise operations
    public static int negate(int a) {
        return add(~a, 1);
    }
}
