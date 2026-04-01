package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Order {
    ;
    
    private final Comparator<MediaItem> mSorter;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.slideshow.Order$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends Order {
        public /* synthetic */ AnonymousClass1() {
            this("TIME_DESC", 0);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$createSorter$0(MediaItem mediaItem, MediaItem mediaItem2) {
            int compare = Long.compare(mediaItem2.getDateTaken(), mediaItem.getDateTaken());
            if (compare == 0) {
                return Long.compare(mediaItem2.getFileId(), mediaItem.getFileId());
            }
            return compare;
        }

        /* JADX WARNING: type inference failed for: r0v1, types: [java.util.Comparator<com.samsung.android.gallery.module.data.MediaItem>, java.lang.Object] */
        public Comparator<MediaItem> createSorter() {
            return new Object();
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.stories.slideshow.Order$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends Order {
        public /* synthetic */ AnonymousClass2() {
            this("TIME_ASC", 1);
        }

        public Comparator<MediaItem> createSorter() {
            return Comparator.comparingLong(new c(0)).thenComparingLong(new c(1));
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    public abstract Comparator<MediaItem> createSorter();

    public Comparator<MediaItem> get() {
        return this.mSorter;
    }
}
