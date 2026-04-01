package com.samsung.android.gallery.module.data;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxClusterItem {
    private final int mClusterIndex;
    private int mCount;
    private final int mDataVersion;
    private final boolean mIsEmpty;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public int clusterIndex;
        /* access modifiers changed from: private */
        public int count;
        /* access modifiers changed from: private */
        public int version;

        public MxClusterItem build() {
            return new MxClusterItem(this, 0);
        }

        public Builder setClusterIndex(int i2) {
            this.clusterIndex = i2;
            return this;
        }

        public Builder setCount(int i2) {
            this.count = i2;
            return this;
        }

        public Builder setDataVersion(int i2) {
            this.version = i2;
            return this;
        }
    }

    public /* synthetic */ MxClusterItem(Builder builder, int i2) {
        this(builder);
    }

    public int getClusterIndex() {
        return this.mClusterIndex;
    }

    public int getCount() {
        if (this.mIsEmpty) {
            return 1;
        }
        return this.mCount;
    }

    public boolean isEmpty() {
        return this.mIsEmpty;
    }

    public void setCount(int i2) {
        this.mCount = i2;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("MxClusterItem{");
        sb2.append(this.mClusterIndex);
        sb2.append(":V");
        sb2.append(this.mDataVersion);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mCount);
        if (this.mIsEmpty) {
            str = ",empty";
        } else {
            str = "";
        }
        return C0212a.p(sb2, str, "}");
    }

    private MxClusterItem(Builder builder) {
        this.mClusterIndex = builder.clusterIndex;
        this.mCount = builder.count;
        this.mDataVersion = builder.version;
        this.mIsEmpty = builder.version > 0 && this.mCount == 0;
    }
}
