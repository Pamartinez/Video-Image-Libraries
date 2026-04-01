package com.samsung.android.gallery.support.library.abstraction;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import i.C0212a;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmOptions implements Closeable {
    public final String mBgmName;
    private ArrayList<BgmOption> mBgmOptions = new ArrayList<>();
    public int mBodyLastIndex;
    public int mBodyRepeatCount;
    private ArrayList<Closeable> mCloseables = new ArrayList<>();
    public final int mDuration;
    public boolean mUseFadeOut;
    public boolean mUseOutro;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BgmOption {
        public int duration;

        /* renamed from: fd  reason: collision with root package name */
        public FileDescriptor f3141fd;
        public Uri uri;

        public BgmOption(Uri uri2, ParcelFileDescriptor parcelFileDescriptor, int i2) {
            FileDescriptor fileDescriptor;
            this.uri = uri2;
            if (parcelFileDescriptor != null) {
                fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            } else {
                fileDescriptor = null;
            }
            this.f3141fd = fileDescriptor;
            this.duration = i2;
        }
    }

    public BgmOptions(String str, int i2) {
        this.mBgmName = str;
        this.mDuration = i2;
    }

    public void add(Uri uri, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.mBgmOptions.add(new BgmOption(uri, parcelFileDescriptor, i2));
        this.mCloseables.add(parcelFileDescriptor);
    }

    public void close() {
        release();
    }

    public BgmOption getBgmOption(int i2) {
        return this.mBgmOptions.get(i2);
    }

    public synchronized void release() {
        this.mBgmOptions.clear();
        Iterator<Closeable> it = this.mCloseables.iterator();
        while (it.hasNext()) {
            Closeable next = it.next();
            if (next != null) {
                try {
                    next.close();
                } catch (IOException e) {
                    Log.w("BgmOptions", "close failed " + e);
                }
            }
        }
        this.mCloseables.clear();
    }

    public void setPlaybackRule(int i2, int i7, boolean z, boolean z3) {
        this.mBodyRepeatCount = i2;
        this.mBodyLastIndex = i7;
        this.mUseOutro = z;
        this.mUseFadeOut = z3;
    }

    public int size() {
        return this.mBgmOptions.size();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append("[");
        return C0212a.p(sb2, this.mBgmName, "]");
    }
}
