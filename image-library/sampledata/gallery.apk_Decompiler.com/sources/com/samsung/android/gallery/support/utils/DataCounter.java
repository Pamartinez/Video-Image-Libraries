package com.samsung.android.gallery.support.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataCounter<KEY> {
    HashMap<KEY, Integer> map = new HashMap<>();

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$getSortedList$0(Object obj, Object obj2) {
        int compareTo = this.map.get(obj2).compareTo(this.map.get(obj));
        if (compareTo == 0) {
            return ((Comparable) obj).compareTo(obj2);
        }
        return compareTo;
    }

    public void add(KEY key) {
        this.map.put(key, Integer.valueOf(this.map.getOrDefault(key, 0).intValue() + 1));
    }

    public void clear() {
        this.map.clear();
    }

    public List<KEY> getSortedList() {
        ArrayList arrayList = new ArrayList(this.map.keySet());
        Collections.sort(arrayList, new C0669g(this));
        return arrayList;
    }
}
