import javafx.util.Pair;

import java.util.*;

/**
 * Cyclic Sort 循环排序/圈排序
 * 处理数组中数值限定在一定区间的问题
 * <p>
 * 一般涉及到排序好的数组 数值一般满足于移动区间
 * 让你排序/翻转数组，寻找丢失/重复/最小的元素
 */
public class CyclicSort {

    public static void main(String[] args) {

        testCyclicSort();

    }

    /**
     * CyclicSort
     * Find All Numbers Disappeared in an Array from 1 to n
     * input [4,3,2,7,8,2,3,1]
     * output [5,6]
     * <p>
     * 解题思路
     * origin 【4,3,2,7,8,2,3,1】
     * <p>
     * result 【1,2,3,4,5,6,7,8】
     * X X X X     X X
     * 按照Origin 江对应位置数给标记出来
     * （实际上就是为了标记位置，但为了降低空间复杂度，直接标记原数组，二次循环能识别出来即可）
     * （因为轮训先后，标记的数得能取出来原数据）
     * （*-1，绝对值取出原数据/+N，%N取出原数据）
     * 循环标记后的数组，未被标记的索引 即缺失的数
     */
    private static void testCyclicSort() {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};
//        int[] arr = {8, 8, 8, 8, 8, 8, 8, 8};
        List<Integer> disappearedNumbers = findDisappearedNumbers2(arr);
        System.out.println(disappearedNumbers.toString());
    }

    private static List<Integer> findDisappearedNumbers2(int[] arr) {

        int N = arr.length;
        List<Integer> res = new ArrayList<>();
        if (arr.length == 0) {
            return res;
        }

        for (int i = 0; i < arr.length; i++) {
            int idx = (arr[i] - 1) % N;
            arr[idx] = arr[idx] + N;
            System.out.println("i=" + i + " idx=" + idx + " arr[" + idx + "]=" + arr[idx]);
            System.out.println("i=" + i + " arr=" + Arrays.toString(arr));
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= N) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private static List<Integer> findDisappearedNumbers(int[] arr) {
        List<Integer> res = new ArrayList<>();
        if (arr.length == 0) {
            return res;
        }

        for (int i = 0; i < arr.length; i++) {
            int idx = Math.abs(arr[i]) - 1;
            arr[idx] = arr[idx] > 0 ? -arr[idx] : arr[idx];
            System.out.println("i=" + i + " idx=" + idx + " arr[" + idx + "]=" + arr[idx]);
            System.out.println("i=" + i + " arr=" + Arrays.toString(arr));

        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static List<Integer> findDisappearedNumbers1(int[] nums) {
        for (int i : nums) {
            int index = Math.abs(i);
            if (nums[index - 1] > 0) {
                nums[index - 1] *= -1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    /**
     * 情侣牵手
     * Input [0, 2, 1, 3]
     * Output 1
     * <p>
     * 处理方案  由于是偶数
     * 最终更换后结果
     * <p>
     * [0,2] [1,3]
     * [0,1] [2,3
     *
     * @param row
     * @return
     */
    public static int minSwapsCouples(int[] row) {
        //  01 无需交换 一个联通分量

        //  02 13   一个联通分量 （交换一次后）  01 23  2个联通分量

        //  05 24 13  1个联通分量 （交换两次后） 01  23  45  3个联通分量

        //...

        // N组数据   最终联通分量N  交换次数 = N - 交换前联通分量

        //只需要计算 交换前联通分量 即可得出最小交换次数
        //
        // 需要一个并查集结构
        // 并查集 ： N个元素的集合应用问题，需要让每一个元素构成一个单元素集合
        // 然后按一定顺序将属于同一组的集合合并其中，其间要反复查询一个元素在哪个集合中
        // 最好使用 树形数据结构 处理一些不相交集合合并及查询问题

        //总长度 = N * 2
        int len = row.length;
        //总节点数
        int N = len / 2;

        UnionFind unionFind = new UnionFind(N);

        for (int i = 0; i < len; i+=2) {
            unionFind.union(row[i]/2,row[i+1]/2); //合并每一组元素，合并后 并查集会进行联通量处理 ，非自成节点的联通量需-1
        }
        return N - unionFind.getCount();

    }

    /**
     * 并查集 数据结构
     */
   static class UnionFind {
        private int[] parent;
        //联通量
        private int count;

        public int getCount() {
            return count;
        }

        /**
         * 并查集 根据传入的节点数 创建对应尺寸的数组 并将每个节点都 存储一个节点元素
         * @param n
         */
        public UnionFind(int n){
            this.count = n; //默认联通量N对
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        //x == parent[x] 时 x为传入x的根节点
        public int find(int x){
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * 合并
         * @param x
         * @param y
         */
        public void union(int x,int y){
            //x = row[i]/2
            //y = row[i+1]/2
            // row[i] row[i+1] 是一组数 整除2 为其所在节点位置 0-N  ，如果相等说明自成一组 ，联通量无减少
            int rootX = find(x); //有节点找到节点
            int rootY = find(y); //两个数的节点一致 说明两个数自成一环 联通量无减少
            if (rootX == rootY) {
                return;
            }
            //走下面的代码 说明 找到了其环 并且不是原节点自成一环 ，联通量-1
            parent[rootX] = rootY;
            count--;
        }
    }


}
