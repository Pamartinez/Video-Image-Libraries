package Gf;

import Ae.b;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends j {
    public final /* synthetic */ int g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(m mVar, ConcurrentHashMap concurrentHashMap, b bVar, int i2) {
        super(mVar, concurrentHashMap, bVar);
        this.g = i2;
    }

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 != 3) {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i2 != 3) {
            i7 = 3;
        } else {
            i7 = 2;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1) {
            objArr[0] = "map";
        } else if (i2 == 2) {
            objArr[0] = "computation";
        } else if (i2 != 3) {
            objArr[0] = "storageManager";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
        }
        if (i2 != 3) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
        } else {
            objArr[1] = "computeIfAbsent";
        }
        if (i2 == 2) {
            objArr[2] = "computeIfAbsent";
        } else if (i2 != 3) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 != 3) {
            th = new IllegalArgumentException(format);
        } else {
            th = new IllegalStateException(format);
        }
        throw th;
    }

    public Object invoke(Object obj) {
        switch (this.g) {
            case 1:
                Object invoke = super.invoke(obj);
                if (invoke != null) {
                    return invoke;
                }
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", new Object[]{"kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull", "invoke"}));
            default:
                return super.invoke(obj);
        }
    }
}
