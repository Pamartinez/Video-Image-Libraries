package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.channel.BufferChannel;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class u implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MFGraphUnitFactory f4121a;

    public /* synthetic */ u(MFGraphUnitFactory mFGraphUnitFactory) {
        this.f4121a = mFGraphUnitFactory;
    }

    public final void accept(Object obj, Object obj2) {
        this.f4121a.newOutputChannelHandler((BufferChannel) obj, (Consumer) obj2);
    }
}
