package com.samsung.android.sdk.mobileservice.social.group;

import N2.j;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.SeMobileServiceSession;
import com.samsung.android.sdk.mobileservice.common.CommonConstants;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sdk.mobileservice.common.api.SeMobileServiceApi;
import com.samsung.android.sdk.mobileservice.common.exception.NotConnectedException;
import com.samsung.android.sdk.mobileservice.common.exception.NotSupportedApiException;
import com.samsung.android.sdk.mobileservice.common.result.BooleanResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;
import com.samsung.android.sdk.mobileservice.social.group.IGroupCoverImageDownloadingResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupInvitationResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupListResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupListWithInvitationResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupRequestResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IGroupSyncResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.IMemberListResultCallback;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupInvitationContract;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupAuthorityListResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupImageDownloadResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationListResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupInvitationResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupListResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupMemberResult;
import com.samsung.android.sdk.mobileservice.social.group.result.GroupResult;
import com.samsung.android.sdk.mobileservice.social.group.result.InvitationLinkResult;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupApi extends SeMobileServiceApi {
    public static final String API_NAME = "GroupApi";
    private final HashSet<Integer> featureIdSet = new HashSet<>(Arrays.asList(new Integer[]{4, 32, 32, 101, 102, 103, 104, 107, 501}));
    private int mFeatureId = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FamilyGroupRequest {
        private final String mGroupName;

        public FamilyGroupRequest(String str) {
            this.mGroupName = str;
        }

        public String getGroupName() {
            return this.mGroupName;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface GroupListResultCallback {
        void onResult(GroupListResult groupListResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface GroupResultCallback<T> {
        void onResult(T t);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface GroupSyncResultCallback {
        void onResult(BooleanResult booleanResult);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ImageDownloadingResultCallback {
        void onResult(GroupImageDownloadResult groupImageDownloadResult);
    }

    public GroupApi(SeMobileServiceSession seMobileServiceSession) {
        super(seMobileServiceSession, "GroupApi");
        checkAuthorized(0, 2);
    }

    private GroupAuthority bundleToAuthority(Bundle bundle) {
        String string = bundle.getString(BundleKey.GROUP_ID);
        String string2 = bundle.getString("member_guid");
        String string3 = bundle.getString("optional_id");
        String string4 = bundle.getString("authority");
        int i2 = bundle.getInt(BundleKey.FAILED_REASON, 0);
        GroupAuthority.AuthorityType authorityType = GroupAuthority.AuthorityType.toAuthorityType(string4);
        debugLog("- groupId=[" + string + "]");
        debugLog("- authority=[" + string4 + "]");
        debugLog("- failedReason=[" + i2 + "]");
        return new GroupAuthority(string, string2, string3, authorityType, i2);
    }

    /* access modifiers changed from: private */
    public List<GroupAuthority> bundleToAuthorityList(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (bundle == null) {
            debugLog("bundle is null : bundleToAuthorityList");
            return arrayList;
        }
        ArrayList<Bundle> parcelableArrayList = bundle.getParcelableArrayList(BundleKey.AUTHORITY_LIST);
        if (parcelableArrayList != null) {
            for (Bundle bundleToAuthority : parcelableArrayList) {
                arrayList.add(bundleToAuthority(bundleToAuthority));
            }
        }
        debugLog("bundleToActivityList size : " + arrayList.size());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public Group bundleToGroup(Bundle bundle) {
        Bundle bundle2 = bundle;
        String string = bundle2.getString(BundleKey.GROUP_ID, (String) null);
        String string2 = bundle2.getString(BundleKey.GROUP_NAME, (String) null);
        debugLog(j.d("- groupId=[", string, "], groupName=[", string2, "] "));
        String string3 = bundle2.getString(BundleKey.GROUP_TYPE, (String) null);
        String string4 = bundle2.getString(BundleKey.OWNER_ID, (String) null);
        long j2 = bundle2.getLong(BundleKey.CREATED_TIME, 0);
        int i2 = bundle2.getInt("max_member_count", 0);
        int i7 = bundle2.getInt("active_member_count", 0);
        long j3 = bundle2.getLong("group_update_time", 0);
        long j8 = bundle2.getLong("contents_update_time", 0);
        long j10 = bundle2.getLong("expire_time", 0);
        String str = string;
        boolean z = bundle2.getBoolean(BundleKey.AUTHORITY_MANAGE, false);
        String string5 = bundle2.getString(BundleKey.DEFAULT_MEMBER_AUTHORITY);
        GroupAuthority.AuthorityType authorityType = GroupAuthority.AuthorityType.toAuthorityType(string5);
        InvitationLink bundleToInvitationLink = bundleToInvitationLink(bundle);
        debugLog("- groupType=[" + string3 + "]");
        String str2 = string3;
        debugLog("- maxMemberCount=[" + i2 + "]");
        StringBuilder sb2 = new StringBuilder("- activeMemberCount=[");
        int i8 = i7;
        sb2.append(i8);
        sb2.append("]");
        debugLog(sb2.toString());
        debugLog("- contentsUpdateTime=[" + j8 + "]");
        debugLog("- expireTime=[" + j10 + "]");
        debugLog("- authorityManage=[" + z + "]");
        debugLog("- defaultMemberAuthority=[" + string5 + "]");
        int i10 = i8;
        String str3 = str2;
        boolean z3 = z;
        return new Group(str, string2, str3, string4, j2, i2, i10, j3, j8, j10, z3, bundleToInvitationLink, authorityType);
    }

    /* access modifiers changed from: private */
    public InvitationLink bundleToInvitationLink(Bundle bundle) {
        if (bundle == null) {
            debugLog("bundle is null : bundleToInvitationLink");
            return null;
        }
        String string = bundle.getString(BundleKey.INVITATION_LINK_URL);
        if (TextUtils.isEmpty(string)) {
            debugLog("url is null : bundleToInvitationLink");
            return null;
        }
        long j2 = bundle.getLong(BundleKey.INVITATION_LINK_CREATED_TIME);
        long j3 = bundle.getLong(BundleKey.INVITATION_LINK_EXPIRED_TIME);
        InvitationLink invitationLink = new InvitationLink(string, j2, j3);
        debugLog("bundleToInvitationLink : " + string + " / " + j2 + " / " + j3);
        return invitationLink;
    }

    private boolean checkInvalidFeatureIdAndAgentVersion() {
        if (this.mFeatureId != 501 || isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) || isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_0)) {
            return false;
        }
        infoLog("checkInvalidFeatureIdAndAgentVersion with" + this.mFeatureId + "is not supported in this agent version");
        return true;
    }

    private boolean isValidFeatureId(int i2) {
        return this.featureIdSet.contains(Integer.valueOf(i2));
    }

    public String[] getEssentialServiceNames() {
        return new String[]{"SocialService"};
    }

    public GroupListResult getGroupList() {
        List<Bundle> list;
        infoLog("getGroupList started");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return new GroupListResult(new CommonResultStatus(-7), (List<Group>) null);
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return new GroupListResult(new CommonResultStatus(-1), (List<Group>) null);
        }
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                list = getSocialService().getGroupListWithData(bundle);
            } else {
                list = getSocialService().getGroupList(getAppId());
            }
            ArrayList arrayList = new ArrayList();
            for (Bundle bundleToGroup : list) {
                arrayList.add(bundleToGroup(bundleToGroup));
            }
            return new GroupListResult(new CommonResultStatus(1), arrayList);
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return new GroupListResult(new CommonResultStatus(-1), (List<Group>) null);
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return new GroupListResult(new CommonResultStatus(-8), (List<Group>) null);
        }
    }

    public int requestFamilyGroupCreation(FamilyGroupRequest familyGroupRequest, final GroupResultCallback<GroupResult> groupResultCallback) {
        infoLog("requestFamilyGroupCreation ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.GROUP_NAME, familyGroupRequest.getGroupName());
        AnonymousClass20 r5 = new IGroupResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestFamilyGroupCreation onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Group) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestFamilyGroupCreation onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                GroupApi.this.infoLog("requestFamilyGroupCreation onSuccess ");
                if (groupResultCallback != null) {
                    GroupApi.this.debugLog(j.d("- groupId=[", bundle.getString(BundleKey.GROUP_ID, (String) null), "], groupName=[", bundle.getString(BundleKey.GROUP_NAME, (String) null), "] "));
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(1), GroupApi.this.bundleToGroup(bundle)));
                }
            }
        };
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("featureId", this.mFeatureId);
            bundle2.putString("appId", getAppId());
            getSocialService().requestFamilyGroupCreationWithData(bundle2, bundle, r5);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestFamilyGroupDeletion(String str, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestFamilyGroupDeletion ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass21 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestFamilyGroupDeletion onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestFamilyGroupDeletion onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestFamilyGroupDeletion onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", this.mFeatureId);
            bundle.putString("appId", getAppId());
            bundle.putString("groupId", str);
            getSocialService().requestFamilyGroupDeletionWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestFamilyGroupMemberList(String str, final GroupResultCallback<GroupMemberResult> groupResultCallback) {
        infoLog("requestFamilyGroupMemberList");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass22 r0 = new IMemberListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestFamilyGroupMemberList onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupMemberResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<GroupMember>) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestFamilyGroupMemberList onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupMemberResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (List<GroupMember>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                List<Bundle> list2;
                Uri uri;
                Uri uri2;
                GroupApi.this.infoLog("requestFamilyGroupMemberList onSuccess ");
                if (groupResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString("name", (String) null);
                        String string2 = next.getString("id", (String) null);
                        String string3 = next.getString("optionalId", (String) null);
                        int i2 = next.getInt("status", 0);
                        String string4 = next.getString("thumbnail_local_path", (String) null);
                        String string5 = next.getString("thumbnail_content_uri", (String) null);
                        boolean z = next.getBoolean("owner", false);
                        if (string4 != null) {
                            uri = Uri.parse(string4);
                        } else {
                            uri = null;
                        }
                        if (string4 != null) {
                            uri2 = Uri.parse(string5);
                        } else {
                            uri2 = null;
                        }
                        String string6 = next.getString("authority", (String) null);
                        GroupMember groupMember = new GroupMember(string2, string3, i2, string, z, uri, uri2, GroupAuthority.AuthorityType.toAuthorityType(string6));
                        GroupApi.this.debugLog("- authority=[" + string6 + "]");
                        arrayList.add(groupMember);
                    }
                    GroupMemberResult groupMemberResult = new GroupMemberResult(new CommonResultStatus(1), arrayList);
                    if (!list.isEmpty()) {
                        list2 = list;
                        groupMemberResult.setTotalCountWithInvitation(list2.get(0).getInt("total", 0));
                    } else {
                        list2 = list;
                        groupMemberResult.setTotalCountWithInvitation(0);
                    }
                    GroupApi.this.infoLog("requestFamilyGroupMemberList onSuccess. result size: " + list2.size() + ", groupMembers size: " + arrayList.size());
                    groupResultCallback.onResult(groupMemberResult);
                }
            }
        };
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("featureId", this.mFeatureId);
            bundle.putString("appId", getAppId());
            bundle.putString("groupId", str);
            getSocialService().requestFamilyGroupMemberListWithData(bundle, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroup(String str, final GroupResultCallback<GroupResult> groupResultCallback) {
        infoLog("requestGroup : groupId=[" + str + "] ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass2 r0 = new IGroupResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroup onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Group) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroup onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                GroupApi.this.infoLog("requestGroup onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(1), GroupApi.this.bundleToGroup(bundle)));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestGroupWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroup(getAppId(), str, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupAuthorityChange(String str, List<String> list, GroupAuthority.AuthorityType authorityType, final GroupResultCallback<GroupAuthorityListResult> groupResultCallback) {
        infoLog("requestGroupAuthorityChange ");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (this.mFeatureId != 501) {
            infoLog("requestGroupAuthorityChange is not supported in FEATURE_ID_INVALID");
            return -1;
        } else if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        } else {
            AnonymousClass19 r0 = new IGroupResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestGroupAuthorityChange onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                        groupResultCallback.onResult(new GroupAuthorityListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<GroupAuthority>) null));
                    }
                }

                public void onFailureWithBundle(Bundle bundle) {
                    long j2 = bundle.getLong("error_code");
                    String string = bundle.getString("error_message");
                    String string2 = bundle.getString("error_string", (String) null);
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestGroupAuthorityChange onFailureWithBundle : code=[", "], message=[", string);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new GroupAuthorityListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (List<GroupAuthority>) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    GroupApi.this.infoLog("requestGroupAuthorityChange onSuccess ");
                    GroupResultCallback groupResultCallback = groupResultCallback;
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new GroupAuthorityListResult(new CommonResultStatus(1), GroupApi.this.bundleToAuthorityList(bundle)));
                    }
                }
            };
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                bundle.putString("authority", authorityType.getAuthority());
                bundle.putStringArrayList("memberGuidList", new ArrayList(list));
                getSocialService().requestGroupAuthorityChange(bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    @Deprecated
    public int requestGroupCoverImageDownload(String str, final ImageDownloadingResultCallback imageDownloadingResultCallback) {
        infoLog("requestOriginalImageDownload groupId=[" + str + "] ");
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        } else if (this.mFeatureId != -1) {
            infoLog("The cover image is not supported in " + this.mFeatureId);
            return -1;
        } else {
            try {
                getSocialService().requestOriginalGroupImageDownload(getAppId(), str, new IGroupCoverImageDownloadingResultCallback.Stub() {
                    public void onFailure(long j2, String str) {
                        GroupApi groupApi = GroupApi.this;
                        StringBuilder p6 = C0086a.p(j2, "requestGroupCoverImageDownload onFailure : code=[", "], message=[", str);
                        p6.append("] ");
                        groupApi.infoLog(p6.toString());
                        if (GroupApi.this.getSemsAgentVersion() < 1050000000 && imageDownloadingResultCallback != null) {
                            imageDownloadingResultCallback.onResult(new GroupImageDownloadResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (GroupImageDownloadResult.DownloadedImage) null));
                        }
                    }

                    public void onFailureWithBundle(Bundle bundle) {
                        long j2 = bundle.getLong("error_code");
                        String string = bundle.getString("error_message");
                        String string2 = bundle.getString("error_string", (String) null);
                        GroupApi groupApi = GroupApi.this;
                        StringBuilder p6 = C0086a.p(j2, "requestGroupCoverImageDownload onFailureWithBundle : code=[", "], message=[", string);
                        p6.append("] ");
                        groupApi.infoLog(p6.toString());
                        if (imageDownloadingResultCallback != null) {
                            imageDownloadingResultCallback.onResult(new GroupImageDownloadResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (GroupImageDownloadResult.DownloadedImage) null));
                        }
                    }

                    public void onSuccess(Bundle bundle) {
                        GroupApi.this.infoLog("requestOriginalImageDownload onSuccess ");
                        if (imageDownloadingResultCallback != null) {
                            Uri uri = null;
                            String string = bundle.getString(BundleKey.GROUP_ID, (String) null);
                            String string2 = bundle.getString(BundleKey.DOWNLOADED_URI, (String) null);
                            if (string2 != null) {
                                uri = Uri.parse(string2);
                            }
                            GroupImageDownloadResult.DownloadedImage downloadedImage = new GroupImageDownloadResult.DownloadedImage(string, uri);
                            GroupApi.this.debugLog(j.d("- groupId=[", string, "], uriString=[", string2, "] "));
                            imageDownloadingResultCallback.onResult(new GroupImageDownloadResult(new CommonResultStatus(1), downloadedImage));
                        }
                    }
                });
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public int requestGroupCreation(GroupRequest groupRequest, InvitationRequest invitationRequest, final GroupResultCallback<GroupInvitationResult> groupResultCallback) {
        Bundle bundle;
        infoLog("requestGroupCreation ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(BundleKey.GROUP_NAME, groupRequest.getGroupName());
        bundle2.putString(BundleKey.GROUP_TYPE, groupRequest.getGroupType());
        bundle2.putString("mime_type", groupRequest.getMimeType());
        if (groupRequest.getCoverImageUri() != null) {
            bundle2.putString("cover_thumbnail_uri", groupRequest.getCoverImageUri().toString());
        }
        if (groupRequest.getUseInvitationLink()) {
            bundle2.putBoolean(BundleKey.USE_INVITATION_LINK, groupRequest.getUseInvitationLink());
        }
        if (groupRequest.getDefaultMemberAuthorityType() != GroupAuthority.AuthorityType.INVALID) {
            bundle2.putString(BundleKey.DEFAULT_MEMBER_AUTHORITY, groupRequest.getDefaultMemberAuthorityType().getAuthority());
        }
        if (invitationRequest != null) {
            bundle = new Bundle();
            bundle.putInt("invitation_type", invitationRequest.getIdType());
            bundle.putString("invitation_message", invitationRequest.getInvitationMessage());
            bundle.putStringArrayList("id", new ArrayList(invitationRequest.getIds()));
            bundle.putStringArrayList("optionalId", new ArrayList(invitationRequest.getOptionalIds()));
        } else {
            bundle = null;
        }
        AnonymousClass6 r62 = new IGroupInvitationResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupCreation onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Group) null, (List<GroupInvitationResult.ExcludedMember>) null, (String) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupCreation onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null, (List<GroupInvitationResult.ExcludedMember>) null, (String) null));
                }
            }

            public void onSuccess(Bundle bundle, List<Bundle> list) {
                GroupApi.this.infoLog("requestGroupCreation onSuccess ");
                if (groupResultCallback != null) {
                    GroupApi.this.debugLog(j.d("- groupId=[", bundle.getString(BundleKey.GROUP_ID, (String) null), "], groupName=[", bundle.getString(BundleKey.GROUP_NAME, (String) null), "] "));
                    String string = bundle.getString("error_string", "");
                    Group access$600 = GroupApi.this.bundleToGroup(bundle);
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        String string2 = next.getString("id", (String) null);
                        String string3 = next.getString("optionalId", (String) null);
                        String string4 = next.getString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, (String) null);
                        GroupApi groupApi = GroupApi.this;
                        groupApi.debugLog("- reason=[" + string4 + "]");
                        arrayList.add(new GroupInvitationResult.ExcludedMember(string2, string3, string4));
                    }
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(1), access$600, arrayList, string));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle3 = new Bundle();
                bundle3.putInt("featureId", this.mFeatureId);
                bundle3.putString("appId", getAppId());
                getSocialService().requestGroupCreationWithData(bundle3, bundle2, bundle, r62);
                return 1;
            }
            getSocialService().requestGroupCreation(getAppId(), bundle2, bundle, r62);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupDeletion(String str, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestGroupDeletion ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass9 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupDeletion onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupDeletion onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestGroupDeletion onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestGroupDeletionWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupDeletion(getAppId(), str, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupInvitationAcceptance(String str, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestGroupInvitationAcceptance ");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass11 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupInvitationAcceptance onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupInvitationAcceptance onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestGroupInvitationAcceptance onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestGroupInvitationAcceptanceWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupInvitationAcceptance(getAppId(), str, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupInvitationRejection(String str, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestGroupInvitationRejection ");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass12 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupInvitationRejection onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupInvitationRejection onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestGroupInvitationRejection onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestGroupInvitationRejectionWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupInvitationRejection(getAppId(), str, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupList(final GroupListResultCallback groupListResultCallback) {
        infoLog("requestGroupList ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass3 r0 = new IGroupListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupList onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupListResultCallback != null) {
                    groupListResultCallback.onResult(new GroupListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<Group>) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupList onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupListResultCallback != null) {
                    groupListResultCallback.onResult(new GroupListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (List<Group>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                GroupApi.this.infoLog("requestGroupList onSuccess ");
                if (groupListResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle access$600 : list) {
                        arrayList.add(GroupApi.this.bundleToGroup(access$600));
                    }
                    groupListResultCallback.onResult(new GroupListResult(new CommonResultStatus(1), arrayList));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                getSocialService().requestGroupListWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupList(getAppId(), r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupMemberInvitation(String str, InvitationRequest invitationRequest, final GroupResultCallback<GroupInvitationResult> groupResultCallback) {
        infoLog("requestGroupMemberInvitation ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("invitation_type", invitationRequest.getIdType());
        bundle.putString("invitation_message", invitationRequest.getInvitationMessage());
        bundle.putStringArrayList("id", new ArrayList(invitationRequest.getIds()));
        bundle.putStringArrayList("optionalId", new ArrayList(invitationRequest.getOptionalIds()));
        AnonymousClass10 r62 = new IGroupInvitationResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberInvitation onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Group) null, (List<GroupInvitationResult.ExcludedMember>) null, (String) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberInvitation onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null, (List<GroupInvitationResult.ExcludedMember>) null, (String) null));
                }
            }

            public void onSuccess(Bundle bundle, List<Bundle> list) {
                GroupApi.this.infoLog("requestGroupMemberInvitation onSuccess ");
                if (groupResultCallback != null) {
                    GroupApi.this.debugLog(j.d("- groupId=[", bundle.getString(BundleKey.GROUP_ID, (String) null), "], groupName=[", bundle.getString(BundleKey.GROUP_NAME, (String) null), "] "));
                    String string = bundle.getString("error_string", "");
                    Group access$600 = GroupApi.this.bundleToGroup(bundle);
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        String string2 = next.getString("id", (String) null);
                        String string3 = next.getString("optionalId", (String) null);
                        String string4 = next.getString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, (String) null);
                        GroupApi groupApi = GroupApi.this;
                        groupApi.debugLog("- reason=[" + string4 + "]");
                        arrayList.add(new GroupInvitationResult.ExcludedMember(string2, string3, string4));
                    }
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(1), access$600, arrayList, string));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", this.mFeatureId);
                bundle2.putString("appId", getAppId());
                bundle2.putString("groupId", str);
                getSocialService().requestGroupMemberInvitationWithData(bundle2, bundle, r62);
                return 1;
            }
            getSocialService().requestGroupMemberInvitation(getAppId(), str, bundle, r62);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupMemberList(String str, final GroupResultCallback<GroupMemberResult> groupResultCallback) {
        infoLog("requestGroupMemberList ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass13 r0 = new IMemberListResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberList onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupMemberResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<GroupMember>) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberList onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupMemberResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (List<GroupMember>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                List<Bundle> list2;
                Uri uri;
                Uri uri2;
                GroupMember groupMember;
                GroupApi.this.infoLog("requestGroupMemberList onSuccess ");
                if (groupResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Bundle next : list) {
                        String string = next.getString("name", (String) null);
                        String string2 = next.getString("id", (String) null);
                        String string3 = next.getString("optionalId", (String) null);
                        int i2 = next.getInt("status", 0);
                        int i7 = next.getInt("invitation_type", 0);
                        String string4 = next.getString("thumbnail_local_path", (String) null);
                        String string5 = next.getString("thumbnail_content_uri", (String) null);
                        boolean z = next.getBoolean("owner", false);
                        if (string4 != null) {
                            uri = Uri.parse(string4);
                        } else {
                            uri = null;
                        }
                        if (string4 != null) {
                            uri2 = Uri.parse(string5);
                        } else {
                            uri2 = null;
                        }
                        String string6 = next.getString("authority", (String) null);
                        if (i7 != 0) {
                            String str = string;
                            int i8 = i2;
                            groupMember = new GroupMember(i7, string2, string3, i8, str, z, uri, uri2);
                        } else {
                            groupMember = new GroupMember(string2, string3, i2, string, z, uri, uri2, GroupAuthority.AuthorityType.toAuthorityType(string6));
                        }
                        GroupApi.this.debugLog("- authority=[" + string6 + "]");
                        arrayList.add(groupMember);
                    }
                    GroupMemberResult groupMemberResult = new GroupMemberResult(new CommonResultStatus(1), arrayList);
                    if (!list.isEmpty()) {
                        list2 = list;
                        groupMemberResult.setTotalCountWithInvitation(list2.get(0).getInt("total", 0));
                    } else {
                        list2 = list;
                        groupMemberResult.setTotalCountWithInvitation(0);
                    }
                    GroupApi.this.infoLog("requestGroupMemberList onSuccess. result size: " + list2.size() + ", groupMembers size: " + arrayList.size());
                    groupResultCallback.onResult(groupMemberResult);
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestGroupMemberListWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupMemberList(getAppId(), str, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupMemberRemoval(String str, String str2, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestGroupMemberRemoval ");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass15 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberRemoval onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberRemoval onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestGroupMemberRemoval onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                bundle.putString("memberGuid", str2);
                getSocialService().requestGroupMemberRemovalWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupMemberRemoval(getAppId(), str, str2, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestGroupPendingInvitationCancellation(String str, String str2, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestGroupPendingInvitationCancellation ");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass17 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupPendingInvitationCancellation onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupPendingInvitationCancellation onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestGroupPendingInvitationCancellation onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                bundle.putString("memberGuid", str2);
                getSocialService().requestGroupPendingInvitationCancellationWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupPendingInvitationCancellation(getAppId(), str, str2, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    @Deprecated
    public int requestGroupUpdate(String str, GroupRequest groupRequest, final GroupResultCallback<GroupResult> groupResultCallback) {
        infoLog("requestGroupUpdate ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0)) {
            return -7;
        }
        if (this.mFeatureId != -1) {
            infoLog("requestGroupUpdate is not supported in " + this.mFeatureId);
            return -1;
        } else if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(BundleKey.GROUP_NAME, groupRequest.getGroupName());
            bundle.putString("mime_type", groupRequest.getMimeType());
            if (groupRequest.getCoverImageUri() != null) {
                bundle.putString("cover_thumbnail_uri", groupRequest.getCoverImageUri().toString());
            }
            try {
                getSocialService().requestGroupUpdate(getAppId(), str, bundle, new IGroupResultCallback.Stub() {
                    public void onFailure(long j2, String str) {
                        GroupApi groupApi = GroupApi.this;
                        StringBuilder p6 = C0086a.p(j2, "requestGroupUpdate onFailure : code=[", "], message=[", str);
                        p6.append("] ");
                        groupApi.infoLog(p6.toString());
                        if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                            groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Group) null));
                        }
                    }

                    public void onFailureWithBundle(Bundle bundle) {
                        long j2 = bundle.getLong("error_code");
                        String string = bundle.getString("error_message");
                        String string2 = bundle.getString("error_string", (String) null);
                        GroupApi groupApi = GroupApi.this;
                        StringBuilder p6 = C0086a.p(j2, "requestGroupUpdate onFailureWithBundle : code=[", "], message=[", string);
                        p6.append("] ");
                        groupApi.infoLog(p6.toString());
                        if (groupResultCallback != null) {
                            groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null));
                        }
                    }

                    public void onSuccess(Bundle bundle) {
                        GroupApi.this.infoLog("requestGroupUpdate onSuccess ");
                        GroupResultCallback groupResultCallback = groupResultCallback;
                        if (groupResultCallback != null) {
                            groupResultCallback.onResult(new GroupResult(new CommonResultStatus(1), GroupApi.this.bundleToGroup(bundle)));
                        }
                    }
                });
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }

    public int requestInvitationLinkCreation(String str, final GroupResultCallback<InvitationLinkResult> groupResultCallback) {
        infoLog("requestInvitationLinkCreation ");
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        } else if (isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) || isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            AnonymousClass23 r0 = new IGroupResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestInvitationLinkCreation onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new InvitationLinkResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (InvitationLink) null));
                    }
                }

                public void onFailureWithBundle(Bundle bundle) {
                    long j2 = bundle.getLong("error_code");
                    String string = bundle.getString("error_message");
                    String string2 = bundle.getString("error_string", (String) null);
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestInvitationLinkCreation onFailureWithBundle : code=[", "], message=[", string);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new InvitationLinkResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (InvitationLink) null));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    GroupApi.this.infoLog("requestInvitationLinkCreation onSuccess ");
                    GroupResultCallback groupResultCallback = groupResultCallback;
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new InvitationLinkResult(new CommonResultStatus(1), GroupApi.this.bundleToInvitationLink(bundle)));
                    }
                }
            };
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestInvitationLinkCreation(bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            infoLog(getAppId() + " with " + this.mFeatureId + " is not supported in this agent version");
            return -7;
        }
    }

    public int requestInvitationLinkDeletion(String str, String str2, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestInvitationLinkDeletion ");
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        } else if (isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) || isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            AnonymousClass24 r0 = new IGroupResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestInvitationLinkDeletion onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                    }
                }

                public void onFailureWithBundle(Bundle bundle) {
                    long j2 = bundle.getLong("error_code");
                    String string = bundle.getString("error_message");
                    String string2 = bundle.getString("error_string", (String) null);
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestInvitationLinkDeletion onFailureWithBundle : code=[", "], message=[", string);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                    }
                }

                public void onSuccess(Bundle bundle) {
                    GroupApi.this.infoLog("requestInvitationLinkDeletion onSuccess ");
                    GroupResultCallback groupResultCallback = groupResultCallback;
                    if (groupResultCallback != null) {
                        groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                    }
                }
            };
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                bundle.putString(BundleKey.INVITATION_LINK_URL, str2);
                getSocialService().requestInvitationLinkDeletion(bundle, r0);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        } else {
            infoLog(getAppId() + " with " + this.mFeatureId + " is not supported in this agent version");
            return -7;
        }
    }

    public int requestLeaderAssignment(String str, String str2, final GroupResultCallback<GroupResult> groupResultCallback) {
        infoLog("requestLeaderAssignment ");
        if (checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass18 r0 = new IGroupResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestLeaderAssignment onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (Group) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestLeaderAssignment onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null));
                }
            }

            public void onSuccess(Bundle bundle) {
                GroupApi.this.infoLog("requestLeaderAssignment onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupResult(new CommonResultStatus(1), GroupApi.this.bundleToGroup(bundle)));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                bundle.putString("memberGuid", str2);
                getSocialService().requestDelegateAuthorityOfOwnerWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestDelegateAuthorityOfOwner(getAppId(), str, str2, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestLeave(String str, final GroupResultCallback<BooleanResult> groupResultCallback) {
        infoLog("requestGroupMemberRemoval ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass16 r0 = new IGroupRequestResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberRemoval onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupMemberRemoval onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestGroupMemberRemoval onSuccess ");
                GroupResultCallback groupResultCallback = groupResultCallback;
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                bundle.putString("groupId", str);
                getSocialService().requestLeaveWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestLeave(getAppId(), str, r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestMyInvitationList(final GroupResultCallback<GroupInvitationListResult> groupResultCallback) {
        infoLog("requestGroupWithInvitationList ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_0) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass14 r0 = new IGroupListWithInvitationResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupWithInvitationList onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupInvitationListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), (List<GroupInvitationListResult.GroupInvitation>) null));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestGroupWithInvitationList onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupResultCallback != null) {
                    groupResultCallback.onResult(new GroupInvitationListResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (List<GroupInvitationListResult.GroupInvitation>) null));
                }
            }

            public void onSuccess(List<Bundle> list) {
                Uri uri;
                Uri uri2;
                Uri uri3;
                Uri uri4;
                GroupApi.this.infoLog("requestGroupWithInvitationList onSuccess ");
                if (groupResultCallback != null) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Bundle> it = list.iterator();
                    while (it.hasNext()) {
                        Bundle next = it.next();
                        String string = next.getString(BundleKey.GROUP_ID, (String) null);
                        String string2 = next.getString(BundleKey.GROUP_NAME, (String) null);
                        GroupApi.this.debugLog(j.d("- groupId=[", string, "], groupName=[", string2, "] "));
                        String string3 = next.getString("group_cover_thumbnail_url", (String) null);
                        String string4 = next.getString("group_cover_thumbnail_uri", (String) null);
                        String string5 = next.getString("group_cover_thumbnail_content_uri", (String) null);
                        String string6 = next.getString("invitation_message", (String) null);
                        String string7 = next.getString(GroupInvitationContract.Invitation.REQUESTER_ID, (String) null);
                        String string8 = next.getString(GroupInvitationContract.Invitation.REQUESTER_NAME, (String) null);
                        String string9 = next.getString("requesterImageUrl", (String) null);
                        String string10 = next.getString("requesterImageFileUri", (String) null);
                        String string11 = next.getString("requesterImageContentUri", (String) null);
                        String str = string5;
                        String str2 = string;
                        long j2 = next.getLong(GroupInvitationContract.Invitation.REQUESTED_TIME, 0);
                        long j3 = next.getLong("expired_time", 0);
                        GroupApi groupApi = GroupApi.this;
                        Iterator<Bundle> it2 = it;
                        groupApi.debugLog("- expiredTime=[" + j3 + "]");
                        if (string4 != null) {
                            uri = Uri.parse(string4);
                        } else {
                            uri = null;
                        }
                        if (str != null) {
                            uri2 = Uri.parse(str);
                        } else {
                            uri2 = null;
                        }
                        if (string10 != null) {
                            uri3 = Uri.parse(string10);
                        } else {
                            uri3 = null;
                        }
                        if (string11 != null) {
                            uri4 = Uri.parse(string11);
                        } else {
                            uri4 = null;
                        }
                        arrayList.add(new GroupInvitationListResult.GroupInvitation(str2, string2, string3, uri, uri2, string6, string7, string8, string9, uri3, uri4, j2, j3));
                        it = it2;
                    }
                    groupResultCallback.onResult(new GroupInvitationListResult(new CommonResultStatus(1), arrayList));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                getSocialService().requestGroupWithInvitationListWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupWithInvitationList(getAppId(), r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestSync(final GroupSyncResultCallback groupSyncResultCallback) {
        infoLog("requestSync ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_4_1) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass4 r0 = new IGroupSyncResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestSync onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupSyncResultCallback != null) {
                    groupSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestSync onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupSyncResultCallback != null) {
                    groupSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestSync onSuccess ");
                GroupSyncResultCallback groupSyncResultCallback = groupSyncResultCallback;
                if (groupSyncResultCallback != null) {
                    groupSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                getSocialService().requestGroupSyncWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupSync(getAppId(), r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    public int requestSyncWithoutImage(final GroupSyncResultCallback groupSyncResultCallback) {
        infoLog("requestSyncWithoutImage ");
        if (!isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_10_5) || checkInvalidFeatureIdAndAgentVersion()) {
            return -7;
        }
        if (getAppId() == null) {
            infoLog("app id is null ");
            return -1;
        }
        AnonymousClass5 r0 = new IGroupSyncResultCallback.Stub() {
            public void onFailure(long j2, String str) {
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestSyncWithoutImage onFailure : code=[", "], message=[", str);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (GroupApi.this.getSemsAgentVersion() < 1050000000 && groupSyncResultCallback != null) {
                    groupSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), str, Long.toString(j2)), false));
                }
            }

            public void onFailureWithBundle(Bundle bundle) {
                long j2 = bundle.getLong("error_code");
                String string = bundle.getString("error_message");
                String string2 = bundle.getString("error_string", (String) null);
                GroupApi groupApi = GroupApi.this;
                StringBuilder p6 = C0086a.p(j2, "requestSyncWithoutImage onFailureWithBundle : code=[", "], message=[", string);
                p6.append("] ");
                groupApi.infoLog(p6.toString());
                if (groupSyncResultCallback != null) {
                    groupSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), false));
                }
            }

            public void onSuccess() {
                GroupApi.this.infoLog("requestSyncWithoutImage onSuccess ");
                GroupSyncResultCallback groupSyncResultCallback = groupSyncResultCallback;
                if (groupSyncResultCallback != null) {
                    groupSyncResultCallback.onResult(new BooleanResult(new CommonResultStatus(1), true));
                }
            }
        };
        try {
            if (this.mFeatureId == 501) {
                Bundle bundle = new Bundle();
                bundle.putInt("featureId", this.mFeatureId);
                bundle.putString("appId", getAppId());
                getSocialService().requestGroupSyncWithoutImageWithData(bundle, r0);
                return 1;
            }
            getSocialService().requestGroupSyncWithoutImage(getAppId(), r0);
            return 1;
        } catch (RemoteException | NullPointerException e) {
            secureLog(e);
            return -1;
        } catch (NotConnectedException e7) {
            secureLog(e7);
            return -8;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class InvitationRequest {
        @Deprecated
        public static final int ID_TYPE_ACCOUNT_ID = 3;
        @Deprecated
        public static final int ID_TYPE_DUID = 2;
        @Deprecated
        public static final int ID_TYPE_GUID = 0;
        @Deprecated
        public static final int ID_TYPE_MSISDN = 1;
        private int mIdType;
        private List<String> mIds;
        private String mInvitationMessage;
        private List<String> mOptionalIds;

        public InvitationRequest(String str, int i2, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            this.mInvitationMessage = str;
            this.mIdType = i2;
            this.mIds = arrayList;
            this.mOptionalIds = arrayList2;
        }

        @Deprecated
        public int getIdType() {
            return this.mIdType;
        }

        public List<String> getIds() {
            return this.mIds;
        }

        public String getInvitationMessage() {
            return this.mInvitationMessage;
        }

        public List<String> getOptionalIds() {
            return this.mOptionalIds;
        }

        public InvitationRequest(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            this.mInvitationMessage = str;
            this.mIdType = 0;
            this.mIds = arrayList;
            this.mOptionalIds = arrayList2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GroupRequest {
        public static final String GROUP_TYPE_AUTO_HOTSPOT = "AHSP";
        @Deprecated
        public static final String GROUP_TYPE_FAMILY = "FMLY";
        @Deprecated
        public static final String GROUP_TYPE_GENERAL = "GNRL";
        public static final String GROUP_TYPE_LOCAL = "UNM1";
        private Uri mCoverImageUri;
        private GroupAuthority.AuthorityType mDefaultMemberAuthority;
        private String mGroupName;
        private String mGroupType;
        private String mMimeType;
        private boolean mUseInvitationLink;

        @Deprecated
        public GroupRequest(String str, String str2, Uri uri, String str3) {
            this.mUseInvitationLink = false;
            this.mDefaultMemberAuthority = GroupAuthority.AuthorityType.INVALID;
            this.mGroupName = str;
            this.mGroupType = str2;
            this.mCoverImageUri = uri;
            this.mMimeType = str3;
        }

        @Deprecated
        public Uri getCoverImageUri() {
            return this.mCoverImageUri;
        }

        public GroupAuthority.AuthorityType getDefaultMemberAuthorityType() {
            return this.mDefaultMemberAuthority;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public String getGroupType() {
            return this.mGroupType;
        }

        public String getMimeType() {
            return this.mMimeType;
        }

        public boolean getUseInvitationLink() {
            return this.mUseInvitationLink;
        }

        public GroupRequest(String str, String str2) {
            this.mUseInvitationLink = false;
            this.mDefaultMemberAuthority = GroupAuthority.AuthorityType.INVALID;
            this.mGroupName = str;
            this.mGroupType = str2;
        }

        public GroupRequest(String str, String str2, boolean z, GroupAuthority.AuthorityType authorityType) {
            this.mUseInvitationLink = false;
            GroupAuthority.AuthorityType authorityType2 = GroupAuthority.AuthorityType.INVALID;
            this.mGroupName = str;
            this.mGroupType = str2;
            this.mUseInvitationLink = z;
            this.mDefaultMemberAuthority = authorityType;
        }

        public GroupRequest(String str, String str2, boolean z) {
            this.mUseInvitationLink = false;
            this.mDefaultMemberAuthority = GroupAuthority.AuthorityType.INVALID;
            this.mGroupName = str;
            this.mGroupType = str2;
            this.mUseInvitationLink = z;
        }

        @Deprecated
        public GroupRequest(String str, Uri uri, String str2) {
            this.mUseInvitationLink = false;
            this.mDefaultMemberAuthority = GroupAuthority.AuthorityType.INVALID;
            this.mGroupName = str;
            this.mCoverImageUri = uri;
            this.mMimeType = str2;
        }

        public GroupRequest(String str) {
            this.mUseInvitationLink = false;
            this.mDefaultMemberAuthority = GroupAuthority.AuthorityType.INVALID;
            this.mGroupName = str;
        }
    }

    public GroupApi(SeMobileServiceSession seMobileServiceSession, int i2) {
        super(seMobileServiceSession, "GroupApi");
        checkAuthorized(0, 2);
        if (isValidFeatureId(i2)) {
            this.mFeatureId = i2;
            return;
        }
        throw new NotSupportedApiException(i2 + " is not valid");
    }

    public int requestGroupCreation(GroupRequest groupRequest, final GroupResultCallback<GroupInvitationResult> groupResultCallback) {
        infoLog("requestGroupCreation only for invitation link");
        if (!isSupportedSemsAgentVersionBetween(CommonConstants.SupportedApiMinVersion.VERSION_10_8_9, CommonConstants.SupportedApiMinVersion.VERSION_11_0) && !isSupportedSemsAgentVersionMoreThan(CommonConstants.SupportedApiMinVersion.VERSION_13_1_10)) {
            infoLog(getAppId() + " with " + this.mFeatureId + " is not supported in this agent version");
            return -7;
        } else if (getAppId() == null) {
            infoLog("app id is null");
            return -1;
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(BundleKey.GROUP_NAME, groupRequest.getGroupName());
            bundle.putString(BundleKey.GROUP_TYPE, groupRequest.getGroupType());
            bundle.putString("mime_type", groupRequest.getMimeType());
            bundle.putBoolean(BundleKey.USE_INVITATION_LINK, groupRequest.getUseInvitationLink());
            if (groupRequest.getDefaultMemberAuthorityType() != GroupAuthority.AuthorityType.INVALID) {
                bundle.putString(BundleKey.DEFAULT_MEMBER_AUTHORITY, groupRequest.getDefaultMemberAuthorityType().getAuthority());
            }
            AnonymousClass7 r5 = new IGroupInvitationResultCallback.Stub() {
                public void onFailure(long j2, String str) {
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestGroupCreation only for invitation link is onFailure : code=[", "], message=[", str);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                }

                public void onFailureWithBundle(Bundle bundle) {
                    if (groupResultCallback == null) {
                        GroupApi.this.infoLog("callback is null, requestGroupCreation only for invitation link is onFailureWithBundle ");
                        return;
                    }
                    long j2 = bundle.getLong("error_code");
                    String string = bundle.getString("error_message");
                    String string2 = bundle.getString("error_string", (String) null);
                    GroupApi groupApi = GroupApi.this;
                    StringBuilder p6 = C0086a.p(j2, "requestGroupCreation only for invitation link is onFailureWithBundle : code=[", "], message=[", string);
                    p6.append("] ");
                    groupApi.infoLog(p6.toString());
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(ErrorCodeConvertor.convertErrorcode(j2), string, Long.toString(j2), string2), (Group) null, (List<GroupInvitationResult.ExcludedMember>) null, (String) null));
                }

                public void onSuccess(Bundle bundle, List<Bundle> list) {
                    if (groupResultCallback == null) {
                        GroupApi.this.infoLog("callback is null, requestGroupCreation only for invitation link is onSuccess ");
                        return;
                    }
                    GroupApi.this.infoLog("requestGroupCreation only for invitation link is onSuccess ");
                    GroupApi.this.debugLog(j.d("- groupId=[", bundle.getString(BundleKey.GROUP_ID, (String) null), "], groupName=[", bundle.getString(BundleKey.GROUP_NAME, (String) null), "] "));
                    String string = bundle.getString("error_string", "");
                    groupResultCallback.onResult(new GroupInvitationResult(new CommonResultStatus(1), GroupApi.this.bundleToGroup(bundle), new ArrayList(0), string));
                }
            };
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("featureId", this.mFeatureId);
                bundle2.putString("appId", getAppId());
                getSocialService().requestGroupCreationWithData(bundle2, bundle, new Bundle(), r5);
                return 1;
            } catch (RemoteException | NullPointerException e) {
                secureLog(e);
                return -1;
            } catch (NotConnectedException e7) {
                secureLog(e7);
                return -8;
            }
        }
    }
}
