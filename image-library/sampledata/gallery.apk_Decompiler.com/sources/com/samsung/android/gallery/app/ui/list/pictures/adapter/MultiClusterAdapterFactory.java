package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import com.samsung.android.gallery.app.ui.list.pictures.adapter.MultiClusterAdapter;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MultiClusterAdapterFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultiClusterBothAdapter extends MultiClusterHeaderAdapter {
        public MultiClusterBothAdapter(GalleryListView galleryListView, MediaData mediaData, boolean z, boolean z3, MultiClusterAdapter.TimelineModeLookup timelineModeLookup) {
            super(galleryListView, mediaData, z, z3, timelineModeLookup);
        }

        public int getCount() {
            return super.getCount() + 1;
        }

        public int getHintItemCount(int i2) {
            return super.getHintItemCount(i2) + 1;
        }

        public int getHintViewCount(int i2) {
            return super.getHintViewCount(i2) + 1;
        }

        public int getItemCount() {
            return super.getItemCount() + 1;
        }

        public int getStartSpan(int i2, int i7) {
            if (i2 == getItemCount() - 1) {
                return 0;
            }
            return super.getStartSpan(i2, i7);
        }

        public MultiClusterBothAdapter(MediaData mediaData) {
            super(mediaData);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultiClusterFooterAdapter extends MultiClusterAdapter {
        public MultiClusterFooterAdapter(GalleryListView galleryListView, MediaData mediaData, boolean z, boolean z3, MultiClusterAdapter.TimelineModeLookup timelineModeLookup) {
            super(galleryListView, mediaData, z, z3, timelineModeLookup);
        }

        public int getHintItemCount(int i2) {
            return super.getHintItemCount(i2) + 1;
        }

        public int getHintViewCount(int i2) {
            return super.getHintViewCount(i2) + 1;
        }

        public int getItemCount() {
            return super.getItemCount() + 1;
        }

        public int getStartSpan(int i2, int i7) {
            if (i2 == getItemCount() - 1) {
                return 0;
            }
            return super.getStartSpan(i2, i7);
        }

        public boolean isDivider(int i2) {
            if (i2 >= getItemCount() - 1 || !super.isDivider(i2)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MultiClusterHeaderAdapter extends MultiClusterAdapter {
        public MultiClusterHeaderAdapter(GalleryListView galleryListView, MediaData mediaData, boolean z, boolean z3, MultiClusterAdapter.TimelineModeLookup timelineModeLookup) {
            super(galleryListView, mediaData, z, z3, timelineModeLookup);
        }

        public int getCount() {
            return super.getCount() + 1;
        }

        public int getDividerIndex(int i2) {
            if (i2 == 0) {
                return 1;
            }
            int dividerIndex = super.getDividerIndex(i2);
            if (dividerIndex >= 0) {
                return dividerIndex + 1;
            }
            return dividerIndex;
        }

        public int getHeaderDeltaPosition(int i2) {
            return Math.max(i2 - 1, 0);
        }

        public int getHintItemCount(int i2) {
            return super.getHintItemCount(i2) + 1;
        }

        public int getHintItemViewType(int i2, int i7) {
            if (i2 == 0) {
                return -3;
            }
            return super.getHintItemViewType(i2, i7);
        }

        public int getHintPrevRowIndex(int i2, int i7) {
            if (i2 == 0) {
                return -1;
            }
            return super.getHintPrevRowIndex(i2, i7);
        }

        public SpanInfo getHintSpanInfo(int i2, int i7) {
            if (isHeader(i2)) {
                return new SpanInfo(0, 0, -3);
            }
            return super.getHintSpanInfo(i2, i7).increaseRow();
        }

        public int getHintViewCount(int i2) {
            return super.getHintViewCount(i2) + 1;
        }

        public int getHintViewPosition(int i2, int i7) {
            return super.getHintViewPosition(i2, i7) + 1;
        }

        public int getItemCount() {
            return super.getItemCount() + 1;
        }

        public ScrollText getScrollText(int i2) {
            if (i2 == 0) {
                i2 = 1;
            }
            return super.getScrollText(i2);
        }

        public int getStartSpan(int i2, int i7) {
            if (i2 == 0) {
                return 0;
            }
            return super.getStartSpan(i2, i7);
        }

        public int getViewPosition(int i2) {
            return super.getViewPosition(i2) + 1;
        }

        public boolean isDivider(int i2) {
            if (i2 == 0 || !super.isDivider(i2)) {
                return false;
            }
            return true;
        }

        public boolean isHeader(int i2) {
            if (i2 == 0) {
                return true;
            }
            return false;
        }

        public MultiClusterHeaderAdapter(MediaData mediaData) {
            super(mediaData);
        }
    }

    public static MultiClusterAdapter create(GalleryListView galleryListView, MediaData mediaData, boolean z, boolean z3, MultiClusterAdapter.TimelineModeLookup timelineModeLookup, boolean z7, boolean z9) {
        if (!z7 || !z9) {
            boolean z10 = z;
            GalleryListView galleryListView2 = galleryListView;
            boolean z11 = z7;
            boolean z12 = z3;
            MediaData mediaData2 = mediaData;
            boolean z13 = z9;
            MultiClusterAdapter.TimelineModeLookup timelineModeLookup2 = timelineModeLookup;
            boolean z14 = z10;
            if (z11) {
                return new MultiClusterHeaderAdapter(galleryListView2, mediaData2, z14, z12, timelineModeLookup2);
            }
            if (z13) {
                return new MultiClusterFooterAdapter(galleryListView2, mediaData2, z14, z12, timelineModeLookup2);
            }
            return new MultiClusterAdapter(galleryListView2, mediaData2, z14, z12, timelineModeLookup2);
        }
        MultiClusterAdapter.TimelineModeLookup timelineModeLookup3 = timelineModeLookup;
        boolean z15 = z3;
        boolean z16 = z;
        return new MultiClusterBothAdapter(galleryListView, mediaData, z16, z15, timelineModeLookup3);
    }
}
