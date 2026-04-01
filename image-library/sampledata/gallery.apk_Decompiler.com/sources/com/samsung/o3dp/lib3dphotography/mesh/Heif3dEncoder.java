package com.samsung.o3dp.lib3dphotography.mesh;

import A0.l;
import Dd.C0730a;
import He.F;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.system.ErrnoException;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Heif3dEncoder {
    private static final String TAG = "Heif3dEncoder";

    public byte[] encodeImage(byte[] bArr, byte[] bArr2, byte[] bArr3, Context context) {
        SharedMemory B;
        l lVar = new l();
        try {
            B = F.B("model3d", bArr2);
            SharedMemory B9 = F.B("heif", bArr);
            try {
                ByteBuffer mapReadOnly = lVar.j(context, B9, B, bArr3).mapReadOnly();
                byte[] bArr4 = new byte[mapReadOnly.remaining()];
                mapReadOnly.get(bArr4);
                SharedMemory.unmap(mapReadOnly);
                B9.close();
                B.close();
                return bArr4;
            } catch (ErrnoException e) {
                throw new C0730a("Failed to map shared memory for read only", (Exception) e);
            } catch (Throwable th) {
                B9.close();
                throw th;
            }
        } catch (RemoteException e7) {
            throw new Heif3dException("Error during encoding", e7);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public byte[] encodeImage(Bitmap bitmap, byte[] bArr, byte[] bArr2, Context context) {
        l lVar = new l();
        try {
            SharedMemory B = F.B("model3d", bArr);
            try {
                ByteBuffer mapReadOnly = lVar.i(context, bitmap, B, bArr2).mapReadOnly();
                byte[] bArr3 = new byte[mapReadOnly.remaining()];
                mapReadOnly.get(bArr3);
                SharedMemory.unmap(mapReadOnly);
                B.close();
                return bArr3;
            } catch (ErrnoException e) {
                throw new C0730a("Failed to map shared memory for read only", (Exception) e);
            } catch (Throwable th) {
                B.close();
                throw th;
            }
        } catch (RemoteException e7) {
            throw new Heif3dException("Error during encoding", e7);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }
}
