package com.samsung.android.sdk.moneta.memory.entity.content;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.FourWEventBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.context.Poi;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b \u0010\u001dR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b$\u0010#R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b&\u0010'R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010(\u001a\u0004\b)\u0010*R\u001f\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u000f\u0010(\u001a\u0004\b+\u0010*¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/FourWEvent;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "eventId", "title", "description", "", "timeBegin", "timeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "", "categories", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Poi;", "poi", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLcom/samsung/android/sdk/moneta/memory/entity/context/Place;Ljava/util/List;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getEventId", "getTitle", "getDescription", "J", "getTimeBegin", "()J", "getTimeEnd", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "Ljava/util/List;", "getCategories", "()Ljava/util/List;", "getPoi", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FourWEvent extends Content {
    public static final Parcelable.Creator<FourWEvent> CREATOR = new Creator();
    private final List<String> categories;
    private final String description;
    private final String eventId;
    private final String id;
    private final Place location;
    private final List<Poi> poi;
    private final long timeBegin;
    private final long timeEnd;
    private final String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<FourWEvent> {
        public final FourWEvent createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            ArrayList arrayList = null;
            Place createFromParcel = parcel.readInt() == 0 ? null : Place.CREATOR.createFromParcel(parcel);
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                arrayList = new ArrayList(readInt);
                int i2 = 0;
                while (i2 != readInt) {
                    i2 = a.a(Poi.CREATOR, parcel, arrayList, i2, 1);
                }
            }
            return new FourWEvent(readString, readString2, readString3, readString4, readLong, readLong2, createFromParcel, createStringArrayList, arrayList);
        }

        public final FourWEvent[] newArray(int i2) {
            return new FourWEvent[i2];
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FourWEvent(String str, String str2, String str3, String str4, long j2, long j3, Place place, List list, List list2, int i2, e eVar) {
        this(str, str2, str3, str4, j2, j3, place, list, (i2 & 256) != 0 ? null : list2);
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

    public String getId() {
        return this.id;
    }

    public final Place getLocation() {
        return this.location;
    }

    public final List<Poi> getPoi() {
        return this.poi;
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
        } else {
            parcel.writeInt(1);
            place.writeToParcel(parcel, i2);
        }
        parcel.writeStringList(this.categories);
        List<Poi> list = this.poi;
        if (list == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(list.size());
        for (Poi writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, i2);
        }
    }

    public FourWEvent(String str, String str2, String str3, String str4, long j2, long j3, Place place, List<String> list, List<Poi> list2) {
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
        this.location = place;
        this.categories = list;
        this.poi = list2;
    }
}
