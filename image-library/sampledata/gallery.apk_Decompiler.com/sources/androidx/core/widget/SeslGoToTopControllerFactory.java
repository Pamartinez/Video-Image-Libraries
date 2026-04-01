package androidx.core.widget;

import android.util.Log;
import androidx.core.widget.SeslGoToTopController;
import androidx.core.widget.SeslNestedGoToTopController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslGoToTopControllerFactory {

    /* renamed from: androidx.core.widget.SeslGoToTopControllerFactory$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$core$widget$SeslGoToTopControllerFactory$ControllerType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                androidx.core.widget.SeslGoToTopControllerFactory$ControllerType[] r0 = androidx.core.widget.SeslGoToTopControllerFactory.ControllerType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$core$widget$SeslGoToTopControllerFactory$ControllerType = r0
                androidx.core.widget.SeslGoToTopControllerFactory$ControllerType r1 = androidx.core.widget.SeslGoToTopControllerFactory.ControllerType.RECYCLERVIEW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$core$widget$SeslGoToTopControllerFactory$ControllerType     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.core.widget.SeslGoToTopControllerFactory$ControllerType r1 = androidx.core.widget.SeslGoToTopControllerFactory.ControllerType.NESTEDSCROLLVIEW     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.SeslGoToTopControllerFactory.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ControllerType {
        RECYCLERVIEW,
        NESTEDSCROLLVIEW
    }

    public static SeslGoToTopController createController(ControllerType controllerType, SeslGoToTopConfig seslGoToTopConfig, SeslGoToTopController.Host host, String str) {
        try {
            int i2 = AnonymousClass1.$SwitchMap$androidx$core$widget$SeslGoToTopControllerFactory$ControllerType[controllerType.ordinal()];
            if (i2 == 1) {
                return ((SeslGoToTopController.Builder) ((SeslGoToTopController.Builder) new SeslGoToTopController.Builder().setHost(host)).setConfig(seslGoToTopConfig)).build();
            }
            if (i2 == 2) {
                return ((SeslNestedGoToTopController.Builder) ((SeslNestedGoToTopController.Builder) new SeslNestedGoToTopController.Builder().setHost(host)).setConfig(seslGoToTopConfig)).build();
            }
            Log.e(str, "Unknown controller type: " + controllerType);
            return null;
        } catch (Throwable th) {
            Log.e(str, "Failed to initialize GoToTopController", th);
            return null;
        }
    }
}
