package DSA.Sorting;

public class bubbleSort
{
    public static void printArray(int[] arr)
    {
        for(int j : arr)
        {
            System.out.print(j + " ");
        }
    }

    public static void main(String[] args)
    {
        int[] nums = {3, 7, 2, 6, 9, 1, 4};

        for(int i = 0; i < nums.length; i++)
        {
            for(int j = 0; j < nums.length - i - 1; j++)
            {
                if(nums[j] > nums[j + 1])
                {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        printArray(nums);
    }
}
