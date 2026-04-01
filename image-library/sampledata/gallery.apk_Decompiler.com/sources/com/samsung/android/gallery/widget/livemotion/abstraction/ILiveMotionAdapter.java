package com.samsung.android.gallery.widget.livemotion.abstraction;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ILiveMotionAdapter {
    int getDataPosition(int i2);

    int getFocusDataPosition();

    int getItemCount();

    MediaItem getMediaItem(int i2);

    boolean isDataPrepared();

    boolean isLast(int i2);

    boolean prepareNext(int i2);

    void release();

    void updateCurrentPosition(int i2) {
    }
}
