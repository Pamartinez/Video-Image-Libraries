package com.samsung.android.sdk.moneta.memory.service;

import com.samsung.android.sdk.moneta.memory.option.EngramSearchActivityOption;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchContentOption;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchContentStatOption;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchGraphOption;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchIntentOption;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchOption;
import com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\u0003\u001a\u00020\bH¦@¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0006\u0010\u0003\u001a\u00020\bH¦@¢\u0006\u0004\b\r\u0010\u000bJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00042\u0006\u0010\u0003\u001a\u00020\u000eH¦@¢\u0006\u0004\b\u0010\u0010\u0011J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\u0006\u0010\u0003\u001a\u00020\u0012H¦@¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0016H¦@¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u001aH¦@¢\u0006\u0004\b\u001b\u0010\u001cJ\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00042\u0006\u0010\u0003\u001a\u00020\u001dH¦@¢\u0006\u0004\b\u001f\u0010 J\u0018\u0010#\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020!H¦@¢\u0006\u0004\b#\u0010$J\u0010\u0010&\u001a\u00020%H¦@¢\u0006\u0004\b&\u0010'¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/service/MemorySearchService;", "", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;", "options", "", "", "getSearchIntent", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchIntentOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "searchEngram", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "searchPerson", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "searchActivity", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "searchContent", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;", "", "searchContentStat", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "searchEngramStat", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GraphPathNode;", "searchGraphPath", "(Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "getRecommendations", "(Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;Lqe/c;)Ljava/lang/Object;", "Lme/x;", "prepareSearchEngine", "(Lqe/c;)Ljava/lang/Object;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MemorySearchService {
    Object getRecommendations(RecommendationsGetOption recommendationsGetOption, C1227c cVar);

    Object getSearchIntent(EngramSearchIntentOption engramSearchIntentOption, C1227c cVar);

    Object prepareSearchEngine(C1227c cVar);

    Object searchActivity(EngramSearchActivityOption engramSearchActivityOption, C1227c cVar);

    Object searchContent(EngramSearchContentOption engramSearchContentOption, C1227c cVar);

    Object searchContentStat(EngramSearchContentStatOption engramSearchContentStatOption, C1227c cVar);

    Object searchEngram(EngramSearchOption engramSearchOption, C1227c cVar);

    Object searchEngramStat(EngramSearchEngramStatOption engramSearchEngramStatOption, C1227c cVar);

    Object searchGraphPath(EngramSearchGraphOption engramSearchGraphOption, C1227c cVar);

    Object searchPerson(EngramSearchOption engramSearchOption, C1227c cVar);
}
