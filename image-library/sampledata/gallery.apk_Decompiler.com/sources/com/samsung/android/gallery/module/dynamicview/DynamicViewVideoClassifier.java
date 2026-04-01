package com.samsung.android.gallery.module.dynamicview;

import com.samsung.srcb.unihal.ActionClipInfo;
import com.samsung.srcb.unihal.UnihalJNI;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DynamicViewVideoClassifier {
    public static List<ActionClipInfo> unpack(byte[] bArr) {
        return UnihalJNI.getActionResult(bArr);
    }
}
