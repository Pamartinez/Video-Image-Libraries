package com.samsung.android.gallery.database.dal.abstraction;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SuggestedApi {
    Cursor getCleanOutDuplicatedCoverCursor();

    Cursor getCleanOutDuplicatedCursor();

    Cursor getCleanOutSuggestedCoverCursor(boolean z, int i2);

    Cursor getCleanOutSuggestedCursor(int i2);

    Cursor getPortraitCoverCursor();

    Cursor getPortraitCursor();
}
