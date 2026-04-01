package com.samsung.o3dp.lib3dphotography;

import A0.l;
import Ed.b;
import android.content.Context;
import android.os.RemoteException;
import com.samsung.o3dp.lib3dphotography.MeshUtils;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DracoCompression {
    private static final String TAG = "DracoCompression";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DracoAttributeIndex {
        Primitive,
        Vertices,
        Colors,
        TexCoords,
        Indices,
        MAX
    }

    public static MeshUtils.MeshLayerInfo decode(Context context, byte[] bArr) {
        try {
            b h5 = new l().h(context, bArr);
            if (h5 == null) {
                LogUtil.e(TAG, "Failed to decode mesh");
                return null;
            }
            MeshUtils.MeshLayerInfo meshLayerInfo = new MeshUtils.MeshLayerInfo();
            meshLayerInfo.primitive = h5.b;
            meshLayerInfo.indices = (int[]) h5.f3372c;
            meshLayerInfo.vertices = (float[]) h5.d;
            meshLayerInfo.colors = (float[]) h5.e;
            meshLayerInfo.texCoords = (float[]) h5.f;
            return meshLayerInfo;
        } catch (RemoteException e) {
            LogUtil.e(TAG, "RemoteException at decode() - " + e);
            return null;
        }
    }

    public static byte[] encode(Context context, int i2, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3) {
        b bVar = new b();
        bVar.b = i2;
        bVar.f3372c = iArr;
        bVar.d = fArr;
        bVar.e = fArr2;
        bVar.f = fArr3;
        try {
            return new l().k(context, bVar);
        } catch (RemoteException e) {
            LogUtil.e(TAG, "RemoteException at encode() - " + e);
            return null;
        }
    }
}
