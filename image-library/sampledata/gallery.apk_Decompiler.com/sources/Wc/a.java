package Wc;

import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.motionphoto.utils.ex.WrapVSWEnginePlugin;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.o3dp.jpeg3dcontainer.model.GContainer;
import com.samsung.o3dp.jpeg3dcontainer.model.Jpeg3d;
import com.samsung.o3dp.jpeg3dcontainer.model.Segment;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f891a;

    public /* synthetic */ a(int i2) {
        this.f891a = i2;
    }

    public final Object apply(Object obj, Object obj2) {
        switch (this.f891a) {
            case 0:
                return Integer.valueOf(StatusCodes.UNDEFINED);
            case 1:
                return WrapVSWEnginePlugin.x1Upscaler$lambda$2((MediaBuffer) obj, (String) obj2);
            case 2:
                return WrapVSWEnginePlugin.dumpImage$lambda$9((MediaBuffer) obj, (String) obj2);
            case 3:
                return Jpeg3d.lambda$cutOffBytes$1((Integer) obj, (GContainer.Item) obj2);
            case 4:
                return Integer.valueOf(((Segment) obj2).getLength() + ((Integer) obj).intValue());
            default:
                return Log.getMsg((String) obj, (String) obj2);
        }
    }
}
