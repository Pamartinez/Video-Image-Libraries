package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Message;
import com.samsung.android.sdk.moneta.memory.entity.context.Person;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/MessageWrapperV1;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "contentUri", "", "messageId", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "senderOrRecipient", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLcom/samsung/android/sdk/moneta/memory/entity/context/Person;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Message;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/Message;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getContentUri", "J", "getMessageId", "()J", "Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "getSenderOrRecipient", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/Person;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MessageWrapperV1 extends ContentWrapper {
    public static final Parcelable.Creator<MessageWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String contentUri;
    private final String id;
    private final long messageId;
    private final Person senderOrRecipient;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/MessageWrapperV1$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/content/MessageWrapperV1;", "message", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Message;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ MessageWrapperV1 fromContent(Message message) {
            j.e(message, "message");
            return new MessageWrapperV1(message.getId(), message.getContentUri(), message.getMessageId(), message.getSenderOrRecipient());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MessageWrapperV1> {
        public final MessageWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MessageWrapperV1(parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt() == 0 ? null : Person.CREATOR.createFromParcel(parcel));
        }

        public final MessageWrapperV1[] newArray(int i2) {
            return new MessageWrapperV1[i2];
        }
    }

    public MessageWrapperV1(String str, String str2, long j2, Person person) {
        j.e(str, "id");
        j.e(str2, "contentUri");
        this.id = str;
        this.contentUri = str2;
        this.messageId = j2;
        this.senderOrRecipient = person;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public final String getId() {
        return this.id;
    }

    public final long getMessageId() {
        return this.messageId;
    }

    public final Person getSenderOrRecipient() {
        return this.senderOrRecipient;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.contentUri);
        parcel.writeLong(this.messageId);
        Person person = this.senderOrRecipient;
        if (person == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        person.writeToParcel(parcel, i2);
    }
}
