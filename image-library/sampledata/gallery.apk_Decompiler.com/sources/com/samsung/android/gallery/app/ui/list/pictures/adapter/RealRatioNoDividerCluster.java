package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import android.content.Context;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RealRatioNoDividerCluster extends NoDividerCluster {
    protected final String TAG = getClass().getSimpleName();
    private final Lazy<RealRatioIndexer> mLazyIndexer = new Lazy<>(new a(this, 1));
    MediaData mMediaData;
    private RealRatioIndexer mRealRatioIndexer;
    SpecProvider mSpecProvider;

    public RealRatioNoDividerCluster(MediaData mediaData, SpecProvider specProvider) {
        super(mediaData);
        this.mMediaData = mediaData;
        this.mRealRatioIndexer = mediaData.getRealRatioIndexer();
        this.mSpecProvider = specProvider;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ RealRatioIndexer lambda$new$0() {
        if (this.mRealRatioIndexer != null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mRealRatioIndexer.init(this.mSpecProvider);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            Context appContext = AppResources.getAppContext();
            Utils.showDebugToast(appContext, "init realRatio +" + currentTimeMillis2);
            String str = this.TAG;
            Log.d(str, "init RealRatio +" + currentTimeMillis2);
        }
        return this.mRealRatioIndexer;
    }

    public int getColumnSpan(int i2, int i7) {
        if (this.mRealRatioIndexer == null) {
            return getSpanCount();
        }
        return this.mLazyIndexer.get().getItemWidth(i2);
    }

    public int getItemHeight(int i2, int i7) {
        if (this.mRealRatioIndexer == null) {
            return 0;
        }
        return this.mLazyIndexer.get().getItemHeight(i2);
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        if (this.mRealRatioIndexer != null) {
            return this.mLazyIndexer.get().getMaxScroll();
        }
        return super.getMaxScroll(i2, f, i7, i8);
    }

    public int getSpanCount() {
        if (this.mRealRatioIndexer == null) {
            return 1;
        }
        return this.mLazyIndexer.get().getMaxWidth();
    }

    public SpanInfo getSpanInfo(int i2, int i7) {
        if (this.mRealRatioIndexer == null) {
            return super.getSpanInfo(i2, i7);
        }
        return new SpanInfo(this.mLazyIndexer.get().getRow(i2), this.mLazyIndexer.get().getColumn(i2), getItemViewType(i2));
    }

    public int getStartSpan(int i2, int i7) {
        if (isDivider(i2) || this.mRealRatioIndexer == null) {
            return 0;
        }
        return this.mLazyIndexer.get().getStartSpan(i2);
    }

    public void recalculatePosition(int i2) {
        RealRatioIndexer realRatioIndexer = this.mMediaData.getRealRatioIndexer();
        this.mRealRatioIndexer = realRatioIndexer;
        if (realRatioIndexer != null) {
            realRatioIndexer.init(this.mSpecProvider, i2);
        }
    }
}
