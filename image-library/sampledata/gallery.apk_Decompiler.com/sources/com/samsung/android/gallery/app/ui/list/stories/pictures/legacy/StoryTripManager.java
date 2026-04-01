package com.samsung.android.gallery.app.ui.list.stories.pictures.legacy;

import A.a;
import A4.C0384t;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.IMediaDataTable;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Calendar;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryTripManager {
    private void clearStoryTripDay(IMediaDataTable iMediaDataTable, int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            MediaItem mediaItem = iMediaDataTable.get(i7);
            if (mediaItem != null) {
                MediaItemStory.setStoryTripDay(mediaItem, 0);
            }
        }
    }

    private int getNextStoryTripDay(long j2, long j3) {
        return ((int) Math.floor(((double) Math.abs(j2 - j3)) / 8.64E7d)) + 1;
    }

    private long getRefStoryTripDate(long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j2);
        instance.set(instance.get(1), instance.get(2), instance.get(5), 0, 0);
        return instance.getTimeInMillis();
    }

    public static boolean isStoryTrip(MediaData mediaData, int i2) {
        try {
            if (i2 != StoryType.TRIP.getValue()) {
                if (i2 == StoryType.DOMESTIC_TRIP.getValue()) {
                }
                return false;
            }
            if (((Boolean) Optional.ofNullable(mediaData.getClusterTable(0)).map(new C0384t(24)).orElse(Boolean.FALSE)).booleanValue()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("isTripStory failed e="), "StoryTripManager");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isStoryTrip$0(IMediaDataTable iMediaDataTable) {
        boolean z = true;
        if (iMediaDataTable.getLoadedCount() <= 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private boolean supportOnlyContinuousTripDay() {
        return true;
    }

    public void makeStoryTripDay(MediaData mediaData) {
        IMediaDataTable clusterTable = mediaData.getClusterTable(0);
        if (clusterTable != null) {
            MediaItem mediaItem = clusterTable.get(0);
            if (mediaItem == null) {
                Log.e("StoryTripManager", "fail makeStoryTripDay");
                return;
            }
            MediaItemStory.setStoryTripDay(mediaItem, 1);
            long refStoryTripDate = getRefStoryTripDate(mediaItem.getDateTaken());
            int loadedCount = clusterTable.getLoadedCount();
            int i2 = 1;
            while (i2 < loadedCount) {
                MediaItem mediaItem2 = clusterTable.get(i2);
                if (mediaItem2 == null) {
                    a.B(i2, "fail makeStoryTripDay : ", "StoryTripManager");
                    return;
                }
                int nextStoryTripDay = getNextStoryTripDay(mediaItem2.getDateTaken(), refStoryTripDate);
                if (!supportOnlyContinuousTripDay() || Math.abs(nextStoryTripDay - MediaItemStory.getStoryTripDay(mediaItem)) <= 1) {
                    MediaItemStory.setStoryTripDay(mediaItem2, nextStoryTripDay);
                    i2++;
                    mediaItem = mediaItem2;
                } else {
                    clearStoryTripDay(clusterTable, i2);
                    return;
                }
            }
        }
    }
}
