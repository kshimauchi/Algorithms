package programming.algorithm.Lzw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class LZWTest {
    LZW l;

    @Before
    public void init(){
        l = new LZW();
    }

    /*  LZW compress method,
    */

    @Test
    public void compressTest(){
        //ababcbc --> [91,98,256,99,98,99]
        List<Integer> result = l.compress("ababcbc");

        Assert.assertEquals(6, result.size());
        Assert.assertEquals(97, result.get(0).intValue());
        Assert.assertEquals(98, result.get(1).intValue());
        Assert.assertEquals(256, result.get(2).intValue());
        Assert.assertEquals(99, result.get(3).intValue());
        Assert.assertEquals(98, result.get(4).intValue());
        Assert.assertEquals(99, result.get(5).intValue());
        Assert.assertNull(l.compress(null));
    }

    @Test
    public void decompressTest(){
        /*  receive integers [97,98,256,99,98,99] --> ababcbc*/
        List<Integer> compressed = l.compress("ababcbc");
        Assert.assertEquals(6, compressed.size());
        Assert.assertEquals(97, compressed.get(0).intValue());
        Assert.assertEquals(98, compressed.get(1).intValue());
        Assert.assertEquals(256, compressed.get(2).intValue());
        Assert.assertEquals(99, compressed.get(3).intValue());
        Assert.assertEquals(98, compressed.get(4).intValue());
        Assert.assertEquals(99, compressed.get(5).intValue());
        //Assert.assertNull(l.compress(null));

        LZW lzw = new LZW(); //new instance so we can avoid cached instances data
        String decompressed = lzw.decompress(compressed);
        Assert.assertNotNull(decompressed);
        Assert.assertEquals("ababcbc", decompressed);

        List<Integer> compressed2 = l.compress("This is a test of compression. Just to test if compression works.");
        String decompressed2 = lzw.decompress(compressed2);
        Assert.assertNotNull(decompressed2);
        Assert.assertEquals("This is a test of compression. Just to test if compression works.", decompressed2);
    }
    @Test
    public void decompressSpecialCaseTest(){
        List<Integer> compressed = l.compress("abababa");

        Assert.assertEquals("abababa", l.decompress(compressed));
        //System.out.println(l.decompress(compressed));
    //Error because instance is being reused
    }
    @Test
    public void decompressInvalidCompression(){
        List<Integer> compressed = l.compress("abababa");
        compressed.add(456); //does not exist
        Assert.assertEquals("-1", l.decompress(compressed));
        System.out.println(l.decompress(compressed));
    }

}
