package com.samsung.android.gallery.app.ui.list.albums.mx.manage;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.IMxManageAlbumsView;
import com.samsung.android.gallery.app.ui.list.albums.mx.manage.MxManageAlbumsPresenter;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxManageAlbumsFragment<V extends IMxManageAlbumsView, P extends MxManageAlbumsPresenter<V>> extends AlbumsFragment<V, P> implements IMxManageAlbumsView {
    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new ManageAlbumsViewHolderFactory(context);
            }
        };
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return null;
    }

    public int getSelectionToolbarTitle() {
        return R.string.select_essential_albums;
    }

    public boolean onBackPressed() {
        getBlackboard().postEvent(EventMessage.obtain(1003));
        return false;
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (isViewActive() && !isSelectionMode() && getDataCount() > 0) {
            enterSelectionMode(0);
        }
    }

    public void onItemSelected(int i2, boolean z) {
        ((MxManageAlbumsPresenter) this.mPresenter).onItemSelected(i2, z);
    }

    public void onSelectAll() {
        super.onSelectAll();
        ((MxManageAlbumsPresenter) this.mPresenter).onAllItemsSelected(true);
    }

    public void onUnSelectAll() {
        super.onUnSelectAll();
        ((MxManageAlbumsPresenter) this.mPresenter).onAllItemsSelected(false);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportTabLayout() {
        return false;
    }

    public MxManageAlbumsPresenter createPresenter(IMxManageAlbumsView iMxManageAlbumsView) {
        return new MxManageAlbumsPresenter(this.mBlackboard, iMxManageAlbumsView);
    }

    public AlbumsViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new MxManageAlbumsViewAdapter(this, getLocationKey());
    }

    public void createDragAndDropDelegate() {
    }
}
