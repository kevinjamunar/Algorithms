import java.util.Random;

public class QuickSort{

    public static void main(String[] args){

        int[] A = {8,19,32,9,5,92,46,11,23,7,12,56,7,98,54,3,7};
        int leftStartIndex = 0;
        int rightEndIndex = A.length-1;

        quickSort(leftStartIndex, rightEndIndex, A);
        printSortedArray(A);
    }

    private static void quickSort(int leftStartIndex, int rightEndIndex, int[] A){

        if(leftStartIndex>=rightEndIndex){
            return;
        }

        int i = partitionArray(leftStartIndex, rightEndIndex, A);
        quickSort(leftStartIndex, i-2, A); //recursive call on the left sub-array.
        quickSort(i,rightEndIndex, A); //recursive call on the right sub-array.
    }

    /*
    Partitions the array into 2 parts.
    All elements < pivot
    All elements > pivot.

    After partitioning, the pivot is sorted.

    leftStartIndex keeps track of the left end of the sub-array.
    rightEndIndex keeps track of the right end of the sub-array.

    i,j are used to traverse the sub-array in linear time
    without consuming any extra space.

    Total average running time = n*log(n)
    Minimum Space Consumption = log(n)
    Maximum Space Consumption = n
   */
    private static int partitionArray(int leftStartIndex, int rightEndIndex, int[] A){

        int pivot = chooseRandomPivot(leftStartIndex,rightEndIndex,A);

        int i=leftStartIndex+1;
        int j=leftStartIndex+1;

        for(;j<=rightEndIndex; j++){
            if(A[j]<pivot){
                int temp = A[j];
                A[j] = A[i];
                A[i] = temp;
                i++;
            }
        }

        //insert the pivot element in its sorted position
        A[leftStartIndex] = A[i-1];
        A[i-1] = pivot;

        return i;
    }

    private static void printSortedArray(int[] A){
        for(int a:A){
            System.out.print(a + "\t");
        }
    }

     /*
       Gets a random pivot within the range of the sub-array
       and swaps its position with the first element.
       This allows us to iterate left to right.
     */
    private static int chooseRandomPivot(int leftStartIndex, int rightEndIndex, int[] A){
        int pivot = new Random().nextInt(rightEndIndex-leftStartIndex + 1) + leftStartIndex;
        int temp = A[pivot];
        A[pivot] = A[leftStartIndex];
        A[leftStartIndex] = temp;
        return A[leftStartIndex];
    }
}
