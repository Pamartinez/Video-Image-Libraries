package C1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements View.OnClickListener {
    public final /* synthetic */ Context d;
    public final /* synthetic */ Intent e;

    public d(Context context, Intent intent) {
        this.d = context;
        this.e = intent;
    }

    public final void onClick(View view) {
        try {
            this.d.startActivity(this.e);
        } catch (ActivityNotFoundException e7) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e7);
        }
    }
}
