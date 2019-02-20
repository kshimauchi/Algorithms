package programming.algorithm.zalgorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import programming.algorithm.zAlgorithm.ZAlgorithm;

public class ZAlgorithmTest {
    ZAlgorithm z;
    @Before
    public void init(){
        z = new ZAlgorithm();
    }
    @Test
    public void createLongStringTest(){
        Assert.assertArrayEquals("abc$edfabc".toCharArray(), z.createLongString("abc".toCharArray(), "edfabc".toCharArray()));
    }
    @Test
    public void createZTableTest(){
        // ab$aabb
        // 0001200
        Assert.assertArrayEquals(new int []{0,0,0,1,2,0,0},z.createZTable("ab".toCharArray(),"aabb".toCharArray()));
    }
    @Test
    public void searchTest(){
        Assert.assertEquals(-1, z.search("cc".toCharArray(), "dfabfabds".toCharArray()));
        Assert.assertEquals(2, z.search("ab".toCharArray(), "dfabfabds".toCharArray()));

        Assert.assertEquals(12, z.search("name".toCharArray(), "I knew your name already.".toCharArray()));
    }

}
