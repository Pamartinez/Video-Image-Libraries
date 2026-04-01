package com.samsung.android.gallery.app.ui.list.search.pictures;

import B5.e;
import com.samsung.android.gallery.module.data.ClusterItem;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.Comparator;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExpansionHelper {
    private final Map<String, ExpandedInfo> mExpandedMap = new TreeMap(Comparator.reverseOrder());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExpandedInfo {
        private int expandedCount;
        private String ids;
        private int originCount;

        public /* synthetic */ ExpandedInfo(int i2) {
            this();
        }

        public int getExpandedCount() {
            return this.expandedCount;
        }

        public int getOriginCount() {
            return this.originCount;
        }

        public ExpandedInfo setExpandedCount(int i2) {
            this.expandedCount = i2;
            return this;
        }

        public ExpandedInfo setIds(String str) {
            this.ids = str;
            return this;
        }

        public ExpandedInfo setOriginCount(int i2) {
            this.originCount = i2;
            return this;
        }

        private ExpandedInfo() {
        }
    }

    private boolean isExpandedInfoValid(ClusterItem clusterItem, int i2) {
        ExpandedInfo expandedInfo = this.mExpandedMap.get(clusterItem.getDateRaw());
        if (expandedInfo == null || i2 <= expandedInfo.getOriginCount()) {
            return false;
        }
        return true;
    }

    public void clearExpansionInfo() {
        this.mExpandedMap.clear();
    }

    public int getAddedCount() {
        AtomicInteger atomicInteger = new AtomicInteger();
        this.mExpandedMap.values().forEach(new a(atomicInteger));
        return atomicInteger.get();
    }

    public String getAllExpandedDates() {
        StringJoiner stringJoiner = new StringJoiner(NumericEnum.SEP);
        this.mExpandedMap.keySet().forEach(new e(stringJoiner, 2));
        return stringJoiner.toString();
    }

    public boolean isExpanded(ClusterItem clusterItem) {
        return this.mExpandedMap.containsKey(clusterItem.getDateRaw());
    }

    public void onExpandClicked(ClusterItem clusterItem, String str, Integer num) {
        if (str != null && num != null) {
            String dateRaw = clusterItem.getDateRaw();
            if (isExpanded(clusterItem)) {
                this.mExpandedMap.remove(dateRaw);
            } else {
                this.mExpandedMap.put(dateRaw, new ExpandedInfo(0).setIds(str).setExpandedCount(num.intValue()).setOriginCount(clusterItem.getCount()));
            }
        }
    }

    public boolean supportExpand(ClusterItem clusterItem, Integer num) {
        if (num == null) {
            return false;
        }
        boolean isExpanded = isExpanded(clusterItem);
        boolean isExpandedInfoValid = isExpandedInfoValid(clusterItem, num.intValue());
        if (isExpanded && !isExpandedInfoValid) {
            this.mExpandedMap.remove(clusterItem.getDateRaw());
        }
        if (!isExpanded || !isExpandedInfoValid) {
            return false;
        }
        return true;
    }
}
