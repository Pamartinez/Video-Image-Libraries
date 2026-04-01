package com.samsung.o3dp.jpeg3dcontainer.util;

import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.XMPMetaFactory;
import com.adobe.internal.xmp.options.ParseOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class XmpUtil {
    public static XMPMeta parse(byte[] bArr) {
        return XMPMetaFactory.parseFromBuffer(bArr, new ParseOptions());
    }
}
