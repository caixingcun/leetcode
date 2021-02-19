import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        testInsertInterval();

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
            } else if (cur.end >= newInterval.start||cur.start<=newInterval.end) {//有交叉
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
}


