package com.samsung.android.gallery.module.dataset.comparator;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumCountNameComparator extends AlbumNameComparator {
    public AlbumCountNameComparator(boolean z, boolean z3) {
        super(z, z3);
    }

    public int compareFolders(MediaItem mediaItem, MediaItem mediaItem2) {
        int count = mediaItem.getCount();
        int count2 = mediaItem2.getCount();
        if (count == count2) {
            return super.compareFolders(mediaItem, mediaItem2);
        }
        if (this.mIsAscending) {
            if (count > count2) {
                return 1;
            }
            return -1;
        } else if (count < count2) {
            return 1;
        } else {
            return -1;
        }
    }

    public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
        int compareBuckets = compareBuckets(mediaItem, mediaItem2);
        if (compareBuckets != 0) {
            return compareBuckets;
        }
        int count = mediaItem.getCount();
        int count2 = mediaItem2.getCount();
        if (count != count2) {
            return this.mIsAscending ? count > count2 ? 1 : -1 : count < count2 ? 1 : -1;
        }
        String displayName = mediaItem.getDisplayName();
        String displayName2 = mediaItem2.getDisplayName();
        if (displayName == null || !displayName.equalsIgnoreCase(displayName2)) {
            return compare(displayName, displayName2);
        }
        return compareWithSameWord(mediaItem, mediaItem2);
    }
}
