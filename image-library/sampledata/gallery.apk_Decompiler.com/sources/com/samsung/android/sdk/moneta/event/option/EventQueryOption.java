package com.samsung.android.sdk.moneta.event.option;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.event.entity.event.EventType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001eB9\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0017\u0010\u0016R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/option/EventQueryOption;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "Lcom/samsung/android/sdk/moneta/event/entity/event/EventType;", "eventType", "", "eventCategory", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/event/entity/event/EventType;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Lcom/samsung/android/sdk/moneta/event/entity/event/EventType;", "getEventType", "()Lcom/samsung/android/sdk/moneta/event/entity/event/EventType;", "Ljava/lang/String;", "getEventCategory", "()Ljava/lang/String;", "Builder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EventQueryOption implements Parcelable {
    public static final Parcelable.Creator<EventQueryOption> CREATOR = new Creator();
    private final /* synthetic */ Long endTimestamp;
    private final /* synthetic */ String eventCategory;
    private final /* synthetic */ EventType eventType;
    private final /* synthetic */ Long startTimestamp;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0010R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/option/EventQueryOption$Builder;", "", "eventType", "Lcom/samsung/android/sdk/moneta/event/entity/event/EventType;", "startTimestamp", "", "endTimestamp", "eventCategory", "", "<init>", "(Lcom/samsung/android/sdk/moneta/event/entity/event/EventType;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)V", "Ljava/lang/Long;", "activityType", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/event/option/EventQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private /* synthetic */ Long endTimestamp;
        private /* synthetic */ String eventCategory;
        private /* synthetic */ EventType eventType;
        private /* synthetic */ Long startTimestamp;

        public Builder() {
            this((EventType) null, (Long) null, (Long) null, (String) null, 15, (e) null);
        }

        public final /* synthetic */ Builder activityType(EventType eventType2) {
            j.e(eventType2, "eventType");
            this.eventType = eventType2;
            return this;
        }

        public final /* synthetic */ EventQueryOption build() {
            return new EventQueryOption(this.startTimestamp, this.endTimestamp, this.eventType, this.eventCategory, (e) null);
        }

        public final /* synthetic */ Builder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final /* synthetic */ Builder eventCategory(String str) {
            j.e(str, "eventCategory");
            this.eventCategory = str;
            return this;
        }

        public final /* synthetic */ Builder startTimeStamp(long j2) {
            this.startTimestamp = Long.valueOf(j2);
            return this;
        }

        public Builder(EventType eventType2, Long l, Long l8, String str) {
            this.eventType = eventType2;
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.eventCategory = str;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(EventType eventType2, Long l, Long l8, String str, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : eventType2, (i2 & 2) != 0 ? null : l, (i2 & 4) != 0 ? null : l8, (i2 & 8) != 0 ? null : str);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EventQueryOption> {
        public final EventQueryOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            EventType eventType = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            Long valueOf2 = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                eventType = EventType.CREATOR.createFromParcel(parcel);
            }
            return new EventQueryOption(valueOf, valueOf2, eventType, parcel.readString(), (e) null);
        }

        public final EventQueryOption[] newArray(int i2) {
            return new EventQueryOption[i2];
        }
    }

    public /* synthetic */ EventQueryOption(Long l, Long l8, EventType eventType2, String str, e eVar) {
        this(l, l8, eventType2, str);
    }

    public final int describeContents() {
        return 0;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getEventCategory() {
        return this.eventCategory;
    }

    public final EventType getEventType() {
        return this.eventType;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Long l = this.startTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.endTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        EventType eventType2 = this.eventType;
        if (eventType2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            eventType2.writeToParcel(parcel, i2);
        }
        parcel.writeString(this.eventCategory);
    }

    private EventQueryOption(Long l, Long l8, EventType eventType2, String str) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.eventType = eventType2;
        this.eventCategory = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EventQueryOption(Long l, Long l8, EventType eventType2, String str, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : l, (i2 & 2) != 0 ? null : l8, (i2 & 4) != 0 ? null : eventType2, (i2 & 8) != 0 ? null : str);
    }
}
