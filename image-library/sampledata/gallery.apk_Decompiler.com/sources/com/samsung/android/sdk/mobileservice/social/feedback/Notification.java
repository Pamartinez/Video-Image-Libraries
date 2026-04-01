package com.samsung.android.sdk.mobileservice.social.feedback;

import com.samsung.android.sdk.mobileservice.social.feedback.ContentId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Notification<T extends ContentId> {
    public static int FEEDBACK_TYPE_COMMENT = 1;
    public static int FEEDBACK_TYPE_EMOTION = 2;
    private String mComment;
    private String mCommentId;
    private T mContentId;
    private int mEmotionType;
    private int mFeedbackType;
    private String mNotificationId;
    private UserProfile mSenderProfile;
    private long mTimestamp;

    public Notification(T t, String str, int i2, UserProfile userProfile, String str2, String str3, int i7, long j2) {
        this.mContentId = t;
        this.mNotificationId = str;
        this.mFeedbackType = i2;
        this.mSenderProfile = userProfile;
        this.mCommentId = str2;
        this.mComment = str3;
        this.mEmotionType = i7;
        this.mTimestamp = j2;
    }

    public String getComment() {
        return this.mComment;
    }

    public String getCommentId() {
        return this.mCommentId;
    }

    public T getContentId() {
        return this.mContentId;
    }

    public int getEmotionType() {
        return this.mEmotionType;
    }

    public int getFeedbackType() {
        return this.mFeedbackType;
    }

    public String getNotificationId() {
        return this.mNotificationId;
    }

    public UserProfile getSenderProfile() {
        return this.mSenderProfile;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }
}
