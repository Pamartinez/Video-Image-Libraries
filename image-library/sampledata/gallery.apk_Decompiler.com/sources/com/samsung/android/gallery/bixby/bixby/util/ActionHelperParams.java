package com.samsung.android.gallery.bixby.bixby.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionHelperParams {
    private final int mFlag;
    private final String mName;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public int mFlag = 0;
        /* access modifiers changed from: private */
        public String mName = null;

        public Builder addFlag(int i2) {
            this.mFlag = i2 | this.mFlag;
            return this;
        }

        public ActionHelperParams build() {
            return new ActionHelperParams(this, 0);
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }
    }

    public /* synthetic */ ActionHelperParams(Builder builder, int i2) {
        this(builder);
    }

    public String getName() {
        return this.mName;
    }

    public boolean isExcludeEmpty() {
        if ((this.mFlag & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean isExcludeVirtual() {
        if ((this.mFlag & 16) == 16) {
            return true;
        }
        return false;
    }

    public boolean isIncludeAlbumOnly() {
        if ((this.mFlag & 256) == 256) {
            return true;
        }
        return false;
    }

    public boolean isIncludeGroupOnly() {
        if ((this.mFlag & 4096) == 4096) {
            return true;
        }
        return false;
    }

    public boolean usePattern() {
        if ((this.mFlag & 65536) == 65536) {
            return true;
        }
        return false;
    }

    private ActionHelperParams(Builder builder) {
        this.mFlag = builder.mFlag;
        this.mName = builder.mName;
    }
}
