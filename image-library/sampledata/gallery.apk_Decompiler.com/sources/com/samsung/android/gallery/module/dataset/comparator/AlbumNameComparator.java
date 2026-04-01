package com.samsung.android.gallery.module.dataset.comparator;

import V8.a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.math.BigInteger;
import java.text.Collator;
import java.text.Normalizer;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumNameComparator implements IAlbumComparator {
    private static final ConcurrentHashMap<Locale, Collator> sCollatorFactory = new ConcurrentHashMap<>();
    private Collator mCollator = sCollatorFactory.computeIfAbsent(Locale.getDefault(), new a(13));
    final boolean mIsAscending;
    final boolean mShowSystemFoldersTop;

    public AlbumNameComparator(boolean z, boolean z3) {
        this.mIsAscending = z;
        this.mShowSystemFoldersTop = z3;
    }

    private int compareWithLocale(String str, String str2) {
        return this.mCollator.compare(str, str2);
    }

    private int compareWithNumberAware(String str, String str2) {
        int i2;
        int length = str.length();
        int length2 = str2.length();
        int i7 = 0;
        int i8 = 0;
        while (i7 < length && i8 < length2) {
            String chunk = getChunk(str, length, i7);
            int length3 = chunk.length();
            i7 += length3;
            String chunk2 = getChunk(str2, length2, i8);
            int length4 = chunk2.length();
            i8 += length4;
            if (!isDigit(chunk.charAt(0)) || !isDigit(chunk2.charAt(0))) {
                i2 = compareWithLocale(chunk, chunk2);
                continue;
            } else {
                i2 = length3 - length4;
                if (i2 == 0) {
                    i2 = new BigInteger(chunk).compareTo(new BigInteger(chunk2));
                    continue;
                } else {
                    continue;
                }
            }
            if (i2 != 0) {
                return i2;
            }
        }
        return length - length2;
    }

    private int getBucketIndex(int i2) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return BucketUtils.indexEx(i2);
        }
        return BucketUtils.index(i2);
    }

    private String getChunk(String str, int i2, int i7) {
        StringBuilder sb2 = new StringBuilder();
        char charAt = str.charAt(i7);
        sb2.append(charAt);
        int i8 = i7 + 1;
        if (isDigit(charAt)) {
            while (i8 < i2) {
                char charAt2 = str.charAt(i8);
                if (!isDigit(charAt2)) {
                    break;
                }
                sb2.append(charAt2);
                i8++;
            }
        } else {
            while (i8 < i2) {
                char charAt3 = str.charAt(i8);
                if (isDigit(charAt3)) {
                    break;
                }
                sb2.append(charAt3);
                i8++;
            }
        }
        return sb2.toString();
    }

    private boolean isDigit(char c5) {
        if (c5 < '0' || c5 > '9') {
            return false;
        }
        return true;
    }

    public int compareBuckets(MediaItem mediaItem, MediaItem mediaItem2) {
        int bucketIndex;
        int bucketIndex2;
        if (this.mShowSystemFoldersTop && (bucketIndex = getBucketIndex(mediaItem.getAlbumID())) != (bucketIndex2 = getBucketIndex(mediaItem2.getAlbumID()))) {
            return Integer.compare(bucketIndex, bucketIndex2);
        }
        boolean isCameras = BucketUtils.isCameras(mediaItem.getAlbumID());
        boolean isCameras2 = BucketUtils.isCameras(mediaItem2.getAlbumID());
        if (!isCameras && !isCameras2) {
            boolean isFolder = mediaItem.isFolder();
            boolean isFolder2 = mediaItem2.isFolder();
            if (!isFolder && !isFolder2) {
                return 0;
            }
            if (isFolder && isFolder2) {
                return compareFolders(mediaItem, mediaItem2);
            }
            if (isFolder) {
                return -1;
            }
            return 1;
        } else if (!isCameras || !isCameras2) {
            if (isCameras) {
                return -1;
            }
            return 1;
        } else if (BucketUtils.isCamera(mediaItem.getAlbumID())) {
            return -1;
        } else {
            return 1;
        }
    }

    public int compareFolders(MediaItem mediaItem, MediaItem mediaItem2) {
        return compare(mediaItem.getFolderName(), mediaItem2.getFolderName());
    }

    public int compareWithSameWord(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem.getAlbumOrder() == 0 && mediaItem2.getAlbumOrder() == 0) {
            if (mediaItem.getAlbumID() < mediaItem2.getAlbumID()) {
                return 1;
            }
            return -1;
        } else if (mediaItem.getAlbumOrder() < mediaItem2.getAlbumOrder()) {
            return 1;
        } else {
            return -1;
        }
    }

    public IAlbumComparator setCollator() {
        this.mCollator = sCollatorFactory.computeIfAbsent(Locale.getDefault(), new a(13));
        return this;
    }

    public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
        int compareBuckets = compareBuckets(mediaItem, mediaItem2);
        if (compareBuckets != 0) {
            return compareBuckets;
        }
        String displayName = mediaItem.getDisplayName();
        String displayName2 = mediaItem2.getDisplayName();
        if (displayName == null || !displayName.equalsIgnoreCase(displayName2)) {
            return compare(displayName, displayName2);
        }
        return compareWithSameWord(mediaItem, mediaItem2);
    }

    public int compare(String str, String str2) {
        if (str == null || str2 == null) {
            return 0;
        }
        String upperCase = str.toUpperCase();
        String upperCase2 = str2.toUpperCase();
        Normalizer.Form form = Normalizer.Form.NFD;
        String normalize = Normalizer.normalize(upperCase, form);
        String normalize2 = Normalizer.normalize(upperCase2, form);
        try {
            return this.mIsAscending ? compareWithNumberAware(normalize, normalize2) : compareWithNumberAware(normalize2, normalize);
        } catch (Exception unused) {
            return this.mIsAscending ? compareWithLocale(normalize, normalize2) : compareWithLocale(normalize2, normalize);
        }
    }
}
