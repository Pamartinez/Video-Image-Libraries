package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.ExercisingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.content.ExerciseType;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\b\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0018¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010 J\u0012\u0010$\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b$\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0012\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b(\u0010'J\u0012\u0010)\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b)\u0010'J\u0012\u0010*\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b*\u0010'J\u0010\u0010+\u001a\u00020\u000fHÆ\u0003¢\u0006\u0004\b+\u0010,J\u0012\u0010-\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0004\b-\u0010.J\u0010\u0010/\u001a\u00020\u0012HÆ\u0003¢\u0006\u0004\b/\u00100J\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u0012HÆ\u0001¢\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b3\u0010 J\u0010\u00104\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b4\u0010\u001eJ\u001a\u00108\u001a\u0002072\b\u00106\u001a\u0004\u0018\u000105HÖ\u0003¢\u0006\u0004\b8\u00109R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010:\u001a\u0004\b;\u0010 R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010<\u001a\u0004\b=\u0010\"R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010:\u001a\u0004\b>\u0010 R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010?\u001a\u0004\b@\u0010%R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010A\u001a\u0004\bB\u0010'R\u0019\u0010\f\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\f\u0010A\u001a\u0004\bC\u0010'R\u0019\u0010\r\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\r\u0010A\u001a\u0004\bD\u0010'R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000e\u0010A\u001a\u0004\bE\u0010'R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010F\u001a\u0004\bG\u0010,R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010H\u001a\u0004\bI\u0010.R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010J\u001a\u0004\bK\u00100¨\u0006L"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "name", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "", "calorie", "maxHeartRate", "meanHeartRate", "minHeartRate", "", "startTimestamp", "endTimestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;", "exerciseType", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;JLjava/lang/Long;Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "component4", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "component5", "()Ljava/lang/Float;", "component6", "component7", "component8", "component9", "()J", "component10", "()Ljava/lang/Long;", "component11", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;JLjava/lang/Long;Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/ExercisingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "getName", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "Ljava/lang/Float;", "getCalorie", "getMaxHeartRate", "getMeanHeartRate", "getMinHeartRate", "J", "getStartTimestamp", "Ljava/lang/Long;", "getEndTimestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;", "getExerciseType", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ExercisingActivity extends Activity {
    public static final Parcelable.Creator<ExercisingActivity> CREATOR = new Creator();
    private final Float calorie;
    private final List<Content> contents;
    private final Long endTimestamp;
    private final ExerciseType exerciseType;
    private final String id;
    private final Place location;
    private final Float maxHeartRate;
    private final Float meanHeartRate;
    private final Float minHeartRate;
    private final String name;
    private final long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ExercisingActivity> {
        public final ExercisingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(ExercisingActivity.class, parcel, arrayList, i2, 1);
            }
            String readString2 = parcel.readString();
            Long l = null;
            Place createFromParcel = parcel.readInt() == 0 ? null : Place.CREATOR.createFromParcel(parcel);
            Float valueOf = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            Float valueOf2 = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            Float valueOf3 = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            Float valueOf4 = parcel.readInt() == 0 ? null : Float.valueOf(parcel.readFloat());
            long readLong = parcel.readLong();
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new ExercisingActivity(readString, arrayList, readString2, createFromParcel, valueOf, valueOf2, valueOf3, valueOf4, readLong, l, ExerciseType.valueOf(parcel.readString()));
        }

        public final ExercisingActivity[] newArray(int i2) {
            return new ExercisingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExercisingActivity(String str, List<? extends Content> list, String str2, Place place, Float f, Float f5, Float f8, Float f10, long j2, Long l, ExerciseType exerciseType2) {
        super(ActivityType.Exercising);
        j.e(str, "id");
        j.e(list, "contents");
        j.e(str2, "name");
        j.e(exerciseType2, ExercisingActivityBundleWrapper.BUNDLE_KEY_EXERCISE_TYPE);
        this.id = str;
        this.contents = list;
        this.name = str2;
        this.location = place;
        this.calorie = f;
        this.maxHeartRate = f5;
        this.meanHeartRate = f8;
        this.minHeartRate = f10;
        this.startTimestamp = j2;
        this.endTimestamp = l;
        this.exerciseType = exerciseType2;
    }

    public static /* synthetic */ ExercisingActivity copy$default(ExercisingActivity exercisingActivity, String str, List<Content> list, String str2, Place place, Float f, Float f5, Float f8, Float f10, long j2, Long l, ExerciseType exerciseType2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = exercisingActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = exercisingActivity.contents;
        }
        if ((i2 & 4) != 0) {
            str2 = exercisingActivity.name;
        }
        if ((i2 & 8) != 0) {
            place = exercisingActivity.location;
        }
        if ((i2 & 16) != 0) {
            f = exercisingActivity.calorie;
        }
        if ((i2 & 32) != 0) {
            f5 = exercisingActivity.maxHeartRate;
        }
        if ((i2 & 64) != 0) {
            f8 = exercisingActivity.meanHeartRate;
        }
        if ((i2 & 128) != 0) {
            f10 = exercisingActivity.minHeartRate;
        }
        if ((i2 & 256) != 0) {
            j2 = exercisingActivity.startTimestamp;
        }
        if ((i2 & 512) != 0) {
            l = exercisingActivity.endTimestamp;
        }
        if ((i2 & 1024) != 0) {
            exerciseType2 = exercisingActivity.exerciseType;
        }
        long j3 = j2;
        Float f11 = f8;
        Float f12 = f10;
        Float f13 = f;
        Float f14 = f5;
        String str3 = str2;
        Place place2 = place;
        List<Content> list2 = list;
        return exercisingActivity.copy(str, list2, str3, place2, f13, f14, f11, f12, j3, l, exerciseType2);
    }

    public final String component1() {
        return this.id;
    }

    public final Long component10() {
        return this.endTimestamp;
    }

    public final ExerciseType component11() {
        return this.exerciseType;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final String component3() {
        return this.name;
    }

    public final Place component4() {
        return this.location;
    }

    public final Float component5() {
        return this.calorie;
    }

    public final Float component6() {
        return this.maxHeartRate;
    }

    public final Float component7() {
        return this.meanHeartRate;
    }

    public final Float component8() {
        return this.minHeartRate;
    }

    public final long component9() {
        return this.startTimestamp;
    }

    public final ExercisingActivity copy(String str, List<? extends Content> list, String str2, Place place, Float f, Float f5, Float f8, Float f10, long j2, Long l, ExerciseType exerciseType2) {
        j.e(str, "id");
        j.e(list, "contents");
        String str3 = str2;
        j.e(str3, "name");
        ExerciseType exerciseType3 = exerciseType2;
        j.e(exerciseType3, ExercisingActivityBundleWrapper.BUNDLE_KEY_EXERCISE_TYPE);
        return new ExercisingActivity(str, list, str3, place, f, f5, f8, f10, j2, l, exerciseType3);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExercisingActivity)) {
            return false;
        }
        ExercisingActivity exercisingActivity = (ExercisingActivity) obj;
        if (j.a(this.id, exercisingActivity.id) && j.a(this.contents, exercisingActivity.contents) && j.a(this.name, exercisingActivity.name) && j.a(this.location, exercisingActivity.location) && j.a(this.calorie, exercisingActivity.calorie) && j.a(this.maxHeartRate, exercisingActivity.maxHeartRate) && j.a(this.meanHeartRate, exercisingActivity.meanHeartRate) && j.a(this.minHeartRate, exercisingActivity.minHeartRate) && this.startTimestamp == exercisingActivity.startTimestamp && j.a(this.endTimestamp, exercisingActivity.endTimestamp) && this.exerciseType == exercisingActivity.exerciseType) {
            return true;
        }
        return false;
    }

    public final Float getCalorie() {
        return this.calorie;
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final ExerciseType getExerciseType() {
        return this.exerciseType;
    }

    public String getId() {
        return this.id;
    }

    public final Place getLocation() {
        return this.location;
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

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int i10;
        int i11;
        int d = C0212a.d(C0212a.f(this.contents, this.id.hashCode() * 31, 31), 31, this.name);
        Place place = this.location;
        int i12 = 0;
        if (place == null) {
            i2 = 0;
        } else {
            i2 = place.hashCode();
        }
        int i13 = (d + i2) * 31;
        Float f = this.calorie;
        if (f == null) {
            i7 = 0;
        } else {
            i7 = f.hashCode();
        }
        int i14 = (i13 + i7) * 31;
        Float f5 = this.maxHeartRate;
        if (f5 == null) {
            i8 = 0;
        } else {
            i8 = f5.hashCode();
        }
        int i15 = (i14 + i8) * 31;
        Float f8 = this.meanHeartRate;
        if (f8 == null) {
            i10 = 0;
        } else {
            i10 = f8.hashCode();
        }
        int i16 = (i15 + i10) * 31;
        Float f10 = this.minHeartRate;
        if (f10 == null) {
            i11 = 0;
        } else {
            i11 = f10.hashCode();
        }
        int c5 = C0212a.c((i16 + i11) * 31, 31, this.startTimestamp);
        Long l = this.endTimestamp;
        if (l != null) {
            i12 = l.hashCode();
        }
        return this.exerciseType.hashCode() + ((c5 + i12) * 31);
    }

    public String toString() {
        return "ExercisingActivity(id=" + this.id + ", contents=" + this.contents + ", name=" + this.name + ", location=" + this.location + ", calorie=" + this.calorie + ", maxHeartRate=" + this.maxHeartRate + ", meanHeartRate=" + this.meanHeartRate + ", minHeartRate=" + this.minHeartRate + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ", exerciseType=" + this.exerciseType + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeString(this.name);
        Place place = this.location;
        if (place == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            place.writeToParcel(parcel, i2);
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
        parcel.writeString(this.exerciseType.name());
    }
}
