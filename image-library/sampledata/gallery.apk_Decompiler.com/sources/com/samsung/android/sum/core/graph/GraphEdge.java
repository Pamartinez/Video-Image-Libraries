package com.samsung.android.sum.core.graph;

import android.util.Pair;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.evaluate.Evaluator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GraphEdge {
    public static int INVALID_ID = -1;
    private final BufferChannel bufferChannel;
    private final Evaluator evaluator;
    private int id;
    private Pair<String, String> node;

    public GraphEdge(int i2) {
        this((BufferChannel) null, (Evaluator) null);
        this.id = i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getBeginNode$0(Pair pair) {
        return (String) pair.first;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getEndNode$1(Pair pair) {
        return (String) pair.second;
    }

    public String getBeginNode() {
        return (String) Optional.ofNullable(this.node).map(new n(2)).orElse("n/a");
    }

    public BufferChannel getBufferChannel() {
        return this.bufferChannel;
    }

    public String getEndNode() {
        return (String) Optional.ofNullable(this.node).map(new n(3)).orElse("n/a");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.Supplier] */
    public Evaluator getEvaluator() {
        return (Evaluator) Optional.ofNullable(this.evaluator).orElseGet(new Object());
    }

    public int getId() {
        return this.id;
    }

    public void setNode(String str, String str2) {
        this.node = new Pair<>(str, str2);
    }

    public GraphEdge(int i2, Evaluator evaluator2) {
        this((BufferChannel) null, evaluator2);
        this.id = i2;
    }

    public GraphEdge(BufferChannel bufferChannel2) {
        this(bufferChannel2, (Evaluator) null);
    }

    public GraphEdge(BufferChannel bufferChannel2, Evaluator evaluator2) {
        this.id = INVALID_ID;
        this.bufferChannel = bufferChannel2;
        this.evaluator = evaluator2;
    }
}
