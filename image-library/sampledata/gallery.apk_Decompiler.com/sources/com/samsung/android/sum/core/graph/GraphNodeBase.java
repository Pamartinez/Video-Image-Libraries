package com.samsung.android.sum.core.graph;

import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.BufferChannelGroup;
import com.samsung.android.sum.core.channel.ReceiveChannelRouter;
import com.samsung.android.sum.core.channel.SendChannelRouter;
import com.samsung.android.sum.core.channel.VoidBufferChannel;
import com.samsung.android.sum.core.channel.b;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNFWDescriptor;
import com.samsung.android.sum.core.evaluate.EvalNone;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.filter.DecorateFilter;
import com.samsung.android.sum.core.filter.ImgpDecorateFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterGroup;
import com.samsung.android.sum.core.filter.MediaFilterPlaceHolder;
import com.samsung.android.sum.core.filter.MediaFilterRetriever;
import com.samsung.android.sum.core.filter.MediaFilterTracer;
import com.samsung.android.sum.core.filter.MediaInputStreamFilter;
import com.samsung.android.sum.core.filter.MediaOutputStreamFilter;
import com.samsung.android.sum.core.filter.NNFWFilter;
import com.samsung.android.sum.core.filter.collection.SequentialFilter;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.message.MessageChannel;
import com.samsung.android.sum.core.message.MessagePublisher;
import com.samsung.android.sum.core.message.MessageSubscriberBase;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.nn.NNFW;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GraphNodeBase<T> extends MessageSubscriberBase implements GraphNode<T> {
    private static final String TAG = Def.tagOf((Class<?>) GraphNodeBase.class);
    private Function<Exception, Boolean> exceptionHandler;
    protected final T impl;
    protected final List<GraphEdge> inputEdges = new ArrayList();
    protected MessagePublisher messagePublisher;
    protected String nodeId;
    protected Graph.Option option = new Graph.Option();
    protected final List<GraphEdge> outputEdges = new ArrayList();
    private boolean quit = false;
    protected BufferChannel receiveRouter;
    protected BufferChannel sendRouter;

    public GraphNodeBase(T t, MessageChannel messageChannel) {
        super(messageChannel);
        this.impl = t;
        if (t instanceof MediaFilter) {
            MediaFilter mediaFilter = (MediaFilter) t;
            this.nodeId = mediaFilter.getId() + Log.TAG_SEPARATOR + t.hashCode();
            MediaFilterRetriever mediaFilterRetriever = new MediaFilterRetriever();
            mediaFilterRetriever.addPredicateHandler(new g(0), new h(this, 0));
            mediaFilterRetriever.retrieve(mediaFilter);
        }
        this.exceptionHandler = new i(this, 0);
    }

    private void configInputChannels(MediaFilter mediaFilter, MediaFilter.Option option2) {
        ReceiveChannelRouter.Type type;
        String str = TAG;
        SLog.d(str, "configInputChannels: " + mediaFilter);
        boolean z = mediaFilter instanceof MediaInputStreamFilter;
        if (!z || option2.isWaitToReceiveAll()) {
            Map map = (Map) this.inputEdges.stream().collect(Collectors.toMap(new b(8), new b(9)));
            if (option2.isWaitToReceiveAll()) {
                type = ReceiveChannelRouter.Type.ALL;
            } else {
                type = ReceiveChannelRouter.Type.ANY;
            }
            this.receiveRouter = new ReceiveChannelRouter((Map<Evaluator, BufferChannel>) map, type);
            if (z) {
                ((MediaInputStreamFilter) mediaFilter).configInputChannel(new i(this, 2), 1);
                return;
            }
            return;
        }
        if (this.inputEdges.size() > 1) {
            Map map2 = (Map) this.inputEdges.stream().collect(Collectors.toMap(new n(4), new b(9)));
            Objects.requireNonNull(map2);
            ((MediaInputStreamFilter) mediaFilter).configInputChannel(new o(2, map2), map2.size());
        } else {
            ((MediaInputStreamFilter) mediaFilter).configInputChannel(new i(this, 1), 1);
        }
        this.receiveRouter = new VoidBufferChannel();
    }

    private void configOutputChannels(MediaFilter mediaFilter, MediaFilter.Option option2) {
        SendChannelRouter.Type type;
        String str = TAG;
        SLog.d(str, "configOutputChannels: " + mediaFilter);
        if (mediaFilter instanceof MediaOutputStreamFilter) {
            if (this.outputEdges.size() > 1) {
                HashMap hashMap = new HashMap();
                this.outputEdges.forEach(new k(0, hashMap));
                if (hashMap.size() > 1) {
                    ((MediaOutputStreamFilter) mediaFilter).configOutputChannel(new l(0, hashMap), hashMap.size());
                } else {
                    ((MediaOutputStreamFilter) mediaFilter).configOutputChannel(new l(1, hashMap), hashMap.size());
                }
            } else {
                ((MediaOutputStreamFilter) mediaFilter).configOutputChannel(new i(this, 3), 1);
            }
            this.sendRouter = new VoidBufferChannel();
            return;
        }
        Map map = (Map) this.outputEdges.stream().collect(Collectors.toMap(new b(8), new b(9)));
        if (option2.isAllowPartialConnection()) {
            type = SendChannelRouter.Type.ANY;
        } else {
            type = SendChannelRouter.Type.ALL;
        }
        this.sendRouter = new SendChannelRouter((Map<Evaluator, BufferChannel>) map, type);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyGraphOption$10(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        ((ArrayList) getOption(6, new ArrayList())).add(mediaFilter.getDescriptor().getFilterId());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyGraphOption$11(MediaFilterRetriever mediaFilterRetriever, Object obj) {
        if (obj instanceof NNFW) {
            mediaFilterRetriever.addPredicateHandler(new f((NNFW) obj), new h(this, 1));
            return;
        }
        throw new UnsupportedOperationException("unknown key: " + obj);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$applyGraphOption$12(MediaFilter mediaFilter) {
        if ((mediaFilter instanceof DecorateFilter) || (mediaFilter instanceof MediaFilterGroup)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyGraphOption$13(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        MediaFilterTracer mediaFilterTracer;
        String str = TAG;
        SLog.d(str, "found leaf filter=" + mediaFilter + ", parent=" + mediaFilter2);
        if (mediaFilter instanceof MediaFilterPlaceHolder) {
            SLog.d(str, "skip to trace MediaFilterPlaceHolder");
            return;
        }
        if (mediaFilter2 instanceof ImgpDecorateFilter) {
            ImgpDecorateFilter imgpDecorateFilter = (ImgpDecorateFilter) mediaFilter2;
            if (imgpDecorateFilter.getPreFilter() == mediaFilter) {
                mediaFilterTracer = new MediaFilterTracer(mediaFilter, this.messagePublisher.getMessageProducer());
                imgpDecorateFilter.setPreFilter(mediaFilterTracer);
            } else if (imgpDecorateFilter.getPostFilter() == mediaFilter) {
                mediaFilterTracer = new MediaFilterTracer(mediaFilter, this.messagePublisher.getMessageProducer());
                imgpDecorateFilter.setPostFilter(mediaFilterTracer);
            } else {
                mediaFilterTracer = new MediaFilterTracer(mediaFilter, this.messagePublisher.getMessageProducer());
                imgpDecorateFilter.setSuccessorFilter(mediaFilterTracer);
            }
        } else if (mediaFilter2 instanceof DecorateFilter) {
            MediaFilterTracer mediaFilterTracer2 = new MediaFilterTracer(mediaFilter, this.messagePublisher.getMessageProducer(), mediaFilter2);
            ((DecorateFilter) mediaFilter2).setSuccessorFilter(mediaFilterTracer2);
            mediaFilterTracer = mediaFilterTracer2;
        } else if (mediaFilter2 instanceof MediaFilterGroup) {
            mediaFilterTracer = new MediaFilterTracer(mediaFilter, this.messagePublisher.getMessageProducer());
            ((MediaFilterGroup) mediaFilter2).replaceFilter(mediaFilter, mediaFilterTracer);
        } else {
            mediaFilterTracer = null;
        }
        if (mediaFilterTracer != null) {
            addMessageConsumer(mediaFilterTracer);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$applyGraphOption$9(NNFW nnfw, MediaFilter mediaFilter) {
        if (!(mediaFilter instanceof NNFWFilter) || ((NNFWDescriptor) mediaFilter.getDescriptor()).getFw() != nnfw) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Enum lambda$configInputChannels$2(GraphEdge graphEdge) {
        return (Enum) graphEdge.getEvaluator().getValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BufferChannel lambda$configInputChannels$3(Enum enumR) {
        return (BufferChannel) this.inputEdges.stream().findFirst().map(new b(9)).orElseThrow(new u(4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BufferChannel lambda$configInputChannels$4(Enum enumR) {
        return this.receiveRouter;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$configOutputChannels$5(Map map, GraphEdge graphEdge) {
        MediaType mediaType;
        Evaluator evaluator = graphEdge.getEvaluator();
        if (evaluator instanceof EvalNone) {
            mediaType = MediaType.NONE;
        } else {
            mediaType = (MediaType) evaluator.getValue();
        }
        if (map.containsKey(mediaType)) {
            BufferChannel bufferChannel = (BufferChannel) map.remove(mediaType);
            if (bufferChannel instanceof BufferChannelGroup) {
                ((BufferChannelGroup) bufferChannel).addBufferChannel(graphEdge.getBufferChannel());
                map.put(mediaType, bufferChannel);
                return;
            }
            map.put(mediaType, new SendChannelRouter(bufferChannel, graphEdge.getBufferChannel()));
            return;
        }
        map.put(mediaType, graphEdge.getBufferChannel());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$configOutputChannels$6(Map map, Enum enumR) {
        return (BufferChannel) map.values().stream().findFirst().get();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BufferChannel lambda$configOutputChannels$7(Enum enumR) {
        return (BufferChannel) this.outputEdges.stream().findFirst().map(new b(9)).orElseThrow(new u(4));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(MediaFilter mediaFilter) {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        addMessageConsumer(mediaFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$release$14(GraphEdge graphEdge) {
        if (graphEdge.getBufferChannel() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$release$15(GraphEdge graphEdge) {
        BufferChannel bufferChannel = graphEdge.getBufferChannel();
        String str = TAG;
        SLog.d(str, "[" + graphEdge.getBeginNode() + " => " + graphEdge.getEndNode() + "]cancel buffer channel " + bufferChannel);
        try {
            bufferChannel.cancel();
        } catch (CancellationException e) {
            String str2 = TAG;
            SLog.d(str2, "canceled buffer-channel: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public boolean parseException(Exception exc) {
        try {
            String substring = exc.getMessage().split("]@")[0].substring(2);
            List list = (List) getOption(6);
            if (list == null || !list.stream().anyMatch(new b(1, substring))) {
                return false;
            }
            return true;
        } catch (Exception unused) {
        }
    }

    public GraphNode<T> addInputEdge(GraphEdge graphEdge) {
        this.inputEdges.add(graphEdge);
        return this;
    }

    public GraphNode<T> addOutputEdge(GraphEdge graphEdge) {
        this.outputEdges.add(graphEdge);
        return this;
    }

    public void applyGraphOption(Graph.Option option2) {
        String str = TAG;
        SLog.d(str, "applyGraphOption: option=" + option2 + " => node=" + this);
        MediaFilterRetriever mediaFilterRetriever = new MediaFilterRetriever();
        if (option2.isIgnoreFilterException()) {
            ((List) option2.getIgnoreFilterException()).forEach(new e(9, this, mediaFilterRetriever));
        }
        if (option2.isTraceMediaFilter()) {
            mediaFilterRetriever.addPredicateHandler(new g(1), new h(this, 2));
        }
        mediaFilterRetriever.retrieve((MediaFilter) get());
    }

    public boolean containsOption(int i2) {
        return this.option.contains(i2);
    }

    public T get() {
        return this.impl;
    }

    public MFDescriptor getDescriptor() {
        T t = this.impl;
        if (t instanceof MFDescriptor) {
            return (MFDescriptor) t;
        }
        if (t instanceof MediaFilter) {
            return ((MediaFilter) t).getDescriptor();
        }
        throw new IllegalStateException("type is not MediaFilter either MFDescriptor");
    }

    public Function<Exception, Boolean> getExceptionHandler() {
        return this.exceptionHandler;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public <V> V getOption(int i2) {
        return this.option.get(i2);
    }

    public BufferChannel getReceiveChannelRouter() {
        return this.receiveRouter;
    }

    public BufferChannel getSendChannelRouter() {
        return this.sendRouter;
    }

    public boolean hasInputEdge() {
        return !this.inputEdges.isEmpty();
    }

    public boolean hasOutputEdge() {
        return !this.outputEdges.isEmpty();
    }

    public boolean isQuit() {
        return this.quit;
    }

    /* JADX WARNING: type inference failed for: r3v8, types: [java.lang.Object, java.util.function.BinaryOperator] */
    public void prepare(Graph.Option option2) {
        if (option2.contains(11)) {
            this.nodeId = "[" + option2.getGraphName() + "]" + this.nodeId;
        }
        String str = TAG;
        SLog.d(str, "prepare[" + this.nodeId + "]: " + this.impl);
        MediaFilter mediaFilter = this.impl;
        if (mediaFilter instanceof MediaFilter) {
            MediaFilter mediaFilter2 = mediaFilter;
            if (option2.contains(11)) {
                mediaFilter2.addTag(option2.getGraphName());
            }
            if (mediaFilter2.getDescriptor().getFilterOption().contains(13)) {
                mediaFilter2.addTag((String) mediaFilter2.getDescriptor().getFilterOption().get(13));
            }
            applyGraphOption(option2);
            MediaFilter.Option filterOption = getDescriptor().getFilterOption();
            SequentialFilter sequentialFilter = this.impl;
            if (sequentialFilter instanceof SequentialFilter) {
                configInputChannels(sequentialFilter.stream().findFirst().orElseThrow(new u(4)), filterOption);
            } else {
                configInputChannels(mediaFilter2, filterOption);
            }
            SequentialFilter sequentialFilter2 = this.impl;
            if (sequentialFilter2 instanceof SequentialFilter) {
                configOutputChannels(sequentialFilter2.stream().reduce(new Object()).orElseThrow(new u(4)), filterOption);
            } else {
                configOutputChannels(mediaFilter2, filterOption);
            }
            mediaFilter2.prepare();
        }
        SLog.i(str, "success to prepare MediaFilter: " + getNodeId());
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [java.util.function.Predicate, java.lang.Object] */
    public void release() {
        String str = TAG;
        SLog.d(str, "release...E: " + this.nodeId);
        super.release();
        Stream.concat(this.inputEdges.stream(), this.outputEdges.stream()).filter(new Object()).forEach(new a(1));
        MediaFilter mediaFilter = this.impl;
        if (mediaFilter instanceof MediaFilter) {
            mediaFilter.release();
        }
        SLog.d(str, "release...X: " + this.nodeId);
    }

    public void setExceptionHandler(Function<Exception, Boolean> function) {
        this.exceptionHandler = function;
    }

    public void setMessagePublisher(MessagePublisher messagePublisher2) {
        messagePublisher2.setName(this.nodeId);
        this.messagePublisher = messagePublisher2;
        T t = this.impl;
        if (t instanceof MediaFilter) {
            ((MediaFilter) t).setMessageProducer(messagePublisher2.getMessageProducer());
        }
    }

    public void setOption(int i2) {
        this.option.set(i2);
    }

    public void setQuit(boolean z) {
        this.quit = z;
    }

    public <V> V getOption(int i2, V v) {
        return this.option.get(i2, v);
    }

    public void setOption(int i2, Object obj) {
        this.option.set(i2, obj);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilter lambda$prepare$8(MediaFilter mediaFilter, MediaFilter mediaFilter2) {
        return mediaFilter2;
    }
}
