package com.samsung.android.gallery.app.activity.external;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeNotificationManager;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SharingsViewNavigatorController extends ViewNavigatorController {
    private boolean mLaunched = false;
    private MediaData mMediaData;

    public SharingsViewNavigatorController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    private boolean checkSpaceExist(String str) {
        if (MdeAlbumHelper.checkSpaceExist(str)) {
            return true;
        }
        finishActivityOnUiThread((int) R.string.unable_to_view_this_album_removed_by_its_creator);
        return false;
    }

    private void finishActivityOnUiThread(int i2) {
        finishActivityOnUiThread(getContext().getString(i2));
    }

    private int getFailedStringRes(int i2) {
        if (i2 == 1) {
            return R.string.could_not_add_item_because_it_could_not_be_found;
        }
        return R.string.could_not_add_items_because_they_could_not_be_found;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, java.util.function.IntFunction] */
    private MediaItem[] getItems() {
        ArrayList arrayList = new ArrayList();
        if (UriItemLoader.loadMediaItemFromUris(getLaunchIntent().getUriList(), arrayList)) {
            return (MediaItem[]) arrayList.stream().toArray(new Object());
        }
        return null;
    }

    private String getSharingAlbumDataKey() {
        String sharedAlbumSpaceId = getLaunchIntent().getSharedAlbumSpaceId();
        return new UriBuilder("location://sharing/albums/fileList").appendArg("id", sharedAlbumSpaceId).appendArg("groupId", getLaunchIntent().getSharedAlbumGroupId()).appendArg("isNewItemUpdated", true).build();
    }

    private String getSharingDataKey() {
        return ArgumentsUtil.appendArgs("location://sharing/albums", (String) null);
    }

    private String getSharingInvitationsDataKey() {
        return ArgumentsUtil.appendArgs("location://sharing/albums/invitations", (String) null);
    }

    private String getSharingStorageUseDataKey() {
        return ArgumentsUtil.appendArgs("location://sharing/albums/storageUse", (String) null);
    }

    private boolean isAddContentsToSharedAlbum() {
        return getLaunchIntent().isAddContentsToSharedAlbum();
    }

    private boolean isLaunchFamilyAlbum() {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) || !getLaunchIntent().isLaunchFamilyAlbum()) {
            return false;
        }
        return true;
    }

    private boolean isRefreshNotification() {
        return getLaunchIntent().isRefreshNotification();
    }

    private boolean isSharingAlbum() {
        return getLaunchIntent().isStartSharedDetailView();
    }

    private boolean isSharingInvitations() {
        return getLaunchIntent().isStartSharingInvitationsView();
    }

    private boolean isSharingStorageUse() {
        return getLaunchIntent().isStartSharingStorageUseView();
    }

    private boolean isSharings() {
        return getLaunchIntent().isStartSharedView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishActivityOnUiThread$7(String str) {
        Optional.ofNullable(getContext()).ifPresent(new m(str));
        this.mBlackboard.post("command://request_suicide", (Object) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaItem[] lambda$getItems$5(int i2) {
        return new MediaItem[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchFamilyAlbumView$1() {
        if (!MdeGroupHelper.isAccountSupportCreateFamilySharedAlbum(getContext()) || new MdeDatabase().hasFamilySharedAlbum()) {
            this.mBlackboard.post("command://MoveURL", "location://sharing/albums");
        } else {
            this.mBlackboard.post("command://MoveURL", MdeAlbumHelper.buildFamilyAlbumWelcomePageLocation(true, false));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchSharingChoice$2(int i2, MediaItem[] mediaItemArr, Context context) {
        int length = i2 - mediaItemArr.length;
        if (length > 0) {
            Toast.makeText(context, getFailedStringRes(length), 1).show();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchSharingChoice$3(int i2, MediaItem[] mediaItemArr) {
        Optional.ofNullable(getContext()).ifPresent(new o(this, i2, mediaItemArr));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchSharingChoice$4(int i2) {
        MediaItem[] items = getItems();
        if (items == null || items.length == 0) {
            finishActivityOnUiThread(getFailedStringRes(i2));
            return;
        }
        ThreadUtil.postponeOnUiThread(new h(this, i2, items));
        MdeSharingService.getInstance().connectSessionAsync(2, (ConnectListener) null);
        this.mBlackboard.publish("data://user/selected", items);
        this.mBlackboard.post("command://MoveURL", "location://sharing/choice");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onNavigatorCreated$0() {
        if (isSharings()) {
            loadSharings();
        } else if (isSharingAlbum() && checkSpaceExist(getLaunchIntent().getSharedAlbumSpaceId())) {
            loadSharingAlbum();
        } else if (isSharingInvitations()) {
            loadSharingInvitations();
        } else if (isSharingStorageUse()) {
            loadSharingStorageUse();
        } else if (isLaunchFamilyAlbum()) {
            launchFamilyAlbumView();
        } else if (isAddContentsToSharedAlbum()) {
            launchSharingChoice();
        }
    }

    private void launchFamilyAlbumView() {
        SimpleThreadPool.getInstance().execute(new k(this, 1));
    }

    private void launchSharingChoice() {
        if (!MdeSharingService.getInstance().isServiceSupported()) {
            finishActivityOnUiThread((int) R.string.can_not_add_to_shared_album_from_that_app);
            return;
        }
        int size = getLaunchIntent().getUriList().size();
        if (size == 0) {
            finishActivityOnUiThread((int) R.string.select_at_least_one_item);
        } else {
            SimpleThreadPool.getInstance().execute(new l(this, size));
        }
    }

    private void loadSharingAlbum() {
        this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(getSharingAlbumDataKey());
        if (isRefreshNotification()) {
            MdeNotificationManager.getInstance(getContext()).refreshNotification();
        }
    }

    private void loadSharingInvitations() {
        this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(getSharingInvitationsDataKey());
        if (isRefreshNotification()) {
            MdeNotificationManager.getInstance(getContext()).refreshNotification();
        }
    }

    private void loadSharingStorageUse() {
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(getSharingStorageUseDataKey());
            if (isRefreshNotification()) {
                MdeNotificationManager.getInstance(getContext()).refreshNotification();
                return;
            }
            return;
        }
        loadSharings();
    }

    private void loadSharings() {
        this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(getSharingDataKey());
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS && isRefreshNotification()) {
            MdeNotificationManager.getInstance(getContext()).refreshNotification();
        }
    }

    public void onDestroy() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.close();
            this.mMediaData = null;
        }
    }

    public void onNavigatorCreated() {
        if (Features.isEnabled(Features.IS_UPSM)) {
            finishActivityOnUiThread(SeApiCompat.naturalizeText(getContext().getString(R.string.unable_in_max_power_saving, new Object[]{getContext().getString(R.string.shared_album)})));
            return;
        }
        if (Features.isEnabled(Features.SUPPORT_AUTO_BLOCKER)) {
            Features features = Features.IS_SHARED_ALBUM_BLOCKED;
            Features.recycle(features);
            if (Features.isEnabled(features)) {
                finishActivityOnUiThread((int) R.string.could_not_open_shared_album_because_auto_blocker_is_on);
                return;
            }
        }
        ThreadUtil.postOnBgThread(new k(this, 0));
    }

    public void onSharingsDataLoaded(Object obj, Bundle bundle) {
        MediaData mediaData;
        if (!this.mLaunched && (mediaData = this.mMediaData) != null) {
            this.mBlackboard.post("command://MoveURL", mediaData.getLocationReference());
            this.mLaunched = true;
        }
    }

    private void finishActivityOnUiThread(String str) {
        ThreadUtil.postOnUiThread(new e(1, this, str));
    }
}
