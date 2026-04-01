package com.samsung.android.gallery.app.ui.list.albums.virtual;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.dragdrop.abstraction.DummyDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.NoItemView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrivateAlbumPicturesFragment extends VirtualAlbumPicturesFragment<IPicturesView, PrivateAlbumPicturesPresenter> {
    private final ContentObserver mObserver = new ContentObserver((Handler) null) {
        public void onChange(boolean z, Uri uri) {
            Log.i(PrivateAlbumPicturesFragment.this.TAG, "onChange", uri);
            Optional.ofNullable((PrivateAlbumPicturesPresenter) PrivateAlbumPicturesFragment.this.mPresenter).ifPresent(new a(0));
        }

        public void onChange(boolean z) {
            onChange(z, (Uri) null);
        }
    };

    public void createDragAndDropDelegate() {
        this.mDragAndDropDelegate = new DummyDragAndDropDelegate();
    }

    public int getLayoutId() {
        return R.layout.fragment_recent_layout;
    }

    public String getScreenId() {
        AnalyticsScreenId analyticsScreenId;
        if (isSelectionMode()) {
            analyticsScreenId = AnalyticsScreenId.SCREEN_PRIVATE_ALBUM_EDIT;
        } else {
            analyticsScreenId = AnalyticsScreenId.SCREEN_PRIVATE_ALBUM;
        }
        return analyticsScreenId.toString();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PrivateDatabase.getInstance().registerObserver(this.mObserver);
    }

    public void onDestroy() {
        super.onDestroy();
        PrivateDatabase.getInstance().unregisterObserver(this.mObserver);
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        NoItemView noItemView = this.mEmptyViewText;
        if (noItemView != null) {
            noItemView.setLabel((int) R.string.empty_set_description_no_items);
            this.mEmptyViewText.setDescription((int) R.string.private_album_empty_set_description_no_item);
        }
    }

    public void onStop() {
        super.onStop();
        GalleryToolbar toolbar = getToolbar();
        if (LocationKey.isPrivateAlbum(getLocationKey()) && toolbar != null && toolbar.getMenu() != null) {
            toolbar.getMenu().close();
        }
    }

    public boolean supportTimeline() {
        return true;
    }

    public PrivateAlbumPicturesPresenter createPresenter(IPicturesView iPicturesView) {
        return new PrivateAlbumPicturesPresenter(this.mBlackboard, iPicturesView);
    }

    public void postAnalyticsLog() {
    }
}
