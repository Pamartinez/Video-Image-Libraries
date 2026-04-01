package androidx.appsearch.usagereporting;

import androidx.appsearch.usagereporting.TakenAction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAction extends TakenAction {
    private final int mFetchedResultCount;
    private final String mQuery;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends TakenAction.BuilderImpl<Builder> {
        private int mFetchedResultCount = -1;
        private String mQuery;

        public Builder(String str, String str2, long j2, int i2) {
            super(str, str2, j2, i2);
        }

        public SearchAction build() {
            return new SearchAction(this.mNamespace, this.mId, this.mDocumentTtlMillis, this.mActionTimestampMillis, this.mActionType, this.mQuery, this.mFetchedResultCount);
        }

        public /* bridge */ /* synthetic */ TakenAction.BuilderImpl setDocumentTtlMillis(long j2) {
            return super.setDocumentTtlMillis(j2);
        }

        public Builder setFetchedResultCount(int i2) {
            this.mFetchedResultCount = i2;
            return this;
        }

        public Builder setQuery(String str) {
            this.mQuery = str;
            return this;
        }
    }

    public SearchAction(String str, String str2, long j2, long j3, int i2, String str3, int i7) {
        super(str, str2, j2, j3, i2);
        this.mQuery = str3;
        this.mFetchedResultCount = i7;
    }
}
