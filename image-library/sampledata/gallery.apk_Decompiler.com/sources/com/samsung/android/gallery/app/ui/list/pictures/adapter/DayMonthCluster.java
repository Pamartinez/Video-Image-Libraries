package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import A.a;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DayMonthCluster extends DividerCluster {
    private LinkedHashMap<String, Integer> mAllDatesCount;
    private HashMap<String, String> mAllDatesIds;
    private long[] mDateTaken;

    public DayMonthCluster(ClusterIndexer clusterIndexer, int i2) {
        super(i2);
        initIndexInfo(clusterIndexer);
    }

    private int getIndex(String str) {
        int i2 = 0;
        while (true) {
            ScrollText[] scrollTextArr = this.mScrollIndexTag;
            if (i2 >= scrollTextArr.length) {
                a.u("year index fail ", str, this.TAG);
                return 0;
            } else if (str.equals(TimeUtil.toLocalDate(scrollTextArr[i2].getDateTaken(), "YM"))) {
                return i2;
            } else {
                i2++;
            }
        }
    }

    private void initIndexInfo(ClusterIndexer clusterIndexer) {
        this.mScrollIndex = clusterIndexer.getScrollIndex();
        this.mScrollIndexTag = clusterIndexer.getScrollIndexTag();
        this.mDateTaken = clusterIndexer.getDateTaken();
        this.mDividerIndexList = clusterIndexer.getTimelineIdxList();
        this.mDividerItemMapReadOnly = clusterIndexer.getTimelineItemMap();
        this.mCount = clusterIndexer.getCount();
    }

    public void clear() {
        super.clear();
        this.mScrollIndexTag = null;
    }

    public int[] findDataPositionRange(MediaItem mediaItem) {
        int i2;
        try {
            int index = getIndex(TimeUtil.toLocalDate(mediaItem.getDateTaken(), "YM"));
            int dataPosition = getDataPosition(this.mScrollIndex[index] + 1);
            int i7 = index + 1;
            int[] iArr = this.mScrollIndex;
            if (i7 >= iArr.length) {
                i2 = this.mTotalItemCount;
            } else {
                i2 = getDataPosition(iArr[i7] - 1);
            }
            return new int[]{dataPosition, i2};
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "findViewPosition failed", (Throwable) e);
            return new int[]{0, 1};
        }
    }

    public int[] getClusterItemRange(MediaItem mediaItem) {
        String str;
        if (this.mDividerItemMapReadOnly.values().stream().anyMatch(new com.samsung.android.gallery.module.dynamicview.a(23))) {
            str = TimeUtil.toLocalDate(mediaItem.getDateTaken(), "YM");
        } else {
            str = mediaItem.getDate();
        }
        int dividerIndex = getDividerIndex(this.mDividerItemMapReadOnly, str);
        int i2 = -1;
        if (dividerIndex == -1) {
            return null;
        }
        int indexOf = this.mDividerIndexList.indexOf(Integer.valueOf(dividerIndex)) + 1;
        if (indexOf < this.mDividerIndexList.size()) {
            i2 = this.mDividerIndexList.get(indexOf).intValue();
        }
        return new int[]{dividerIndex + 1, i2, indexOf};
    }

    public Integer getDateCount(int i2) {
        ClusterItem clusterItem = this.mDividerItemMapReadOnly.get(Integer.valueOf(i2));
        if (this.mAllDatesCount == null || clusterItem == null || TextUtils.isEmpty(clusterItem.getDateRaw())) {
            return null;
        }
        return this.mAllDatesCount.get(clusterItem.getDateRaw());
    }

    public String getDateIds(int i2) {
        ClusterItem clusterItem = this.mDividerItemMapReadOnly.get(Integer.valueOf(i2));
        if (this.mAllDatesIds == null || clusterItem == null || TextUtils.isEmpty(clusterItem.getDateRaw())) {
            return null;
        }
        return this.mAllDatesIds.get(clusterItem.getDateRaw());
    }

    public int getDividerIndex(MediaItem mediaItem) {
        return getDividerIndex(this.mDividerItemMapReadOnly, mediaItem.getDate());
    }

    public int getDividerIndexInternal(long j2) {
        int binarySearch = Arrays.binarySearch(this.mDateTaken, j2);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        int length = (this.mDateTaken.length - 1) - binarySearch;
        if (length < 0) {
            return length;
        }
        return this.mDividerIndexList.get(length).intValue();
    }

    public ArrayList<Pair<String, Integer>> getDividerScroll(int i2, int i7, int i8, int i10, int i11) {
        int i12;
        long dateTaken;
        int yearInt;
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        Iterator<Integer> it = this.mDividerIndexList.iterator();
        int i13 = -1;
        int i14 = -1;
        int i15 = 0;
        int i16 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            int intValue = next.intValue();
            if (intValue != i13) {
                int i17 = (intValue - i13) - 1;
                if (i17 >= 0) {
                    i15 = getRowCount(i2, i17) + i15;
                }
                int i18 = (i16 * i8) + (i15 * i7) + i11;
                if (i16 == 0 || i10 == 0) {
                    i12 = 0;
                } else {
                    i12 = i10 - i8;
                }
                int i19 = i18 + i12;
                if (!(this.mDividerItemMapReadOnly.get(next) == null || (yearInt = TimeUtil.getYearInt(dateTaken)) == i14)) {
                    arrayList.add(new Pair(TimeUtil.getYearString((dateTaken = this.mDividerItemMapReadOnly.get(next).getDateTaken())), Integer.valueOf(i19)));
                    i14 = yearInt;
                }
                i16++;
                i13 = intValue;
            }
        }
        return arrayList;
    }

    public int getStartSpanInternal(int i2, int i7) {
        int binarySearch = Arrays.binarySearch(this.mScrollIndex, i2);
        if (binarySearch > -2) {
            return 0;
        }
        return ((i2 - this.mScrollIndex[(-binarySearch) - 2]) - 1) % i7;
    }

    public void setAllDates(HashMap<String, String> hashMap, LinkedHashMap<String, Integer> linkedHashMap) {
        this.mAllDatesIds = hashMap;
        this.mAllDatesCount = linkedHashMap;
    }

    public boolean supportExpand(int i2) {
        ClusterItem clusterItem = this.mDividerItemMapReadOnly.get(Integer.valueOf(i2));
        if (!(this.mAllDatesCount == null || clusterItem == null || TextUtils.isEmpty(clusterItem.getDateRaw()))) {
            Pair<String, String> dateRawRange = clusterItem.getDateRawRange();
            String str = (String) dateRawRange.first;
            String str2 = (String) dateRawRange.second;
            Integer num = this.mAllDatesCount.get(str2);
            if (!TextUtils.equals(str, str2)) {
                boolean z = false;
                for (Map.Entry next : this.mAllDatesCount.entrySet()) {
                    if (z) {
                        num = Integer.valueOf(((Integer) next.getValue()).intValue() + num.intValue());
                        if (TextUtils.equals((CharSequence) next.getKey(), str)) {
                            break;
                        }
                    }
                    if (TextUtils.equals((CharSequence) next.getKey(), str2)) {
                        z = true;
                    }
                }
            }
            if (num == null || num.intValue() <= clusterItem.getCount()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
