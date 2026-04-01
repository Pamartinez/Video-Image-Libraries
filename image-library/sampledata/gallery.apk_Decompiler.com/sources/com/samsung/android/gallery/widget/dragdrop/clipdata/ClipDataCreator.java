package com.samsung.android.gallery.widget.dragdrop.clipdata;

import android.content.ClipData;
import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ClipDataCreator {
    void addExtraDataToClipData(Context context, ArrayList<ClipData.Item> arrayList, MediaItem mediaItem);

    ClipData get(Context context);

    void removeExtraDataFromClipData(ArrayList<ClipData.Item> arrayList, MediaItem mediaItem);
}
