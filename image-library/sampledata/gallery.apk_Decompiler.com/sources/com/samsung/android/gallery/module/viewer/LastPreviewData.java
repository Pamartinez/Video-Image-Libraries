package com.samsung.android.gallery.module.viewer;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Logger;
import java.lang.ref.SoftReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LastPreviewData {
    SoftReference<Bitmap> mBitmap;
    int mGroupPosition;
    boolean mIsPostCandidate;
    MediaItem mMediaItem;
    int mPosition;
    String mPppTargetPath;
    String tag;

    public LastPreviewData(Bitmap bitmap, MediaItem mediaItem, int i2) {
        this(bitmap, mediaItem, i2, -1);
    }

    public Bitmap getBitmap() {
        return this.mBitmap.get();
    }

    public int getGroupPosition() {
        return this.mGroupPosition;
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean isPostCandidate() {
        return this.mIsPostCandidate;
    }

    public LastPreviewData setPostCandidate() {
        this.mIsPostCandidate = true;
        return this;
    }

    public void setPppTargetPath(String str) {
        this.mPppTargetPath = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String toString() {
        return "LastPreviewData{" + this.tag + ", mBitmap=" + this.mBitmap + ", postCandidate=" + this.mIsPostCandidate + Logger.toString((Object) this.mMediaItem) + '}';
    }

    public LastPreviewData(Bitmap bitmap, MediaItem mediaItem, int i2, int i7) {
        this.mBitmap = new SoftReference<>(bitmap);
        this.mMediaItem = mediaItem;
        this.mPosition = i2;
        this.mGroupPosition = i7;
    }
}
