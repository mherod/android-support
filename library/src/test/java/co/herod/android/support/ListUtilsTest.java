package co.herod.android.support;

import com.google.common.collect.Lists;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.ListIterator;

import static junit.framework.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListUtilsTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(20); // max per method tested

    @Test
    public void testMerge() {
        List<String> src = Lists.newArrayList("s1", "s2", "s3", "s4", "s5", "s8");
        List<String> dest = Lists.newArrayList("s2", "s1", "s2", "s4", "s6", "s7", "s9");

        ListUtils.merge(dest, src);
        verifyLists(src, dest);

        src = Lists.newArrayList("s1", "s2", "s3", "s4", "s5", "s8", "s10", "s7", "s6");
        dest = Lists.newArrayList("s2", "s1", "s2", "s4", "s6", "s7", "s9");

        ListUtils.merge(dest, src);
        verifyLists(src, dest);
    }

    private void verifyLists(List<?> aList, List<?> bList) {
        assertEquals(aList.size(), bList.size());
        ListIterator<?> aIterator = aList.listIterator();
        ListIterator<?> bIterator = bList.listIterator();
        while (aIterator.hasNext() && bIterator.hasNext())
            assertEquals(aIterator.next(), bIterator.next());
    }
}
