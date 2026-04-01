package com.samsung.android.sdk.globalpostprocmgr;

import android.content.ContentValues;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PPPipelineData implements IGPPDBInterface.IPipelineColumns {
    private long mId;
    private String mName;
    private String mPluginList;
    private int mPriority;
    private long mUpdateTime;
    private long mVersion;

    public PPPipelineData(long j2, String str, String str2, int i2, long j3, long j8) {
        this.mId = j2;
        this.mName = str;
        this.mPluginList = str2;
        this.mPriority = i2;
        this.mVersion = j3;
        this.mUpdateTime = j8;
    }

    public ContentValues getContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.mName);
        contentValues.put(IGPPDBInterface.IPipelineColumns.FIELD_PIPELINE_LIST, this.mPluginList);
        contentValues.put("priority", Integer.valueOf(this.mPriority));
        contentValues.put("version", Long.valueOf(this.mVersion));
        contentValues.put("update_time", Long.valueOf(this.mUpdateTime));
        return contentValues;
    }

    public long getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getPluginList() {
        return this.mPluginList;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public long getVersion() {
        return this.mVersion;
    }

    public void setId(long j2) {
        this.mId = j2;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setPluginList(String str) {
        this.mPluginList = str;
    }

    public void setPriority(int i2) {
        this.mPriority = i2;
    }

    public void setUpdateTime(long j2) {
        this.mUpdateTime = j2;
    }

    public void setVersion(long j2) {
        this.mVersion = j2;
    }

    public String toString() {
        return "PPPipelineData{mId=" + this.mId + ", mName='" + this.mName + "', mPluginList='" + this.mPluginList + "', mPriority=" + this.mPriority + ", mVersion=" + this.mVersion + ", mUpdateTime=" + this.mUpdateTime + '}';
    }

    public PPPipelineData(String str, String str2, int i2, long j2, long j3) {
        this.mName = str;
        this.mPluginList = str2;
        this.mPriority = i2;
        this.mVersion = j2;
        this.mUpdateTime = j3;
    }
}
