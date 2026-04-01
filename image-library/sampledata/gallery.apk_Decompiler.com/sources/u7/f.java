package U7;

import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessingViewHandler;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2457a;
    public final /* synthetic */ RemasterProcessingViewHandler b;

    public /* synthetic */ f(RemasterProcessingViewHandler remasterProcessingViewHandler, int i2) {
        this.f2457a = i2;
        this.b = remasterProcessingViewHandler;
    }

    public final void onHandle(Object[] objArr) {
        int i2 = this.f2457a;
        RemasterProcessingViewHandler remasterProcessingViewHandler = this.b;
        switch (i2) {
            case 0:
                remasterProcessingViewHandler.lambda$addActionInvokeListener$0(objArr);
                return;
            case 1:
                remasterProcessingViewHandler.lambda$addActionInvokeListener$1(objArr);
                return;
            case 2:
                remasterProcessingViewHandler.onRemasterCancel(objArr);
                return;
            case 3:
                remasterProcessingViewHandler.onRemasterProcessing(objArr);
                return;
            case 4:
                remasterProcessingViewHandler.onBitmapLoaded(objArr);
                return;
            default:
                remasterProcessingViewHandler.onPhotoPositionChanged(objArr);
                return;
        }
    }
}
