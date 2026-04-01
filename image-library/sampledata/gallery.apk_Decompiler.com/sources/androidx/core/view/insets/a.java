package androidx.core.view.insets;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements OnApplyWindowInsetsListener {
    public final /* synthetic */ SystemBarStateMonitor d;

    public /* synthetic */ a(SystemBarStateMonitor systemBarStateMonitor) {
        this.d = systemBarStateMonitor;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return this.d.lambda$new$0(view, windowInsetsCompat);
    }
}
