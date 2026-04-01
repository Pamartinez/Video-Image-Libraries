package com.samsung.android.gallery.module.search.root;

import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterResultsEntity {
    private final ArrayList<String> mCategoryList = new ArrayList<>();
    private int mCount = 0;
    private String mId;
    private final String mName;
    private String mPdcId;
    private String mPdcToken;
    protected ArrayList<String> mRawLabels = new ArrayList<>();
    private long[] mTime;

    public ClusterResultsEntity(String str) {
        this.mName = str;
    }

    public void addCategory(String str) {
        this.mCategoryList.add(str);
    }

    public void addId(String str) {
        this.mId = str;
    }

    public void addPdcId(String str) {
        this.mPdcId = str;
    }

    public void addPdcToken(String str) {
        this.mPdcToken = str;
    }

    public void addRawLabel(String str) {
        this.mRawLabels.add(str);
    }

    public void addTimeInfo(long[] jArr) {
        this.mTime = jArr;
    }

    public String getCategory() {
        return this.mCategoryList.get(0);
    }

    public int getCount() {
        return this.mCount;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public ArrayList<String> getRawLabels() {
        return this.mRawLabels;
    }

    public long[] getTimeInfo() {
        return this.mTime;
    }

    public boolean isMediaType() {
        return TextUtils.equals(getCategory(), ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
    }

    public boolean isOCRs() {
        return TextUtils.equals(getCategory(), "ocrtext");
    }

    public void setRawLabel(ArrayList<String> arrayList) {
        this.mRawLabels = arrayList;
    }

    public void sumCount(int i2) {
        this.mCount += i2;
    }

    public String toString() {
        return "Entity{" + Logger.getEncodedString(this.mName) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCount + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCategoryList + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.getEncodedString((Object) this.mRawLabels) + "}";
    }
}
