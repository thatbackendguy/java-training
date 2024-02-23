package DSA.Sorting;

public class mergeSort
{
    public static void conquer(int arr[], int start, int mid, int end)
    {
        int merged[] = new int[end - start + 1];

        int index1 = start;
        int index2 = mid + 1;
        int x = 0;

        while(index1 <= mid && index2 <= end)
        {
            if(arr[index1] <= arr[index2])
            {
                merged[x++] = arr[index1++];
            }
            else
            {
                merged[x++] = arr[index2++];
            }
        }

        while(index1 <= mid)
        {
            merged[x++] = arr[index1++];
        }
        while(index2 <= end)
        {
            merged[x++] = arr[index2++];
        }

        for(int i = 0, j = start; i < merged.length; i++, j++)
        {
            arr[j] = merged[i];
        }
    }

    public static void divide(int arr[], int start, int end)
    {
        if(start >= end)
        {
            return;
        }

        int mid = start + (end - start) / 2;

        divide(arr, start, mid);
        divide(arr, mid + 1, end);

        conquer(arr, start, mid, end);
    }

    public static void main(String[] args)
    {
        int arr[] = {23, 65, 21, 76, 54, 26, 17, 52, 4};
        int n = arr.length;
        divide(arr, 0, n - 1);

        for(int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }
}
