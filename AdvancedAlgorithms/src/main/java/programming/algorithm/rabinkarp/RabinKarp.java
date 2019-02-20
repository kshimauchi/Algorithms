package programming.algorithm.rabinkarp;

/**
 * Rabin-Karp algorithm
 * It uses hasing to accomplish a match between a pattern and array of characters.
 * It Uses rolling hash function to recalculate the hash on the subsequent possible matches.
 * It return the index of the match in the array.
 * It will return -1, if not match is discovered!
 */
public class RabinKarp {

    private final int prime = 3;

    /**
     * learning = 8
     * nin = 3
     *
     * @param array
     * @param pattern
     * @return
     */
    public int search(char[] array, char[] pattern) {
        if (array == null || pattern == null) return -1;

        int n = array.length;
        int m = pattern.length;
        int lastChar = (n - m);
        long patternHash = calculateHash(pattern, m);
        long arrayHash = calculateHash(array, m);

        for (int i = 0; i <= lastChar; i++) {
            if (patternHash == arrayHash && match(array, pattern, i)) {
                return i;
            }

            if (i < lastChar) {
                arrayHash = recalculateHash(arrayHash, array[i], array[i + m], m);
            }
        }
        return -1;
    }

    public boolean match(char[] array, char[] pattern, int index) {
        for (int i = 0; i < pattern.length; i++) {
            if (array[index + i] != pattern[i]) return false;
        }
        return true;
    }


    public long calculateHash(char[] text, int hashSize) {
        long hash = 0;
        for (int i = 0; i < hashSize; i++) {
            hash += charVal(text[i]) * Math.pow(prime, i);
        }
        return hash;
    }

    public long recalculateHash(long oldHash, char oldChar, char newChar, int hashSize) {
        long hash = oldHash - charVal(oldChar);
        hash = hash / prime;
        hash += charVal(newChar) * Math.pow(prime, hashSize - 1);
        return hash;
    }

    public int charVal(char val) {
        return val - 96;
    }
}
