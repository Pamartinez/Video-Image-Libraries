package com.samsung.android.gallery.app.ui.container.clipboard;

import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClipboardView extends IMvpBaseView {
    RecyclerView getClipboardView();

    boolean isOpened() {
        return false;
    }

    void setClipboardViewVisibility(int i2);

    void setScreenId(String str);

    void updateClipboardBackground(boolean z) {
    }

    void operateClipboard(boolean z, boolean z3) {
    }
}
