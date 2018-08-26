
public class MergeSort {
    public static void main(String[] args){

        /*array to be sorted*/
        int[] A = {7,3,5,4,1,9,8,11};

        int start = 0; //beginning index of the array
        int end = A.length-1; //ending index of the array

       mergesort(A, start, end);
       printSortedArray(A);
    }
    private static void mergesort(int[] A, int leftStartIndex, int rightEndIndex){
        if(leftStartIndex >= rightEndIndex){
            return;
        }
        int leftEndIndex = (leftStartIndex + rightEndIndex)/2;
        int rightStartIndex = leftEndIndex+1;

        mergesort(A, leftStartIndex, leftEndIndex);
        mergesort(A, rightStartIndex, rightEndIndex);
        merge(A, leftStartIndex, leftEndIndex, rightStartIndex, rightEndIndex);
    }

    private static void merge(int[] A, int leftStartIndex, int leftEndIndex, int rightStartIndex, int rightEndIndex){

        int []leftArray = new int[(leftEndIndex-leftStartIndex)+1];
        int []rightArray = new int[(rightEndIndex-rightStartIndex)+1];

        int i=0;//variable to iterate through the left array.
        int j=0;//variable to iterate through the right array.

       for(int k=leftStartIndex; k<=leftEndIndex; k++){
           leftArray[i] = A[k];
           i++;
       }

       for(int k=rightStartIndex; k<=rightEndIndex; k++){
           rightArray[j] = A[k];
           j++;
       }

       i=0;
       j=0;

        for(int k=leftStartIndex; k<=rightEndIndex; k++){
            /*If both i and j do not exceed their respective array lengths*/
            if(i < leftArray.length && j < rightArray.length){
                if(leftArray[i] < rightArray[j]){
                    A[k] = leftArray[i];
                    i++;
                }
                else {
                    A[k] = rightArray[j];
                    j++;
                }
            }
            /*otherwise, if all of the left array is copied, copy over everything from the right array.*/
            else if (j < rightArray.length){
                A[k] = rightArray[j];
                j++;
            }
            /*otherwise, if all of the right array is copied, copy over everything from the left array.*/
            else if(i < leftArray.length){
                A[k] = leftArray[i];
                i++;
            }
        }
    }

    /*method to print array A*/
    private static void printSortedArray(int[] A){
        for (int a : A) {
            System.out.print(a + " ");
        }
    }
}


