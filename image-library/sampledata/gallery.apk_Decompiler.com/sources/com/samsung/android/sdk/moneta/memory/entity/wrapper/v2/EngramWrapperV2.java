package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.Engram;
import com.samsung.android.sdk.moneta.memory.entity.activity.Activity;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.EngramBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.context.TravelStateBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.util.WrapperMapperKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(BE\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b$\u0010#R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u001e\u001a\u0004\b%\u0010 R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010&\u001a\u0004\b\f\u0010'¨\u0006)"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/EngramWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/EngramWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "activitieWrappers", "", "startTimestamp", "endTimestamp", "specialMoments", "", "isComplete", "<init>", "(Ljava/lang/String;Ljava/util/List;JJLjava/util/List;Z)V", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "toEngram", "()Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getActivitieWrappers", "()Ljava/util/List;", "J", "getStartTimestamp", "()J", "getEndTimestamp", "getSpecialMoments", "Z", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramWrapperV2 extends EngramWrapper {
    public static final Parcelable.Creator<EngramWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<ActivityWrapper> activitieWrappers;
    private final long endTimestamp;
    private final String id;
    private final boolean isComplete;
    private final List<String> specialMoments;
    private final long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/EngramWrapperV2$Companion;", "", "<init>", "()V", "fromEngram", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/EngramWrapperV2;", "engram", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramWrapperV2 fromEngram(Engram engram) {
            j.e(engram, TravelStateBundleWrapper.BUNDLE_KEY_ENGRAM);
            String id = engram.getId();
            Iterable<Activity> activities = engram.getActivities();
            ArrayList arrayList = new ArrayList(C1196n.w0(activities, 10));
            for (Activity wrapperV2 : activities) {
                arrayList.add(WrapperMapperKt.toWrapperV2(wrapperV2));
            }
            return new EngramWrapperV2(id, arrayList, engram.getStartTimestamp(), engram.getEndTimestamp(), engram.getSpecialMoments(), engram.isComplete());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramWrapperV2> {
        public final EngramWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(EngramWrapperV2.class, parcel, arrayList, i2, 1);
            }
            return new EngramWrapperV2(readString, arrayList, parcel.readLong(), parcel.readLong(), parcel.createStringArrayList(), parcel.readInt() != 0);
        }

        public final EngramWrapperV2[] newArray(int i2) {
            return new EngramWrapperV2[i2];
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramWrapperV2(String str, List list, long j2, long j3, List list2, boolean z, int i2, e eVar) {
        this(str, list, j2, j3, (i2 & 16) != 0 ? C1202t.d : list2, z);
    }

    public final int describeContents() {
        return 0;
    }

    public final List<ActivityWrapper> getActivitieWrappers() {
        return this.activitieWrappers;
    }

    public final long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getId() {
        return this.id;
    }

    public final List<String> getSpecialMoments() {
        return this.specialMoments;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final boolean isComplete() {
        return this.isComplete;
    }

    public /* synthetic */ Engram toEngram() {
        String str = this.id;
        Iterable<ActivityWrapper> iterable = this.activitieWrappers;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (ActivityWrapper activity : iterable) {
            arrayList.add(activity.toActivity());
        }
        return new Engram(str, arrayList, this.startTimestamp, this.endTimestamp, this.specialMoments, this.isComplete);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.activitieWrappers);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeLong(this.startTimestamp);
        parcel.writeLong(this.endTimestamp);
        parcel.writeStringList(this.specialMoments);
        parcel.writeInt(this.isComplete ? 1 : 0);
    }

    public EngramWrapperV2(String str, List<? extends ActivityWrapper> list, long j2, long j3, List<String> list2, boolean z) {
        j.e(str, "id");
        j.e(list, "activitieWrappers");
        j.e(list2, EngramBundleWrapper.BUNDLE_KEY_SPECIAL_MOMENTS);
        this.id = str;
        this.activitieWrappers = list;
        this.startTimestamp = j2;
        this.endTimestamp = j3;
        this.specialMoments = list2;
        this.isComplete = z;
    }
}
