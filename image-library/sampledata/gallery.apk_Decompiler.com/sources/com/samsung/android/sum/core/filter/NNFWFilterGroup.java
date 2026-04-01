package com.samsung.android.sum.core.filter;

import A4.I;
import J5.c;
import c4.C0438h;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.NNFWDescriptor;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.types.Status;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNFWFilterGroup extends NNFWFilter implements MediaFilterGroup {
    private static final String TAG = Def.tagOf((Class<?>) NNFWFilterGroup.class);
    private List<MediaFilter> filters;
    private MediaFilter mediaFilter;
    private ModelSelector modelSelector;

    public NNFWFilterGroup(NNFWDescriptor nNFWDescriptor, List<MediaFilter> list) {
        super(nNFWDescriptor);
        this.modelSelector = nNFWDescriptor.getNNDescriptor().getModelSelector();
        nNFWDescriptor.getNNDescriptor().setModelSelector(new m(this));
        this.filters = list;
        Def.require(this.modelSelector != null, "no model selector is given", new Object[0]);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ IllegalStateException lambda$loadModel$2(ModelSelector.Item item) {
        return new IllegalStateException("no matched model with " + item);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$3(MediaFilter mediaFilter2) {
        MediaFilter mediaFilter3;
        if (mediaFilter2 instanceof DecorateFilter) {
            mediaFilter3 = ((DecorateFilter) mediaFilter2).getEnclosedFilter();
        } else {
            mediaFilter3 = mediaFilter2;
        }
        if (mediaFilter3 instanceof NNFWFilter) {
            ((NNFWFilter) mediaFilter3).setExecuteDelegator(this.executeDelegator);
        }
        mediaFilter2.prepare();
    }

    /* access modifiers changed from: private */
    /* renamed from: loadModel */
    public ModelSelector.Item lambda$new$0(MediaBuffer mediaBuffer) {
        ModelSelector.Item select = this.modelSelector.select(mediaBuffer);
        String str = TAG;
        SLog.d(str, "load model: " + select.name);
        this.mediaFilter = this.filters.stream().filter(new I(22, select)).findFirst().orElseThrow(new c(20, select));
        return select;
    }

    public MediaFilterGroup addFilter(List<MediaFilter> list) {
        this.filters.addAll(list);
        return this;
    }

    public void prepare() {
        this.filters.forEach(new b(5, this));
    }

    public void release() {
        this.filters.forEach(new C0438h(10));
    }

    public boolean removeFilter(MediaFilter... mediaFilterArr) {
        return this.filters.removeAll(Arrays.asList(mediaFilterArr));
    }

    public boolean replaceFilter(MediaFilter mediaFilter2, MediaFilter mediaFilter3) {
        int indexOf = this.filters.indexOf(mediaFilter2);
        if (indexOf < 0) {
            return false;
        }
        this.filters.set(indexOf, mediaFilter3);
        return true;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        if (this.mediaFilter == null) {
            lambda$new$0(mediaBuffer);
        }
        MutableMediaBuffer run = this.mediaFilter.run(mediaBuffer, mutableMediaBuffer);
        if (this.descriptor.isInstant()) {
            this.mediaFilter = null;
        }
        return run;
    }

    public Status runAdapter(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2) {
        throw new UnsupportedOperationException("do not use this");
    }

    public Stream<MediaFilter> stream() {
        return this.filters.stream();
    }

    public NNFWFilterGroup(NNFWDescriptor nNFWDescriptor) {
        super(nNFWDescriptor);
    }
}
