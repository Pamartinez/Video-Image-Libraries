package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ExercisingActivity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ExercisingActivityBundleWrapper;
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

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0007\u0018\u0000 82\u00020\u0001:\u00018Bq\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u001a¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b'\u0010#R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010+\u001a\u0004\b,\u0010-R\u0019\u0010\f\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u0010+\u001a\u0004\b.\u0010-R\u0019\u0010\r\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\r\u0010+\u001a\u0004\b/\u0010-R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000e\u0010+\u001a\u0004\b0\u0010-R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00101\u001a\u0004\b2\u00103R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u00104\u001a\u0004\b5\u00106R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010!\u001a\u0004\b7\u0010#¨\u00069"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/ExercisingActivityWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "contentWrappers", "name", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "locationWrapper", "", "calorie", "maxHeartRate", "meanHeartRate", "minHeartRate", "", "startTimestamp", "endTimestamp", "exerciseType", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;JLjava/lang/Long;Ljava/lang/String;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getContentWrappers", "()Ljava/util/List;", "getName", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getLocationWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "Ljava/lang/Float;", "getCalorie", "()Ljava/lang/Float;", "getMaxHeartRate", "getMeanHeartRate", "getMinHeartRate", "J", "getStartTimestamp", "()J", "Ljava/lang/Long;", "getEndTimestamp", "()Ljava/lang/Long;", "getExerciseType", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExercisingActivityWrapperV2 extends ActivityWrapper {
    public static final Parcelable.Creator<ExercisingActivityWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Float calorie;
    private final List<ContentWrapper> contentWrappers;
    private final Long endTimestamp;
    private final String exerciseType;
    private final String id;
    private final PlaceWrapperV2 locationWrapper;
    private final Float maxHeartRate;
    private final Float meanHeartRate;
    private final Float minHeartRate;
    private final String name;
    private final long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/ExercisingActivityWrapperV2$Companion;", "", "<init>", "()V", "fromActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/ExercisingActivityWrapperV2;", "exercisingActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ExercisingActivityWrapperV2 fromActivity(ExercisingActivity exercisingActivity) {
            PlaceWrapperV2 placeWrapperV2;
            j.e(exercisingActivity, "exercisingActivity");
            String id = exercisingActivity.getId();
            Iterable<Content> contents = exercisingActivity.getContents();
            ArrayList arrayList = new ArrayList(C1196n.w0(contents, 10));
            for (Content wrapperV2 : contents) {
                arrayList.add(WrapperMapperKt.toWrapperV2(wrapperV2));
            }
            String name = exercisingActivity.getName();
            Place location = exercisingActivity.getLocation();
            if (location != null) {
                placeWrapperV2 = j.e(location, "<this>");
            } else {
                placeWrapperV2 = null;
            }
            return new ExercisingActivityWrapperV2(id, arrayList, name, placeWrapperV2, exercisingActivity.getCalorie(), exercisingActivity.getMaxHeartRate(), exercisingActivity.getMeanHeartRate(), exercisingActivity.getMinHeartRate(), exercisingActivity.getStartTimestamp(), exercisingActivity.getEndTimestamp(), exercisingActivity.getExerciseType().getValue());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ExercisingActivityWrapperV2> {
        public final ExercisingActivityWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(ExercisingActivityWrapperV2.class, parcel, arrayList, i2, 1);
            }
            String readString2 = parcel.readString();
            Long l = null;
            PlaceWrapperV2 createFromParcel = parcel.readInt() == 0 ? null : PlaceWrapperV2.CREATOR.createFromParcel(parcel);
            Float valueOf = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            Float valueOf2 = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            Float valueOf3 = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            Float valueOf4 = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            long readLong = parcel.readLong();
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new ExercisingActivityWrapperV2(readString, arrayList, readString2, createFromParcel, valueOf, valueOf2, valueOf3, valueOf4, readLong, l, parcel.readString());
        }

        public final ExercisingActivityWrapperV2[] newArray(int i2) {
            return new ExercisingActivityWrapperV2[i2];
        }
    }

    public ExercisingActivityWrapperV2(String str, List<? extends ContentWrapper> list, String str2, PlaceWrapperV2 placeWrapperV2, Float f, Float f5, Float f8, Float f10, long j2, Long l, String str3) {
        j.e(str, "id");
        j.e(list, "contentWrappers");
        j.e(str2, "name");
        j.e(str3, ExercisingActivityBundleWrapper.BUNDLE_KEY_EXERCISE_TYPE);
        this.id = str;
        this.contentWrappers = list;
        this.name = str2;
        this.locationWrapper = placeWrapperV2;
        this.calorie = f;
        this.maxHeartRate = f5;
        this.meanHeartRate = f8;
        this.minHeartRate = f10;
        this.startTimestamp = j2;
        this.endTimestamp = l;
        this.exerciseType = str3;
    }

    public final int describeContents() {
        return 0;
    }

    public final Float getCalorie() {
        return this.calorie;
    }

    public final List<ContentWrapper> getContentWrappers() {
        return this.contentWrappers;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getExerciseType() {
        return this.exerciseType;
    }

    public final String getId() {
        return this.id;
    }

    public final PlaceWrapperV2 getLocationWrapper() {
        return this.locationWrapper;
    }

    public final Float getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public final Float getMeanHeartRate() {
        return this.meanHeartRate;
    }

    public final Float getMinHeartRate() {
        return this.minHeartRate;
    }

    public final String getName() {
        return this.name;
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
        parcel.writeString(this.name);
        PlaceWrapperV2 placeWrapperV2 = this.locationWrapper;
        if (placeWrapperV2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            placeWrapperV2.writeToParcel(parcel, i2);
        }
        Float f = this.calorie;
        if (f == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeFloat(f.floatValue());
        }
        Float f5 = this.maxHeartRate;
        if (f5 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeFloat(f5.floatValue());
        }
        Float f8 = this.meanHeartRate;
        if (f8 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeFloat(f8.floatValue());
        }
        Float f10 = this.minHeartRate;
        if (f10 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeFloat(f10.floatValue());
        }
        parcel.writeLong(this.startTimestamp);
        Long l = this.endTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        parcel.writeString(this.exerciseType);
    }
}
