package z2;

import G0.c;
import Kb.b;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.BaseTransientBottomBar$Behavior;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements Handler.Callback {
    public final boolean handleMessage(Message message) {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        int i2 = message.what;
        int i7 = 0;
        if (i2 == 0) {
            q qVar = (q) message.obj;
            p pVar = qVar.f2221i;
            ViewGroup viewGroup = qVar.g;
            if (pVar.getParent() == null) {
                ViewGroup.LayoutParams layoutParams = pVar.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
                    BaseTransientBottomBar$Behavior baseTransientBottomBar$Behavior = new BaseTransientBottomBar$Behavior();
                    c cVar = baseTransientBottomBar$Behavior.m;
                    cVar.getClass();
                    cVar.e = qVar.f2225x;
                    baseTransientBottomBar$Behavior.e = new l(qVar);
                    layoutParams2.setBehavior(baseTransientBottomBar$Behavior);
                    if (qVar.b() == null) {
                        layoutParams2.insetEdge = 80;
                    }
                }
                pVar.n = true;
                viewGroup.addView(pVar);
                pVar.n = false;
                if (qVar.b() != null) {
                    int[] iArr = new int[2];
                    qVar.b().getLocationOnScreen(iArr);
                    int i8 = iArr[1];
                    int[] iArr2 = new int[2];
                    viewGroup.getLocationOnScreen(iArr2);
                    i7 = (viewGroup.getHeight() + iArr2[1]) - i8;
                }
                qVar.q = i7;
                qVar.i();
                pVar.setVisibility(4);
            }
            if (ViewCompat.isLaidOut(pVar)) {
                qVar.g();
                return true;
            }
            qVar.t = true;
            return true;
        } else if (i2 != 1) {
            return false;
        } else {
            q qVar2 = (q) message.obj;
            int i10 = message.arg1;
            p pVar2 = qVar2.f2221i;
            p pVar3 = qVar2.f2221i;
            if ((pVar2.f != 2 && (accessibilityManager = qVar2.v) != null && ((enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) == null || !enabledAccessibilityServiceList.isEmpty())) || pVar2.getVisibility() != 0) {
                qVar2.c(i10);
                return true;
            } else if (pVar2.getAnimationMode() == 1 || pVar2.getAnimationMode() == 2) {
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
                ofFloat.setInterpolator(qVar2.d);
                ofFloat.addUpdateListener(new b(4, qVar2, (SnackbarContentLayout) pVar3.findViewById(R.id.snackbar_content_layout)));
                qVar2.e(false);
                ofFloat.setDuration(150).setInterpolator(q.f2216A);
                ofFloat.addListener(new C0365d(qVar2, i10, 0));
                ofFloat.start();
                return true;
            } else {
                ValueAnimator valueAnimator = new ValueAnimator();
                int height = pVar3.getHeight();
                ViewGroup.LayoutParams layoutParams3 = pVar3.getLayoutParams();
                if (layoutParams3 instanceof ViewGroup.MarginLayoutParams) {
                    height += ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin;
                }
                valueAnimator.setIntValues(new int[]{0, height});
                valueAnimator.setInterpolator(qVar2.e);
                valueAnimator.setDuration((long) qVar2.f2219c);
                valueAnimator.addListener(new C0365d(qVar2, i10, 1));
                valueAnimator.addUpdateListener(new e(qVar2, 2, (byte) 0));
                valueAnimator.start();
                return true;
            }
        }
    }
}
