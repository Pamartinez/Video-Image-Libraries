package com.samsung.android.gallery.app.ui.list.sharings.choice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.IAlbumChoiceBaseView;
import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoicePresenter;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingAlbumChoiceFragment<V extends IAlbumChoiceBaseView, P extends SharingAlbumChoicePresenter> extends AlbumChoiceBaseFragment<V, P> {
    RelativeLayout mSharedEmptyView;

    public int getCurrentColCount() {
        return loadPinchColumnResource()[getStartPinchDepth()];
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new SharingAlbumChoiceDummyAdapter(getListView());
    }

    public int getLayoutId() {
        return R.layout.shared_album_choice_fragment_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_VIEW_ADD_TO_SHARED_ALBUM.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.sharings_column_count);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSharedEmptyView = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(getLayoutId(), (ViewGroup) null).findViewById(R.id.shared_empty_view);
    }

    public void onDataChangedOnUi() {
        int i2;
        super.onDataChangedOnUi();
        RelativeLayout relativeLayout = this.mSharedEmptyView;
        if (this.mMediaData.getCount() > 0) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        relativeLayout.setVisibility(i2);
    }

    public SharingAlbumChoicePresenter createPresenter(IAlbumChoiceBaseView iAlbumChoiceBaseView) {
        return new SharingAlbumChoicePresenter(this.mBlackboard, iAlbumChoiceBaseView);
    }

    public SharingAlbumChoiceAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SharingAlbumChoiceAdapter(this, getLocationKey());
    }
}
