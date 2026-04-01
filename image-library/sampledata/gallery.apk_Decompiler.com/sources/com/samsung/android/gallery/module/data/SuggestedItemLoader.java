package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import android.database.StaleDataException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SuggestedItemLoader {
    public static MediaItem loadSuggestedMediaItem(Cursor cursor) {
        MediaItem buildSuggestions;
        synchronized (cursor) {
            try {
                buildSuggestions = MediaItemBuilderDelegate.buildSuggestions(cursor);
            } catch (StaleDataException | IllegalStateException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return buildSuggestions;
    }
}
