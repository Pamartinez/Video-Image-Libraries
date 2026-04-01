package z2;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.sec.android.gallery3d.R;

/* renamed from: z2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0362a implements Runnable {
    public final /* synthetic */ q d;
    public final /* synthetic */ SnackbarContentLayout e;
    public final /* synthetic */ TextView f;
    public final /* synthetic */ Button g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2202h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2203i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ int f2204j;
    public final /* synthetic */ int k;

    public /* synthetic */ C0362a(q qVar, SnackbarContentLayout snackbarContentLayout, TextView textView, Button button, int i2, int i7, int i8, int i10) {
        this.d = qVar;
        this.e = snackbarContentLayout;
        this.f = textView;
        this.g = button;
        this.f2202h = i2;
        this.f2203i = i7;
        this.f2204j = i8;
        this.k = i10;
    }

    public final void run() {
        q qVar = this.d;
        Context context = qVar.f2220h;
        SnackbarContentLayout snackbarContentLayout = this.e;
        snackbarContentLayout.setElevation((float) context.getResources().getDimensionPixelSize(R$dimen.sesl_figma_elevation_md));
        TextView textView = this.f;
        textView.setAlpha(0.0f);
        textView.animate().alpha(1.0f).setDuration(150).setInterpolator(AnimationUtils.loadInterpolator(context, R.interpolator.sesl_snackbar_suggestion_interpolator)).setStartDelay(150).start();
        Button button = this.g;
        button.setAlpha(0.0f);
        button.animate().alpha(1.0f).setDuration(150).setInterpolator(AnimationUtils.loadInterpolator(context, R.interpolator.sesl_snackbar_suggestion_interpolator)).setStartDelay(150).start();
        SpringAnimation springAnimation = new SpringAnimation(snackbarContentLayout, new f(qVar, this.f2202h, this.f2203i, this.f2204j, this.k, snackbarContentLayout));
        springAnimation.setStartValue(0.0f);
        springAnimation.setSpring(new SpringForce().setStiffness(50.0f).setDampingRatio(0.72f));
        springAnimation.animateToFinalPosition(1.0f);
        springAnimation.start();
        SpringAnimation springAnimation2 = new SpringAnimation(qVar.f2221i, DynamicAnimation.TRANSLATION_Y);
        springAnimation2.cancel();
        springAnimation2.setSpring(new SpringForce().setStiffness(300.0f).setDampingRatio(0.72f));
        springAnimation2.animateToFinalPosition(0.0f);
        springAnimation2.setStartVelocity(0.1f);
        springAnimation2.start();
        springAnimation.addEndListener(new C0363b(qVar));
    }
}
