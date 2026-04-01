package com.samsung.android.sdk.scs.ai.visual.c2pa;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paResult {
    private String errorString;
    private boolean isCompleted;
    private boolean isSuccess;
    private boolean isTrusted;
    private String manifestResult;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String errorString = "";
        /* access modifiers changed from: private */
        public boolean isCompleted = false;
        /* access modifiers changed from: private */
        public boolean isSuccess = false;
        /* access modifiers changed from: private */
        public boolean isTrusted = false;
        /* access modifiers changed from: private */
        public String manifestResult = "";

        public C2paResult build() {
            return new C2paResult(this, 0);
        }

        public Builder setCompleted(boolean z) {
            this.isCompleted = z;
            return this;
        }

        public Builder setError(String str) {
            this.errorString = str;
            return this;
        }

        public Builder setManifestResult(String str) {
            this.manifestResult = str;
            return this;
        }

        public Builder setSuccess(boolean z) {
            this.isSuccess = z;
            return this;
        }

        public Builder setTrusted(boolean z) {
            this.isTrusted = z;
            return this;
        }
    }

    public /* synthetic */ C2paResult(Builder builder, int i2) {
        this(builder);
    }

    public String getError() {
        return this.errorString;
    }

    public String getManifestResult() {
        return this.manifestResult;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public boolean isTrusted() {
        return this.isTrusted;
    }

    private C2paResult(Builder builder) {
        this.isSuccess = builder.isSuccess;
        this.isTrusted = builder.isTrusted;
        this.isCompleted = builder.isCompleted;
        this.manifestResult = builder.manifestResult;
        this.errorString = builder.errorString;
    }
}
