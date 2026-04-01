package com.samsung.android.sum.core.graph;

import A8.C0545b;
import B8.k;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.channel.BufferChannelDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.graph.MFGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MFDescriptorGraph implements Parcelable {
    public static final Parcelable.Creator<MFDescriptorGraph> CREATOR = new Parcelable.Creator<MFDescriptorGraph>() {
        public MFDescriptorGraph createFromParcel(Parcel parcel) {
            return new MFDescriptorGraph(parcel);
        }

        public MFDescriptorGraph[] newArray(int i2) {
            return new MFDescriptorGraph[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) MFDescriptorGraph.class);
    private final DescriptorNode[] nodes;
    private final Graph.Option option;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        AtomicInteger id = new AtomicInteger(0);
        Map<MFDescriptor, DescriptorNode> nodeMap = new HashMap();

        private DescriptorNode getNode(MFDescriptor mFDescriptor) {
            if (!this.nodeMap.containsKey(mFDescriptor)) {
                this.nodeMap.put(mFDescriptor, new DescriptorNode(this.id.getAndIncrement(), mFDescriptor));
            }
            return this.nodeMap.get(mFDescriptor);
        }

        public Builder addNode(MFDescriptor mFDescriptor) {
            getNode(mFDescriptor);
            return this;
        }

        public MFDescriptorGraph build() {
            return new MFDescriptorGraph((DescriptorNode[]) this.nodeMap.values().toArray(new DescriptorNode[0]), new Graph.Option());
        }

        public Builder addNode(MFDescriptor mFDescriptor, MFDescriptor mFDescriptor2) {
            return addNode(getNode(mFDescriptor), getNode(mFDescriptor2), (Evaluator) null, new BufferChannelDescriptor());
        }

        public MFDescriptorGraph build(Graph.Option option) {
            return new MFDescriptorGraph((DescriptorNode[]) this.nodeMap.values().toArray(new DescriptorNode[0]), option);
        }

        public Builder addNode(MFDescriptor mFDescriptor, MFDescriptor mFDescriptor2, Evaluator evaluator) {
            return addNode(getNode(mFDescriptor), getNode(mFDescriptor2), evaluator, new BufferChannelDescriptor());
        }

        public Builder addNode(MFDescriptor mFDescriptor, MFDescriptor mFDescriptor2, int i2) {
            return addNode(getNode(mFDescriptor), getNode(mFDescriptor2), (Evaluator) null, new BufferChannelDescriptor(i2, Integer.MAX_VALUE));
        }

        public Builder addNode(MFDescriptor mFDescriptor, MFDescriptor mFDescriptor2, Evaluator evaluator, int i2) {
            return addNode(getNode(mFDescriptor), getNode(mFDescriptor2), evaluator, new BufferChannelDescriptor(i2, Integer.MAX_VALUE));
        }

        public Builder addNode(MFDescriptor mFDescriptor, MFDescriptor mFDescriptor2, BufferChannelDescriptor bufferChannelDescriptor) {
            return addNode(getNode(mFDescriptor), getNode(mFDescriptor2), (Evaluator) null, bufferChannelDescriptor);
        }

        public Builder addNode(MFDescriptor mFDescriptor, MFDescriptor mFDescriptor2, Evaluator evaluator, BufferChannelDescriptor bufferChannelDescriptor) {
            return addNode(getNode(mFDescriptor), getNode(mFDescriptor2), evaluator, bufferChannelDescriptor);
        }

        private Builder addNode(DescriptorNode descriptorNode, DescriptorNode descriptorNode2, Evaluator evaluator, BufferChannelDescriptor bufferChannelDescriptor) {
            descriptorNode.addNode(descriptorNode2, evaluator, bufferChannelDescriptor);
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DescriptorNode lambda$new$0(Parcelable parcelable) {
        return (DescriptorNode) parcelable;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DescriptorNode[] lambda$new$1(int i2) {
        return new DescriptorNode[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Pair lambda$toMediaFilterGraph$2(MFGraphUnitFactory mFGraphUnitFactory, DescriptorNode descriptorNode) {
        return new Pair(descriptorNode, mFGraphUnitFactory.newNode(descriptorNode.descriptor));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.samsung.android.sum.core.channel.BufferChannelDescriptor} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$toMediaFilterGraph$3(java.util.List r2, java.util.Map r3, java.util.Map r4, com.samsung.android.sum.core.graph.MFGraph.Builder r5, com.samsung.android.sum.core.graph.GraphNode r6, java.lang.Integer r7) {
        /*
            int r0 = r7.intValue()
            java.lang.Object r2 = r2.get(r0)
            android.util.Pair r2 = (android.util.Pair) r2
            java.lang.Object r2 = r2.second
            com.samsung.android.sum.core.graph.GraphNode r2 = (com.samsung.android.sum.core.graph.GraphNode) r2
            boolean r0 = r3.containsKey(r7)
            r1 = 0
            if (r0 == 0) goto L_0x001c
            java.lang.Object r3 = r3.get(r7)
            com.samsung.android.sum.core.evaluate.Evaluator r3 = (com.samsung.android.sum.core.evaluate.Evaluator) r3
            goto L_0x001d
        L_0x001c:
            r3 = r1
        L_0x001d:
            boolean r0 = r4.containsKey(r7)
            if (r0 == 0) goto L_0x002a
            java.lang.Object r4 = r4.get(r7)
            r1 = r4
            com.samsung.android.sum.core.channel.BufferChannelDescriptor r1 = (com.samsung.android.sum.core.channel.BufferChannelDescriptor) r1
        L_0x002a:
            if (r1 != 0) goto L_0x0038
            java.lang.String r4 = TAG
            java.lang.String r7 = "no channel-descriptor given: assign default"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r4, (java.lang.String) r7)
            com.samsung.android.sum.core.channel.BufferChannelDescriptor r1 = new com.samsung.android.sum.core.channel.BufferChannelDescriptor
            r1.<init>()
        L_0x0038:
            r5.addNode(r6, r2, r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.graph.MFDescriptorGraph.lambda$toMediaFilterGraph$3(java.util.List, java.util.Map, java.util.Map, com.samsung.android.sum.core.graph.MFGraph$Builder, com.samsung.android.sum.core.graph.GraphNode, java.lang.Integer):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$toMediaFilterGraph$4(MFGraph.Builder builder, List list, Pair pair) {
        DescriptorNode descriptorNode = (DescriptorNode) pair.first;
        List<Integer> list2 = descriptorNode.children;
        Map<Integer, Evaluator> map = descriptorNode.evaluatorMap;
        Map<Integer, BufferChannelDescriptor> map2 = descriptorNode.channelMap;
        GraphNode graphNode = (GraphNode) pair.second;
        if (list2.isEmpty()) {
            builder.addNode(graphNode);
            return;
        }
        list2.forEach(new k(list, map, map2, builder, graphNode, 17));
    }

    public int describeContents() {
        return 0;
    }

    public Graph.Option getOption() {
        return this.option;
    }

    public Graph<MediaFilter> toMediaFilterGraph(MFGraphUnitFactory mFGraphUnitFactory) {
        String str = TAG;
        SLog.d(str, "toMediaFilterGraph: option=" + this.option);
        MFGraph.Builder builder = new MFGraph.Builder(mFGraphUnitFactory, this.option);
        try {
            List list = (List) Arrays.stream(this.nodes).sorted(Comparator.comparing(new n(0))).map(new o(0, mFGraphUnitFactory)).collect(Collectors.toList());
            list.forEach(new e(10, builder, list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelableArray(this.nodes, i2);
        parcel.writeParcelable(this.option, i2);
    }

    private MFDescriptorGraph(DescriptorNode[] descriptorNodeArr, Graph.Option option2) {
        this.nodes = descriptorNodeArr;
        this.option = option2;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.util.function.IntFunction] */
    public MFDescriptorGraph(Parcel parcel) {
        DescriptorNode[] descriptorNodeArr = (DescriptorNode[]) Arrays.stream(parcel.readParcelableArray(DescriptorNode.class.getClassLoader())).map(new n(5)).toArray(new Object());
        this.nodes = descriptorNodeArr;
        this.option = (Graph.Option) parcel.readParcelable(Graph.Option.class.getClassLoader());
        for (DescriptorNode descriptorNode : descriptorNodeArr) {
            MFDescriptor mFDescriptor = descriptorNode.descriptor;
            if (mFDescriptor instanceof NNDescriptor) {
                NNDescriptor nNDescriptor = (NNDescriptor) mFDescriptor;
                String str = TAG;
                SLog.d(str, "node: isKeepFilterDatatype " + nNDescriptor.isKeepFilterDatatype());
                SLog.d(str, "node: isMultipleInputOutput " + nNDescriptor.isBatchIO());
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DescriptorNode implements Parcelable {
        public static final Parcelable.Creator<DescriptorNode> CREATOR = new Parcelable.Creator<DescriptorNode>() {
            public DescriptorNode createFromParcel(Parcel parcel) {
                return new DescriptorNode(parcel);
            }

            public DescriptorNode[] newArray(int i2) {
                return new DescriptorNode[i2];
            }
        };
        Map<Integer, BufferChannelDescriptor> channelMap = new HashMap();
        List<Integer> children = new ArrayList();
        MFDescriptor descriptor;
        Map<Integer, Evaluator> evaluatorMap = new HashMap();
        int id;

        public DescriptorNode(int i2, MFDescriptor mFDescriptor) {
            this.id = i2;
            this.descriptor = mFDescriptor;
        }

        public void addNode(DescriptorNode descriptorNode, Evaluator evaluator, BufferChannelDescriptor bufferChannelDescriptor) {
            this.children.add(Integer.valueOf(descriptorNode.getId()));
            if (evaluator != null) {
                this.evaluatorMap.put(Integer.valueOf(descriptorNode.getId()), evaluator);
            }
            this.channelMap.put(Integer.valueOf(descriptorNode.getId()), bufferChannelDescriptor);
        }

        public int describeContents() {
            return 0;
        }

        public int getId() {
            return this.id;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.id);
            parcel.writeParcelable(this.descriptor, i2);
            parcel.writeIntArray(this.children.stream().mapToInt(new C0545b(25)).toArray());
            parcel.writeMap(this.evaluatorMap);
            parcel.writeMap(this.channelMap);
        }

        public DescriptorNode(Parcel parcel) {
            this.id = parcel.readInt();
            this.descriptor = (MFDescriptor) parcel.readParcelable(MFDescriptor.class.getClassLoader(), MFDescriptor.class);
            this.children = (List) Arrays.stream(parcel.createIntArray()).boxed().collect(Collectors.toList());
            parcel.readMap(this.evaluatorMap, Evaluator.class.getClassLoader(), Integer.class, Evaluator.class);
            parcel.readMap(this.channelMap, BufferChannelDescriptor.class.getClassLoader(), Integer.class, BufferChannelDescriptor.class);
        }
    }
}
