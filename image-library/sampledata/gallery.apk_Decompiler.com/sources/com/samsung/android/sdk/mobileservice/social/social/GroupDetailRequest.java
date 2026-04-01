package com.samsung.android.sdk.mobileservice.social.social;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupDetailRequest {
    private static final String DELIMITER = ";";
    private static final String KEY_APP_ID = "app_id";
    private static final String KEY_FEATURE_ID = "feature_id";
    private static final String KEY_GROUP_ID = "group_id";
    private static final String KEY_IGNORE_APP_ID = "ignore_app_id";
    private static final String KEY_INVITABLE = "invitable";
    private static final String KEY_MAX_GROUP_MEMBER_COUNT = "max_group_member_count";
    private static final String KEY_SPACE_NAME = "space_name";
    private static final String KEY_SUPPORT_LOCAL_GROUP_OWNER_DELEGATION = "support_local_group_owner_delegation";
    private final String mAppId;
    private int mFeatureId;
    private final List<Integer> mFeatureIdList;
    private final String mGroupId;
    private final boolean mInvitable;
    private final int mMaxGroupMemberCount;
    private final String mSpaceName;
    private final boolean mSupportGroupOwnerDelegation;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public final String mAppId;
        /* access modifiers changed from: private */
        public int mFeatureId;
        /* access modifiers changed from: private */
        public List<Integer> mFeatureIdList;
        /* access modifiers changed from: private */
        public final String mGroupId;
        /* access modifiers changed from: private */
        public boolean mInvitable;
        /* access modifiers changed from: private */
        public int mMaxGroupMemberCount;
        /* access modifiers changed from: private */
        public String mSpaceName;
        public boolean mSupportGroupOwnerDelegation;

        @Deprecated
        public Builder(String str, String str2) {
            this.mMaxGroupMemberCount = -1;
            this.mSpaceName = null;
            this.mFeatureIdList = null;
            this.mInvitable = true;
            this.mFeatureId = -1;
            this.mSupportGroupOwnerDelegation = true;
            this.mAppId = str;
            this.mGroupId = str2;
        }

        public GroupDetailRequest build() {
            if (TextUtils.isEmpty(this.mAppId)) {
                throw new NullPointerException("AppId should not be empty");
            } else if (!TextUtils.isEmpty(this.mGroupId)) {
                return new GroupDetailRequest(this);
            } else {
                throw new NullPointerException("GroupId should not be empty");
            }
        }

        @Deprecated
        public Builder setFeatureId(List<Integer> list) {
            this.mFeatureIdList = list;
            return this;
        }

        @Deprecated
        public Builder setFeatureIdList(List<String> list) {
            ArrayList arrayList = new ArrayList();
            for (String valueOf : list) {
                arrayList.add(Integer.valueOf(valueOf));
            }
            this.mFeatureIdList = arrayList;
            return this;
        }

        public Builder setInvitable(boolean z) {
            this.mInvitable = z;
            return this;
        }

        public Builder setMaxGroupMemberCount(int i2) {
            this.mMaxGroupMemberCount = i2;
            return this;
        }

        public Builder setSpaceName(String str) {
            this.mSpaceName = str;
            return this;
        }

        public Builder setSupportGroupOwnerDelegation(boolean z) {
            this.mSupportGroupOwnerDelegation = z;
            return this;
        }

        public Builder setFeatureId(int i2) {
            this.mFeatureId = i2;
            return this;
        }

        public Builder(String str, String str2, int i2) {
            this.mMaxGroupMemberCount = -1;
            this.mSpaceName = null;
            this.mFeatureIdList = null;
            this.mInvitable = true;
            this.mSupportGroupOwnerDelegation = true;
            this.mAppId = str;
            this.mGroupId = str2;
            this.mFeatureId = i2;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("app_id", this.mAppId);
        bundle.putString("group_id", this.mGroupId);
        bundle.putBoolean(KEY_INVITABLE, this.mInvitable);
        bundle.putBoolean(KEY_IGNORE_APP_ID, true);
        bundle.putBoolean(KEY_SUPPORT_LOCAL_GROUP_OWNER_DELEGATION, this.mSupportGroupOwnerDelegation);
        int i2 = this.mMaxGroupMemberCount;
        if (i2 != -1) {
            bundle.putInt(KEY_MAX_GROUP_MEMBER_COUNT, i2);
        }
        if (!TextUtils.isEmpty(this.mSpaceName)) {
            bundle.putString(KEY_SPACE_NAME, this.mSpaceName);
        }
        int i7 = this.mFeatureId;
        if (i7 != -1) {
            bundle.putString("feature_id", String.valueOf(i7));
            return bundle;
        }
        List<Integer> list = this.mFeatureIdList;
        if (list != null && !list.isEmpty()) {
            bundle.putString("feature_id", TextUtils.join(DELIMITER, this.mFeatureIdList));
        }
        return bundle;
    }

    private GroupDetailRequest(Builder builder) {
        this.mAppId = builder.mAppId;
        this.mGroupId = builder.mGroupId;
        this.mSpaceName = builder.mSpaceName;
        this.mFeatureIdList = builder.mFeatureIdList;
        this.mMaxGroupMemberCount = builder.mMaxGroupMemberCount;
        this.mInvitable = builder.mInvitable;
        this.mFeatureId = builder.mFeatureId;
        this.mSupportGroupOwnerDelegation = builder.mSupportGroupOwnerDelegation;
    }
}
