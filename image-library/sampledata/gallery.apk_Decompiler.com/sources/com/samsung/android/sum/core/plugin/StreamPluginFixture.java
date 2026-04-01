package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.functional.PlaceHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StreamPluginFixture extends StaplePluginFixture {
    PlaceHolder<Consumer<MediaBuffer>> outputHandlerHolder;

    public StreamPluginFixture(Plugin<StreamPluginFixture> plugin) {
        super(plugin);
    }

    public PlaceHolder<Consumer<MediaBuffer>> getOutputHandlerHolder() {
        return this.outputHandlerHolder;
    }

    public void setOutputHandlerHolder(PlaceHolder<Consumer<MediaBuffer>> placeHolder) {
        this.outputHandlerHolder = placeHolder;
    }
}
