package com.samsung.android.sdk.moneta.event.service;

import com.samsung.android.sdk.moneta.event.entity.Event;
import com.samsung.android.sdk.moneta.event.entity.EventCategoryEnum;
import com.samsung.android.sdk.moneta.event.entity.EventSubCategoryEnum;
import com.samsung.android.sdk.moneta.event.entity.What;
import com.samsung.android.sdk.moneta.event.entity.When;
import com.samsung.android.sdk.moneta.event.entity.Where;
import com.samsung.android.sdk.moneta.event.entity.Who;
import com.samsung.android.sdk.moneta.event.entity.event.EventCategory;
import com.samsung.android.sdk.moneta.event.entity.event.EventType;
import com.samsung.android.sdk.moneta.event.option.EventOption;
import com.samsung.android.sdk.moneta.event.option.EventQueryOption;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u0002\u001a\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004H\u0002\u001a\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004H\u0002\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0002\u001a\f\u0010\u0013\u001a\u00020\u0005*\u00020\u0007H\u0002\u001a\f\u0010\u0014\u001a\u00020\t*\u00020\u000bH\u0002\u001a\f\u0010\u0015\u001a\u00020\r*\u00020\u000fH\u0002\u001a\n\u0010\u0016\u001a\u00020\u0017*\u00020\u0018¨\u0006\u0019"}, d2 = {"toSerializableEvent", "Lcom/samsung/android/sdk/moneta/event/entity/Event;", "Lcom/samsung/android/sdk/moneta/event/entity/event/Event;", "convertToSerializableWhatList", "", "Lcom/samsung/android/sdk/moneta/event/entity/What;", "whatList", "Lcom/samsung/android/sdk/moneta/event/entity/event/What;", "convertToSerializableWhereList", "Lcom/samsung/android/sdk/moneta/event/entity/Where;", "whereList", "Lcom/samsung/android/sdk/moneta/event/entity/event/Where;", "convertToSerializableWhoList", "Lcom/samsung/android/sdk/moneta/event/entity/Who;", "whoList", "Lcom/samsung/android/sdk/moneta/event/entity/event/Who;", "toSerializableWhen", "Lcom/samsung/android/sdk/moneta/event/entity/When;", "Lcom/samsung/android/sdk/moneta/event/entity/event/When;", "toSerializableWhat", "toSerializableWhere", "toSerializableWho", "toEventQueryOption", "Lcom/samsung/android/sdk/moneta/event/option/EventQueryOption;", "Lcom/samsung/android/sdk/moneta/event/option/EventOption;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConverterKt {
    private static final List<What> convertToSerializableWhatList(List<com.samsung.android.sdk.moneta.event.entity.event.What> list) {
        Iterable<com.samsung.android.sdk.moneta.event.entity.event.What> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (com.samsung.android.sdk.moneta.event.entity.event.What serializableWhat : iterable) {
            arrayList.add(toSerializableWhat(serializableWhat));
        }
        return arrayList;
    }

    private static final List<Where> convertToSerializableWhereList(List<com.samsung.android.sdk.moneta.event.entity.event.Where> list) {
        Iterable<com.samsung.android.sdk.moneta.event.entity.event.Where> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (com.samsung.android.sdk.moneta.event.entity.event.Where serializableWhere : iterable) {
            arrayList.add(toSerializableWhere(serializableWhere));
        }
        return arrayList;
    }

    private static final List<Who> convertToSerializableWhoList(List<com.samsung.android.sdk.moneta.event.entity.event.Who> list) {
        Iterable<com.samsung.android.sdk.moneta.event.entity.event.Who> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (com.samsung.android.sdk.moneta.event.entity.event.Who serializableWho : iterable) {
            arrayList.add(toSerializableWho(serializableWho));
        }
        return arrayList;
    }

    public static final /* synthetic */ EventQueryOption toEventQueryOption(EventOption eventOption) {
        j.e(eventOption, "<this>");
        EventQueryOption.Builder builder = new EventQueryOption.Builder((EventType) null, (Long) null, (Long) null, (String) null, 15, (e) null);
        Long startTimestamp = eventOption.getStartTimestamp();
        if (startTimestamp != null) {
            builder.startTimeStamp(startTimestamp.longValue());
        }
        Long endTimestamp = eventOption.getEndTimestamp();
        if (endTimestamp != null) {
            builder.endTimeStamp(endTimestamp.longValue());
        }
        EventCategoryEnum eventCategoryEnum = eventOption.getEventCategoryEnum();
        if (eventCategoryEnum != null) {
            builder.eventCategory(eventCategoryEnum.name());
        }
        return builder.build();
    }

    public static final /* synthetic */ Event toSerializableEvent(com.samsung.android.sdk.moneta.event.entity.event.Event event) {
        EventCategoryEnum eventCategoryEnum;
        j.e(event, "<this>");
        Long id = event.getId();
        When serializableWhen = toSerializableWhen(event.getWhen());
        List<What> convertToSerializableWhatList = convertToSerializableWhatList(event.getWhat());
        List<Where> convertToSerializableWhereList = convertToSerializableWhereList(event.getWhere());
        List<Who> convertToSerializableWhoList = convertToSerializableWhoList(event.getWho());
        EventCategory eventCategory = event.getEventCategory();
        if (eventCategory == null || (eventCategoryEnum = EventCategoryEnum.valueOf(eventCategory.getCategoryName())) == null) {
            eventCategoryEnum = EventCategoryEnum.UNKNOWN;
        }
        return new Event(id, serializableWhen, convertToSerializableWhatList, convertToSerializableWhereList, convertToSerializableWhoList, eventCategoryEnum, EventSubCategoryEnum.UNKNOWN);
    }

    private static final What toSerializableWhat(com.samsung.android.sdk.moneta.event.entity.event.What what) {
        return new What(what.getTitle(), what.getSourcePackage(), what.getSourceUri(), (String) null, (List) null, 24, (e) null);
    }

    private static final When toSerializableWhen(com.samsung.android.sdk.moneta.event.entity.event.When when) {
        return new When(when.getStartTime(), when.getEndTime(), when.getSourcePackage(), when.getSourceUri(), (String) null, when.isLunar(), 16, (e) null);
    }

    private static final Where toSerializableWhere(com.samsung.android.sdk.moneta.event.entity.event.Where where) {
        return new Where(where.getPlaceName(), where.getAddress(), where.getPoi(), where.getCountry(), where.getCity(), where.getPostalCode(), where.getSourcePackage(), where.getSourceUri(), where.getLng(), where.getLat(), where.getAugmented(), (List) null, 2048, (e) null);
    }

    private static final Who toSerializableWho(com.samsung.android.sdk.moneta.event.entity.event.Who who) {
        return new Who(who.getName(), who.getPhoneNumber(), who.getContactId(), who.getEmail(), who.getGroupName(), who.getNickName(), who.getSnsName(), who.getRelation(), who.isContributor(), who.getSourcePackage(), who.getSourceUri(), who.getAugmented(), (List) null, 4096, (e) null);
    }
}
