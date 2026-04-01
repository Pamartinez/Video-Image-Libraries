package androidx.appsearch.usagereporting;

import androidx.appsearch.usagereporting.TakenAction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DismissAction extends TakenAction {
    private final String mQuery;
    private final String mReferencedQualifiedId;
    private final int mResultRankGlobal;
    private final int mResultRankInBlock;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends TakenAction.BuilderImpl<Builder> {
        private String mQuery;
        private String mReferencedQualifiedId;
        private int mResultRankGlobal = -1;
        private int mResultRankInBlock = -1;

        public Builder(String str, String str2, long j2, int i2) {
            super(str, str2, j2, i2);
        }

        public DismissAction build() {
            return new DismissAction(this.mNamespace, this.mId, this.mDocumentTtlMillis, this.mActionTimestampMillis, this.mActionType, this.mQuery, this.mReferencedQualifiedId, this.mResultRankInBlock, this.mResultRankGlobal);
        }

        public /* bridge */ /* synthetic */ TakenAction.BuilderImpl setDocumentTtlMillis(long j2) {
            return super.setDocumentTtlMillis(j2);
        }

        public Builder setQuery(String str) {
            this.mQuery = str;
            return this;
        }

        public Builder setReferencedQualifiedId(String str) {
            this.mReferencedQualifiedId = str;
            return this;
        }

        public Builder setResultRankGlobal(int i2) {
            this.mResultRankGlobal = i2;
            return this;
        }

        public Builder setResultRankInBlock(int i2) {
            this.mResultRankInBlock = i2;
            return this;
        }
    }

    public DismissAction(String str, String str2, long j2, long j3, int i2, String str3, String str4, int i7, int i8) {
        super(str, str2, j2, j3, i2);
        this.mQuery = str3;
        this.mReferencedQualifiedId = str4;
        this.mResultRankInBlock = i7;
        this.mResultRankGlobal = i8;
    }
}
