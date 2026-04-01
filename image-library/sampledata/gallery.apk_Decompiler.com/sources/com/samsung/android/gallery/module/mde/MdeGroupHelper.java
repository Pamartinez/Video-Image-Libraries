package com.samsung.android.gallery.module.mde;

import A4.C0375j;
import A4.H;
import Ad.j;
import D9.a;
import D9.b;
import D9.c;
import D9.d;
import Fa.C0571z;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.R$plurals;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mdebase.constants.MdeConstants;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import com.samsung.android.sdk.mobileservice.social.group.GroupApi;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;
import com.samsung.android.sdk.mobileservice.social.group.GroupMember;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationListResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupMemberResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupResult;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.social.social.GroupDetailRequest;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeGroupHelper {
    private static void cancelSharedNotification(List<GroupInvitationListResult.GroupInvitation> list) {
        Object obj;
        StringBuilder sb2 = new StringBuilder("cancelSharedNotification");
        if (list == null) {
            obj = "null";
        } else {
            obj = Integer.valueOf(list.size());
        }
        sb2.append(Logger.v(obj));
        Log.sh("SharedGroupHelper", sb2.toString());
        if (list != null) {
            for (GroupInvitationListResult.GroupInvitation groupId : list) {
                String groupId2 = groupId.getGroupId();
                if (!TextUtils.isEmpty(groupId2)) {
                    MdeNotificationManager.getInstance(AppResources.getAppContext()).cancel(MediaItemMde.toUid(groupId2));
                }
            }
        }
    }

    public static String getCountString(Context context, MediaItem mediaItem, int i2) {
        if (i2 < 0) {
            i2 = MediaItemMde.getMemberCount(mediaItem);
        }
        if (isSAFamilyGroup(mediaItem)) {
            return context.getString(R$string.group_name_family);
        }
        return context.getResources().getQuantityString(R$plurals.number_of_members, i2, new Object[]{Integer.valueOf(i2)});
    }

    public static Intent getExternalGroupDetailIntent(Context context, String str, String str2) {
        return new OpenSessionApi().getIntentForGroupDetail(context, new GroupDetailRequest.Builder("22n6hzkam0", str).setFeatureId(32).setSpaceName(str2).build());
    }

    private static Intent getGroupDetailIntent(String str) {
        Intent intent = new Intent();
        intent.setAction("com.samsung.android.mobileservice.action.ACTION_GROUP_DETAIL");
        intent.putExtra(BundleKey.GROUP_ID, str);
        return intent;
    }

    public static GroupApi.InvitationRequest getInvitationRequest(int i2, HashMap<Integer, Object> hashMap) {
        ArrayList arrayList;
        if (i2 != 0 && i2 != 1 && i2 != 3) {
            return null;
        }
        Object[] objArr = (Object[]) hashMap.get(Integer.valueOf(i2));
        ArrayList arrayList2 = (ArrayList) objArr[0];
        if (((ArrayList) objArr[1]).size() != 0) {
            arrayList = (ArrayList) objArr[1];
        } else {
            arrayList = new ArrayList();
        }
        return new GroupApi.InvitationRequest("", i2, arrayList2, arrayList);
    }

    private static HashMap<String, String> getValidMembers(List<GroupMember> list) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (list != null && !list.isEmpty()) {
            for (GroupMember next : list) {
                if (MdeConstants.isValidMember(next.getStatus())) {
                    hashMap.put(next.getId(), next.getName());
                }
            }
        }
        return hashMap;
    }

    private static boolean hasNoFamilyAlbum(ArrayList<MediaItem> arrayList) {
        if (arrayList == null || !arrayList.stream().filter(new C0571z(4)).noneMatch(new C0375j(18))) {
            return false;
        }
        return true;
    }

    public static boolean isAccountSupportCreateFamilySharedAlbum(Context context) {
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) || !SamsungAccountManager.getInstance().reload().hasAccount() || !SamsungAccountManager.getInstance().supportCreationFamilySharedAlbum(context)) {
            return false;
        }
        return true;
    }

    public static boolean isLocalGroup(String str) {
        if (str == null || !str.startsWith("UNM1")) {
            return false;
        }
        return true;
    }

    public static boolean isNeedToShowNoticeProfileSharingDialog() {
        return PreferenceCache.SharedProfileShareNotice.getBoolean();
    }

    public static boolean isSAFamilyGroup(MediaItem mediaItem) {
        return isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem));
    }

    public static boolean isSAFamilyGroupId(String str) {
        if (str == null || !str.startsWith(Group.GROUP_TYPE_SA_FAMILY_GROUP)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasNoFamilyAlbum$11(MediaItem mediaItem) {
        if (MediaItemMde.isInvitation(mediaItem) || !isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestFamilyGroupCreation$3(BiConsumer biConsumer, GroupResult groupResult) {
        String str;
        String str2;
        int code = groupResult.getStatus().getCode();
        if (groupResult.getResult() != null) {
            Group result = groupResult.getResult();
            str2 = result.getGroupId();
            str = result.getGroupName();
        } else {
            str2 = null;
            str = null;
        }
        biConsumer.accept(Integer.valueOf(code), new String[]{str2, str});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestGroupCreationForShare$6(String str, BiConsumer biConsumer, GroupInvitationResult groupInvitationResult) {
        String str2;
        String str3 = null;
        if (groupInvitationResult.getGroup() != null) {
            Group group = groupInvitationResult.getGroup();
            String groupId = group.getGroupId();
            String groupName = group.getGroupName();
            if (group.getInvitationLink() != null) {
                str3 = group.getInvitationLink().getUrl();
            }
            String str4 = str3;
            str3 = groupId;
            str2 = str4;
            str = groupName;
        } else {
            str2 = null;
        }
        biConsumer.accept(Integer.valueOf(groupInvitationResult.getStatus().getCode()), new String[]{str3, str, str2, groupInvitationResult.getDisplayMessage()});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestGroupInvitationAcceptance$1(Blackboard blackboard, Context context, BooleanResult booleanResult) {
        int i2;
        blackboard.postEvent(EventMessage.obtain(6005));
        int code = booleanResult.getStatus().getCode();
        Log.sh("SharedGroupHelper", "requestGroupInvitationAcceptance result" + Logger.v(Integer.valueOf(code)));
        if (MdeResultCode.isSuccess(code)) {
            if (booleanResult.getResult()) {
                i2 = R$string.invitation_joined;
            } else {
                i2 = R$string.invitation_expired;
            }
        } else if (code != 115) {
            MdeAlbumHelper.handleMdeFailResultCode(context, code);
            return;
        } else if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            i2 = R$string.youre_already_responded_to_this_invitation;
        } else {
            i2 = R$string.sharing_invitations_already_accepted;
        }
        Utils.showShortToast(context, i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestGroupInvitationRejection$2(Context context, BooleanResult booleanResult) {
        int i2;
        int code = booleanResult.getStatus().getCode();
        Log.sh("SharedGroupHelper", "requestGroupInvitationRejection result" + Logger.v(Integer.valueOf(code)));
        if (MdeResultCode.isSuccess(code)) {
            if (booleanResult.getResult()) {
                i2 = R$string.invitation_declined;
            } else {
                i2 = R$string.invitation_expired;
            }
        } else if (code != 115) {
            MdeAlbumHelper.handleMdeFailResultCode(context, code);
            return;
        } else if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            i2 = R$string.youre_already_responded_to_this_invitation;
        } else {
            i2 = R$string.sharing_invitations_already_accepted;
        }
        Utils.showShortToast(context, i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestGroupMemberSync$10(GroupMemberResult groupMemberResult) {
        int code = groupMemberResult.getStatus().getCode();
        if (MdeResultCode.isSuccess(code)) {
            Blackboard.postGlobal("command://SharingPicturesGroupMemberSynced", getValidMembers(groupMemberResult.getGroupMembers()));
        } else if (!MdeResultCode.isGdprError(code)) {
            MdeAlbumHelper.handleMdeFailResultCode(AppResources.getAppContext(), code);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestLocalGroupCreation$5(BiConsumer biConsumer, GroupInvitationResult groupInvitationResult) {
        String str;
        String str2;
        int code = groupInvitationResult.getStatus().getCode();
        if (groupInvitationResult.getGroup() != null) {
            Group group = groupInvitationResult.getGroup();
            str = group.getGroupId();
            str2 = group.getGroupName();
        } else {
            str = null;
            str2 = null;
        }
        biConsumer.accept(Integer.valueOf(code), new String[]{str, str2, groupInvitationResult.getDisplayMessage()});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestMyInvitationList$0(GroupInvitationListResult groupInvitationListResult) {
        cancelSharedNotification(groupInvitationListResult.getInvitationList());
        CommonResultStatus status = groupInvitationListResult.getStatus();
        int code = status.getCode();
        if (!MdeResultCode.isSuccess(code)) {
            if (MdeResultCode.isPermissionDenied(code, status.getMessage())) {
                MdeAlbumHelper.sSemsPermissionDenied = true;
            }
            MdeAlbumHelper.handleMdeFailResultCode(AppResources.getAppContext(), code);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$shareGroupInvitationLink$7(Context context, String str, String str2) {
        Intent intentForShareViaInvitationLink = new OpenSessionApi().getIntentForShareViaInvitationLink(context, Integer.parseInt("32"), str, str2);
        intentForShareViaInvitationLink.setFlags(268435456);
        context.startActivity(intentForShareViaInvitationLink, (Bundle) null);
    }

    public static void requestFamilyGroupCreation(String str, BiConsumer<Integer, String[]> biConsumer) {
        GroupApi.FamilyGroupRequest familyGroupRequest = new GroupApi.FamilyGroupRequest(str);
        Log.sh("SharedGroupHelper", "requestFamilyGroupCreation groupName : " + familyGroupRequest.getGroupName());
        MdeSharingService.getInstance().requestFamilyGroupCreation(familyGroupRequest, new c(biConsumer, 0));
    }

    public static void requestFamilyGroupDeletion(String str, Consumer<Integer> consumer) {
        Log.sh("SharedGroupHelper", "requestFamilyGroupDeletion groupId : " + str);
        MdeSharingService.getInstance().requestFamilyGroupDeletion(str, new a(1, consumer));
    }

    public static void requestGroupCreationForShare(String str, BiConsumer<Integer, String[]> biConsumer) {
        MdeSharingService.getInstance().requestGroupCreationForShare(new GroupApi.GroupRequest(str, "UNM1", true, GroupAuthority.AuthorityType.INVALID), new H(7, (Object) biConsumer, str));
    }

    public static void requestGroupInvitationAcceptance(Blackboard blackboard, Context context, String str) {
        Log.sh("SharedGroupHelper", "requestInvitationAcceptance called");
        MdeSharingService.getInstance().requestGroupInvitationAcceptance(str, new H(6, (Object) blackboard, (Object) context));
    }

    public static void requestGroupInvitationRejection(Context context, String str) {
        Log.sh("SharedGroupHelper", "requestInvitationRejection called");
        MdeSharingService.getInstance().requestGroupInvitationRejection(str, new b(context, 0));
    }

    public static void requestGroupMemberSync(String str) {
        MdeSharingService.getInstance().requestGroupMemberListSync(str, new j(9));
    }

    public static void requestLocalGroupCreation(String str, int i2, HashMap<Integer, Object> hashMap, BiConsumer<Integer, String[]> biConsumer) {
        MdeSharingService.getInstance().requestGroupCreation(new GroupApi.GroupRequest(str, "UNM1", (Uri) null, (String) null), getInvitationRequest(i2, hashMap), new c(biConsumer, 1));
    }

    public static int requestLocalGroupDeletion(String str, IntConsumer intConsumer) {
        return MdeSharingService.getInstance().requestLocalGroupDeletion(str, new d(intConsumer, 1));
    }

    public static void requestLocalGroupLeave(String str, IntConsumer intConsumer) {
        MdeSharingService.getInstance().requestLocalGroupLeave(str, new d(intConsumer, 0));
    }

    public static void requestMyInvitationList() {
        MdeSharingService.getInstance().requestMyInvitationList(new j(8));
    }

    public static void requestShowGroupDetail(Context context, String str, String str2) {
        Intent intent;
        if (TextUtils.isEmpty(str)) {
            Log.w("SharedGroupHelper", "groupId is null !!");
            return;
        }
        MdeNotificationManager.getInstance(context).cancel(MediaItemMde.toUid(str));
        if (str.startsWith("UNM1")) {
            intent = getExternalGroupDetailIntent(context, str, str2);
        } else {
            intent = getGroupDetailIntent(str);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w("SharedGroupHelper", "Activity Not Found !!!");
        }
    }

    public static void saveNoticeProfileSharingDialogPreferenceState(boolean z) {
        PreferenceCache.SharedProfileShareNotice.setBoolean(z);
    }

    public static void shareGroupInvitationLink(Context context, String str, String str2) {
        ThreadUtil.postOnUiThread(new A9.b(context, str, str2, 11));
    }

    public static boolean supportFamilySharedAlbumCreation(Context context, ArrayList<MediaItem> arrayList) {
        if (!SdkConfig.atLeast(SdkConfig.SEM.S) || !hasNoFamilyAlbum(arrayList) || !isAccountSupportCreateFamilySharedAlbum(context)) {
            return false;
        }
        return true;
    }

    public static boolean isSAFamilyGroup(String str) {
        return Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) && isSAFamilyGroupId(str);
    }
}
