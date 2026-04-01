package com.samsung.android.sdk.mobileservice.social.share;

import android.net.Uri;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Space {
    private long mContentUpdatedTime;
    private Uri mCoverThumbnailFileUri;
    private long mCreatedTime;
    private String mGroupId;
    private boolean mIsOwnedByMe;
    private boolean mIsStandAlone = false;
    private int mItemCount;
    private String mLeaderId;
    private String mMemo;
    private Map mMetaData;
    private long mModifiedTime;
    private long mMyCountUsage;
    private long mMyUsage;
    private long mSize;
    private String mSourceCid;
    private String mSpaceId;
    private String mTitle;
    private int mUnreadCount;
    private WebLink mWebLink;

    public Space(String str, String str2, String str3) {
        this.mGroupId = str;
        this.mSpaceId = str2;
        this.mLeaderId = str3;
    }

    public long getContentUpdatedTime() {
        return this.mContentUpdatedTime;
    }

    public Uri getCoverThumbnailFileUri() {
        return this.mCoverThumbnailFileUri;
    }

    public long getCreatedTime() {
        return this.mCreatedTime;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public String getLeaderId() {
        return this.mLeaderId;
    }

    public String getMemo() {
        return this.mMemo;
    }

    public Map getMetaData() {
        return this.mMetaData;
    }

    public long getModifiedTime() {
        return this.mModifiedTime;
    }

    public long getMyCountUsage() {
        return this.mMyCountUsage;
    }

    public long getMyUsage() {
        return this.mMyUsage;
    }

    public long getSize() {
        return this.mSize;
    }

    public String getSourceCid() {
        return this.mSourceCid;
    }

    public String getSpaceId() {
        return this.mSpaceId;
    }

    public boolean getStandAlone() {
        return this.mIsStandAlone;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getUnreadCount() {
        return this.mUnreadCount;
    }

    public WebLink getWebLink() {
        return this.mWebLink;
    }

    public boolean isOwnedByMe() {
        return this.mIsOwnedByMe;
    }

    public void setContentUpdatedTime(long j2) {
        this.mContentUpdatedTime = j2;
    }

    public void setCoverThumbnailFileUri(Uri uri) {
        this.mCoverThumbnailFileUri = uri;
    }

    public void setCreatedTime(long j2) {
        this.mCreatedTime = j2;
    }

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    public void setItemCount(int i2) {
        this.mItemCount = i2;
    }

    public void setLeaderId(String str) {
        this.mLeaderId = str;
    }

    public void setMemo(String str) {
        this.mMemo = str;
    }

    public void setMetaData(Map map) {
        this.mMetaData = map;
    }

    public void setModifiedTime(long j2) {
        this.mModifiedTime = j2;
    }

    public void setMyCountUsage(long j2) {
        this.mMyCountUsage = j2;
    }

    public void setMyUsage(long j2) {
        this.mMyUsage = j2;
    }

    public void setOwnedByMe(boolean z) {
        this.mIsOwnedByMe = z;
    }

    public void setSize(long j2) {
        this.mSize = j2;
    }

    public void setSourceCid(String str) {
        this.mSourceCid = str;
    }

    public void setSpaceId(String str) {
        this.mSpaceId = str;
    }

    public void setStandAlone(boolean z) {
        this.mIsStandAlone = z;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setUnreadCount(int i2) {
        this.mUnreadCount = i2;
    }

    public void setWebLink(WebLink webLink) {
        this.mWebLink = webLink;
    }
}
