package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.e0;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbnailRequestHolder implements UniqueKey {
    private Bitmap mBitmap;
    private final MediaItem mMediaItem;
    private int mPosition;
    private final ImageView mView;
    protected final ListViewHolder mViewHolder;

    public ThumbnailRequestHolder(ListViewHolder listViewHolder) {
        this.mViewHolder = listViewHolder;
        this.mView = listViewHolder.getImage();
        this.mMediaItem = listViewHolder.getMediaItem();
        try {
            this.mPosition = listViewHolder.getViewPosition();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            this.mPosition = -1;
            Log.w("ThumbnailRequestHolder", "fail to getViewPosition : " + e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImageIfAvailable$0(Bitmap bitmap) {
        if (available()) {
            this.mViewHolder.bindImage(bitmap);
        }
    }

    public boolean available() {
        String tag = getTag();
        if (tag == null || !tag.equals(getPath()) || this.mViewHolder.getViewPosition() != this.mPosition) {
            return false;
        }
        return true;
    }

    public boolean bindImageIfAvailable(Bitmap bitmap) {
        if (!available()) {
            return false;
        }
        if (bitmap == null) {
            return true;
        }
        bitmap.prepareToDraw();
        runOnUi(new e0(this, bitmap, 0));
        return true;
    }

    public void bindResult() {
        this.mViewHolder.bindImage(this.mBitmap);
    }

    public boolean checkImageUid() {
        String tag = getTag();
        if (tag == null && PreferenceFeatures.OneUi5x.MX_ALBUMS && MediaItemUtil.isVirtualEmptyAlbum(this.mMediaItem)) {
            return true;
        }
        if (tag == null || !tag.equals(getPath())) {
            return false;
        }
        return true;
    }

    public int getChildItemCount() {
        return 0;
    }

    public int getKey() {
        return this.mView.hashCode();
    }

    public MediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public String getPath() {
        return this.mMediaItem.getPath();
    }

    public int getPosition() {
        return this.mPosition;
    }

    public String getTag() {
        return (String) this.mView.getTag();
    }

    public ListViewHolder getViewHolder() {
        return this.mViewHolder;
    }

    public void runOnUi(Runnable runnable) {
        if (ThreadUtil.isMainThread()) {
            runnable.run();
        } else {
            this.mViewHolder.itemView.post(runnable);
        }
    }

    public void setResult(Bitmap bitmap) {
        this.mBitmap = bitmap;
        if (bitmap != null) {
            bitmap.prepareToDraw();
        }
    }
}
