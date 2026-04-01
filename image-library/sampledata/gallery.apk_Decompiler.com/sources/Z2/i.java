package z2;

import Kb.b;
import a8.d;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ q e;

    public /* synthetic */ i(q qVar, int i2) {
        this.d = i2;
        this.e = qVar;
    }

    public final void run() {
        Context context;
        int i2 = this.d;
        q qVar = this.e;
        switch (i2) {
            case 0:
                p pVar = qVar.f2221i;
                if (pVar != null && (context = qVar.f2220h) != null) {
                    int height = ((WindowManager) context.getSystemService("window")).getCurrentWindowMetrics().getBounds().height();
                    int[] iArr = new int[2];
                    pVar.getLocationInWindow(iArr);
                    int height2 = (height - (pVar.getHeight() + iArr[1])) + ((int) pVar.getTranslationY());
                    int i7 = qVar.r;
                    if (height2 >= i7) {
                        qVar.s = i7;
                        return;
                    }
                    ViewGroup.LayoutParams layoutParams = pVar.getLayoutParams();
                    if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                        Log.w(q.E, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                        return;
                    }
                    int i8 = qVar.r;
                    qVar.s = i8;
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginLayoutParams.bottomMargin = (i8 - height2) + marginLayoutParams.bottomMargin;
                    pVar.requestLayout();
                    return;
                }
                return;
            case 1:
                qVar.c(3);
                return;
            default:
                q qVar2 = this.e;
                p pVar2 = qVar2.f2221i;
                if (pVar2 != null) {
                    if (pVar2.getParent() != null) {
                        pVar2.setVisibility(0);
                    }
                    if (pVar2.getAnimationMode() == 1) {
                        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                        ofFloat.setInterpolator(qVar2.d);
                        ofFloat.addUpdateListener(new b(4, qVar2, (SnackbarContentLayout) pVar2.findViewById(R.id.snackbar_content_layout)));
                        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.2f, 1.0f});
                        ofFloat2.setInterpolator(qVar2.f);
                        ofFloat2.addUpdateListener(new e(qVar2, 0, (byte) 0));
                        AnimatorSet animatorSet = new AnimatorSet();
                        qVar2.e(true);
                        animatorSet.playTogether(new Animator[]{ofFloat});
                        animatorSet.setDuration(150).setInterpolator(q.f2216A);
                        animatorSet.addListener(new C0364c(qVar2, 0));
                        animatorSet.start();
                        return;
                    } else if (pVar2.getAnimationMode() == 2) {
                        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) pVar2.findViewById(R.id.snackbar_content_layout);
                        TextView textView = (TextView) snackbarContentLayout.findViewById(R.id.snackbar_text);
                        Button button = (Button) snackbarContentLayout.findViewById(R.id.snackbar_action);
                        snackbarContentLayout.setAlpha(0.0f);
                        snackbarContentLayout.setScaleX(1.0f);
                        snackbarContentLayout.setScaleY(1.0f);
                        textView.setAlpha(0.0f);
                        button.setAlpha(0.0f);
                        q.f(snackbarContentLayout, 0);
                        textView.setVisibility(0);
                        button.setVisibility(0);
                        snackbarContentLayout.post(new d((Object) qVar2, (Object) snackbarContentLayout, (Object) textView, (Object) button, 26));
                        return;
                    } else {
                        int height3 = pVar2.getHeight();
                        ViewGroup.LayoutParams layoutParams2 = pVar2.getLayoutParams();
                        if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                            height3 += ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
                        }
                        pVar2.setTranslationY((float) height3);
                        ValueAnimator valueAnimator = new ValueAnimator();
                        valueAnimator.setIntValues(new int[]{height3, 0});
                        valueAnimator.setInterpolator(qVar2.e);
                        valueAnimator.setDuration((long) qVar2.f2219c);
                        valueAnimator.addListener(new C0364c(qVar2, 1));
                        valueAnimator.addUpdateListener(new e(qVar2, height3));
                        valueAnimator.start();
                        return;
                    }
                } else {
                    return;
                }
        }
    }
}
