package androidx.fragment.app;

import android.view.View;
import androidx.collection.ArrayMap;
import androidx.transition.FragmentTransitionSupport;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\t\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J3\u0010\f\u001a\u00020\u000b*\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0007¢\u0006\u0004\b\f\u0010\rJC\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\u0014\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u001b\u001a\u00020\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0006\n\u0004\b!\u0010 ¨\u0006\""}, d2 = {"Landroidx/fragment/app/FragmentTransition;", "", "<init>", "()V", "Landroidx/fragment/app/FragmentTransitionImpl;", "resolveSupportImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "Landroidx/collection/ArrayMap;", "", "Landroid/view/View;", "namedViews", "Lme/x;", "retainValues", "(Landroidx/collection/ArrayMap;Landroidx/collection/ArrayMap;)V", "Landroidx/fragment/app/Fragment;", "inFragment", "outFragment", "", "isPop", "sharedElements", "isStart", "callSharedElementStartEnd", "(Landroidx/fragment/app/Fragment;Landroidx/fragment/app/Fragment;ZLandroidx/collection/ArrayMap;Z)V", "", "views", "", "visibility", "setViewVisibility", "(Ljava/util/List;I)V", "supportsTransition", "()Z", "PLATFORM_IMPL", "Landroidx/fragment/app/FragmentTransitionImpl;", "SUPPORT_IMPL", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FragmentTransition {
    public static final FragmentTransition INSTANCE;
    public static final FragmentTransitionImpl PLATFORM_IMPL = new FragmentTransitionCompat21();
    public static final FragmentTransitionImpl SUPPORT_IMPL;

    static {
        FragmentTransition fragmentTransition = new FragmentTransition();
        INSTANCE = fragmentTransition;
        SUPPORT_IMPL = fragmentTransition.resolveSupportImpl();
    }

    private FragmentTransition() {
    }

    public static final void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z3) {
        j.e(fragment, "inFragment");
        j.e(fragment2, "outFragment");
        j.e(arrayMap, "sharedElements");
        if (z) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    private final FragmentTransitionImpl resolveSupportImpl() {
        try {
            return FragmentTransitionSupport.class.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final void retainValues(ArrayMap<String, String> arrayMap, ArrayMap<String, View> arrayMap2) {
        j.e(arrayMap, "<this>");
        j.e(arrayMap2, "namedViews");
        int size = arrayMap.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            if (!arrayMap2.containsKey(arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    public static final void setViewVisibility(List<? extends View> list, int i2) {
        j.e(list, "views");
        for (View visibility : list) {
            visibility.setVisibility(i2);
        }
    }

    public static final boolean supportsTransition() {
        if (PLATFORM_IMPL == null && SUPPORT_IMPL == null) {
            return false;
        }
        return true;
    }
}
