package com.samsung.android.gallery.module.utils;

import android.net.Uri;
import com.samsung.android.gallery.module.abstraction.ShareData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import q8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ShareList {
    private static final ArrayList<Integer> sShareBucketList = new ArrayList<>();
    private static final ArrayList<ShareData> sShareList = new ArrayList<>();
    private static final ArrayList<String> sSharePathList = new ArrayList<>();
    private static Uri sShareSinglePath;
    private static byte[] sSharedMemory;

    public static synchronized void clearShareList() {
        synchronized (ShareList.class) {
            sSharePathList.clear();
            sShareList.clear();
            sShareBucketList.clear();
            sShareSinglePath = null;
        }
    }

    public static synchronized ArrayList<Integer> getShareBucketList() {
        ArrayList<Integer> arrayList;
        synchronized (ShareList.class) {
            arrayList = sShareBucketList;
        }
        return arrayList;
    }

    public static synchronized ArrayList<ShareData> getShareList() {
        ArrayList<ShareData> arrayList;
        synchronized (ShareList.class) {
            arrayList = sShareList;
        }
        return arrayList;
    }

    public static synchronized ArrayList<String> getSharePathList() {
        ArrayList<String> arrayList;
        synchronized (ShareList.class) {
            arrayList = sSharePathList;
        }
        return arrayList;
    }

    public static byte[] getSharedMemory() {
        try {
            return sSharedMemory;
        } finally {
            sSharedMemory = null;
        }
    }

    public static int getSharedMemoryHash() {
        return ((Integer) Optional.ofNullable(sSharedMemory).map(new a(14)).orElse(0)).intValue();
    }

    public static synchronized void setShareBucketList(List<Integer> list) {
        synchronized (ShareList.class) {
            ArrayList<Integer> arrayList = sShareBucketList;
            arrayList.clear();
            arrayList.addAll(list);
        }
    }

    public static synchronized void setShareList(List<ShareData> list) {
        synchronized (ShareList.class) {
            ArrayList<ShareData> arrayList = sShareList;
            arrayList.clear();
            arrayList.addAll(list);
        }
    }

    public static synchronized void setSharePathList(ArrayList<String> arrayList) {
        synchronized (ShareList.class) {
            ArrayList<String> arrayList2 = sSharePathList;
            arrayList2.clear();
            arrayList2.addAll(arrayList);
        }
    }

    public static synchronized void setShareSingleUri(Uri uri) {
        synchronized (ShareList.class) {
            sShareSinglePath = uri;
        }
    }

    public static void setSharedMemory(byte[] bArr) {
        sSharedMemory = bArr;
    }
}
