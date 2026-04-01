package com.samsung.android.gallery.module.quicksearch;

import A.a;
import B8.d;
import L5.b;
import N3.c;
import O3.o;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickSearchManager {
    private boolean mDateFilterViewEnabled;
    private boolean mDateFiltered;
    private long mEndDate;
    private final List<String> mFilteredLocationCategories = new ArrayList();
    private final List<Long> mFilteredPeopleUnifiedIds = new ArrayList();
    private boolean mImageFilterViewEnabled;
    private boolean mLocationFilterViewEnabled;
    private boolean mPeopleFilterViewEnabled;
    private boolean mQuickSearchViewEnabled;
    private boolean mQuickSearchViewExpanded;
    private long mStartDate;
    private boolean mVideoFilterViewEnabled;

    public static QuickSearchManager getInstance(Blackboard blackboard) {
        return (QuickSearchManager) blackboard.computeIfAbsent("data://QuickSearchManager", new o(5));
    }

    private List<Long> getMediaIdListBySubCategory(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor locationFileCursor = new LocationApi().getLocationFileCursor(str);
        if (locationFileCursor != null) {
            try {
                if (locationFileCursor.moveToFirst()) {
                    int columnIndex = locationFileCursor.getColumnIndex("__absID");
                    do {
                        arrayList.add(Long.valueOf(locationFileCursor.getLong(columnIndex)));
                    } while (locationFileCursor.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (locationFileCursor != null) {
            locationFileCursor.close();
        }
        return arrayList;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getFilteredMediaIds$1(List list, String str) {
        list.addAll(getMediaIdListBySubCategory(str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ QuickSearchManager lambda$getInstance$0(String str) {
        return new QuickSearchManager();
    }

    public void addFilteredLocationCategory(String str) {
        this.mFilteredLocationCategories.add(str);
    }

    public void addFilteredPeopleUnifiedId(long j2) {
        this.mFilteredPeopleUnifiedIds.add(Long.valueOf(j2));
    }

    public void clearFilteredLocation() {
        this.mFilteredLocationCategories.clear();
    }

    public void clearFilteredPeople() {
        this.mFilteredPeopleUnifiedIds.clear();
    }

    public boolean getDateFilterViewEnabled() {
        return this.mDateFilterViewEnabled;
    }

    public boolean getDateFiltered() {
        return this.mDateFiltered;
    }

    public long getEndDate() {
        return this.mEndDate;
    }

    public String getFilteredMediaIds() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean hasFilteredLocation = hasFilteredLocation();
        boolean hasFilteredPeople = hasFilteredPeople();
        long currentTimeMillis = System.currentTimeMillis();
        if (hasFilteredLocation) {
            new ArrayList(this.mFilteredLocationCategories).forEach(new c(15, this, arrayList));
        }
        if (hasFilteredPeople) {
            new ArrayList(this.mFilteredPeopleUnifiedIds).forEach(new d(arrayList2, 13));
        }
        a.x(new StringBuilder("getFilteredMediaIds +"), currentTimeMillis, "QuickSearchManager");
        if (hasFilteredLocation && hasFilteredPeople) {
            return (String) ((List) arrayList.stream().filter(new O9.a(arrayList2, 0)).collect(Collectors.toList())).stream().distinct().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        }
        if (hasFilteredLocation) {
            return (String) arrayList.stream().distinct().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        }
        if (hasFilteredPeople) {
            return (String) arrayList2.stream().distinct().map(new b(5)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        }
        return null;
    }

    public boolean getImageFilterViewEnabled() {
        return this.mImageFilterViewEnabled;
    }

    public boolean getLocationFilterViewEnabled() {
        return this.mLocationFilterViewEnabled;
    }

    public boolean getPeopleFilterViewEnabled() {
        return this.mPeopleFilterViewEnabled;
    }

    public boolean getQuickSearchViewEnabled() {
        return this.mQuickSearchViewEnabled;
    }

    public boolean getQuickSearchViewExpanded() {
        return this.mQuickSearchViewExpanded;
    }

    public long getStartDate() {
        return this.mStartDate;
    }

    public boolean getVideoFilterViewEnabled() {
        return this.mVideoFilterViewEnabled;
    }

    public boolean hasFilteredLocation() {
        if (this.mFilteredLocationCategories.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasFilteredPeople() {
        if (this.mFilteredPeopleUnifiedIds.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isLocationFiltered(String str) {
        return this.mFilteredLocationCategories.contains(str);
    }

    public boolean isPeopleFiltered(long j2) {
        return this.mFilteredPeopleUnifiedIds.contains(Long.valueOf(j2));
    }

    public void release() {
        this.mQuickSearchViewEnabled = false;
        this.mImageFilterViewEnabled = false;
        this.mVideoFilterViewEnabled = false;
        this.mDateFilterViewEnabled = false;
        this.mQuickSearchViewExpanded = false;
        this.mStartDate = 0;
        this.mEndDate = 0;
        this.mDateFiltered = false;
        this.mLocationFilterViewEnabled = false;
        this.mPeopleFilterViewEnabled = false;
        clearFilteredPeople();
        clearFilteredLocation();
    }

    public void removeFilteredLocationCategory(String str) {
        this.mFilteredLocationCategories.remove(str);
    }

    public void removeFilteredPeopleUnifiedId(long j2) {
        this.mFilteredPeopleUnifiedIds.remove(Long.valueOf(j2));
    }

    public void setDateFilterViewEnabled(boolean z) {
        this.mDateFilterViewEnabled = z;
    }

    public void setDateFiltered(boolean z) {
        this.mDateFiltered = z;
    }

    public void setEndDate(long j2) {
        this.mEndDate = j2;
    }

    public void setImageFilterViewEnabled(boolean z) {
        this.mImageFilterViewEnabled = z;
    }

    public void setLocationFilterViewEnabled(boolean z) {
        this.mLocationFilterViewEnabled = z;
    }

    public void setPeopleFilterViewEnabled(boolean z) {
        this.mPeopleFilterViewEnabled = z;
    }

    public void setQuickSearchViewEnabled(boolean z) {
        this.mQuickSearchViewEnabled = z;
    }

    public void setQuickSearchViewExpanded(boolean z) {
        this.mQuickSearchViewExpanded = z;
    }

    public void setStartDate(long j2) {
        this.mStartDate = j2;
    }

    public void setVideoFilterViewEnabled(boolean z) {
        this.mVideoFilterViewEnabled = z;
    }
}
