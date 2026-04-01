package com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c0.C0086a;
import com.samsung.android.app.sdk.deepsky.textextraction.R$dimen;
import com.samsung.android.app.sdk.deepsky.textextraction.R$id;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.util.CapsuleAnimationUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ\u001f\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J7\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u001a\u0010\u001bJ7\u0010 \u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f¢\u0006\u0004\b \u0010!J%\u0010\"\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\f¢\u0006\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/TextCapsuleAnimator;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/util/CapsuleAnimationUtil;", "<init>", "()V", "Landroid/view/View;", "view", "Landroid/animation/ObjectAnimator;", "getShowAlphaAnimator", "(Landroid/view/View;)Landroid/animation/ObjectAnimator;", "getHideAlphaAnimator", "Landroid/content/Context;", "context", "", "resId", "getPixelSize", "(Landroid/content/Context;I)I", "capsuleView", "Landroid/widget/TextView;", "titleView", "", "hasSingleItemOnly", "Landroid/animation/AnimatorSet;", "getShowAnimation", "(Landroid/content/Context;Landroid/view/View;Landroid/widget/TextView;Z)Landroid/animation/AnimatorSet;", "Landroid/widget/ImageView;", "iconView", "getHideAnimation", "(Landroid/content/Context;Landroid/view/View;Landroid/widget/TextView;Landroid/widget/ImageView;Z)Landroid/animation/AnimatorSet;", "capsuleListViewWidth", "capsuleListView", "maxHeight", "size", "showMoreCapsuleListAnimation", "(Landroid/content/Context;ILandroid/view/View;II)Landroid/animation/AnimatorSet;", "hideMoreCapsuleListAnimation", "(Landroid/content/Context;Landroid/view/View;I)Landroid/animation/AnimatorSet;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextCapsuleAnimator extends CapsuleAnimationUtil {
    public static final TextCapsuleAnimator INSTANCE = new TextCapsuleAnimator();

    private TextCapsuleAnimator() {
    }

    private final ObjectAnimator getHideAlphaAnimator(View view) {
        return CapsuleAnimationUtil.getViewAlphaAnimator$default(this, view, 1.0f, 0.0f, 100, 0, (Interpolator) null, 48, (Object) null);
    }

    private final int getPixelSize(Context context, int i2) {
        return context.getResources().getDimensionPixelSize(i2);
    }

    private final ObjectAnimator getShowAlphaAnimator(View view) {
        return CapsuleAnimationUtil.getViewAlphaAnimator$default(this, view, 0.0f, 1.0f, 200, 100, (Interpolator) null, 32, (Object) null);
    }

    public final AnimatorSet getHideAnimation(Context context, View view, TextView textView, ImageView imageView, boolean z) {
        int i2;
        Context context2 = context;
        View view2 = view;
        ImageView imageView2 = imageView;
        j.e(context2, "context");
        j.e(view2, "capsuleView");
        TextView textView2 = textView;
        j.e(textView2, "titleView");
        j.e(imageView2, "iconView");
        Log.d("TextCapsuleAnimator", "Building hide collapse animation for capsule");
        int min = Math.min(((int) textView2.getPaint().measureText(textView2.getText().toString())) + 1, getPixelSize(context2, R$dimen.capsule_extract_text_action_expanded_max_width));
        int pixelSize = getPixelSize(context2, R$dimen.capsule_extract_text_action_view_expanded_margin_start);
        int pixelSize2 = getPixelSize(context2, R$dimen.capsule_extract_text_action_view_height);
        if (!z) {
            i2 = getPixelSize(context2, R$dimen.capsule_extract_text_action_more_icon_dimension) + getPixelSize(context2, R$dimen.capsule_extract_text_action_margin_between_icon_divider) + getPixelSize(context2, R$dimen.capsule_extract_text_divider_width);
        } else {
            i2 = 0;
        }
        int i7 = min + pixelSize + pixelSize2 + i2 + 1;
        C0086a.C(i7, "Final capsule width: ", "TextCapsuleAnimator");
        LinearLayout linearLayout = (LinearLayout) view2.findViewById(R$id.expandable_capsule_more_icon_layout);
        linearLayout.setAlpha(1.0f);
        int i8 = i7;
        ObjectAnimator viewAlphaAnimator$default = CapsuleAnimationUtil.getViewAlphaAnimator$default(this, linearLayout, 1.0f, 0.0f, 100, 0, (Interpolator) null, 48, (Object) null);
        TextView textView3 = textView2;
        ObjectAnimator objectAnimator = viewAlphaAnimator$default;
        TextView textView4 = textView3;
        ObjectAnimator textViewAlphaAnimator$default = CapsuleAnimationUtil.getTextViewAlphaAnimator$default(this, textView4, 1.0f, 0.0f, 100, 0, (Interpolator) null, 48, (Object) null);
        float translationX = view2.getTranslationX();
        float pixelSize3 = ((float) (pixelSize - getPixelSize(context2, R$dimen.capsule_extract_text_action_view_icon_margin))) + ((float) 1);
        View findViewById = view2.findViewById(R$id.expandable_capsule_item_icon);
        j.d(findViewById, "findViewById(...)");
        ObjectAnimator moveXByAnimator$default = CapsuleAnimationUtil.getMoveXByAnimator$default(this, findViewById, translationX, translationX - pixelSize3, 200, (Interpolator) null, 16, (Object) null);
        View view3 = view2;
        ValueAnimator valueAnimatorAlongWidth$default = CapsuleAnimationUtil.getValueAnimatorAlongWidth$default(this, view3, i8, pixelSize2, 400, 0, (Interpolator) null, 48, (Object) null);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimatorAlongWidth$default).with(objectAnimator);
        animatorSet.play(textViewAlphaAnimator$default);
        animatorSet.play(moveXByAnimator$default);
        TextCapsuleAnimator$getHideAnimation$lambda$2$$inlined$doOnEnd$1 textCapsuleAnimator$getHideAnimation$lambda$2$$inlined$doOnEnd$1 = new TextCapsuleAnimator$getHideAnimation$lambda$2$$inlined$doOnEnd$1(imageView2, view3);
        animatorSet.addListener(textCapsuleAnimator$getHideAnimation$lambda$2$$inlined$doOnEnd$1);
        animatorSet.addListener(textCapsuleAnimator$getHideAnimation$lambda$2$$inlined$doOnEnd$1);
        return animatorSet;
    }

    public final AnimatorSet getShowAnimation(Context context, View view, TextView textView, boolean z) {
        int i2;
        Context context2 = context;
        View view2 = view;
        j.e(context2, "context");
        j.e(view2, "capsuleView");
        TextView textView2 = textView;
        j.e(textView2, "titleView");
        Log.d("TextCapsuleAnimator", "Building show expand animation for capsule");
        int min = Math.min(((int) textView2.getPaint().measureText(textView2.getText().toString())) + 1, getPixelSize(context2, R$dimen.capsule_extract_text_action_expanded_max_width));
        int pixelSize = getPixelSize(context2, R$dimen.capsule_extract_text_action_view_expanded_margin_start);
        int pixelSize2 = getPixelSize(context2, R$dimen.capsule_extract_text_action_view_icon_margin);
        int pixelSize3 = getPixelSize(context2, R$dimen.capsule_extract_text_action_view_height);
        if (!z) {
            i2 = getPixelSize(context2, R$dimen.capsule_extract_text_action_more_icon_dimension) + getPixelSize(context2, R$dimen.capsule_extract_text_action_margin_between_icon_divider) + getPixelSize(context2, R$dimen.capsule_extract_text_divider_width) + pixelSize2;
        } else {
            i2 = 0;
        }
        int i7 = min + pixelSize + pixelSize3 + i2 + 1;
        Log.d("TextCapsuleAnimator", "Final capsule width: " + i7);
        textView2.getLayoutParams().width = min;
        LinearLayout linearLayout = (LinearLayout) view2.findViewById(R$id.expandable_capsule_more_icon_layout);
        linearLayout.setAlpha(0.0f);
        int i8 = i7;
        ObjectAnimator viewAlphaAnimator$default = CapsuleAnimationUtil.getViewAlphaAnimator$default(this, linearLayout, 0.0f, 1.0f, 200, 100, (Interpolator) null, 32, (Object) null);
        TextView textView3 = textView2;
        ObjectAnimator objectAnimator = viewAlphaAnimator$default;
        ObjectAnimator textViewAlphaAnimator$default = CapsuleAnimationUtil.getTextViewAlphaAnimator$default(this, textView3, 0.0f, 1.0f, 200, 0, (Interpolator) null, 48, (Object) null);
        float translationX = view2.getTranslationX();
        View findViewById = view2.findViewById(R$id.expandable_capsule_item_icon);
        j.d(findViewById, "findViewById(...)");
        ObjectAnimator moveXByAnimator$default = CapsuleAnimationUtil.getMoveXByAnimator$default(this, findViewById, translationX - (((float) (pixelSize - pixelSize2)) + ((float) 1)), translationX, 200, (Interpolator) null, 16, (Object) null);
        ValueAnimator valueAnimatorAlongWidth$default = CapsuleAnimationUtil.getValueAnimatorAlongWidth$default(this, view2, pixelSize3, i8, 400, 0, (Interpolator) null, 48, (Object) null);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(textViewAlphaAnimator$default).with(objectAnimator);
        animatorSet.play(moveXByAnimator$default);
        animatorSet.play(valueAnimatorAlongWidth$default);
        return animatorSet;
    }

    public final AnimatorSet hideMoreCapsuleListAnimation(Context context, View view, int i2) {
        j.e(context, "context");
        j.e(view, "capsuleListView");
        Log.d("TextCapsuleAnimator", "Building hide animation for capsule list");
        int pixelSize = getPixelSize(context, R$dimen.capsule_extract_text_action_view_height);
        AnimatorSet animatorSet = new AnimatorSet();
        TextCapsuleAnimator textCapsuleAnimator = INSTANCE;
        View view2 = view;
        animatorSet.playTogether(new Animator[]{CapsuleAnimationUtil.getValueAnimatorAlongHeight$default(textCapsuleAnimator, view2, (i2 + 1) * pixelSize, pixelSize, 400, 0, (Interpolator) null, 48, (Object) null), textCapsuleAnimator.getHideAlphaAnimator(view2)});
        animatorSet.play(textCapsuleAnimator.getShowAlphaAnimator(view2)).after(200);
        return animatorSet;
    }

    public final AnimatorSet showMoreCapsuleListAnimation(Context context, int i2, View view, int i7, int i8) {
        int i10;
        j.e(context, "context");
        j.e(view, "capsuleListView");
        Log.d("TextCapsuleAnimator", "Building show animation for capsule list");
        int pixelSize = getPixelSize(context, R$dimen.capsule_extract_text_action_more_icon_dimension) + getPixelSize(context, R$dimen.capsule_extract_text_action_margin_between_icon_divider) + getPixelSize(context, R$dimen.capsule_extract_text_divider_width);
        int pixelSize2 = getPixelSize(context, R$dimen.capsule_extract_text_action_expanded_max_width);
        TextView textView = (TextView) view.findViewById(R$id.expandable_capsule_list_item_text);
        textView.getLayoutParams().width = Math.max(textView.getWidth() - pixelSize, pixelSize2);
        view.getLayoutParams().width = i2;
        int pixelSize3 = getPixelSize(context, R$dimen.capsule_extract_text_action_view_height);
        view.getLayoutParams().height = pixelSize3;
        if (i7 == 0 || (i8 + 1) * pixelSize3 <= i7) {
            i10 = (i8 + 1) * pixelSize3;
        } else {
            i10 = i7;
        }
        if (isRemoveAnimationOn(context)) {
            view.getLayoutParams().height = i10;
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        TextCapsuleAnimator textCapsuleAnimator = INSTANCE;
        animatorSet.play(textCapsuleAnimator.getShowAlphaAnimator(view));
        animatorSet.play(CapsuleAnimationUtil.getValueAnimatorAlongHeight$default(textCapsuleAnimator, view, pixelSize3, i10, 400, 0, (Interpolator) null, 48, (Object) null)).after(textCapsuleAnimator.getHideAlphaAnimator(view));
        return animatorSet;
    }
}
