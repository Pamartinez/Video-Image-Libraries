package androidx.customview.poolingcontainer;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.ViewKt;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0005\u001a\u00020\u0001*\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006\"\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t\"\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0004¢\u0006\u0006\n\u0004\b\n\u0010\t\"(\u0010\r\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b8F@FX\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\"\u0018\u0010\u0014\u001a\u00020\u0011*\u00020\u00008BX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroid/view/View;", "Lme/x;", "callPoolingContainerOnRelease", "(Landroid/view/View;)V", "Landroid/view/ViewGroup;", "callPoolingContainerOnReleaseForChildren", "(Landroid/view/ViewGroup;)V", "", "PoolingContainerListenerHolderTag", "I", "IsPoolingContainerTag", "", "value", "isPoolingContainer", "(Landroid/view/View;)Z", "setPoolingContainer", "(Landroid/view/View;Z)V", "Landroidx/customview/poolingcontainer/PoolingContainerListenerHolder;", "getPoolingContainerListenerHolder", "(Landroid/view/View;)Landroidx/customview/poolingcontainer/PoolingContainerListenerHolder;", "poolingContainerListenerHolder", "customview-poolingcontainer_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PoolingContainer {
    private static final int IsPoolingContainerTag = R$id.is_pooling_container_tag;
    private static final int PoolingContainerListenerHolderTag = R$id.pooling_container_listener_holder_tag;

    public static final void callPoolingContainerOnRelease(View view) {
        j.e(view, "<this>");
        for (View poolingContainerListenerHolder : ViewKt.getAllViews(view)) {
            getPoolingContainerListenerHolder(poolingContainerListenerHolder).onRelease();
        }
    }

    public static final void callPoolingContainerOnReleaseForChildren(ViewGroup viewGroup) {
        j.e(viewGroup, "<this>");
        for (View poolingContainerListenerHolder : ViewGroupKt.getChildren(viewGroup)) {
            getPoolingContainerListenerHolder(poolingContainerListenerHolder).onRelease();
        }
    }

    private static final PoolingContainerListenerHolder getPoolingContainerListenerHolder(View view) {
        int i2 = PoolingContainerListenerHolderTag;
        PoolingContainerListenerHolder poolingContainerListenerHolder = (PoolingContainerListenerHolder) view.getTag(i2);
        if (poolingContainerListenerHolder != null) {
            return poolingContainerListenerHolder;
        }
        PoolingContainerListenerHolder poolingContainerListenerHolder2 = new PoolingContainerListenerHolder();
        view.setTag(i2, poolingContainerListenerHolder2);
        return poolingContainerListenerHolder2;
    }

    public static final void setPoolingContainer(View view, boolean z) {
        j.e(view, "<this>");
        view.setTag(IsPoolingContainerTag, Boolean.valueOf(z));
    }
}
