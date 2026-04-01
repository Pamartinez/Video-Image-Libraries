package com.samsung.android.sdk.mobileservice.social.social;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SocialPickerRequest {
    private static final String DELIMITER = ";";
    private static final String KEY_DISPLAY_BUDDY = "display_buddy";
    private static final String KEY_DISPLAY_SA_FAMILY = "display_sa_family";
    private static final String KEY_ENABLE_QUICK_INVITATION = "enable_quick_invitation";
    private static final String KEY_ENABLE_RECENT_INVITATION = "enable_recent_invitation";
    private static final String KEY_ENABLE_SHARING_ACCOUNT = "enable_sharing_account";
    private static final String KEY_ENABLE_SKIP = "enable_skip_button";
    private static final String KEY_EXCEPTION_GUID = "exception_guid_list";
    private static final String KEY_FILTER_APP_ID = "filter_app_id";
    private static final String KEY_FILTER_CAPABILITY = "filter_capability";
    private static final String KEY_FILTER_FEATURE_ID = "filter_feature_id";
    private static final String KEY_IGNORE_APP_ID = "ignore_app_id";
    private static final String KEY_INVITE_TITLE_URI = "invite_title_uri";
    private static final String KEY_MAX_RECIPIENT_COUNT = "maxRecipientCount";
    private static final String KEY_REQUEST_GROUP_PERMISSION = "request_group_permission";
    private static final String KEY_SELECTED_GUIDS = "selected_guids";
    private static final String KEY_SHOW_EDIT_AUTHORITY = "show_edit_authority";
    private static final String KEY_SHOW_INVITATION_CHOICE = "show_invitation_choice";
    private static final String KEY_STANDALONE_GROUP = "standalone_group";
    private final boolean mEnableFilterCapability;
    private final boolean mEnableQuickInvitation;
    private final boolean mEnableRecentInvitation;
    private final boolean mEnableSharingAccount;
    private final boolean mEnableSkip;
    private final List<String> mExceptionGuidList;
    private final int mFeatureId;
    private final String mFilterAppId;
    private final List<Integer> mFilterFeatureIdList;
    private final String mInviteTitleUri;
    private final int mMaxRecipientCount;
    private final boolean mRequestGroupPermission;
    private final List<String> mSelectedGuids;
    private final boolean mShowBuddy;
    private final boolean mShowEditAuthority;
    private final boolean mShowFamilyMember;
    private final boolean mShowInvitationChoice;
    private final boolean mStandalone;

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FILTER_APP_ID, this.mFilterAppId);
        int i2 = this.mFeatureId;
        if (i2 != -1) {
            bundle.putString(KEY_FILTER_FEATURE_ID, String.valueOf(i2));
        } else {
            List<Integer> list = this.mFilterFeatureIdList;
            if (list != null && !list.isEmpty()) {
                bundle.putString(KEY_FILTER_FEATURE_ID, TextUtils.join(DELIMITER, this.mFilterFeatureIdList));
            }
        }
        List<String> list2 = this.mExceptionGuidList;
        if (list2 != null && !list2.isEmpty()) {
            bundle.putString(KEY_EXCEPTION_GUID, TextUtils.join(DELIMITER, this.mExceptionGuidList));
        }
        int i7 = this.mMaxRecipientCount;
        if (i7 != -1) {
            bundle.putInt(KEY_MAX_RECIPIENT_COUNT, i7);
        }
        bundle.putBoolean(KEY_ENABLE_SHARING_ACCOUNT, this.mEnableSharingAccount);
        bundle.putBoolean(KEY_ENABLE_RECENT_INVITATION, this.mEnableRecentInvitation);
        bundle.putBoolean(KEY_REQUEST_GROUP_PERMISSION, this.mRequestGroupPermission);
        bundle.putBoolean(KEY_IGNORE_APP_ID, true);
        bundle.putBoolean(KEY_SHOW_INVITATION_CHOICE, this.mShowInvitationChoice);
        bundle.putBoolean(KEY_SHOW_EDIT_AUTHORITY, this.mShowEditAuthority);
        bundle.putBoolean(KEY_ENABLE_SKIP, this.mEnableSkip);
        bundle.putBoolean(KEY_STANDALONE_GROUP, this.mStandalone);
        bundle.putBoolean(KEY_FILTER_CAPABILITY, this.mEnableFilterCapability);
        bundle.putBoolean(KEY_DISPLAY_BUDDY, this.mShowBuddy);
        bundle.putBoolean(KEY_DISPLAY_SA_FAMILY, this.mShowFamilyMember);
        bundle.putBoolean(KEY_ENABLE_QUICK_INVITATION, this.mEnableQuickInvitation);
        bundle.putStringArray(KEY_SELECTED_GUIDS, (String[]) this.mSelectedGuids.toArray(new String[0]));
        if (!TextUtils.isEmpty(this.mInviteTitleUri)) {
            bundle.putString(KEY_INVITE_TITLE_URI, this.mInviteTitleUri);
        }
        return bundle;
    }

    private SocialPickerRequest(Builder builder) {
        this.mFilterAppId = builder.mFilterAppId;
        this.mFilterFeatureIdList = builder.mFilterFeatureIdList;
        this.mFeatureId = builder.mFeatureId;
        this.mShowInvitationChoice = builder.mShowInvitationChoice;
        this.mShowEditAuthority = builder.mShowEditAuthority;
        this.mExceptionGuidList = builder.mExceptionGuidList;
        this.mMaxRecipientCount = builder.mMaxRecipientCount;
        this.mEnableSharingAccount = builder.mEnableSharingAccount;
        this.mEnableRecentInvitation = builder.mEnableRecentInvitation;
        this.mRequestGroupPermission = builder.mRequestGroupPermission;
        this.mInviteTitleUri = builder.mInviteTitleUri;
        this.mEnableSkip = builder.mEnableSkip;
        this.mStandalone = builder.mStandalone;
        this.mEnableFilterCapability = builder.mEnableFilterCapability;
        this.mShowBuddy = builder.mShowBuddy;
        this.mShowFamilyMember = builder.mShowFamilyMember;
        this.mEnableQuickInvitation = builder.mEnableQuickInvitation;
        this.mSelectedGuids = builder.mSelectedGuids;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public boolean mEnableFilterCapability = true;
        /* access modifiers changed from: private */
        public boolean mEnableQuickInvitation = false;
        /* access modifiers changed from: private */
        public boolean mEnableRecentInvitation = true;
        /* access modifiers changed from: private */
        public boolean mEnableSharingAccount = true;
        /* access modifiers changed from: private */
        public boolean mEnableSkip = false;
        /* access modifiers changed from: private */
        public List<String> mExceptionGuidList = null;
        /* access modifiers changed from: private */
        public int mFeatureId = -1;
        /* access modifiers changed from: private */
        public String mFilterAppId = null;
        /* access modifiers changed from: private */
        public List<Integer> mFilterFeatureIdList = null;
        /* access modifiers changed from: private */
        public String mInviteTitleUri = null;
        /* access modifiers changed from: private */
        public int mMaxRecipientCount = -1;
        /* access modifiers changed from: private */
        public boolean mRequestGroupPermission = false;
        /* access modifiers changed from: private */
        public List<String> mSelectedGuids = new ArrayList();
        /* access modifiers changed from: private */
        public boolean mShowBuddy = true;
        /* access modifiers changed from: private */
        public boolean mShowEditAuthority = false;
        /* access modifiers changed from: private */
        public boolean mShowFamilyMember = false;
        /* access modifiers changed from: private */
        public boolean mShowInvitationChoice = false;
        /* access modifiers changed from: private */
        public boolean mStandalone = false;

        @Deprecated
        public Builder() {
        }

        public SocialPickerRequest build() {
            return new SocialPickerRequest(this);
        }

        public Builder setEnableFilterCapability(boolean z) {
            this.mEnableFilterCapability = z;
            return this;
        }

        public Builder setEnableQuickInvitation(boolean z) {
            this.mEnableQuickInvitation = z;
            return this;
        }

        public Builder setEnableRecentInvitation(boolean z) {
            this.mEnableRecentInvitation = z;
            return this;
        }

        public Builder setEnableSharingAccount(boolean z) {
            this.mEnableSharingAccount = z;
            return this;
        }

        public Builder setEnableSkip(boolean z) {
            this.mEnableSkip = z;
            return this;
        }

        public Builder setExceptionGuidList(List<String> list) {
            this.mExceptionGuidList = list;
            return this;
        }

        @Deprecated
        public Builder setFilterAppId(String str) {
            this.mFilterAppId = str;
            return this;
        }

        @Deprecated
        public Builder setFilterFeatureIdList(List<String> list) {
            ArrayList arrayList = new ArrayList();
            for (String valueOf : list) {
                arrayList.add(Integer.valueOf(valueOf));
            }
            this.mFilterFeatureIdList = arrayList;
            return this;
        }

        public Builder setInviteTitleUri(Context context, int i2) {
            this.mInviteTitleUri = new Uri.Builder().scheme("android.resource").authority(context.getResources().getResourcePackageName(i2)).appendPath(String.valueOf(i2)).build().toString();
            return this;
        }

        public Builder setMaxRecipientCount(int i2) {
            this.mMaxRecipientCount = i2;
            return this;
        }

        public Builder setRequestGroupPermission(boolean z) {
            this.mRequestGroupPermission = z;
            return this;
        }

        public Builder setSelectedGuids(List<String> list) {
            this.mSelectedGuids = list;
            return this;
        }

        public Builder setShowBuddy(boolean z) {
            this.mShowBuddy = z;
            return this;
        }

        public Builder setShowEditAuthority(Boolean bool) {
            this.mShowEditAuthority = bool.booleanValue();
            return this;
        }

        public Builder setShowFamilyMember(boolean z) {
            this.mShowFamilyMember = z;
            return this;
        }

        public Builder setShowInvitationChoice(Boolean bool) {
            this.mShowInvitationChoice = bool.booleanValue();
            return this;
        }

        public Builder setStandalone(boolean z) {
            this.mStandalone = z;
            return this;
        }

        @Deprecated
        public Builder(String str, List<Integer> list) {
            this.mFilterAppId = str;
            this.mFilterFeatureIdList = list;
        }

        public Builder(String str, int i2) {
            this.mFilterAppId = str;
            this.mFeatureId = i2;
        }

        public Builder(String str) {
            this.mFilterAppId = str;
        }
    }
}
