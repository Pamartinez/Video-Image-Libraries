package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.PayingActivity;
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

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.BM\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001d\u001a\u0004\b)\u0010\u001fR\u0019\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\u001d\u001a\u0004\b*\u0010\u001fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010+\u001a\u0004\b,\u0010-¨\u0006/"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/PayingActivityWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "contentWrappers", "", "timestamp", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "locationWrapper", "paymentType", "merchantDisplayName", "", "amount", "<init>", "(Ljava/lang/String;Ljava/util/List;JLcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/PayingActivity;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/PayingActivity;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getContentWrappers", "()Ljava/util/List;", "J", "getTimestamp", "()J", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getLocationWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PlaceWrapperV2;", "getPaymentType", "getMerchantDisplayName", "Ljava/lang/Double;", "getAmount", "()Ljava/lang/Double;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PayingActivityWrapperV2 extends ActivityWrapper {
    public static final Parcelable.Creator<PayingActivityWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Double amount;
    private final List<ContentWrapper> contentWrappers;
    private final String id;
    private final PlaceWrapperV2 locationWrapper;
    private final String merchantDisplayName;
    private final String paymentType;
    private final long timestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/PayingActivityWrapperV2$Companion;", "", "<init>", "()V", "fromActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/activity/PayingActivityWrapperV2;", "payingActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/PayingActivity;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ PayingActivityWrapperV2 fromActivity(PayingActivity payingActivity) {
            PlaceWrapperV2 placeWrapperV2;
            j.e(payingActivity, "payingActivity");
            String id = payingActivity.getId();
            Iterable<Content> contents = payingActivity.getContents();
            ArrayList arrayList = new ArrayList(C1196n.w0(contents, 10));
            for (Content wrapperV2 : contents) {
                arrayList.add(WrapperMapperKt.toWrapperV2(wrapperV2));
            }
            long timestamp = payingActivity.getTimestamp();
            Place location = payingActivity.getLocation();
            if (location != null) {
                placeWrapperV2 = j.e(location, "<this>");
            } else {
                placeWrapperV2 = null;
            }
            return new PayingActivityWrapperV2(id, arrayList, timestamp, placeWrapperV2, payingActivity.getPaymentType(), payingActivity.getMerchantDisplayName(), payingActivity.getAmount());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<PayingActivityWrapperV2> {
        public final PayingActivityWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(PayingActivityWrapperV2.class, parcel, arrayList, i2, 1);
            }
            long readLong = parcel.readLong();
            Double d = null;
            PlaceWrapperV2 createFromParcel = parcel.readInt() == 0 ? null : PlaceWrapperV2.CREATOR.createFromParcel(parcel);
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            if (parcel.readInt() != 0) {
                d = Double.valueOf(parcel.readDouble());
            }
            return new PayingActivityWrapperV2(readString, arrayList, readLong, createFromParcel, readString2, readString3, d);
        }

        public final PayingActivityWrapperV2[] newArray(int i2) {
            return new PayingActivityWrapperV2[i2];
        }
    }

    public PayingActivityWrapperV2(String str, List<? extends ContentWrapper> list, long j2, PlaceWrapperV2 placeWrapperV2, String str2, String str3, Double d) {
        j.e(str, "id");
        j.e(list, "contentWrappers");
        this.id = str;
        this.contentWrappers = list;
        this.timestamp = j2;
        this.locationWrapper = placeWrapperV2;
        this.paymentType = str2;
        this.merchantDisplayName = str3;
        this.amount = d;
    }

    public final int describeContents() {
        return 0;
    }

    public final Double getAmount() {
        return this.amount;
    }

    public final List<ContentWrapper> getContentWrappers() {
        return this.contentWrappers;
    }

    public final String getId() {
        return this.id;
    }

    public final PlaceWrapperV2 getLocationWrapper() {
        return this.locationWrapper;
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

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contentWrappers);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
        parcel.writeLong(this.timestamp);
        PlaceWrapperV2 placeWrapperV2 = this.locationWrapper;
        if (placeWrapperV2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            placeWrapperV2.writeToParcel(parcel, i2);
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
