package com.samsung.scsp.framework.core.network.visitor;

import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PayloadWriterVisitor<T> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Payload<T> {
        public Object content;
        public String contentType;
        public HttpRequest httpRequest;
        public long length;
        public T output;
        public Network.StreamListener streamListener;
        public long transferred = 0;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class PayloadWriter {
        public abstract void accept(Payload payload, PayloadWriterVisitor payloadWriterVisitor);
    }

    void visit(Payload<T> payload, FileInputStreamPayloadWriter fileInputStreamPayloadWriter);

    void visit(Payload<T> payload, FilePayloadWriter filePayloadWriter);

    void visit(Payload<T> payload, StringPayloadWriter stringPayloadWriter);
}
