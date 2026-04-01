package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.ICreaturePickerDialogView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMergeCreatureDialogView extends ICreaturePickerDialogView {
    void bindHeaderImage(Bitmap bitmap);

    MediaItem getHeaderItem();

    void onDestroyDialog();
}
