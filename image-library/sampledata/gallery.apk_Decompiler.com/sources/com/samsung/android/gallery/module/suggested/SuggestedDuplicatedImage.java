package com.samsung.android.gallery.module.suggested;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestedDuplicatedImage implements SuggestedInterface {
    public Cursor getCleanOutContentsCoverCursor() {
        return DbCompat.suggestedApi().getCleanOutDuplicatedCoverCursor();
    }

    public SuggestedUpdateType getType() {
        return SuggestedUpdateType.TYPE_DUPLICATED;
    }
}
