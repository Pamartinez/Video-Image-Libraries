package androidx.appsearch.app;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.safeparcel.stub.StubCreators$SearchSpecCreator;
import androidx.appsearch.util.BundleUtil;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchSpec extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SearchSpec> CREATOR = new StubCreators$SearchSpecCreator();
    private final String mAdvancedRankingExpression;
    private final int mDefaultEmbeddingSearchMetricType;
    private final List<EmbeddingVector> mEmbeddingParameters;
    private final List<String> mEnabledFeatures;
    private final List<String> mFilterDocumentIds;
    private final int mGroupingLimit;
    private final List<String> mInformationalRankingExpressions;
    private final int mMaxSnippetSize;
    private final List<String> mNamespaces;
    private final int mOrder;
    private final List<String> mPackageNames;
    final Bundle mProjectionTypePropertyMasks;
    private final int mRankingStrategy;
    private final int mResultCountPerPage;
    private final int mResultGroupingTypeFlags;
    private final boolean mRetrieveEmbeddingMatchInfos;
    private final List<String> mSchemas;
    private final String mSearchSourceLogTag;
    private final List<String> mSearchStringParameters;
    private final int mSnippetCount;
    private final int mSnippetCountPerProperty;
    private final int mTermMatchType;
    final Bundle mTypePropertyFilters;
    final Bundle mTypePropertyWeightsField;

    public SearchSpec(int i2, List<String> list, List<String> list2, Bundle bundle, List<String> list3, int i7, int i8, int i10, int i11, int i12, int i13, Bundle bundle2, int i14, int i15, Bundle bundle3, JoinSpec joinSpec, String str, List<String> list4, String str2, List<EmbeddingVector> list5, int i16, List<String> list6, List<String> list7, List<String> list8, boolean z) {
        List<String> list9;
        List<String> list10;
        this.mTermMatchType = i2;
        this.mSchemas = Collections.unmodifiableList((List) Preconditions.checkNotNull(list));
        this.mNamespaces = Collections.unmodifiableList((List) Preconditions.checkNotNull(list2));
        this.mTypePropertyFilters = (Bundle) Preconditions.checkNotNull(bundle);
        this.mPackageNames = Collections.unmodifiableList((List) Preconditions.checkNotNull(list3));
        this.mResultCountPerPage = i7;
        this.mRankingStrategy = i8;
        this.mOrder = i10;
        this.mSnippetCount = i11;
        this.mSnippetCountPerProperty = i12;
        this.mMaxSnippetSize = i13;
        this.mProjectionTypePropertyMasks = (Bundle) Preconditions.checkNotNull(bundle2);
        this.mResultGroupingTypeFlags = i14;
        this.mGroupingLimit = i15;
        this.mTypePropertyWeightsField = (Bundle) Preconditions.checkNotNull(bundle3);
        this.mAdvancedRankingExpression = (String) Preconditions.checkNotNull(str);
        this.mEnabledFeatures = Collections.unmodifiableList((List) Preconditions.checkNotNull(list4));
        this.mSearchSourceLogTag = str2;
        if (list5 != null) {
            this.mEmbeddingParameters = Collections.unmodifiableList(list5);
        } else {
            this.mEmbeddingParameters = Collections.EMPTY_LIST;
        }
        this.mDefaultEmbeddingSearchMetricType = i16;
        if (list6 != null) {
            this.mInformationalRankingExpressions = Collections.unmodifiableList(list6);
        } else {
            this.mInformationalRankingExpressions = Collections.EMPTY_LIST;
        }
        if (list7 != null) {
            list9 = Collections.unmodifiableList(list7);
        } else {
            list9 = Collections.EMPTY_LIST;
        }
        this.mSearchStringParameters = list9;
        if (list8 != null) {
            list10 = Collections.unmodifiableList(list8);
        } else {
            list10 = Collections.EMPTY_LIST;
        }
        this.mFilterDocumentIds = list10;
        this.mRetrieveEmbeddingMatchInfos = z;
    }

    public String getAdvancedRankingExpression() {
        return this.mAdvancedRankingExpression;
    }

    public int getDefaultEmbeddingSearchMetricType() {
        return this.mDefaultEmbeddingSearchMetricType;
    }

    public List<EmbeddingVector> getEmbeddingParameters() {
        return this.mEmbeddingParameters;
    }

    public List<String> getEnabledFeatures() {
        return this.mEnabledFeatures;
    }

    public List<String> getFilterDocumentIds() {
        return this.mFilterDocumentIds;
    }

    public List<String> getFilterNamespaces() {
        List<String> list = this.mNamespaces;
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return list;
    }

    public List<String> getFilterPackageNames() {
        List<String> list = this.mPackageNames;
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return list;
    }

    public Map<String, List<String>> getFilterProperties() {
        Set<String> keySet = this.mTypePropertyFilters.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String next : keySet) {
            arrayMap.put(next, (List) Preconditions.checkNotNull(this.mTypePropertyFilters.getStringArrayList(next)));
        }
        return arrayMap;
    }

    public List<String> getFilterSchemas() {
        List<String> list = this.mSchemas;
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return list;
    }

    public List<String> getInformationalRankingExpressions() {
        return this.mInformationalRankingExpressions;
    }

    public JoinSpec getJoinSpec() {
        return null;
    }

    public int getMaxSnippetSize() {
        return this.mMaxSnippetSize;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public Map<String, List<String>> getProjections() {
        Set<String> keySet = this.mProjectionTypePropertyMasks.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String next : keySet) {
            ArrayList<String> stringArrayList = this.mProjectionTypePropertyMasks.getStringArrayList(next);
            Objects.requireNonNull(stringArrayList);
            arrayMap.put(next, stringArrayList);
        }
        return arrayMap;
    }

    public Map<String, Map<String, Double>> getPropertyWeights() {
        Set<String> keySet = this.mTypePropertyWeightsField.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String next : keySet) {
            Bundle bundle = this.mTypePropertyWeightsField.getBundle(next);
            if (bundle != null) {
                Set<String> keySet2 = bundle.keySet();
                ArrayMap arrayMap2 = new ArrayMap(keySet2.size());
                for (String next2 : keySet2) {
                    arrayMap2.put(next2, Double.valueOf(bundle.getDouble(next2)));
                }
                arrayMap.put(next, arrayMap2);
            }
        }
        return arrayMap;
    }

    public int getRankingStrategy() {
        return this.mRankingStrategy;
    }

    public int getResultCountPerPage() {
        return this.mResultCountPerPage;
    }

    public int getResultGroupingLimit() {
        return this.mGroupingLimit;
    }

    public int getResultGroupingTypeFlags() {
        return this.mResultGroupingTypeFlags;
    }

    public String getSearchSourceLogTag() {
        return this.mSearchSourceLogTag;
    }

    public List<String> getSearchStringParameters() {
        return this.mSearchStringParameters;
    }

    public int getSnippetCount() {
        return this.mSnippetCount;
    }

    public int getSnippetCountPerProperty() {
        return this.mSnippetCountPerProperty;
    }

    public int getTermMatch() {
        return this.mTermMatchType;
    }

    public boolean isListFilterHasPropertyFunctionEnabled() {
        return this.mEnabledFeatures.contains("LIST_FILTER_HAS_PROPERTY_FUNCTION");
    }

    public boolean isListFilterMatchScoreExpressionFunctionEnabled() {
        return this.mEnabledFeatures.contains("LIST_FILTER_MATCH_SCORE_EXPRESSION_FUNCTION");
    }

    public boolean isListFilterQueryLanguageEnabled() {
        return this.mEnabledFeatures.contains("LIST_FILTER_QUERY_LANGUAGE");
    }

    public boolean isNumericSearchEnabled() {
        return this.mEnabledFeatures.contains("NUMERIC_SEARCH");
    }

    public boolean isScorablePropertyRankingEnabled() {
        return this.mEnabledFeatures.contains("SCHEMA_SCORABLE_PROPERTY_CONFIG");
    }

    public boolean isVerbatimSearchEnabled() {
        return this.mEnabledFeatures.contains("VERBATIM_SEARCH");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String mAdvancedRankingExpression = "";
        private boolean mBuilt = false;
        private int mDefaultEmbeddingSearchMetricType = 1;
        private List<EmbeddingVector> mEmbeddingParameters = new ArrayList();
        private ArraySet<String> mEnabledFeatures = new ArraySet<>();
        private List<String> mFilterDocumentIds = new ArrayList();
        private int mGroupingLimit = 0;
        private int mGroupingTypeFlags = 0;
        private List<String> mInformationalRankingExpressions = new ArrayList();
        private int mMaxSnippetSize = 0;
        private List<String> mNamespaces = new ArrayList();
        private int mOrder = 0;
        private List<String> mPackageNames = new ArrayList();
        private Bundle mProjectionTypePropertyMasks = new Bundle();
        private int mRankingStrategy = 0;
        private int mResultCountPerPage = 10;
        private boolean mRetrieveEmbeddingMatchInfos = false;
        private List<String> mSchemas = new ArrayList();
        private String mSearchSourceLogTag;
        private List<String> mSearchStringParameters = new ArrayList();
        private int mSnippetCount = 0;
        private int mSnippetCountPerProperty = 10000;
        private int mTermMatchType = 2;
        private Bundle mTypePropertyFilters = new Bundle();
        private Bundle mTypePropertyWeights = new Bundle();

        private void modifyEnabledFeature(String str, boolean z) {
            resetIfBuilt();
            if (z) {
                this.mEnabledFeatures.add(str);
            } else {
                this.mEnabledFeatures.remove(str);
            }
        }

        private void resetIfBuilt() {
            if (this.mBuilt) {
                this.mSchemas = new ArrayList(this.mSchemas);
                this.mTypePropertyFilters = BundleUtil.deepCopy(this.mTypePropertyFilters);
                this.mNamespaces = new ArrayList(this.mNamespaces);
                this.mPackageNames = new ArrayList(this.mPackageNames);
                this.mProjectionTypePropertyMasks = BundleUtil.deepCopy(this.mProjectionTypePropertyMasks);
                this.mTypePropertyWeights = BundleUtil.deepCopy(this.mTypePropertyWeights);
                this.mEmbeddingParameters = new ArrayList(this.mEmbeddingParameters);
                this.mInformationalRankingExpressions = new ArrayList(this.mInformationalRankingExpressions);
                this.mSearchStringParameters = new ArrayList(this.mSearchStringParameters);
                this.mFilterDocumentIds = new ArrayList(this.mFilterDocumentIds);
                this.mBuilt = false;
            }
        }

        public Builder addFilterDocumentClasses(Collection<? extends Class<?>> collection) {
            Preconditions.checkNotNull(collection);
            resetIfBuilt();
            ArrayList arrayList = new ArrayList(collection.size());
            DocumentClassFactoryRegistry instance = DocumentClassFactoryRegistry.getInstance();
            for (Class orCreateFactory : collection) {
                arrayList.add(instance.getOrCreateFactory(orCreateFactory).getSchemaName());
            }
            addFilterSchemas(arrayList);
            return this;
        }

        public Builder addFilterNamespaces(String... strArr) {
            Preconditions.checkNotNull(strArr);
            resetIfBuilt();
            return addFilterNamespaces((Collection<String>) Arrays.asList(strArr));
        }

        public Builder addFilterPackageNames(String... strArr) {
            Preconditions.checkNotNull(strArr);
            resetIfBuilt();
            return addFilterPackageNames((Collection<String>) Arrays.asList(strArr));
        }

        public Builder addFilterSchemas(Collection<String> collection) {
            Preconditions.checkNotNull(collection);
            resetIfBuilt();
            this.mSchemas.addAll(collection);
            return this;
        }

        public SearchSpec build() {
            int i2;
            if (this.mRankingStrategy == 8) {
                throw new IllegalStateException("Attempting to rank based on joined documents, but no JoinSpec provided");
            } else if (this.mTypePropertyWeights.isEmpty() || (i2 = this.mRankingStrategy) == 3 || i2 == 9) {
                this.mBuilt = true;
                int i7 = this.mTermMatchType;
                List<String> list = this.mSchemas;
                List<String> list2 = this.mNamespaces;
                Bundle bundle = this.mTypePropertyFilters;
                List<String> list3 = this.mPackageNames;
                int i8 = this.mResultCountPerPage;
                int i10 = this.mRankingStrategy;
                int i11 = this.mOrder;
                int i12 = this.mSnippetCount;
                int i13 = this.mSnippetCountPerProperty;
                int i14 = this.mMaxSnippetSize;
                Bundle bundle2 = this.mProjectionTypePropertyMasks;
                int i15 = this.mGroupingTypeFlags;
                int i16 = this.mGroupingLimit;
                Bundle bundle3 = this.mTypePropertyWeights;
                String str = this.mAdvancedRankingExpression;
                ArrayList arrayList = new ArrayList(this.mEnabledFeatures);
                return new SearchSpec(i7, list, list2, bundle, list3, i8, i10, i11, i12, i13, i14, bundle2, i15, i16, bundle3, (JoinSpec) null, str, arrayList, this.mSearchSourceLogTag, this.mEmbeddingParameters, this.mDefaultEmbeddingSearchMetricType, this.mInformationalRankingExpressions, this.mSearchStringParameters, this.mFilterDocumentIds, this.mRetrieveEmbeddingMatchInfos);
            } else {
                throw new IllegalArgumentException("Property weights are only compatible with the RANKING_STRATEGY_RELEVANCE_SCORE and RANKING_STRATEGY_ADVANCED_RANKING_EXPRESSION ranking strategies.");
            }
        }

        public Builder setListFilterQueryLanguageEnabled(boolean z) {
            modifyEnabledFeature("LIST_FILTER_QUERY_LANGUAGE", z);
            return this;
        }

        public Builder setNumericSearchEnabled(boolean z) {
            modifyEnabledFeature("NUMERIC_SEARCH", z);
            return this;
        }

        public Builder setVerbatimSearchEnabled(boolean z) {
            modifyEnabledFeature("VERBATIM_SEARCH", z);
            return this;
        }

        public Builder addFilterNamespaces(Collection<String> collection) {
            Preconditions.checkNotNull(collection);
            resetIfBuilt();
            this.mNamespaces.addAll(collection);
            return this;
        }

        public Builder addFilterPackageNames(Collection<String> collection) {
            Preconditions.checkNotNull(collection);
            resetIfBuilt();
            this.mPackageNames.addAll(collection);
            return this;
        }

        public Builder addFilterDocumentClasses(Class<?>... clsArr) {
            Preconditions.checkNotNull(clsArr);
            resetIfBuilt();
            return addFilterDocumentClasses((Collection<? extends Class<?>>) Arrays.asList(clsArr));
        }
    }
}
