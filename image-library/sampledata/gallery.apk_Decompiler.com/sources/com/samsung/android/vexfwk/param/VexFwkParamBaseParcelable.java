package com.samsung.android.vexfwk.param;

import L1.d;
import Sf.q;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.metadata.IVexFwkMetadataConverter;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u000f\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0010\u0010\fJ\u0017\u0010\u0011\u001a\u00020\t2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u000fR \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Landroid/os/Parcelable;", "T", "Lcom/samsung/android/vexfwk/metadata/IVexFwkMetadataConverter;", "Ljava/util/function/Supplier;", "Landroid/os/Parcelable$Creator;", "creatorSupplier", "<init>", "(Ljava/util/function/Supplier;)V", "", "data", "fromParcel", "([B)Landroid/os/Parcelable;", "value", "toParcel", "(Landroid/os/Parcelable;)[B", "from", "to", "Ljava/util/function/Supplier;", "creator$delegate", "Lme/f;", "getCreator", "()Landroid/os/Parcelable$Creator;", "creator", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkParamBaseParcelable<T extends Parcelable> implements IVexFwkMetadataConverter<T> {
    private final f creator$delegate = d.q(new q(17, this));
    private final Supplier<Parcelable.Creator<T>> creatorSupplier;

    public VexFwkParamBaseParcelable(Supplier<Parcelable.Creator<T>> supplier) {
        j.e(supplier, "creatorSupplier");
        this.creatorSupplier = supplier;
    }

    /* access modifiers changed from: private */
    public static final Parcelable.Creator creator_delegate$lambda$0(VexFwkParamBaseParcelable vexFwkParamBaseParcelable) {
        Parcelable.Creator<T> creator = vexFwkParamBaseParcelable.creatorSupplier.get();
        j.d(creator, "get(...)");
        return creator;
    }

    private final T fromParcel(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        j.d(obtain, "obtain(...)");
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T t = (Parcelable) getCreator().createFromParcel(obtain);
        obtain.recycle();
        j.b(t);
        return t;
    }

    private final Parcelable.Creator<T> getCreator() {
        return (Parcelable.Creator) this.creator$delegate.getValue();
    }

    private final byte[] toParcel(T t) {
        Parcel obtain = Parcel.obtain();
        j.d(obtain, "obtain(...)");
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        j.b(marshall);
        return marshall;
    }

    public T from(byte[] bArr) {
        j.e(bArr, "data");
        return fromParcel(bArr);
    }

    public byte[] to(T t) {
        j.e(t, "value");
        return toParcel(t);
    }
}
