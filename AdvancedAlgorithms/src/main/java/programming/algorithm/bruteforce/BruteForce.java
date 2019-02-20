package programming.algorithm.bruteforce;

import java.util.Arrays;

/*  Brute Force Algorithm (Naive Approach, but it works)
    Search for a pattern into an array
 *  if the pattern is found it will return the index of the array
  *  where the first letter of the pattern is found
  *
  * if no match is found it will return -1
  * Very Inefficient O(m*n) O(m+n)
  * */
public class BruteForce {
    /**
     *
     * @param array abcadef
     * @param pattern -> def
     * @return
     */
    public int findMatch(char [] array, char[] pattern){
        //we don't need to continue if the pattern length is greater then the
        //the remaining array length so we terminate a bit early
        for(int i=0 ; i <= array.length-pattern.length; i++){
            //move the pointer on j without moving i when starting a match
            for(int j= 0; j < pattern.length; j++){
                if(array[i+j] != pattern[j]) break;
                if(j == pattern.length -1) return i;
            }
        }
        return -1;
    }

    /**
     * This method searches for a pattern into an array
     * and returns an array of int with the indexes of the elements found
     * @param array
     * @param pattern
     * @return
     */
    public int [] everyMatch(char [] array, char[] pattern){
        int [] found = new int[array.length];
        Arrays.fill(found,-1);  //fill this -1 from Utils
        int index = 0;
        //for each position in the found array we will initialize to a -1

        for(int i=0 ; i <= array.length-pattern.length; i++){

            for(int j= 0; j < pattern.length; j++){
                if(array[i+j] != pattern[j]) break;
                if(j == pattern.length -1) {
                    found[index++]= i;

                }
            }
        }
        return found;
    }
}
