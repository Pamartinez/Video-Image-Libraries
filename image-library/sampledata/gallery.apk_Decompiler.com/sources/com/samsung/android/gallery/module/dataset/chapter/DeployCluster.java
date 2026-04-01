package com.samsung.android.gallery.module.dataset.chapter;

import X8.b;
import X8.c;
import X8.d;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.chain.Chain;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeployCluster implements Cluster {
    private final BiConsumer<Integer, LayoutInfo> callBacks;
    private final List<Cluster> clusters = new ArrayList();
    private final List<LayoutInfo> layoutGroup = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Rule implements Chain<Rule> {
        private static final List<Rule> pool = new ArrayList();
        private Rule next;
        private Predicate<?> predicate;

        private Rule() {
        }

        /* access modifiers changed from: private */
        public static Rule load(Predicate<Boolean> predicate2) {
            Rule rule;
            List<Rule> list = pool;
            if (list.isEmpty()) {
                rule = new Rule();
            } else {
                rule = list.remove(0);
            }
            return rule.setRule(predicate2);
        }

        /* access modifiers changed from: private */
        public static void restore(Rule rule) {
            rule.setNext((Rule) null);
            pool.add(rule);
        }

        private Rule setRule(Predicate<?> predicate2) {
            this.predicate = predicate2;
            return this;
        }

        public boolean test(Consumer<Rule> consumer) {
            boolean z;
            Rule rule;
            if (this.predicate.test((Object) null) || ((rule = this.next) != null && rule.test(consumer))) {
                z = true;
            } else {
                z = false;
            }
            consumer.accept(this);
            return z;
        }

        public void setNext(Rule rule) {
            this.next = rule;
        }
    }

    public DeployCluster(BiConsumer<Integer, LayoutInfo> biConsumer) {
        this.callBacks = biConsumer;
    }

    private void applyGroupRule(List<LayoutInfo> list, boolean z) {
        if (list != null) {
            if (z) {
                this.layoutGroup.addAll(list);
            } else {
                LayoutRule.setGroupRule(list);
            }
        }
        LayoutRule.setGroupRule(this.layoutGroup);
        this.layoutGroup.clear();
    }

    private void deployInternal(LayoutInfo[] layoutInfoArr) {
        LayoutInfo layoutInfo;
        for (int i2 = 0; i2 < layoutInfoArr.length; i2++) {
            if (i2 < layoutInfoArr.length - 1) {
                layoutInfo = layoutInfoArr[i2 + 1];
            } else {
                layoutInfo = null;
            }
            boolean z = false;
            for (Cluster cluster : this.clusters) {
                z = cluster.cluster(layoutInfoArr[i2], layoutInfo, z);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.util.function.Consumer, java.lang.Object] */
    private boolean handle(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z) {
        return ((Rule) new ChainBuilder().append(Rule.load(new b(this, layoutInfo, 0))).append(Rule.load(new b(this, layoutInfo, 1))).append(Rule.load(new b(this, layoutInfo, 2))).append(Rule.load(new c(this, layoutInfo, z))).append(Rule.load(new b(this, layoutInfo, 3))).append(Rule.load(new d(this, layoutInfo, layoutInfo2))).append(Rule.load(new b(this, layoutInfo, 4))).build()).test(new Object());
    }

    private boolean handleClustered(LayoutInfo layoutInfo, boolean z) {
        if (z) {
            applyGroupRule(Collections.singletonList(layoutInfo), !layoutInfo.horizontal);
        }
        return z;
    }

    private boolean handleHorizontal(LayoutInfo layoutInfo) {
        if (!layoutInfo.horizontal) {
            return false;
        }
        applyGroupRule(Collections.singletonList(layoutInfo), false);
        return true;
    }

    private boolean handleMaxVideoCount(LayoutInfo layoutInfo) {
        if (!layoutInfo.isVideo || !this.layoutGroup.stream().skip((long) Math.max(this.layoutGroup.size() - 2, 0)).anyMatch(new S3.d(17))) {
            return false;
        }
        applyGroupRule(Collections.singletonList(layoutInfo), false);
        handleTagChunk(layoutInfo);
        return true;
    }

    private boolean handleSimilarChunk(LayoutInfo layoutInfo) {
        LayoutInfo layoutInfo2;
        if (layoutInfo.similarChunkDisplayable) {
            if (!this.layoutGroup.isEmpty()) {
                layoutInfo2 = (LayoutInfo) C0086a.f(1, this.layoutGroup);
            } else {
                layoutInfo2 = null;
            }
            if (layoutInfo2 != null && !layoutInfo2.horizontal && !layoutInfo.horizontal) {
                this.layoutGroup.remove(layoutInfo2);
                applyGroupRule(Arrays.asList(new LayoutInfo[]{layoutInfo2, layoutInfo}), false);
                return true;
            }
        }
        return false;
    }

    private boolean handleTagChunk(LayoutInfo layoutInfo) {
        if (!layoutInfo.chunkDisplayable) {
            return false;
        }
        LayoutRule.setTagChunkRule(layoutInfo);
        this.callBacks.accept(0, layoutInfo);
        applyGroupRule((List<LayoutInfo>) null, false);
        return true;
    }

    private boolean handleVertical(LayoutInfo layoutInfo, LayoutInfo layoutInfo2) {
        if (this.layoutGroup.size() != 8 && (layoutInfo2 == null || !layoutInfo2.horizontal)) {
            return false;
        }
        applyGroupRule(Collections.singletonList(layoutInfo), true);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$0(LayoutInfo layoutInfo, Boolean bool) {
        return handleMaxVideoCount(layoutInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$1(LayoutInfo layoutInfo, Boolean bool) {
        return handleTagChunk(layoutInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$2(LayoutInfo layoutInfo, Boolean bool) {
        return handleSimilarChunk(layoutInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$3(LayoutInfo layoutInfo, boolean z, Boolean bool) {
        return handleClustered(layoutInfo, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$4(LayoutInfo layoutInfo, Boolean bool) {
        return handleHorizontal(layoutInfo);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$5(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, Boolean bool) {
        return handleVertical(layoutInfo, layoutInfo2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handle$6(LayoutInfo layoutInfo, Boolean bool) {
        return unhandled(layoutInfo);
    }

    private boolean unhandled(LayoutInfo layoutInfo) {
        this.layoutGroup.add(layoutInfo);
        return false;
    }

    public boolean cluster(LayoutInfo layoutInfo, LayoutInfo layoutInfo2, boolean z) {
        return handle(layoutInfo, layoutInfo2, z);
    }

    public void deploy(LayoutInfo[] layoutInfoArr) {
        withCluster(this).deployInternal(layoutInfoArr);
    }

    public DeployCluster withCluster() {
        return withCluster(Cluster.get(0)).withCluster(Cluster.get(1));
    }

    private DeployCluster withCluster(Cluster cluster) {
        this.clusters.add(cluster);
        return this;
    }
}
