package com.samsung.android.sdk.moneta.event.option;

import com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0014B=\b\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/option/EventOption;", "", "startTimestamp", "", "endTimestamp", "eventCategoryEnum", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "keywords", "", "", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;Ljava/util/List;)V", "getStartTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEndTimestamp", "getEventCategoryEnum", "()Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "getKeywords", "()Ljava/util/List;", "Builder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EventOption {
    private final Long endTimestamp;
    private final EventCategoryEnum eventCategoryEnum;
    private final List<String> keywords;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00002\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00002\b\b\u0001\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0012\u001a\u00020\u00112\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u001bR\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001c8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/option/EventOption$Builder;", "", "", "startTimestamp", "endTimestamp", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "eventCategoryEnum", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;)V", "startTimeStamp", "(J)Lcom/samsung/android/sdk/moneta/event/option/EventOption$Builder;", "endTimeStamp", "eventCategory", "(Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;)Lcom/samsung/android/sdk/moneta/event/option/EventOption$Builder;", "", "", "keywords", "Lme/x;", "addAllKeywords", "(Ljava/util/List;)V", "keyword", "addKeyword", "(Ljava/lang/String;)V", "Lcom/samsung/android/sdk/moneta/event/option/EventOption;", "build", "()Lcom/samsung/android/sdk/moneta/event/option/EventOption;", "Ljava/lang/Long;", "Lcom/samsung/android/sdk/moneta/event/entity/EventCategoryEnum;", "", "Ljava/util/Set;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Long endTimestamp;
        private EventCategoryEnum eventCategoryEnum;
        private Set<String> keywords;
        private Long startTimestamp;

        public Builder() {
            this((Long) null, (Long) null, (EventCategoryEnum) null, 7, (e) null);
        }

        public final void addAllKeywords(List<String> list) {
            j.e(list, "keywords");
            this.keywords.addAll(list);
        }

        public final void addKeyword(String str) {
            j.e(str, "keyword");
            this.keywords.add(str);
        }

        public final EventOption build() {
            List k12;
            Long l = this.startTimestamp;
            Long l8 = this.endTimestamp;
            EventCategoryEnum eventCategoryEnum2 = this.eventCategoryEnum;
            if (eventCategoryEnum2 != null) {
                k12 = C1194l.X0(this.keywords, C0246a.e0(eventCategoryEnum2.name()));
            } else {
                k12 = C1194l.k1(this.keywords);
            }
            return new EventOption(l, l8, eventCategoryEnum2, k12, (e) null);
        }

        public final Builder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final Builder eventCategory(EventCategoryEnum eventCategoryEnum2) {
            j.e(eventCategoryEnum2, "eventCategoryEnum");
            this.eventCategoryEnum = eventCategoryEnum2;
            return this;
        }

        public final Builder startTimeStamp(long j2) {
            this.startTimestamp = Long.valueOf(j2);
            return this;
        }

        public Builder(Long l, Long l8, EventCategoryEnum eventCategoryEnum2) {
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.eventCategoryEnum = eventCategoryEnum2;
            this.keywords = new LinkedHashSet();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(Long l, Long l8, EventCategoryEnum eventCategoryEnum2, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : l, (i2 & 2) != 0 ? null : l8, (i2 & 4) != 0 ? null : eventCategoryEnum2);
        }
    }

    public /* synthetic */ EventOption(Long l, Long l8, EventCategoryEnum eventCategoryEnum2, List list, e eVar) {
        this(l, l8, eventCategoryEnum2, list);
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final EventCategoryEnum getEventCategoryEnum() {
        return this.eventCategoryEnum;
    }

    public final List<String> getKeywords() {
        return this.keywords;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    private EventOption(Long l, Long l8, EventCategoryEnum eventCategoryEnum2, List<String> list) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.eventCategoryEnum = eventCategoryEnum2;
        this.keywords = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EventOption(Long l, Long l8, EventCategoryEnum eventCategoryEnum2, List list, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : l, (i2 & 2) != 0 ? null : l8, (i2 & 4) != 0 ? null : eventCategoryEnum2, (i2 & 8) != 0 ? C1202t.d : list);
    }
}
