package com.samsung.android.sdk.moneta.memory.service;

import L2.a;
import android.os.Parcel;
import android.os.SharedMemory;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.ISharedMemoryResponse;
import i.C0212a;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/samsung/android/sdk/moneta/memory/service/MemoryServiceImpl$createSharedMemoryResponse$1", "Lcom/samsung/android/sdk/moneta/memory/ISharedMemoryResponse$Stub;", "Landroid/os/SharedMemory;", "response", "Lme/x;", "onResponse", "(Landroid/os/SharedMemory;)V", "", "code", "", "message", "onError", "(ILjava/lang/String;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemoryServiceImpl$createSharedMemoryResponse$1 extends ISharedMemoryResponse.Stub {
    final /* synthetic */ String $className;
    final /* synthetic */ C1227c $continuation;
    final /* synthetic */ MemoryServiceImpl this$0;

    public MemoryServiceImpl$createSharedMemoryResponse$1(MemoryServiceImpl memoryServiceImpl, String str, C1227c cVar) {
        this.this$0 = memoryServiceImpl;
        this.$className = str;
        this.$continuation = cVar;
    }

    public void onError(int i2, String str) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("Memory-MemoryServiceImpl", C0212a.k(i2, "[createSharedMemoryResponse-onError] in error code: ", ", message: ", str));
        this.$continuation.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
    }

    /* JADX INFO: finally extract failed */
    public void onResponse(SharedMemory sharedMemory) {
        Integer num;
        Logger logger = Logger.INSTANCE;
        StringBuilder sb2 = new StringBuilder("[createSharedMemoryResponse-onResponse] in response size : ");
        if (sharedMemory != null) {
            num = Integer.valueOf(sharedMemory.getSize());
        } else {
            num = null;
        }
        sb2.append(num);
        logger.i$pde_sdk_1_0_40_release("Memory-MemoryServiceImpl", sb2.toString());
        Parcel obtain = Parcel.obtain();
        j.d(obtain, "obtain(...)");
        if (sharedMemory != null) {
            try {
                ByteBuffer mapReadOnly = sharedMemory.mapReadOnly();
                j.d(mapReadOnly, "mapReadOnly(...)");
                int remaining = mapReadOnly.remaining();
                byte[] bArr = new byte[remaining];
                mapReadOnly.get(bArr);
                obtain.unmarshall(bArr, 0, remaining);
                obtain.setDataPosition(0);
                this.$continuation.resumeWith(this.this$0.getDataList(this.$className, obtain));
                obtain.recycle();
                sharedMemory.close();
            } catch (Throwable th) {
                obtain.recycle();
                if (sharedMemory != null) {
                    sharedMemory.close();
                }
                throw th;
            }
        } else {
            throw new Exception("RemoteException");
        }
    }
}
