package com.samsung.android.sdk.moneta.basicdomain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u0000 \u00112\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u000ej\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/AgeGroup;", "Landroid/os/Parcelable;", "", "", "value", "<init>", "(Ljava/lang/String;II)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "I", "getValue", "Companion", "MINOR_LEVEL_1", "MINOR_LEVEL_2", "ADULT_LEVEL_1", "ADULT_LEVEL_2", "ADULT_LEVEL_3", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum AgeGroup implements Parcelable {
    MINOR_LEVEL_1(1),
    MINOR_LEVEL_2(2),
    ADULT_LEVEL_1(3),
    ADULT_LEVEL_2(4),
    ADULT_LEVEL_3(5);
    
    public static final Parcelable.Creator<AgeGroup> CREATOR = null;
    public static final Companion Companion = null;
    public static final String TAG = "AgeGroup";
    private final int value;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/AgeGroup$Companion;", "", "<init>", "()V", "TAG", "", "fromValue", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/AgeGroup;", "value", "", "ageToAgeGroup", "age", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final AgeGroup ageToAgeGroup(int i2) {
            if (i2 >= 0 && i2 < 10) {
                return AgeGroup.MINOR_LEVEL_1;
            }
            if (10 <= i2 && i2 < 18) {
                return AgeGroup.MINOR_LEVEL_2;
            }
            if (18 <= i2 && i2 < 35) {
                return AgeGroup.ADULT_LEVEL_1;
            }
            if (35 > i2 || i2 >= 55) {
                return AgeGroup.ADULT_LEVEL_3;
            }
            return AgeGroup.ADULT_LEVEL_2;
        }

        public final AgeGroup fromValue(int i2) {
            for (AgeGroup ageGroup : AgeGroup.getEntries()) {
                if (ageGroup.getValue() == i2) {
                    return ageGroup;
                }
            }
            Logger logger = Logger.INSTANCE;
            logger.e$pde_sdk_1_0_40_release(AgeGroup.TAG, "No matching AgeGroup found for value " + i2 + '.');
            return null;
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<AgeGroup> {
        public final AgeGroup createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return AgeGroup.valueOf(parcel.readString());
        }

        public final AgeGroup[] newArray(int i2) {
            return new AgeGroup[i2];
        }
    }

    static {
        AgeGroup[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        CREATOR = new Creator();
    }

    private AgeGroup(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getValue() {
        return this.value;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(name());
    }
}
