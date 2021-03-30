package january2021.week1.beautifularrangement;

class Solution {
    int count = 0;

    public int countArrangement(int n) {

        int[] nums = new int[n];
        for (int i =1; i <= n; i++) {
            nums[i-1] = i;
        }

        permute(nums, 0);
        return count;
    }

    private void permute(int[] nums, int index) {
//        permuteWithOverheadOfInvalidPermutation(nums, index); // TLE

//        permuteOnlyIfIndexSwappingDoesNotBreakBeautifulCondition(nums, index);


    }

    private void permuteOnlyIfIndexSwappingDoesNotBreakBeautifulCondition(int[] nums, int index) {
        if(index == nums.length) {
            count++;
        }

        for(int i = index; i< nums.length; i++) {
            swap(nums, i, index);

            if(nums[index] % (index+1) == 0 || (index+1)% nums[index] == 0) {
                permuteOnlyIfIndexSwappingDoesNotBreakBeautifulCondition(nums,  index +1);
            }
            swap(nums, i, index);
        }
    }
    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
    private void permuteWithOverheadOfInvalidPermutation(int[] nums, int index) { //TLE
        if (index == nums.length - 1) {
            int i;
            for (i = 1; i <= nums.length; i++) {
                if (nums[i - 1] % i != 0 && i % nums[i - 1] != 0) {
                    break;
                }
            }
            if (i == nums.length + 1) {
                count++;
            }
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            permuteWithOverheadOfInvalidPermutation(nums, index + 1);
            swap(nums, i, index);
        }
    }


}