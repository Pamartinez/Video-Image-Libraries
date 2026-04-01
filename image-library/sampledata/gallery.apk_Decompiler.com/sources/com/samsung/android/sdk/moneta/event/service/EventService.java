package com.samsung.android.sdk.moneta.event.service;

import com.samsung.android.sdk.moneta.event.option.EventOption;
import java.util.List;
import kotlin.Metadata;
import ne.C1202t;
import qe.C1227c;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H§@¢\u0006\u0004\b\b\u0010\tJ$\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0004H§@¢\u0006\u0004\b\u0006\u0010\fJ6\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0004H¦@¢\u0006\u0004\b\u0006\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/service/EventService;", "", "Lcom/samsung/android/sdk/moneta/event/option/EventOption;", "option", "", "Lcom/samsung/android/sdk/moneta/event/entity/Event;", "queryEvents", "(Lcom/samsung/android/sdk/moneta/event/option/EventOption;Lqe/c;)Ljava/lang/Object;", "queryAllEvents", "(Lqe/c;)Ljava/lang/Object;", "", "keywords", "(Ljava/util/List;Lqe/c;)Ljava/lang/Object;", "", "startTime", "endTime", "(JJLjava/util/List;Lqe/c;)Ljava/lang/Object;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EventService {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static /* synthetic */ Object queryEvents$default(EventService eventService, long j2, long j3, List list, C1227c cVar, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 4) != 0) {
                    list = C1202t.d;
                }
                return eventService.queryEvents(j2, j3, list, cVar);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: queryEvents");
        }
    }

    Object queryAllEvents(C1227c cVar);

    Object queryEvents(long j2, long j3, List<String> list, C1227c cVar);

    Object queryEvents(EventOption eventOption, C1227c cVar);

    Object queryEvents(List<String> list, C1227c cVar);
}
