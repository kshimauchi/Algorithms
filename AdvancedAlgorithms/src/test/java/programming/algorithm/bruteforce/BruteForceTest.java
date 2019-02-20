package programming.algorithm.bruteforce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BruteForceTest {
    BruteForce b = null;
    char[] array;


    @Before
    public void init(){
        b = new BruteForce();
        String s = new String("programming.algorithm");
        array = s.toCharArray();
    }
    @Test
    public void firstMatchTest(){


        Assert.assertEquals(0,b.findMatch(array, new char[]{'p'}));
        Assert.assertEquals(3, b.findMatch(array, new char[]{'g'}));
        Assert.assertEquals(19, b.findMatch(array, new char []{'h','m'}));
        Assert.assertEquals(10, b.findMatch(array, new char[]{'g','.','a'}));
        Assert.assertEquals(-1, b.findMatch(array, new char[]{'z','z'}));
    }
    @Test
    public void everyMatchTest(){
        int [] expect = new int[array.length];
        Arrays.fill(expect, -1);

        expect[0]= 2;   //search for 'o'
        expect[1]=15;
        System.out.println("expect " + Arrays.toString(expect));
        int [] found = b.everyMatch(array, new char[]{'o'});
       // System.out.println("found " +Arrays.toString(found));
        Assert.assertArrayEquals(expect, found);

        System.out.println();
        //Rewrite array for test
        Arrays.fill(expect, -1);
        expect[0]= 3;   //search for 'g'
        expect[1]= 10;
        expect[2]= 14;
        System.out.println("expect " + Arrays.toString(expect));
        found = b.everyMatch(array, new char[]{'g'});
        Assert.assertArrayEquals(expect, found);


    }

}
