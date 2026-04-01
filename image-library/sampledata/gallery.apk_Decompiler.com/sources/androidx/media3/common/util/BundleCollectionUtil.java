package androidx.media3.common.util;

import E2.h;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BundleCollectionUtil {
    public static <T> ArrayList<Bundle> toBundleArrayList(Collection<T> collection, h hVar) {
        ArrayList<Bundle> arrayList = new ArrayList<>(collection.size());
        for (T apply : collection) {
            arrayList.add((Bundle) hVar.apply(apply));
        }
        return arrayList;
    }
}
