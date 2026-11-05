class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            int slow = i;
            int fast = i;
            boolean forward = nums[i] > 0;

            while (true) {
                slow = next(nums, slow, forward);

                if (slow == -1) break;

                fast = next(nums, fast, forward);

                if (fast == -1) break;

                fast = next(nums, fast, forward);

                if (fast == -1) break;

                if (slow == fast)
                    return true;
            }

            int current = i;

            while (nums[current] != 0 &&
                   (nums[current] > 0) == forward) {
                int next = getNext(nums, current);
                nums[current] = 0;
                current = next;
            }
        }

        return false;
    }

    private int next(int[] nums, int index, boolean forward) {
        if ((nums[index] > 0) != forward)
            return -1;

        int next = getNext(nums, index);

        if (next == index)
            return -1;

        return next;
    }

    private int getNext(int[] nums, int index) {
        int n = nums.length;
        return ((index + nums[index]) % n + n) % n;
    }
}