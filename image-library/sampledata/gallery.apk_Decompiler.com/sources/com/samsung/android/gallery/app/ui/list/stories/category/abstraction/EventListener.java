package com.samsung.android.gallery.app.ui.list.stories.category.abstraction;

import android.os.Bundle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EventListener {
    void handleDensityChange();

    void invalidateLayout();

    boolean onHandleInternalEvent(InternalEvent internalEvent, Object... objArr);

    void saveState(Bundle bundle);
}
