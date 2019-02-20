package programming.algorithm.huffman;

import java.util.Comparator;

public class FrequencyComparator implements Comparator<HuffmanNode> {

    public int compare(HuffmanNode first, HuffmanNode second){

        return first.frequency - second.frequency;
    }
}
