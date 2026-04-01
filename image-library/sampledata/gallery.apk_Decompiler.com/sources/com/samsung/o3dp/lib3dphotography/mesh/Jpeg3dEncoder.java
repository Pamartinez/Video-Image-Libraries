package com.samsung.o3dp.lib3dphotography.mesh;

import android.graphics.Bitmap;
import com.samsung.o3dp.jpeg3dcontainer.Jpeg3dTranscoder;
import com.samsung.o3dp.jpeg3dcontainer.exception.JpegParseException;
import com.samsung.o3dp.lib3dphotography.mesh.exception.MeshEncodingException;
import com.samsung.o3dp.lib3dphotography.utils.ByteUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Jpeg3dEncoder {
    private static final String TAG = "Jpeg3dEncoder";

    private InputStream convertBitmapToInputStream(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 95, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public byte[] encodeImage(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            return new Jpeg3dTranscoder().transcode(new ByteArrayInputStream(bArr), bArr2, bArr3).toBytes();
        } catch (JpegParseException e) {
            LogUtil.e(TAG, "Error during encoding", e);
            throw new MeshEncodingException("Error during encoding", e);
        }
    }

    public byte[] encodeImage(Bitmap bitmap, byte[] bArr, byte[] bArr2) {
        InputStream convertBitmapToInputStream;
        try {
            convertBitmapToInputStream = convertBitmapToInputStream(bitmap);
            byte[] encodeImage = encodeImage(ByteUtil.toBytes(convertBitmapToInputStream), bArr, bArr2);
            if (convertBitmapToInputStream != null) {
                convertBitmapToInputStream.close();
            }
            return encodeImage;
        } catch (IOException e) {
            LogUtil.e(TAG, "Error during encoding", e);
            throw new MeshEncodingException("Error during encoding", e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
