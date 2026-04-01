package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.channel.BufferChannel;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaInputStreamFilter extends MediaFilter {
    void configInputChannel(Function<Enum<?>, BufferChannel> function, int i2);

    BufferChannel getInputChannel() {
        boolean z = true;
        if (getInputChannelCount() != 1) {
            z = false;
        }
        Def.check(z, "not single input channel", new Object[0]);
        return getInputChannel((Enum<?>) null);
    }

    BufferChannel getInputChannel(Enum<?> enumR);

    int getInputChannelCount();

    boolean isInputChannelConfigured();
}
