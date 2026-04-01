package A4;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.preference.Preference;
import com.google.android.material.sidesheet.SideSheetBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehaviorAccessibility;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterrupter;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.listview.pinch.v3.HeaderItem;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.util.DeviceUtil;
import com.samsung.scsp.framework.core.util.NetworkUtil;
import i.C0212a;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import n5.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Y implements MediaItemLoader.LoadingStatusInformer, ThumbnailInterrupter, Preference.OnPreferenceClickListener, PropertyAnimator.PropertyAnimationListener, FaultBarrier.ThrowableSupplier, AccessibilityViewCommand {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ Y(int i2, Serializable serializable, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = serializable;
    }

    public Object get() {
        switch (this.d) {
            case 4:
                return Integer.valueOf(Integer.parseInt(DeviceUtil.getSystemProperties((String) this.f, Integer.toString(this.e))));
            default:
                return NetworkUtil.lambda$isConnected$0((Context) this.f, this.e);
        }
    }

    public boolean isInterrupted() {
        return ((FilmStripViewHolder) this.f).lambda$setDefaultImage$3(this.e);
    }

    public void onAnimationEnd(View view) {
        HeaderItem.lambda$getHeightAnimator$0(this.e, (TextUtils.TruncateAt) this.f, view);
    }

    public boolean onPreferenceClick(Preference preference) {
        return LabsDeveloperFragment.lambda$initPreferenceDevOptions$10(this.e, (String[]) this.f, preference);
    }

    public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        String str;
        switch (this.d) {
            case 6:
                return ((StoryHighlightBehaviorAccessibility) this.f).lambda$createAccessibilityViewCommandForState$0(this.e, view, (AccessibilityViewCommand.CommandArguments) null);
            case 7:
                return ((SearchPicturesLocationBehavior) this.f).lambda$createAccessibilityViewCommandForState$2(this.e, view, (AccessibilityViewCommand.CommandArguments) null);
            default:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.f;
                int i2 = this.e;
                if (i2 == 1 || i2 == 2) {
                    StringBuilder sb2 = new StringBuilder("STATE_");
                    if (i2 == 1) {
                        str = "DRAGGING";
                    } else {
                        str = "SETTLING";
                    }
                    throw new IllegalArgumentException(C0212a.p(sb2, str, " should not be set externally."));
                }
                WeakReference weakReference = sideSheetBehavior.s;
                if (weakReference == null || weakReference.get() == null) {
                    sideSheetBehavior.setStateInternal(i2);
                } else {
                    View view2 = (View) sideSheetBehavior.s.get();
                    f fVar = new f(sideSheetBehavior, i2, 5);
                    ViewParent parent = view2.getParent();
                    if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(view2)) {
                        fVar.run();
                    } else {
                        view2.post(fVar);
                    }
                }
                return true;
        }
    }

    public /* synthetic */ Y(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }
}
