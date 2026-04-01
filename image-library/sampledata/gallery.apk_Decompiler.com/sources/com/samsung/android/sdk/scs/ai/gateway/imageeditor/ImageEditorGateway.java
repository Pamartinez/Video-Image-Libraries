package com.samsung.android.sdk.scs.ai.gateway.imageeditor;

import a3.C0077a;
import a3.c;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.scs.ai.gateway.AiServiceExecutor;
import com.samsung.android.sdk.scs.ai.gateway.aidl.IImageEditorService;
import com.samsung.android.visual.ai.sdkcommon.p;
import com.samsung.android.visual.ai.sdkcommon.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageEditorGateway implements IImageEditorService {
    private final AiServiceExecutor<? extends IInterface> mServiceExecutor;

    public ImageEditorGateway(AiServiceExecutor<? extends IInterface> aiServiceExecutor) {
        this.mServiceExecutor = aiServiceExecutor;
    }

    public Bundle cancel(int i2, Bundle bundle) {
        Object obj = null;
        if (this.mServiceExecutor.getService() instanceof c) {
            C0077a aVar = (C0077a) ((c) this.mServiceExecutor.getService());
            aVar.getClass();
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.samsung.android.aicore.sdkcommon.IOnDeviceService");
                obtain.writeInt(i2);
                if (bundle != null) {
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                aVar.f976a.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                Parcelable.Creator creator = Bundle.CREATOR;
                if (obtain2.readInt() != 0) {
                    obj = creator.createFromParcel(obtain2);
                }
                return (Bundle) obj;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        } else {
            p pVar = (p) ((r) this.mServiceExecutor.getService());
            pVar.getClass();
            Parcel obtain3 = Parcel.obtain();
            Parcel obtain4 = Parcel.obtain();
            try {
                obtain3.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IImageEditorService");
                obtain3.writeInt(i2);
                if (bundle != null) {
                    obtain3.writeInt(1);
                    bundle.writeToParcel(obtain3, 0);
                } else {
                    obtain3.writeInt(0);
                }
                pVar.f4190a.transact(3, obtain3, obtain4, 0);
                obtain4.readException();
                Parcelable.Creator creator2 = Bundle.CREATOR;
                if (obtain4.readInt() != 0) {
                    obj = creator2.createFromParcel(obtain4);
                }
                return (Bundle) obj;
            } finally {
                obtain4.recycle();
                obtain3.recycle();
            }
        }
    }

    public Bundle generate(int i2, Bundle bundle) {
        Object obj;
        if (this.mServiceExecutor.getService() instanceof c) {
            return ((C0077a) ((c) this.mServiceExecutor.getService())).a(i2, bundle);
        }
        p pVar = (p) ((r) this.mServiceExecutor.getService());
        pVar.getClass();
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IImageEditorService");
            obtain.writeInt(i2);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            pVar.f4190a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            Parcelable.Creator creator = Bundle.CREATOR;
            if (obtain2.readInt() != 0) {
                obj = creator.createFromParcel(obtain2);
            } else {
                obj = null;
            }
            return (Bundle) obj;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public void prepare(int i2) {
        if (this.mServiceExecutor.getService() instanceof c) {
            ((C0077a) ((c) this.mServiceExecutor.getService())).b(i2);
            return;
        }
        p pVar = (p) ((r) this.mServiceExecutor.getService());
        pVar.getClass();
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IImageEditorService");
            obtain.writeInt(i2);
            pVar.f4190a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public void release(int i2) {
        if (this.mServiceExecutor.getService() instanceof c) {
            ((C0077a) ((c) this.mServiceExecutor.getService())).c(i2);
            return;
        }
        p pVar = (p) ((r) this.mServiceExecutor.getService());
        pVar.getClass();
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.visual.ai.sdkcommon.IImageEditorService");
            obtain.writeInt(i2);
            pVar.f4190a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
