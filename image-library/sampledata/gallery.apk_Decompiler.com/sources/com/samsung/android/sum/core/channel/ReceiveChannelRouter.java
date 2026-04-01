package com.samsung.android.sum.core.channel;

import Gb.b;
import Tc.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.evaluate.Evaluator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReceiveChannelRouter extends ChannelRouterBase {
    private static final String TAG = Def.tagOf((Class<?>) ReceiveChannelRouter.class);
    private Supplier<MediaBuffer> receiveOp;
    private final Type receiveType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        ANY,
        ALL
    }

    public ReceiveChannelRouter(List<BufferChannel> list, Type type) {
        super(list);
        this.receiveType = type;
        init();
    }

    private void init() {
        if (!this.evChannelMap.isEmpty()) {
            this.channels.addAll(new ArrayList(this.evChannelMap.values()));
        }
        if (this.receiveType == Type.ANY) {
            this.receiveOp = new i(this, 0);
            return;
        }
        this.evChannelMap.clear();
        this.receiveOp = new i(this, 1);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$receiveAll$0(List list, int i2) {
        if (((MediaBuffer) list.get(i2)).containsExtra("primary") || !((MediaBuffer) list.get(i2)).getFormat().getMediaType().isMetaData()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$receiveAny$1(Integer num, BlockingQueue blockingQueue) {
        try {
            MediaBuffer mediaBuffer = (MediaBuffer) this.channels.get(num.intValue()).receive();
            blockingQueue.put(num);
            return mediaBuffer;
        } catch (Exception e) {
            String str = TAG;
            SLog.d(str, "buffer-channel receive thread is interrupted: " + e.getMessage());
            blockingQueue.put(num);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Future lambda$receiveAny$2(ExecutorService executorService, BlockingQueue blockingQueue, Integer num) {
        return executorService.submit(new a((Object) this, (Object) num, (Object) blockingQueue, 5));
    }

    /* access modifiers changed from: private */
    public MediaBuffer receiveAll() {
        List list = (List) ((Stream) this.channels.stream().parallel()).map(new b(2)).collect(Collectors.toList());
        return MediaBuffer.newGroupAlloc().setBuffers(IntStream.range(0, list.size()).filter(new b(1, list)).findFirst().orElse(0), (List<MediaBuffer>) list).allocate();
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [java.util.function.Consumer, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.util.function.Consumer, java.lang.Object] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00b5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00c6, code lost:
        throw new java.util.concurrent.CancellationException("buffer-channels receive thread are interrupted");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c7, code lost:
        r6.values().forEach(new java.lang.Object());
        r1.shutdownNow();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d6, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x00bf */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.sum.core.buffer.MediaBuffer receiveAny() {
        /*
            r6 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "anyReceived: # of channel="
            r1.<init>(r2)
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r2 = r6.channels
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r1)
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r0 = r6.channels
            boolean r0 = r0.isEmpty()
            r1 = 1
            r0 = r0 ^ r1
            com.samsung.android.sum.core.Def.require(r0)
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r0 = r6.channels
            int r0 = r0.size()
            if (r0 != r1) goto L_0x004d
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r6 = r6.channels
            java.util.stream.Stream r6 = r6.stream()
            java.util.Optional r6 = r6.findFirst()
            com.samsung.android.sum.core.channel.b r0 = new com.samsung.android.sum.core.channel.b
            r1 = 2
            r0.<init>(r1)
            java.util.Optional r6 = r6.map(r0)
            com.samsung.android.sum.core.buffer.u r0 = new com.samsung.android.sum.core.buffer.u
            r1 = 4
            r0.<init>(r1)
            java.lang.Object r6 = r6.orElseThrow(r0)
            com.samsung.android.sum.core.buffer.MediaBuffer r6 = (com.samsung.android.sum.core.buffer.MediaBuffer) r6
            return r6
        L_0x004d:
            java.util.concurrent.LinkedBlockingQueue r0 = new java.util.concurrent.LinkedBlockingQueue
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r1 = r6.channels
            int r1 = r1.size()
            r0.<init>(r1)
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r1 = r6.channels
            int r1 = r1.size()
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newFixedThreadPool(r1)
            java.util.List<com.samsung.android.sum.core.channel.BufferChannel> r2 = r6.channels
            int r2 = r2.size()
            r3 = 0
            java.util.stream.IntStream r2 = java.util.stream.IntStream.range(r3, r2)
            java.util.stream.Stream r2 = r2.boxed()
            java.util.function.Function r3 = java.util.function.Function.identity()
            F9.d r4 = new F9.d
            r5 = 3
            r4.<init>(r6, r1, r0, r5)
            java.util.stream.Collector r6 = java.util.stream.Collectors.toMap(r3, r4)
            java.lang.Object r6 = r2.collect(r6)
            java.util.Map r6 = (java.util.Map) r6
        L_0x0085:
            boolean r2 = r6.isEmpty()     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            if (r2 != 0) goto L_0x00b7
            java.lang.Object r2 = r0.take()     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            r2.getClass()     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            java.lang.Object r2 = r6.remove(r2)     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            java.util.concurrent.Future r2 = (java.util.concurrent.Future) r2     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            java.util.Objects.requireNonNull(r2)     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            java.lang.Object r2 = r2.get()     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            com.samsung.android.sum.core.buffer.MediaBuffer r2 = (com.samsung.android.sum.core.buffer.MediaBuffer) r2     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            if (r2 == 0) goto L_0x0085
            java.util.Collection r6 = r6.values()
            com.samsung.android.sum.core.channel.j r0 = new com.samsung.android.sum.core.channel.j
            r0.<init>()
            r6.forEach(r0)
            r1.shutdownNow()
            return r2
        L_0x00b5:
            r0 = move-exception
            goto L_0x00c7
        L_0x00b7:
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            java.lang.String r2 = "all buffer-channels are canceled"
            r0.<init>(r2)     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
            throw r0     // Catch:{ InterruptedException | ExecutionException -> 0x00bf }
        L_0x00bf:
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "buffer-channels receive thread are interrupted"
            r0.<init>(r2)     // Catch:{ all -> 0x00b5 }
            throw r0     // Catch:{ all -> 0x00b5 }
        L_0x00c7:
            java.util.Collection r6 = r6.values()
            com.samsung.android.sum.core.channel.j r2 = new com.samsung.android.sum.core.channel.j
            r2.<init>()
            r6.forEach(r2)
            r1.shutdownNow()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.channel.ReceiveChannelRouter.receiveAny():com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    public boolean isClosedForReceive() {
        return false;
    }

    public boolean isClosedForSend() {
        return false;
    }

    public MediaBuffer receive() {
        return this.receiveOp.get();
    }

    public void send(MediaBuffer mediaBuffer) {
        throw new UnsupportedOperationException();
    }

    public ReceiveChannelRouter(Map<Evaluator, BufferChannel> map, Type type) {
        super(map);
        this.receiveType = type;
        init();
    }

    public void cancel() {
    }

    public void close() {
    }
}
