package androidx.appsearch.app;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.stub.StubCreators$MatchInfoCreator;
import androidx.appsearch.safeparcel.stub.StubCreators$SearchResultCreator;
import androidx.appsearch.safeparcel.stub.StubCreators$TextMatchInfoCreator;
import androidx.appsearch.util.BundleUtil;
import androidx.collection.ArrayMap;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SearchResult> CREATOR = new StubCreators$SearchResultCreator();
    private final String mDatabaseName;
    final GenericDocumentParcel mDocument;
    private GenericDocument mDocumentCached;
    private final List<Double> mInformationalRankingSignals;
    private final List<SearchResult> mJoinedResults;
    final List<MatchInfo> mMatchInfos;
    private List<MatchInfo> mMatchInfosCached;
    private final String mPackageName;
    final Bundle mParentTypeMap;
    private final double mRankingSignal;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class EmbeddingMatchInfo extends AbstractSafeParcelable {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MatchRange {
        private final int mEnd;
        private final int mStart;

        public MatchRange(int i2, int i7) {
            if (i2 <= i7) {
                this.mStart = i2;
                this.mEnd = i7;
                return;
            }
            throw new IllegalArgumentException("Start point must be less than or equal to end point");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MatchRange)) {
                return false;
            }
            MatchRange matchRange = (MatchRange) obj;
            if (getStart() == matchRange.getStart() && getEnd() == matchRange.getEnd()) {
                return true;
            }
            return false;
        }

        public int getEnd() {
            return this.mEnd;
        }

        public int getStart() {
            return this.mStart;
        }

        public int hashCode() {
            return ObjectsCompat.hash(Integer.valueOf(this.mStart), Integer.valueOf(this.mEnd));
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("MatchRange { start: ");
            sb2.append(this.mStart);
            sb2.append(" , end: ");
            return C0086a.l(sb2, this.mEnd, "}");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TextMatchInfo extends AbstractSafeParcelable {
        public static final Parcelable.Creator<TextMatchInfo> CREATOR = new StubCreators$TextMatchInfoCreator();
        private GenericDocument mDocument = null;
        private MatchRange mExactMatchRangeCached;
        final int mExactMatchRangeEnd;
        final int mExactMatchRangeStart;
        private String mPropertyPath = null;
        final int mSnippetRangeEnd;
        final int mSnippetRangeStart;
        final int mSubmatchRangeEnd;
        final int mSubmatchRangeStart;
        private MatchRange mWindowRangeCached;

        public TextMatchInfo(int i2, int i7, int i8, int i10, int i11, int i12) {
            this.mExactMatchRangeStart = i2;
            this.mExactMatchRangeEnd = i7;
            this.mSubmatchRangeStart = i8;
            this.mSubmatchRangeEnd = i10;
            this.mSnippetRangeStart = i11;
            this.mSnippetRangeEnd = i12;
        }

        public MatchRange getExactMatchRange() {
            if (this.mExactMatchRangeCached == null) {
                this.mExactMatchRangeCached = new MatchRange(this.mExactMatchRangeStart, this.mExactMatchRangeEnd);
            }
            return this.mExactMatchRangeCached;
        }

        public MatchRange getSnippetRange() {
            if (this.mWindowRangeCached == null) {
                this.mWindowRangeCached = new MatchRange(this.mSnippetRangeStart, this.mSnippetRangeEnd);
            }
            return this.mWindowRangeCached;
        }

        public void setDocument(GenericDocument genericDocument) {
            this.mDocument = (GenericDocument) Preconditions.checkNotNull(genericDocument);
        }

        public void setPropertyPath(String str) {
            this.mPropertyPath = (String) Preconditions.checkNotNull(str);
        }
    }

    public SearchResult(GenericDocumentParcel genericDocumentParcel, List<MatchInfo> list, String str, String str2, double d, List<SearchResult> list2, List<Double> list3, Bundle bundle) {
        this.mDocument = (GenericDocumentParcel) Preconditions.checkNotNull(genericDocumentParcel);
        this.mMatchInfos = (List) Preconditions.checkNotNull(list);
        this.mPackageName = (String) Preconditions.checkNotNull(str);
        this.mDatabaseName = (String) Preconditions.checkNotNull(str2);
        this.mRankingSignal = d;
        this.mJoinedResults = Collections.unmodifiableList((List) Preconditions.checkNotNull(list2));
        if (list3 != null) {
            this.mInformationalRankingSignals = Collections.unmodifiableList(list3);
        } else {
            this.mInformationalRankingSignals = Collections.EMPTY_LIST;
        }
        if (bundle != null) {
            this.mParentTypeMap = bundle;
        } else {
            this.mParentTypeMap = Bundle.EMPTY;
        }
    }

    public String getDatabaseName() {
        return this.mDatabaseName;
    }

    public GenericDocument getGenericDocument() {
        if (this.mDocumentCached == null) {
            this.mDocumentCached = new GenericDocument(this.mDocument);
        }
        return this.mDocumentCached;
    }

    public List<Double> getInformationalRankingSignals() {
        return this.mInformationalRankingSignals;
    }

    public List<SearchResult> getJoinedResults() {
        return this.mJoinedResults;
    }

    public List<MatchInfo> getMatchInfos() {
        if (this.mMatchInfosCached == null) {
            this.mMatchInfosCached = new ArrayList(this.mMatchInfos.size());
            for (int i2 = 0; i2 < this.mMatchInfos.size(); i2++) {
                MatchInfo matchInfo = this.mMatchInfos.get(i2);
                matchInfo.setDocument(getGenericDocument());
                if (matchInfo.getTextMatch() != null) {
                    matchInfo.mTextMatch.setPropertyPath(matchInfo.getPropertyPath());
                }
                List<MatchInfo> list = this.mMatchInfosCached;
                if (list != null) {
                    list.add(matchInfo);
                }
            }
            this.mMatchInfosCached = Collections.unmodifiableList(this.mMatchInfosCached);
        }
        return (List) Preconditions.checkNotNull(this.mMatchInfosCached);
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public Map<String, List<String>> getParentTypeMap() {
        Set<String> keySet = this.mParentTypeMap.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String next : keySet) {
            ArrayList<String> stringArrayList = this.mParentTypeMap.getStringArrayList(next);
            if (stringArrayList != null) {
                arrayMap.put(next, stringArrayList);
            }
        }
        return arrayMap;
    }

    public double getRankingSignal() {
        return this.mRankingSignal;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MatchInfo extends AbstractSafeParcelable {
        public static final Parcelable.Creator<MatchInfo> CREATOR = new StubCreators$MatchInfoCreator();
        /* access modifiers changed from: private */
        public GenericDocument mDocument = null;
        final int mExactMatchRangeEnd;
        final int mExactMatchRangeStart;
        /* access modifiers changed from: private */
        public final String mPropertyPath;
        private PropertyPath mPropertyPathObject = null;
        final int mSnippetRangeEnd;
        final int mSnippetRangeStart;
        final int mSubmatchRangeEnd;
        final int mSubmatchRangeStart;
        /* access modifiers changed from: private */
        public final TextMatchInfo mTextMatch;

        public MatchInfo(String str, int i2, int i7, int i8, int i10, int i11, int i12, TextMatchInfo textMatchInfo, EmbeddingMatchInfo embeddingMatchInfo) {
            String str2 = (String) Preconditions.checkNotNull(str);
            this.mPropertyPath = str2;
            this.mExactMatchRangeStart = i2;
            this.mExactMatchRangeEnd = i7;
            this.mSubmatchRangeStart = i8;
            this.mSubmatchRangeEnd = i10;
            this.mSnippetRangeStart = i11;
            this.mSnippetRangeEnd = i12;
            if (textMatchInfo == null) {
                int i13 = i12;
                int i14 = i11;
                int i15 = i10;
                TextMatchInfo textMatchInfo2 = new TextMatchInfo(i2, i7, i8, i15, i14, i13);
                textMatchInfo2.setPropertyPath(str2);
                textMatchInfo = textMatchInfo2;
            }
            this.mTextMatch = textMatchInfo;
        }

        public EmbeddingMatchInfo getEmbeddingMatch() {
            return null;
        }

        public MatchRange getExactMatchRange() {
            TextMatchInfo textMatchInfo = this.mTextMatch;
            if (textMatchInfo == null) {
                return new MatchRange(0, 0);
            }
            return textMatchInfo.getExactMatchRange();
        }

        public String getPropertyPath() {
            return this.mPropertyPath;
        }

        public MatchRange getSnippetRange() {
            TextMatchInfo textMatchInfo = this.mTextMatch;
            if (textMatchInfo == null) {
                return new MatchRange(0, 0);
            }
            return textMatchInfo.getSnippetRange();
        }

        public TextMatchInfo getTextMatch() {
            return this.mTextMatch;
        }

        public void setDocument(GenericDocument genericDocument) {
            this.mDocument = (GenericDocument) Preconditions.checkNotNull(genericDocument);
            TextMatchInfo textMatchInfo = this.mTextMatch;
            if (textMatchInfo != null) {
                textMatchInfo.setDocument(genericDocument);
            }
        }

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Builder {
            private MatchRange mExactMatchRange = new MatchRange(0, 0);
            private final String mPropertyPath;
            private MatchRange mSnippetRange = new MatchRange(0, 0);
            private MatchRange mSubmatchRange = new MatchRange(-1, -1);

            public Builder(String str) {
                this.mPropertyPath = (String) Preconditions.checkNotNull(str);
            }

            public MatchInfo build() {
                TextMatchInfo textMatchInfo = new TextMatchInfo(this.mExactMatchRange.getStart(), this.mExactMatchRange.getEnd(), this.mSubmatchRange.getStart(), this.mSubmatchRange.getEnd(), this.mSnippetRange.getStart(), this.mSnippetRange.getEnd());
                textMatchInfo.setPropertyPath(this.mPropertyPath);
                return new MatchInfo(this.mPropertyPath, this.mExactMatchRange.getStart(), this.mExactMatchRange.getEnd(), this.mSubmatchRange.getStart(), this.mSubmatchRange.getEnd(), this.mSnippetRange.getStart(), this.mSnippetRange.getEnd(), textMatchInfo, (EmbeddingMatchInfo) null);
            }

            public Builder setExactMatchRange(MatchRange matchRange) {
                this.mExactMatchRange = (MatchRange) Preconditions.checkNotNull(matchRange);
                return this;
            }

            public Builder setSnippetRange(MatchRange matchRange) {
                this.mSnippetRange = (MatchRange) Preconditions.checkNotNull(matchRange);
                return this;
            }

            public Builder setSubmatchRange(MatchRange matchRange) {
                this.mSubmatchRange = (MatchRange) Preconditions.checkNotNull(matchRange);
                return this;
            }

            public Builder(MatchInfo matchInfo) {
                Preconditions.checkNotNull(matchInfo);
                this.mPropertyPath = matchInfo.mPropertyPath;
                matchInfo.getEmbeddingMatch();
                this.mExactMatchRange = matchInfo.getExactMatchRange();
                this.mSubmatchRange = new MatchRange(matchInfo.mSubmatchRangeStart, matchInfo.mSubmatchRangeEnd);
                this.mSnippetRange = matchInfo.getSnippetRange();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean mBuilt;
        private final String mDatabaseName;
        private GenericDocument mGenericDocument;
        private List<Double> mInformationalRankingSignals;
        private List<SearchResult> mJoinedResults;
        private List<MatchInfo> mMatchInfos;
        private final String mPackageName;
        private Bundle mParentTypeMap;
        private double mRankingSignal;

        public Builder(String str, String str2) {
            this.mMatchInfos = new ArrayList();
            this.mInformationalRankingSignals = new ArrayList();
            this.mParentTypeMap = new Bundle();
            this.mJoinedResults = new ArrayList();
            this.mBuilt = false;
            this.mPackageName = (String) Preconditions.checkNotNull(str);
            this.mDatabaseName = (String) Preconditions.checkNotNull(str2);
        }

        private void resetIfBuilt() {
            if (this.mBuilt) {
                this.mMatchInfos = new ArrayList(this.mMatchInfos);
                this.mJoinedResults = new ArrayList(this.mJoinedResults);
                this.mInformationalRankingSignals = new ArrayList(this.mInformationalRankingSignals);
                this.mParentTypeMap = BundleUtil.deepCopy(this.mParentTypeMap);
                this.mBuilt = false;
            }
        }

        public Builder addInformationalRankingSignal(double d) {
            resetIfBuilt();
            this.mInformationalRankingSignals.add(Double.valueOf(d));
            return this;
        }

        public Builder addJoinedResult(SearchResult searchResult) {
            resetIfBuilt();
            this.mJoinedResults.add(searchResult);
            return this;
        }

        public Builder addMatchInfo(MatchInfo matchInfo) {
            boolean z;
            if (matchInfo.mDocument == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z, "This MatchInfo is already associated with a SearchResult and can't be reassigned");
            resetIfBuilt();
            this.mMatchInfos.add(matchInfo);
            return this;
        }

        public SearchResult build() {
            this.mBuilt = true;
            return new SearchResult(this.mGenericDocument.getDocumentParcel(), this.mMatchInfos, this.mPackageName, this.mDatabaseName, this.mRankingSignal, this.mJoinedResults, this.mInformationalRankingSignals, this.mParentTypeMap);
        }

        public Builder clearMatchInfos() {
            resetIfBuilt();
            this.mMatchInfos.clear();
            return this;
        }

        public Builder setGenericDocument(GenericDocument genericDocument) {
            Preconditions.checkNotNull(genericDocument);
            resetIfBuilt();
            this.mGenericDocument = genericDocument;
            return this;
        }

        public Builder setParentTypeMap(Map<String, List<String>> map) {
            Preconditions.checkNotNull(map);
            resetIfBuilt();
            this.mParentTypeMap.clear();
            for (Map.Entry next : map.entrySet()) {
                Preconditions.checkNotNull((String) next.getKey());
                Preconditions.checkNotNull((List) next.getValue());
                ArrayList arrayList = new ArrayList(((List) next.getValue()).size());
                for (int i2 = 0; i2 < ((List) next.getValue()).size(); i2++) {
                    arrayList.add((String) Preconditions.checkNotNull((String) ((List) next.getValue()).get(i2)));
                }
                this.mParentTypeMap.putStringArrayList((String) next.getKey(), arrayList);
            }
            return this;
        }

        public Builder setRankingSignal(double d) {
            resetIfBuilt();
            this.mRankingSignal = d;
            return this;
        }

        public Builder(SearchResult searchResult) {
            this.mMatchInfos = new ArrayList();
            this.mInformationalRankingSignals = new ArrayList();
            this.mParentTypeMap = new Bundle();
            this.mJoinedResults = new ArrayList();
            this.mBuilt = false;
            Preconditions.checkNotNull(searchResult);
            this.mPackageName = searchResult.getPackageName();
            this.mDatabaseName = searchResult.getDatabaseName();
            this.mGenericDocument = searchResult.getGenericDocument();
            this.mRankingSignal = searchResult.getRankingSignal();
            this.mInformationalRankingSignals = new ArrayList(searchResult.getInformationalRankingSignals());
            setParentTypeMap(searchResult.getParentTypeMap());
            List<MatchInfo> matchInfos = searchResult.getMatchInfos();
            for (int i2 = 0; i2 < matchInfos.size(); i2++) {
                addMatchInfo(new MatchInfo.Builder(matchInfos.get(i2)).build());
            }
            List<SearchResult> joinedResults = searchResult.getJoinedResults();
            for (int i7 = 0; i7 < joinedResults.size(); i7++) {
                addJoinedResult(joinedResults.get(i7));
            }
        }
    }
}
