package com.samsung.android.gallery.app.ui.list.albums.choice.abstraction;

import Ab.b;
import Bb.l;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseLayoutManager;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.IAlbumChoiceBaseView;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceBaseFragment<V extends IAlbumChoiceBaseView, P extends AlbumChoiceBasePresenter> extends AlbumsBaseFragment<V, P> implements IAlbumChoiceBaseView {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshView$0(String str, int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.reInit(str);
            if (getListView() != null) {
                if (i2 == 0 && getAppbarLayout() != null) {
                    getAppbarLayout().seslSetExpanded(false);
                }
                getListView().scrollToPositionWithOffset(i2, 0);
            }
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        Optional.ofNullable(this.mAppBarLayout).ifPresent(new l(17));
    }

    public int getCurrentColCount() {
        return loadPinchColumnResource()[loadPinchDepth()];
    }

    public final void loadArguments(Bundle bundle) {
        super.loadArguments(bundle);
        loadValues();
    }

    public boolean onBackPressed() {
        if (((AlbumChoiceBasePresenter) this.mPresenter).onBackPressed() || super.onBackPressed()) {
            return true;
        }
        return false;
    }

    public void refreshView(String str, int i2) {
        setLocationKey(str);
        ThreadUtil.postOnUiThread(new b((Object) this, (Object) str, i2, 9));
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public AlbumsBaseLayoutManager createLayoutManager() {
        return new AlbumsChoiceLayoutManager(getContext(), getCurrentColCount()) {
            public int[] getItemPadding(int i2) {
                int albumHorizontalPadding = GridMarginHelper.getAlbumHorizontalPadding(AlbumChoiceBaseFragment.this.mRecyclerView);
                int albumVerticalPadding = GridMarginHelper.getAlbumVerticalPadding(AlbumChoiceBaseFragment.this.mRecyclerView);
                return new int[]{albumHorizontalPadding, albumVerticalPadding, albumHorizontalPadding, albumVerticalPadding};
            }

            public int getSpacing(int i2) {
                if (isListView(i2)) {
                    return 0;
                }
                return AlbumChoiceBaseFragment.this.getGridSpacing();
            }
        };
    }

    public AlbumChoiceBasePresenter createPresenter(IAlbumChoiceBaseView iAlbumChoiceBaseView) {
        return new AlbumChoiceBasePresenter(this.mBlackboard, iAlbumChoiceBaseView);
    }

    public AlbumChoiceBaseAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumChoiceBaseAdapter(this, getLocationKey());
    }

    public void loadValues() {
    }
}
