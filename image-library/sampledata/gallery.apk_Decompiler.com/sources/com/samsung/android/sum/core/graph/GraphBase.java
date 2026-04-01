package com.samsung.android.sum.core.graph;

import android.hardware.HardwareBuffer;
import android.os.Parcelable;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.BufferExtension;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageChannelRouter;
import com.samsung.android.sum.core.message.MessagePublisher;
import com.samsung.android.sum.core.message.MessageSubscriber;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GraphBase<T> implements Graph<T> {
    private static final String TAG = Def.tagOf((Class<?>) GraphBase.class);
    protected CountingLatch countingLatch = CountingLatch.downOf();
    protected BufferChannel inputChannel;
    protected final MessageChannelRouter messageChannelRouter;
    protected MessagePublisher messagePublisher;
    protected final List<GraphNode<T>> nodes;
    protected final Graph.Option option;
    protected final ConcurrentHashMap<Integer, MediaBuffer> outBufferMap = new ConcurrentHashMap<>();
    protected List<MediaBuffer> outBuffers;
    protected BufferChannel outputChannel;

    public GraphBase(List<GraphNode<T>> list, Graph.Option option2) {
        MessageChannelRouter messageChannelRouter2 = new MessageChannelRouter(32);
        this.messageChannelRouter = messageChannelRouter2;
        this.messagePublisher = messageChannelRouter2.newMessagePublisher();
        this.nodes = list;
        this.option = option2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0090, code lost:
        if (r1 != null) goto L_0x007a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0095 A[SYNTHETIC, Splitter:B:27:0x0095] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.sum.core.buffer.MediaBuffer handleOutputBuffer(com.samsung.android.sum.core.buffer.MediaBuffer r4) {
        /*
            r3 = this;
            com.samsung.android.sum.core.format.MediaFormat r0 = r4.getFormat()
            com.samsung.android.sum.core.types.MediaType r0 = r0.getMediaType()
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.samsung.android.sum.core.buffer.MediaBuffer> r3 = r3.outBufferMap
            java.lang.String r1 = "media-id"
            java.lang.Object r1 = r4.getExtra(r1)
            java.lang.Object r3 = r3.remove(r1)
            com.samsung.android.sum.core.buffer.MediaBuffer r3 = (com.samsung.android.sum.core.buffer.MediaBuffer) r3
            com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.SCALA
            if (r0 == r1) goto L_0x00a6
            com.samsung.android.sum.core.types.MediaType r1 = com.samsung.android.sum.core.types.MediaType.META
            if (r0 == r1) goto L_0x00a6
            if (r3 == 0) goto L_0x00a6
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onReceiveOutputBuffer: "
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r2 = " => "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r1)
            com.samsung.android.sum.core.functional.Operator r0 = com.samsung.android.sum.solution.filter.UniImgp.ofUnified()
            com.samsung.android.sum.core.buffer.MutableMediaBuffer r1 = r3.toMutable()
            r0.run((com.samsung.android.sum.core.buffer.MediaBuffer) r4, (com.samsung.android.sum.core.buffer.MutableMediaBuffer) r1)
            java.util.Map r4 = r4.getExtra()
            r3.setExtra(r4)
            com.samsung.android.sum.core.format.MediaFormat r4 = r3.getFormat()
            com.samsung.android.sum.core.types.MediaType r4 = r4.getMediaType()
            com.samsung.android.sum.core.types.MediaType r0 = com.samsung.android.sum.core.types.MediaType.COMPRESSED_IMAGE
            if (r4 != r0) goto L_0x009e
            r4 = 0
            java.lang.String r0 = "file-descriptor"
            java.lang.Object r0 = r3.getExtra(r0)     // Catch:{ Exception -> 0x008a, all -> 0x0088 }
            android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0     // Catch:{ Exception -> 0x008a, all -> 0x0088 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x008a, all -> 0x0088 }
            java.io.FileDescriptor r0 = r0.getFileDescriptor()     // Catch:{ Exception -> 0x008a, all -> 0x0088 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x008a, all -> 0x0088 }
            java.lang.Class<android.graphics.Bitmap> r4 = android.graphics.Bitmap.class
            java.lang.Object r4 = r3.getTypedData(r4)     // Catch:{ Exception -> 0x0086 }
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4     // Catch:{ Exception -> 0x0086 }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0086 }
            r2 = 95
            r4.compress(r0, r2, r1)     // Catch:{ Exception -> 0x0086 }
        L_0x007a:
            r1.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x009e
        L_0x007e:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x009e
        L_0x0083:
            r3 = move-exception
            r4 = r1
            goto L_0x0093
        L_0x0086:
            r4 = move-exception
            goto L_0x008d
        L_0x0088:
            r3 = move-exception
            goto L_0x0093
        L_0x008a:
            r0 = move-exception
            r1 = r4
            r4 = r0
        L_0x008d:
            r4.printStackTrace()     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x009e
            goto L_0x007a
        L_0x0093:
            if (r4 == 0) goto L_0x009d
            r4.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x009d
        L_0x0099:
            r4 = move-exception
            r4.printStackTrace()
        L_0x009d:
            throw r3
        L_0x009e:
            java.lang.String r4 = "freezed"
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r3.setExtra(r4, r0)
            return r3
        L_0x00a6:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.graph.GraphBase.handleOutputBuffer(com.samsung.android.sum.core.buffer.MediaBuffer):com.samsung.android.sum.core.buffer.MediaBuffer");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runBatch$1(MediaBuffer mediaBuffer) {
        mediaBuffer.dup();
        this.inputChannel.send(mediaBuffer);
        publishEvent(109, mediaBuffer);
        mediaBuffer.release();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runOneWay$0(MediaBuffer mediaBuffer) {
        this.inputChannel.send(mediaBuffer);
        publishEvent(109, mediaBuffer);
    }

    private void onCanceled(int i2) {
        String str = TAG;
        SLog.i(str, "onCanceled: " + i2);
        this.messagePublisher.sendMessage(Event.of(i2));
    }

    private void publishEvent(int i2, MediaBuffer mediaBuffer) {
        long j2;
        String str = TAG;
        SLog.d(str, "publishEvent E: code=" + i2 + ", buffer=" + mediaBuffer);
        if (this.messagePublisher != null) {
            Event of2 = Event.of(i2);
            of2.setPublisher(this.messagePublisher);
            if (i2 == 109) {
                of2.put("media-id", mediaBuffer.getExtra("media-id"));
                if (mediaBuffer.containsExtra(Message.KEY_IN_FILE)) {
                    of2.put(Message.KEY_IN_FILE, mediaBuffer.getExtra(Message.KEY_IN_FILE));
                }
                of2.put(Message.KEY_START_TIME_MS, Long.valueOf(System.currentTimeMillis()));
            } else if (i2 == 110) {
                of2.put("media-id", mediaBuffer.getExtra("media-id"));
                if (mediaBuffer.containsExtra(Message.KEY_IN_FILE)) {
                    of2.put(Message.KEY_IN_FILE, mediaBuffer.getExtra(Message.KEY_IN_FILE));
                }
                of2.put("width", Integer.valueOf(mediaBuffer.getCols()));
                of2.put("height", Integer.valueOf(mediaBuffer.getRows()));
                of2.put(Message.KEY_END_TIME_MS, Long.valueOf(System.currentTimeMillis()));
                Stream.of(new String[]{Message.KEY_ROTATION, "last-video-timestamp-us", "last-audio-timestamp-us"}).filter(new b(0, mediaBuffer)).forEach(new e(8, of2, mediaBuffer));
                if (mediaBuffer.getFormat().getMediaType().isVideo()) {
                    long longValue = ((Long) Optional.ofNullable(of2.get("last-video-timestamp-us")).orElse(-1L)).longValue();
                    long longValue2 = ((Long) Optional.ofNullable(of2.get("last-audio-timestamp-us")).orElse(-1L)).longValue();
                    if (longValue > longValue2) {
                        j2 = (longValue / 1000) + 1;
                    } else {
                        j2 = (longValue2 / 1000) + 1;
                    }
                    of2.put("duration", Long.valueOf(j2));
                }
                if (this.option.isOutputOnEventCallback() && ((mediaBuffer.getData() instanceof Parcelable) || BufferExtension.isSupportedTransform(mediaBuffer.getDataClass(), HardwareBuffer.class))) {
                    SLog.d(str, "set output buffer to event cb");
                    of2.setBundledDataHandler(new c(mediaBuffer));
                }
            }
            of2.post();
            SLog.d(str, "publishEvent X: code=" + i2);
        }
    }

    public MessagePublisher getMessagePublisher() {
        return this.messagePublisher;
    }

    public <V> V getOption(int i2) {
        return this.option.get(i2);
    }

    public void onReceiveOutputBuffer(MediaBuffer mediaBuffer) {
        String str = TAG;
        SLog.v(str, "onReceiveOutputBuffer: " + mediaBuffer);
        MediaBuffer handleOutputBuffer = handleOutputBuffer(mediaBuffer);
        if (!this.option.isOutputOnEventCallback()) {
            this.outBuffers.add(handleOutputBuffer);
        }
        publishEvent(110, handleOutputBuffer);
        this.countingLatch.down();
    }

    public void quitNow() {
        String str = TAG;
        SLog.d(str, "quitNow...E");
        this.countingLatch.reset();
        this.inputChannel.cancel();
        this.outputChannel.cancel();
        this.nodes.forEach(new a(0));
        this.option.clear();
        SLog.d(str, "quitNow...X");
    }

    public void quitSafely() {
        String str = TAG;
        SLog.d(str, "quitSafely...E");
        this.inputChannel.cancel();
        this.outputChannel.cancel();
        this.nodes.forEach(new a(0));
        this.option.clear();
        SLog.d(str, "quitSafely...X");
    }

    @Deprecated
    public void release() {
        String str = TAG;
        SLog.d(str, "release...E");
        quitSafely();
        SLog.d(str, "release...X");
    }

    public void runBatch(List<MediaBuffer> list) {
        try {
            list.forEach(new d(this, 0));
            this.countingLatch.up(list.size());
            this.countingLatch.await(0);
        } catch (Exception e) {
            onCanceled(e);
        }
    }

    public void runOneByOne(List<MediaBuffer> list) {
        try {
            for (MediaBuffer next : list) {
                this.inputChannel.send(next);
                this.countingLatch.up();
                publishEvent(109, next);
                long runTimeout = this.option.getRunTimeout(0);
                if (runTimeout != 0) {
                    this.countingLatch.await(0, runTimeout);
                } else {
                    this.countingLatch.await(0);
                }
            }
        } catch (Exception e) {
            onCanceled(e);
        }
    }

    public void runOneWay(List<MediaBuffer> list) {
        list.forEach(new d(this, 1));
    }

    public void setMessageSubscriber(MessageSubscriber messageSubscriber) {
        this.messageChannelRouter.addMessageSubscriber(messageSubscriber);
    }

    public <V> V getOption(int i2, V v) {
        return this.option.get(i2, v);
    }

    private void onCanceled(Exception exc) {
        String str = TAG;
        SLog.i(str, "onCanceled: " + exc);
        this.messagePublisher.sendMessage(Event.of(-2, exc));
    }

    private void onCanceled(Message message) {
        String str = TAG;
        SLog.i(str, "onCanceled: " + message);
        this.messagePublisher.sendMessage(message);
    }
}
