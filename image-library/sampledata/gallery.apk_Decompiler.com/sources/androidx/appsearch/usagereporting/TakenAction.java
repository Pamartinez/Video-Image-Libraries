package androidx.appsearch.usagereporting;

import androidx.core.util.Preconditions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TakenAction {
    private final long mActionTimestampMillis;
    private final int mActionType;
    private final long mDocumentTtlMillis;
    private final String mId;
    private final String mNamespace;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends BuilderImpl<Builder> {
        public Builder(String str, String str2, long j2, int i2) {
            super(str, str2, j2, i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BuilderImpl<T extends BuilderImpl<T>> {
        protected long mActionTimestampMillis;
        protected int mActionType;
        protected long mDocumentTtlMillis = 5184000000L;
        protected final String mId;
        protected final String mNamespace;

        public BuilderImpl(String str, String str2, long j2, int i2) {
            this.mNamespace = (String) Preconditions.checkNotNull(str);
            this.mId = (String) Preconditions.checkNotNull(str2);
            this.mActionTimestampMillis = j2;
            this.mActionType = i2;
        }

        public TakenAction build() {
            throw new UnsupportedOperationException();
        }

        public T setDocumentTtlMillis(long j2) {
            this.mDocumentTtlMillis = j2;
            return this;
        }
    }

    public TakenAction(String str, String str2, long j2, long j3, int i2) {
        this.mNamespace = (String) Preconditions.checkNotNull(str);
        this.mId = (String) Preconditions.checkNotNull(str2);
        this.mDocumentTtlMillis = j2;
        this.mActionTimestampMillis = j3;
        this.mActionType = i2;
    }
}
