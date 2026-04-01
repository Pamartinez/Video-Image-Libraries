package com.samsung.android.gallery.app.ui.list.stories.category;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ d(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        int i7 = this.e;
        StoriesCategory2Header storiesCategory2Header = (StoriesCategory2Header) obj;
        switch (i2) {
            case 0:
                storiesCategory2Header.handleDensityChange(i7);
                return;
            default:
                storiesCategory2Header.handleResolutionChange(i7);
                return;
        }
    }
}
