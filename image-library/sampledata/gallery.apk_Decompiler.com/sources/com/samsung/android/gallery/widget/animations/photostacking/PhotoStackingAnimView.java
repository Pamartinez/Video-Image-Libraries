package com.samsung.android.gallery.widget.animations.photostacking;

import a6.g;
import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoStackingAnimView extends RelativeLayout {
    private final DescriptionAnimation mDescriptionAnim;
    private View mLastView;
    private onAnimationEndListener mOnEndListener;
    private InterruptState mState;
    private final ThrowingViHandler mThrowingVi;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface onAnimationEndListener {
    }

    public PhotoStackingAnimView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* access modifiers changed from: private */
    public void onLastStackingFinished(View view) {
        boolean z;
        onAnimationEndListener onanimationendlistener;
        if (view != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d("PhotoStackingAnimView", "onLastStackingFinished", Boolean.valueOf(z), this.mState);
        this.mLastView = view;
        if ((InterruptState.isSuccess(this.mState) || InterruptState.isFail(this.mState)) && (onanimationendlistener = this.mOnEndListener) != null) {
            ((ProcessingViewManager) ((g) onanimationendlistener).e).onAnimationFinished(InterruptState.isSuccess(this.mState));
        }
    }

    private void updateState(InterruptState interruptState) {
        if (this.mState != interruptState) {
            Log.d("PhotoStackingAnimView", "interrupted " + this.mState + " > " + interruptState);
            this.mState = interruptState;
            this.mThrowingVi.updateInterruptType(interruptState);
        }
    }

    private void updateTextLayout(boolean z) {
        ViewUtils.setVisibleOrGone(findViewById(R$id.title), !z);
        int i2 = R$id.description_text_layout;
        ViewUtils.setVisibleOrGone(findViewById(i2), !z);
        ViewMarginUtils.setTopMargin(findViewById(i2), getResources().getDimensionPixelSize(R$dimen.ondemand_story_vi_textview_margin_top));
    }

    private void updateTitle(String str) {
        TextView textView = (TextView) findViewById(R$id.title);
        if (!TextUtils.isEmpty(str) && textView != null) {
            textView.setText(str);
            textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
                    if (view instanceof TextView) {
                        ((TextView) view).getPaint().setShader(new LinearGradient(0.0f, 0.0f, (float) (i8 - i2), (float) (i7 - i10), new int[]{-1318913, -2493185, -2162703}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
                    }
                    view.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    public void cancel() {
        updateState(InterruptState.CANCEL);
        releaseView();
    }

    public ImageView getTransitionImage() {
        View view = this.mLastView;
        if (view != null) {
            return (ImageView) view.findViewById(R$id.vi_image);
        }
        return null;
    }

    public TextView getTransitionText() {
        return (TextView) findViewById(R$id.title);
    }

    public boolean isRunning() {
        if (getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public void onPause() {
        this.mDescriptionAnim.stop();
        this.mThrowingVi.onPause();
    }

    public void onResume() {
        this.mDescriptionAnim.play();
        this.mThrowingVi.onResume();
    }

    public void releaseView() {
        this.mThrowingVi.releaseView();
        this.mDescriptionAnim.stop();
        ViewUtils.setVisibility(this, 8);
        ViewUtils.removeSelf(this);
    }

    public void requestStop(boolean z, ImageInfo imageInfo) {
        InterruptState interruptState;
        if (z) {
            interruptState = InterruptState.SUCCESS;
        } else {
            interruptState = InterruptState.FAIL;
        }
        updateState(interruptState);
        if (imageInfo != null) {
            this.mThrowingVi.updateLastAnimInfo(imageInfo);
        }
    }

    public void setOnEndListener(onAnimationEndListener onanimationendlistener) {
        this.mOnEndListener = onanimationendlistener;
    }

    public void start(String str, ArrayList<ImageInfo> arrayList) {
        setVisibility(0);
        updateTitle(str);
        InterruptState interruptState = InterruptState.NONE;
        this.mState = interruptState;
        this.mThrowingVi.updateInterruptType(interruptState);
        this.mThrowingVi.setListener(new a(18, this));
        this.mThrowingVi.play(arrayList);
        this.mDescriptionAnim.play();
    }

    public void updateLayout(boolean z) {
        updateTextLayout(z);
        if (isRunning()) {
            this.mThrowingVi.updateLayoutPosition();
            if (!this.mDescriptionAnim.isPlaying() && !z) {
                this.mDescriptionAnim.play();
            }
        }
    }

    public PhotoStackingAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R$layout.photo_stacking_animation_layout, this);
        this.mDescriptionAnim = new DescriptionAnimation(this);
        this.mThrowingVi = new ThrowingViHandler(this);
        setVisibility(8);
    }
}
