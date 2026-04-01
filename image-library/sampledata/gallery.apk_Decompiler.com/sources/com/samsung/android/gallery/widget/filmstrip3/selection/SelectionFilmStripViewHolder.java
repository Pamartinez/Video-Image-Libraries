package com.samsung.android.gallery.widget.filmstrip3.selection;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.SquareImageView;
import com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionFilmStripViewHolder extends FilmStripViewHolder<SelectionFilmStripImageItemLayoutBinding> {
    private ImageView mBestIcon;
    private ImageView mCheckedIcon;
    private SquareImageView mImageView;
    private ImageView mSavedIcon;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TINT_SELECTED_FILTER {
        static final ConcurrentHashMap<Integer, ColorFilter> map = new ConcurrentHashMap<>();

        /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.util.function.Function] */
        public static ColorFilter get(Context context) {
            return map.computeIfAbsent(Integer.valueOf(context.getColor(R$color.burstshot_select_v2_dim)), new Object());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ ColorFilter lambda$get$0(Integer num) {
            return new PorterDuffColorFilter(num.intValue(), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TINT_TRANSPARENT_FILTER {
        static final ColorFilter value = new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP);
    }

    public SelectionFilmStripViewHolder(SelectionFilmStripImageItemLayoutBinding selectionFilmStripImageItemLayoutBinding) {
        super(selectionFilmStripImageItemLayoutBinding);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x002f, code lost:
        if (r2.getBestImage() == 1) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bindView(com.samsung.android.gallery.module.data.MediaItem r2, int r3) {
        /*
            r1 = this;
            super.bindView(r2, r3)
            VIEW_BINDING r3 = r1.mViewBinding
            r0 = r3
            com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding r0 = (com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding) r0
            com.samsung.android.gallery.widget.SquareImageView r0 = r0.filmStripViewImage
            r1.mImageView = r0
            r0 = r3
            com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding r0 = (com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding) r0
            android.widget.ImageView r0 = r0.bestImageIcon
            r1.mBestIcon = r0
            r0 = r3
            com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding r0 = (com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding) r0
            android.widget.ImageView r0 = r0.savedIcon
            r1.mSavedIcon = r0
            r0 = r3
            com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding r0 = (com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding) r0
            android.widget.ImageView r0 = r0.checkedIcon
            r1.mCheckedIcon = r0
            com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding r3 = (com.samsung.android.gallery.widget.databinding.SelectionFilmStripImageItemLayoutBinding) r3
            com.samsung.android.gallery.widget.SquareImageView r3 = r3.filmStripViewImage
            r1.setDefaultImage(r3)
            if (r2 == 0) goto L_0x0032
            int r2 = r2.getBestImage()
            r3 = 1
            if (r2 != r3) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            r3 = 0
        L_0x0033:
            r1.setBestItem(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.filmstrip3.selection.SelectionFilmStripViewHolder.bindView(com.samsung.android.gallery.module.data.MediaItem, int):void");
    }

    public void setBestItem(boolean z) {
        ViewUtils.setVisibleOrGone(this.mBestIcon, z);
    }

    public void setChecked(boolean z) {
        ColorFilter colorFilter;
        ViewUtils.setVisibleOrGone(this.mCheckedIcon, z);
        SquareImageView squareImageView = this.mImageView;
        if (z) {
            colorFilter = TINT_SELECTED_FILTER.get(this.itemView.getContext());
        } else {
            colorFilter = TINT_TRANSPARENT_FILTER.value;
        }
        squareImageView.setColorFilter(colorFilter);
    }

    public void setSaved(boolean z) {
        ViewUtils.setVisibleOrGone(this.mSavedIcon, z);
    }
}
