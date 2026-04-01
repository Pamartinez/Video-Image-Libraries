package com.samsung.android.gallery.app.ui.map.search;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.LocationValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchMapFragmentContainerView extends FrameLayout {
    private final FragmentManager mFragmentManager;
    private boolean mIsFragmentAdded;
    private SearchMapFragment<ISearchMapView, SearchMapPresenter<ISearchMapView>> mMapFragment;
    private boolean mTouchBlock;
    private final int mViewId;

    public SearchMapFragmentContainerView(EventContext eventContext) {
        super(eventContext.getContext());
        createMapFragment();
        int generateViewId = View.generateViewId();
        this.mViewId = generateViewId;
        setId(generateViewId);
        this.mFragmentManager = ((AppCompatActivity) eventContext.getActivity()).getSupportFragmentManager();
    }

    private void createMapFragment() {
        this.mMapFragment = new SearchMapFragment<>();
        Bundle bundle = new Bundle();
        bundle.putString("locationKey", "location://map");
        this.mMapFragment.setArguments(bundle);
    }

    public void attachMapFragment() {
        String str;
        if (findViewById(getId()) == null) {
            Log.se("SearchMapFragmentContainerView", "attachMapFragment : not found " + getId() + ArcCommonLog.TAG_COMMA + this.mViewId);
            return;
        }
        try {
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            StringBuilder sb2 = new StringBuilder("attachMapFragment");
            Boolean valueOf = Boolean.valueOf(this.mIsFragmentAdded);
            if (this.mFragmentManager.findFragmentByTag(this.mMapFragment.getLocationKey()) != null) {
                str = "found";
            } else {
                str = "n/a";
            }
            sb2.append(Logger.v(valueOf, str));
            Log.s("SearchMapFragmentContainerView", sb2.toString());
            if (this.mIsFragmentAdded) {
                beginTransaction.show(this.mMapFragment);
            } else {
                int id = getId();
                SearchMapFragment<ISearchMapView, SearchMapPresenter<ISearchMapView>> searchMapFragment = this.mMapFragment;
                beginTransaction.add(id, (Fragment) searchMapFragment, searchMapFragment.getLocationKey());
                this.mIsFragmentAdded = true;
            }
            beginTransaction.commitNowAllowingStateLoss();
        } catch (Exception e) {
            Log.se("SearchMapFragmentContainerView", e.getMessage());
        }
    }

    public void bindData(MediaData mediaData, LocationValue locationValue) {
        SearchMapFragment<ISearchMapView, SearchMapPresenter<ISearchMapView>> searchMapFragment = this.mMapFragment;
        if (searchMapFragment != null) {
            searchMapFragment.bindData(mediaData, locationValue);
        }
    }

    public void destroy() {
        this.mFragmentManager.beginTransaction().remove(this.mMapFragment).commitAllowingStateLoss();
        this.mMapFragment = null;
        this.mIsFragmentAdded = false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mTouchBlock) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean isMapFragmentAdded() {
        return this.mIsFragmentAdded;
    }

    public boolean isTouchOngoing() {
        return false;
    }

    public void moveTo(MediaItem mediaItem) {
        SearchMapFragment<ISearchMapView, SearchMapPresenter<ISearchMapView>> searchMapFragment = this.mMapFragment;
        if (searchMapFragment != null) {
            searchMapFragment.moveTo(mediaItem);
        }
    }

    public void moveToCurrentLocation() {
        SearchMapFragment<ISearchMapView, SearchMapPresenter<ISearchMapView>> searchMapFragment = this.mMapFragment;
        if (searchMapFragment != null) {
            searchMapFragment.moveToCurrentLocation();
        }
    }

    public void setOnSnapShotReadyListener(MapContainer.OnSnapshotReadyListener onSnapshotReadyListener) {
        SearchMapFragment<ISearchMapView, SearchMapPresenter<ISearchMapView>> searchMapFragment = this.mMapFragment;
        if (searchMapFragment != null) {
            searchMapFragment.setOnSnapShotReadyListener(onSnapshotReadyListener);
        }
    }

    public void setTouchBlock(boolean z) {
        this.mTouchBlock = z;
    }
}
