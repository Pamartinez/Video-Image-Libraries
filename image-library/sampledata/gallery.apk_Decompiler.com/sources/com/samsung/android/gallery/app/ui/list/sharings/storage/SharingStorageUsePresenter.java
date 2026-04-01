package com.samsung.android.gallery.app.ui.list.sharings.storage;

import A.a;
import R5.c;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.ISharingsStorageView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeConstants;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingStorageUsePresenter extends BaseListPresenter<ISharingsStorageView> {
    private final ContentObserver mContentObserver = new ContentObserver(ThreadUtil.createMainThreadHandler()) {
        public void onChange(boolean z, Uri uri) {
            StringCompat access$000 = SharingStorageUsePresenter.this.TAG;
            Log.sh(access$000, "onChange " + uri);
            SharingStorageUsePresenter.this.requestQuota();
            SharingStorageUsePresenter.this.updateFamilyItem();
        }
    };
    private MediaItem mFamilyItem;

    public SharingStorageUsePresenter(Blackboard blackboard, ISharingsStorageView iSharingsStorageView) {
        super(blackboard, iSharingsStorageView);
    }

    private int adjustDataPosition(int i2) {
        if (!existFamilySharedAlbum()) {
            return i2;
        }
        if (i2 < 0) {
            return 0;
        }
        return i2 + 1;
    }

    private String getDetailId(String str) {
        if (str == null) {
            return AnalyticsDetail.EVENT_DETAIL_STORAGE_USE_FAMILY_TRASH_ALBUM.toString();
        }
        if (MdeGroupHelper.isSAFamilyGroup(str)) {
            return AnalyticsDetail.EVENT_DETAIL_STORAGE_USE_FAMILY_SHARED_ALBUM.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_STORAGE_USE_SHARED_ALBUM.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$0(Object obj, Bundle bundle) {
        ((ISharingsStorageView) this.mView).updateMyQuotaValues(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createSubscriberList$1(Object obj, Bundle bundle) {
        ((ISharingsStorageView) this.mView).updateFamilyQuotaValues(obj);
    }

    private void registerContentObserver() {
        try {
            getContext().getContentResolver().registerContentObserver(MdeConstants.MDE_QUOTA_URI, true, this.mContentObserver);
        } catch (Exception e) {
            a.r(e, new StringBuilder("registerContentObserver failed e="), this.TAG);
        }
    }

    private void requestFamilyQuota() {
        new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_FAMILY_QUOTA);
    }

    private void requestMyQuota() {
        new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_MY_QUOTA);
    }

    private void unregisterContentObserver() {
        try {
            getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
        } catch (Exception e) {
            a.r(e, new StringBuilder("unregisterContentObserver failed e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void updateFamilyItem() {
        if (getMediaData() != null) {
            this.mFamilyItem = getMediaData().readByKey(Group.GROUP_TYPE_SA_FAMILY_GROUP);
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://SharingUpdateMyQuota", new c(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://SharingUpdateFamilyQuota", new c(this, 1)).setWorkingOnUI());
    }

    public boolean existFamilySharedAlbum() {
        if (this.mFamilyItem != null) {
            return true;
        }
        return false;
    }

    public MediaItem getFamilyItem() {
        return this.mFamilyItem;
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.storage_use);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 6017) {
            return super.handleEvent(eventMessage);
        }
        new RequestSharedAlbumCmd().execute(this, RequestCmdType.REQUEST_SYNC, RequestSync.Types.SpaceWithQuota);
        return true;
    }

    public void moveToTrash() {
        this.mBlackboard.post("command://MoveURL", new UriBuilder("location://trash").appendArg("has_family_album", existFamilySharedAlbum()).appendArg("index", 1).appendArg("editMode", true).build());
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_STORAGE_USE_ALBUM_SELECT, getDetailId((String) null));
    }

    public void notifyDataChanged(MediaData mediaData) {
        updateFamilyItem();
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        String groupId = MediaItemMde.getGroupId(mediaItem);
        this.mBlackboard.post("command://MoveURL", new UriBuilder("location://sharing/albums/fileList/storageUsage").appendArg("id", MediaItemMde.getSpaceId(mediaItem)).appendArg(Message.KEY_POSITION, adjustDataPosition(i2)).appendArg("groupId", groupId).appendArg("owner", MediaItemMde.isOwnedByMe(mediaItem)).build());
        this.mBlackboard.publish("data://albums/current", mediaItem);
        postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_STORAGE_USE_ALBUM_SELECT, getDetailId(groupId));
    }

    public void onViewCreate() {
        super.onViewCreate();
        registerContentObserver();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        unregisterContentObserver();
    }

    public void requestQuota() {
        requestMyQuota();
        if (existFamilySharedAlbum()) {
            requestFamilyQuota();
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((int) R.string.storage_use);
        setNavigationUpButton(toolbar);
        toolbar.setSubtitle((CharSequence) null);
        toolbar.setBackgroundColor(toolbar.getContext().getColor(R.color.default_fw_background));
    }
}
