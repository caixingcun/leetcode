import java.util.*;

/**
 * 进制处理
 */
public class HexFormat {
    public static void main(String[] args) {

//        System.out.println(intToRoman(1994));
//        System.out.println(intToRoman2(1994));

//        System.out.println(romanToInt("II"));
//        System.out.println(romanToInt("IV"));
//        System.out.println(romanToInt("VII"));
//        System.out.println(romanToInt("IX"));
//        System.out.println(romanToInt("X"));
//        System.out.println(romanToInt("XIX"));
//        System.out.println(romanToInt("CM"));

        List<String> strings = letterCombinations("23");
        System.out.println(strings);

    }

    private static String intToRoman2(int num) {
        char template[] = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int length;
        if (num >= 1000) {
            length = 4;
        } else if (num >= 100) {
            length = 3;
        } else if (num >= 10) {
            length = 2;
        } else {
            length = 1;
        }

        int arr[] = new int[length];
        int index = arr.length - 1;
        while (index >= 0) {
            arr[index] = num % 10;
            num = num / 10;
            index--;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= length - 1; i++) {
            int n = arr[i];
            if (n == 0) {
                continue;
            }
            if (n <= 3) {
                for (int j = 0; j < n; j++) {
                    result.append(template[0 + 2 * (length - i - 1)]);
                }
                continue;
            }
            if (n == 4) {
                result.append(template[0 + 2 * (length - i - 1)]);
                result.append(template[1 + 2 * (length - i - 1)]);
                continue;
            }
            if (n <= 8) {
                result.append(template[1 + 2 * (length - i - 1)]);
                for (int j = 0; j < n - 5; j++) {
                    result.append(template[0 + 2 * (length - i - 1)]);
                }
                continue;
            }
            if (n == 9) {
                result.append(template[0 + 2 * (length - i - 1)]);
                result.append(template[2 + 2 * (length - i - 1)]);
                continue;
            }
        }
        return result.toString();
    }

    /**
     * 数字 转 罗马数字
     * 思路  分离出每一位 进行转换
     * <p>
     * 输入 1994
     * 输出 MCMXCIV
     *
     * @param num
     * @return
     */

//            1  = I
//            2     II
//            3     III
//            4     IV
//            5     V
//            6     VI
//            ..    VIII
//            9     IX
//            10    X
//            11    XI
//            ...
//            14    XIV
//            15    XV
//            50    L
//            51    LI
//            55    LV
//            56    LVI
//            1994  MCMXCIV
//            4    IV
//            9     IX
//            40    XL
//            50    L
//            60    LX
//            70    LXX
//            80    LXXX
//            90    XC
//            400   CD
//            600   DC
//            700   DCC
//            800   DCCC
//            900   CM
    public static String intToRoman(int num) {
        //整除 拼接
        List<Character> map = new ArrayList<>();
        map.add('I');
        map.add('V');
        map.add('X');
        map.add('L');
        map.add('C');
        map.add('D');
        map.add('M');
        int length = (num + "").length();
        int arr[] = new int[length];
        int index = arr.length - 1;
        while (index >= 0) {
            arr[index] = num % 10;
            num = num / 10;
            index--;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= length - 1; i++) {
            int n = arr[i];
            if (n == 0) {
                continue;
            }
            if (n <= 3) {
                for (int j = 0; j < n; j++) {
                    result.append(map.get(0 + 2 * (length - i - 1)));
                }
                continue;
            }
            if (n == 4) {
                result.append(map.get(0 + 2 * (length - i - 1)));
                result.append(map.get(1 + 2 * (length - i - 1)));
                continue;
            }
            if (n <= 8) {
                result.append(map.get(1 + 2 * (length - i - 1)));
                for (int j = 0; j < n - 5; j++) {
                    result.append(map.get(0 + 2 * (length - i - 1)));
                }
                continue;
            }
            if (n == 9) {
                result.append(map.get(0 + 2 * (length - i - 1)));
                result.append(map.get(2 + 2 * (length - i - 1)));
                continue;
            }
        }
        return result.toString();
    }

