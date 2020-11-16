package programming.algorithm.suffixtrie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SuffixTrieMapTest {

    SuffixTrieMap t;

    @Before
    public void init() {
        t = new SuffixTrieMap("banana");
    }

    @Test
    public void insertSuffixTest() {
        /*
        --banana$
        --anana$
        --nana$
        --ana$
        --na$
        --a$

        banana$
        a$na$na$
        na$na$
         */
        Assert.assertNotNull(t.root.children.get('b').children.get('a').children.get('n').children.get('a').children.get('n').children.get('a').children.get('$'));
        Assert.assertNotNull(t.root.children.get('a').children.get('n').children.get('a').children.get('n').children.get('a').children.get('$'));
        Assert.assertNotNull(t.root.children.get('a').children.get('n').children.get('a').children.get('$'));
        Assert.assertNotNull(t.root.children.get('a').children.get('$'));
        Assert.assertNotNull(t.root.children.get('n').children.get('a').children.get('n').children.get('a').children.get('$'));
        Assert.assertNotNull(t.root.children.get('n').children.get('a').children.get('$'));


        System.out.println(t.root);
    }

    @Test
    public void searchTest() {
        List<Integer> r1 = t.search("ana");
        Assert.assertEquals(2, r1.size());
        Assert.assertEquals(3, r1.get(0).intValue());
        Assert.assertEquals(5, r1.get(1).intValue());

        List<Integer> r2 = t.search("a");
        Assert.assertEquals(3, r2.size());
        Assert.assertEquals(1, r2.get(0).intValue());
        Assert.assertEquals(3, r2.get(1).intValue());
        Assert.assertEquals(5, r2.get(2).intValue());

        Assert.assertNull(t.search("anc"));
    }

    @Test
    public void isSuffixTest() {
        Assert.assertTrue(t.isSuffix("ana"));
        Assert.assertTrue(t.isSuffix("na"));
        Assert.assertTrue(t.isSuffix("a"));
        Assert.assertFalse(t.isSuffix("ba"));
        Assert.assertFalse(t.isSuffix("nac"));
    }

    @Test
    public void isSubstringTest() {
        Assert.assertTrue(t.isSubstring("nan"));
        Assert.assertTrue(t.isSubstring("nana"));
        Assert.assertTrue(t.isSubstring("anana"));
        Assert.assertTrue(t.isSubstring("a"));
        Assert.assertFalse(t.isSubstring("ac"));
        Assert.assertFalse(t.isSubstring("c"));
    }
}
