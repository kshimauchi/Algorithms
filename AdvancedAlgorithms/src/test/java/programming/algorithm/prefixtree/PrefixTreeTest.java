package programming.algorithm.prefixtree;

import org.junit.Assert;
import org.junit.Test;


public class PrefixTreeTest {


    @Test
    public void insertTest() {
        PrefixTree tree = new PrefixTree();
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);
        System.out.println(tree.root);
        Assert.assertTrue(tree.root.getChildren('c').getChildren('a').getChildren('t').isWord);
        Assert.assertEquals('c', tree.root.getChildren('c').c);
        Assert.assertEquals(0, tree.root.getChildren('c').id);
        Assert.assertEquals('a', tree.root.getChildren('c').getChildren('a').c);
        Assert.assertEquals(0, tree.root.getChildren('c').getChildren('a').id);
        Assert.assertEquals('t', tree.root.getChildren('c').getChildren('a').getChildren('t').c);
        Assert.assertEquals(1, tree.root.getChildren('c').getChildren('a').getChildren('t').id);
    }

    @Test
    public void findTest() {
        PrefixTree tree = new PrefixTree();
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);

        Assert.assertEquals(3, tree.find("cats".toCharArray()).id);
        Assert.assertEquals(2, tree.find("door".toCharArray()).id);
        Assert.assertEquals(1, tree.find("cat".toCharArray()).id);
        Assert.assertTrue(tree.find("cat".toCharArray()).isWord);
        Assert.assertTrue(tree.find("cats".toCharArray()).isWord);
        Assert.assertTrue(tree.find("door".toCharArray()).isWord);
        Assert.assertNull(tree.find("canfind".toCharArray()));
    }

    @Test
    public void deleteTest() {
        PrefixTree tree = new PrefixTree();
        tree.insert("cat".toCharArray(), 1);
        tree.insert("cats".toCharArray(), 2);
        tree.insert("door".toCharArray(), 3);
        tree.insert("done".toCharArray(), 4);
        tree.insert("horse".toCharArray(), 5);
        System.out.println(tree.root);

        tree.delete("horse".toCharArray());
        System.out.println(tree.root);

        tree.delete("cat".toCharArray());
        System.out.println(tree.root);

        tree.delete("cats".toCharArray());
        System.out.println(tree.root);

    }
}
