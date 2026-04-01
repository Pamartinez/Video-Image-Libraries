package com.samsung.android.sum.core.channel;

import A4.I;
import Ad.C0720a;
import J5.c;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MediaBufferReader;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.functional.BufferSupplier;
import com.samsung.android.sum.core.graph.GraphEdge;
import com.samsung.android.sum.core.types.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SendChannelRouter extends ChannelRouterBase {
    private static final String TAG = Def.tagOf((Class<?>) SendChannelRouter.class);
    private Consumer<MediaBuffer> sendOp;
    private final Type sendType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        ANY,
        ALL,
        EVALUATE_ONLY,
        BROADCAST_ONLY
    }

    public SendChannelRouter(List<BufferChannel> list) {
        super(list);
        this.sendType = Type.BROADCAST_ONLY;
        init();
    }

    /* access modifiers changed from: private */
    public boolean broadcast(MediaBuffer mediaBuffer) {
        MediaBuffer mediaBuffer2;
        if (this.channels.size() <= 1 || !mediaBuffer.containsExtra("media-buffer-owner")) {
            mediaBuffer2 = null;
        } else {
            mediaBuffer2 = mediaBuffer.copyTo(mediaBuffer.getDataClass());
        }
        this.channels.forEach(new e(2, mediaBuffer, mediaBuffer2));
        return !this.channels.isEmpty();
    }

    /* access modifiers changed from: private */
    public boolean evaluate(MediaBuffer mediaBuffer) {
        return this.evChannelMap.entrySet().stream().anyMatch(new I(21, mediaBuffer));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$broadcast$1(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2, BufferChannel bufferChannel) {
        if (!mediaBuffer.containsExtra("media-buffer-owner")) {
            bufferChannel.send((MediaBuffer) mediaBuffer.dup());
        } else if (((Integer) mediaBuffer.getExtra("media-buffer-owner", -1)).intValue() != bufferChannel.hashCode()) {
            bufferChannel.send((MediaBuffer) mediaBuffer2.dup());
        } else {
            bufferChannel.send((MediaBuffer) mediaBuffer.dup());
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$evaluate$2(MediaBuffer mediaBuffer) {
        if (mediaBuffer.getFormat().getMediaType() == MediaType.SCALA) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$evaluate$4(MediaBuffer mediaBuffer, Map.Entry entry) {
        MediaBuffer mediaBuffer2;
        Evaluator evaluator = (Evaluator) entry.getKey();
        BufferChannel bufferChannel = (BufferChannel) entry.getValue();
        boolean z = mediaBuffer instanceof MediaBufferGroup;
        if (z) {
            mediaBuffer2 = mediaBuffer.stream().filter(new a(2)).findFirst().orElseGet(new c(14, (MediaBufferGroup) mediaBuffer));
        } else {
            mediaBuffer2 = mediaBuffer;
        }
        MediaBufferReader<V> of2 = MediaBufferReader.of(mediaBuffer2, evaluator.getValueType());
        if (!evaluator.evaluate(of2.get())) {
            return false;
        }
        if (z && mediaBuffer.containFlags(2)) {
            MediaBuffer primaryBuffer = ((MediaBufferGroup) mediaBuffer).getPrimaryBuffer();
            primaryBuffer.setExtra("evaluate-value", of2.get());
            primaryBuffer.addExtra(mediaBuffer.getExtra());
            mediaBuffer = primaryBuffer;
        }
        bufferChannel.send((MediaBuffer) mediaBuffer.dup());
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getBuffer$7(BufferChannel bufferChannel) {
        return bufferChannel instanceof BufferSupplier;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getBuffer$8(BufferChannel bufferChannel) {
        return bufferChannel instanceof BufferSupplier;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isBufferSupplier$5(BufferChannel bufferChannel) {
        return bufferChannel instanceof BufferSupplier;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isBufferSupplier$6(BufferChannel bufferChannel) {
        return bufferChannel instanceof BufferSupplier;
    }

    /* access modifiers changed from: private */
    public boolean sendAll(MediaBuffer mediaBuffer) {
        if (evaluate(mediaBuffer) || broadcast(mediaBuffer)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean sendAny(MediaBuffer mediaBuffer) {
        if (evaluate(mediaBuffer)) {
            return true;
        }
        return broadcast(mediaBuffer);
    }

    public MediaBuffer getBuffer(MediaType mediaType) {
        BufferSupplier bufferSupplier;
        if (isBufferSupplier(mediaType)) {
            if (mediaType == null) {
                bufferSupplier = (BufferSupplier) this.channels.stream().filter(new a(3)).findFirst().get();
            } else {
                bufferSupplier = (BufferSupplier) this.evChannelMap.values().stream().filter(new a(4)).findFirst().get();
            }
            MediaBuffer buffer = bufferSupplier.getBuffer();
            buffer.setExtra("media-buffer-owner", Integer.valueOf(bufferSupplier.hashCode()));
            return buffer;
        }
        throw new IllegalStateException("no buffer-supplier channel exists for type=" + mediaType);
    }

    public void init() {
        Type type = this.sendType;
        if (type == Type.EVALUATE_ONLY) {
            this.sendOp = new k(this, 0);
        } else if (type == Type.BROADCAST_ONLY) {
            this.sendOp = new k(this, 1);
        } else if (type == Type.ANY) {
            this.sendOp = new k(this, 2);
        } else {
            this.sendOp = new k(this, 3);
        }
    }

    public boolean isBufferSupplier(MediaType mediaType) {
        if (mediaType == null) {
            return this.channels.stream().anyMatch(new a(5));
        }
        return this.evChannelMap.values().stream().anyMatch(new a(1));
    }

    public boolean isClosedForReceive() {
        return false;
    }

    public boolean isClosedForSend() {
        return false;
    }

    public MediaBuffer receive() {
        throw new UnsupportedOperationException();
    }

    public void send(MediaBuffer mediaBuffer) {
        this.sendOp.accept(mediaBuffer);
    }

    public SendChannelRouter(BufferChannel... bufferChannelArr) {
        super((List<BufferChannel>) (List) Arrays.stream(bufferChannelArr).collect(Collectors.toList()));
        this.sendType = Type.BROADCAST_ONLY;
        init();
    }

    public SendChannelRouter(Map<Evaluator, BufferChannel> map, Type type) {
        super(map);
        this.sendType = type;
        init();
    }

    public SendChannelRouter(Map<Evaluator, BufferChannel> map) {
        this(map, Type.ALL);
    }

    public SendChannelRouter(GraphEdge[] graphEdgeArr, Type type) {
        this((Map<Evaluator, BufferChannel>) (Map) Arrays.stream(graphEdgeArr).collect(Collectors.toMap(new b(8), new b(9), new c(1), new C0720a(10))), type);
    }

    public SendChannelRouter(GraphEdge[] graphEdgeArr) {
        this(graphEdgeArr, Type.ALL);
    }

    public void cancel() {
    }

    public void close() {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$new$0(BufferChannel bufferChannel, BufferChannel bufferChannel2) {
        return bufferChannel;
    }
}
