package com.samsung.android.gallery.app.ui.list.albums.hide;

import A4.H;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.hide.AlbumsHidePresenter;
import com.samsung.android.gallery.app.ui.list.albums.hide.IHideAlbumsView;
import com.samsung.android.gallery.module.album.hide.AlbumsHideManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsHideFragment<V extends IHideAlbumsView, P extends AlbumsHidePresenter<IHideAlbumsView>> extends AlbumsBaseFragment<V, P> implements IHideAlbumsView {
    private final AlbumsHideManager mHideAlbumManager = new AlbumsHideManager();
    private boolean mIsProcessingHide;
    private Dialog mProgressDialog;

    public AlbumsHideFragment() {
        setLocationKey("location://albums/hide");
    }

    private void hideProgressDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null) {
            if (dialog.isShowing()) {
                this.mProgressDialog.dismiss();
            }
            this.mProgressDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSwitchClick$0(Consumer consumer) {
        if (!isDestroyed()) {
            hideProgressDialog();
            this.mIsProcessingHide = false;
            consumer.accept((Object) null);
        }
    }

    private void showProgressDialog() {
        Context context = getContext();
        if (context != null) {
            if (this.mProgressDialog == null) {
                this.mProgressDialog = new ProgressCircleBuilder(context).setProgressMessage(getResources().getString(R.string.processing)).removeDialogAnim().create();
            }
            this.mProgressDialog.show();
        }
    }

    private void updateLayoutPadding() {
        GalleryListView listView = getListView();
        if (listView != null && listView.getParent() != null) {
            ViewUtils.setMainLayoutFlexibleSideSpacing((ViewGroup) listView.getParent());
        }
    }

    public AlbumsViewDummyAdapter getDummyAdapter() {
        return new AlbumsViewDummyAdapter(getListView()) {
            public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
                return new AlbumsHideViewHolderFactory(context);
            }
        };
    }

    public int getLayoutId() {
        return R.layout.fragment_hide_albums_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return null;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_ALBUM_HIDE_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public int getStartPinchDepthDex() {
        return 0;
    }

    public int[] loadPinchColumnResource() {
        return new int[]{1};
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateLayoutPadding();
    }

    public void onDestroy() {
        this.mBlackboard.post("command://HideBottomBar", Boolean.FALSE);
        this.mBlackboard.postBroadcastEvent(EventMessage.obtain(101));
        hideProgressDialog();
        super.onDestroy();
    }

    public boolean onSwitchClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, Consumer<Void> consumer) {
        if (this.mIsProcessingHide) {
            return false;
        }
        showProgressDialog();
        this.mIsProcessingHide = true;
        this.mHideAlbumManager.setHideAlbum(new H(13, (Object) this, (Object) consumer), mediaItem);
        postAnalyticsLog(AnalyticsEventId.EVENT_CHANGE_HIDE_ALBUM, AnalyticsDetail.getOnOff(!mediaItem.isAlbumHide()));
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateLayoutPadding();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportSelection() {
        return false;
    }

    public AlbumsHidePresenter<?> createPresenter(IHideAlbumsView iHideAlbumsView) {
        return new AlbumsHidePresenter<>(this.mBlackboard, iHideAlbumsView);
    }

    public AlbumsHideViewAdapter<?> createListViewAdapter(GalleryListView galleryListView) {
        return new AlbumsHideViewAdapter<>(this, getLocationKey());
    }

    public void savePinchDepth(String str, int i2) {
    }
}
