package W1;

import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar$Behavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements View.OnLayoutChangeListener {
    public final /* synthetic */ BottomAppBar$Behavior d;

    public a(BottomAppBar$Behavior bottomAppBar$Behavior) {
        this.d = bottomAppBar$Behavior;
    }

    public final void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (this.d.l.get() == null) {
            view.removeOnLayoutChangeListener(this);
            return;
        }
        throw new ClassCastException();
    }
}
