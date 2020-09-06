package programming.algorithm.Selectionsort;
/*
*  Space-Complexity : n^2
*  Time-complexity  :
*
*  The selection sort algorithm sorts an array by repeatedly
*  finding the minimum element (considering ascending order)
*  from unsorted part and putting it at the beginning. The
*  algorithm maintains two subarrays in a given array.
*  1) The subarray which is already sorted.
*  2) Remaining subarray which is unsorted
*
* */
public class SelectionSort{


    public int[] selectionSort(int[] unorderedarray){

        for(int i=0;i<= unorderedarray.length; i++){

            int min_index = i;

            for(int j = i+1; j < unorderedarray.length; j++){
                if(unorderedarray[j]< unorderedarray[minimum])
                min_index =j;
            }
        }
        int temp = unorderedarray[minimum];
        unorderedarray[min_index]=unorderedarray[i];
        unorderedarray[i] = temp;

    }
    int [] sorted = new int[unorderedarray.length];

    return sorted =  unorderedarray;

}