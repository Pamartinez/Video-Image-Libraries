package com.samsung.android.gallery.app.ui.list.stories.highlight;

import Ad.C0720a;
import E5.b;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.scsp.media.api.d;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PositionInTrips {
    public static int get(MediaItem mediaItem, MediaData mediaData) {
        if (!(mediaItem == null || mediaData == null)) {
            ArrayList<MediaItem> allData = mediaData.getAllData();
            allData.sort(Comparator.comparingLong(new b(8)).reversed());
            ArrayList arrayList = (ArrayList) ((HashMap) allData.stream().collect(Collectors.groupingBy(new d(17), new C0720a(10), Collectors.toCollection(new C0720a(1))))).get(Integer.valueOf(getStartYear(mediaItem)));
            if (arrayList != null && !arrayList.isEmpty()) {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (MediaItemStory.getStoryId((FileItemInterface) arrayList.get(i2)) == MediaItemStory.getStoryId(mediaItem)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int getStartYear(MediaItem mediaItem) {
        return TimeUtil.getYearInt(MediaItemStory.getStoryStartTime(mediaItem));
    }
}
