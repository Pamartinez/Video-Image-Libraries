package com.samsung.android.sdk.moneta.lifestyle.social.entity.option;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.lifestyle.social.entity.ContactChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0017B!\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;", "Landroid/os/Parcelable;", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/ContactChannel;", "contactChannel", "", "myPhoneNumber", "<init>", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/ContactChannel;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/ContactChannel;", "getContactChannel", "()Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/ContactChannel;", "Ljava/lang/String;", "getMyPhoneNumber", "()Ljava/lang/String;", "Builder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MostContactRequestOption implements Parcelable {
    public static final Parcelable.Creator<MostContactRequestOption> CREATOR = new Creator();
    private final ContactChannel contactChannel;
    private final String myPhoneNumber;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption$Builder;", "", "contactChannel", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/ContactChannel;", "myPhoneNumber", "", "<init>", "(Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/ContactChannel;Ljava/lang/String;)V", "setContactChannel", "setMyPhoneNumber", "build", "Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/option/MostContactRequestOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ContactChannel contactChannel;
        private String myPhoneNumber;

        public Builder() {
            this((ContactChannel) null, (String) null, 3, (e) null);
        }

        public final MostContactRequestOption build() {
            return new MostContactRequestOption(this.contactChannel, this.myPhoneNumber, (e) null);
        }

        public final Builder setContactChannel(ContactChannel contactChannel2) {
            j.e(contactChannel2, "contactChannel");
            this.contactChannel = contactChannel2;
            return this;
        }

        public final Builder setMyPhoneNumber(String str) {
            j.e(str, "myPhoneNumber");
            this.myPhoneNumber = str;
            return this;
        }

        public Builder(ContactChannel contactChannel2, String str) {
            this.contactChannel = contactChannel2;
            this.myPhoneNumber = str;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(ContactChannel contactChannel2, String str, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : contactChannel2, (i2 & 2) != 0 ? null : str);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MostContactRequestOption> {
        public final MostContactRequestOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MostContactRequestOption(parcel.readInt() == 0 ? null : ContactChannel.valueOf(parcel.readString()), parcel.readString(), (e) null);
        }

        public final MostContactRequestOption[] newArray(int i2) {
            return new MostContactRequestOption[i2];
        }
    }

    public /* synthetic */ MostContactRequestOption(ContactChannel contactChannel2, String str, e eVar) {
        this(contactChannel2, str);
    }

    public final int describeContents() {
        return 0;
    }

    public final ContactChannel getContactChannel() {
        return this.contactChannel;
    }

    public final String getMyPhoneNumber() {
        return this.myPhoneNumber;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        ContactChannel contactChannel2 = this.contactChannel;
        if (contactChannel2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(contactChannel2.name());
        }
        parcel.writeString(this.myPhoneNumber);
    }

    private MostContactRequestOption(ContactChannel contactChannel2, String str) {
        this.contactChannel = contactChannel2;
        this.myPhoneNumber = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MostContactRequestOption(ContactChannel contactChannel2, String str, int i2, e eVar) {
        this((i2 & 1) != 0 ? null : contactChannel2, (i2 & 2) != 0 ? null : str);
    }
}
