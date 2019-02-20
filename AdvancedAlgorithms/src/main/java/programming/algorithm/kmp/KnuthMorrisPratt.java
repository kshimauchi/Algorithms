package programming.algorithm.kmp;

public class KnuthMorrisPratt {
    /** O(n+m) roughly
     *
     * <pre>
     * This method returns the index of the first match of the pattern into the index
     * Returns -1 in the event that it does not match
     * i
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13
     * a b a z a c a b a b a  b  a  c
     *
     * a b a b a c
     * 0 0 1 2 3 0
     * j
     * example returns 8;
     * </pre>
     * @param array
     * @param pattern
     * @return
     */

    public int search(char [] array, char[] pattern){
        int[] lsp = computeLSPTable(pattern);
        int j = 0;
        for(int i = 0; i < array.length; i++) {
            //
            while (j > 0 && (array[i] != pattern[j])) {
                j = lsp[j - 1];
            }
            if (array[i] == pattern[j]) {
                j++; //if it is match we increase j
                if (j == pattern.length) {

                    return (i - (j - 1));
                }
            }
        }
        return -1;
    }
    /**
     * This computes the table of the longest suffic and longest preffix on the pattern
     * This is to be used by Knuth-Morris-Pratt main method of the algorithm
     * @param pattern
     * @return
     */

    public int [] computeLSPTable(char[] pattern){

        int [] lsp = new int[pattern.length];
        int j=0;
        for(int i=1; i< pattern.length; i++){
            while(j > 0 && (pattern[i] != pattern[j]) ){
                j = lsp[j-1];
            }
            //Match
            if(pattern[i]== pattern[j]){
                lsp[i] = j + 1;
                j++;
            }else{
                //doesn't match
                lsp[i] = 0 ;
            }
        }
        return lsp;
    }
}
