package com.samsung.android.sdk.globalpostprocmgr;

import android.content.ContentValues;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PPRequestQueueData implements IGPPDBInterface.IRequestQueue {
    private long mDateTime;
    private String mFilePath;
    private long mId;
    private long mMediaID;
    private String mPipelineName;
    private int mPriority;
    private long mSecMediaID;
    private int mStatus;
    private long mUpdateTime;

    public PPRequestQueueData(long j2, String str, String str2, long j3, long j8, int i2, long j10, int i7, long j11) {
        this.mId = j2;
        this.mPipelineName = str;
        this.mFilePath = str2;
        this.mSecMediaID = j3;
        this.mMediaID = j8;
        this.mPriority = i2;
        this.mDateTime = j10;
        this.mStatus = i7;
        this.mUpdateTime = j11;
    }

    public ContentValues getContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_PIPELINE_NAME, this.mPipelineName);
        contentValues.put("file_path", this.mFilePath);
        contentValues.put("sec_media_id", Long.valueOf(this.mSecMediaID));
        contentValues.put("media_id", Long.valueOf(this.mMediaID));
        contentValues.put("priority", Integer.valueOf(this.mPriority));
        contentValues.put(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME, Long.valueOf(this.mDateTime));
        contentValues.put("status", Integer.valueOf(this.mStatus));
        contentValues.put("update_time", Long.valueOf(this.mUpdateTime));
        return contentValues;
    }

    public long getDateTime() {
        return this.mDateTime;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public long getId() {
        return this.mId;
    }

    public long getMediaID() {
        return this.mMediaID;
    }

    public String getPipelineName() {
        return this.mPipelineName;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public long getSecMediaID() {
        return this.mSecMediaID;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public void setDateTime(long j2) {
        this.mDateTime = j2;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public void setId(long j2) {
        this.mId = j2;
    }

    public void setMediaID(long j2) {
        this.mMediaID = j2;
    }

    public void setPipelineName(String str) {
        this.mPipelineName = str;
    }

    public void setPriority(int i2) {
        this.mPriority = i2;
    }

    public void setSecMediaID(long j2) {
        this.mSecMediaID = j2;
    }

    public void setStatus(int i2) {
        this.mStatus = i2;
    }

    public void setUpdateTime(long j2) {
        this.mUpdateTime = j2;
    }

    public String toString() {
        return "PPRequestQueueData{mId=" + this.mId + ", mPipelineName='" + this.mPipelineName + "', mFilePath='" + this.mFilePath + "', mSecMediaID=" + this.mSecMediaID + ", mMediaID=" + this.mMediaID + ", mPriority=" + this.mPriority + ", mDateTime=" + this.mDateTime + ", mStatus=" + this.mStatus + ", mUpdateTime=" + this.mUpdateTime + '}';
    }
}
