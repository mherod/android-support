package co.herod.android.support;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class ListUtils {

    public static <T> void merge(List<T> dest, List<? extends T> src) {
        if (dest.size() == 0) {
            dest.addAll(src);
            return;
        }
        boolean good;
        do {
            good = true;
            // remove all from dest not in source
            ListIterator<T> destIt = dest.listIterator();
            ListIterator<? extends T> srcIt = src.listIterator();
            Set<T> destSeenSet = new HashSet<>();
            while (srcIt.hasNext() && destIt.hasNext()) {
                T srcItem = srcIt.next();
                T destItem = destIt.next();

                if (destItem.equals(srcItem))
                    continue; // nothing to change

                good = false;

                if (!dest.contains(srcItem)) {
                    if (!src.contains(destItem))
                        destIt.remove();
                    destIt.add(srcItem);
                    continue;
                }
                destIt.remove();
                if (!destSeenSet.contains(srcItem)) {
                    while (destIt.hasNext() && !destIt.next().equals(srcItem))
                        destIt.remove();
                }
                destSeenSet.add(destItem);
            }
            while (srcIt.hasNext())
                destIt.add(srcIt.next());
            for (ListIterator<T> d = dest.listIterator(); d.hasNext();){
                if (!src.contains(d.next()))
                    d.remove();
            }
            System.out.printf("%d %d", src.size(), dest.size());
            System.out.printf("%s %s", src.toString(), dest.toString());
        } while (!dest.containsAll(src) || dest.size() != src.size() || !good);
    }

}
