package de;

import android.system.Os;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface f {
    static int a(FileDescriptor fileDescriptor) {
        byte[] bArr = new byte[2];
        if (Os.read(fileDescriptor, bArr, 0, 2) == 2) {
            return D1.f.J(bArr, 0, ByteOrder.BIG_ENDIAN);
        }
        throw new EOFException();
    }

    static int b(FileDescriptor fileDescriptor) {
        byte[] bArr = new byte[4];
        if (Os.read(fileDescriptor, bArr, 0, 4) == 4) {
            return D1.f.I(bArr, 0, ByteOrder.BIG_ENDIAN);
        }
        throw new EOFException();
    }

    boolean c();
}
