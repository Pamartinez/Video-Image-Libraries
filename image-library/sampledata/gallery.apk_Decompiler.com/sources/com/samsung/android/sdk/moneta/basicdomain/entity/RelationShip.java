package com.samsung.android.sdk.moneta.basicdomain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b/\b\u0002\u0018\u0000 \u00162\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001\u0016B\u0019\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0010j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:¨\u0006;"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "Landroid/os/Parcelable;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationGroup;", "relationGroup", "", "value", "<init>", "(Ljava/lang/String;ILcom/samsung/android/sdk/moneta/basicdomain/entity/RelationGroup;I)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationGroup;", "getRelationGroup", "()Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationGroup;", "I", "getValue", "Companion", "FAMILY_UNKNOWN", "FATHER", "MOTHER", "PARENTS", "HUSBAND", "WIFE", "SPOUSE", "SON", "DAUGHTER", "CHILD", "OLDER_SISTER_MALE", "OLDER_SISTER_FEMALE", "YOUNGER_SISTER", "OLDER_BROTHER_MALE", "OLDER_BROTHER_FEMALE", "YOUNGER_BROTHER", "PATERNAL_GRANDMOTHER", "MATERNAL_GRANDMOTHER", "PATERNAL_GRANDFATHER", "MATERNAL_GRANDFATHER", "MOTHER_IN_LAW_HUSBAND", "MOTHER_IN_LAW_WIFE", "FATHER_IN_LAW_HUSBAND", "FATHER_IN_LAW_WIFE", "DAUGHTER_IN_LAW", "SON_IN_LAW", "BROTHER", "SISTER", "GRAND_FATHER", "GRAND_MOTHER", "FATHER_IN_LAW", "MOTHER_IN_LAW", "ALL", "OTHER", "ME", "UNKNOWN", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum RelationShip implements Parcelable {
    FAMILY_UNKNOWN(r1, 0),
    FATHER(r1, 1),
    MOTHER(r1, 2),
    PARENTS(r1, 23),
    HUSBAND(r1, 3),
    WIFE(r1, 4),
    SPOUSE(r1, 24),
    SON(r1, 5),
    DAUGHTER(r1, 6),
    CHILD(r1, 25),
    OLDER_SISTER_MALE(r1, 7),
    OLDER_SISTER_FEMALE(r1, 8),
    YOUNGER_SISTER(r1, 9),
    OLDER_BROTHER_MALE(r1, 10),
    OLDER_BROTHER_FEMALE(r1, 11),
    YOUNGER_BROTHER(r1, 12),
    PATERNAL_GRANDMOTHER(r1, 13),
    MATERNAL_GRANDMOTHER(r1, 14),
    PATERNAL_GRANDFATHER(r1, 15),
    MATERNAL_GRANDFATHER(r1, 16),
    MOTHER_IN_LAW_HUSBAND(r1, 17),
    MOTHER_IN_LAW_WIFE(r1, 18),
    FATHER_IN_LAW_HUSBAND(r1, 19),
    FATHER_IN_LAW_WIFE(r1, 20),
    DAUGHTER_IN_LAW(r1, 21),
    SON_IN_LAW(r1, 22),
    BROTHER(r1, 26),
    SISTER(r1, 27),
    GRAND_FATHER(r1, 28),
    GRAND_MOTHER(r1, 29),
    FATHER_IN_LAW(r1, 30),
    MOTHER_IN_LAW(r1, 31),
    ALL(r1, 32),
    OTHER(r1, 33),
    ME(r1, 34),
    UNKNOWN(RelationGroup.UNKNOWN, -1);
    
    public static final Parcelable.Creator<RelationShip> CREATOR = null;
    public static final Companion Companion = null;
    private final RelationGroup relationGroup;
    private final int value;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip$Companion;", "", "<init>", "()V", "fromValue", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/RelationShip;", "value", "", "fromName", "name", "", "fromGroupAndVal", "group", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final RelationShip fromGroupAndVal(int i2, int i7) {
            for (RelationShip relationShip : RelationShip.values()) {
                if (relationShip.getRelationGroup().ordinal() == i2 && relationShip.getValue() == i7) {
                    return relationShip;
                }
            }
            return RelationShip.UNKNOWN;
        }

        public final RelationShip fromName(String str) {
            j.e(str, "name");
            for (RelationShip relationShip : RelationShip.values()) {
                String name = relationShip.name();
                String upperCase = str.toUpperCase(Locale.ROOT);
                j.d(upperCase, "toUpperCase(...)");
                if (j.a(name, upperCase)) {
                    return relationShip;
                }
            }
            return RelationShip.UNKNOWN;
        }

        public final RelationShip fromValue(int i2) {
            for (RelationShip relationShip : RelationShip.values()) {
                if (relationShip.getValue() == i2) {
                    return relationShip;
                }
            }
            return RelationShip.UNKNOWN;
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<RelationShip> {
        public final RelationShip createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return RelationShip.valueOf(parcel.readString());
        }

        public final RelationShip[] newArray(int i2) {
            return new RelationShip[i2];
        }
    }

    static {
        RelationShip[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        CREATOR = new Creator();
    }

    private RelationShip(RelationGroup relationGroup2, int i2) {
        this.relationGroup = relationGroup2;
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int describeContents() {
        return 0;
    }

    public final RelationGroup getRelationGroup() {
        return this.relationGroup;
    }

    public final int getValue() {
        return this.value;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(name());
    }
}
