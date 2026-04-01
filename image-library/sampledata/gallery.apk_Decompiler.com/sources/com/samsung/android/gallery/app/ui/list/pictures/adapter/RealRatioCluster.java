package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import android.content.Context;
import android.util.Pair;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.RealRatioIndexer;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Lazy;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RealRatioCluster extends DayMonthCluster {
    private final Lazy<RealRatioIndexer> mLazyIndexer = new Lazy<>(new a(this, 0));
    MediaData mMediaData;
    protected RealRatioIndexer mRealRatioIndexer;
    SpecProvider mSpecProvider;

    public RealRatioCluster(ClusterIndexer clusterIndexer, MediaData mediaData, SpecProvider specProvider) {
        super(clusterIndexer, mediaData.getCount());
        this.mMediaData = mediaData;
        this.mSpecProvider = specProvider;
        this.mRealRatioIndexer = mediaData.getRealRatioIndexer();
    }

    private int getDividerRow(int i2) {
        ArrayList<Integer> dividerRow = this.mLazyIndexer.get().getDividerRow();
        int scrollIndex = getScrollIndex(i2);
        if (dividerRow.size() > scrollIndex) {
            return dividerRow.get(scrollIndex).intValue();
        }
        return 0;
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
        if (isDivider(i2) || this.mRealRatioIndexer == null) {
            return getSpanCount();
        }
        return this.mLazyIndexer.get().getItemWidth(i2);
    }

    public ArrayList<Integer> getDividerRowList(int i2) {
        if (this.mRealRatioIndexer == null) {
            return super.getDividerRowList(i2);
        }
        return this.mLazyIndexer.get().getDividerRow();
    }

    public ArrayList<Pair<String, Integer>> getDividerScroll(int i2, int i7, int i8, int i10, int i11) {
        int i12;
        long dateTaken;
        int yearInt;
        if (this.mRealRatioIndexer == null) {
            return null;
        }
        int[] clusterHeight = this.mLazyIndexer.get().getClusterHeight();
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        Iterator<Integer> it = this.mDividerIndexList.iterator();
        int i13 = -1;
        int i14 = -1;
        int i15 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            int intValue = next.intValue();
            if (intValue != i13) {
                if (i15 < clusterHeight.length) {
                    int i16 = (i15 * i8) + clusterHeight[i15] + i11;
                    if (i15 == 0 || i10 == 0) {
                        i12 = 0;
                    } else {
                        i12 = i10 - i8;
                    }
                    int i17 = i16 + i12;
                    if (!(this.mDividerItemMapReadOnly.get(next) == null || (yearInt = TimeUtil.getYearInt(dateTaken)) == i14)) {
                        arrayList.add(new Pair(TimeUtil.getYearString((dateTaken = this.mDividerItemMapReadOnly.get(next).getDateTaken())), Integer.valueOf(i17)));
                        i14 = yearInt;
                    }
                }
                i15++;
                i13 = intValue;
            }
        }
        return arrayList;
    }

    public int getItemHeight(int i2, int i7) {
        if (this.mRealRatioIndexer == null) {
            return 0;
        }
        return this.mLazyIndexer.get().getItemHeight(i2);
    }

    public int getMaxScroll(int i2, float f, int i7, int i8) {
        if (this.mRealRatioIndexer == null) {
            return super.getMaxScroll(i2, f, i7, i8);
        }
        int size = (this.mDividerIndexList.size() * i7) + this.mLazyIndexer.get().getMaxScroll();
        if (i8 != 0) {
            return (i8 - i7) + size;
        }
        return size;
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
        if (isDivider(i2)) {
            return new SpanInfo(getDividerRow(i2), 0, getItemViewType(i2));
        }
        return new SpanInfo(this.mLazyIndexer.get().getRow(i2), this.mLazyIndexer.get().getColumn(i2), getItemViewType(i2));
    }

    public int getStartSpanInternal(int i2, int i7) {
        if (isDivider(i2) || this.mRealRatioIndexer == null) {
            return 0;
        }
        return this.mLazyIndexer.get().getStartSpan(i2);
    }

    public void recalculatePosition(int i2) {
        RealRatioIndexer realRatioIndexer = this.mMediaData.getRealRatioIndexer();
        this.mRealRatioIndexer = realRatioIndexer;
        if (realRatioIndexer.getMaxWidth() != i2) {
            this.mRealRatioIndexer.init(this.mSpecProvider, i2);
        }
    }
}
