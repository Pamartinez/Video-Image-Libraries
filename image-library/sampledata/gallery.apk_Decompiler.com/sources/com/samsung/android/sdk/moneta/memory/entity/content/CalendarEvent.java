package com.samsung.android.sdk.moneta.memory.entity.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b!\u0010\u001cR\u0017\u0010\b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b%\u0010\u001cR\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u001a\u001a\u0004\b&\u0010\u001cR\u0017\u0010\u000b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\"\u001a\u0004\b'\u0010$R\u0017\u0010\f\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\f\u0010\"\u001a\u0004\b(\u0010$R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010)\u001a\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/CalendarEvent;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "contentUri", "", "rawCalendarId", "rawEventId", "allDay", "title", "description", "timeBegin", "timeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;JJLcom/samsung/android/sdk/moneta/memory/entity/context/Place;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getContentUri", "Ljava/lang/Long;", "getRawCalendarId", "()Ljava/lang/Long;", "getRawEventId", "J", "getAllDay", "()J", "getTitle", "getDescription", "getTimeBegin", "getTimeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CalendarEvent extends Content {
    public static final Parcelable.Creator<CalendarEvent> CREATOR = new Creator();
    private final long allDay;
    private final String contentUri;
    private final String description;
    private final String id;
    private final Place location;
    private final Long rawCalendarId;
    private final String rawEventId;
    private final long timeBegin;
    private final long timeEnd;
    private final String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<CalendarEvent> {
        public final CalendarEvent createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            Place place = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            String readString3 = parcel.readString();
            long readLong = parcel.readLong();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            long readLong2 = parcel.readLong();
            long readLong3 = parcel.readLong();
            if (parcel.readInt() != 0) {
                place = Place.CREATOR.createFromParcel(parcel);
            }
            return new CalendarEvent(readString, readString2, valueOf, readString3, readLong, readString4, readString5, readLong2, readLong3, place);
        }

        public final CalendarEvent[] newArray(int i2) {
            return new CalendarEvent[i2];
        }
    }

    public CalendarEvent(String str, String str2, Long l, String str3, long j2, String str4, String str5, long j3, long j8, Place place) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        j.e(str4, "title");
        j.e(str5, "description");
        this.id = str;
        this.contentUri = str2;
        this.rawCalendarId = l;
        this.rawEventId = str3;
        this.allDay = j2;
        this.title = str4;
        this.description = str5;
        this.timeBegin = j3;
        this.timeEnd = j8;
        this.location = place;
    }

    public final int describeContents() {
        return 0;
    }

    public final long getAllDay() {
        return this.allDay;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public final String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public final Place getLocation() {
        return this.location;
    }

    public final Long getRawCalendarId() {
        return this.rawCalendarId;
    }

    public final String getRawEventId() {
        return this.rawEventId;
    }

    public final long getTimeBegin() {
        return this.timeBegin;
    }

    public final long getTimeEnd() {
        return this.timeEnd;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.contentUri);
        Long l = this.rawCalendarId;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        parcel.writeString(this.rawEventId);
        parcel.writeLong(this.allDay);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeLong(this.timeBegin);
        parcel.writeLong(this.timeEnd);
        Place place = this.location;
        if (place == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        place.writeToParcel(parcel, i2);
    }
}
