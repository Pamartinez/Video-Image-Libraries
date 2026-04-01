package com.samsung.android.gallery.app.ui.list.sharings;

import A4.C0368c;
import A4.C0371f;
import C3.g;
import L5.a;
import L5.b;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.ISharingsView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsPresenter;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeNotificationManager;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.noitem.NoItemVIShared;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingsFragment<V extends ISharingsView, P extends SharingsPresenter> extends BaseListFragment<V, P> implements ISharingsView {
    private LinearLayout mCreateButton;

    public SharingsFragment() {
        setLocationKey("location://sharing/albums");
        MdeAlbumHelper.updateLastVisitedTime();
    }

    private String getSubTitle() {
        int itemCount = getItemCount();
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            if (itemCount == 0) {
                return null;
            }
            Integer num = (Integer) Optional.ofNullable((SharingsViewAdapter) getAdapter()).map(new b(0)).orElse(0);
            int intValue = num.intValue();
            int i2 = itemCount - intValue;
            if (i2 == 0 && intValue > 0) {
                return getResources().getQuantityString(R.plurals.n_invitations, intValue, new Object[]{num});
            }
            if (i2 > 0 && intValue == 0) {
                return getResources().getQuantityString(R.plurals.count_shared_albums, i2, new Object[]{Integer.valueOf(i2)});
            }
            if (i2 == 1 && intValue > 0) {
                return getResources().getQuantityString(R.plurals.one_shared_album_n_invitations, intValue, new Object[]{num});
            }
            if (i2 <= 0 || intValue != 1) {
                return getResources().getString(R.string.n_shared_albums_n_invitations, new Object[]{Integer.valueOf(i2), num});
            }
            return getResources().getQuantityString(R.plurals.n_shared_albums_one_invitation, i2, new Object[]{Integer.valueOf(i2)});
        } else if (itemCount == 0) {
            return getString(R.string.empty_set_description_no_shared_albums);
        } else {
            return AppResources.getQuantityString(R.plurals.count_shared_albums, itemCount, Integer.valueOf(itemCount));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChangedOnUi$0(int i2) {
        if (!isDestroyed()) {
            PreferenceAnalytics.SharedCount.setInteger(i2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEmptyViewVisibilityChanged$4(View view) {
        ((SharingsPresenter) this.mPresenter).createSharedAlbum();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showNoticeProfileSharingDialog$2(MediaItem mediaItem, DialogInterface dialogInterface, int i2) {
        requestInvitationAction(RequestCmdType.REQUEST_INVITATION_ACCEPTANCE, MediaItemMde.getGroupId(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showNoticeProfileSharingDialog$3(String str, MediaItem mediaItem, Context context) {
        new AlertDialog.Builder(context).setTitle((CharSequence) str).setMessage((CharSequence) getString(R.string.your_profile_and_name_will_be_shared, mediaItem.getTitle())).setPositiveButton((int) R.string.join, (DialogInterface.OnClickListener) new g(4, this, mediaItem)).setNeutralButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateExtendedAppBar$1(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setSubtitle(getSubTitle());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViewDensity$5(View view) {
        ((SharingsPresenter) this.mPresenter).createSharedAlbum();
    }

    private void requestInvitationAction(RequestCmdType requestCmdType, String str) {
        AnalyticsEventId analyticsEventId;
        if (requestCmdType == RequestCmdType.REQUEST_INVITATION_ACCEPTANCE) {
            analyticsEventId = AnalyticsEventId.EVENT_SHARED_VIEW_INVITATIONS_ACCEPT;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SHARED_VIEW_INVITATIONS_DECLINE;
        }
        postAnalyticsLog(analyticsEventId);
        new RequestSharedAlbumCmd().execute(getEventContext(), requestCmdType, str);
        MdeNotificationManager.getInstance(getContext()).cancel(Math.abs(str.hashCode()));
    }

    private void showNoticeProfileSharingDialog(MediaItem mediaItem) {
        MdeGroupHelper.saveNoticeProfileSharingDialogPreferenceState(false);
        Optional.ofNullable(getContext()).ifPresent(new C0371f((Object) this, (Object) getString(R.string.shared_album_invitation_join_dialog_title, mediaItem.getTitle()), mediaItem, 8));
    }

    private void updateViewDensity() {
        View view = this.mEmptyView;
        if (view != null && this.mCreateButton != null) {
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.create_button_layout_container);
            int indexOfChild = viewGroup.indexOfChild(this.mCreateButton);
            ViewUtils.removeView(viewGroup, this.mCreateButton);
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.shared_album_create_button_layout, (ViewGroup) null);
            this.mCreateButton = linearLayout;
            viewGroup.addView(linearLayout, indexOfChild);
            this.mCreateButton.setOnClickListener(new a(this, 1));
        }
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        if (!PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING) {
            super.addSharedTransition(listViewHolder, mediaItem, i2, z);
            TransitionInfo transitionInfo = new TransitionInfo(listViewHolder.getMediaItem(), listViewHolder.getBitmap(), 0);
            String spaceId = MediaItemMde.getSpaceId(listViewHolder.getMediaItem());
            Blackboard blackboard = this.mBlackboard;
            TextView titleView = listViewHolder.getTitleView();
            SharedTransition.addSharedElement(blackboard, titleView, "sharing/title/" + spaceId, transitionInfo);
            Blackboard blackboard2 = this.mBlackboard;
            View decoView = listViewHolder.getDecoView(43);
            SharedTransition.addSharedElement(blackboard2, decoView, "sharing/groupInfo/" + spaceId, transitionInfo);
        }
    }

    public void completeSession(boolean z) {
        initializeEmptyView();
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SharingsViewAdapter(this, getLocationKey());
    }

    public void exitSelectionMode(boolean z) {
        super.exitSelectionMode(z);
        updateExtendedAppBar();
    }

    public int getLayoutId() {
        return R.layout.fragment_sharings_layout;
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_SHARED_VIEW_SELECTION.toString();
        }
        return AnalyticsScreenId.SCREEN_SHARED_VIEW_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        updateViewDensity();
    }

    public void initializeEmptyView() {
        if (!isDestroyed()) {
            ((SharingsPresenter) this.mPresenter).updateAddContentMenu();
            getListView().setCustomVI(new NoItemVIShared(this.mEmptyView));
        }
    }

    public boolean isSupportExtendedAppBar() {
        return true;
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.sharings_column_count);
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        updateExtendedAppBar();
        int dataCount = getDataCount();
        if (dataCount > 0) {
            ThreadUtil.postOnBgThreadDelayed(new C0368c(this, dataCount, 7), 1000);
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        View view2 = this.mEmptyView;
        if (view2 != null && view2.getVisibility() == 0 && this.mCreateButton == null) {
            LinearLayout linearLayout = (LinearLayout) this.mEmptyView.findViewById(R.id.create_button_layout);
            this.mCreateButton = linearLayout;
            linearLayout.setOnClickListener(new a(this, 0));
            ViewUtils.setAccessibilityRoleDescription(this.mCreateButton, R.string.speak_button);
        }
        if (!isSupportExtendedAppBar() && this.mAppBarLayout != null) {
            View view3 = this.mEmptyView;
            if (view3 == null || view3.getVisibility() != 0) {
                view = getListView();
            }
            this.mAppBarLayout.setScrollEnable(false, view);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        observeMediaData(!z);
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        RequestCmdType requestCmdType;
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS || !MediaItemMde.isInvitation(mediaItem)) {
            super.onListItemClick(listViewHolder, i2, mediaItem, i7);
            return;
        }
        Log.d(this.TAG, "onListItemClick", Integer.valueOf(i2), Integer.valueOf(i7), "invitation");
        if (MdeGroupHelper.isNeedToShowNoticeProfileSharingDialog() && i7 == 101) {
            showNoticeProfileSharingDialog(mediaItem);
        } else if (i7 == 101 || i7 == 100) {
            String groupId = MediaItemMde.getGroupId(mediaItem);
            if (i7 == 101) {
                requestCmdType = RequestCmdType.REQUEST_INVITATION_ACCEPTANCE;
            } else {
                requestCmdType = RequestCmdType.REQUEST_INVITATION_REJECTION;
            }
            requestInvitationAction(requestCmdType, groupId);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        StringCompat stringCompat = this.TAG;
        Log.she(stringCompat, "onListItemLongClick p=" + i2);
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS && MediaItemMde.isInvitation(mediaItem)) {
            return false;
        }
        new SharingsContextMenuHandler().handle(this, mediaItem);
        return true;
    }

    public boolean onPostViewCreated() {
        long currentTimeMillis = System.currentTimeMillis();
        int[] iArr = {R.layout.recycler_item_sharing_pictures_header_layout};
        LayoutCache instance = LayoutCache.getInstance();
        int i2 = iArr[0];
        if (!instance.hasView(i2)) {
            instance.putView(i2, getLayoutInflater().inflate(i2, (ViewGroup) null, false));
        }
        StringCompat stringCompat = this.TAG;
        Log.sh(stringCompat, "onPostViewCreated +" + (System.currentTimeMillis() - currentTimeMillis));
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        GalleryAppBarLayout galleryAppBarLayout;
        super.onViewCreated(view, bundle);
        if (!isSupportExtendedAppBar() && (galleryAppBarLayout = this.mAppBarLayout) != null) {
            galleryAppBarLayout.setScrollEnable(false, getListView());
        }
    }

    public void printTransitionDebugInfo() {
        for (int findFirstVisibleItemPositionCompat = getListView().findFirstVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat <= getListView().findLastVisibleItemPositionCompat(); findFirstVisibleItemPositionCompat++) {
            View findViewByPosition = getLayoutManager().findViewByPosition(findFirstVisibleItemPositionCompat);
            if (findViewByPosition != null) {
                RecyclerView.ViewHolder childViewHolder = getListView().getChildViewHolder(findViewByPosition);
                if (childViewHolder instanceof ImageViewHolder) {
                    StringCompat stringCompat = this.TAG;
                    StringBuilder o2 = C0086a.o(findFirstVisibleItemPositionCompat, "TransitionName[", "] : ");
                    o2.append(Logger.getEncodedString(((ImageViewHolder) childViewHolder).getImage().getTransitionName()));
                    Log.st(stringCompat, o2.toString());
                }
            }
        }
    }

    public void startPostponedEnterTransition() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.startPostponedEnterTransition();
        } else {
            super.startPostponedEnterTransition();
        }
    }

    public void startPostponedEnterTransitionV2() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment != null) {
            mvpBaseFragment.startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return PreferenceFeatures.OneUi40.DISPLAY_CUSTOM_COVER_SHARING;
    }

    public boolean supportFastScroll() {
        return Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY);
    }

    public boolean supportLayoutCache() {
        return true;
    }

    public boolean supportPostViewCreated() {
        return true;
    }

    public void updateExtendedAppBar() {
        if (isSupportExtendedAppBar() && getToolbar() != null && getContext() != null) {
            Optional.ofNullable(this.mAppBarLayout).ifPresent(new K5.a(3, this));
        }
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }

    public SharingsPresenter createPresenter(ISharingsView iSharingsView) {
        return new SharingsPresenter(this.mBlackboard, iSharingsView);
    }
}
