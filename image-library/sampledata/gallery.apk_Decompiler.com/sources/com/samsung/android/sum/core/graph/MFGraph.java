package com.samsung.android.sum.core.graph;

import Gb.c;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.util.Pair;
import com.samsung.android.gallery.support.utils.K;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.cache.DiskCache;
import com.samsung.android.sum.core.cache.KeyGenerator;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.BufferChannelDescriptor;
import com.samsung.android.sum.core.channel.BufferSupplyChannel;
import com.samsung.android.sum.core.channel.ReceiveChannelRouter;
import com.samsung.android.sum.core.channel.SendChannelRouter;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.functional.BufferChannelProvider;
import com.samsung.android.sum.core.functional.BufferSupplier;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MFGraph extends GraphBase<MediaFilter> {
    private static final String TAG = Def.tagOf((Class<?>) MFGraph.class);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder extends GraphBuilderBase<MediaFilter> {
        /* access modifiers changed from: private */
        public final BufferChannelProvider bufferChannelProvider;
        /* access modifiers changed from: private */
        public final Graph.Option option;
        private Consumer<MediaBuffer> outputBufferHandler;
        /* access modifiers changed from: private */
        public BiConsumer<BufferChannel, Consumer<MediaBuffer>> outputChannelHandler;

        public Builder() {
            this(new Graph.Option());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ BufferChannel lambda$new$0(BufferChannelDescriptor bufferChannelDescriptor) {
            BufferChannel of2 = BufferChannel.of(bufferChannelDescriptor.getType());
            of2.setCapacity(bufferChannelDescriptor.getCapacity());
            return of2;
        }

        public GraphBuilder<MediaFilter> addNode(GraphNode<? extends MediaFilter> graphNode, GraphNode<? extends MediaFilter> graphNode2) {
            GraphEdge graphEdge = new GraphEdge(this.bufferChannelProvider.getBufferChannel(new BufferChannelDescriptor(0, Integer.MAX_VALUE)));
            graphNode.addOutputEdge(graphEdge);
            graphNode2.addInputEdge(graphEdge);
            this.graphNodes.add(graphNode);
            this.graphNodes.add(graphNode2);
            return this;
        }

        public Graph<MediaFilter> build() {
            return new MFGraph(this);
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.samsung.android.sum.core.functional.BufferChannelProvider, java.lang.Object] */
        public Builder(Graph.Option option2) {
            this.bufferChannelProvider = new Object();
            this.option = option2;
        }

        public Builder(MFGraphUnitFactory mFGraphUnitFactory) {
            this(mFGraphUnitFactory, new Graph.Option());
        }

        public Builder(MFGraphUnitFactory mFGraphUnitFactory, Graph.Option option2) {
            Objects.requireNonNull(mFGraphUnitFactory);
            this.bufferChannelProvider = new c(mFGraphUnitFactory);
            this.option = option2;
            if (option2.isCacheable() && option2.get(1) == null) {
                option2.set(1, mFGraphUnitFactory.newDiskCache());
            }
            this.outputChannelHandler = new u(mFGraphUnitFactory);
        }

        public GraphBuilder<MediaFilter> addNode(GraphNode<? extends MediaFilter> graphNode, GraphNode<? extends MediaFilter> graphNode2, Evaluator evaluator) {
            return addNode(graphNode, graphNode2, evaluator, new BufferChannelDescriptor());
        }

        public GraphBuilder<MediaFilter> addNode(GraphNode<? extends MediaFilter> graphNode, GraphNode<? extends MediaFilter> graphNode2, BufferChannelDescriptor bufferChannelDescriptor) {
            return addNode(graphNode, graphNode2, (Evaluator) null, bufferChannelDescriptor);
        }

        public GraphBuilder<MediaFilter> addNode(GraphNode<? extends MediaFilter> graphNode, GraphNode<? extends MediaFilter> graphNode2, Evaluator evaluator, BufferChannelDescriptor bufferChannelDescriptor) {
            BufferChannel bufferChannel = this.bufferChannelProvider.getBufferChannel(bufferChannelDescriptor);
            MediaFilter mediaFilter = (MediaFilter) graphNode2.get();
            if (mediaFilter instanceof BufferSupplier) {
                bufferChannel = new BufferSupplyChannel(bufferChannel, new t(mediaFilter));
            }
            if (!(bufferChannelDescriptor.getCapacity() == 0 || bufferChannelDescriptor.getCapacity() == Integer.MAX_VALUE)) {
                bufferChannel.setCapacity(bufferChannelDescriptor.getCapacity());
            }
            GraphEdge graphEdge = new GraphEdge(bufferChannel, evaluator);
            graphEdge.setNode(graphNode.getNodeId(), graphNode2.getNodeId());
            graphNode.addOutputEdge(graphEdge);
            graphNode2.addInputEdge(graphEdge);
            this.graphNodes.add(graphNode);
            this.graphNodes.add(graphNode2);
            return this;
        }
    }

    private void configOutputChannel(Builder builder) {
        if (this.option.isRunOneWay()) {
            builder.outputChannelHandler.accept(this.outputChannel, new a(4));
        } else {
            builder.outputChannelHandler.accept(this.outputChannel, new q(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Builder builder, List list, List list2, List list3, List list4, GraphNode graphNode) {
        if (!graphNode.hasInputEdge()) {
            String str = TAG;
            SLog.d(str, "input node=" + graphNode.getNodeId());
            BufferChannel bufferChannel = builder.bufferChannelProvider.getBufferChannel(new BufferChannelDescriptor(0, Integer.MAX_VALUE));
            graphNode.addInputEdge(new GraphEdge(bufferChannel));
            list.add(graphNode);
            list2.add(bufferChannel);
        }
        if (!graphNode.hasOutputEdge()) {
            String str2 = TAG;
            SLog.d(str2, "output node=" + graphNode.getNodeId());
            BufferChannel bufferChannel2 = builder.bufferChannelProvider.getBufferChannel(new BufferChannelDescriptor(0, Integer.MAX_VALUE));
            graphNode.addOutputEdge(new GraphEdge(bufferChannel2));
            list3.add(graphNode);
            list4.add(bufferChannel2);
        }
        if (graphNode.getDescriptor().getFilterOption().isAllowPartialConnection()) {
            graphNode.addOutputEdge(new GraphEdge(this.outputChannel));
        }
        graphNode.setMessagePublisher(this.messageChannelRouter.newMessagePublisher());
        graphNode.prepare(this.option);
        this.messageChannelRouter.addMessageSubscriber(graphNode);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$run$2(MediaBuffer mediaBuffer) {
        mediaBuffer.setExtra(Message.KEY_END_TIME_US, Long.valueOf(this.option.getMaxDuration(TimeUnit.MICROSECONDS)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$3(DiskCache diskCache, MediaBuffer mediaBuffer) {
        File file;
        MediaMetadataRetriever mediaMetadataRetriever;
        try {
            if (mediaBuffer.containsExtra(Message.KEY_CACHE_ID) && (file = diskCache.get(KeyGenerator.getSimpleKey((String) mediaBuffer.getExtra(Message.KEY_CACHE_ID)))) != null && file.exists()) {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                mediaBuffer.setExtra(Message.KEY_START_TIME_US, Long.valueOf(TimeUnit.MILLISECONDS.toMicros(Long.parseLong(mediaMetadataRetriever.extractMetadata(9)))));
                mediaMetadataRetriever.close();
                return;
            }
            return;
        } catch (IOException e) {
            String str = TAG;
            SLog.i(str, "fail to handle cache: " + e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$run$4(List list, List list2, int i2) {
        MediaBuffer mediaBuffer = (MediaBuffer) list.get(i2);
        MediaBuffer mediaBuffer2 = (MediaBuffer) list2.get(i2);
        MediaBuffer allocate = MediaBuffer.newGroupAlloc().setBuffers(0, mediaBuffer, mediaBuffer2).allocate();
        if (mediaBuffer.containsExtra("media-id")) {
            allocate.setExtra("media-id", mediaBuffer.getExtra("media-id"));
        }
        if (mediaBuffer.containsExtra(Message.KEY_IN_FILE)) {
            allocate.setExtra(Message.KEY_IN_FILE, mediaBuffer.getExtra(Message.KEY_IN_FILE));
        }
        if (mediaBuffer2.containsExtra(Message.KEY_OUT_FILE)) {
            allocate.setExtra(Message.KEY_OUT_FILE, mediaBuffer2.getExtra(Message.KEY_OUT_FILE));
        }
        allocate.setFlags(1);
        return allocate;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$run$5(List list, Integer num) {
        return (Integer) ((MediaBuffer) list.get(num.intValue())).getExtra("media-id");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$run$6(List list, Integer num) {
        return (Integer) ((MediaBuffer) list.get(num.intValue())).getExtra("media-id");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$7(List list, List list2, int i2) {
        ExifInterface exifInterface = (ExifInterface) ((MediaBuffer) list.get(i2)).getExtra("exif");
        if (exifInterface != null) {
            ((MediaBuffer) list2.get(i2)).setExtra("exif", exifInterface);
        }
    }

    public void pause() {
        SLog.d(TAG, "pause");
        this.nodes.forEach(new a(2));
    }

    public void resume() {
        SLog.d(TAG, "resume");
        this.nodes.forEach(new a(3));
    }

    public Response run(List<MediaBuffer> list, List<MediaBuffer> list2) {
        Response response;
        boolean z;
        String str = TAG;
        SLog.d(str, "run E");
        if (this.option.getMaxDuration(TimeUnit.MICROSECONDS) != 0) {
            list.forEach(new q(this, 1));
        }
        if (this.option.isCacheable()) {
            DiskCache diskCache = (DiskCache) this.option.get(1);
            list.forEach(new k(1, diskCache));
            this.messagePublisher.sendMessage(Event.of(1, (Map<String, Object>) new HashMap<String, Object>(diskCache) {
                final /* synthetic */ DiskCache val$diskCache;

                {
                    this.val$diskCache = r3;
                    put("cache", new Pair(r3, Boolean.valueOf(MFGraph.this.option.contains(0))));
                }
            }));
        }
        if (this.option.isPackedIOBuffers()) {
            if (list.size() == list2.size()) {
                z = true;
            } else {
                z = false;
            }
            Def.require(z);
            Map map = (Map) IntStream.range(0, list2.size()).boxed().collect(Collectors.toMap(new s(0, list), new s(1, list2)));
            this.outBufferMap.putAll(map);
            map.clear();
            list.clear();
            list2.clear();
            list.addAll((List) IntStream.range(0, list2.size()).mapToObj(new r(0, list, list2)).collect(Collectors.toList()));
        } else if (list.size() == list2.size()) {
            Map map2 = (Map) IntStream.range(0, list2.size()).boxed().collect(Collectors.toMap(new s(2, list), new s(1, list2)));
            this.outBufferMap.putAll(map2);
            map2.clear();
            list2.clear();
        }
        this.outBuffers = list2;
        if (this.option.isRunOneWay()) {
            runOneWay(list);
            response = Response.of(0);
            response.setFlags(1);
        } else if (this.option.isRunOneByOne()) {
            runOneByOne(list);
            if (list2.isEmpty()) {
                response = Response.of((int) Message.WARN_CANCELED);
            } else {
                response = Response.of(0);
            }
        } else {
            runBatch(list);
            if (list2.isEmpty()) {
                response = Response.of((int) Message.WARN_CANCELED);
            } else {
                response = Response.of(0);
            }
        }
        if (this.option.isRestoreMetadata()) {
            IntStream.range(0, list.size()).forEach(new c(2, list, list2));
        }
        SLog.d(str, "run X");
        return response;
    }

    private MFGraph(Builder builder) {
        super((List) builder.graphNodes.stream().distinct().collect(Collectors.toList()), builder.option);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        String str = TAG;
        SLog.d(str, "prepare each node..." + this.nodes.size());
        Builder builder2 = builder;
        this.nodes.forEach(new K(this, builder2, arrayList3, arrayList, arrayList4, arrayList2));
        if (!arrayList.isEmpty()) {
            if (arrayList.size() == 1) {
                this.inputChannel = (BufferChannel) arrayList.get(0);
            } else {
                this.inputChannel = new SendChannelRouter((List<BufferChannel>) arrayList);
            }
            if (!arrayList2.isEmpty()) {
                if (arrayList2.size() == 1) {
                    this.outputChannel = (BufferChannel) arrayList2.get(0);
                } else {
                    this.outputChannel = new ReceiveChannelRouter((List<BufferChannel>) arrayList2, ReceiveChannelRouter.Type.ANY);
                }
                Def.check(!arrayList3.isEmpty(), "no input node given", new Object[0]);
                Def.check(!arrayList4.isEmpty(), "no input node given", new Object[0]);
                configOutputChannel(builder2);
                SLog.i(str, "success to create MediaFilter graph");
                return;
            }
            throw new IllegalStateException("no output channel given");
        }
        throw new IllegalStateException("no input channel given");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$configOutputChannel$1(MediaBuffer mediaBuffer) {
    }
}
