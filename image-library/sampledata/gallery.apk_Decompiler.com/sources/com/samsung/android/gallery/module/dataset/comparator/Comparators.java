package com.samsung.android.gallery.module.dataset.comparator;

import Pa.a;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Comparators {
    private static final ConcurrentHashMap<String, IAlbumComparator> sFactory = new ConcurrentHashMap<>();

    /* access modifiers changed from: private */
    public static IAlbumComparator createComparator(String str, int i2) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1878330214:
                if (str.equals("location://search/fileList/KeywordAlbums")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1734318230:
                if (str.equals("location://albums/choice/root")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1394219096:
                if (str.equals("location://albums/hide")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1393988146:
                if (str.equals("location://albums/pane")) {
                    c5 = 3;
                    break;
                }
                break;
            case -440239236:
                if (str.equals("location://sharing/albums")) {
                    c5 = 4;
                    break;
                }
                break;
            case -125579287:
                if (str.equals("location://albums")) {
                    c5 = 5;
                    break;
                }
                break;
            case 321300043:
                if (str.equals("location://albums/manage")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1344752317:
                if (str.equals("location://folder/choice")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1894681211:
                if (str.equals("location://albums/all")) {
                    c5 = 8;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return getComparatorCompat(50, PreferenceFeatures.OneUi5x.MX_ALBUMS, false);
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return getComparatorCompat(i2, PreferenceFeatures.OneUi5x.MX_ALBUMS, false);
            case 2:
                return getComparatorBasedOnSortBy(31, true);
            default:
                if (LocationKey.isFolder(str)) {
                    return getComparatorBasedOnSortBy(i2, false);
                }
                throw new AssertionError("Wrong location key access");
        }
    }

    public static IAlbumComparator getComparator(String str) {
        return getComparator(str, SortByType.getAlbumsOrder());
    }

    private static IAlbumComparator getComparatorBasedOnSortBy(int i2, boolean z) {
        return getComparatorCompat(i2, z, z);
    }

    private static IAlbumComparator getComparatorCompat(int i2, boolean z, boolean z3) {
        int sortBy = SortByType.getSortBy(i2);
        boolean z7 = false;
        if (sortBy == 20) {
            if (SortByType.getOrderBy(i2) == 1) {
                z7 = true;
            }
            return new AlbumDateModifiedComparator(z7, z);
        } else if (sortBy == 30) {
            if (SortByType.getOrderBy(i2) == 1) {
                z7 = true;
            }
            return new AlbumNameComparator(z7, z);
        } else if (sortBy != 50) {
            return new AlbumDefaultComparator(z3);
        } else {
            if (SortByType.getOrderBy(i2) == 1) {
                z7 = true;
            }
            return new AlbumCountNameComparator(z7, z);
        }
    }

    public static IAlbumComparator getComparatorForSharings(String str) {
        return getComparator(str, SortByType.getSharingOrder());
    }

    public static IAlbumComparator getComparator(String str, int i2) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        return sFactory.computeIfAbsent(removeArgs + i2, new a(removeArgs, i2, 1)).setCollator();
    }
}
