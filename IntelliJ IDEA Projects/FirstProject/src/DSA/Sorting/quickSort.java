package DSA.Sorting;

public class quickSort
{
    public static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = low - 1;
        for(int j = low; j < high; j++)
        {
            if(arr[j] < pivot)
            {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }
        }

        i++;

        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;

        return i;
    }

    public static void QuickSort(int arr[], int low, int high)
    {
        if(low < high)
        {
            int pivotIndex = partition(arr, low, high);

            QuickSort(arr, low, pivotIndex - 1);
            QuickSort(arr, pivotIndex + 1, high);
        }
    }

    public static void main(String[] args)
    {
        int arr[] = {81, 23, 63, 41, 59, 62, 77, 85};
        int n = arr.length;

        QuickSort(arr, 0, n - 1);

        for(int j : arr)
        {
            System.out.print(j + " ");
        }
    }
}
