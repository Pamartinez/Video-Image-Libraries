package com.samsung.android.gallery.app.ui.dialog.tag;

import com.samsung.android.gallery.app.ui.dialog.abstraction.IMvpDialogView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IAddTagDialogView extends IMvpDialogView {
    String getInputText();

    void onItemClicked(String str);

    void updateTagName(String str);
}
