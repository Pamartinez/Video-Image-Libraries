package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.FourWEventBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.FourWEvent;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context.PlaceWrapperV2;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b%\u0010$R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b'\u0010(R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/FourWEventWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "eventId", "title", "description", "", "timeBegin", "timeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "locationWrapper", "", "categories", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;Ljava/util/List;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getEventId", "getTitle", "getDescription", "J", "getTimeBegin", "()J", "getTimeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getLocationWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "Ljava/util/List;", "getCategories", "()Ljava/util/List;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FourWEventWrapperV2 extends ContentWrapper {
    public static final Parcelable.Creator<FourWEventWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<String> categories;
    private final String description;
    private final String eventId;
    private final String id;
    private final PlaceWrapperV2 locationWrapper;
    private final long timeBegin;
    private final long timeEnd;
    private final String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/FourWEventWrapperV2$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/FourWEventWrapperV2;", "fourWEvent", "Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ FourWEventWrapperV2 fromContent(FourWEvent fourWEvent) {
            PlaceWrapperV2 placeWrapperV2;
            j.e(fourWEvent, "fourWEvent");
            String id = fourWEvent.getId();
            String eventId = fourWEvent.getEventId();
            String title = fourWEvent.getTitle();
            String description = fourWEvent.getDescription();
            long timeBegin = fourWEvent.getTimeBegin();
            long timeEnd = fourWEvent.getTimeEnd();
            Place location = fourWEvent.getLocation();
            if (location != null) {
                placeWrapperV2 = j.e(location, "<this>");
            } else {
                placeWrapperV2 = null;
            }
            return new FourWEventWrapperV2(id, eventId, title, description, timeBegin, timeEnd, placeWrapperV2, fourWEvent.getCategories());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<FourWEventWrapperV2> {
        public final FourWEventWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new FourWEventWrapperV2(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt() == 0 ? null : PlaceWrapperV2.CREATOR.createFromParcel(parcel), parcel.createStringArrayList());
        }

        public final FourWEventWrapperV2[] newArray(int i2) {
            return new FourWEventWrapperV2[i2];
        }
    }

    public FourWEventWrapperV2(String str, String str2, String str3, String str4, long j2, long j3, PlaceWrapperV2 placeWrapperV2, List<String> list) {
        j.e(str, "id");
        j.e(str2, FourWEventBundleWrapper.BUNDLE_KEY_EVENT_ID);
        j.e(str3, "title");
        j.e(list, FourWEventBundleWrapper.BUNDLE_KEY_CATEGORIES);
        this.id = str;
        this.eventId = str2;
        this.title = str3;
        this.description = str4;
        this.timeBegin = j2;
        this.timeEnd = j3;
        this.locationWrapper = placeWrapperV2;
        this.categories = list;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<String> getCategories() {
        return this.categories;
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

    public final PlaceWrapperV2 getLocationWrapper() {
        return this.locationWrapper;
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
        PlaceWrapperV2 placeWrapperV2 = this.locationWrapper;
        if (placeWrapperV2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            placeWrapperV2.writeToParcel(parcel, i2);
        }
        parcel.writeStringList(this.categories);
    }
}
