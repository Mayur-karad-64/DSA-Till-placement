class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {

            // Partition nums1
            int partition1 = (low + high) / 2;

            // Partition nums2
            int partition2 = (m + n + 1) / 2 - partition1;

            // Left and right values around the partitions
            int left1 = (partition1 == 0)
                    ? Integer.MIN_VALUE
                    : nums1[partition1 - 1];

            int right1 = (partition1 == m)
                    ? Integer.MAX_VALUE
                    : nums1[partition1];

            int left2 = (partition2 == 0)
                    ? Integer.MIN_VALUE
                    : nums2[partition2 - 1];

            int right2 = (partition2 == n)
                    ? Integer.MAX_VALUE
                    : nums2[partition2];

            // Correct partition
            if (left1 <= right2 && left2 <= right1) {

                // Odd total length
                if ((m + n) % 2 == 1) {
                    return Math.max(left1, left2);
                }

                // Even total length
                int leftMax = Math.max(left1, left2);
                int rightMin = Math.min(right1, right2);

                return (leftMax + rightMin) / 2.0;
            }

            // nums1 partition is too far right
            else if (left1 > right2) {
                high = partition1 - 1;
            }

            // nums1 partition is too far left
            else {
                low = partition1 + 1;
            }
        }

        // Should never reach here for valid input
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}