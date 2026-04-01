package androidx.core.oneui.common.internal.semblurinfo;

import android.view.View;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H'¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H'¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/core/oneui/common/internal/semblurinfo/SemBlurInfoState;", "", "", "blurMode", "<init>", "(I)V", "Landroid/view/View;", "view", "", "applyBlurInfo", "(Landroid/view/View;)Z", "Lme/x;", "clearBlurInfo", "(Landroid/view/View;)V", "I", "getBlurMode", "()I", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SemBlurInfoState {
    private final int blurMode;

    public SemBlurInfoState(int i2) {
        this.blurMode = i2;
    }

    public abstract boolean applyBlurInfo(View view);

    public abstract void clearBlurInfo(View view);
}
