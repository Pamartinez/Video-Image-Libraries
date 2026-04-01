package A;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.gallery.module.media.quramsoft.AgifDecoder;
import com.samsung.android.gallery.module.media.quramsoft.AgifEncoder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class a {
    public static void A(Object[] objArr, StringBuilder sb2, String str) {
        sb2.append(Logger.vt(objArr));
        Log.d(str, sb2.toString());
    }

    public static void B(int i2, String str, String str2) {
        Log.e(str2, str + i2);
    }

    public static void C(String str, String str2, String str3) {
        androidx.media3.common.util.Log.w(str3, str + str2);
    }

    public static void D(int i2, String str, String str2) {
        androidx.media3.common.util.Log.w(str2, str + i2);
    }

    public static int a(Parcelable.Creator creator, Parcel parcel, ArrayList arrayList, int i2, int i7) {
        arrayList.add(creator.createFromParcel(parcel));
        return i2 + i7;
    }

    public static int b(Class cls, Parcel parcel, ArrayList arrayList, int i2, int i7) {
        arrayList.add(parcel.readParcelable(cls.getClassLoader()));
        return i2 + i7;
    }

    public static Object c(int i2, int i7, ArrayList arrayList) {
        return arrayList.get(Math.min(i7, arrayList.size() - i2));
    }

    public static String d(int i2, int i7, String str, String str2, String str3) {
        return str + i2 + str2 + i7 + str3;
    }

    public static String e(long j2, String str, String str2) {
        return str + j2 + str2;
    }

    public static String f(String str, long j2) {
        return str + j2;
    }

    public static String g(w wVar, Class cls, StringBuilder sb2) {
        sb2.append(wVar.b(cls));
        return sb2.toString();
    }

    public static StringBuilder h(int i2, int i7, String str, String str2, String str3) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(i2);
        sb2.append(str2);
        sb2.append(i7);
        sb2.append(str3);
        return sb2;
    }

    public static StringBuilder i(String str, String str2, char c5, StringBuilder sb2, String str3) {
        sb2.append(str + str2 + c5);
        return new StringBuilder(str3);
    }

    public static Iterator j(Parcel parcel, List list) {
        parcel.writeInt(list.size());
        return list.iterator();
    }

    public static void k(int i2, String str, String str2) {
        Log.d(str2, str + i2);
    }

    public static void l(int i2, HashMap hashMap, String str, int i7, String str2) {
        hashMap.put(str, Integer.valueOf(i2));
        hashMap.put(str2, Integer.valueOf(i7));
    }

    public static void m(Resources resources, int i2, ArrayList arrayList) {
        arrayList.add(Integer.valueOf(resources.getDimensionPixelSize(i2)));
    }

    public static void n(Parcel parcel, int i2, Double d) {
        parcel.writeInt(i2);
        parcel.writeDouble(d.doubleValue());
    }

    public static void o(Parcel parcel, int i2, Long l) {
        parcel.writeInt(i2);
        parcel.writeLong(l.longValue());
    }

    public static /* synthetic */ void p(AgifDecoder agifDecoder) {
        boolean isTerminated;
        if (agifDecoder instanceof AutoCloseable) {
            agifDecoder.close();
        } else if (agifDecoder instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) agifDecoder;
            if (executorService != ForkJoinPool.commonPool() && !(isTerminated = executorService.isTerminated())) {
                executorService.shutdown();
                boolean z = false;
                while (!isTerminated) {
                    try {
                        isTerminated = executorService.awaitTermination(1, TimeUnit.DAYS);
                    } catch (InterruptedException unused) {
                        if (!z) {
                            executorService.shutdownNow();
                            z = true;
                        }
                    }
                }
                if (z) {
                    Thread.currentThread().interrupt();
                }
            }
        } else if (agifDecoder instanceof TypedArray) {
            ((TypedArray) agifDecoder).recycle();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static /* synthetic */ void q(AgifEncoder agifEncoder) {
        boolean isTerminated;
        if (agifEncoder instanceof AutoCloseable) {
            agifEncoder.close();
        } else if (agifEncoder instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) agifEncoder;
            if (executorService != ForkJoinPool.commonPool() && !(isTerminated = executorService.isTerminated())) {
                executorService.shutdown();
                boolean z = false;
                while (!isTerminated) {
                    try {
                        isTerminated = executorService.awaitTermination(1, TimeUnit.DAYS);
                    } catch (InterruptedException unused) {
                        if (!z) {
                            executorService.shutdownNow();
                            z = true;
                        }
                    }
                }
                if (z) {
                    Thread.currentThread().interrupt();
                }
            }
        } else if (agifEncoder instanceof TypedArray) {
            ((TypedArray) agifEncoder).recycle();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void r(Exception exc, StringBuilder sb2, StringCompat stringCompat) {
        sb2.append(exc.getMessage());
        Log.e(stringCompat, sb2.toString());
    }

    public static void s(Exception exc, StringBuilder sb2, String str) {
        sb2.append(exc.getMessage());
        Log.e(str, sb2.toString());
    }

    public static /* synthetic */ void t(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static void u(String str, String str2, String str3) {
        Log.e(str3, str + str2);
    }

    public static void v(String str, String str2, String str3, boolean z) {
        Log.d(str3, str + z + str2);
    }

    public static void w(StringBuilder sb2, int i2, String str) {
        sb2.append(i2);
        Log.d(str, sb2.toString());
    }

    public static void x(StringBuilder sb2, long j2, String str) {
        sb2.append(System.currentTimeMillis() - j2);
        Log.d(str, sb2.toString());
    }

    public static void y(StringBuilder sb2, String str, StringCompat stringCompat) {
        sb2.append(str);
        Log.p(stringCompat, sb2.toString());
    }

    public static void z(Throwable th, StringBuilder sb2, String str) {
        sb2.append(th.getMessage());
        Log.e(str, sb2.toString());
    }
}
