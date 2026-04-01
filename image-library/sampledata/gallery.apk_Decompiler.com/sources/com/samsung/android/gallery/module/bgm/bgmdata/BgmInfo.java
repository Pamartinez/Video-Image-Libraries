package com.samsung.android.gallery.module.bgm.bgmdata;

import A.a;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.AppResources;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmInfo {
    protected final ArrayList<BgmFileInfo> mBgmFileInfoList = new ArrayList<>();

    public void add(String str) {
        this.mBgmFileInfoList.add(new BgmFileInfo(Uri.parse(str)));
    }

    public void addFileInfo(ArrayList<Uri> arrayList) {
        Iterator<Uri> it = arrayList.iterator();
        while (it.hasNext()) {
            this.mBgmFileInfoList.add(new BgmFileInfo(it.next()));
        }
    }

    public int getDuration(int i2) {
        return this.mBgmFileInfoList.get(i2).getDuration();
    }

    public ParcelFileDescriptor getParcelFileDescriptor(int i2) {
        return this.mBgmFileInfoList.get(i2).getParcelFileDescriptor();
    }

    public Uri getUri(int i2) {
        return this.mBgmFileInfoList.get(i2).getUri();
    }

    public ArrayList<Uri> getUris() {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.mBgmFileInfoList.size(); i2++) {
            arrayList.add(getUri(i2));
        }
        return arrayList;
    }

    public boolean isDummy() {
        return false;
    }

    public int size() {
        return this.mBgmFileInfoList.size();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BgmFileInfo {
        private Integer mDuration;
        private final Uri mUri;

        public BgmFileInfo(Uri uri) {
            this.mUri = uri;
        }

        public int getDuration() {
            if (this.mDuration == null) {
                this.mDuration = Integer.valueOf(getDuration(this.mUri));
            }
            return this.mDuration.intValue();
        }

        public ParcelFileDescriptor getParcelFileDescriptor() {
            try {
                return AppResources.getAppContext().getContentResolver().openFileDescriptor(this.mUri, "r");
            } catch (FileNotFoundException | NullPointerException | SecurityException e) {
                a.s(e, new StringBuilder("openFileDescriptor failed = "), "BgmFileInfo");
                return null;
            }
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getDuration(Uri uri) {
            MediaMetadataRetriever mediaMetadataRetriever;
            Context appContext = AppResources.getAppContext();
            int i2 = 0;
            if (!(appContext == null || uri == null)) {
                try {
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(appContext, uri);
                    String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
                    if (!TextUtils.isEmpty(extractMetadata)) {
                        i2 = (int) Long.parseLong(extractMetadata);
                    }
                    mediaMetadataRetriever.close();
                    return i2;
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            return i2;
            throw th;
        }
    }

    public void addFileInfo(List<String> list) {
        for (String add : list) {
            add(add);
        }
    }
}
