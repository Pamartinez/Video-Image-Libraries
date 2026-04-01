package com.samsung.android.sdk.moneta.lifestyle.social.service;

import com.samsung.android.sdk.moneta.lifestyle.common.TimeRange;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.PreferenceLevel;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.option.FrequentContactRequestOption;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.option.MostContactRequestOption;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H¦@¢\u0006\u0004\b\b\u0010\tJ*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH¦@¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000f\u001a\u00020\u000eH¦@¢\u0006\u0004\b\u0010\u0010\u0011J\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H¦@¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H¦@¢\u0006\u0004\b\u0016\u0010\u0017J \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0018H§@¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/service/SocialService;", "", "Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;", "timeRange", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;", "mostContactRequestOption", "", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/Person;", "getMostContactPersonList", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/FrequentContactRequestOption;", "frequentContactRequestOption", "getFrequentContactPersonList", "(Lcom/samsung/android/sdk/moneta/lifestyle/common/TimeRange;Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/FrequentContactRequestOption;Lqe/c;)Ljava/lang/Object;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;", "preferenceLevel", "getFrequentContactPersonListByPreferenceLevel", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/PreferenceLevel;Lqe/c;)Ljava/lang/Object;", "", "myPhoneNumber", "getRecentContactPersonList", "(Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "getUpcomingContactPersonList", "(Lqe/c;)Ljava/lang/Object;", "", "startTimestamp", "getUpcomingEventPersonList", "(JLqe/c;)Ljava/lang/Object;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SocialService {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static /* synthetic */ Object getFrequentContactPersonList$default(SocialService socialService, TimeRange timeRange, FrequentContactRequestOption frequentContactRequestOption, C1227c cVar, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    frequentContactRequestOption = null;
                }
                return socialService.getFrequentContactPersonList(timeRange, frequentContactRequestOption, cVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFrequentContactPersonList");
        }

        public static /* synthetic */ Object getMostContactPersonList$default(SocialService socialService, TimeRange timeRange, MostContactRequestOption mostContactRequestOption, C1227c cVar, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    mostContactRequestOption = null;
                }
                return socialService.getMostContactPersonList(timeRange, mostContactRequestOption, cVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getMostContactPersonList");
        }

        public static /* synthetic */ Object getRecentContactPersonList$default(SocialService socialService, String str, C1227c cVar, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    str = null;
                }
                return socialService.getRecentContactPersonList(str, cVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getRecentContactPersonList");
        }

        public static /* synthetic */ Object getUpcomingEventPersonList$default(SocialService socialService, long j2, C1227c cVar, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    j2 = System.currentTimeMillis();
                }
                return socialService.getUpcomingEventPersonList(j2, cVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUpcomingEventPersonList");
        }
    }

    Object getFrequentContactPersonList(TimeRange timeRange, FrequentContactRequestOption frequentContactRequestOption, C1227c cVar);

    Object getFrequentContactPersonListByPreferenceLevel(PreferenceLevel preferenceLevel, C1227c cVar);

    Object getMostContactPersonList(TimeRange timeRange, MostContactRequestOption mostContactRequestOption, C1227c cVar);

    Object getRecentContactPersonList(String str, C1227c cVar);

    Object getUpcomingContactPersonList(C1227c cVar);

    Object getUpcomingEventPersonList(long j2, C1227c cVar);
}
