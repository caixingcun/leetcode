import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class SumTest {


    public static void main(String[] args) {
//        int arr[] = {-1, -1, 1, 1, 2, 2};
//        int arr[] = {-1,-1, 1, 1, 2, 2};
        int arr[] = {0,0,0,0};

        Arrays.sort(arr);

        List<List<Integer>> lists = fourSum(arr, 0);

        System.out.println(lists);
    }

    /**
     * 求n个数中 4数之和 等于目标值的 所有组合
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = threeSum(nums, i + 1, len - 1, target - nums[i]);
            for (List<Integer> list : lists) {
                list.add(0, nums[i]);
                result.add(list);
            }
        }
        return result;
    }


    public static List<List<Integer>> threeSum(int[] nums, int left, int right, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = left; i <= right-2; i++) { //right被包含
            if (i - 1 >= left && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = twoSum(nums, i + 1, right, target - nums[i]);
            for (List<Integer> list : lists) {
                list.add(0, nums[i]);
                result.add(list);
            }
        }
        return result;
    }

    public static List<List<Integer>> twoSum(int[] nums, int left, int right, int target) {
        List<List<Integer>> result = new ArrayList<>();
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                result.add(list);
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (sum > target) {
                right--;
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else {
                left++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
        return result;
    }

}
