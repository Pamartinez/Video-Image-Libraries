package com.samsung.o3dp.lib3dphotography.mesh;

import A0.l;
import He.F;
import android.content.Context;
import android.os.RemoteException;
import android.os.SharedMemory;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.liveeffect.service.IHeifDecodingListener;
import com.samsung.android.liveeffect.service.b;
import i.C0212a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Heif3dDecoder {
    private IHeifDecodingListener createHeifDecodingListener(final CompletableFuture<HeifDecodingResult> completableFuture) {
        return new b() {
            private final ByteArrayOutputStream cameraAnimationBuffer = new ByteArrayOutputStream();
            private final ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();
            private final ByteArrayOutputStream meshBuffer = new ByteArrayOutputStream();

            {
                attachInterface(this, "com.samsung.android.liveeffect.service.IHeifDecodingListener");
            }

            public void onCameraAnimationReceived(byte[] bArr) {
                try {
                    this.cameraAnimationBuffer.write(bArr);
                } catch (IOException e) {
                    throw new Heif3dException("Failed to write camera animation buffer", e);
                }
            }

            public void onCompleted() {
                byte[] bArr;
                byte[] byteArray = this.imageBuffer.toByteArray();
                byte[] bArr2 = null;
                if (this.meshBuffer.toByteArray().length > 0) {
                    bArr = this.meshBuffer.toByteArray();
                } else {
                    bArr = null;
                }
                if (this.cameraAnimationBuffer.toByteArray().length > 0) {
                    bArr2 = this.cameraAnimationBuffer.toByteArray();
                }
                completableFuture.complete(new HeifDecodingResult(byteArray, bArr, bArr2));
            }

            public void onFailed(String str, String str2) {
                completableFuture.completeExceptionally(new Heif3dException(C0212a.n("Failed to decode image: ", str, ArcCommonLog.TAG_COMMA, str2)));
            }

            public void onImageReceived(byte[] bArr, boolean z) {
                try {
                    this.imageBuffer.write(bArr);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public void onMeshReceived(byte[] bArr, boolean z) {
                try {
                    this.meshBuffer.write(bArr);
                } catch (IOException e) {
                    throw new Heif3dException("Failed to write mesh buffer", e);
                }
            }
        };
    }

    public HeifDecodingResult decode(Context context, byte[] bArr) {
        try {
            SharedMemory B = F.B("meshEncodedHeif", bArr);
            CompletableFuture completableFuture = new CompletableFuture();
            new l().g(context, B, createHeifDecodingListener(completableFuture));
            return (HeifDecodingResult) completableFuture.get();
        } catch (RemoteException e) {
            throw new Heif3dException("Remote service error during image decoding", e);
        } catch (InterruptedException e7) {
            Thread.currentThread().interrupt();
            throw new Heif3dException("Interrupted during image decoding", e7);
        } catch (ExecutionException e8) {
            throw new Heif3dException("Execution error during image decoding", e8);
        }
    }
}
