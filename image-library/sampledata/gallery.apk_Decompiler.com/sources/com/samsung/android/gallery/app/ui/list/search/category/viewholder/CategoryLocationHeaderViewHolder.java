package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import A.a;
import B8.e;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemCompat;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import h.C0199b;
import k6.b;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryLocationHeaderViewHolder extends CategoryTitleCountViewHolder {
    private ImageView mMarkerImageView;
    private MediaItem mMarkerItem;
    private View mMarkerView;

    public CategoryLocationHeaderViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadMarkerBitmap$0(MediaItem mediaItem, Bitmap bitmap) {
        if (mediaItem.equals(this.mMarkerItem)) {
            bindMarkerImage(mediaItem, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadMarkerBitmap$1(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new C0235b(this, mediaItem, bitmap, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMarkerImageViewMatrix$2(MediaItem mediaItem) {
        setViewMatrix(this.mMarkerImageView, mediaItem);
    }

    private MediaItem queryMarkerItem() {
        Cursor latestLocationData;
        try {
            latestLocationData = new LocationApi().getLatestLocationData(0);
            if (latestLocationData != null) {
                if (latestLocationData.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(latestLocationData);
                    latestLocationData.close();
                    return load;
                }
            }
            if (latestLocationData == null) {
                return null;
            }
            latestLocationData.close();
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("queryMarkerItem failed "), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void setMarkerImageViewMatrix(MediaItem mediaItem) {
        if (ViewUtils.getWidth(this.mMarkerImageView) > 0 || ViewUtils.getHeight(this.mMarkerImageView) > 0) {
            setViewMatrix(this.mMarkerImageView, mediaItem);
        } else {
            this.mMarkerImageView.post(new C0199b(16, this, mediaItem));
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mImageView.setBackgroundResource(R.drawable.gallery_map_bg);
        String loadCache = loadCache();
        if (loadCache != null) {
            loadMarkerBitmap(MediaItemCompat.parse(loadCache));
        }
        SimpleThreadPool.getInstance().execute(new b(23, this));
    }

    public void bindMarkerImage(MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap == null) {
            mediaItem.setBroken(true);
            this.mMarkerImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.mMarkerImageView.setImageDrawable(getBrokenDrawable(getBrokenBitmap(mediaItem), getColorFilter(false)));
        } else {
            this.mMarkerImageView.setScaleType(ImageView.ScaleType.MATRIX);
            this.mMarkerImageView.setImageBitmap(bitmap);
            setMarkerImageViewMatrix(mediaItem);
        }
        this.mMarkerView.setVisibility(0);
    }

    public void bindView(View view) {
        super.bindView(view);
        View findViewById = view.findViewById(R.id.map_view_marker_holder);
        this.mMarkerView = findViewById;
        findViewById.findViewById(R.id.map_view_marker_background).setBackgroundResource(R.drawable.gallery_map_view_popup_normal_selected);
        this.mMarkerImageView = (ImageView) this.mMarkerView.findViewById(R.id.map_view_marker);
    }

    public long getHeaderExpireTime() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            return ((Long) mediaItem.getTag("HeaderExpireTime", 0L)).longValue();
        }
        return 0;
    }

    public boolean isEnabled() {
        return this.itemView.isEnabled();
    }

    public String loadCache() {
        return GalleryPreference.getInstanceCache().loadString("CatLocationHeader", (String) null);
    }

    public void loadMarkerBitmap(MediaItem mediaItem) {
        MediaItem mediaItem2 = this.mMediaItem;
        if (mediaItem2 != null) {
            MediaItem mediaItem3 = this.mMarkerItem;
            if (mediaItem3 == null || !matchMarker(mediaItem3, mediaItem)) {
                this.mMarkerItem = mediaItem;
                mediaItem2.setLatLong(new double[]{mediaItem.getLatitude(), mediaItem.getLongitude()});
                ThumbnailLoader.getInstance().getOrLoad(mediaItem, ThumbKind.SMALL_CROP_KIND, new e(mediaItem, 1), new l6.a(2, this, mediaItem));
            }
        }
    }

    public void loadMarkerItem() {
        if (this.mMarkerItem == null || System.currentTimeMillis() >= getHeaderExpireTime()) {
            updateHeadExpireTime();
            MediaItem queryMarkerItem = queryMarkerItem();
            if (queryMarkerItem != null && !matchMarker(queryMarkerItem, this.mMarkerItem)) {
                saveCache(MediaItemCompat.toString(queryMarkerItem));
                loadMarkerBitmap(queryMarkerItem);
            }
        }
    }

    public boolean matchMarker(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || !TextUtils.equals(MediaItemCompat.toString(mediaItem), MediaItemCompat.toString(mediaItem2))) {
            return false;
        }
        return true;
    }

    public void recycle() {
        super.recycle();
        this.mMarkerItem = null;
        this.mMarkerImageView.setImageDrawable((Drawable) null);
        this.mMarkerImageView.setImageMatrix((Matrix) null);
        this.mMarkerView.setVisibility(4);
        if (!isEnabled()) {
            setEnable(true);
        }
    }

    public void saveCache(String str) {
        GalleryPreference.getInstanceCache().saveState("CatLocationHeader", str);
    }

    public void setEnable(boolean z) {
        float f;
        ImageView imageView = this.mImageView;
        float f5 = 0.5f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        ViewUtils.setAlpha(imageView, f);
        View view = this.mMarkerView;
        if (z) {
            f5 = 1.0f;
        }
        ViewUtils.setAlpha(view, f5);
        this.itemView.setEnabled(z);
    }

    public void updateHeadExpireTime() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            mediaItem.setTag("HeaderExpireTime", Long.valueOf(System.currentTimeMillis() + 1800000));
        }
    }
}
