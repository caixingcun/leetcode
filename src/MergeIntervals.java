import java.util.*;
import java.util.stream.Collectors;

/**
 * 区间合并问题
 * 区间合并模式用来处理区间重叠
 * <p>
 * 当需要产生一堆相互没有交集的区间时
 * 当听到重叠区间的时候
 */
public class MergeIntervals {

    public static void main(String[] args) {
        // merge interval
//        testMergeInterval();
        // insert interval
//        testInsertInterval();
        int arr[][] = new int[][]{
                {8, 10}, {15, 18}, {1, 3}, {2, 6}
        };
        int[][] merge = new MergeIntervals().merge(arr);

        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
    }

    private static void testInsertInterval() {
        //give      [1,3] [6,9]     newInterval     [2,5]
        //return    [1,5] [6,9]
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(6, 9));
        List<Interval> results = insert(intervals, new Interval(2, 5));
        for (Interval result : results) {
            System.out.println(result.start + "-" + result.end);
        }

    }

    private static void testMergeInterval() {
//        Given
//        [1, 3],[2, 6],[8, 10],[15, 18]
//        return
//        [1, 6],[8, 10],[15, 18]
//        Given
//                [1, 4][4, 5]
//        return
//         [1, 5]

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 3));
        list.add(new Interval(2, 6));
        list.add(new Interval(8, 10));
        list.add(new Interval(15, 18));
        List<Interval> merge = merge(list);
        for (Interval interval : merge) {
            System.out.println(interval.start + "-" + interval.end);
        }

    }

    /**
     * Insert Interval
     * Input intervals [1,3] [6,9]; newInterval [2,5]
     * Output [1,5] [6,9]
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        for (Interval cur : intervals) {
            if (cur.end < newInterval.start) { //可以直接插入到后方
                result.add(cur);
            } else if (cur.start > newInterval.end) { // 可以直接插入到当前元素前方
                result.add(newInterval);
                newInterval = cur;
            } else if (cur.end >= newInterval.start || cur.start <= newInterval.end) {//有交叉
                newInterval = new Interval(Math.min(cur.start, newInterval.start), Math.max(cur.end, newInterval.end));
            }
        }
        result.add(newInterval);
        return result;
    }

    /**
     * Merge Intervals 合并区间
     *
     * @param intervals
     * @return
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        Collections.sort(intervals, new IntervalComparator());
        List<Interval> result = new ArrayList<>();
        //获取第一个区间
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            if (prev.end >= curr.start) { //球球大作战 从左向右 有重复内容 则合并/没有合并内容 则作为单独合并加入
                //合并区间
                Interval merge = new Interval(prev.start, Math.max(prev.end, curr.end));
                prev = merge;
            } else {
                result.add(prev);
                prev = curr;
            }
        }
        result.add(prev);
        return result;
    }

    /**
     * 区间结构
     */
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 比较器 进行区间排序
     */
    public static class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    }


    public int[][] merge(int[][] intervals) {
        // 区间问题
        // 先排序
        // 判断区间关系 进行合并 包含/反包含 部分重叠 不重叠

        Arrays.sort(intervals,(o1,o2)->o1[0]-o2[0]);

        int length = intervals.length;
        int[] temp = intervals[0];
        List<int[]> result_list = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            int temp_left = temp[0];
            int temp_right = temp[1];
            int[] interval = intervals[i];
            int curr_left = interval[0];
            int curr_right = interval[1];
            //已经排序 temp_left < curr_right
            if (temp_right>=curr_left) { //有相交 合并
                temp = new int[]{Math.min(temp_left, curr_left), Math.max(temp_right, curr_right)};
            }else{ //不相交
                result_list.add(temp);
                temp = interval;
            }
        }
        result_list.add(temp);
        int[][] result = new int[result_list.size()][];

        for (int i = 0; i < result_list.size(); i++) {
            result[i] = result_list.get(i);
        }

        return result;
    }
}


