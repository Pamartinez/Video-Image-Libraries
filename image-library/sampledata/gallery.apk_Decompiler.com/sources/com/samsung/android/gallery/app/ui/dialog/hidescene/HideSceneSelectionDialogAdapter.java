package com.samsung.android.gallery.app.ui.dialog.hidescene;

import B8.e;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import l6.a;
import w4.C0533c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideSceneSelectionDialogAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final ArrayList<MediaItem> mList;
    private int mRealCount;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HideImageViewHolder extends ImageViewHolder {
        private TextView mCount;
        private View mDimContainer;

        public HideImageViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bind(MediaItem mediaItem) {
            super.bind(mediaItem);
            setThumbnailShape(0, 0.0f);
            addThumbnailBorder(getContext().getDrawable(R.drawable.search_people_thumbnail_border));
        }

        public void bindCount(int i2) {
            ViewUtils.setVisibleOrGone(this.mDimContainer, true);
            TextView textView = this.mCount;
            ViewUtils.setText(textView, "+" + i2);
        }

        public void bindView(View view) {
            super.bindView(view);
            this.mDimContainer = view.findViewById(R.id.dim_layout);
            this.mCount = (TextView) view.findViewById(R.id.count);
        }

        public void recycle() {
            super.recycle();
            ViewUtils.setVisibleOrGone(this.mDimContainer, false);
            ViewUtils.setText(this.mCount, "0");
        }
    }

    public HideSceneSelectionDialogAdapter(MediaItem[] mediaItemArr, int i2) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        this.mList = arrayList;
        arrayList.addAll(Arrays.asList(mediaItemArr));
        this.mRealCount = i2;
    }

    private void bindThumbnail(ListViewHolder listViewHolder, MediaItem mediaItem) {
        ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
        listViewHolder.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            lambda$bindThumbnail$0(listViewHolder, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new a(11, this, listViewHolder));
    }

    private boolean isVisibleCount(int i2) {
        if (this.mRealCount <= 4 || i2 != 3) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$1(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new C0533c(this, listViewHolder, bitmap, 0));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindThumbnail */
    public void lambda$bindThumbnail$0(ListViewHolder listViewHolder, Bitmap bitmap) {
        listViewHolder.bindImage(bitmap);
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new HideImageViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_hide_scene_selection_dialog_item_layout, viewGroup, false), i2);
    }

    public void onBindViewHolder(ImageViewHolder imageViewHolder, int i2) {
        MediaItem mediaItem = this.mList.get(i2);
        if (mediaItem != null) {
            imageViewHolder.bind(mediaItem);
            bindThumbnail(imageViewHolder, mediaItem);
            if (isVisibleCount(i2)) {
                ((HideImageViewHolder) imageViewHolder).bindCount(this.mRealCount - 4);
            }
        }
    }
}
