package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.channel.BufferChannel;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaOutputStreamFilter extends MediaFilter {
    void configOutputChannel(Function<Enum<?>, BufferChannel> function, int i2);

    BufferChannel getOutputChannel() {
        boolean z = true;
        if (getOutputChannelCount() != 1) {
            z = false;
        }
        Def.check(z, "not single output channel", new Object[0]);
        return getOutputChannel((Enum<?>) null);
    }

    BufferChannel getOutputChannel(Enum<?> enumR);

    int getOutputChannelCount();

    boolean isOutputChannelConfigured();
}
