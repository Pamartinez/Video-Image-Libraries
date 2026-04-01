package Y1;

import android.view.View;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements AccessibilityViewCommand {
    public final /* synthetic */ int d;
    public final /* synthetic */ BottomSheetBehavior e;

    public d(BottomSheetBehavior bottomSheetBehavior, int i2) {
        this.e = bottomSheetBehavior;
        this.d = i2;
    }

    public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        this.e.setState(this.d);
        return true;
    }
}
