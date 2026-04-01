package androidx.activity.result;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001d\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"androidx/activity/result/IntentSenderRequest$Companion$CREATOR$1", "Landroid/os/Parcelable$Creator;", "Landroidx/activity/result/IntentSenderRequest;", "createFromParcel", "inParcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Landroidx/activity/result/IntentSenderRequest;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IntentSenderRequest$Companion$CREATOR$1 implements Parcelable.Creator<IntentSenderRequest> {
    public IntentSenderRequest createFromParcel(Parcel parcel) {
        j.e(parcel, "inParcel");
        return new IntentSenderRequest(parcel);
    }

    public IntentSenderRequest[] newArray(int i2) {
        return new IntentSenderRequest[i2];
    }
}
