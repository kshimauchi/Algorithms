package programming.algorithm.boyermoore;

/**
 * Boyer Moore Algorithm
 * This algorithm will search for a pattern in an array of character(s)
 * and return index of the match
 * or -1, in the case where no match was made
 *
 *  This will use two tables that will improve the time complexity
 * and return the search in less comparison than its bruteforce counter part
 */
public class BoyerMoore {

    private final int ASCII_TABLE_SIZE =256;
    /**
     * abcdbabaibai
     * baibai
     * 1st round [6,7,8,9,10,11] test with preprocessingSuffixTableTest()
     * @param pattern
     * @return
     */
    public int [] preprocessSuffixTable(char[] pattern){
        int [] table = new int[pattern.length];
        computePrefix(pattern, table);
        computeSuffix(pattern, table);
        return table;
    }

    /**
     * abcdbabaibai
     *    baibai
     *
     * @param array
     * @param pattern
     * @return
     */

    public int search(char [] array, char [] pattern){
        //Conditional
        if(pattern == null || pattern.length ==0) return 0;
        if(array == null) return -1;

        int [] badCharTable = preComputeBadCharTable(pattern);
        int [] suffixTable = preprocessSuffixTable(pattern);

        for(int i=pattern.length -1, j; i <array.length;){
            for(j= pattern.length-1; pattern[j]==array[i]; --i,--j){
                if(j==0) return i;
            }
           i += Math.max(suffixTable[pattern.length-1-j], badCharTable[array[i]]);
        }
        return -1;
    }

    /**
     * This method creates the 'bad' character table.  It will add pattern.length to every character
     * that is not in the pattern, Additionally, it adds pattern.length to the last character of the patter in
     * case it is unique
     *
     * test*
     * 3214=1
     *
     * abc
     * 213
     *
     * max(1, p.length - index -1)
     *
     * @param pattern
     * @return
     */
    int [] preComputeBadCharTable(char [] pattern){
        int [] table = new int[ASCII_TABLE_SIZE];
        for(int i=0 ; i < ASCII_TABLE_SIZE; i++){
            table[i] = pattern.length;
        }
        for(int t=0; t< pattern.length-1; t++){
            table[pattern[t]] = Math.max(1, pattern.length -t-1);
        }

        if(table[pattern[pattern.length-1]] < pattern.length){
            //recalculate
            table[pattern[pattern.length -1]]= 1;
        }
        return table;
    }
    public void computePrefix(char [] pattern, int [] table){
        int lastPrefixPosition = pattern.length;
        for(int i= pattern.length; i>0; --i){
            if(isPrefix(pattern, i))
                lastPrefixPosition = i;
            table[pattern.length -i] = lastPrefixPosition-i + pattern.length;
        }
    }
    public void computeSuffix(char [] pattern, int [] table){
        for(int i=0 ; i < pattern.length-1;  ++i){
            int len = suffixLength(pattern, i);

            table[len] = (pattern.length -1 -i + len );
        }
    }
    /**
     * baibai
     *   j  i
     * @param pattern
     * @param index
     * @return
     */
    public boolean isPrefix(char [] pattern, int index){

        for(int i= index, j=0; i < pattern.length; ++i, ++j){
            if(pattern[i] != pattern[j]) return false;
        }
    return true;
    }

    /**
     *
     * @param pattern
     * @param index
     * @return
     */
    public int suffixLength(char[] pattern, int index){
        int len =0;
        int j = pattern.length -1;
        for(int i = index; i >=0 && pattern[i]== pattern[j]; --i, --j){
            len++;
        }
        return len;
    }


}
