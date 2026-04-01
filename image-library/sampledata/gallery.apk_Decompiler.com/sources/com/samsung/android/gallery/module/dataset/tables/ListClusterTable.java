package com.samsung.android.gallery.module.dataset.tables;

import A5.a;
import Ad.C0720a;
import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListClusterTable extends ClusterTable {
    private final ArrayList<MediaItem> mItems;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClusterInfo {
        private int count;
        private String dateRaw;
        private long dateTaken;
        private long lastDateMillis;
        private final ArrayList<String> latitudes;
        private final ArrayList<String> locations;
        private final ArrayList<String> longitudes;

        public /* synthetic */ ClusterInfo(MediaItem mediaItem, int i2) {
            this(mediaItem);
        }

        /* access modifiers changed from: private */
        public void addInfo(MediaItem mediaItem) {
            if (mediaItem.getLocation() != null) {
                if (!this.locations.contains(mediaItem.getLocation())) {
                    this.locations.add(mediaItem.getLocation());
                }
                this.latitudes.add(String.valueOf(mediaItem.getLatitude()));
                this.longitudes.add(String.valueOf(mediaItem.getLongitude()));
            }
            this.count++;
        }

        private void clear() {
            this.locations.clear();
            this.latitudes.clear();
            this.longitudes.clear();
            this.count = 0;
        }

        /* access modifiers changed from: private */
        public MediaItem createItem() {
            return MediaItemBuilder.createListClusterItem(this.dateTaken, this.dateRaw, this.count, this.locations, String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.latitudes), String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.longitudes));
        }

        private long getLastDateMillis() {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(this.dateTaken);
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis();
        }

        /* access modifiers changed from: private */
        public boolean isDateChanged(long j2) {
            long j3 = this.lastDateMillis;
            if (j2 < j3 || j2 - j3 >= MediaApiContract.DAY_IN_MILLI) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void onDateChanged(MediaItem mediaItem) {
            clear();
            updateDate(mediaItem);
            addInfo(mediaItem);
        }

        private void updateDate(MediaItem mediaItem) {
            long dateTaken2 = mediaItem.getDateTaken();
            this.dateTaken = dateTaken2;
            this.dateRaw = TimeUtil.getIsoLocalDate(dateTaken2);
            this.lastDateMillis = getLastDateMillis();
        }

        private ClusterInfo(MediaItem mediaItem) {
            this.latitudes = new ArrayList<>();
            this.longitudes = new ArrayList<>();
            this.locations = new ArrayList<>();
            updateDate(mediaItem);
            addInfo(mediaItem);
        }
    }

    public ListClusterTable(Cursor cursor, ArrayList<MediaItem> arrayList) {
        super(cursor);
        this.mItems = arrayList;
    }

    public MediaItem createMediaItem(Cursor cursor, int i2) {
        return new MediaItem();
    }

    public boolean init() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mItems.size() < 1) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        ClusterInfo clusterInfo = new ClusterInfo(this.mItems.get(0), 0);
        for (int i2 = 1; i2 < this.mItems.size(); i2++) {
            MediaItem mediaItem = this.mItems.get(i2);
            if (clusterInfo.isDateChanged(mediaItem.getDateTaken())) {
                arrayList.add(clusterInfo.createItem());
                clusterInfo.onDateChanged(mediaItem);
            } else {
                clusterInfo.addInfo(mediaItem);
            }
        }
        arrayList.add(clusterInfo.createItem());
        this.mDataList = (ArrayList) arrayList.stream().map(new a(27, this)).collect(Collectors.toCollection(new C0720a(1)));
        int size = arrayList.size();
        this.mRealCount = size;
        this.mCount = size;
        Log.d(this.TAG, "init items {" + this.mRealCount + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        return true;
    }

    public String tag() {
        return "ListClusterTable";
    }

    public void onConstruct(Cursor cursor) {
    }
}
