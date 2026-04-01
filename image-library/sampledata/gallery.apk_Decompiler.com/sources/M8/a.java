package M8;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.scsp.common.PushVoFactory;
import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements MediaScannerListener, FaultBarrier.ThrowableSupplier, ObjectConstructor, UniqueKey {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;

    public /* synthetic */ a(String str, int i2) {
        this.d = i2;
        this.e = str;
    }

    public Object construct() {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 2:
                return ConstructorConstructor.lambda$newUnsafeAllocator$20(str);
            case 3:
                return ConstructorConstructor.lambda$newDefaultConstructor$7(str);
            case 4:
                return ConstructorConstructor.lambda$newDefaultConstructor$8(str);
            case 5:
                return ConstructorConstructor.lambda$get$2(str);
            case 6:
                return ConstructorConstructor.lambda$get$3(str);
            default:
                return ConstructorConstructor.lambda$get$4(str);
        }
    }

    public Object get() {
        int i2 = this.d;
        String str = this.e;
        switch (i2) {
            case 1:
                return Class.forName("android.os.SystemProperties");
            default:
                return PushVoFactory.lambda$create$1(str);
        }
    }

    public int getKey() {
        return this.e.hashCode();
    }

    public void onCompleted() {
        Log.d("PPP_Helper", "save temp success : " + this.e);
    }
}
