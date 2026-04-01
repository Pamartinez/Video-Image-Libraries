package com.samsung.o3dp.lib3dphotography.mesh;

import com.samsung.o3dp.jpeg3dcontainer.Jpeg3dParser;
import com.samsung.o3dp.jpeg3dcontainer.exception.JpegParseException;
import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.lib3dphotography.mesh.exception.MeshRemovingException;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.io.ByteArrayInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Jpeg3dRemover {
    private static final String TAG = "Jpeg3dRemover";

    public byte[] remove(byte[] bArr) {
        try {
            Jpeg3d parse = new Jpeg3dParser().parse(new ByteArrayInputStream(bArr));
            if (parse.hasModel3D()) {
                parse.removeModel3D();
            }
            if (parse.hasCameraAnimation()) {
                parse.removeCameraAnimation();
            }
            return parse.toBytes();
        } catch (JpegParseException e) {
            LogUtil.e(TAG, "Failed to remove jpeg3d metadata", e);
            throw new MeshRemovingException("Failed to remove jpeg3d meatadata", e);
        }
    }
}
