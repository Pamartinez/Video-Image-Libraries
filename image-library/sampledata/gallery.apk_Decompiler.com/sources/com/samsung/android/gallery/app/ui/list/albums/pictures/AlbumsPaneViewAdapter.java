package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.IAlbumsBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsPaneViewAdapter<V extends IAlbumsBaseView> extends AlbumsBaseViewAdapter<V> {
    private Drawable[] mFolderPaneDrawable;
    private Drawable mNonSelectedBorder;
    private Drawable mSelectedBorder;

    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPaneViewAdapter$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.data.MediaItem$FolderPosition[] r0 = com.samsung.android.gallery.module.data.MediaItem.FolderPosition.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition = r0
                com.samsung.android.gallery.module.data.MediaItem$FolderPosition r1 = com.samsung.android.gallery.module.data.MediaItem.FolderPosition.FIRST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.data.MediaItem$FolderPosition r1 = com.samsung.android.gallery.module.data.MediaItem.FolderPosition.MIDDLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.data.MediaItem$FolderPosition r1 = com.samsung.android.gallery.module.data.MediaItem.FolderPosition.LAST     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.data.MediaItem$FolderPosition r1 = com.samsung.android.gallery.module.data.MediaItem.FolderPosition.ALL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.data.MediaItem$FolderPosition r1 = com.samsung.android.gallery.module.data.MediaItem.FolderPosition.NONE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPaneViewAdapter.AnonymousClass2.<clinit>():void");
        }
    }

    public AlbumsPaneViewAdapter(V v, String str) {
        super(v, str);
        v.getListView().setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            }
        });
    }

    private MediaItem getCurrentAlbum() {
        Blackboard blackboard = this.mBlackBoard;
        if (blackboard != null) {
            return (MediaItem) blackboard.read("data://albums/current", null);
        }
        return null;
    }

    private boolean isCurrentAlbum(ListViewHolder listViewHolder) {
        MediaItem currentAlbum = getCurrentAlbum();
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (currentAlbum == null || mediaItem == null || currentAlbum.getAlbumID() != mediaItem.getAlbumID()) {
            return false;
        }
        return true;
    }

    private void setItemViewBackground(ListViewHolder listViewHolder) {
        Drawable drawable;
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null) {
            int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$gallery$module$data$MediaItem$FolderPosition[mediaItem.getFolderPosition().ordinal()];
            if (i2 == 1) {
                drawable = this.mFolderPaneDrawable[0];
            } else if (i2 == 2) {
                drawable = this.mFolderPaneDrawable[1];
            } else if (i2 == 3) {
                drawable = this.mFolderPaneDrawable[2];
            } else if (i2 == 4) {
                drawable = this.mFolderPaneDrawable[3];
            } else if (i2 != 5) {
                drawable = null;
            } else {
                drawable = this.mFolderPaneDrawable[4];
            }
            listViewHolder.getRootView().setBackground(drawable);
        }
    }

    public AlbumsViewHolderFactory createViewHolderFactory(Context context) {
        return new AlbumsPaneViewHolderFactory(context);
    }

    public Bitmap getBitmapForEmptyAlbum(MediaItem mediaItem, boolean z) {
        if (MediaItemUtil.isEmptyAlbum(mediaItem) || (mediaItem.isFolder() && mediaItem.isEmpty())) {
            return ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
        }
        return null;
    }

    public int getItemViewType(int i2) {
        return 2;
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (isCurrentAlbum(listViewHolder) || !((IAlbumsBaseView) this.mView).isSplitMode()) {
            Log.w(this.TAG, "ignore change album. same or hidden");
        } else {
            super.handleItemClick(listViewHolder, i2, mediaItem, i7);
        }
    }

    public void initResourceValues() {
        super.initResourceValues();
        this.mSelectedBorder = getContext().getDrawable(R.drawable.albums_pane_selected_border);
        this.mNonSelectedBorder = getContext().getDrawable(R.drawable.albums_pane_non_selected_border);
        this.mFolderPaneDrawable = new Drawable[]{getContext().getDrawable(R.drawable.album_pane_folder_top_element), getContext().getDrawable(R.drawable.album_pane_folder_middle_element), getContext().getDrawable(R.drawable.album_pane_folder_bottom_element), getContext().getDrawable(R.drawable.album_pane_folder_all_element), getContext().getDrawable(R.drawable.album_pane_folder_empty_element)};
    }

    public boolean isEnabledDragOnAdvancedMouseEvent() {
        return false;
    }

    public boolean supportHighQuality() {
        return false;
    }

    public boolean supportHover() {
        return false;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("album_changed")) {
            listViewHolder.addThumbnailBorder(isCurrentAlbum(listViewHolder) ? this.mSelectedBorder : this.mNonSelectedBorder);
        } else {
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        listViewHolder.setThumbnailShape(1, getContext().getResources().getDimension(R.dimen.albums_pane_corner_radius));
        listViewHolder.addThumbnailBorder(isCurrentAlbum(listViewHolder) ? this.mSelectedBorder : this.mNonSelectedBorder);
        setItemViewBackground(listViewHolder);
    }
}
