package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.EatingActivity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.EatingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.util.WrapperMapperKt;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context.PlaceWrapperV2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u0000 +2\u00020\u0001:\u0001+BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b!\u0010 R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010\f\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u0010(\u001a\u0004\b)\u0010*¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/EatingActivityWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "contentWrappers", "foods", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "locationWrapper", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;JLjava/lang/Long;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getContentWrappers", "()Ljava/util/List;", "getFoods", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getLocationWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "J", "getStartTimestamp", "()J", "Ljava/lang/Long;", "getEndTimestamp", "()Ljava/lang/Long;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EatingActivityWrapperV2 extends ActivityWrapper {
    public static final Parcelable.Creator<EatingActivityWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<ContentWrapper> contentWrappers;
    private final Long endTimestamp;
    private final List<String> foods;
    private final String id;
    private final PlaceWrapperV2 locationWrapper;
    private final long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/EatingActivityWrapperV2$Companion;", "", "<init>", "()V", "fromActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/EatingActivityWrapperV2;", "eatingActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/EatingActivity;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EatingActivityWrapperV2 fromActivity(EatingActivity eatingActivity) {
            PlaceWrapperV2 placeWrapperV2;
            j.e(eatingActivity, "eatingActivity");
            String id = eatingActivity.getId();
            Iterable<Content> contents = eatingActivity.getContents();
            ArrayList arrayList = new ArrayList(C1196n.w0(contents, 10));
            for (Content wrapperV2 : contents) {
                arrayList.add(WrapperMapperKt.toWrapperV2(wrapperV2));
            }
            List<String> foods = eatingActivity.getFoods();
            Place location = eatingActivity.getLocation();
            if (location != null) {
                placeWrapperV2 = j.e(location, "<this>");
            } else {
                placeWrapperV2 = null;
            }
            return new EatingActivityWrapperV2(id, arrayList, foods, placeWrapperV2, eatingActivity.getStartTimestamp(), eatingActivity.getEndTimestamp());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EatingActivityWrapperV2> {
        public final EatingActivityWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(EatingActivityWrapperV2.class, parcel, arrayList, i2, 1);
            }
            ArrayList<String> createStringArrayList = parcel.createStringArrayList();
            Long l = null;
            PlaceWrapperV2 createFromParcel = parcel.readInt() == 0 ? null : PlaceWrapperV2.CREATOR.createFromParcel(parcel);
            long readLong = parcel.readLong();
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new EatingActivityWrapperV2(readString, arrayList, createStringArrayList, createFromParcel, readLong, l);
        }

        public final EatingActivityWrapperV2[] newArray(int i2) {
            return new EatingActivityWrapperV2[i2];
        }
    }

    public EatingActivityWrapperV2(String str, List<? extends ContentWrapper> list, List<String> list2, PlaceWrapperV2 placeWrapperV2, long j2, Long l) {
        j.e(str, "id");
        j.e(list, "contentWrappers");
        j.e(list2, EatingActivityBundleWrapper.BUNDLE_KEY_FOODS);
        this.id = str;
        this.contentWrappers = list;
        this.foods = list2;
        this.locationWrapper = placeWrapperV2;
        this.startTimestamp = j2;
        this.endTimestamp = l;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<ContentWrapper> getContentWrappers() {
        return this.contentWrappers;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final List<String> getFoods() {
        return this.foods;
    }

    public final String getId() {
        return this.id;
    }

    public final PlaceWrapperV2 getLocationWrapper() {
        return this.locationWrapper;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contentWrappers);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeStringList(this.foods);
        PlaceWrapperV2 placeWrapperV2 = this.locationWrapper;
        if (placeWrapperV2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            placeWrapperV2.writeToParcel(parcel, i2);
        }
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
    }
}
