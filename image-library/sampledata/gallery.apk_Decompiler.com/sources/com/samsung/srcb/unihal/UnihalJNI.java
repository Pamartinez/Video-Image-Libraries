package com.samsung.srcb.unihal;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnihalJNI {
    static {
        try {
            System.loadLibrary("VideoClassifier.camera.samsung");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static native List<ActionClipInfo> getActionResult(byte[] bArr);
}
