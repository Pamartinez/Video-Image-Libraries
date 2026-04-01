package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.FourWEventBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.FourWEvent;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 '2\u00020\u0001:\u0001'BC\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001f\u0010\u001cR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b#\u0010\"R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/FourWEventWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "eventId", "title", "description", "", "timeBegin", "timeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLcom/samsung/android/sdk/moneta/memory/entity/context/Place;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getEventId", "getTitle", "getDescription", "J", "getTimeBegin", "()J", "getTimeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FourWEventWrapperV1 extends ContentWrapper {
    public static final Parcelable.Creator<FourWEventWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String description;
    private final String eventId;
    private final String id;
    private final Place location;
    private final long timeBegin;
    private final long timeEnd;
    private final String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/FourWEventWrapperV1$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/FourWEventWrapperV1;", "fourWEvent", "Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ FourWEventWrapperV1 fromContent(FourWEvent fourWEvent) {
            j.e(fourWEvent, "fourWEvent");
            return new FourWEventWrapperV1(fourWEvent.getId(), fourWEvent.getEventId(), fourWEvent.getTitle(), fourWEvent.getDescription(), fourWEvent.getTimeBegin(), fourWEvent.getTimeEnd(), fourWEvent.getLocation());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<FourWEventWrapperV1> {
        public final FourWEventWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new FourWEventWrapperV1(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt() == 0 ? null : Place.CREATOR.createFromParcel(parcel));
        }

        public final FourWEventWrapperV1[] newArray(int i2) {
            return new FourWEventWrapperV1[i2];
        }
    }

    public FourWEventWrapperV1(String str, String str2, String str3, String str4, long j2, long j3, Place place) {
        j.e(str, "id");
        j.e(str2, FourWEventBundleWrapper.BUNDLE_KEY_EVENT_ID);
        j.e(str3, "title");
        this.id = str;
        this.eventId = str2;
        this.title = str3;
        this.description = str4;
        this.timeBegin = j2;
        this.timeEnd = j3;
        this.location = place;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getEventId() {
        return this.eventId;
    }

    public final String getId() {
        return this.id;
    }

    public final Place getLocation() {
        return this.location;
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
        parcel.writeString(this.eventId);
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
