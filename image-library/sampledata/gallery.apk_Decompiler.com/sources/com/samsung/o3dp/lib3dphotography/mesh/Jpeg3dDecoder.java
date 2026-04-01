package com.samsung.o3dp.lib3dphotography.mesh;

import com.samsung.o3dp.jpeg3dcontainer.Jpeg3dParser;
import com.samsung.o3dp.jpeg3dcontainer.exception.JpegParseException;
import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.lib3dphotography.mesh.exception.MeshDecodingException;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.io.ByteArrayInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Jpeg3dDecoder {
    private static final String TAG = "Jpeg3dDecoder";

    public JpegDecodingResult decode(byte[] bArr) {
        try {
            Jpeg3d parse = new Jpeg3dParser().parse(new ByteArrayInputStream(bArr));
            return new JpegDecodingResult(parse.getImageBytes(), parse.getModel3D(), parse.getCameraAnimation());
        } catch (JpegParseException e) {
            LogUtil.e(TAG, "Failed to decode result", e);
            throw new MeshDecodingException("Failed to decode result", e);
        }
    }
}
