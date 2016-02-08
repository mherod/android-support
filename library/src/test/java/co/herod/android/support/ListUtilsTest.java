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
    public Timeout globalTimeout = Timeout.seconds(5); // 5 seconds max per method tested

    @Test
    public void testMerge() {
        List<String> src = Lists.newArrayList("s1", "s2", "s3", "s4", "s5");
        List<String> dest = Lists.newArrayList("s2", "s1", "s2", "s4", "s6");

        ListUtils.merge(dest, src);

        assertEquals(src.size(), dest.size());
        ListIterator<String> srcIt = src.listIterator();
        ListIterator<String> destIt = dest.listIterator();
        while (srcIt.hasNext() && destIt.hasNext())
            assertEquals(srcIt.next(), destIt.next());
    }
}
