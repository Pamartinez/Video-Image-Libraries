package com.samsung.android.gallery.app.ui.list.albums.folder;

import Ab.a;
import Ab.b;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragFragment;
import com.samsung.android.gallery.app.ui.list.albums.folder.FolderViewPresenter;
import com.samsung.android.gallery.app.ui.list.albums.folder.IFolderView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderViewFragment<V extends IFolderView, P extends FolderViewPresenter> extends AlbumsDragFragment<V, P> implements IFolderView {
    private View mCreateButton;
    private String mFolderLocationKey = "location://folder/root";

    public FolderViewFragment() {
        setLocationKey("location://folder/root");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEmptyViewVisibilityChanged$0(View view) {
        ((FolderViewPresenter) this.mPresenter).addAlbumsToFolder();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshFolder$2(String str, int i2) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.reInit(str);
            if (getListView() != null) {
                getListView().scrollToPositionWithOffset(i2, 0);
                if (isDisplayWithDrawer()) {
                    this.mBlackboard.publish("data://drawer_focus_changed", str);
                }
            }
        }
    }

    private void onPredictiveBackInvokableStateChanged() {
        if (PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            this.mBlackboard.post("command:///OnBackPressInvokableStateChanged", (Object) null);
        }
    }

    private void setCreateButtonText(View view) {
        TextView textView;
        if (view != null && (textView = (TextView) view.findViewById(R.id.button_text)) != null) {
            textView.setText(R.string.add_albums_body);
        }
    }

    private void updateAddAlbumButtonEnable(boolean z) {
        View view;
        float f;
        View view2 = this.mEmptyView;
        if (view2 != null && view2.getVisibility() == 0 && (view = this.mCreateButton) != null) {
            view.setEnabled(z);
            View view3 = this.mCreateButton;
            if (z) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            view3.setAlpha(f);
            this.mCreateButton.setClickable(z);
        }
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new FolderViewHolderFactory(context);
            }
        };
    }

    public String getFolderLocationKey() {
        return this.mFolderLocationKey;
    }

    public int getLayoutId() {
        return R.layout.fragment_folder_layout;
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_ALBUM_GROUP_EDIT.toString();
        }
        return AnalyticsScreenId.SCREEN_ALBUM_GROUP_NORMAL.toString();
    }

    public void initializeEmptyView() {
        GalleryListView listView = getListView();
        if (listView != null) {
            listView.setEmptyView(this.mEmptyView);
        }
    }

    public void onAlbumsAdded(int[] iArr) {
        if (getAdapter() != null) {
            getAdapter().onPrepareAlbumAddition(iArr);
        }
        View view = this.mEmptyView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        super.onApplyWindowInsets(view, windowInsets);
        adjustEmptyViewButtonLayout(false);
        return windowInsets;
    }

    public boolean onBackPressed() {
        if ((isSelectionMode() || !((FolderViewPresenter) this.mPresenter).onBackPressed()) && !super.onBackPressed()) {
            return false;
        }
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getArguments() != null) {
            String string = getArguments().getString("locationKey", "location://folder/root");
            if (this.mFolderLocationKey.equals("location://folder/root")) {
                this.mFolderLocationKey = ArgumentsUtil.removeArgs(string);
            }
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDataInserted(int i2, int i7) {
        super.onDataInserted(i2, i7);
        ((FolderViewPresenter) this.mPresenter).updateToolbar(getToolbar());
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        View view2 = this.mEmptyView;
        if (view2 != null && view2.getVisibility() == 0) {
            if (isMoveMode()) {
                this.mEmptyView.setVisibility(8);
            } else if (this.mCreateButton == null) {
                View findViewById = this.mEmptyView.findViewById(R.id.create_button_layout);
                this.mCreateButton = findViewById;
                findViewById.setOnClickListener(new a(23, this));
                setCreateButtonText(this.mCreateButton);
                ViewUtils.setAccessibilityRoleDescription(this.mCreateButton, R.string.speak_button);
            }
            updateAddAlbumButtonEnable(!Features.isEnabled(Features.IS_UPSM));
        }
    }

    public void refreshFolder(String str, int i2) {
        this.mFolderLocationKey = ArgumentsUtil.removeArgs(str);
        ThreadUtil.postOnUiThread(new b((Object) this, (Object) str, i2, 16));
        onInitMoveMode();
        onPredictiveBackInvokableStateChanged();
    }

    public void savePinchDepth(String str, int i2) {
        super.savePinchDepth(str, i2);
        P p6 = this.mPresenter;
        if (p6 != null) {
            Blackboard.publishGlobal("command://AlbumsViewChanged", Integer.valueOf(((FolderViewPresenter) p6).getCurrentViewDepth()));
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        P p6;
        if (!super.supportExitPredictiveBack() || (p6 = this.mPresenter) == null || ((FolderViewPresenter) p6).isDepthIn()) {
            return false;
        }
        return true;
    }

    public FolderViewPresenter createPresenter(IFolderView iFolderView) {
        return new FolderViewPresenter(this.mBlackboard, iFolderView);
    }

    public FolderViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new FolderViewAdapter(this, getLocationKey());
    }

    public FolderViewAdapter getAdapter() {
        return (FolderViewAdapter) super.getAdapter();
    }
}
