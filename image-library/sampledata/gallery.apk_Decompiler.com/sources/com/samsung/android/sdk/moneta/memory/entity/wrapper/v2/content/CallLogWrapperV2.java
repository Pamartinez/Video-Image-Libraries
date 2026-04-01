package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.CallLog;
import com.samsung.android.sdk.moneta.memory.entity.context.Person;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.context.PersonWrapperV2;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/CallLogWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "contentUri", "", "callId", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;", "senderOrRecipientWrapper", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/CallLog;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/CallLog;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getContentUri", "J", "getCallId", "()J", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;", "getSenderOrRecipientWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/context/PersonWrapperV2;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CallLogWrapperV2 extends ContentWrapper {
    public static final Parcelable.Creator<CallLogWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final long callId;
    private final String contentUri;
    private final String id;
    private final PersonWrapperV2 senderOrRecipientWrapper;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/CallLogWrapperV2$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/CallLogWrapperV2;", "callLog", "Lcom/samsung/android/sdk/moneta/memory/entity/content/CallLog;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ CallLogWrapperV2 fromContent(CallLog callLog) {
            PersonWrapperV2 personWrapperV2;
            j.e(callLog, "callLog");
            String id = callLog.getId();
            String contentUri = callLog.getContentUri();
            long callId = callLog.getCallId();
            Person senderOrRecipient = callLog.getSenderOrRecipient();
            if (senderOrRecipient != null) {
                personWrapperV2 = j.e(senderOrRecipient, "<this>");
            } else {
                personWrapperV2 = null;
            }
            return new CallLogWrapperV2(id, contentUri, callId, personWrapperV2);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<CallLogWrapperV2> {
        public final CallLogWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new CallLogWrapperV2(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt() == 0 ? null : PersonWrapperV2.CREATOR.createFromParcel(parcel));
        }

        public final CallLogWrapperV2[] newArray(int i2) {
            return new CallLogWrapperV2[i2];
        }
    }

    public CallLogWrapperV2(String str, String str2, long j2, PersonWrapperV2 personWrapperV2) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        this.id = str;
        this.contentUri = str2;
        this.callId = j2;
        this.senderOrRecipientWrapper = personWrapperV2;
    }

    public final int describeContents() {
        return 0;
    }

    public final long getCallId() {
        return this.callId;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public final String getId() {
        return this.id;
    }

    public final PersonWrapperV2 getSenderOrRecipientWrapper() {
        return this.senderOrRecipientWrapper;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.contentUri);
        parcel.writeLong(this.callId);
        PersonWrapperV2 personWrapperV2 = this.senderOrRecipientWrapper;
        if (personWrapperV2 == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        personWrapperV2.writeToParcel(parcel, i2);
    }
}
