package com.samsung.android.gallery.app.ui.list.search.transition;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITransition {
    static ITransition create(IMvpBaseView iMvpBaseView) {
        return new TransitionImpl(iMvpBaseView);
    }

    void destroy();

    boolean requireShrinkTransition();

    void reset();

    void startShrinkAnimation();

    void startSimpleAutoScroller(int i2, Consumer<Boolean> consumer);
}
