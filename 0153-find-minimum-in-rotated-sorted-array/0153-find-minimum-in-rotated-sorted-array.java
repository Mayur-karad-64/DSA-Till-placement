class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[end]) {
                // minimum is in right half
                start = mid + 1;
            } else {
                // minimum is in left half or at mid
                end = mid;
            }
        }
        return nums[start];
    }
}
