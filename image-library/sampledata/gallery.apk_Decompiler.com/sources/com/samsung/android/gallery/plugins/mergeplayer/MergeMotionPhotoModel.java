package com.samsung.android.gallery.plugins.mergeplayer;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.sum.core.Def;
import java.io.Closeable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeMotionPhotoModel implements Closeable {
    private int mCurrentPosition = 0;
    private MediaData mMediaData;
    private MediaItem mMediaItem;
    private final ArrayList<PlayerInstance> mPlayerList = new ArrayList<>();
    private final ArrayList<MediaItem> mSubItemList = new ArrayList<>();

    private void makeList(int i2, MediaItem mediaItem) {
        MediaItem read;
        int i7 = i2 - 1;
        MediaItem mediaItem2 = mediaItem;
        while (i7 >= 0 && (read = this.mMediaData.read(i7)) != null && read.isMotionPhoto() && read.getDateTaken() - mediaItem2.getDateTaken() <= Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS) {
            this.mSubItemList.add(read);
            i7--;
            mediaItem2 = read;
        }
        int count = this.mMediaData.getCount();
        int i8 = i2 + 1;
        while (i8 < count) {
            MediaItem read2 = this.mMediaData.read(i8);
            if (read2 != null && read2.isMotionPhoto() && mediaItem.getDateTaken() - read2.getDateTaken() <= Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS) {
                this.mSubItemList.add(0, read2);
                i8++;
                mediaItem = read2;
            } else {
                return;
            }
        }
    }

    public void addPlayer(PlayerInstance playerInstance) {
        this.mPlayerList.add(playerInstance);
    }

    public void close() {
        this.mCurrentPosition = 0;
        this.mMediaData = null;
        this.mMediaItem = null;
        this.mSubItemList.clear();
        this.mPlayerList.clear();
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }

    public PlayerInstance getPlayer(int i2) {
        return this.mPlayerList.get(i2);
    }

    public ArrayList<PlayerInstance> getPlayerList() {
        return this.mPlayerList;
    }

    public MediaItem getSubItem(int i2) {
        return this.mSubItemList.get(i2);
    }

    public int getSubItemCount() {
        return this.mSubItemList.size();
    }

    public void setCurrentPosition(int i2) {
        this.mCurrentPosition = i2;
    }

    public void setMediaData(MediaData mediaData, MediaItem mediaItem) {
        this.mCurrentPosition = 0;
        this.mMediaData = mediaData;
        this.mMediaItem = mediaItem;
        if (mediaData != null) {
            this.mPlayerList.clear();
            this.mSubItemList.clear();
            this.mSubItemList.add(this.mMediaItem);
            int findPositionByFileId = this.mMediaData.findPositionByFileId(this.mMediaItem.getFileId());
            if (findPositionByFileId >= 0) {
                makeList(findPositionByFileId, this.mMediaItem);
            }
        }
    }
}