    /**
     * 罗马字符转 int
     * 最大 3999
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        char template[] = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int index = s.length() - 1;
        int num = 0;
        while (index >= 0) {
            // 1-8
            if (s.charAt(index) == template[0]) { // 123
                num += 1;
                index--;
                continue;
            }

            if (s.charAt(index) == template[1]) { // 4 5 v
                num += 5;
                index--;
                if (index >= 0 && s.charAt(index) == template[0]) {
                    index--;
                    num--;
                    continue;
                }
                continue;
            }
            // 9 - 39
            if (s.charAt(index) == template[2]) { //X  10
                num += 10;
                index--;
                if (index >= 0 && s.charAt(index) == template[0]) { // 9
                    index--;
                    num--;
                    continue;
                }
                continue;
            }

            // 40 - 89
            if (s.charAt(index) == template[3]) { // L  50
                num += 50;
                index--;
                if (index >= 0 && s.charAt(index) == template[2]) { // 40
                    num = num - 10;
                    index--;
                    continue;
                }
                continue;
            }

            // 90 - 399
            if (s.charAt(index) == template[4]) { //C  100
                num += 100;
                index--;
                if (index >= 0 && s.charAt(index) == template[2]) { // 10
                    num = num - 10;
                    index--;
                    continue;
                }
                continue;
            }

            // 400 - 899
            if (s.charAt(index) == template[5]) { // L  500
                num += 500;
                index--;
                if (index >= 0 && s.charAt(index) == template[4]) { //
                    num = num - 100;
                    index--;
                    continue;
                }
                continue;
            }

            // 900 - 3999
            if (s.charAt(index) == template[6]) { //M  1000
                num += 1000;
                index--;
                if (index >= 0 && s.charAt(index) == template[4]) { // 100
                    num = num - 100;
                    index--;
                    continue;
                }
                continue;
            }

        }
        return num;
    }

    /**
     * 九宫数组输入
     * 返回可能的字母组合
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null && digits.length() == 0) {
            return new ArrayList<>();
        }
        Map<Character, char[]> map = new HashMap<>();
        // 2-9
        char[] num_2 = {'a', 'b', 'c'};
        map.put('2', num_2);
        char[] num_3 = {'d', 'e', 'f'};
        map.put('3', num_3);
        char[] num_4 = {'g', 'h', 'i'};
        map.put('4', num_4);
        char[] num_5 = {'j', 'k', 'l'};
        map.put('5', num_5);
        char[] num_6 = {'m', 'n', 'o'};
        map.put('6', num_6);
        char[] num_7 = {'p', 'q', 'r', 's'};
        map.put('7', num_7);
        char[] num_8 = {'t', 'u', 'v'};
        map.put('8', num_8);
        char[] num_9 = {'w', 'x', 'y', 'z'};
        map.put('9', num_9);

        Head head = new Head();

        int len = digits.length();
        int index = 0;
        while (index < len) {
            char[] chars = map.get(digits.charAt(index));
            List<String> temps = new ArrayList<>();
            if (head.getSize() > 0) {
                while (head.getSize() > 0) {
                    String pop = head.pop();
                    for (char aChar : chars) {
                        temps.add(pop + aChar);
                    }
                }

                head.pushAll(temps);
            }else{
                for (char aChar : chars) {
                    head.push( aChar+"");
                }
            }

            index++;
        }

        return head.getList();
    }

    public  static  class Head{
        private List<String> list = new ArrayList<>();

        public List<String> getList() {
            return list;
        }

        public int getSize(){
            return list.size();
        }
        public void pushAll(List<String> data){
            list.addAll(data);
        }
        public void push(String data){
            list.add(data);
        }
        public String pop(){
            String s = list.get(list.size()-1);
            list.remove(list.size() - 1);
            return s;
        }

    }


}
