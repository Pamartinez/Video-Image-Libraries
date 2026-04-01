package com.samsung.android.gallery.module.data;

import Fa.C0571z;
import Gb.a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StoryCategoryType;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinData {
    private ArrayList<MediaItem> data = new ArrayList<>();
    private final DividerItem dividerItem = new DividerItem(-1);
    private ConcurrentHashMap<Integer, Integer> yearIndex;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DividerItem extends MediaItem {
        public int position;

        public DividerItem(int i2) {
            this.position = i2;
        }

        public int getAlbumID() {
            return -100;
        }

        public String getTitle() {
            return "DividerItem";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Favorite extends PinData {
        public /* synthetic */ Favorite(int i2) {
            this();
        }

        public void append(MediaItem mediaItem) {
            if (MediaItemStory.getStoryFavorite(mediaItem) > 0) {
                this.items.add(mediaItem);
            }
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.Comparator] */
        public PinData build(List<MediaItem> list) {
            super.build(list);
            this.items.sort(new Object());
            return this;
        }

        private Favorite() {
            super(0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class PinData {
        protected final List<MediaItem> items;

        public /* synthetic */ PinData(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public List<MediaItem> getItems() {
            return this.items;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$build$0(List list) {
            list.forEach(new n(this, 1));
        }

        /* access modifiers changed from: private */
        public int size() {
            return this.items.size();
        }

        public abstract void append(MediaItem mediaItem);

        public PinData build(List<MediaItem> list) {
            Optional.ofNullable(list).ifPresent(new n(this, 0));
            return this;
        }

        public ConcurrentHashMap<Integer, Integer> getYearIndex() {
            return null;
        }

        private PinData() {
            this.items = new ArrayList();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Reminder extends PinData {
        List<Integer> exclude;
        private final ConcurrentHashMap<Integer, MediaItem> nYearMap;

        public /* synthetic */ Reminder(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$getYearIndex$0(ConcurrentHashMap concurrentHashMap, Integer num) {
            MediaItem mediaItem = this.nYearMap.get(num);
            Objects.requireNonNull(mediaItem);
            concurrentHashMap.put(Integer.valueOf(mediaItem.getAlbumID()), num);
        }

        private MediaItem preferred(MediaItem mediaItem, MediaItem mediaItem2) {
            if (MediaItemStory.getStoryScoring(mediaItem) != MediaItemStory.getStoryScoring(mediaItem2) ? MediaItemStory.getStoryScoring(mediaItem) <= MediaItemStory.getStoryScoring(mediaItem2) : MediaItemStory.getStoryStartTime(mediaItem) >= MediaItemStory.getStoryStartTime(mediaItem2)) {
                return mediaItem2;
            }
            return mediaItem;
        }

        private boolean supported(MediaItem mediaItem) {
            StoryCategoryType category = StoryType.getCategory(MediaItemStory.getStoryType(mediaItem));
            if (StoryCategoryType.SPECIAL_DAY.equals(category) || StoryCategoryType.TRIP.equals(category) || StoryCategoryType.GROWTH.equals(category) || StoryCategoryType.BEST_MOMENT.equals(category)) {
                return true;
            }
            return false;
        }

        public void append(MediaItem mediaItem) {
            int yearAgo;
            if (supported(mediaItem)) {
                List<Integer> list = this.exclude;
                if ((list == null || !list.contains(Integer.valueOf(mediaItem.getAlbumID()))) && (yearAgo = ReminderDate.getYearAgo((FileItemInterface) mediaItem)) > -1) {
                    this.nYearMap.put(Integer.valueOf(yearAgo), preferred(mediaItem, this.nYearMap.getOrDefault(Integer.valueOf(yearAgo), mediaItem)));
                }
            }
        }

        public PinData build(List<MediaItem> list) {
            super.build(list);
            this.items.addAll(this.nYearMap.values());
            return this;
        }

        public PinData exclude(List<MediaItem> list) {
            if (list != null && !list.isEmpty()) {
                this.exclude = (List) list.stream().filter(new C0571z(4)).map(new a(7)).collect(Collectors.toList());
            }
            return this;
        }

        public ConcurrentHashMap<Integer, Integer> getYearIndex() {
            ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
            this.nYearMap.keySet().forEach(new o(0, this, concurrentHashMap));
            return concurrentHashMap;
        }

        private Reminder() {
            super(0);
            this.nYearMap = new ConcurrentHashMap<>();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ReminderDate {
        public static final ConcurrentHashMap<Integer, long[]> sDateBoundary = new ConcurrentHashMap<>();
        private static Long sTodayDate;

        public static int getYearAgo(FileItemInterface fileItemInterface) {
            return getYearAgo(MediaItemStory.getStoryStartTime(fileItemInterface));
        }

        private static boolean isDayDiffered(long j2) {
            Long l = sTodayDate;
            if (l == null || l.longValue() != j2) {
                return true;
            }
            return false;
        }

        private static void setDateBoundary() {
            long j2 = TimeUtil.todayInMillis();
            if (isDayDiffered(j2)) {
                Long valueOf = Long.valueOf(j2);
                sTodayDate = valueOf;
                long longValue = valueOf.longValue() + 86399999;
                for (int i2 = 1; i2 <= 10; i2++) {
                    long[] yearsAgo = TimeUtil.getYearsAgo(longValue, i2);
                    yearsAgo[0] = yearsAgo[0] - 604800000;
                    sDateBoundary.put(Integer.valueOf(i2), yearsAgo);
                }
            }
        }

        public static int getYearAgo(long j2) {
            setDateBoundary();
            int i2 = 1;
            for (long[] next : sDateBoundary.values()) {
                if (next[0] <= j2 && next[1] >= j2) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Transitory extends PinData {
        public /* synthetic */ Transitory(int i2) {
            this();
        }

        public void append(MediaItem mediaItem) {
            if (StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem))) {
                this.items.add(mediaItem);
            }
        }

        private Transitory() {
            super(0);
        }
    }

    public StoriesPinData(List<MediaItem> list) {
        buildData(list);
    }

    private void buildData(List<MediaItem> list) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        this.data = arrayList;
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            arrayList.addAll(new Transitory(0).build(list).getItems());
            return;
        }
        PinData build = new Favorite(0).build(list);
        PinData build2 = new Reminder(0).exclude(build.getItems()).build(list);
        this.yearIndex = build2.getYearIndex();
        if (!reminderDisplayable(build2.size())) {
            this.data.addAll(build.getItems());
            return;
        }
        this.data.addAll(build2.getItems());
        if (build.size() > 0) {
            this.dividerItem.position = this.data.size();
            this.data.add(this.dividerItem);
            this.data.addAll(build.getItems());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$trimData$0(List list, Integer num) {
        if (!list.contains(num)) {
            this.yearIndex.remove(num);
        }
    }

    private boolean reminderDisplayable(int i2) {
        if (1 <= i2) {
            return true;
        }
        return false;
    }

    public ArrayList<MediaItem> getData() {
        return this.data;
    }

    public int getDividerPos() {
        return this.dividerItem.position;
    }

    public boolean isDivider(MediaItem mediaItem) {
        return mediaItem instanceof DividerItem;
    }

    public int size() {
        ArrayList<MediaItem> arrayList = this.data;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void trimData(int i2) {
        if (this.data != null) {
            ArrayList<MediaItem> arrayList = this.data;
            ArrayList<MediaItem> arrayList2 = new ArrayList<>(arrayList.subList(0, Math.min(arrayList.size(), i2)));
            this.data = arrayList2;
            List list = (List) arrayList2.stream().map(new a(7)).collect(Collectors.toList());
            ConcurrentHashMap<Integer, Integer> concurrentHashMap = this.yearIndex;
            if (concurrentHashMap != null) {
                concurrentHashMap.keySet().forEach(new o(6, this, list));
            }
            if (this.dividerItem.position >= this.data.size() - 1) {
                DividerItem dividerItem2 = this.dividerItem;
                dividerItem2.position = -1;
                this.data.remove(dividerItem2);
            }
        }
    }
}
