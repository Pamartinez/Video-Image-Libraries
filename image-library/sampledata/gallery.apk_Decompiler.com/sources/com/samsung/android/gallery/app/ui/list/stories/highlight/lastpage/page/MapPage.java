package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import T8.C0578a;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.list.mapclustering.EmbeddedMapFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.MapPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.manager.MarkerIconManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.sec.android.gallery3d.R;
import h3.b;
import java.util.ArrayList;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapPage extends SummaryPage {
    private MapPageItem mItem;
    private EmbeddedMapFragment<?, ?> mMapFragment;
    private View mMapParent;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class StoryEmbeddedMapFragment extends EmbeddedMapFragment {
        private final View.OnClickListener mOnMapClickedListener;

        public StoryEmbeddedMapFragment(String str, MediaItem[] mediaItemArr, View.OnClickListener onClickListener) {
            super(str, mediaItemArr);
            this.mOnMapClickedListener = onClickListener;
        }

        public MarkerIconManager createIconMarker() {
            return super.createIconMarker().enableSelectedMarker(false);
        }

        public void onMapClicked() {
            View.OnClickListener onClickListener = this.mOnMapClickedListener;
            if (onClickListener != null) {
                onClickListener.onClick((View) null);
            }
            super.onMapClicked();
        }
    }

    public MapPage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private MediaItem[] getLocationItems() {
        ArrayList<MediaItem> arrayList;
        if (this.mView.getMediaData() != null) {
            arrayList = this.mView.getMediaData().getAllData();
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            return (MediaItem[]) arrayList.stream().filter(new b(5, this)).toArray(new C0578a(29));
        }
        return new MediaItem[0];
    }

    private void initMap() {
        try {
            if (this.mView.isDestroyed()) {
                Log.i(this.TAG, "ignore initMap [destroyed]");
                return;
            }
            this.mMapFragment = new StoryEmbeddedMapFragment(this.mView.getLocationKey(), getLocationItems(), new C0496a(14, this));
            this.mView.getChildFragmentManager().beginTransaction().add((int) R.id.map_parent, (Fragment) this.mMapFragment).commitAllowingStateLoss();
            Log.d(this.TAG, "initMap");
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "initMap failed", e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public boolean isValidLocation(MediaItem mediaItem) {
        return MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getLocationItems$1(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initMap$0(View view) {
        onMapClicked();
    }

    private void onMapClicked() {
        this.mEventHandler.postEvent(Event.LAST_PAGE_RESET_COUNTDOWN, new Object[0]);
    }

    private void removeMapFragment() {
        if (this.mMapFragment != null) {
            try {
                this.mView.getChildFragmentManager().beginTransaction().remove(this.mMapFragment).commitNowAllowingStateLoss();
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "removeMapFragment failed", (Throwable) e);
            }
            this.mMapFragment = null;
        }
    }

    public void bind(PageItem pageItem) {
        super.bind(pageItem);
        MapPageItem mapPageItem = this.mItem;
        if (mapPageItem == null || !mapPageItem.equalItems(pageItem)) {
            this.mItem = (MapPageItem) pageItem;
            removeMapFragment();
            initMap();
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMapParent = view.findViewById(R.id.map_parent);
    }

    public View getContent() {
        return this.mMapParent;
    }

    public void onBindItem() {
        initLayout(new PageSpec(this.mView, this.mRootParent).calculate());
    }

    public void prepareResolutionChange() {
        removeMapFragment();
    }

    public void recycle() {
        super.recycle();
        removeMapFragment();
    }

    public void updateLayout(PageSpec.Config config, int i2) {
        super.updateLayout(config, i2);
        updateViewSize(this.mMapParent, config, i2);
    }
}
