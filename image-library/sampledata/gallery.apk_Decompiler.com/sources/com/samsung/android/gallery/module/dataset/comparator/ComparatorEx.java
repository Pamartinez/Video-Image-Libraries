package com.samsung.android.gallery.module.dataset.comparator;

import java.util.ArrayList;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ComparatorEx<T> extends Comparator<T> {
    void complete(ArrayList<T> arrayList);

    void prepare(ArrayList<T> arrayList);
}
