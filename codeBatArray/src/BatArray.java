import java.util.Arrays;


/**
* Exercise CodingBat Array.
*
* @author simon wright
* @version
* @since 3/23/2026
*/
public class BatArray {
/**
* Given an int array length 2, return true if it does not contain a 2 or 3.
*
* @param nums pair of numbers
* @return whether no 2 or 3 is found
*/
public static boolean no23(int[] nums) {
  //for(int i = 0; i < nums.length; i++) {
    //if(nums[i] == 2 || nums[i] == 3) {
      //return false;
    //}
  //}
    //return true;
    return nums[0] != 2 && nums[0] != 3 && nums[1] != 2 && nums[1] != 3;
}

/**
* Given an int array, return a new array with double the length where its
* last element is the same as the original array, and all the other
* elements are 0. The original array will be length 1 or more. Note: by
* default, a new int array contains all 0's.
*
* @param nums the original numbers
* @return new array with double length
*/
public static int[] makeLast(int[] nums) {
    int[] newArray;
    newArray = new int[(nums.length*2)];

    newArray[newArray.length - 1] = nums[nums.length-1];

    return newArray;
}
/**
* Given an array of ints of even length, return a new array length 2
* containing the middle two elements from the original array. The original
* array will be length 2 or more.
*
* @param nums the original array
* @return middle two elements
*/
public static int[] makeMiddle(int[] nums) {
    int[] middle = new int[2];
    middle[0] = nums[(nums.length/2) - 1];
    middle[1] = nums[(nums.length/2)];
    return middle;
}
public static void main(String[] args) throws Exception {
        int[] no23Test = {4,5};
        int[] makeLastTest = {4,5,6};
        int[] makeMiddleTest = {7,1,2,3,4,9};
        makeLastTest=makeLast(makeLastTest);
        makeMiddleTest = makeMiddle(makeMiddleTest);
        System.out.println("no23 test - should be true - " + no23(no23Test));
        System.out.println("makeLast test - should be [0, 0, 0, 0, 0, 6] - " + Arrays.toString(makeLastTest));
        System.out.println("makeMiddle test - should be [2, 3] - " + Arrays.toString(makeMiddleTest));
    }
}
