package com.samsung.android.gallery.widget.dragdrop.clipdata;

import android.content.ClipData;
import android.content.Context;
import android.os.PersistableBundle;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipDataCreatorLocalImpl implements ClipDataCreator {
    private PersistableBundle disableCTW() {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putBoolean("com.samsung.android.content.clipdescription.extra.IGNORE_LEFT_EDGE", true);
        persistableBundle.putBoolean("com.samsung.android.content.clipdescription.extra.IGNORE_RIGHT_EDGE", true);
        return persistableBundle;
    }

    public ClipData get(Context context) {
        ClipData newPlainText = ClipData.newPlainText("galleryUri", "Skip making a uri list");
        newPlainText.getDescription().setExtras(disableCTW());
        return newPlainText;
    }

    public void removeExtraDataFromClipData(ArrayList<ClipData.Item> arrayList, MediaItem mediaItem) {
    }

    public void addExtraDataToClipData(Context context, ArrayList<ClipData.Item> arrayList, MediaItem mediaItem) {
    }
}
