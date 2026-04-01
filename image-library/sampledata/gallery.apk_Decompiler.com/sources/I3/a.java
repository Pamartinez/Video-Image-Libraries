package i3;

import Ae.b;
import android.graphics.Point;
import com.samsung.android.app.sdk.deepsky.objectcapture.multi.MultiObjectViewManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ float d;
    public final /* synthetic */ Point e;

    public /* synthetic */ a(float f, Point point) {
        this.d = f;
        this.e = point;
    }

    public final Object invoke(Object obj) {
        return MultiObjectViewManager.fixDimensions$lambda$5(this.d, this.e, (Point) obj);
    }
}
