package com.samsung.android.gallery.module.creature.base;

import K.a;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.mp.helper.CreatureApi;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.search.CreatureIndexingBuilder;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.search.SearchIndexListener;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CreatureIndexingDelegate {
    protected static final boolean SUPPORT_UNIFIED_CREATURE_KEY = Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY);
    protected final SearchIndexListener mIndexListener = new a(29);

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(int i2) {
        if (i2 > 0) {
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(8006));
        }
    }

    public abstract void execute(CreatureIndexingBuilder creatureIndexingBuilder, ArrayList<Long> arrayList, int i2, IntelligentSearchIndex.IndexMode indexMode);

    public abstract CreatureApi getCreatureApi();

    public ArrayList<Long> getMediaIdList(long j2) {
        ArrayList<Long> arrayList = new ArrayList<>();
        if (j2 != 1) {
            Cursor mediaIdListCursor = getCreatureApi().getMediaIdListCursor(j2);
            if (mediaIdListCursor != null) {
                try {
                    if (mediaIdListCursor.moveToFirst()) {
                        int columnIndex = mediaIdListCursor.getColumnIndex("__absID");
                        do {
                            arrayList.add(Long.valueOf(mediaIdListCursor.getLong(columnIndex)));
                        } while (mediaIdListCursor.moveToNext());
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (mediaIdListCursor != null) {
                mediaIdListCursor.close();
            }
        }
        return arrayList;
        throw th;
    }

    public ArrayList<Long> getMediaIdListByGroupId(long j2) {
        ArrayList<Long> arrayList = new ArrayList<>();
        Cursor mediaIdListCursorByGroupIds = getCreatureApi().getMediaIdListCursorByGroupIds(Collections.singletonList(Long.valueOf(j2)));
        if (mediaIdListCursorByGroupIds != null) {
            try {
                if (mediaIdListCursorByGroupIds.moveToFirst()) {
                    int columnIndex = mediaIdListCursorByGroupIds.getColumnIndex("__absID");
                    do {
                        arrayList.add(Long.valueOf(mediaIdListCursorByGroupIds.getLong(columnIndex)));
                    } while (mediaIdListCursorByGroupIds.moveToNext());
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (mediaIdListCursorByGroupIds != null) {
            mediaIdListCursorByGroupIds.close();
        }
        return arrayList;
        throw th;
    }

    public void index(CreatureIndexingBuilder creatureIndexingBuilder) {
        ArrayList<Long> arrayList;
        if (Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH) && creatureIndexingBuilder.anyChanged()) {
            int removeIndex = creatureIndexingBuilder.getRemoveIndex();
            int appendIndex = creatureIndexingBuilder.getAppendIndex();
            if (creatureIndexingBuilder.getFileId() != 0) {
                arrayList = new ArrayList<>(Collections.singletonList(Long.valueOf(creatureIndexingBuilder.getFileId())));
            } else if (creatureIndexingBuilder.getTargetGroupId() != 0) {
                arrayList = getMediaIdListByGroupId(creatureIndexingBuilder.getTargetGroupId());
            } else {
                arrayList = getMediaIdList(creatureIndexingBuilder.getTargetTagId());
            }
            Log.i("CreatureIdxDelegate", "file ids size = " + arrayList.size());
            if (removeIndex != 0) {
                execute(creatureIndexingBuilder, arrayList, removeIndex, IntelligentSearchIndex.IndexMode.REMOVE);
            }
            if (appendIndex != 0) {
                execute(creatureIndexingBuilder, arrayList, appendIndex, IntelligentSearchIndex.IndexMode.APPEND);
            }
        }
    }
}
