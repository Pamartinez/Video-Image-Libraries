package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0002\u0018\u0000 \u00112\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u000ej\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&¨\u0006'"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;", "Landroid/os/Parcelable;", "", "", "value", "<init>", "(Ljava/lang/String;II)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "I", "getValue", "Companion", "TAG_INVALID", "TAG_PERSON", "TAG_FOOD", "TAG_FLOWER", "TAG_ANIMAL", "TAG_DRINK", "TAG_PETFACE", "TAG_MOON", "TAG_PET", "TAG_HUMANHEAD", "TAG_TEXT", "TAG_SCENE_TEXT", "TAG_WINE", "TAG_WINELABEL", "TAG_DOC_TEXT", "TAG_SUNSET_SUNRISE", "TAG_SNOW", "TAG_HOMEINDOOR", "TAG_SCENERY", "TAG_GREENERY", "TAG_RESTAURANT_INDOOR", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkUnifiedDetectorTagCategory implements Parcelable {
    TAG_INVALID(0),
    TAG_PERSON(2),
    TAG_FOOD(3),
    TAG_FLOWER(5),
    TAG_ANIMAL(6),
    TAG_DRINK(7),
    TAG_PETFACE(8),
    TAG_MOON(9),
    TAG_PET(10),
    TAG_HUMANHEAD(11),
    TAG_TEXT(12),
    TAG_SCENE_TEXT(13),
    TAG_WINE(15),
    TAG_WINELABEL(16),
    TAG_DOC_TEXT(17),
    TAG_SUNSET_SUNRISE(100),
    TAG_SNOW(101),
    TAG_HOMEINDOOR(102),
    TAG_SCENERY(103),
    TAG_GREENERY(104),
    TAG_RESTAURANT_INDOOR(105);
    
    public static final Parcelable.Creator<VexFwkUnifiedDetectorTagCategory> CREATOR = null;
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory$Companion;", "", "<init>", "()V", "fromInt", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;", "value", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final VexFwkUnifiedDetectorTagCategory fromInt(int i2) {
            for (VexFwkUnifiedDetectorTagCategory vexFwkUnifiedDetectorTagCategory : VexFwkUnifiedDetectorTagCategory.getEntries()) {
                if (vexFwkUnifiedDetectorTagCategory.getValue() == i2) {
                    return vexFwkUnifiedDetectorTagCategory;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkUnifiedDetectorTagCategory> {
        public final VexFwkUnifiedDetectorTagCategory createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return VexFwkUnifiedDetectorTagCategory.valueOf(parcel.readString());
        }

        public final VexFwkUnifiedDetectorTagCategory[] newArray(int i2) {
            return new VexFwkUnifiedDetectorTagCategory[i2];
        }
    }

    static {
        VexFwkUnifiedDetectorTagCategory[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        CREATOR = new Creator();
    }

    private VexFwkUnifiedDetectorTagCategory(int i2) {
        this.value = i2;
    }

    public static final VexFwkUnifiedDetectorTagCategory fromInt(int i2) {
        return Companion.fromInt(i2);
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
