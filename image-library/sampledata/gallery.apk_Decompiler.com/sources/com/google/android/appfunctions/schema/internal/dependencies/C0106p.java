package com.google.android.appfunctions.schema.internal.dependencies;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0106p {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f1226a = Charset.forName("UTF-8");
    public static final byte[] b;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        b = bArr;
        ByteBuffer.wrap(bArr);
        int i2 = (0 - 0) + 0;
        if (i2 < 0) {
            throw new IOException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        } else if (i2 > Integer.MAX_VALUE) {
            try {
                throw new IOException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            } catch (r e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
