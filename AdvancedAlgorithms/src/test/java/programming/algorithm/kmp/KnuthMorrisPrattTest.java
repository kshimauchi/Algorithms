package programming.algorithm.kmp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnuthMorrisPrattTest {
    KnuthMorrisPratt kmp = null;

    @Before
    public void init(){
        kmp = new KnuthMorrisPratt();
    }
    @Test
    public void searchTest(){
            String array = new String("abazacabababac");
            String pattern = new String("ababac");

            int index = kmp.search(array.toCharArray(), pattern.toCharArray());
            Assert.assertEquals(8, index);

            array = new String("aabaaabacaz");
            pattern = new String("aabacaz");
            index = kmp.search(array.toCharArray(), pattern.toCharArray());
            Assert.assertEquals(4, index);


            array = new String("this is a test");
            pattern = new String("is a");
            index = kmp.search(array.toCharArray(), pattern.toCharArray());
            Assert.assertEquals(5, index);

            array = new String("cantfindanindex");
            pattern = new String("foo");
            index = kmp.search(array.toCharArray(), pattern.toCharArray());
            Assert.assertEquals(-1, index);
    }

    @Test
    public void computeLSPTableTest(){
        KnuthMorrisPratt kmp = new KnuthMorrisPratt();
        int [] actual = kmp.computeLSPTable(new char []{'a','b','a','b','a','c'});
        int [] expect = new int[]{0,0,1,2,3,0};
        Assert.assertArrayEquals(expect,actual);

        actual = kmp.computeLSPTable(new char[]{'a','a','b','a','c'});
        expect = new int[] {0,1,0,1,0};
        Assert.assertArrayEquals(expect, actual);

        actual = kmp.computeLSPTable(new char[]{'a','b','c','d','a','b','c','a'});
        expect = new int[]{0,0,0,0,1,2,3,1};
        Assert.assertArrayEquals(expect, actual);


        actual = kmp.computeLSPTable(new char[]{'c','f','g','c','f','a'});
        expect = new int[]{0,0,0,1,2,0};
        Assert.assertArrayEquals(expect, actual);

        actual = kmp.computeLSPTable(new char[]{'a','a','b','a','c','a','z'});
        expect = new int[]{0,1,0,1,0,1,0};
        Assert.assertArrayEquals(expect, actual);


        actual = kmp.computeLSPTable(new char[]{'a'});
        expect = new int[]{0};
        Assert.assertArrayEquals(expect, actual);

        actual = kmp.computeLSPTable(new char[]{'a','a'});
        expect = new int[]{0,1};
        Assert.assertArrayEquals(expect, actual);

        actual = kmp.computeLSPTable(new char[]{'a','b'});
        expect = new int[]{0,0};
        Assert.assertArrayEquals(expect, actual);
    }
}
