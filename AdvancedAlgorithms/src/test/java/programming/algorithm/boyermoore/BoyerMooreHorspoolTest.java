package programming.algorithm.boyermoore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoyerMooreHorspoolTest {

    BoyerMooreHorspool b = null;
    @Before
    public void init(){
        b = new BoyerMooreHorspool();
    }
    @Test
    public void preprocessTableTest(){

        int [] table = b.preprocessTable("test".toCharArray());
        Assert.assertEquals(1, table['t']);
        Assert.assertEquals(2, table['e']);
        Assert.assertEquals(1, table['s']);
        Assert.assertEquals(4, table['x']);


        table = b.preprocessTable("abc".toCharArray());
        Assert.assertEquals(2, table['a']);
        Assert.assertEquals(1, table['b']);
        Assert.assertEquals(3, table['c']);
        Assert.assertEquals(3, table['g']);


        table = b.preprocessTable("abcdb".toCharArray());
        Assert.assertEquals(4, table['a']);
        Assert.assertEquals(1, table['b']);
        Assert.assertEquals(2, table['c']);
        Assert.assertEquals(1, table['d']);
        Assert.assertEquals(5, table['j']);
    }
    @Test
    public void searchTest(){
        //Feel free to test this to your heart's content
        Assert.assertEquals(4, b.search("learning".toCharArray(),"nin".toCharArray()));
        Assert.assertEquals(4, b.search("learning".toCharArray(),"nin".toCharArray()));
        Assert.assertEquals(0, b.search("learning".toCharArray(), "learning".toCharArray()));
        Assert.assertEquals(5, b.search("learning".toCharArray(), "in".toCharArray()));
        Assert.assertEquals(0, b.search("learning".toCharArray(), "le".toCharArray()));

        Assert.assertEquals(-1, b.search("learning".toCharArray(), "x".toCharArray()));
        Assert.assertEquals(0, b.search("learning".toCharArray(), null));
        Assert.assertEquals(0, b.search(null, null));
    }
}
