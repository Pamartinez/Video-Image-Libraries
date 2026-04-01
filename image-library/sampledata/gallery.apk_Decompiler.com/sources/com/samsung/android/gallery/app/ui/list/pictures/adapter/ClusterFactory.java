package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.ClusterIndexer;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.IMediaDataTable;
import com.samsung.android.gallery.module.dataset.tables.SpecProvider;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import e5.C0451a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClusterFactory {
    private static void createDayCluster(Cluster[] clusterArr, MediaData mediaData, boolean z, boolean z3, SpecProvider specProvider) {
        Cluster cluster;
        Cluster cluster2;
        Cluster cluster3;
        if (mediaData.supportDayCluster()) {
            ClusterIndexer dayClusterIndexer = getDayClusterIndexer(mediaData);
            if (z3) {
                cluster3 = new SpannableCluster(mediaData, specProvider);
            } else {
                cluster3 = new DayMonthCluster(dayClusterIndexer, mediaData.getCount());
            }
            clusterArr[0] = cluster3;
            if (z) {
                if (z3) {
                    cluster3 = new SpannableRealRatioCluster(mediaData, specProvider);
                } else {
                    cluster3 = new RealRatioCluster(dayClusterIndexer, mediaData, specProvider);
                }
            }
            clusterArr[3] = cluster3;
            if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
                clusterArr[0].setAllDates(mediaData.getAllDatesIds(), mediaData.getAllDatesCount());
                clusterArr[3].setAllDates(mediaData.getAllDatesIds(), mediaData.getAllDatesCount());
                return;
            }
            return;
        }
        if (z3) {
            cluster = new SpannableCluster(mediaData, specProvider);
        } else {
            cluster = new NoDividerCluster(mediaData);
        }
        clusterArr[0] = cluster;
        if (!z) {
            cluster2 = new NoDividerCluster(mediaData);
        } else if (z3) {
            cluster2 = new SpannableRealRatioCluster(mediaData, specProvider);
        } else {
            cluster2 = new RealRatioNoDividerCluster(mediaData, specProvider);
        }
        clusterArr[3] = cluster2;
    }

    private static Cluster createMonthCluster(MediaData mediaData, boolean z, SpecProvider specProvider) {
        if (!mediaData.supportMonthCluster()) {
            return null;
        }
        if (z) {
            return new SpannableCluster(mediaData, specProvider);
        }
        return new DayMonthCluster(getMonthClusterIndexer(mediaData), mediaData.getCount());
    }

    private static Cluster createMonthXsCluster(MediaData mediaData, SpecProvider specProvider) {
        if (!mediaData.supportMonthXsCluster()) {
            return null;
        }
        if (mediaData.supportMonthCluster()) {
            return new MonthXsCluster(getMonthClusterIndexer(mediaData), mediaData.getCount(), specProvider);
        }
        return new MonthXsNoDividerCluster(mediaData, specProvider);
    }

    public static Cluster createMonthXsFakeCluster(MediaData mediaData, int i2, int i7) {
        if (!mediaData.supportMonthXsCluster()) {
            return null;
        }
        if (mediaData.supportMonthCluster()) {
            return new MonthXsCluster(getMonthClusterIndexer(mediaData), mediaData.getCount(), i2, i7);
        }
        return new MonthXsNoDividerCluster(mediaData, i2, i7);
    }

    public static Cluster[] createMultipleCluster(MediaData mediaData, boolean z, boolean z3, SpecProvider specProvider) {
        boolean z7 = false;
        Cluster[] clusterArr = {null, null, null, null, null};
        if (z && Features.isEnabled(Features.SUPPORT_REAL_RATIO)) {
            z7 = true;
        }
        if (mediaData != null) {
            Log.i("ClusterFactory", "create {day=" + mediaData.supportDayCluster() + ",month=" + mediaData.supportMonthCluster() + ",real=" + z7 + ",span=" + z3 + "} " + mediaData);
            createDayCluster(clusterArr, mediaData, z7, z3, specProvider);
            clusterArr[1] = createMonthCluster(mediaData, z3, specProvider);
            clusterArr[4] = createMonthXsCluster(mediaData, specProvider);
            clusterArr[2] = createYearCluster(mediaData, specProvider);
        }
        fillDefaultData(mediaData, clusterArr);
        return clusterArr;
    }

    private static Cluster createYearCluster(MediaData mediaData, SpecProvider specProvider) {
        boolean z;
        boolean z3;
        boolean z7;
        if (mediaData.getRealCount() >= 3000 || !mediaData.isFullyLoaded()) {
            z = false;
        } else {
            z = true;
        }
        if (mediaData.getClusterTable(0) != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!mediaData.supportYearCluster()) {
            return null;
        }
        ClusterIndexer yearClusterIndexer = getYearClusterIndexer(mediaData);
        int count = mediaData.getCount();
        if (z3 || z) {
            z7 = true;
        } else {
            z7 = false;
        }
        return new YearQueryCluster(yearClusterIndexer, count, specProvider, z7, mediaData.getDataHash(), new C0451a(10, mediaData));
    }

    private static void fillDefaultData(MediaData mediaData, Cluster[] clusterArr) {
        fillDefaultDayCluster(mediaData, clusterArr);
        fillDefaultMonthCluster(clusterArr);
        fillDefaultRealRatioCluster(clusterArr);
    }

    private static void fillDefaultDayCluster(MediaData mediaData, Cluster[] clusterArr) {
        if (clusterArr[0] == null) {
            clusterArr[0] = new NoDividerCluster(mediaData);
        }
    }

    private static void fillDefaultMonthCluster(Cluster[] clusterArr) {
        if (clusterArr[1] == null) {
            clusterArr[1] = clusterArr[0];
        }
    }

    private static void fillDefaultRealRatioCluster(Cluster[] clusterArr) {
        if (clusterArr[3] == null) {
            clusterArr[3] = clusterArr[0];
        }
    }

    private static ClusterIndexer getDayClusterIndexer(MediaData mediaData) {
        IMediaDataTable clusterTable = mediaData.getClusterTable(0);
        if (clusterTable != null) {
            return clusterTable.getClusterIndexer(mediaData.getCount());
        }
        return mediaData.getClusterIndexer(0);
    }

    private static ClusterIndexer getMonthClusterIndexer(MediaData mediaData) {
        ClusterTable clusterTable = (ClusterTable) mediaData.getClusterTable(0);
        if (clusterTable != null) {
            return clusterTable.getClusterIndexerMonth(mediaData.getCount());
        }
        return mediaData.getClusterIndexer(1);
    }

    private static ClusterIndexer getYearClusterIndexer(MediaData mediaData) {
        ClusterTable clusterTable = (ClusterTable) mediaData.getClusterTable(0);
        if (clusterTable != null) {
            return clusterTable.getClusterIndexerYear(mediaData.getCount());
        }
        return mediaData.getClusterIndexer(2);
    }
}
