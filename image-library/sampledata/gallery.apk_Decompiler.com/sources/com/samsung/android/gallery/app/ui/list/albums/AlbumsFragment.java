package com.samsung.android.gallery.app.ui.list.albums;

import A4.C0366a;
import A4.C0372g;
import A4.C0387w;
import A4.L;
import Fa.C0571z;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.IAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragFragment;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.Analytics;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsFragment<V extends IAlbumsView, P extends AlbumsPresenter> extends AlbumsDragFragment<V, P> implements IAlbumsView {
    ViewStub mSmartAlbumLayoutStub;

    public AlbumsFragment() {
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getAllItems$3(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFolderCreated$1() {
        if (!isDestroyed()) {
            ((AlbumsPresenter) this.mPresenter).forceReloadData();
        }
    }

    private void setHeaderAndFooterEnabled(boolean z) {
        Optional.ofNullable(getAdapter()).ifPresent(new L(z, 1));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mSmartAlbumLayoutStub = (ViewStub) view.findViewById(R.id.smart_album_layout_stub);
    }

    public SmartAlbumHolder createSmartAlbumHolder() {
        return new SmartAlbumHolder(this.mSmartAlbumLayoutStub, getBlackboard(), getLocationKey(), getAnalyticsScreenId(getScreenId()));
    }

    public void exitSelectionMode(boolean z) {
        super.exitSelectionMode(z);
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder != null && !smartAlbumHolder.exitSelectionMode()) {
            Optional.ofNullable(this.mAppBarLayout).ifPresent(new C0366a(21));
        }
    }

    public MediaItem[] getAllItems() {
        return (MediaItem[]) Arrays.stream(super.getAllItems()).filter(new C0571z(4)).toArray(new C0387w(3));
    }

    public int getLayoutId() {
        return R.layout.fragment_albums_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new AlbumsPinchAnimationManager((PinchLayoutManager) this.mLayoutManager);
    }

    public String getScreenId() {
        if (!isSelectionMode() || getAdapter() == null) {
            return AnalyticsScreenId.SCREEN_ALBUM_VIEW_NORMAL.toString();
        }
        if (getAdapter().getSelectionModeByLongPress()) {
            return AnalyticsScreenId.SCREEN_ALBUM_VIEW_LONG_PRESS_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_ALBUM_VIEW_EDIT.toString();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        Optional.ofNullable(this.mSmartAlbumHolder).ifPresent(new C0366a(20));
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Optional.ofNullable(this.mSmartAlbumHolder).ifPresent(new C0366a(20));
    }

    public boolean onBackPressed() {
        if (!isMoveMode()) {
            return super.onBackPressed();
        }
        this.mBlackboard.post("command://ExitMoveMode", Boolean.FALSE);
        return true;
    }

    public void onCreate(Bundle bundle) {
        try {
            Trace.beginSection("APP_AlbumsFragment onCreate");
            super.onCreate(bundle);
        } finally {
            Trace.endSection();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            Trace.beginSection("APP_AlbumsFragment onCreateView");
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        } finally {
            Trace.endSection();
        }
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, "sortBy : ");
        t.append(SortByType.getAlbumsOrder());
        Logger.dumpLog(printWriter, t.toString());
    }

    public void onEnterMoveMode() {
        super.onEnterMoveMode();
        setHeaderAndFooterEnabled(false);
    }

    public void onExitMoveMode() {
        super.onExitMoveMode();
        setHeaderAndFooterEnabled(true);
    }

    public boolean onFolderCreated(int i2, String str, ArrayList<Integer> arrayList) {
        if (getAdapter() == null || !getAdapter().onFolderCreated(i2, str, arrayList)) {
            return false;
        }
        ThreadUtil.postOnUiThreadDelayed(new C0372g(6, this), 500);
        return true;
    }

    public void onPrepareUngrouping(ArrayList<Integer> arrayList) {
        if (getAdapter() != null) {
            getAdapter().onPrepareUngrouping(arrayList);
        }
    }

    public void onSelectionModeChanged(boolean z) {
        super.onSelectionModeChanged(z);
        if (!isMoveMode()) {
            setHeaderAndFooterEnabled(!z);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        try {
            Trace.beginSection("APP_AlbumsFragment onViewCreated");
            super.onViewCreated(view, bundle);
        } finally {
            Trace.endSection();
        }
    }

    public void saLoggingSendCount() {
        if (this.mListAdapter != null) {
            ArrayList<MediaItem> allData = this.mMediaData.getAllData();
            long count = allData.stream().filter(new C0571z(12)).count();
            postAnalyticsLog(AnalyticsEventId.EVENT_TAB_ALBUMS, (Pair<String, String>[]) Analytics.buildAlbumCountDetail(((long) allData.size()) - count, count));
        }
    }

    public boolean supportLayoutCache() {
        return true;
    }

    public boolean supportTabLayout() {
        return true;
    }

    public void updateAppbarSelectionCount(int i2, int i7) {
        SmartAlbumHolder smartAlbumHolder = this.mSmartAlbumHolder;
        if (smartAlbumHolder == null || !smartAlbumHolder.updateSelectionCount(getAppBarSelectionCountString(i2, i7))) {
            super.updateAppbarSelectionCount(i2, i7);
        }
    }

    public AlbumsBaseLayoutManager createLayoutManager() {
        GalleryListView listView = getListView();
        if (listView == null) {
            return null;
        }
        return new AlbumsLayoutManager(getContext(), listView.getColumnCount()) {
            public int[] getItemPadding(int i2) {
                int albumHorizontalPadding = GridMarginHelper.getAlbumHorizontalPadding(AlbumsFragment.this.mRecyclerView);
                int albumVerticalPadding = GridMarginHelper.getAlbumVerticalPadding(AlbumsFragment.this.mRecyclerView);
                return new int[]{albumHorizontalPadding, albumVerticalPadding, albumHorizontalPadding, albumVerticalPadding};
            }

            public int getSpacing(int i2) {
                if (isListView(i2)) {
                    return 0;
                }
                return AlbumsFragment.this.getGridSpacing();
            }

            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                AlbumsFragment.this.onGridChanged(i2, spanCount);
            }
        };
    }

    public AlbumsPresenter createPresenter(IAlbumsView iAlbumsView) {
        return new AlbumsPresenter(this.mBlackboard, iAlbumsView);
    }

    public AlbumsViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumsViewAdapter(this, getLocationKey());
    }

    public AlbumsViewAdapter getAdapter() {
        return (AlbumsViewAdapter) super.getAdapter();
    }
}
