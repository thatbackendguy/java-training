package DSA.Sorting;

public class selectionSort
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

        for(int i = 0; i < nums.length - 1; i++)
        {
            int smallest = i;
            for(int j = i + 1; j < nums.length; j++)
            {
                if(nums[smallest] > nums[j])
                {
                    smallest = j;
                }
            }
            int temp = nums[smallest];
            nums[smallest] = nums[i];
            nums[i] = temp;
        }
        printArray(nums);
    }
}
