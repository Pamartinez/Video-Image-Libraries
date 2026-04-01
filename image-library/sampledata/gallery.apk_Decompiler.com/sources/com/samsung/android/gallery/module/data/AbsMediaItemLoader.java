package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.chain.Chain;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsMediaItemLoader implements Chain<AbsMediaItemLoader> {
    protected final String TAG = tag();
    AbsMediaItemLoader mNext;

    public MediaItem getMediaItem(Cursor cursor) {
        if (support(cursor)) {
            return getMediaItemInternal(cursor);
        }
        AbsMediaItemLoader absMediaItemLoader = this.mNext;
        if (absMediaItemLoader != null) {
            return absMediaItemLoader.getMediaItem(cursor);
        }
        String str = this.TAG;
        Log.e((CharSequence) str, "fail to load : " + cursor, "");
        return null;
    }

    public abstract MediaItem getMediaItemInternal(Cursor cursor);

    public MediaItem getPrimitiveMediaItem(Cursor cursor) {
        if (support(cursor)) {
            return getPrimitiveMediaItemInternal(cursor);
        }
        AbsMediaItemLoader absMediaItemLoader = this.mNext;
        if (absMediaItemLoader != null) {
            return absMediaItemLoader.getPrimitiveMediaItem(cursor);
        }
        return null;
    }

    public abstract MediaItem getPrimitiveMediaItemInternal(Cursor cursor);

    public abstract boolean support(Cursor cursor);

    public abstract String tag();

    public void setNext(AbsMediaItemLoader absMediaItemLoader) {
        this.mNext = absMediaItemLoader;
    }
}
