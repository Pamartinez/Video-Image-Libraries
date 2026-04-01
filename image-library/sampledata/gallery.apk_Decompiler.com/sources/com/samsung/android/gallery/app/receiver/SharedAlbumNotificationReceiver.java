package com.samsung.android.gallery.app.receiver;

import A.a;
import Ba.h;
import H.b;
import S3.d;
import Y3.C0414a;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeNotificationManager;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.sec.android.gallery3d.R;
import java.util.Map;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedAlbumNotificationReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public String mGroupId;
    private String mGroupName;
    private String mSharedAlbumId;
    private String mSharedAlbumName;

    private void handleDeletePostPush() {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : ACTION_SHARE_DELETE_ITEM_PUSH");
        if (isAlreadyOnSharingPictures()) {
            Blackboard.publishGlobal("command://SharingPicturesItemsSync", this.mSharedAlbumId);
        }
        requestUsageSync();
    }

    private void handleDisableWebLink() {
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
            Blackboard.getApplicationInstance().post("global://sharing/setting/dataChanged", new UriBuilder("data://webLink").appendArg("groupId", this.mGroupId).appendArg(BundleKey.SPACE_ID, this.mSharedAlbumId).appendArg("space_name", this.mSharedAlbumName).build());
            Log.sh("SharedAlbumNotificationReceiver", "handleDisableWebLink");
        }
    }

    private void handleEnableWebLink(Intent intent) {
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
            String stringExtra = intent.getStringExtra("weblink_url");
            long longExtra = intent.getLongExtra("weblink_expired_time", 0);
            if (stringExtra == null || longExtra <= 0) {
                Log.she("SharedAlbumNotificationReceiver", "handleEnableWebLink, invalid data" + Logger.v(a.f("expired=", longExtra), Logger.getEncodedString(stringExtra)));
                return;
            }
            Blackboard.getApplicationInstance().post("global://sharing/setting/dataChanged", new UriBuilder("data://webLink").appendArg("space_weblink_url", stringExtra).appendArg("space_weblink_expiry", longExtra).appendArg("groupId", this.mGroupId).appendArg(BundleKey.SPACE_ID, this.mSharedAlbumId).appendArg("space_name", this.mSharedAlbumName).build());
            Log.sh("SharedAlbumNotificationReceiver", "handleEnableWebLink {" + longExtra + '}');
        }
    }

    private void handleGroupDelegateAuthorityPush(Context context, Intent intent) {
        if (PreferenceFeatures.OneUi41.SHARING_LEADER_AUTHORITY_DELEGATION) {
            ThreadUtil.postOnBgThread(new C0414a(this, context, intent, 1));
            updateSpaceList();
        }
    }

    private void handleGroupDeletedPush(Context context, String str) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : " + str);
        if (isAlreadyOnSharingPictures()) {
            Utils.showShortToast(context, (int) R.string.unable_to_view_this_album_removed_by_its_creator);
            return;
        }
        MdeNotificationManager.getInstance(context).notifySharedAlbumDeleted(this.mGroupId, this.mGroupName, this.mSharedAlbumName);
        requestUsageSync();
    }

    private void handleGroupInvitationPush(Context context, Intent intent) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : GROUP_INVITE_PUSH");
        String stringExtra = intent.getStringExtra("group_requester_name");
        boolean z = true;
        boolean z3 = true;
        for (Map.Entry<String, Blackboard> value : Blackboard.getBlackboardMap().entrySet()) {
            Blackboard blackboard = (Blackboard) value.getValue();
            Object read = blackboard.read("location://variable/currentv1");
            if (read != null) {
                String obj = read.toString();
                if (obj.startsWith("location://sharing/albums")) {
                    z = false;
                }
                if (hasSharingInvitation(obj)) {
                    Log.d("SharedAlbumNotificationReceiver", "current view is sharing invitations");
                    new RequestSharedAlbumCmd().execute((EventContext) BlackboardUtils.readActivity(blackboard), RequestCmdType.REQUEST_INVITATION_SYNC);
                    String str = (String) blackboard.read("lifecycle://last_activity_lifecycle");
                    if (str != null && (str.equals("lifecycle://on_activity_create") || str.equals("lifecycle://on_activity_resume"))) {
                        z3 = false;
                    }
                }
                z3 = true;
            }
        }
        if (z) {
            MdeAlbumHelper.updateLastUpdatedTime();
        }
        PreferenceCache.SharedNewNotificationBadge.setBoolean(z3);
        if (z3 && SettingPreference.SharedNotification.isEnabled() && SettingPreference.SharedNotificationAlbum.isEnabled()) {
            MdeNotificationManager.getInstance(context).notifySharedAlbumInvitation(this.mGroupId, this.mGroupName, stringExtra);
        }
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            Blackboard.postEventGlobal(EventMessage.obtain(6002));
        }
        updateNewBadgeInMenuTab();
        requestBadgeData();
    }

    private void handleGroupInvitationResultPush(final Context context, final String str) {
        if (this.mGroupId == null) {
            Log.w("SharedAlbumNotificationReceiver", "onReceive failed with null group-id");
            return;
        }
        Log.d("SharedAlbumNotificationReceiver", "onReceive : " + str);
        MdeNotificationManager.getInstance(context).cancel(MediaItemMde.toUid(this.mGroupId));
        if (!NetworkUtils.isNetworkConnected(context)) {
            Utils.showShortToast(context, (int) R.string.sharing_invitations_check_your_network);
            return;
        }
        PreferenceCache.SharedNewNotificationBadge.setBoolean(false);
        Blackboard.postEventGlobal(EventMessage.obtain(6002));
        MdeSharingService.getInstance().connectSessionAsync(2, new ConnectListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onSuccess$0(String str, Context context, Blackboard blackboard) {
                if ("com.samsung.android.intent.action.SHARED_INVITE_DECLINED".equals(str)) {
                    MdeGroupHelper.requestGroupInvitationRejection(context, SharedAlbumNotificationReceiver.this.mGroupId);
                } else {
                    MdeGroupHelper.requestGroupInvitationAcceptance(blackboard, context, SharedAlbumNotificationReceiver.this.mGroupId);
                }
            }

            public void onSuccess() {
                Blackboard.getBlackboardMap().values().stream().filter(new d(19)).findFirst().ifPresent(new a(this, str, context));
            }

            public void onFailure(int i2) {
            }
        });
    }

    private void handleGroupMemberJoinedPush(Context context, Intent intent) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : GROUP_ACCEPT_INVITE_PUSH");
        ThreadUtil.postOnBgThread(new C0414a(this, context, intent, 4));
        refreshMember(context, this.mGroupId);
    }

    private void handleGroupMemberLeftPush(Context context, Intent intent) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : GROUP_MEMBER_DELETE_PUSH");
        ThreadUtil.postOnBgThread(new C0414a(this, context, intent, 5));
        updateSpaceList();
    }

    private void handleGroupMemberRemovedPush(Context context, Intent intent) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : GROUP_FORCE_MEMBER_DELETE_PUSH");
        String stringExtra = intent.getStringExtra("my_id");
        String stringExtra2 = intent.getStringExtra("group_requester_id");
        Log.d("SharedAlbumNotificationReceiver", "myGuid=" + stringExtra + " requester=" + stringExtra2);
        if (stringExtra == null || stringExtra.isEmpty() || !stringExtra.equals(stringExtra2)) {
            ThreadUtil.postOnBgThread(new C0414a(this, context, intent, 2));
        } else {
            Log.d("SharedAlbumNotificationReceiver", "this is a requester");
        }
    }

    private void handleNewAlbumPush(Context context, Intent intent) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : ACTION_SHARE_ADD_SPACE_PUSH");
        if (!SettingPreference.SharedNotification.isEnabled()) {
            Log.d("SharedAlbumNotificationReceiver", "shared notification setting is disabled.");
        } else if (!SettingPreference.SharedNotificationAlbum.isEnabled()) {
            Log.d("SharedAlbumNotificationReceiver", "KEY_NEW_ALBUMS push notification setting is disabled.");
        } else {
            ThreadUtil.postOnBgThread(new C0414a(this, context, intent, 0));
            updateNewBadgeInMenuTab();
            requestBadgeData();
            requestUsageSync();
        }
    }

    private void handleNewPostPush(Context context, Intent intent) {
        Log.d("SharedAlbumNotificationReceiver", "onReceive : ACTION_SHARE_ADD_ITEM_PUSH");
        if (isAlreadyOnSharingPictures()) {
            Blackboard.publishGlobal("command://SharingPicturesItemsSync", this.mSharedAlbumId);
        } else if (!SettingPreference.SharedNotification.isEnabled()) {
            Log.d("SharedAlbumNotificationReceiver", "shared notification setting is disabled.");
        } else if (!SettingPreference.SharedNotificationPosting.isEnabled()) {
            Log.d("SharedAlbumNotificationReceiver", "KEY_NEW_POSTS push notification setting is disabled.");
        } else {
            ThreadUtil.postOnBgThread(new C0414a(this, context, intent, 3));
            updateNewBadgeInMenuTab();
            requestBadgeData();
            requestUsageSync();
        }
    }

    private void handleOnePersonAlbumDeleteNotice(Context context, Intent intent) {
        if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            String stringExtra = intent.getStringExtra("group_expire_time");
            if (stringExtra == null) {
                Log.d("SharedAlbumNotificationReceiver", "expiryTime data is null");
                return;
            }
            long parseLong = Long.parseLong(stringExtra);
            Log.sh("SharedAlbumNotificationReceiver", "handleOnePersonAlbumDeleteNotice" + Logger.v(Long.valueOf(parseLong)));
            ThreadUtil.postOnBgThread(new b(this, context, parseLong, 3));
        }
    }

    private void handleOpenOnePersonAlbum(Context context) {
        Log.sh("SharedAlbumNotificationReceiver", "handleOpenOnePersonAlbum");
        if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            MdeNotificationManager.getInstance(context).cancel(MediaItemMde.toUid(this.mGroupId));
            if (!SdkConfig.atLeast(SdkConfig.GED.S)) {
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            }
            if (!isAlreadyOnSharingPictures()) {
                context.startActivity(MdeNotificationManager.getInstance(context).createOpenSharedAlbumDetailViewIntent(this.mSharedAlbumId, this.mGroupId));
            }
        }
    }

    private void handleRemoveNotification(Context context) {
        Log.sh("SharedAlbumNotificationReceiver", "handleRemoveNotification");
        MdeNotificationManager.getInstance(context).cancel(MediaItemMde.toUid(this.mGroupId));
    }

    private void handleSharedGroupDetailPush(Context context) {
        Intent externalGroupDetailIntent = MdeGroupHelper.getExternalGroupDetailIntent(context, this.mGroupId, this.mSharedAlbumName);
        externalGroupDetailIntent.addFlags(268435456);
        context.startActivity(externalGroupDetailIntent);
    }

    private boolean hasSharingInvitation(String str) {
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS || !"location://sharing/albums".equals(str)) {
            return "location://sharing/albums/invitations".equals(str);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleGroupDelegateAuthorityPush$7(Context context, Intent intent) {
        MdeNotificationManager.getInstance(context).notifyGroupDelegateAuthorityInfo(this.mGroupId, this.mGroupName, intent.getStringExtra("group_requester_name"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleGroupMemberJoinedPush$2(Context context, Intent intent) {
        MdeNotificationManager.getInstance(context).notifyMemberUpdates(MdeNotificationManager.SharedAlbumPushType.MEMBER_JOINED, this.mGroupId, this.mGroupName, intent.getStringExtra("group_accepted_member_name"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleGroupMemberLeftPush$1(Context context, Intent intent) {
        MdeNotificationManager.getInstance(context).notifyMemberUpdates(MdeNotificationManager.SharedAlbumPushType.MEMBER_LEFT, this.mGroupId, this.mGroupName, intent.getStringExtra("member_name"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleGroupMemberRemovedPush$0(Context context, Intent intent) {
        MdeNotificationManager.getInstance(context).notifyMemberUpdates(MdeNotificationManager.SharedAlbumPushType.FORCE_MEMBER_DELETE, this.mGroupId, this.mGroupName, intent.getStringExtra("member_name"));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleNewAlbumPush$5(Context context, Intent intent) {
        MdeNotificationManager.getInstance(context).notifySharedAlbumInfo(MdeNotificationManager.SharedAlbumPushType.NEW_ALBUM, this.mGroupId, this.mSharedAlbumId, this.mSharedAlbumName, intent.getStringExtra("space_owner_name"), intent.getStringExtra("space_owner_thumbnail_local_path"), "", 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleNewPostPush$3(Context context, Intent intent) {
        MdeNotificationManager.getInstance(context).notifySharedAlbumInfo(MdeNotificationManager.SharedAlbumPushType.NEW_POST, this.mGroupId, this.mSharedAlbumId, this.mSharedAlbumName, intent.getStringExtra("item_owner_name"), intent.getStringExtra("last_item_thumbnail_local_path"), intent.getStringExtra("item_metadata"), intent.getIntExtra(BundleKey.ITEM_COUNT, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleOnePersonAlbumDeleteNotice$6(Context context, long j2) {
        MdeNotificationManager.getInstance(context).notifyOnePersonAlbumDeleteNotice(this.mGroupId, this.mGroupName, j2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isAlreadyOnSharingPictures$8(boolean[] zArr, String str, Blackboard blackboard) {
        String str2 = (String) blackboard.read("location://variable/currentv1");
        if (str2 != null && str2.startsWith("location://sharing/albums/fileList")) {
            String argValue = ArgumentsUtil.getArgValue(str2, "groupId", (String) null);
            Log.d("SharedAlbumNotificationReceiver", "isAlreadyOnSharingPictures groupId=" + argValue);
            if (!TextUtils.isEmpty(argValue) && argValue.equals(this.mGroupId)) {
                Log.d("SharedAlbumNotificationReceiver", "do not need to show notification on current sharing pictures view");
                zArr[0] = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateNewBadgeInMenuTab$4(boolean[] zArr, String str, Blackboard blackboard) {
        String str2 = (String) blackboard.read("location://variable/currentv1");
        if (!TextUtils.isEmpty(str2) && str2.startsWith("location://sharing/albums")) {
            zArr[0] = false;
        }
    }

    private void refreshMember(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("com.samsung.android.mobileservice.ACTION_DETAIL_REFRESH");
        intent.putExtra(BundleKey.GROUP_ID, str);
        context.sendBroadcast(intent);
    }

    private void requestUsageSync() {
        Blackboard.postEventGlobal(EventMessage.obtain(6017));
    }

    private void updateNewBadgeInMenuTab() {
        boolean[] zArr = {true};
        Blackboard.getBlackboardMap().forEach(new h(21, zArr));
        if (zArr[0]) {
            MdeAlbumHelper.updateLastUpdatedTime();
        }
    }

    private void updateSpaceList() {
        String str = this.mGroupId;
        if (str == null) {
            return;
        }
        if (str.startsWith("UNM1") || MdeGroupHelper.isSAFamilyGroup(this.mGroupId)) {
            MdeSharingHelper.requestSpaceListSync(false, (BiConsumer<Integer, String>) null);
        }
    }

    public boolean checkDisabled() {
        if (!SettingPreference.SharedNotification.isEnabled()) {
            Log.d("SharedAlbumNotificationReceiver", "shared notification setting is disabled.");
            return true;
        } else if (SettingPreference.SharedNotificationMember.isEnabled()) {
            return false;
        } else {
            Log.d("SharedAlbumNotificationReceiver", "member updates push notification setting is disabled.");
            return true;
        }
    }

    public boolean isAlreadyOnSharingPictures() {
        boolean[] zArr = {false};
        Blackboard.getBlackboardMap().forEach(new A9.a(11, this, zArr));
        return zArr[0];
    }

    public boolean isNotSupported(Context context, String str) {
        if (Features.isEnabled(Features.SUPPORT_AUTO_BLOCKER) && Features.isEnabled(Features.IS_SHARED_ALBUM_BLOCKED)) {
            return true;
        }
        if (!Features.isEnabled(Features.IS_UPSM)) {
            return !MdeSharingService.getInstance().computeShareServiceAvailable();
        }
        if ("com.samsung.android.intent.action.ACTION_SHARED_GROUP_DETAIL".equals(str)) {
            Utils.showShortToast(context, SeApiCompat.naturalizeText(context.getString(R.string.unable_in_max_power_saving, new Object[]{context.getString(R.string.shared_album)})));
        }
        return true;
    }

    public boolean isWrongIntent(Intent intent) {
        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
            return false;
        }
        Log.d("SharedAlbumNotificationReceiver", "onReceive : intent is null or action value is invalid.");
        return true;
    }

    public void onReceive(Context context, Intent intent) {
        if (!isWrongIntent(intent)) {
            String action = intent.getAction();
            if (!onReceiveServiceStatusChange(action)) {
                if (isNotSupported(context, action)) {
                    Log.d("SharedAlbumNotificationReceiver", "onReceive : Do not support shared album");
                    return;
                }
                this.mGroupId = intent.getStringExtra(BundleKey.GROUP_ID);
                this.mGroupName = intent.getStringExtra(com.samsung.android.sdk.mobileservice.social.group.BundleKey.GROUP_NAME);
                this.mSharedAlbumId = intent.getStringExtra(BundleKey.SPACE_ID);
                this.mSharedAlbumName = intent.getStringExtra("space_name");
                if ("com.samsung.android.intent.action.SHARED_INVITE_DECLINED".equals(action) || "com.samsung.android.intent.action.SHARED_INVITE_ACCEPTED".equals(action)) {
                    handleGroupInvitationResultPush(context, action);
                } else if ("com.samsung.android.mobileservice.social.ACTION_SHARE_ADD_SPACE_PUSH".equals(action)) {
                    handleNewAlbumPush(context, intent);
                } else if ("com.samsung.android.mobileservice.social.ACTION_SHARE_ADD_ITEM_PUSH".equals(action)) {
                    handleNewPostPush(context, intent);
                } else if ("com.samsung.android.mobileservice.social.ACTION_SHARE_DELETE_ITEM_PUSH".equals(action)) {
                    handleDeletePostPush();
                } else if ("com.samsung.android.mobileservice.social.ACTION_GROUP_INVITE_PUSH".equals(action)) {
                    handleGroupInvitationPush(context, intent);
                } else if ("com.samsung.android.mobileservice.ACTION_OPEN_ONE_PERSON_ALBUM".equals(action)) {
                    handleOpenOnePersonAlbum(context);
                } else if ("com.samsung.android.mobileservice.ACTION_REMOVE_NOTIFICATION".equals(action)) {
                    handleRemoveNotification(context);
                } else if (!checkDisabled()) {
                    if ("com.samsung.android.mobileservice.social.ACTION_GROUP_ACCEPT_INVITE_PUSH".equals(action)) {
                        handleGroupMemberJoinedPush(context, intent);
                    } else if ("com.samsung.android.mobileservice.social.ACTION_GROUP_MEMBER_DELETE_PUSH".equals(action)) {
                        handleGroupMemberLeftPush(context, intent);
                    } else if ("com.samsung.android.mobileservice.social.ACTION_GROUP_FORCE_MEMBER_DELETE_PUSH".equals(action)) {
                        handleGroupMemberRemovedPush(context, intent);
                    } else if ("com.samsung.android.mobileservice.social.ACTION_GROUP_DELETE_PUSH".equals(action) || "com.samsung.android.mobileservice.social.ACTION_SA_FAMILY_GROUP_DELETE_WITH_FEATURE_ID_PUSH".equals(action)) {
                        handleGroupDeletedPush(context, action);
                    } else if ("com.samsung.android.intent.action.ACTION_SHARED_GROUP_DETAIL".equals(action)) {
                        handleSharedGroupDetailPush(context);
                    } else if ("com.samsung.android.mobileservice.social.ACTION_SHARE_ENABLE_WEBLINK".equals(action)) {
                        handleEnableWebLink(intent);
                    } else if ("com.samsung.android.mobileservice.social.ACTION_SHARE_DISABLE_WEBLINK".equals(action)) {
                        handleDisableWebLink();
                    } else if ("com.samsung.android.mobileservice.social.ACTION_GROUP_EXPIRATION_NOTICE_PUSH".equals(action) || "com.samsung.android.mobileservice.social.ACTION_SA_FAMILY_GROUP_EXPIRATION_NOTICE_WITH_FEATURE_ID_PUSH".equals(action)) {
                        handleOnePersonAlbumDeleteNotice(context, intent);
                    } else if ("com.samsung.android.mobileservice.social.ACTION_GROUP_DELEGATE_AUTHORITY_PUSH".equals(action)) {
                        handleGroupDelegateAuthorityPush(context, intent);
                    }
                }
            }
        }
    }

    public boolean onReceiveServiceStatusChange(String str) {
        if (!"com.samsung.android.mobileservice.ACTION_SERVICE_STATUS_CHANGED".equals(str)) {
            return false;
        }
        Log.d("SharedAlbumNotificationReceiver", "onReceive : ACTION_SERVICE_STATUS_CHANGED");
        MdeSharingService.getInstance().clearShareServiceStatus();
        return true;
    }

    public void requestBadgeData() {
        Blackboard.publishGlobal(CommandKey.DATA_REQUEST("data://badge/sharings"), (Object) null);
        Blackboard.publishGlobal(CommandKey.DATA_REQUEST("data://badge/bottom_menu"), (Object) null);
    }
}
