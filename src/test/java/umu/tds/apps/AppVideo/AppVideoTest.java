package umu.tds.apps.AppVideo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.AppVideo;
import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppVideoTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void stringToListTest() {
        List<Integer> l = Arrays.asList(1,10,2,5,6,200);
        List<Integer> appList = AppVideo.getInstance().stringToList("1;10;2;5;6;200");
        List<Integer> out = AppVideo.getInstance().stringToList(AppVideo.getInstance().listToString(l));


        Set<Integer> t1 = new TreeSet<>(l);
        Set<Integer> t2 = new TreeSet<>(appList);
        Set<Integer> t3 = new TreeSet<>(out);

        assertEquals("Test string to list",t1,t2);
        assertEquals("Test list to string",t1,t3);
        assertEquals("Test equality",t2,t3);
    }
}
