package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.context.Person;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ:\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0015J\u0010\u0010\u001e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0013J\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u001fHÖ\u0003¢\u0006\u0004\b\"\u0010#R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b&\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b(\u0010\u0018R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010)\u001a\u0004\b*\u0010\u001a¨\u0006+"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/CallLog;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "contentUri", "", "callId", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "senderOrRecipient", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLcom/samsung/android/sdk/moneta/memory/entity/context/Person;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()J", "component4", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "copy", "(Ljava/lang/String;Ljava/lang/String;JLcom/samsung/android/sdk/moneta/memory/entity/context/Person;)Lcom/samsung/android/sdk/moneta/memory/entity/content/CallLog;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getContentUri", "J", "getCallId", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "getSenderOrRecipient", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CallLog extends Content {
    public static final Parcelable.Creator<CallLog> CREATOR = new Creator();
    private final long callId;
    private final String contentUri;
    private final String id;
    private final Person senderOrRecipient;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<CallLog> {
        public final CallLog createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new CallLog(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt() == 0 ? null : Person.CREATOR.createFromParcel(parcel));
        }

        public final CallLog[] newArray(int i2) {
            return new CallLog[i2];
        }
    }

    public CallLog(String str, String str2, long j2, Person person) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        this.id = str;
        this.contentUri = str2;
        this.callId = j2;
        this.senderOrRecipient = person;
    }

    public static /* synthetic */ CallLog copy$default(CallLog callLog, String str, String str2, long j2, Person person, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = callLog.id;
        }
        if ((i2 & 2) != 0) {
            str2 = callLog.contentUri;
        }
        if ((i2 & 4) != 0) {
            j2 = callLog.callId;
        }
        if ((i2 & 8) != 0) {
            person = callLog.senderOrRecipient;
        }
        Person person2 = person;
        String str3 = str2;
        return callLog.copy(str, str3, j2, person2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.contentUri;
    }

    public final long component3() {
        return this.callId;
    }

    public final Person component4() {
        return this.senderOrRecipient;
    }

    public final CallLog copy(String str, String str2, long j2, Person person) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        return new CallLog(str, str2, j2, person);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallLog)) {
            return false;
        }
        CallLog callLog = (CallLog) obj;
        if (j.a(this.id, callLog.id) && j.a(this.contentUri, callLog.contentUri) && this.callId == callLog.callId && j.a(this.senderOrRecipient, callLog.senderOrRecipient)) {
            return true;
        }
        return false;
    }

    public final long getCallId() {
        return this.callId;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public String getId() {
        return this.id;
    }

    public final Person getSenderOrRecipient() {
        return this.senderOrRecipient;
    }

    public int hashCode() {
        int i2;
        int c5 = C0212a.c(C0212a.d(this.id.hashCode() * 31, 31, this.contentUri), 31, this.callId);
        Person person = this.senderOrRecipient;
        if (person == null) {
            i2 = 0;
        } else {
            i2 = person.hashCode();
        }
        return c5 + i2;
    }

    public String toString() {
        return "CallLog(id=" + this.id + ", contentUri=" + this.contentUri + ", callId=" + this.callId + ", senderOrRecipient=" + this.senderOrRecipient + ')';
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.contentUri);
        parcel.writeLong(this.callId);
        Person person = this.senderOrRecipient;
        if (person == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        person.writeToParcel(parcel, i2);
    }
}
