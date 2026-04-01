package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.drm.DrmManager;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FloatingHistoryViewHolder extends ImageViewHolder {
    private View mCircleContainer;
    private ImageView mCircleThumb;
    BiConsumer<FloatingHistoryViewHolder, HistoryItem> mClickListener;
    private ImageView mDeleteButton;
    BiConsumer<FloatingHistoryViewHolder, HistoryItem> mDeleteCallback;
    private HistoryItem mHistoryItem;
    private TextView mTitleView;
    private ImageView mType;

    public FloatingHistoryViewHolder(View view) {
        super(view, 0);
        this.mThumbKind = ThumbKind.MEDIUM_KIND;
    }

    private void drawTypeIcon(HistoryItem historyItem) {
        Drawable drawable;
        Integer typeIconResId = historyItem.getTypeIconResId();
        if (!(typeIconResId == null || (drawable = this.itemView.getContext().getDrawable(typeIconResId.intValue())) == null)) {
            Drawable mutate = drawable.mutate();
            mutate.setTint(this.itemView.getContext().getColor(R.color.bottom_search_toolbar_floating_items_color));
            setTypeIcon(mutate);
        }
        if (historyItem.isPeople() || historyItem.isPet()) {
            historyItem.getCreatureThumbnail(new f(this, historyItem));
        }
    }

    private boolean isInvalidDrm(MediaItem mediaItem) {
        if (!mediaItem.isDrm() || mediaItem.isCloudOnly() || mediaItem.isSharing() || FileUtils.isEmptyDummyImage(mediaItem.getPath()) || DrmManager.getInstance().isValidRights(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drawTypeIcon$1(Bitmap bitmap, HistoryItem historyItem) {
        BiConsumer<FloatingHistoryViewHolder, HistoryItem> biConsumer;
        if (bitmap == null && (biConsumer = this.mDeleteCallback) != null) {
            biConsumer.accept(this, historyItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drawTypeIcon$2(HistoryItem historyItem, Bitmap bitmap) {
        ThreadUtil.postOnUiThread(new g(bitmap, this, historyItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setClickListener$0(View view) {
        BiConsumer<FloatingHistoryViewHolder, HistoryItem> biConsumer = this.mDeleteCallback;
        if (biConsumer != null) {
            biConsumer.accept(this, this.mHistoryItem);
        }
    }

    /* access modifiers changed from: private */
    public void onViewClicked(View view) {
        BiConsumer<FloatingHistoryViewHolder, HistoryItem> biConsumer = this.mClickListener;
        if (biConsumer != null) {
            biConsumer.accept(this, this.mHistoryItem);
        }
    }

    public void bind(HistoryItem historyItem) {
        this.mHistoryItem = historyItem;
        setTitle(historyItem.getDisplayName());
        if (historyItem.hasTypeIcon()) {
            drawTypeIcon(historyItem);
        }
        super.bind(historyItem.getCoverItem());
        setClickListener();
    }

    public void bindView(View view) {
        super.bindView(view);
        TextView textView = (TextView) view.findViewById(R.id.title);
        this.mTitleView = textView;
        textView.setAutoSizeTextTypeUniformWithConfiguration(13, 16, 1, 1);
        this.mCircleContainer = view.findViewById(R.id.thumbnail_circle_icon_container);
        this.mCircleThumb = (ImageView) view.findViewById(R.id.circle_thumb);
        this.mType = (ImageView) view.findViewById(R.id.type);
        this.mDeleteButton = (ImageView) view.findViewById(R.id.delete_button);
        ViewUtils.setViewShape(this.mImageView, 0, 0.0f);
    }

    public Bitmap getBrokenBitmap() {
        MediaItem mediaItem = getMediaItem();
        if (!mediaItem.isBroken() && !isInvalidDrm(mediaItem) && !TrashData.of(mediaItem).drm) {
            return null;
        }
        mediaItem.setBroken(true);
        return BitmapUtils.getBitmapFromDrawable(this.mImageView.getContext().getDrawable(R.drawable.broken_thumb_with_app_icon));
    }

    public String getContentDescription() {
        return this.mHistoryItem.getContentDescription();
    }

    public void recycle() {
        super.recycle();
        this.mHistoryItem = null;
        this.mCircleThumb.setImageDrawable((Drawable) null);
        this.mCircleThumb.setVisibility(8);
        this.mType.setImageDrawable((Drawable) null);
        this.mType.setVisibility(8);
        this.mCircleContainer.setVisibility(8);
    }

    public FloatingHistoryViewHolder setClickListener(BiConsumer<FloatingHistoryViewHolder, HistoryItem> biConsumer) {
        this.mClickListener = biConsumer;
        return this;
    }

    public FloatingHistoryViewHolder setDeleteCallback(BiConsumer<FloatingHistoryViewHolder, HistoryItem> biConsumer) {
        this.mDeleteCallback = biConsumer;
        return this;
    }

    public void setTitle(String str) {
        this.mTitleView.setText(str);
    }

    public void setTypeIcon(Drawable drawable) {
        this.mType.setImageDrawable(drawable);
        this.mType.setVisibility(0);
        this.mCircleContainer.setVisibility(0);
    }

    private void setClickListener() {
        this.itemView.setOnClickListener(new e(this, 0));
        ViewUtils.setOnClickListener(this.mDeleteButton, new e(this, 1));
    }
}
