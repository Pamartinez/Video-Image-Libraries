package com.google.protobuf;

import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class F extends IOException {
    public boolean d;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.F] */
    public static F a() {
        return new IOException("Protocol message contained an invalid tag (zero).");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.F] */
    public static F b() {
        return new IOException("Protocol message had invalid UTF-8.");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.E] */
    public static E c() {
        return new IOException("Protocol message tag had invalid wire type.");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.F] */
    public static F d() {
        return new IOException("CodedInputStream encountered a malformed varint.");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.F] */
    public static F e() {
        return new IOException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.F] */
    public static F f() {
        return new IOException("Failed to parse the message.");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.IOException, com.google.protobuf.F] */
    public static F g() {
        return new IOException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
