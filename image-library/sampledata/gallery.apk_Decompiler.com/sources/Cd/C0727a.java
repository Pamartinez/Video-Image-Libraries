package Cd;

import android.os.IBinder;
import android.os.Parcel;
import com.samsung.android.sdk.scs.ai.downloader.ModelDownloader;

/* renamed from: Cd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0727a implements C0729c {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f3323a;

    public final int a(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.cloudcore.IDownloadService");
            obtain.writeString(str);
            this.f3323a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f3323a;
    }

    public final int b(String[] strArr, boolean z, boolean z3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.cloudcore.IDownloadService");
            obtain.writeString(ModelDownloader.MODEL_FEATURE_LITERT);
            obtain.writeStringArray(strArr);
            obtain.writeInt(z ? 1 : 0);
            obtain.writeInt(z3 ? 1 : 0);
            this.f3323a.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final int c(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.cloudcore.IDownloadService");
            obtain.writeString(str);
            this.f3323a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final int d(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.cloudcore.IDownloadService");
            obtain.writeString(str);
            this.f3323a.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
