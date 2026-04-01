package com.google.protobuf;

import java.io.IOException;

/* renamed from: com.google.protobuf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0130b implements Parser {
    static {
        ExtensionRegistryLite.a();
    }

    public final GeneratedMessageLite a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        GeneratedMessageLite parsePartialFrom = GeneratedMessageLite.parsePartialFrom(((C0153z) this).f1633a, codedInputStream, extensionRegistryLite);
        if (parsePartialFrom == null || parsePartialFrom.isInitialized()) {
            return parsePartialFrom;
        }
        UninitializedMessageException newUninitializedMessageException = parsePartialFrom.newUninitializedMessageException();
        newUninitializedMessageException.getClass();
        throw new IOException(newUninitializedMessageException.getMessage());
    }
}
