package com.samsung.android.gallery.widget.bottom;

import a6.C0419b;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$plurals;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import n0.C0235b;
import n4.C0491c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomMoveBar extends RelativeLayout {
    private static Boolean[] LAYER_ENABLE_STATE;
    private float mCornerRadius;
    private View mDivider;
    private ImageView mImageLayer0;
    private ImageView mImageLayer1;
    private ImageView mImageLayer2;
    private BooleanSupplier mIsTouchBlocked;
    private View.OnClickListener mListener;
    private MediaItem mMediaItem;
    private Button mMoveButton;
    private int mShadowColor;
    private Animation mSlideDownAnim;
    private Animation mSlideUpAnim;
    private TextView mTextView;

    public BottomMoveBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void bindViews() {
        this.mTextView = (TextView) findViewById(R$id.text);
        this.mImageLayer0 = (ImageView) findViewById(R$id.imageLayer0);
        this.mImageLayer1 = (ImageView) findViewById(R$id.imageLayer1);
        this.mImageLayer2 = (ImageView) findViewById(R$id.imageLayer2);
        Button button = (Button) findViewById(R$id.moveButton);
        this.mMoveButton = button;
        button.setOnClickListener(new C0419b(27, this));
        SeApiCompat.setButtonShapeEnabled(this.mMoveButton);
        Button button2 = (Button) findViewById(R$id.cancelButton);
        button2.setOnClickListener(new C0419b(27, this));
        SeApiCompat.setButtonShapeEnabled(button2);
        this.mDivider = findViewById(R$id.divider);
        float dimension = getResources().getDimension(R$dimen.move_bar_image_radius);
        this.mCornerRadius = dimension;
        ViewUtils.setViewShape(this.mImageLayer0, 1, dimension);
        ViewUtils.setViewShape(this.mImageLayer1, 1, this.mCornerRadius);
        ViewUtils.setViewShape(this.mImageLayer2, 1, this.mCornerRadius);
        this.mShadowColor = getContext().getColor(R$color.move_bottom_bar_shadow_color);
    }

    private void doSlideDownAnimation() {
        if (this.mSlideDownAnim == null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R$anim.floating_fade_out);
            this.mSlideDownAnim = loadAnimation;
            loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    BottomMoveBar.this.setVisible(8);
                    BottomMoveBar.this.setAutoGoToTopOffsetMove(true);
                    BottomMoveBar.this.destroy();
                }
            });
        }
        setAutoGoToTopOffsetMove(false);
        setVisible(4);
        getSlideView().startAnimation(this.mSlideDownAnim);
    }

    private void doSlideUpAnimation() {
        if (this.mSlideUpAnim == null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R$anim.floating_fade_in);
            this.mSlideUpAnim = loadAnimation;
            loadAnimation.setAnimationListener(new SimpleAnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    BottomMoveBar.this.setAutoGoToTopOffsetMove(true);
                    BottomMoveBar.this.getSlideView().requestLayout();
                }
            });
        }
        setAutoGoToTopOffsetMove(false);
        setVisible(0);
        getSlideView().startAnimation(this.mSlideUpAnim);
    }

    private Drawable getBorderDrawable(boolean z) {
        int i2;
        Resources resources = getResources();
        if (z) {
            i2 = R$dimen.folder_border_thickness;
        } else {
            i2 = R$dimen.album_view_border_thickness;
        }
        int dimensionPixelOffset = resources.getDimensionPixelOffset(i2);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RoundRectShape(getRadiiArray(this.mCornerRadius), getInsetRect((float) dimensionPixelOffset), getRadiiArray(this.mCornerRadius)));
        shapeDrawable.setTint(getContext().getResources().getColor(R$color.daynight_thumbnail_border_color, (Resources.Theme) null));
        return shapeDrawable;
    }

    private RectF getInsetRect(float f) {
        return new RectF(f, f, f, f);
    }

    private float[] getRadiiArray(float f) {
        return new float[]{f, f, f, f, f, f, f, f};
    }

    /* access modifiers changed from: private */
    public View getSlideView() {
        View view = (View) getParent();
        if (view != null) {
            return view;
        }
        return this;
    }

    private ImageView getThumbnailLayer() {
        Boolean[] boolArr = LAYER_ENABLE_STATE;
        if (boolArr == null) {
            return null;
        }
        if (boolArr[2].booleanValue()) {
            return this.mImageLayer2;
        }
        if (LAYER_ENABLE_STATE[1].booleanValue()) {
            return this.mImageLayer1;
        }
        return this.mImageLayer0;
    }

    private void initImageLayer(int i2) {
        Drawable drawable;
        setImageLayerAsDefault(this.mImageLayer0, LAYER_ENABLE_STATE[0].booleanValue());
        setImageLayerAsDefault(this.mImageLayer1, LAYER_ENABLE_STATE[1].booleanValue());
        setImageLayerAsDefault(this.mImageLayer2, LAYER_ENABLE_STATE[2].booleanValue());
        ImageView thumbnailLayer = getThumbnailLayer();
        if (i2 > 0) {
            drawable = getBorderDrawable(isFolder());
        } else {
            drawable = null;
        }
        ViewUtils.setShapeBorder(thumbnailLayer, drawable);
    }

    private void initLayerEnableState(int i2) {
        boolean z;
        boolean z3;
        Boolean bool = Boolean.FALSE;
        LAYER_ENABLE_STATE = new Boolean[]{bool, bool, bool};
        boolean z7 = false;
        if (isFolder()) {
            LAYER_ENABLE_STATE[0] = Boolean.TRUE;
            return;
        }
        Boolean[] boolArr = LAYER_ENABLE_STATE;
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        boolArr[0] = Boolean.valueOf(z);
        Boolean[] boolArr2 = LAYER_ENABLE_STATE;
        if (i2 > 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolArr2[1] = Boolean.valueOf(z3);
        Boolean[] boolArr3 = LAYER_ENABLE_STATE;
        if (i2 > 2) {
            z7 = true;
        }
        boolArr3[2] = Boolean.valueOf(z7);
    }

    private boolean isFolder() {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isFolder()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$0(ImageView imageView, Bitmap bitmap) {
        imageView.setVisibility(0);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        imageView.setImageBitmap(bitmap);
        imageView.setBackground((Drawable) null);
        imageView.setImageMatrix(ViewMatrixUtils.createImageMatrix(imageView, this.mMediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$1(Bitmap bitmap, ImageView imageView) {
        imageView.post(new C0235b(this, imageView, bitmap, 3));
    }

    /* access modifiers changed from: private */
    public void onButtonClick(View view) {
        View.OnClickListener onClickListener = this.mListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* access modifiers changed from: private */
    public void setAutoGoToTopOffsetMove(boolean z) {
        View slideView = getSlideView();
        if (slideView instanceof FloatingBottomLayout) {
            FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) slideView;
            if (z) {
                floatingBottomLayout.f1894G = Boolean.TRUE;
                floatingBottomLayout.getFloatingScrollableManager$material_release().f = true;
                return;
            }
            floatingBottomLayout.f1894G = Boolean.FALSE;
            floatingBottomLayout.getFloatingScrollableManager$material_release().f = false;
        }
    }

    private void setImageLayerAsDefault(ImageView imageView, boolean z) {
        if (z) {
            imageView.setBackgroundColor(this.mShadowColor);
            imageView.setVisibility(0);
            return;
        }
        imageView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void setVisible(int i2) {
        setVisibility(i2);
        getSlideView().setVisibility(i2);
    }

    public void bindImage(Bitmap bitmap) {
        if (this.mMediaItem != null && bitmap != null) {
            Optional.ofNullable(getThumbnailLayer()).ifPresent(new C0491c(6, this, bitmap));
        }
    }

    public void destroy() {
        this.mListener = null;
        this.mMediaItem = null;
        ImageView thumbnailLayer = getThumbnailLayer();
        if (thumbnailLayer != null) {
            thumbnailLayer.setImageDrawable((Drawable) null);
            thumbnailLayer.setImageMatrix((Matrix) null);
            thumbnailLayer.setForeground((Drawable) null);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        BooleanSupplier booleanSupplier = this.mIsTouchBlocked;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        Log.d("BottomMoveBar", "dispatchTouchEvent blocked");
        return true;
    }

    public void hideBottomBar(boolean z) {
        if (z) {
            doSlideDownAnimation();
        } else {
            setVisible(8);
        }
    }

    public void initView(MediaItem mediaItem, int i2) {
        this.mMediaItem = mediaItem;
        this.mTextView.setText(getResources().getQuantityString(R$plurals.move_n_items_plural, i2, new Object[]{Integer.valueOf(i2)}));
        initLayerEnableState(i2);
        initImageLayer(i2);
        setVisible(8);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        bindViews();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    public void setTouchBlocker(BooleanSupplier booleanSupplier) {
        this.mIsTouchBlocked = booleanSupplier;
    }

    public void showBottomBar(boolean z, boolean z3, boolean z7) {
        float f;
        this.mMoveButton.setEnabled(z3);
        Button button = this.mMoveButton;
        if (z3) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        button.setAlpha(f);
        ViewUtils.setVisibleOrGone(this.mMoveButton, !z7);
        ViewUtils.setVisibleOrGone(this.mDivider, !z7);
        if (z) {
            doSlideUpAnimation();
        } else {
            setVisible(0);
        }
    }
}
