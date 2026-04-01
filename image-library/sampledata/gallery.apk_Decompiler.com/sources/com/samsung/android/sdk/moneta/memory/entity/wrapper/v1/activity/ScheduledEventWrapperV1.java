package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.activity;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ScheduledEvent;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ActivityWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/activity/ScheduledEventWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ActivityWrapper;", "", "id", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "contents", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "toActivity", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getContents", "()Ljava/util/List;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduledEventWrapperV1 extends ActivityWrapper {
    public static final Parcelable.Creator<ScheduledEventWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<Content> contents;
    private final String id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/activity/ScheduledEventWrapperV1$Companion;", "", "<init>", "()V", "fromActivity", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/activity/ScheduledEventWrapperV1;", "scheduledEvent", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ScheduledEvent;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ScheduledEventWrapperV1 fromActivity(ScheduledEvent scheduledEvent) {
            j.e(scheduledEvent, "scheduledEvent");
            return new ScheduledEventWrapperV1(scheduledEvent.getId(), scheduledEvent.getContents());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ScheduledEventWrapperV1> {
        public final ScheduledEventWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            int i2 = 0;
            while (i2 != readInt) {
                i2 = a.b(ScheduledEventWrapperV1.class, parcel, arrayList, i2, 1);
            }
            return new ScheduledEventWrapperV1(readString, arrayList);
        }

        public final ScheduledEventWrapperV1[] newArray(int i2) {
            return new ScheduledEventWrapperV1[i2];
        }
    }

    public ScheduledEventWrapperV1(String str, List<? extends Content> list) {
        j.e(str, "id");
        j.e(list, "contents");
        this.id = str;
        this.contents = list;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<Content> getContents() {
        return this.contents;
    }

    public final String getId() {
        return this.id;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        Iterator j2 = a.j(parcel, this.contents);
        while (j2.hasNext()) {
            parcel.writeParcelable((Parcelable) j2.next(), i2);
        }
    }
}
