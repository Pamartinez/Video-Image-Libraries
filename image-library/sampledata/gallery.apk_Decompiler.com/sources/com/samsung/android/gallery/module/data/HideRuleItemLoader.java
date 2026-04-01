package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import android.database.StaleDataException;
import com.samsung.android.gallery.module.data.CursorHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class HideRuleItemLoader {
    public static MediaItem buildHideRuleMediaItem(Cursor cursor, int i2) {
        MediaItem mediaItem;
        CursorHolder cursorHolder;
        if (i2 == 1) {
            mediaItem = MediaItemBuilder.build(cursor);
            cursorHolder = MediaItemBuilder.getHolder(cursor);
        } else {
            mediaItem = new MediaItem();
            cursorHolder = CursorHolder.getCursorHolder(cursor, CursorHolder.CursorType.STORY_HIDE_RULE);
        }
        HideRuleData.of(mediaItem).parse(cursorHolder);
        return mediaItem;
    }

    public static MediaItem loadHideRuleMediaItem(Cursor cursor, int i2) {
        MediaItem buildHideRuleMediaItem;
        synchronized (cursor) {
            try {
                buildHideRuleMediaItem = buildHideRuleMediaItem(cursor, i2);
            } catch (StaleDataException | IllegalStateException e) {
                MediaItemLoader.debugStaleDataException(e, cursor);
                throw e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return buildHideRuleMediaItem;
    }
}
