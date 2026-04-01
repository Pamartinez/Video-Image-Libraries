package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.choice.AlbumFolderChoicePresenter;
import com.samsung.android.gallery.app.ui.list.albums.choice.IFolderChoiceView;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.AlbumChoiceBaseFragment;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.Optional;
import q2.C0267a;
import q2.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumFolderChoiceFragment<V extends IFolderChoiceView, P extends AlbumFolderChoicePresenter> extends AlbumChoiceBaseFragment<V, P> implements IFolderChoiceView {
    public y createFloatingToolbarAware(FloatingToolbarLayout floatingToolbarLayout) {
        return new y(floatingToolbarLayout) {
            public View getReferenceView(C0267a aVar) {
                if (!AlbumFolderChoiceFragment.this.isViewActive() || aVar != C0267a.END_FIRST) {
                    return null;
                }
                return super.getReferenceView(aVar);
            }

            public List<View> getReferenceViews(C0267a aVar) {
                if (!AlbumFolderChoiceFragment.this.isViewActive() || aVar != C0267a.START_FIRST || AlbumFolderChoiceFragment.this.getToolbar() == null) {
                    return null;
                }
                View naviUpButton = AlbumFolderChoiceFragment.this.getToolbar().getNaviUpButton();
                View findViewById = AlbumFolderChoiceFragment.this.getToolbar().findViewById(R.id.select_info_layout);
                if (naviUpButton == null || findViewById == null) {
                    return null;
                }
                return List.of(naviUpButton, findViewById);
            }

            public void onStartHideFloatingBackground() {
                if (AlbumFolderChoiceFragment.this.isViewActive()) {
                    Optional.ofNullable(AlbumFolderChoiceFragment.this.mToolbar).ifPresent(new a(0));
                }
            }

            public void onStartShowFloatingBackground() {
                if (AlbumFolderChoiceFragment.this.isViewActive()) {
                    Optional.ofNullable(AlbumFolderChoiceFragment.this.mToolbar).ifPresent(new a(1));
                }
            }
        };
    }

    public void enterSelectionMode(int i2) {
        getListView().enterSelectionMode(i2);
        setSmartAlbumEnabled(false);
    }

    public int getCurrentColCount() {
        return loadPinchColumnResource()[getStartPinchDepth()];
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new AlbumFolderChoiceViewHolderFactory(context);
            }

            public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
                ListViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
                onCreateViewHolder.getRootView().setBackground((Drawable) null);
                return onCreateViewHolder;
            }
        };
    }

    public int getLayoutId() {
        return R.layout.folder_choice_fragment_layout;
    }

    public int getPreferenceDefault() {
        return 1;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.ALBUMS_GRID_SIZE;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_ALBUM_GROUP_ADD_ALBUM.toString();
    }

    public int[] getSelectedAlbumIds() {
        if (getAdapter() != null) {
            return getAdapter().getSelectedAlbumIds();
        }
        return new int[0];
    }

    public int getSelectedItemCount() {
        if (getAdapter() != null) {
            return getAdapter().getSelectedItemCount();
        }
        return 0;
    }

    public int getSelectionToolbarTitle() {
        return R.string.add_albums;
    }

    public int getStartPinchDepth() {
        int loadPinchDepth = loadPinchDepth();
        if (loadPinchDepth == 0) {
            return getPreferenceDefault();
        }
        return loadPinchDepth;
    }

    public boolean isFadingEdgeExtended() {
        return false;
    }

    public boolean isSelectionMode() {
        return true;
    }

    public boolean onBackPressed() {
        return ((AlbumFolderChoicePresenter) this.mPresenter).onBackPressed();
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        enterSelectionMode(0);
    }

    public void onDestroy() {
        super.onDestroy();
        Clipboard.getInstance(this.mBlackboard).clear();
        getBlackboard().post("command://HideBottomBar", Boolean.TRUE);
    }

    public boolean supportNaviBackInSelectionMode() {
        return true;
    }

    public void updateToolbarStartInset(GalleryToolbar galleryToolbar) {
        galleryToolbar.setContentInset();
    }

    public AlbumFolderChoicePresenter createPresenter(IFolderChoiceView iFolderChoiceView) {
        return new AlbumFolderChoicePresenter(this.mBlackboard, iFolderChoiceView);
    }

    public AlbumFolderChoiceAdapter getAdapter() {
        return (AlbumFolderChoiceAdapter) super.getAdapter();
    }

    public AlbumFolderChoiceAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumFolderChoiceAdapter(this, getLocationKey());
    }
}
