package com.samsung.android.gallery.module.data;

import android.util.Pair;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ClusterItem {
    int getCount();

    String getDate();

    String getDateRaw();

    Pair<String, String> getDateRawRange() {
        String dateRaw = getDateRaw();
        return new Pair<>(dateRaw, dateRaw);
    }

    long getDateTaken();

    Pair<Long, Long> getDateTimeRange() {
        long dateTaken = getDateTaken();
        return new Pair<>(Long.valueOf(dateTaken), Long.valueOf(dateTaken));
    }

    long getFileSize();

    String getLatitudeList();

    String getLocation();

    String getLongitudeList();

    MediaItem getMediaItem();

    String getTitle();

    boolean isMonth() {
        return false;
    }

    void setMediaItem(MediaItem mediaItem);
}
