package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.MovingActivity;
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

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u0000 02\u00020\u0001:\u00010BI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b&\u0010%R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010-\u001a\u0004\b.\u0010/¨\u00061"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/MovingActivityWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "contentWrappers", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "startLocationWrapper", "endLocationWrapper", "", "movingSpeed", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;FJLjava/lang/Long;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/MovingActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/MovingActivity;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getContentWrappers", "()Ljava/util/List;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getStartLocationWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getEndLocationWrapper", "F", "getMovingSpeed", "()F", "J", "getStartTimestamp", "()J", "Ljava/lang/Long;", "getEndTimestamp", "()Ljava/lang/Long;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MovingActivityWrapperV2 extends ActivityWrapper {
    public static final Parcelable.Creator<MovingActivityWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<ContentWrapper> contentWrappers;
    private final PlaceWrapperV2 endLocationWrapper;
    private final Long endTimestamp;
    private final String id;
    private final float movingSpeed;
    private final PlaceWrapperV2 startLocationWrapper;
    private final long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/MovingActivityWrapperV2$Companion;", "", "<init>", "()V", "fromActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/MovingActivityWrapperV2;", "movingActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/MovingActivity;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ MovingActivityWrapperV2 fromActivity(MovingActivity movingActivity) {
            PlaceWrapperV2 placeWrapperV2;
            j.e(movingActivity, "movingActivity");
            String id = movingActivity.getId();
            Iterable<Content> contents = movingActivity.getContents();
            ArrayList arrayList = new ArrayList(C1196n.w0(contents, 10));
            for (Content wrapperV2 : contents) {
                arrayList.add(WrapperMapperKt.toWrapperV2(wrapperV2));
            }
            PlaceWrapperV2 wrapperV22 = j.e(movingActivity.getStartLocation(), "<this>");
            Place endLocation = movingActivity.getEndLocation();
            if (endLocation != null) {
                placeWrapperV2 = j.e(endLocation, "<this>");
            } else {
                placeWrapperV2 = null;
            }
            return new MovingActivityWrapperV2(id, arrayList, wrapperV22, placeWrapperV2, movingActivity.getMovingSpeed(), movingActivity.getStartTimestamp(), movingActivity.getEndTimestamp());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MovingActivityWrapperV2> {
        public final MovingActivityWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(MovingActivityWrapperV2.class, parcel, arrayList, i2, 1);
            }
            Parcelable.Creator<PlaceWrapperV2> creator = PlaceWrapperV2.CREATOR;
            PlaceWrapperV2 createFromParcel = creator.createFromParcel(parcel);
            Long l = null;
            PlaceWrapperV2 createFromParcel2 = parcel.readInt() == 0 ? null : creator.createFromParcel(parcel);
            float readFloat = parcel.readFloat();
            long readLong = parcel.readLong();
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new MovingActivityWrapperV2(readString, arrayList, createFromParcel, createFromParcel2, readFloat, readLong, l);
        }

        public final MovingActivityWrapperV2[] newArray(int i2) {
            return new MovingActivityWrapperV2[i2];
        }
    }

    public MovingActivityWrapperV2(String str, List<? extends ContentWrapper> list, PlaceWrapperV2 placeWrapperV2, PlaceWrapperV2 placeWrapperV22, float f, long j2, Long l) {
        j.e(str, "id");
        j.e(list, "contentWrappers");
        j.e(placeWrapperV2, "startLocationWrapper");
        this.id = str;
        this.contentWrappers = list;
        this.startLocationWrapper = placeWrapperV2;
        this.endLocationWrapper = placeWrapperV22;
        this.movingSpeed = f;
        this.startTimestamp = j2;
        this.endTimestamp = l;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<ContentWrapper> getContentWrappers() {
        return this.contentWrappers;
    }

    public final PlaceWrapperV2 getEndLocationWrapper() {
        return this.endLocationWrapper;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getId() {
        return this.id;
    }

    public final float getMovingSpeed() {
        return this.movingSpeed;
    }

    public final PlaceWrapperV2 getStartLocationWrapper() {
        return this.startLocationWrapper;
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
        this.startLocationWrapper.writeToParcel(parcel, i2);
        PlaceWrapperV2 placeWrapperV2 = this.endLocationWrapper;
        if (placeWrapperV2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            placeWrapperV2.writeToParcel(parcel, i2);
        }
        parcel.writeFloat(this.movingSpeed);
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
    }
}
