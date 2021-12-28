package dao;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DAOUtilsTest {

    @Test
    void stringToList() {
        List<Integer> l = Arrays.asList(1, 10, 2, 5, 6, 200);
        List<Integer> appList = DAOUtils.stringToList("1;10;2;5;6;200");
        List<Integer> out = DAOUtils.stringToList(DAOUtils.listToString(l));


        Set<Integer> t1 = new TreeSet<>(l);
        Set<Integer> t2 = new TreeSet<>(appList);
        Set<Integer> t3 = new TreeSet<>(out);

        assertEquals(DAOUtils.stringToList(""), new ArrayList<Integer>());
        assertEquals(DAOUtils.stringToList(null), new ArrayList<Integer>());

        assertEquals(l.size(), appList.size());
        assertEquals(l.size(), out.size());
        assertEquals(appList.size(), out.size());
        assertEquals(t1, t2);
        assertEquals(t1, t3);
        assertEquals(t2, t3);
    }

    @Test
    void listToString() {
        List<Integer> l = Arrays.asList(1, 10, 2, 5, 6, 200);
        List<Integer> appList = DAOUtils.stringToList("1;10;2;5;6;200");
        List<Integer> out = DAOUtils.stringToList(DAOUtils.listToString(l));


        Set<Integer> t1 = new TreeSet<>(l);
        Set<Integer> t2 = new TreeSet<>(appList);
        Set<Integer> t3 = new TreeSet<>(out);

        assertEquals(DAOUtils.listToString(new ArrayList<>()), "");
        assertEquals(DAOUtils.listToString(null), "");

        assertEquals(l.size(), appList.size());
        assertEquals(l.size(), out.size());
        assertEquals(appList.size(), out.size());
        assertEquals(t1, t2);
        assertEquals(t1, t3);
        assertEquals(t2, t3);
    }

    @Test
    void safeValueOf() {
        assertEquals(DAOUtils.safeValueOf("1"), Integer.valueOf(1));
        assertEquals(DAOUtils.safeValueOf("-1"), Integer.valueOf(-1));
        assertEquals(DAOUtils.safeValueOf("0"), Integer.valueOf(0));
        assertEquals(DAOUtils.safeValueOf("10007"), Integer.valueOf(10007));
        assertEquals(DAOUtils.safeValueOf("-10007"), Integer.valueOf("-10007"));
        assertNull(DAOUtils.safeValueOf("wft"));
        assertNull(DAOUtils.safeValueOf("nan"));
        assertNull(DAOUtils.safeValueOf(""));
        assertNull(DAOUtils.safeValueOf(null));
    }
}