package c2;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends ImageView {
    public boolean d;
    public final s e;

    /* JADX WARNING: type inference failed for: r0v4, types: [android.os.CountDownTimer, c2.s] */
    public t(Context context) {
        super(context, (AttributeSet) null, -1);
        setElevation(getResources().getDimension(R.dimen.expansion_button_elevation));
        long integer = (long) context.getResources().getInteger(R.integer.expansion_button_duration);
        this.e = new CountDownTimer(integer, integer);
    }

    public final int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (this.d) {
            View.mergeDrawableStates(onCreateDrawableState, new int[]{R.attr.state_expansion_button_expanded});
        }
        return onCreateDrawableState;
    }

    public void setAutomaticDisappear(boolean z) {
        this.e.cancel();
    }

    public void setExpanded(boolean z) {
        this.d = z;
        refreshDrawableState();
    }

    public void setFloated(boolean z) {
        refreshDrawableState();
    }

    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (i2 == 0) {
            s sVar = this.e;
            sVar.cancel();
            sVar.start();
        }
    }
}
