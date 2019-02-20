package programming.algorithm.huffman;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;

public class HuffmanTest {
    Huffman h;

    @Before
    public void init() {
        h = new Huffman();
    }
    @Test
    public void createFrequency() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        Assert.assertEquals(h.CHARACTER_LIMIT, frequencies.length);
        Assert.assertEquals(2, frequencies[97]);
        Assert.assertEquals(3, frequencies[98]);
        Assert.assertEquals(1, frequencies[99]);
        Assert.assertEquals(1, frequencies[100]);

    }
    @Test
    public void createPriorityQueue() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue < HuffmanNode > queue = h.createPriorityQueue(frequencies);

        //c=1 d=1 a=2 b=3
        Assert.assertEquals('c', queue.peek().c); //looks at first element
        Assert.assertEquals(1, queue.poll().frequency); //Removes the first Element
        //d=1 a=2 b=3
        Assert.assertEquals('d', queue.peek().c); //looks at first element
        Assert.assertEquals(1, queue.poll().frequency); //Removes
        //a=2 b=3
        Assert.assertEquals('a', queue.peek().c); //looks at first element
        Assert.assertEquals(2, queue.poll().frequency); //Removes from the head = first element

        //b=3
        Assert.assertEquals('b', queue.peek().c); //looks at first element
        Assert.assertEquals(3, queue.poll().frequency); //Removes from the head


        //Check
        Assert.assertEquals(0, queue.size());

        //Added Header to the priority queue
        Assert.assertEquals("\u0001:a2:b3:c1:d1\u0002", h.header.toString());


    }
    @Test
    public void pullLeastUsedAsNode() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue < HuffmanNode > queue = h.createPriorityQueue(frequencies);

        //c=1 d=1 a=2 b=3
        HuffmanNode root = h.pullLeastUsedAsNode(queue); //This call polls off two nodes

        //a=2 b=3
        Assert.assertEquals('-', root.c);
        Assert.assertEquals(2, root.frequency);
        Assert.assertEquals('c', root.left.c);
        Assert.assertEquals(1, root.left.frequency);
        Assert.assertNull(root.left.left); //leaf
        Assert.assertNull(root.left.right);

        Assert.assertEquals('d', root.right.c);
        Assert.assertEquals(1, root.right.frequency);
        Assert.assertNull(root.right.left);
        Assert.assertNull(root.right.right);
        //a=2 b=3
        root = h.pullLeastUsedAsNode(queue);
        //empty
        //recalculate
        Assert.assertEquals('-', root.c);
        Assert.assertEquals(5, root.frequency);
        Assert.assertEquals('a', root.left.c);
        Assert.assertEquals(2, root.left.frequency);
        Assert.assertNull(root.left.left); //leaf
        Assert.assertNull(root.left.right);

        Assert.assertEquals('b', root.right.c);
        Assert.assertEquals(3, root.right.frequency);
        Assert.assertNull(root.right.left);
        Assert.assertNull(root.right.right);
    }

    /**
     *
     *          aabbbcd ('-') denote roots, ('') no dash is a leaf node
     *               Our Trees
     *               -7
     *           b       -4     <-- sum of a2 and  2
     *               a2     -2
     *                  c1      d1
     */
    @Test
    public void createHuffmanTreeTest() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue < HuffmanNode > queue = h.createPriorityQueue(frequencies);
        HuffmanNode root = h.createHuffmanTree(queue);

        //aabbbcd
        Assert.assertEquals('-', root.c);
        Assert.assertEquals(7, root.frequency);
        //traversing left
        Assert.assertEquals('b', root.left.c);
        Assert.assertEquals(3, root.left.frequency);
        //test null
        Assert.assertNull(root.left.left);
        Assert.assertNull(root.left.right);

        Assert.assertEquals('-', root.right.c);
        Assert.assertEquals(4, root.right.frequency);
        Assert.assertEquals('a', root.right.left.c);
        Assert.assertEquals(2, root.right.left.frequency);
        Assert.assertNull(root.right.left.left);
        Assert.assertNull(root.right.left.right);


        Assert.assertEquals('-', root.right.right.c);
        Assert.assertEquals(2, root.right.right.frequency);
        Assert.assertEquals('c', root.right.right.left.c);
        Assert.assertEquals(1, root.right.right.left.frequency);
        Assert.assertNull(root.right.right.left.left);
        Assert.assertNull(root.right.right.left.right);

        //Going left
        Assert.assertEquals('d', root.right.right.right.c);
        Assert.assertEquals(1, root.right.right.right.frequency);
        Assert.assertNull(root.right.right.right.left);
        Assert.assertNull(root.right.right.right.right);
    }
    @Test
    public void compressTest() {
        System.out.println(h.compress("aabbbcd".toCharArray()));
    }
    @Test
    public void generateBytesTest() {
        String[] st = new String[h.CHARACTER_LIMIT];
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue < HuffmanNode > queue = h.createPriorityQueue(frequencies);
        HuffmanNode root = h.createHuffmanTree(queue);
        h.generateBytes(st, root, new StringBuilder());
        Assert.assertEquals("10", st[97]); //a
        Assert.assertEquals("0", st[98]); //b
        Assert.assertEquals("110", st[99]); //c
        Assert.assertEquals("111", st[100]); //d

    }
    @Test
    public void encodeStringTest() {
        String text = "aabbbcd";
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue < HuffmanNode > queue = h.createPriorityQueue(frequencies);
        HuffmanNode root = h.createHuffmanTree(queue);
        String s = h.encodeString(text.toCharArray(), root);

        Assert.assertEquals("1010000110111", s);
    }
    @Test
    public void parseHeaderAsFrequencyTest(){
        char[] c = "\u0001:a2:b3:c1:d1\u00021010000110111".toCharArray(); // aabbbcd
        int[] freq = h.parseHeaderAsFrequency(c);
        Assert.assertEquals(2, freq['a']);
        Assert.assertEquals(3, freq['b']);
        Assert.assertEquals(1, freq['c']);
        Assert.assertEquals(1, freq['d']);
    }
    @Test
    public void decompressTest(){
        Huffman huffman = new Huffman();
        char[] c = "\u0001:a2:b3:c1:d1\u00021010000110111".toCharArray(); // aabbbcd
        char [] decompressed = huffman.decompress(c);

        Assert.assertArrayEquals("aabbbcd".toCharArray(), decompressed);
    }
    //decode string and decompress will have the same result
    @Test
    public void decodeString(){
        Huffman huffman = new Huffman();
        char[] c = "\u0001:a2:b3:c1:d1\u00021010000110111".toCharArray(); // aabbbcd
        int[] freq = h.parseHeaderAsFrequency(c);
        PriorityQueue<HuffmanNode>queue = huffman.createPriorityQueue(freq);
        HuffmanNode root = huffman.createHuffmanTree(queue);

        String decoded = huffman.decodeString(c, root);
        Assert.assertEquals("aabbbcd", decoded);
        Assert.assertNotSame("aaabbcd", decoded);
    }
    @Test
    public void isLeafTest(){
        HuffmanNode root = new HuffmanNode('-',2);
        HuffmanNode leftNode = new HuffmanNode('a',2);
        root.left = leftNode;
        Assert.assertTrue(h.isLeaf(leftNode));
        Assert.assertFalse(h.isLeaf(root));
        Assert.assertFalse(h.isLeaf(null));
    }
}