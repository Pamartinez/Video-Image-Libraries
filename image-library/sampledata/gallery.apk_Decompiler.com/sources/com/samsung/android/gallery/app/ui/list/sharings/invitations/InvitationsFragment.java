package com.samsung.android.gallery.app.ui.list.sharings.invitations;

import android.app.Dialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.invitations.IInvitationsView;
import com.samsung.android.gallery.app.ui.list.sharings.invitations.InvitationsPresenter;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InvitationsFragment<V extends IInvitationsView, P extends InvitationsPresenter> extends BaseListFragment<V, P> implements IInvitationsView {
    InvitationsAdapter mInvitationAdapter;
    Dialog mProgressDialog = null;

    public InvitationsFragment() {
        setLocationKey("location://sharing/albums/invitations");
        MdeAlbumHelper.updateLastVisitedTime();
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        if (this.mInvitationAdapter == null) {
            this.mInvitationAdapter = new InvitationsAdapter(this, getLocationKey());
        }
        return this.mInvitationAdapter;
    }

    public void dismissProgressDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    public int getLayoutId() {
        return R.layout.fragment_sharing_invitations;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SHARED_VIEW_INVITATIONS.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public int[] loadPinchColumnResource() {
        return new int[]{1};
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onDestroy() {
        PreferenceCache.SharedNewNotificationBadge.setBoolean(false);
        dismissProgressDialog();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
    }

    public void showProgressDialog() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressCircleBuilder(getContext()).setProgressMessage(getResources().getString(R.string.please_wait)).create();
        }
        this.mProgressDialog.show();
    }

    public boolean supportSelection() {
        return false;
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public InvitationsPresenter createPresenter(IInvitationsView iInvitationsView) {
        return new InvitationsPresenter(this.mBlackboard, iInvitationsView);
    }
}
