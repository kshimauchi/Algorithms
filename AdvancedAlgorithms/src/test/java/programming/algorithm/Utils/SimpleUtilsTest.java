package programming.algorithm.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleUtilsTest {

    SimpleUtils utils = null;
    @Before
    public void init(){
        utils = new SimpleUtils();
    }


    @Test
    public void StringToBooleanTrueTest(){
       // SimpleUtils utils = new SimpleUtils();
        Assert.assertTrue(utils.StringToBoolean("y"));   //expect true
        Assert.assertTrue(utils.StringToBoolean("Y"));
        Assert.assertTrue(utils.StringToBoolean("yEs"));   //expect true
        Assert.assertTrue(utils.StringToBoolean("YES"));
        Assert.assertTrue(utils.StringToBoolean("true"));   //expect true
        Assert.assertTrue(utils.StringToBoolean("true"));
    }
    @Test
    public void StringToBooleanFalseTest(){
        //SimpleUtils utils = new SimpleUtils();
        Assert.assertFalse(utils.StringToBoolean("n"));  //expect false
        Assert.assertFalse(utils.StringToBoolean("N"));   //expect true
        Assert.assertFalse(utils.StringToBoolean("no"));

        Assert.assertFalse(utils.StringToBoolean("NO"));   //expect true
        Assert.assertFalse(utils.StringToBoolean("false"));
        Assert.assertFalse(utils.StringToBoolean("false"));   //expect true
        Assert.assertFalse(utils.StringToBoolean("zebra"));
        Assert.assertFalse(utils.StringToBoolean(null));   //expect true

    }
    @Test
    public void getFileTypeByCode() {
        Assert.assertEquals(SimpleUtils.FileType.PDF, utils.getFileTypeByCode(3));
        Assert.assertEquals(SimpleUtils.FileType.JPEG, utils.getFileTypeByCode(1));
        Assert.assertNull(utils.getFileTypeByCode(999));
    }

    @Test
    public void getFileTypeByName() {
        Assert.assertEquals(SimpleUtils.FileType.TEXT, utils.getFileTypeByName("txt"));
        Assert.assertNull(utils.getFileTypeByName("zebra"));
        Assert.assertNull(utils.getFileTypeByName(null));
    }
}
