package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MessageLite extends V {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Builder extends V, Cloneable {
        MessageLite buildPartial();
    }

    Parser getParserForType();

    int getSerializedSize();

    Builder newBuilderForType();

    byte[] toByteArray();

    void writeTo(CodedOutputStream codedOutputStream);
}
