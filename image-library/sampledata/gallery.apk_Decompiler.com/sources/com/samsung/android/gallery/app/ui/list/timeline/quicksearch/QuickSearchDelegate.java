package com.samsung.android.gallery.app.ui.list.timeline.quicksearch;

import N2.j;
import S6.c;
import S6.d;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.controller.internals.QuickSearchRangeDatePickerCmd;
import com.samsung.android.gallery.app.ui.list.timeline.ITimelineView2;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.quicksearch.QuickSearchManager;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickSearchDelegate {
    private MediaData mLocationFilterData;
    private QuickSearchLocationFilterListViewAdapter mLocationFilterListViewAdapter;
    private MediaData mPeopleFilterData;
    private QuickSearchPeopleFilterListViewAdapter mPeopleFilterListViewAdapter;
    QuickSearchManager mQuickSearchManager;
    private final QuickSearchView mQuickSearchView = new QuickSearchView();
    private final ITimelineView2 mView;

    public QuickSearchDelegate(ITimelineView2 iTimelineView2) {
        this.mView = iTimelineView2;
    }

    /* access modifiers changed from: private */
    public void onDateFilterButtonClicked(View view) {
        boolean dateFilterViewEnabled = this.mQuickSearchManager.getDateFilterViewEnabled();
        boolean z = !dateFilterViewEnabled;
        C0212a.x("onDateFilterButtonClicked,", "QuickSearchDelegate", z);
        this.mQuickSearchManager.setDateFilterViewEnabled(z);
        this.mQuickSearchView.updateDateFilterCardVisibility(z);
        if (dateFilterViewEnabled && this.mQuickSearchManager.getDateFiltered()) {
            this.mQuickSearchManager.setDateFiltered(false);
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1130));
        }
        rotateExpandButtonIfNeeded();
    }

    /* access modifiers changed from: private */
    public void onDateFilterCardClicked(View view) {
        Log.d("QuickSearchDelegate", "onDateFilterCardClicked");
        new QuickSearchRangeDatePickerCmd().execute(this.mView.getPresenter(), new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onExpandButtonClicked(View view) {
        boolean z = !this.mQuickSearchManager.getQuickSearchViewExpanded();
        C0212a.x("onExpandButtonClicked, ", "QuickSearchDelegate", z);
        this.mQuickSearchView.rotateExpandIcon(z);
        this.mQuickSearchManager.setQuickSearchViewExpanded(z);
        this.mQuickSearchView.updateDateFilterCardVisibility(z);
        this.mQuickSearchManager.setDateFilterViewEnabled(z);
        this.mQuickSearchView.updateLocationFilterCardVisibility(z);
        this.mQuickSearchManager.setLocationFilterViewEnabled(z);
        this.mQuickSearchView.updatePeopleFilterCardVisibility(z);
        this.mQuickSearchManager.setPeopleFilterViewEnabled(z);
    }

    /* access modifiers changed from: private */
    public void onImageFilterButtonClicked(View view) {
        boolean imageFilterViewEnabled = this.mQuickSearchManager.getImageFilterViewEnabled();
        boolean z = !imageFilterViewEnabled;
        C0212a.x("onImageFilterButtonClicked,", "QuickSearchDelegate", z);
        this.mQuickSearchManager.setImageFilterViewEnabled(z);
        this.mQuickSearchView.updateImageFilterButtonTextColor(z);
        if (!imageFilterViewEnabled && this.mQuickSearchManager.getVideoFilterViewEnabled()) {
            this.mQuickSearchManager.setVideoFilterViewEnabled(false);
            this.mQuickSearchView.updateVideoFilterButtonTextColor(false);
        }
        BlackboardUtils.requestMediaDataReQuery(this.mView.getBlackboard());
    }

    /* access modifiers changed from: private */
    public void onLocationFilterButtonClicked(View view) {
        boolean locationFilterViewEnabled = this.mQuickSearchManager.getLocationFilterViewEnabled();
        boolean z = !locationFilterViewEnabled;
        C0212a.x("onLocationFilterButtonClicked,", "QuickSearchDelegate", z);
        MediaData mediaData = this.mLocationFilterData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            Log.d("QuickSearchDelegate", "onLocationFilterButtonClicked, data is not exist");
            return;
        }
        this.mQuickSearchManager.setLocationFilterViewEnabled(z);
        this.mQuickSearchView.updateLocationFilterCardVisibility(z);
        if (locationFilterViewEnabled && this.mQuickSearchManager.hasFilteredLocation()) {
            this.mQuickSearchView.updateLocationFilterButtonTextColor(false);
            this.mQuickSearchManager.clearFilteredLocation();
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1131));
            Optional.ofNullable(this.mLocationFilterListViewAdapter).ifPresent(new a(0));
        }
        rotateExpandButtonIfNeeded();
    }

    /* access modifiers changed from: private */
    public void onLocationFilterListItemClicked(String str, Boolean bool) {
        Log.d("QuickSearchDelegate", "onLocationFilterListItemClicked, s=" + str + ", c=" + bool);
        if (bool.booleanValue()) {
            this.mQuickSearchManager.addFilteredLocationCategory(str);
        } else {
            this.mQuickSearchManager.removeFilteredLocationCategory(str);
        }
        this.mQuickSearchView.updateLocationFilterButtonTextColor(this.mQuickSearchManager.hasFilteredLocation());
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1131));
    }

    /* access modifiers changed from: private */
    public void onPeopleFilterButtonClicked(View view) {
        boolean peopleFilterViewEnabled = this.mQuickSearchManager.getPeopleFilterViewEnabled();
        boolean z = !peopleFilterViewEnabled;
        C0212a.x("onPeopleFilterButtonClicked,", "QuickSearchDelegate", z);
        MediaData mediaData = this.mPeopleFilterData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            Log.d("QuickSearchDelegate", "onPeopleFilterButtonClicked, data is not exist");
            return;
        }
        this.mQuickSearchManager.setPeopleFilterViewEnabled(z);
        this.mQuickSearchView.updatePeopleFilterCardVisibility(z);
        if (peopleFilterViewEnabled && this.mQuickSearchManager.hasFilteredPeople()) {
            this.mQuickSearchView.updatePeopleFilterButtonTextColor(false);
            this.mQuickSearchManager.clearFilteredPeople();
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1132));
            Optional.ofNullable(this.mPeopleFilterListViewAdapter).ifPresent(new a(1));
        }
        rotateExpandButtonIfNeeded();
    }

    /* access modifiers changed from: private */
    public void onPeopleFilterListItemClicked(String str, Boolean bool) {
        Log.d("QuickSearchDelegate", "onPeopleFilterListItemClicked, s=" + str + ", c=" + bool);
        long unifiedIdentityId = IdentityCreatureUtil.getUnifiedIdentityId(str);
        if (bool.booleanValue()) {
            this.mQuickSearchManager.addFilteredPeopleUnifiedId(unifiedIdentityId);
        } else {
            this.mQuickSearchManager.removeFilteredPeopleUnifiedId(unifiedIdentityId);
        }
        this.mQuickSearchView.updatePeopleFilterButtonTextColor(this.mQuickSearchManager.hasFilteredPeople());
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1132));
    }

    /* access modifiers changed from: private */
    public void onVideoFilterButtonClicked(View view) {
        boolean videoFilterViewEnabled = this.mQuickSearchManager.getVideoFilterViewEnabled();
        boolean z = !videoFilterViewEnabled;
        C0212a.x("onVideoFilterButtonClicked,", "QuickSearchDelegate", z);
        this.mQuickSearchManager.setVideoFilterViewEnabled(z);
        this.mQuickSearchView.updateVideoFilterButtonTextColor(z);
        if (!videoFilterViewEnabled && this.mQuickSearchManager.getImageFilterViewEnabled()) {
            this.mQuickSearchManager.setImageFilterViewEnabled(false);
            this.mQuickSearchView.updateImageFilterButtonTextColor(false);
        }
        BlackboardUtils.requestMediaDataReQuery(this.mView.getBlackboard());
    }

    private void rotateExpandButtonIfNeeded() {
        boolean dateFilterViewEnabled = this.mQuickSearchManager.getDateFilterViewEnabled();
        boolean locationFilterViewEnabled = this.mQuickSearchManager.getLocationFilterViewEnabled();
        boolean peopleFilterViewEnabled = this.mQuickSearchManager.getPeopleFilterViewEnabled();
        if (dateFilterViewEnabled && locationFilterViewEnabled && peopleFilterViewEnabled && !this.mQuickSearchManager.getQuickSearchViewExpanded()) {
            this.mQuickSearchView.rotateExpandIcon(true);
            this.mQuickSearchManager.setQuickSearchViewExpanded(true);
        } else if (!dateFilterViewEnabled && !locationFilterViewEnabled && !peopleFilterViewEnabled && this.mQuickSearchManager.getQuickSearchViewExpanded()) {
            this.mQuickSearchView.rotateExpandIcon(false);
            this.mQuickSearchManager.setQuickSearchViewExpanded(false);
        }
    }

    private void setLocationFilterListViewAdapter() {
        QuickSearchLocationFilterListViewAdapter quickSearchLocationFilterListViewAdapter = new QuickSearchLocationFilterListViewAdapter(this.mView.getBlackboard());
        this.mLocationFilterListViewAdapter = quickSearchLocationFilterListViewAdapter;
        quickSearchLocationFilterListViewAdapter.setMediaData(this.mLocationFilterData);
        this.mLocationFilterListViewAdapter.setOnItemClickListener(new c(this, 0));
        this.mQuickSearchView.setLocationFilterListViewAdapter(this.mLocationFilterListViewAdapter);
    }

    private void setLocationFilterListViewLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mView.getContext());
        linearLayoutManager.setOrientation(0);
        this.mQuickSearchView.setLocationFilterListViewLayoutManager(linearLayoutManager);
    }

    private void setPeopleFilterListViewAdapter() {
        QuickSearchPeopleFilterListViewAdapter quickSearchPeopleFilterListViewAdapter = new QuickSearchPeopleFilterListViewAdapter(this.mView.getBlackboard());
        this.mPeopleFilterListViewAdapter = quickSearchPeopleFilterListViewAdapter;
        quickSearchPeopleFilterListViewAdapter.setMediaData(this.mPeopleFilterData);
        this.mPeopleFilterListViewAdapter.setOnItemClickListener(new c(this, 1));
        this.mQuickSearchView.setPeopleFilterListViewAdapter(this.mPeopleFilterListViewAdapter);
    }

    private void setPeopleFilterListViewLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mView.getContext());
        linearLayoutManager.setOrientation(0);
        this.mQuickSearchView.setPeopleFilterListViewLayoutManager(linearLayoutManager);
    }

    public void bindView(View view) {
        Log.d("QuickSearchDelegate", "bindView");
        this.mQuickSearchManager = QuickSearchManager.getInstance(this.mView.getBlackboard());
        this.mQuickSearchView.bindView(view);
        this.mQuickSearchView.setOnImageFilterButtonClickListener(new d(this, 0));
        this.mQuickSearchView.setOnVideoFilterButtonClickListener(new d(this, 1));
        this.mQuickSearchView.setOnDateFilterButtonClickListener(new d(this, 2));
        this.mQuickSearchView.setOnLocationFilterButtonClickListener(new d(this, 3));
        this.mQuickSearchView.setOnPeopleFilterButtonClickListener(new d(this, 4));
        this.mQuickSearchView.setOnExpandButtonClickListener(new d(this, 5));
        this.mQuickSearchView.setOnDateFilterCardClickListener(new d(this, 6));
        setLocationFilterListViewAdapter();
        setLocationFilterListViewLayoutManager();
        setPeopleFilterListViewAdapter();
        setPeopleFilterListViewLayoutManager();
    }

    public void closeQuickSearchData() {
        Log.d("QuickSearchDelegate", "closeQuickSearchData");
        MediaData mediaData = this.mLocationFilterData;
        if (mediaData != null) {
            mediaData.close();
            this.mLocationFilterData = null;
        }
        MediaData mediaData2 = this.mPeopleFilterData;
        if (mediaData2 != null) {
            mediaData2.close();
            this.mPeopleFilterData = null;
        }
    }

    public void openQuickSearchData() {
        Log.d("QuickSearchDelegate", "openQuickSearchData");
        this.mLocationFilterData = MediaDataFactory.getInstance(this.mView.getBlackboard()).open("location://search/fileList/Category/Location");
        this.mPeopleFilterData = MediaDataFactory.getInstance(this.mView.getBlackboard()).open("location://search/fileList/Category/People");
    }

    public void unbindView() {
        Log.d("QuickSearchDelegate", "unbindView");
        this.mQuickSearchView.unbindView();
        QuickSearchLocationFilterListViewAdapter quickSearchLocationFilterListViewAdapter = this.mLocationFilterListViewAdapter;
        if (quickSearchLocationFilterListViewAdapter != null) {
            quickSearchLocationFilterListViewAdapter.release();
            this.mLocationFilterListViewAdapter = null;
        }
        QuickSearchPeopleFilterListViewAdapter quickSearchPeopleFilterListViewAdapter = this.mPeopleFilterListViewAdapter;
        if (quickSearchPeopleFilterListViewAdapter != null) {
            quickSearchPeopleFilterListViewAdapter.release();
            this.mPeopleFilterListViewAdapter = null;
        }
        this.mQuickSearchManager.release();
    }

    public void updateContainerVisibility() {
        boolean quickSearchViewEnabled = this.mQuickSearchManager.getQuickSearchViewEnabled();
        boolean z = !quickSearchViewEnabled;
        C0212a.x("updateContainerVisibility, ", "QuickSearchDelegate", z);
        this.mQuickSearchView.updateContainerVisibility(z);
        this.mQuickSearchManager.setQuickSearchViewEnabled(z);
        if (quickSearchViewEnabled) {
            this.mQuickSearchManager.setImageFilterViewEnabled(false);
            this.mQuickSearchView.updateImageFilterButtonTextColor(false);
            this.mQuickSearchManager.setVideoFilterViewEnabled(false);
            this.mQuickSearchView.updateVideoFilterButtonTextColor(false);
            this.mQuickSearchManager.setDateFilterViewEnabled(false);
            this.mQuickSearchManager.setDateFiltered(false);
            this.mQuickSearchView.updateDateFilterCardVisibility(false);
            updateDateFilterCard();
            this.mQuickSearchManager.setLocationFilterViewEnabled(false);
            this.mQuickSearchManager.clearFilteredLocation();
            this.mQuickSearchView.updateLocationFilterButtonTextColor(false);
            this.mLocationFilterListViewAdapter.notifyDataSetChanged();
            Optional.ofNullable(this.mLocationFilterListViewAdapter).ifPresent(new a(0));
            this.mQuickSearchView.updateLocationFilterCardVisibility(false);
            this.mQuickSearchManager.setPeopleFilterViewEnabled(false);
            this.mQuickSearchManager.clearFilteredPeople();
            this.mQuickSearchView.updatePeopleFilterButtonTextColor(false);
            Optional.ofNullable(this.mPeopleFilterListViewAdapter).ifPresent(new a(1));
            this.mQuickSearchView.updatePeopleFilterCardVisibility(false);
            this.mQuickSearchManager.setQuickSearchViewExpanded(false);
            this.mQuickSearchView.refreshExpandIconRotation();
            BlackboardUtils.requestMediaDataReQuery(this.mView.getBlackboard());
        }
    }

    public void updateDateFilterCard() {
        String str;
        boolean dateFiltered = this.mQuickSearchManager.getDateFiltered();
        String str2 = null;
        if (dateFiltered) {
            str = TimeUtil.toLocalDate(this.mQuickSearchManager.getStartDate(), "YYMD");
        } else {
            str = null;
        }
        if (dateFiltered) {
            str2 = TimeUtil.toLocalDate(this.mQuickSearchManager.getEndDate(), "YYMD");
        }
        StringBuilder sb2 = new StringBuilder("updateDateFilterCard, f=");
        sb2.append(dateFiltered);
        sb2.append(", s=");
        sb2.append(str);
        sb2.append(", e=");
        j.y(sb2, str2, "QuickSearchDelegate");
        this.mQuickSearchView.updateDateFilterText(str, str2);
        this.mQuickSearchView.updateDateFilterTextVisibility(dateFiltered);
        this.mQuickSearchView.updateDateFilterButtonTextColor(dateFiltered);
    }
}
