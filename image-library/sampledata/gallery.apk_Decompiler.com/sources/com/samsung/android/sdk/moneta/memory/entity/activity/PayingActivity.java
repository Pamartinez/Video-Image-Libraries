package com.samsung.android.sdk.moneta.memory.entity.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.context.Place;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\"\u0010\u001bJ\u0012\u0010#\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b#\u0010\u001bJ\u0012\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b$\u0010%Jd\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b(\u0010\u001bJ\u0010\u0010)\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b)\u0010\u0019J\u001a\u0010-\u001a\u00020,2\b\u0010+\u001a\u0004\u0018\u00010*HÖ\u0003¢\u0006\u0004\b-\u0010.R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u0010\u001bR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u00101\u001a\u0004\b2\u0010\u001dR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u00103\u001a\u0004\b4\u0010\u001fR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u00105\u001a\u0004\b6\u0010!R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010/\u001a\u0004\b7\u0010\u001bR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010/\u001a\u0004\b8\u0010\u001bR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00109\u001a\u0004\b:\u0010%¨\u0006;"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/PayingActivity;", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "", "timestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "location", "paymentType", "merchantDisplayName", "", "amount", "<init>", "(Ljava/lang/String;Ljava/util/List;JLcom/samsung/android/sdk/moneta/memory/entity/context/Place;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()J", "component4", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "component5", "component6", "component7", "()Ljava/lang/Double;", "copy", "(Ljava/lang/String;Ljava/util/List;JLcom/samsung/android/sdk/moneta/memory/entity/context/Place;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)Lcom/samsung/android/sdk/moneta/memory/entity/activity/PayingActivity;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Ljava/util/List;", "getContents", "J", "getTimestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Place;", "getLocation", "getPaymentType", "getMerchantDisplayName", "Ljava/lang/Double;", "getAmount", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PayingActivity extends Activity {
    public static final Parcelable.Creator<PayingActivity> CREATOR = new Creator();
    private final Double amount;
    private final List<Content> contents;
    private final String id;
    private final Place location;
    private final String merchantDisplayName;
    private final String paymentType;
    private final long timestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PayingActivity> {
        public final PayingActivity createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(PayingActivity.class, parcel, arrayList, i2, 1);
            }
            long readLong = parcel.readLong();
            Double d = null;
            Place createFromParcel = parcel.readInt() == 0 ? null : Place.CREATOR.createFromParcel(parcel);
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            if (parcel.readInt() != 0) {
                d = Double.valueOf(parcel.readDouble());
            }
            return new PayingActivity(readString, arrayList, readLong, createFromParcel, readString2, readString3, d);
        }

        public final PayingActivity[] newArray(int i2) {
            return new PayingActivity[i2];
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PayingActivity(String str, List<? extends Content> list, long j2, Place place, String str2, String str3, Double d) {
        super(ActivityType.Paying);
        j.e(str, "id");
        j.e(list, "contents");
        this.id = str;
        this.contents = list;
        this.timestamp = j2;
        this.location = place;
        this.paymentType = str2;
        this.merchantDisplayName = str3;
        this.amount = d;
    }

    public static /* synthetic */ PayingActivity copy$default(PayingActivity payingActivity, String str, List<Content> list, long j2, Place place, String str2, String str3, Double d, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = payingActivity.id;
        }
        if ((i2 & 2) != 0) {
            list = payingActivity.contents;
        }
        if ((i2 & 4) != 0) {
            j2 = payingActivity.timestamp;
        }
        if ((i2 & 8) != 0) {
            place = payingActivity.location;
        }
        if ((i2 & 16) != 0) {
            str2 = payingActivity.paymentType;
        }
        if ((i2 & 32) != 0) {
            str3 = payingActivity.merchantDisplayName;
        }
        if ((i2 & 64) != 0) {
            d = payingActivity.amount;
        }
        long j3 = j2;
        List<Content> list2 = list;
        return payingActivity.copy(str, list2, j3, place, str2, str3, d);
    }

    public final String component1() {
        return this.id;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final long component3() {
        return this.timestamp;
    }

    public final Place component4() {
        return this.location;
    }

    public final String component5() {
        return this.paymentType;
    }

    public final String component6() {
        return this.merchantDisplayName;
    }

    public final Double component7() {
        return this.amount;
    }

    public final PayingActivity copy(String str, List<? extends Content> list, long j2, Place place, String str2, String str3, Double d) {
        j.e(str, "id");
        j.e(list, "contents");
        return new PayingActivity(str, list, j2, place, str2, str3, d);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PayingActivity)) {
            return false;
        }
        PayingActivity payingActivity = (PayingActivity) obj;
        if (j.a(this.id, payingActivity.id) && j.a(this.contents, payingActivity.contents) && this.timestamp == payingActivity.timestamp && j.a(this.location, payingActivity.location) && j.a(this.paymentType, payingActivity.paymentType) && j.a(this.merchantDisplayName, payingActivity.merchantDisplayName) && j.a(this.amount, payingActivity.amount)) {
            return true;
        }
        return false;
    }

    public final Double getAmount() {
        return this.amount;
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public String getId() {
        return this.id;
    }

    public final Place getLocation() {
        return this.location;
    }

    public final String getMerchantDisplayName() {
        return this.merchantDisplayName;
    }

    public final String getPaymentType() {
        return this.paymentType;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8;
        int c5 = C0212a.c(C0212a.f(this.contents, this.id.hashCode() * 31, 31), 31, this.timestamp);
        Place place = this.location;
        int i10 = 0;
        if (place == null) {
            i2 = 0;
        } else {
            i2 = place.hashCode();
        }
        int i11 = (c5 + i2) * 31;
        String str = this.paymentType;
        if (str == null) {
            i7 = 0;
        } else {
            i7 = str.hashCode();
        }
        int i12 = (i11 + i7) * 31;
        String str2 = this.merchantDisplayName;
        if (str2 == null) {
            i8 = 0;
        } else {
            i8 = str2.hashCode();
        }
        int i13 = (i12 + i8) * 31;
        Double d = this.amount;
        if (d != null) {
            i10 = d.hashCode();
        }
        return i13 + i10;
    }

    public String toString() {
        return "PayingActivity(id=" + this.id + ", contents=" + this.contents + ", timestamp=" + this.timestamp + ", location=" + this.location + ", paymentType=" + this.paymentType + ", merchantDisplayName=" + this.merchantDisplayName + ", amount=" + this.amount + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeLong(this.timestamp);
        Place place = this.location;
        if (place == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            place.writeToParcel(parcel, i2);
        }
        parcel.writeString(this.paymentType);
        parcel.writeString(this.merchantDisplayName);
        Double d = this.amount;
        if (d == null) {
            parcel.writeInt(0);
        } else {
            a.n(parcel, 1, d);
        }
    }
}
