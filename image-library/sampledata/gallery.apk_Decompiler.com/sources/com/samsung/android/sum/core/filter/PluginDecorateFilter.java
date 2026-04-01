package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.functional.ExecuteDelegator;
import com.samsung.android.sum.core.plugin.NNPlugin;
import com.samsung.android.sum.core.plugin.PluginFixture;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PluginDecorateFilter<T extends PluginFixture<?>> extends DecorateFilter {
    protected T plugin;
    protected BiFunction<MediaBuffer, MutableMediaBuffer, MutableMediaBuffer> successorHandler;

    public PluginDecorateFilter(T t, MediaFilter mediaFilter) {
        super(mediaFilter);
        ExecuteDelegator executeDelegator;
        this.plugin = t;
        if ((t instanceof NNPlugin) && (executeDelegator = ((NNPlugin) t).getExecuteDelegator()) != null) {
            mediaFilter = mediaFilter instanceof DecorateFilter ? ((DecorateFilter) mediaFilter).getEnclosedFilter() : mediaFilter;
            if (mediaFilter instanceof NNFWFilter) {
                ((NNFWFilter) mediaFilter).setExecuteDelegator(executeDelegator);
            } else if (mediaFilter instanceof MediaFilterPlaceHolder) {
                ((MediaFilterPlaceHolder) mediaFilter).setMediaFilterUpdater(new b(7, executeDelegator));
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(ExecuteDelegator executeDelegator, MediaFilter mediaFilter) {
        if (mediaFilter instanceof NNFWFilter) {
            ((NNFWFilter) mediaFilter).setExecuteDelegator(executeDelegator);
        }
    }

    public void setSuccessorHandler(BiFunction<MediaBuffer, MutableMediaBuffer, MutableMediaBuffer> biFunction) {
        this.successorHandler = biFunction;
    }
}
