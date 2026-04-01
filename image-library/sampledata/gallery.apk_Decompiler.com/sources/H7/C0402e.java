package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.AudioEraserController;
import java.util.function.Predicate;

/* renamed from: H7.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0402e implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2344a;
    public final /* synthetic */ AudioEraserController b;

    public /* synthetic */ C0402e(AudioEraserController audioEraserController, int i2) {
        this.f2344a = i2;
        this.b = audioEraserController;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2344a;
        AudioEraserController audioEraserController = this.b;
        switch (i2) {
            case 0:
                return audioEraserController.lambda$new$0(obj);
            default:
                return audioEraserController.lambda$new$1(obj);
        }
    }
}
