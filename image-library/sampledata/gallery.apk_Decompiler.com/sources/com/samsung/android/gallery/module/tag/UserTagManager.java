package com.samsung.android.gallery.module.tag;

import M8.c;
import android.database.Cursor;
import android.util.LongSparseArray;
import com.samsung.android.gallery.database.dal.DbCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class UserTagManager {
    private static LongSparseArray<ArrayList<String>> mTagListMap = new LongSparseArray<>();

    public static void addTagData(ArrayList<Long> arrayList, String str) {
        Iterator<Long> it = arrayList.iterator();
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            ArrayList<String> tagList = getTagList(longValue);
            if (tagList.indexOf(str) < 0) {
                tagList.add(str);
            }
            mTagListMap.put(longValue, tagList);
        }
    }

    private static ArrayList<String> getTagList(long j2) {
        ArrayList<String> arrayList = mTagListMap.get(j2);
        if (arrayList != null) {
            return arrayList;
        }
        return loadInternal(j2);
    }

    private static ArrayList<String> loadInternal(long j2) {
        Cursor query = DbCompat.query("mp://myTag", new c(j2, 6));
        try {
            ArrayList<String> loadTagList = loadTagList(query);
            mTagListMap.put(j2, loadTagList);
            if (query != null) {
                query.close();
            }
            return loadTagList;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static synchronized ArrayList<String> loadTagList(Cursor cursor) {
        ArrayList<String> arrayList;
        synchronized (UserTagManager.class) {
            arrayList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(cursor.getString(cursor.getColumnIndex("__subCategory")));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    }

    public static void removeAllData(long j2) {
        mTagListMap.delete(j2);
    }

    public static void addTagData(long j2, ArrayList<String> arrayList) {
        ArrayList<String> tagList = getTagList(j2);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (tagList.indexOf(next) < 0) {
                tagList.add(next);
            }
        }
        mTagListMap.put(j2, tagList);
    }
}
