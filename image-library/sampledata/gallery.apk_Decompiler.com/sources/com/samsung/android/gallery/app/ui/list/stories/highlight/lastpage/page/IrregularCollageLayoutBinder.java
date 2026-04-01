package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IIrregularCollagePage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IrregularCollageLayoutBinder<V extends IIrregularCollagePage> extends SaveLayoutBinder<V> {
    private IrregularView mIrregularView;
    private boolean mIsGradientViewVisible;

    public IrregularCollageLayoutBinder(V v) {
        super(v);
    }

    private Bitmap createBgForGradient() {
        return new IrregularGradientHelper(true).createBgForGradient(this.mContentParent, this.mIrregularView);
    }

    public Bitmap getBgForVideo() {
        Bitmap createBitmap = BitmapUtils.createBitmap(this.mContentParent.getWidth(), this.mContentParent.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(this.mContentParent.getContext().getColor(R.color.story_highlight_collage_content_bg_color));
        if (this.mIsGradientViewVisible) {
            canvas.drawPaint(paint);
            return createBitmap;
        }
        this.mIrregularView.setVisibleOrGone(false);
        canvas.drawPaint(paint);
        this.mContentParent.draw(canvas);
        this.mIrregularView.setVisibleOrGone(true);
        return createBitmap;
    }

    public Bitmap getFgForVideo() {
        Bitmap createBitmap = BitmapUtils.createBitmap(this.mContentParent.getWidth(), this.mContentParent.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        if (ViewUtils.isVisible((ImageView) this.mContentParent.findViewById(R.id.gradient_view))) {
            canvas.drawBitmap(createBgForGradient(), 0.0f, 0.0f, (Paint) null);
            this.mIrregularView.drawWithoutMainViewHolder(canvas);
            this.mIrregularView.setVisibleOrGone(false);
            this.mContentParent.draw(canvas);
            this.mIrregularView.setVisibleOrGone(true);
            return createBitmap;
        }
        this.mIrregularView.drawWithoutMainViewHolder(canvas);
        return createBitmap;
    }

    public ListViewHolder getMainViewHolder() {
        return this.mIrregularView.getMainViewHolder();
    }

    public void initViewInternal(View view, PageSpec.Config config, int i2) {
        IrregularView createIrregularView = ((IIrregularCollagePage) this.mCollagePage).createIrregularView((ViewGroup) view.findViewById(R.id.collage_item_container));
        this.mIrregularView = createIrregularView;
        ((IIrregularCollagePage) this.mCollagePage).initData(createIrregularView);
        ((IIrregularCollagePage) this.mCollagePage).updateCollageSize(this.mIrregularView, config);
    }

    public void updateGradientView(ImageView imageView, PageItem pageItem, int i2) {
        if (ViewUtils.isVisible(imageView)) {
            new IrregularGradientHelper(true).setGradientBackground(this.mContentParent, this.mIrregularView, pageItem, i2);
            this.mContentParent.setBackground((Drawable) null);
            this.mIsGradientViewVisible = true;
        }
    }
}
