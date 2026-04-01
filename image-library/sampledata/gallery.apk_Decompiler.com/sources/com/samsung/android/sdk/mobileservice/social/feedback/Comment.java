package com.samsung.android.sdk.mobileservice.social.feedback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Comment {
    private String mComment;
    private String mCommentId;
    private long mCreatedTime;
    private EmotionList mEmotionList;
    private UserProfile mOwnerProfile;

    public Comment(String str, String str2, UserProfile userProfile, long j2, EmotionList emotionList) {
        this.mCommentId = str;
        this.mComment = str2;
        this.mOwnerProfile = userProfile;
        this.mCreatedTime = j2;
        this.mEmotionList = emotionList;
    }

    public String getComment() {
        return this.mComment;
    }

    public String getCommentId() {
        return this.mCommentId;
    }

    public long getCreatedTime() {
        return this.mCreatedTime;
    }

    public EmotionList getEmotionList() {
        return this.mEmotionList;
    }

    public UserProfile getOwnerProfile() {
        return this.mOwnerProfile;
    }
}
