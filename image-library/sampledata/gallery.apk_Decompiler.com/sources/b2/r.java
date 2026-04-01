package B2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends AnimatorListenerAdapter {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextView e;
    public final /* synthetic */ int f;
    public final /* synthetic */ TextView g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ t f64h;

    public r(t tVar, int i2, TextView textView, int i7, TextView textView2) {
        this.f64h = tVar;
        this.d = i2;
        this.e = textView;
        this.f = i7;
        this.g = textView2;
    }

    public final void onAnimationEnd(Animator animator) {
        AppCompatTextView appCompatTextView;
        int i2 = this.d;
        t tVar = this.f64h;
        tVar.n = i2;
        tVar.l = null;
        TextView textView = this.e;
        if (textView != null) {
            textView.setVisibility(4);
            if (this.f == 1 && (appCompatTextView = tVar.r) != null) {
                appCompatTextView.setText((CharSequence) null);
            }
        }
        TextView textView2 = this.g;
        if (textView2 != null) {
            textView2.setTranslationY(0.0f);
            textView2.setAlpha(1.0f);
        }
    }

    public final void onAnimationStart(Animator animator) {
        TextView textView = this.g;
        if (textView != null) {
            textView.setVisibility(0);
            textView.setAlpha(0.0f);
        }
    }
}
