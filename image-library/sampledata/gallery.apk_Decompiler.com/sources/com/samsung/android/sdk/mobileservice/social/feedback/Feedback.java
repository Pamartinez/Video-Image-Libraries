package com.samsung.android.sdk.mobileservice.social.feedback;

import com.samsung.android.sdk.mobileservice.social.feedback.ContentId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Feedback<T extends ContentId> {
    private CommentList mCommentList;
    private T mContentId;
    private EmotionList mEmotionList;
    private int mTotalCommentCount;

    public Feedback(T t, EmotionList emotionList, int i2, CommentList commentList) {
        this.mContentId = t;
        this.mEmotionList = emotionList;
        this.mTotalCommentCount = i2;
        this.mCommentList = commentList;
    }

    public CommentList getCommentList() {
        return this.mCommentList;
    }

    public T getContentId() {
        return this.mContentId;
    }

    public EmotionList getEmotionList() {
        return this.mEmotionList;
    }

    public int getTotalCommentCount() {
        return this.mTotalCommentCount;
    }
}
