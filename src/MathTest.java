public class MathTest {
    public static void main(String[] args) {
        int divide = divide(100, 23);
        System.out.println(divide);
    }

    /**
     * 不使用乘除进行 整除操作
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {

        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }

        int result = 0;

        boolean fu = false;
        if (dividend > 0 && divisor > 0) {
            fu = false;
        } else if (dividend < 0 && divisor < 0) {
            fu = false;
        }else{
            fu = true;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (dividend > divisor) {
            dividend = dividend - divisor;
            result++;
        }


        return fu ? -result : result;
    }

}
