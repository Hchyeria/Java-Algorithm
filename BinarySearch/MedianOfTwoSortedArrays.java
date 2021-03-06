package BinarySearch;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 0) {
            int[] temp = kth(nums1, 0, nums2, 0, (m+n) / 2);
            int one = temp[3];
            int two = 0;
            if (temp[2] == 1) {
                if (temp[0] < 0) {
                    // be careful the index value of temp[index]+1 might out of bound!!
                    two = nums2[Math.min(nums2.length-1, temp[1] + 1)];
                } else {
                    if (temp[1] + 1 >= nums2.length) {
                        two = nums1[temp[0]];
                    } else {
                        two = nums2[temp[1] + 1] <= nums1[temp[0]] ?
                                nums2[temp[1] + 1] :
                                nums1[temp[0]];
                    }
                }
            } else {
                if (temp[1] < 0) {
                    two = nums1[temp[0] + 1];
                } else {
                    if (temp[0] + 1 >= nums1.length) {
                        two = nums2[temp[1]];
                    } else {
                        two = nums1[temp[0] + 1] <= nums2[temp[1]] ?
                                nums1[temp[0] + 1] :
                                nums2[temp[1]];
                    }
                }
            }
            return (one + two) / 2.0;
        } else {
            return (double)kth(nums1, 0, nums2, 0, (m+n) / 2 + 1)[3];
        }
    }


    // return the four elements array
    // index 0 and 1 represent the value of aLeft and bLeft
    // index 3 value is either 0(represent the kth value is in the array a) or 1 (vice versa)
    // the last element represent the kth value
    private int[] kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
        if (aLeft >= a.length) {
            return new int[] {-1, bLeft + k - 1, 1, b[bLeft + k - 1]};
        }
        if (bLeft >= b.length) {
            return new int[] {aLeft + k - 1, -1, 0, a[aLeft + k - 1]};
        }
        if (k == 1) {
            return a[aLeft] <= b[bLeft] ?
                    new int[] {aLeft, bLeft, 0, a[aLeft]} :
                    new int[] {aLeft, bLeft, 1, b[bLeft]};
        }

        int aMid = aLeft + k / 2 - 1;
        int bMid = bLeft + k / 2 - 1;
        int aTarget = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
        int bTarget = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];

        if (aTarget <= bTarget) {
            return kth(a, aMid + 1, b, bLeft, k - k / 2);
        } else {
            return kth(a, aLeft, b, bMid+1, k - k / 2);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        System.out.println(medianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }
}
