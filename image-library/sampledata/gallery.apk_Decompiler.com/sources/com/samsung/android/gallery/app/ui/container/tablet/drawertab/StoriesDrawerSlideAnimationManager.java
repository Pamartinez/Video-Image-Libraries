package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import A4.X;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.HeightAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o4.a;
import p4.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesDrawerSlideAnimationManager extends DrawerSlideAnimationManager {
    private RecyclerView mHeaderListView;

    public StoriesDrawerSlideAnimationManager(PinchLayoutManager pinchLayoutManager, GalleryListView galleryListView) {
        super(pinchLayoutManager, galleryListView);
    }

    private void addAlphaAnimator(View view, boolean z) {
        float f;
        float f5 = 1.0f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f5 = 0.0f;
        }
        addAnimation(new AlphaAnimator(view, f, f5).setAnimationListener(new j(this, 2)));
    }

    private View getOnDemandView() {
        RecyclerView recyclerView = this.mHeaderListView;
        if (recyclerView == null || recyclerView.getLayoutManager() == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.mHeaderListView.getLayoutManager().getChildCount(); i2++) {
            View childAt = this.mHeaderListView.getLayoutManager().getChildAt(i2);
            if (childAt != null && this.mHeaderListView.getChildViewHolder(childAt).getItemViewType() == -103) {
                return childAt.findViewById(R.id.root_view);
            }
        }
        return null;
    }

    private int getSpacingDelta() {
        return (this.mLayoutManager.getHeaderStartPos(this.mGridInfo.from(), this.mGridInfo.to()) + (this.mLayoutManager.getSpacing(this.mGridInfo.to()) - this.mLayoutManager.getSpacing(this.mGridInfo.from()))) - this.mLayoutManager.getHintPaddingLeft(this.mGridInfo.to());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addHeaderTranslateAnimator$2(View view) {
        addAnimation(new AlphaAnimator(view, 0.0f, 0.0f).setAnimationListener(new j(this, 1)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareOnDemandNoItemDescriptionAnim$5(int i2, TextUtils.TruncateAt truncateAt, int i7, View view) {
        TextView textView = (TextView) view;
        textView.getLayoutParams().height = -2;
        textView.setMaxLines(i2);
        textView.setEllipsize(truncateAt);
        if (this.mIsRtl) {
            textView.setGravity(i7);
        }
    }

    private void prepareOnDemandNoItemDescriptionAnim(View view) {
        TextView textView = (TextView) view.findViewById(R.id.no_item_description);
        if (ViewUtils.isVisible(textView)) {
            int maxLines = textView.getMaxLines();
            TextUtils.TruncateAt ellipsize = textView.getEllipsize();
            int headerDescriptionWidthOffset = this.mLayoutManager.getHeaderDescriptionWidthOffset() + ViewMarginUtils.getEndMargin(view) + ViewMarginUtils.getStartMargin(view);
            StaticLayout headerDescriptionFrom = getHeaderDescriptionFrom(textView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.from()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            StaticLayout headerDescriptionTo = getHeaderDescriptionTo(textView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.to()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            if (headerDescriptionFrom != null && headerDescriptionTo != null) {
                int gravity = textView.getGravity();
                if (this.mIsRtl) {
                    textView.setGravity(8388613);
                }
                int verticalPadding = ViewMarginUtils.getVerticalPadding(textView);
                textView.setMaxLines(Math.min(headerDescriptionFrom.getLineCount(), headerDescriptionTo.getLineCount()));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                addAnimation(new HeightAnimator(textView, headerDescriptionFrom.getHeight() + verticalPadding, headerDescriptionTo.getHeight() + verticalPadding).setAnimationListener(new X(this, maxLines, ellipsize, gravity)));
            }
        }
    }

    public void addHeaderTranslateAnimator(View view, float f, float f5, float f8) {
        int left;
        float f10;
        super.addHeaderTranslateAnimator(view, f, f5, f8);
        View findViewById = this.mHeaderView.findViewById(R.id.text_divider);
        if (findViewById != null) {
            RectF rectF = new RectF((float) findViewById.getLeft(), (float) findViewById.getTop(), 0.0f, 0.0f);
            if (this.mIsRtl) {
                left = findViewById.getLeft() - getSpacingDelta();
            } else {
                left = findViewById.getLeft() + getSpacingDelta();
            }
            float f11 = (float) left;
            float top = (float) findViewById.getTop();
            if (this.mIsRtl) {
                f10 = (float) (findViewById.getLeft() - getSpacingDelta());
            } else {
                f10 = 0.0f;
            }
            addAnimation(new TranslationAnimator(findViewById, rectF, new RectF(f11, top, f10, 0.0f)).setAnimationListener(new j(this, 0)));
        }
        Optional.ofNullable((View) this.mHeaderView.getParent()).ifPresent(new a(6, this));
        View onDemandView = getOnDemandView();
        if (onDemandView != null) {
            prepareOnDemandNoItemDescriptionAnim(onDemandView);
        }
    }

    public boolean animWithUpdateLayoutParam(RectF[] rectFArr) {
        return false;
    }

    public /* bridge */ /* synthetic */ void completeAnimation(boolean z, boolean z3, Runnable runnable) {
        super.completeAnimation(z, z3, runnable);
    }

    public void finishAnimation(boolean z) {
        super.finishAnimation(z);
        RecyclerView recyclerView = this.mHeaderListView;
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            this.mHeaderListView.getLayoutManager().onRestoreInstanceState(this.mHeaderListView.getLayoutManager().onSaveInstanceState());
        }
    }

    public float getFakeHeaderViewTop(View view) {
        View view2 = this.mHeaderView;
        if (view2 == null || view2.getParent() == null) {
            return 0.0f;
        }
        return (float) ((View) this.mHeaderView.getParent()).getTop();
    }

    public int getHeaderViewHeight() {
        View onDemandView = getOnDemandView();
        if (onDemandView != null) {
            TextView textView = (TextView) onDemandView.findViewById(R.id.no_item_description);
            if (ViewUtils.isVisible(textView)) {
                int maxLines = textView.getMaxLines();
                int headerDescriptionWidthOffset = this.mLayoutManager.getHeaderDescriptionWidthOffset();
                TextUtils.TruncateAt ellipsize = textView.getEllipsize();
                StaticLayout headerDescriptionFrom = getHeaderDescriptionFrom(textView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.from()) - headerDescriptionWidthOffset, maxLines, ellipsize);
                StaticLayout headerDescriptionTo = getHeaderDescriptionTo(textView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.to()) - headerDescriptionWidthOffset, maxLines, ellipsize);
                if (!(headerDescriptionFrom == null || headerDescriptionTo == null)) {
                    this.mHeaderDiff = headerDescriptionTo.getHeight() - headerDescriptionFrom.getHeight();
                    return this.mHeaderView.getHeight() + this.mHeaderDiff;
                }
            }
        }
        return this.mHeaderView.getHeight();
    }

    public void hideDecorView(ListViewHolder listViewHolder, boolean z) {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            View decoView = listViewHolder.getDecoView(11);
            if (decoView != null) {
                addAlphaAnimator(decoView, z);
                return;
            }
            return;
        }
        super.hideDecorView(listViewHolder, z);
    }

    public void initHeaderView() {
        View view;
        int i2;
        super.initHeaderView();
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            view = this.mHeaderView;
            i2 = R.id.my_recycler_view;
        } else {
            view = this.mHeaderView;
            i2 = R.id.pin_list;
        }
        this.mHeaderListView = (RecyclerView) view.findViewById(i2);
    }

    public /* bridge */ /* synthetic */ void onPrepareAnimation(int i2, int i7, int i8) {
        super.onPrepareAnimation(i2, i7, i8);
    }

    public void onPrepareWidthAnimation(int i2, int i7) {
        float f;
        getRecyclerView().setClipChildren(false);
        initValues(i2, i7);
        initHeaderView();
        View onDemandView = getOnDemandView();
        if (onDemandView != null) {
            prepareOnDemandNoItemDescriptionAnim(onDemandView);
        }
        for (int i8 = 0; i8 < this.mLayoutManager.getChildCount(); i8++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i8));
            if (listViewHolder != null && !isInvisible(listViewHolder.getViewPosition())) {
                if (listViewHolder.getRootView() != null) {
                    TranslationAnimator translationAnimator = new TranslationAnimator(listViewHolder.getRootView());
                    if (this.mIsRtl) {
                        f = 0.0f;
                    } else {
                        f = (float) getWidthDelta();
                    }
                    addAnimation(translationAnimator.translateXRelative(f).setAnimationListener(new j(this, 3)));
                }
                addItemWidthAnimator(listViewHolder, useWidthAnimation(listViewHolder), false);
            }
        }
        startAnimation();
    }

    public /* bridge */ /* synthetic */ void setAnimationProgress(float f) {
        super.setAnimationProgress(f);
    }

    public /* bridge */ /* synthetic */ void setOpening(boolean z) {
        super.setOpening(z);
    }

    public /* bridge */ /* synthetic */ void setState(int i2) {
        super.setState(i2);
    }
}
