package com.samsung.android.sdk.mobileservice.social.feedback;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommentList {
    private List<Comment> mComments;
    private String mNextCommentId;

    public CommentList(String str, List<Comment> list) {
        this.mNextCommentId = str;
        this.mComments = list;
    }

    public List<Comment> getComments() {
        return this.mComments;
    }

    public String getNextCommentId() {
        return this.mNextCommentId;
    }
}
