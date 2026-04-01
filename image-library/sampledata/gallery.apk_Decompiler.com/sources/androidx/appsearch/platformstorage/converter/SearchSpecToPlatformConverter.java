package androidx.appsearch.platformstorage.converter;

import android.app.appsearch.SearchSpec;
import android.content.Context;
import android.os.Build;
import androidx.appsearch.app.EmbeddingVector;
import androidx.appsearch.app.JoinSpec;
import androidx.appsearch.platformstorage.util.AppSearchVersionUtil;
import androidx.core.util.Preconditions;
import i.C0213b;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import p.C0251a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchSpecToPlatformConverter {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ApiHelperForB {
        public static void addEmbeddingParameters(SearchSpec.Builder builder, List<EmbeddingVector> list) {
            android.app.appsearch.EmbeddingVector[] embeddingVectorArr = new android.app.appsearch.EmbeddingVector[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                C0213b.r();
                embeddingVectorArr[i2] = C0213b.c(list.get(i2).getValues(), list.get(i2).getModelSignature());
            }
            builder.addEmbeddingParameters(embeddingVectorArr);
        }

        public static void addInformationalRankingExpressions(SearchSpec.Builder builder, List<String> list) {
            builder.addInformationalRankingExpressions(list);
        }

        public static void setDefaultEmbeddingSearchMetricType(SearchSpec.Builder builder, int i2) {
            builder.setDefaultEmbeddingSearchMetricType(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ApiHelperForU {
        public static void copyEnabledFeatures(SearchSpec.Builder builder, androidx.appsearch.app.SearchSpec searchSpec) {
            if (searchSpec.isNumericSearchEnabled()) {
                builder.setNumericSearchEnabled(true);
            }
            if (searchSpec.isVerbatimSearchEnabled()) {
                builder.setVerbatimSearchEnabled(true);
            }
            if (searchSpec.isListFilterQueryLanguageEnabled()) {
                builder.setListFilterQueryLanguageEnabled(true);
            }
        }

        public static void setJoinSpec(Context context, SearchSpec.Builder builder, JoinSpec joinSpec) {
            builder.setJoinSpec(JoinSpecToPlatformConverter.toPlatformJoinSpec(context, joinSpec));
        }

        public static void setPropertyWeights(SearchSpec.Builder builder, Map<String, Map<String, Double>> map) {
            for (Map.Entry next : map.entrySet()) {
                builder.setPropertyWeights((String) next.getKey(), (Map) next.getValue());
            }
        }

        public static void setRankingStrategy(SearchSpec.Builder builder, String str) {
            builder.setRankingStrategy(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ApiHelperForV {
        public static void addFilterProperties(SearchSpec.Builder builder, Map<String, List<String>> map) {
            for (Map.Entry next : map.entrySet()) {
                builder.addFilterProperties((String) next.getKey(), (Collection) next.getValue());
            }
        }

        public static void copyEnabledFeatures(SearchSpec.Builder builder, androidx.appsearch.app.SearchSpec searchSpec) {
            if (searchSpec.isListFilterHasPropertyFunctionEnabled()) {
                builder.setListFilterHasPropertyFunctionEnabled(true);
            }
        }

        public static void setSearchSourceLogTag(SearchSpec.Builder builder, String str) {
            builder.setSearchSourceLogTag(str);
        }
    }

    public static SearchSpec toPlatformSearchSpec(Context context, androidx.appsearch.app.SearchSpec searchSpec) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(searchSpec);
        SearchSpec.Builder c5 = C0251a.c();
        if (searchSpec.getAdvancedRankingExpression().isEmpty()) {
            c5.setRankingStrategy(searchSpec.getRankingStrategy());
        } else if (Build.VERSION.SDK_INT >= 34) {
            ApiHelperForU.setRankingStrategy(c5, searchSpec.getAdvancedRankingExpression());
        } else {
            throw new UnsupportedOperationException("SEARCH_SPEC_ADVANCED_RANKING_EXPRESSION is not available on this AppSearch implementation.");
        }
        c5.setTermMatch(searchSpec.getTermMatch()).addFilterSchemas(searchSpec.getFilterSchemas()).addFilterNamespaces(searchSpec.getFilterNamespaces()).addFilterPackageNames(searchSpec.getFilterPackageNames()).setResultCountPerPage(searchSpec.getResultCountPerPage()).setOrder(searchSpec.getOrder()).setSnippetCount(searchSpec.getSnippetCount()).setSnippetCountPerProperty(searchSpec.getSnippetCountPerProperty()).setMaxSnippetSize(searchSpec.getMaxSnippetSize());
        if (searchSpec.getResultGroupingTypeFlags() != 0) {
            if ((searchSpec.getResultGroupingTypeFlags() & 4) == 0 || Build.VERSION.SDK_INT >= 35) {
                c5.setResultGrouping(searchSpec.getResultGroupingTypeFlags(), searchSpec.getResultGroupingLimit());
            } else {
                throw new UnsupportedOperationException("SEARCH_SPEC_GROUPING_TYPE_PER_SCHEMA is not available on this AppSearch implementation.");
            }
        }
        if (Build.VERSION.SDK_INT >= 34 || AppSearchVersionUtil.getAppSearchVersionCode(context) >= 340800000) {
            for (Map.Entry next : searchSpec.getProjections().entrySet()) {
                c5.addProjection((String) next.getKey(), (Collection) next.getValue());
            }
        }
        if (!searchSpec.getPropertyWeights().isEmpty()) {
            if (Build.VERSION.SDK_INT >= 34) {
                ApiHelperForU.setPropertyWeights(c5, searchSpec.getPropertyWeights());
            } else {
                throw new UnsupportedOperationException("Property weights are not supported with this backend/Android API level combination.");
            }
        }
        if (!searchSpec.getEnabledFeatures().isEmpty()) {
            if (searchSpec.isNumericSearchEnabled() || searchSpec.isVerbatimSearchEnabled() || searchSpec.isListFilterQueryLanguageEnabled()) {
                if (Build.VERSION.SDK_INT >= 34) {
                    ApiHelperForU.copyEnabledFeatures(c5, searchSpec);
                } else {
                    throw new UnsupportedOperationException("Advanced query features (NUMERIC_SEARCH, VERBATIM_SEARCH and LIST_FILTER_QUERY_LANGUAGE) are not supported with this backend/Android API level combination.");
                }
            }
            if (searchSpec.isListFilterHasPropertyFunctionEnabled()) {
                if (Build.VERSION.SDK_INT >= 35) {
                    ApiHelperForV.copyEnabledFeatures(c5, searchSpec);
                } else {
                    throw new UnsupportedOperationException("LIST_FILTER_HAS_PROPERTY_FUNCTION is not available on this AppSearch implementation.");
                }
            }
            if (searchSpec.isListFilterMatchScoreExpressionFunctionEnabled()) {
                throw new UnsupportedOperationException("LIST_FILTER_MATCH_SCORE_EXPRESSION_FUNCTION is not available on this AppSearch implementation.");
            }
        }
        if (!searchSpec.getEmbeddingParameters().isEmpty()) {
            if (AppSearchVersionUtil.isAtLeastB()) {
                ApiHelperForB.addEmbeddingParameters(c5, searchSpec.getEmbeddingParameters());
                ApiHelperForB.setDefaultEmbeddingSearchMetricType(c5, searchSpec.getDefaultEmbeddingSearchMetricType());
            } else {
                throw new UnsupportedOperationException("SCHEMA_EMBEDDING_PROPERTY_CONFIG is not available on this AppSearch implementation.");
            }
        }
        if (searchSpec.getSearchStringParameters().isEmpty()) {
            searchSpec.getJoinSpec();
            if (!searchSpec.getFilterProperties().isEmpty()) {
                if (Build.VERSION.SDK_INT >= 35) {
                    ApiHelperForV.addFilterProperties(c5, searchSpec.getFilterProperties());
                } else {
                    throw new UnsupportedOperationException("SEARCH_SPEC_ADD_FILTER_PROPERTIES is not available on this AppSearch implementation.");
                }
            }
            if (searchSpec.getSearchSourceLogTag() != null) {
                if (Build.VERSION.SDK_INT >= 35) {
                    ApiHelperForV.setSearchSourceLogTag(c5, searchSpec.getSearchSourceLogTag());
                } else {
                    throw new UnsupportedOperationException("SEARCH_SPEC_SET_SEARCH_SOURCE_LOG_TAG is not available on this AppSearch implementation.");
                }
            }
            if (!searchSpec.getInformationalRankingExpressions().isEmpty()) {
                if (AppSearchVersionUtil.isAtLeastB()) {
                    ApiHelperForB.addInformationalRankingExpressions(c5, searchSpec.getInformationalRankingExpressions());
                } else {
                    throw new UnsupportedOperationException("SEARCH_SPEC_ADD_INFORMATIONAL_RANKING_EXPRESSIONS are not available on this AppSearch implementation.");
                }
            }
            if (!searchSpec.getFilterDocumentIds().isEmpty()) {
                throw new UnsupportedOperationException("SEARCH_SPEC_ADD_FILTER_DOCUMENT_IDS is not available on this AppSearch implementation.");
            } else if (!searchSpec.isScorablePropertyRankingEnabled()) {
                return c5.build();
            } else {
                throw new UnsupportedOperationException("SCHEMA_SCORABLE_PROPERTY_CONFIG is not available on this AppSearch implementation.");
            }
        } else {
            throw new UnsupportedOperationException("SEARCH_SPEC_SEARCH_STRING_PARAMETERS is not available on this AppSearch implementation.");
        }
    }
}
