package com.samsung.android.gallery.widget.animations.photostacking;

import H7.B;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import bc.d;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.AiProcessingEffectLayout;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.o3dp.lib3dphotography.i;
import e5.C0451a;
import e6.C0453a;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThrowingViHandler {
    private OnViEndListener mFinishListener;
    private InterruptState mInterrupt = InterruptState.NONE;
    private boolean mIsPaused = false;
    private long mKey = 0;
    private int mLastViTryCount = 0;
    private View mLastView;
    private final Queue<View> mPlayingList = new ArrayDeque();
    private final ViewGroup mRootView;
    private int mRotationTryCount = 0;
    private final ThrowingAnimator mThrowingVi;
    private int mValidPlayingIndex = 0;
    private final ArrayList<View> mViewList = new ArrayList<>();
    private int mViewUsedIndex = 0;
    private final ArrayDeque<View> mWaitingList = new ArrayDeque<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnViEndListener {
    }

    public ThrowingViHandler(ViewGroup viewGroup) {
        this.mRootView = viewGroup;
        this.mThrowingVi = new ThrowingAnimator(viewGroup);
    }

    private void bindImage(View view, ImageInfo imageInfo) {
        ImageView imageView;
        if (view != null) {
            imageView = (ImageView) view.findViewById(R$id.vi_image);
        } else {
            imageView = null;
        }
        if (imageView != null && imageInfo != null) {
            imageView.setImageBitmap(imageInfo.getBitmap());
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            if (imageView.getWidth() == 0 && imageView.getHeight() == 0) {
                imageView.post(new d((Object) this, (Object) imageView, (Object) imageInfo, 11));
            } else {
                bindImageMatrix(imageView, imageInfo.getMatrix(), imageInfo.getItem());
            }
        }
    }

    private void bindImageMatrix(ImageView imageView, Matrix matrix, FileItemInterface fileItemInterface) {
        int i2;
        if (matrix != null) {
            imageView.setImageMatrix(matrix);
        } else if (fileItemInterface != null) {
            if (fileItemInterface.isVideo() || fileItemInterface.isBroken()) {
                i2 = 0;
            } else {
                i2 = fileItemInterface.getOrientation();
            }
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, true).withOrientation(i2).withOrientationTag(fileItemInterface.getOrientationTag())));
        }
    }

    private void clearImageBitmap(View view) {
        ImageView imageView;
        if (view != null) {
            imageView = (ImageView) view.findViewById(R$id.vi_image);
        } else {
            imageView = null;
        }
        if (imageView != null) {
            imageView.setImageBitmap((Bitmap) null);
        }
    }

    private View createView() {
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup == null) {
            return null;
        }
        Size imageViewSize = ThrowingValue.getImageViewSize(viewGroup.getContext());
        View inflate = LayoutInflater.from(this.mRootView.getContext()).inflate(R$layout.photo_stack_item_view, (ViewGroup) null);
        ViewUtils.setViewShape(inflate, 1, 20.0f);
        ViewUtils.setViewShape(inflate.findViewById(R$id.vi_effect), 1, 20.0f);
        ViewUtils.setViewSize(inflate.findViewById(R$id.vi_image), Integer.valueOf(imageViewSize.getWidth()), Integer.valueOf(imageViewSize.getHeight()));
        return inflate;
    }

    private int getPlayingCount() {
        return (int) Math.ceil(((double) this.mThrowingVi.getTotalPlayDuration()) / 300.0d);
    }

    private boolean isNeedPlayNext(int i2) {
        InterruptState interruptState = this.mInterrupt;
        if (InterruptState.isPlaying(interruptState)) {
            return true;
        }
        if (InterruptState.isFail(interruptState)) {
            if (i2 >= 5 || this.mRotationTryCount >= 5) {
                return false;
            }
            return true;
        } else if (!InterruptState.isCancel(interruptState) && this.mValidPlayingIndex < 15 && this.mRotationTryCount < 10) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$2(ImageView imageView, ImageInfo imageInfo) {
        bindImageMatrix(imageView, imageInfo.getMatrix(), imageInfo.getItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAnimationEnd$1(View view) {
        ((a) this.mFinishListener).b(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$play$0() {
        View poll = this.mWaitingList.poll();
        if (poll != null) {
            long j2 = this.mKey;
            int i2 = this.mValidPlayingIndex;
            this.mValidPlayingIndex = i2 + 1;
            playSingleThrowing(new ThrowingAnimator.AnimationInfo(poll, j2, i2, false));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayoutPosition$4(int i2, int i7) {
        this.mPlayingList.forEach(new C0453a(10));
        this.mValidPlayingIndex = Math.max(this.mValidPlayingIndex - this.mPlayingList.size(), 0);
        if (ViewUtils.isVisible(this.mLastView)) {
            Log.d("ThrowingViHandler", "configChanged while last playing");
            this.mLastView.setVisibility(4);
        } else {
            this.mRotationTryCount++;
        }
        RectF viewRect = ViewUtils.getViewRect(this.mRootView.findViewById(R$id.thumbnail_area));
        float f = viewRect.left;
        int i8 = (int) (((viewRect.right - f) / 2.0f) + f);
        float f5 = viewRect.top;
        int i10 = (int) (((viewRect.bottom - f5) / 2.0f) + f5);
        Iterator<View> it = this.mWaitingList.iterator();
        while (it.hasNext()) {
            View next = it.next();
            if (next.getVisibility() == 0) {
                float x9 = ((float) i2) - next.getX();
                float y = ((float) i7) - next.getY();
                next.setX(((float) i8) - x9);
                next.setY(((float) i10) - y);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onAnimationEnd(ThrowingAnimator.AnimationInfo animationInfo) {
        View view = animationInfo.view();
        if (view == null) {
            Log.e("ThrowingViHandler", "animationEnd: view null");
        } else if (InterruptState.isCancel(this.mInterrupt) || this.mKey != animationInfo.key()) {
            stopEffect(view);
            ViewUtils.removeSelf(view);
            ViewUtils.setVisibility(view, 4);
        } else {
            if (!animationInfo.isLast()) {
                this.mWaitingList.remove(view);
                this.mPlayingList.remove(view);
                if (view.getVisibility() == 4) {
                    this.mWaitingList.addFirst(view);
                } else {
                    this.mWaitingList.addLast(view);
                }
            }
            if (!this.mIsPaused && (animationInfo.index() % 4 == 0 || animationInfo.isLast())) {
                showEffect(view);
            }
            if (isNeedPlayNext(animationInfo.index())) {
                View poll = this.mWaitingList.poll();
                if (poll != null) {
                    long key = animationInfo.key();
                    int i2 = this.mValidPlayingIndex;
                    this.mValidPlayingIndex = i2 + 1;
                    playSingleThrowing(new ThrowingAnimator.AnimationInfo(poll, key, i2, false));
                    return;
                }
                return;
            }
            boolean z = false;
            if (InterruptState.isFail(this.mInterrupt)) {
                Integer valueOf = Integer.valueOf(this.mRotationTryCount);
                if (this.mFinishListener != null) {
                    z = true;
                }
                Log.i("ThrowingViHandler", "animation finish by fail", valueOf, Boolean.valueOf(z));
                OnViEndListener onViEndListener = this.mFinishListener;
                if (onViEndListener != null) {
                    ((a) onViEndListener).b((View) null);
                    this.mFinishListener = null;
                }
            } else if (!InterruptState.isSuccess(this.mInterrupt)) {
            } else {
                if (!animationInfo.isLast()) {
                    View view2 = this.mLastView;
                    if (view2 != null && view2.getVisibility() == 4) {
                        playLastThrowing();
                    }
                } else if (this.mLastViTryCount >= 3 || view.getVisibility() != 4) {
                    Integer valueOf2 = Integer.valueOf(this.mRotationTryCount);
                    Integer valueOf3 = Integer.valueOf(this.mLastViTryCount);
                    if (this.mFinishListener != null) {
                        z = true;
                    }
                    Log.i("ThrowingViHandler", "animation finish by success", valueOf2, valueOf3, Boolean.valueOf(z));
                    if (this.mFinishListener != null) {
                        ThreadUtil.postOnUiThreadDelayed(new i(16, this, view), 500);
                    }
                } else {
                    playLastThrowing();
                }
            }
        }
    }

    private void playLastThrowing() {
        if (this.mLastView != null) {
            Log.d("ThrowingViHandler", "play last", Integer.valueOf(this.mLastViTryCount), Long.valueOf(this.mKey));
            this.mLastViTryCount++;
            playSingleThrowing(new ThrowingAnimator.AnimationInfo(this.mLastView, this.mKey, Integer.MAX_VALUE, true));
        }
    }

    private void playSingleThrowing(ThrowingAnimator.AnimationInfo animationInfo) {
        if (animationInfo.view() != null) {
            if (!animationInfo.isLast()) {
                this.mPlayingList.add(animationInfo.view());
            }
            this.mViewUsedIndex++;
            this.mThrowingVi.play(animationInfo, new a(this));
        }
    }

    private void showEffect(View view) {
        if (ViewUtils.isVisible(view)) {
            ((AiProcessingEffectLayout) view.findViewById(R$id.vi_effect)).playEffect();
        }
    }

    private void stopEffect() {
        Iterator<View> it = this.mWaitingList.iterator();
        while (it.hasNext()) {
            stopEffect(it.next());
        }
    }

    public void bindView(ArrayList<ImageInfo> arrayList) {
        if (this.mRootView != null && arrayList != null && !arrayList.isEmpty()) {
            int i2 = 0;
            if (this.mViewList.isEmpty()) {
                for (int i7 = 0; i7 < 45; i7++) {
                    this.mViewList.add(createView());
                }
            }
            int size = this.mViewUsedIndex % this.mViewList.size();
            while (this.mWaitingList.size() <= this.mViewList.size()) {
                int i8 = size + 1;
                View view = this.mViewList.get(size % this.mViewList.size());
                ViewUtils.removeSelf(view);
                ViewUtils.setVisibility(view, 4);
                bindImage(view, arrayList.get(i2 % arrayList.size()));
                this.mWaitingList.add(view);
                size = i8;
                i2++;
            }
        }
    }

    public void onPause() {
        this.mIsPaused = true;
        stopEffect();
    }

    public void onResume() {
        this.mIsPaused = false;
    }

    public void play(ArrayList<ImageInfo> arrayList) {
        this.mValidPlayingIndex = 0;
        this.mRotationTryCount = 0;
        this.mLastViTryCount = 0;
        this.mLastView = null;
        this.mIsPaused = false;
        this.mKey = System.currentTimeMillis();
        bindView(arrayList);
        int playingCount = getPlayingCount();
        for (int i2 = 0; i2 < playingCount; i2++) {
            ThreadUtil.postOnUiThreadDelayed(new C0451a(14, this), ((long) i2) * 300);
        }
    }

    public void releaseView() {
        if (!this.mWaitingList.isEmpty() || !this.mPlayingList.isEmpty()) {
            this.mWaitingList.clear();
            this.mPlayingList.clear();
            Iterator<View> it = this.mViewList.iterator();
            while (it.hasNext()) {
                View next = it.next();
                if (ViewUtils.isVisible(next)) {
                    stopEffect(next);
                    next.setVisibility(4);
                }
                ViewUtils.removeSelf(next);
                clearImageBitmap(next);
            }
            clearImageBitmap(this.mLastView);
            ViewUtils.removeSelf(this.mLastView);
            ViewUtils.setVisibility(this.mLastView, 4);
            this.mLastView = null;
        }
    }

    public void setListener(OnViEndListener onViEndListener) {
        this.mFinishListener = onViEndListener;
    }

    public void updateInterruptType(InterruptState interruptState) {
        this.mInterrupt = interruptState;
    }

    public void updateLastAnimInfo(ImageInfo imageInfo) {
        if (imageInfo != null) {
            if (this.mLastView == null) {
                this.mLastView = createView();
            }
            bindImage(this.mLastView, imageInfo);
        }
    }

    public void updateLayoutPosition() {
        RectF viewRect = ViewUtils.getViewRect(this.mRootView.findViewById(R$id.thumbnail_area));
        float f = viewRect.left;
        int i2 = (int) (((viewRect.right - f) / 2.0f) + f);
        float f5 = viewRect.top;
        this.mRootView.post(new B(this, i2, (int) (((viewRect.bottom - f5) / 2.0f) + f5), 1));
    }

    private void stopEffect(View view) {
        if (ViewUtils.isVisible(view)) {
            ((AiProcessingEffectLayout) view.findViewById(R$id.vi_effect)).stopEffect();
        }
    }
}
