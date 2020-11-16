package programming.algorithm.rabinkarp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RabinKarpTest {
    RabinKarp r = null;

    @Before
    public void init(){
        r = new RabinKarp();
    }
    @Test
    public void calculateHashTest(){
        Assert.assertEquals(28L, r.calculateHash("acbacc".toCharArray(),3));
    }

    @Test
    public void charValTest(){
        //a = 97
        Assert.assertEquals(97, (int)'a');
        Assert.assertEquals(1, r.charVal('a'));
    }
}
