package BasicAlgorithms.Programming;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {
    BubbleSort bs;
    @Before
    public void init(){
        bs = new BubbleSort();
    }

    @Test
    public void testSort(){
        Assert.assertArrayEquals(bs.bubbleSort(new int[]{0, 1, 2, 3, 4, 5}),new int []{0,1,2,3,4,5});
    }

}
