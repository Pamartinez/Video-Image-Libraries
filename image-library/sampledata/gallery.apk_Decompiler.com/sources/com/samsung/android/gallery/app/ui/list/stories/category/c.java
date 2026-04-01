package com.samsung.android.gallery.app.ui.list.stories.category;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((StoriesCategory2Header) obj).onPause();
                return;
            case 1:
                ((StoriesCategory2Header) obj).onDestroy();
                return;
            case 2:
                ((StoriesCategory2Presenter) obj).handleOrientationChange();
                return;
            case 3:
                ((StoriesCategory2Header) obj).onDataChangedOnUi();
                return;
            case 4:
                ((StoriesCategory2Header) obj).updateDivider();
                return;
            case 5:
                ((StoriesCategory2Presenter) obj).requestPreview();
                return;
            case 6:
                ((StoriesCategory2Header) obj).onResume();
                return;
            case 7:
                ((StoriesCategory2Header) obj).onStop();
                return;
            case 8:
                ((StoriesCategory2Presenter) obj).handleResolutionChange();
                return;
            case 9:
                ((StoriesCategory2Presenter) obj).handleMultiWindowModeChanged();
                return;
            default:
                ((BaseListViewAdapter) obj).startSelect(0);
                return;
        }
    }
}
