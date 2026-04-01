package com.samsung.android.sdk.mobileservice.social.feedback;

import com.samsung.android.sdk.mobileservice.social.feedback.ContentId;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmotionMemberList<T extends ContentId> {
    private String mCommentId;
    private T mContentId;
    private List<EmotionMember> mEmotionMemberList;
    private String mNextMemberGuid;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmotionMember {
        private int mEmotionType;
        private long mUpdateTime;
        private UserProfile mUserProfile;

        public EmotionMember(UserProfile userProfile, long j2, int i2) {
            this.mUserProfile = userProfile;
            this.mUpdateTime = j2;
            this.mEmotionType = i2;
        }

        public int getEmotionType() {
            return this.mEmotionType;
        }

        public long getUpdateTime() {
            return this.mUpdateTime;
        }

        public UserProfile getUserProfile() {
            return this.mUserProfile;
        }
    }

    public EmotionMemberList(T t, String str, String str2, List<EmotionMember> list) {
        this.mContentId = t;
        this.mCommentId = str;
        this.mNextMemberGuid = str2;
        this.mEmotionMemberList = list;
    }

    public String getCommentId() {
        return this.mCommentId;
    }

    public T getContentId() {
        return this.mContentId;
    }

    public List<EmotionMember> getEmotionMemberList() {
        return this.mEmotionMemberList;
    }

    public String getNextMemberGuid() {
        return this.mNextMemberGuid;
    }
}
