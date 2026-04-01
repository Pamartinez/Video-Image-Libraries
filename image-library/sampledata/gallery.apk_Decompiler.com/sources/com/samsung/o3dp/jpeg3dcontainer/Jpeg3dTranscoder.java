package com.samsung.o3dp.jpeg3dcontainer;

import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.jpeg3dcontainer.util.ByteUtil;
import com.samsung.o3dp.jpeg3dcontainer.util.LogUtil;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Jpeg3dTranscoder {
    private static final String TAG = "Jpeg3dTranscoder";

    private byte[] createMeshBytes(byte[] bArr, byte[] bArr2) {
        LogUtil.d(TAG, "Create mesh bytes");
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public Jpeg3d transcode(InputStream inputStream, byte[] bArr, byte[] bArr2) {
        LogUtil.i(TAG, "Start to encode jpeg3d");
        Jpeg3d parse = new Jpeg3dParser().parse(inputStream);
        int length = parse.getLength();
        if (parse.hasModel3D()) {
            parse.removeModel3D();
        }
        if (parse.hasCameraAnimation()) {
            parse.removeCameraAnimation();
        }
        parse.prepareGContainer(bArr, bArr2);
        int length2 = parse.getLength() - length;
        if (parse.hasMpf()) {
            LogUtil.i(TAG, "Given jpeg has MPF. Start the mpf update");
            parse.updateMpf(length2);
        }
        byte[] bytesAfterEoi = parse.getBytesAfterEoi();
        byte[] createMeshBytes = createMeshBytes(bArr, bArr2);
        if (bytesAfterEoi != null) {
            createMeshBytes = ByteUtil.concat(bytesAfterEoi, createMeshBytes);
        }
        return new Jpeg3d(parse.getSegments(), createMeshBytes);
    }
}
