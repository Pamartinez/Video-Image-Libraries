package he;

import com.google.protobuf.C0142n;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import ee.C0992z;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

/* renamed from: he.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1076a extends InputStream implements C0992z {
    public MessageLite d;
    public final Parser e;
    public ByteArrayInputStream f;

    public C1076a(MessageLite messageLite, Parser parser) {
        this.d = messageLite;
        this.e = parser;
    }

    public final int available() {
        MessageLite messageLite = this.d;
        if (messageLite != null) {
            return messageLite.getSerializedSize();
        }
        ByteArrayInputStream byteArrayInputStream = this.f;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.available();
        }
        return 0;
    }

    public final int read() {
        if (this.d != null) {
            this.f = new ByteArrayInputStream(this.d.toByteArray());
            this.d = null;
        }
        ByteArrayInputStream byteArrayInputStream = this.f;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read();
        }
        return -1;
    }

    public final int read(byte[] bArr, int i2, int i7) {
        MessageLite messageLite = this.d;
        if (messageLite != null) {
            int serializedSize = messageLite.getSerializedSize();
            if (serializedSize == 0) {
                this.d = null;
                this.f = null;
                return -1;
            } else if (i7 >= serializedSize) {
                Logger logger = CodedOutputStream.f1576h;
                C0142n nVar = new C0142n(bArr, i2, serializedSize);
                this.d.writeTo(nVar);
                if (nVar.u0() == 0) {
                    this.d = null;
                    this.f = null;
                    return serializedSize;
                }
                throw new IllegalStateException("Did not write as much data as expected.");
            } else {
                this.f = new ByteArrayInputStream(this.d.toByteArray());
                this.d = null;
            }
        }
        ByteArrayInputStream byteArrayInputStream = this.f;
        if (byteArrayInputStream != null) {
            return byteArrayInputStream.read(bArr, i2, i7);
        }
        return -1;
    }
}
