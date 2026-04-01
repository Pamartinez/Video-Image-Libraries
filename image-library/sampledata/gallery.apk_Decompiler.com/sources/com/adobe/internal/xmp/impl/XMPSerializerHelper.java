package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPSerializerHelper {
    public static void serialize(XMPMetaImpl xMPMetaImpl, OutputStream outputStream, SerializeOptions serializeOptions) {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        if (serializeOptions.getSort()) {
            xMPMetaImpl.sort();
        }
        new XMPSerializerRDF().serialize(xMPMetaImpl, outputStream, serializeOptions);
    }

    public static byte[] serializeToBuffer(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        serialize(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        return byteArrayOutputStream.toByteArray();
    }

    public static String serializeToString(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        serialize(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        try {
            return byteArrayOutputStream.toString(serializeOptions.getEncoding());
        } catch (UnsupportedEncodingException unused) {
            return byteArrayOutputStream.toString();
        }
    }
}
