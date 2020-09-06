package BasicAlgorithms.Programming;

public class BubbleSort {


    public int [] bubbleSort(int[] array){
        //Given an array bubblesort the input
        for(int j = array.length-1; j >= 0; j--){
            for(int i = 0; j >= i; i++){
                if(array[i] > array[i+1]){
                    int temp = array[i];
                    array[i]= array[j+1];
                    array[i+1]= temp;
                }
            }
        }


        return array;
    }



}
