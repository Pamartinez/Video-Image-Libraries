package B2;

import F0.e;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import androidx.appcompat.animation.SeslRecoilAnimator;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureView;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ParticleLayerView;
import com.samsung.android.gallery.app.ui.list.dragdrop.DragAndDropListScroller;
import com.samsung.android.gallery.app.ui.list.pictures.DeleteAnimationHelper;
import com.samsung.android.gallery.app.ui.list.stories.header.PinAnimHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.AutoScroller;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterFinishingViewHandler;
import com.samsung.android.gallery.module.map.transition.AnimateMarkerTask;
import com.samsung.android.gallery.plugins.motionphoto.ProgressAnimator;
import com.samsung.android.gallery.widget.animations.viewer.AudioEraserAnimation;
import com.samsung.android.gallery.widget.effects.RenderEffectBlur;
import com.samsung.android.gallery.widget.listview.scroller.YearScrollTag;
import com.samsung.android.gallery.widget.photoview.ScaleAnimation;
import com.samsung.android.gallery.widget.tag.MyTagHelper;
import x0.C0323a;
import x0.C0326d;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ h(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                l lVar = (l) obj;
                lVar.getClass();
                lVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
            case 1:
                ((ProgressAnimator) obj).onAnimationUpdate(valueAnimator);
                return;
            case 2:
                ((YearScrollTag) obj).lambda$createTagViewAlphaAnimator$1(valueAnimator);
                return;
            case 3:
                ((ScaleAnimation) obj).lambda$new$0(valueAnimator);
                return;
            case 4:
                ((DragAndDropListScroller) obj).lambda$new$0(valueAnimator);
                return;
            case 5:
                ((RemasterFinishingViewHandler) obj).lambda$new$3(valueAnimator);
                return;
            case 6:
                ((SeslRecoilAnimator) obj).lambda$new$0(valueAnimator);
                return;
            case 7:
                ((PinAnimHandler) obj).lambda$hide$0(valueAnimator);
                return;
            case 8:
                ((MyTagHelper) obj).lambda$setTagAddButtonAnimation$0(valueAnimator);
                return;
            case 9:
                ObjectCapturePopup.shiftPopup$lambda$6((ObjectCapturePopup) obj, valueAnimator);
                return;
            case 10:
                ObjectCaptureView.startScaleDownAnimation$lambda$3$lambda$2((ObjectCaptureView) obj, valueAnimator);
                return;
            case 11:
                ParticleLayerView.startAnimation$lambda$4$lambda$3((ParticleLayerView) obj, valueAnimator);
                return;
            case 12:
                ((AnimateMarkerTask) obj).onAnimationUpdate(valueAnimator);
                return;
            case 13:
                ((SeslRecoilDrawable) obj).lambda$initAnimator$0(valueAnimator);
                return;
            case 14:
                DeleteAnimationHelper.lambda$prepareScaledDelete$1((ViewGroup.LayoutParams) obj, valueAnimator);
                return;
            case 15:
                ((AudioEraserAnimation) obj).lambda$getWidthUpdateAnim$0(valueAnimator);
                return;
            case 16:
                ((StoryHighlightBehavior) obj).lambda$createShapeValueAnimator$2(valueAnimator);
                return;
            case 17:
                ((AutoScroller) obj).lambda$new$0(valueAnimator);
                return;
            case 18:
                w wVar = (w) obj;
                C0323a aVar = wVar.f2092P;
                if (aVar == null) {
                    aVar = C0326d.f2049a;
                }
                if (aVar == C0323a.ENABLED) {
                    wVar.invalidateSelf();
                    return;
                }
                e eVar = wVar.u;
                if (eVar != null) {
                    eVar.q(wVar.e.a());
                    return;
                }
                return;
            default:
                ((RenderEffectBlur) obj).lambda$addBlurAnimation$1(valueAnimator);
                return;
        }
    }
}
