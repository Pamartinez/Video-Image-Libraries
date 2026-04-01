package com.samsung.android.gallery.app.ui.list.albums.pictures.filter;

import A.a;
import A4.B;
import A4.C0368c;
import A4.C0369d;
import L5.b;
import M4.d;
import M9.o;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.screenshotfilter.ScreenShotFilterManager;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotFilterDelegate {
    private ScreenShotFilterListViewAdapter mAdapter;
    private final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new o(2, ScreenShotFilterDelegate.this));
        }
    };
    private MediaData mFilterData;
    ScreenShotFilterManager mFilterManager;
    private ScreenShotFilterView mFilterView;
    private final IPicturesView mView;

    public ScreenShotFilterDelegate(IPicturesView iPicturesView) {
        this.mView = iPicturesView;
        this.mFilterView = new ScreenShotFilterView();
    }

    private int getFilteredSceneDataPosition() {
        String filteredScene = this.mFilterManager.getFilteredScene();
        if (ScreenShotFilterType.All.key().equals(filteredScene)) {
            return -1;
        }
        ArrayList<MediaItem> allData = this.mFilterData.getAllData();
        for (int i2 = 0; i2 < allData.size(); i2++) {
            if (TextUtils.equals(allData.get(i2).getSubCategory(), filteredScene)) {
                return i2;
            }
        }
        return -1;
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_SPLIT_VIEW_SCREEN_SHOTS_ALBUM.toString();
    }

    private void initScrollPosition() {
        int filteredSceneDataPosition;
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && (filteredSceneDataPosition = getFilteredSceneDataPosition()) > 0) {
            ThreadUtil.postOnUiThreadDelayed(new C0368c(this, filteredSceneDataPosition, 13), 200);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initScrollPosition$2(int i2) {
        Optional.ofNullable(this.mFilterView).ifPresent(new C0369d(i2, 9));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateVisibility$0(ScreenShotFilterView screenShotFilterView) {
        if (screenShotFilterView.isViewReady()) {
            boolean hasFilterData = hasFilterData();
            if (screenShotFilterView.getVisibility() != hasFilterData) {
                a.v("updateFilterViewVisibility {hasFilterData=", "}", "ScreenShotFilterDelegate", hasFilterData);
                screenShotFilterView.setVisibility(hasFilterData);
                return;
            }
            return;
        }
        Log.w("ScreenShotFilterDelegate", "view is not ready, so skip update visibility");
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        Log.d("ScreenShotFilterDelegate", "onDataChangedOnUi {count=" + this.mFilterData.getCount() + "}");
        updateVisibility();
        updateFilterView();
        Optional.ofNullable(this.mAdapter).ifPresent(new d(17));
    }

    /* access modifiers changed from: private */
    public void onListItemClicked(String str) {
        Optional.ofNullable(this.mFilterManager).ifPresent(new B(str, 11));
        this.mView.getBlackboard().postEvent(EventMessage.obtain(2013));
        this.mView.postAnalyticsLog(getScreenId(), ScreenShotFilterType.getEventId(str));
    }

    private void setListViewAdapter() {
        ScreenShotFilterListViewAdapter screenShotFilterListViewAdapter = new ScreenShotFilterListViewAdapter(this.mView.getBlackboard());
        this.mAdapter = screenShotFilterListViewAdapter;
        screenShotFilterListViewAdapter.setOnItemClickListener(new N4.a(this, 1));
        this.mAdapter.setMediaData(this.mFilterData);
        this.mFilterView.setAdapter(this.mAdapter);
        initScrollPosition();
    }

    private void setListViewLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mView.getContext());
        linearLayoutManager.setOrientation(0);
        this.mFilterView.setLayoutManager(linearLayoutManager);
    }

    private void updateFilterView() {
        this.mView.updateFilterView();
    }

    private void updateVisibility() {
        Optional.ofNullable(this.mFilterView).ifPresent(new N4.a(this, 0));
    }

    public void bindView(View view) {
        if (this.mFilterView != null) {
            this.mFilterManager = ScreenShotFilterManager.getInstance(this.mView.getBlackboard());
            this.mFilterView.bindView(view);
            setListViewAdapter();
            setListViewLayoutManager();
            updateVisibility();
        }
    }

    public void closeScreenShotFilterData() {
        MediaData mediaData = this.mFilterData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mFilterData.close();
            this.mFilterData = null;
        }
    }

    public boolean hasFilterData() {
        MediaData mediaData = this.mFilterData;
        if (mediaData == null || mediaData.getCount() <= 1) {
            return false;
        }
        return true;
    }

    public boolean isListViewScrolling() {
        return ((Boolean) Optional.ofNullable(this.mFilterView).map(new b(19)).orElse(Boolean.FALSE)).booleanValue();
    }

    public void openScreenShotFilterData() {
        if (this.mFilterData == null) {
            MediaData open = MediaDataFactory.getInstance(this.mView.getBlackboard()).open("location://albums/fileList/ScreenShotFilter");
            this.mFilterData = open;
            open.register(this.mDataChangeListener);
        }
    }

    public void resetFilter() {
        Optional.ofNullable(this.mFilterManager).ifPresent(new d(18));
        this.mView.getBlackboard().postEvent(EventMessage.obtain(2013));
    }

    public void unbindView() {
        ScreenShotFilterView screenShotFilterView = this.mFilterView;
        if (screenShotFilterView != null) {
            screenShotFilterView.unbindView();
            this.mFilterView = null;
        }
        ScreenShotFilterListViewAdapter screenShotFilterListViewAdapter = this.mAdapter;
        if (screenShotFilterListViewAdapter != null) {
            screenShotFilterListViewAdapter.release();
            this.mAdapter = null;
        }
        ScreenShotFilterManager screenShotFilterManager = this.mFilterManager;
        if (screenShotFilterManager != null) {
            screenShotFilterManager.release();
            this.mFilterManager = null;
        }
    }

    public void updateScreenShotFilter() {
        Optional.ofNullable(this.mAdapter).ifPresent(new d(17));
    }
}
