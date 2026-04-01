package com.samsung.android.gallery.module.creature.base;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MyQueryInterface {
    ArrayList<String> getTop5List();

    boolean isTop5Creature(String str, ArrayList<String> arrayList);

    void mergeTop5List(ArrayList<String> arrayList, String str);

    void removeTop5List(ArrayList<String> arrayList, ArrayList<String> arrayList2);
}
