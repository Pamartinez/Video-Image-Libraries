package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.message.MessagePublisher;
import com.samsung.android.sum.core.message.MessageSubscriber;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GraphNode<T> extends MessageSubscriber {
    GraphNode<T> addInputEdge(GraphEdge graphEdge);

    GraphNode<T> addOutputEdge(GraphEdge graphEdge);

    boolean containsOption(int i2);

    T get();

    MFDescriptor getDescriptor();

    Function<Exception, Boolean> getExceptionHandler();

    String getNodeId();

    <V> V getOption(int i2);

    <V> V getOption(int i2, V v);

    BufferChannel getReceiveChannelRouter();

    BufferChannel getSendChannelRouter();

    boolean hasInputEdge();

    boolean hasOutputEdge();

    void pause();

    void prepare(Graph.Option option);

    void release();

    void resume();

    void setExceptionHandler(Function<Exception, Boolean> function);

    void setMessagePublisher(MessagePublisher messagePublisher);

    void setOption(int i2);

    void setOption(int i2, Object obj);
}
