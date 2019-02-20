package programming.algorithm.huffman;

import java.util.PriorityQueue;

public class Huffman {

    final int CHARACTER_LIMIT = 256;
    StringBuilder header = new StringBuilder();
    //create frequencyTable
    //Because the size is unknown for every single character
    public int[] createFrequencyTable(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        for (int i = 0; i < text.length; i++) {
            frequencies[text[i]] = frequencies[text[i]] + 1;
        }
        return frequencies;
    }
    //create PriorityQueue
    //Create a list of ASCII characters, add huffman nodes
    //comparator will handle the ordering of the huffman tree
    public PriorityQueue < HuffmanNode > createPriorityQueue(int[] frequencies) {
        header = new StringBuilder();
        header.append((char) 1);
        PriorityQueue < HuffmanNode > queue = new PriorityQueue < HuffmanNode > (1, new FrequencyComparator());
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
                header.append(":").append((char) i).append(frequencies[i]);
            }
        }
        header.append((char) 2);
        return queue;
    }
    //make HuffmanTree
    /**
     * n2 =
     * n2 =
     * n1 = 2 a=2 b=3
     * Assumption is that your not getting a null queue
     */
    public HuffmanNode createHuffmanTree(PriorityQueue < HuffmanNode > queue) {
        HuffmanNode root = null;
        while (queue.size() > 0) {
            root = pullLeastUsedAsNode(queue);
            if (queue.size() > 0)
                queue.add(root);
        }
        return root;
    }
    public String compress(char[] text) {
        int[] frequencies = createFrequencyTable(text);
        PriorityQueue < HuffmanNode > queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String compressed = header.toString() + encodeString(text, root);
        return compressed;
    }

    /**
     * This method decompresses a text previously compressed by this algorithm
     * \u0001:a2:b3:c1:d1\u00021010000110111 becomes aabbbcd
     *
     * @param encodedText
     * @return
     */
    public char [] decompress(char[] encodedText){
        if(encodedText[0] != (char) 1) return null;
        int [] frequencies = parseHeaderAsFrequency(encodedText);
        PriorityQueue<HuffmanNode> queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String decompressed = decodeString(encodedText, root);
        return decompressed.toCharArray();
    }
    public String encodeString(char[] text, HuffmanNode root) {
        StringBuilder sb = new StringBuilder();
        //aabbccc replace 0110 etc we need to generate bytes of binary
        String array[] = new String[CHARACTER_LIMIT];
        generateBytes(array, root, new StringBuilder());
        //replace the text
        for (int i = 0; i < text.length; i++) {
            sb.append(array[text[i]]);
        }
        return sb.toString();
    }

    /**
     * This method decodes the huffman compression bits into characters
     * /u0001:A2:b3:c1:d1 /u0002 1010000110111
     * @param text
     * @param root
     * @return
     */
    public String decodeString(char [] text, HuffmanNode root){
        StringBuilder sb = new StringBuilder(); //String buffer is thread safe but slower and we do not need that here
        HuffmanNode currentNode = root;
        for(int i = header.length(); i < text.length; i++){
            //subtracting gets the proper value
            if(text[i]-'0' == 0) currentNode = currentNode.left;  //Goes left
            else if(text[i]-'0' == 1) currentNode = currentNode.right;//Goes right
            //currentNode.left == null && currentNode.right == null
            if(isLeaf(currentNode)){
                //Is a leaf so append
                sb.append(currentNode.c);
                currentNode = root;
            }
        }
        return sb.toString();

    }
    public void generateBytes(String[] array, HuffmanNode root, StringBuilder sb) {
        if (root.c == '-') {
            //A node which not a leaf
            //we traverse to the left
            sb.append("0");
            generateBytes(array, root.left, sb);
            sb.append("1");
            generateBytes(array, root.right, sb);

        } else {
            System.out.println(root.c + " - " + sb.toString());
            array[root.c] = sb.toString();
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    //      n1(-)
    //      c-1     d-1
    public HuffmanNode pullLeastUsedAsNode(PriorityQueue < HuffmanNode > queue) {
        HuffmanNode node1 = queue.poll(); //removes a node
        HuffmanNode node2 = queue.poll(); //removes another node

        //creates a root
        HuffmanNode root = new HuffmanNode('-', node1.frequency + node2.frequency);

        root.left = node1;
        root.right = node2;

        return root;

    }
    /**
     * /u0001:a2:b3:c1:d1/u00021010000110111
     *
     * @param text
     * @return
     */
    public int[] parseHeaderAsFrequency(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        int i=0;
        for(; i<text.length && text[i] != (char) 2; i++) {
            header.append(text[i]);
            if (text[i] == ':') {
                i++;
                header.append(text[i]);
                int f=0;
                int m=1;
                int j = i +1;
                for(; j<text.length && text[j] != (char) 2 && text[j] != ':'; j++) {
                    f = (f * m) + (text[j] - '0');
                    if (f != 0) m = 10;
                    header.append(text[i] - '0');
                }
                frequencies[text[i]] = f;
                i = j -1;
            }
        }
        return frequencies;
    }
    /**
     * this method returns true if the received node is a leaf node
     * @param node
     * @return
     */
    public boolean isLeaf(HuffmanNode node){
        if(node == null) return false;
        return node.left == null && node.right == null;
    }
}
