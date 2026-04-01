package i;

import android.content.res.TypedArray;
import com.samsung.android.gallery.app.remote.v2.GalleryPresentation;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* renamed from: i.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0212a {
    public static String A(String str, String str2) {
        return str + str2;
    }

    public static String B(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static float a(float f, float f5, float f8, float f10) {
        return ((f - f5) * f8) + f10;
    }

    public static int b(int i2, int i7, int i8) {
        return (Integer.hashCode(i2) + i7) * i8;
    }

    public static int c(int i2, int i7, long j2) {
        return (Long.hashCode(j2) + i2) * i7;
    }

    public static int d(int i2, int i7, String str) {
        return (str.hashCode() + i2) * i7;
    }

    public static int e(int i2, int i7, boolean z) {
        return (Boolean.hashCode(z) + i2) * i7;
    }

    public static int f(List list, int i2, int i7) {
        return (list.hashCode() + i2) * i7;
    }

    public static QueryBuilder g(String str) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addTable(str);
        return queryBuilder;
    }

    public static ClassCastException h(Iterator it) {
        it.next().getClass();
        return new ClassCastException();
    }

    public static Object i(ArrayList arrayList, int i2) {
        return arrayList.get(arrayList.size() - i2);
    }

    public static String j(int i2, String str, String str2) {
        return str + i2 + str2;
    }

    public static String k(int i2, String str, String str2, String str3) {
        return str + i2 + str2 + str3;
    }

    public static String l(String str, String str2) {
        return str + str2;
    }

    public static String m(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String n(String str, String str2, String str3, String str4) {
        return str + str2 + str3 + str4;
    }

    public static String o(StringBuilder sb2, long j2, String str) {
        sb2.append(j2);
        sb2.append(str);
        return sb2.toString();
    }

    public static String p(StringBuilder sb2, String str, String str2) {
        sb2.append(str);
        sb2.append(str2);
        return sb2.toString();
    }

    public static String q(StringBuilder sb2, String str, String str2, String str3, String str4) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        return sb2.toString();
    }

    public static String r(StringBuilder sb2, List list, char c5) {
        sb2.append(list);
        sb2.append(c5);
        return sb2.toString();
    }

    public static StringBuilder s(String str) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        return sb2;
    }

    public static StringBuilder t(String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str2);
        return sb2;
    }

    public static StringBuilder u(String str, String str2, String str3, int i2, String str4) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(i2);
        sb2.append(str4);
        return sb2;
    }

    public static /* synthetic */ void v(GalleryPresentation galleryPresentation) {
        boolean isTerminated;
        if (galleryPresentation instanceof AutoCloseable) {
            galleryPresentation.close();
        } else if (galleryPresentation instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) galleryPresentation;
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
        } else if (galleryPresentation instanceof TypedArray) {
            ((TypedArray) galleryPresentation).recycle();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void w(String str, String str2, String str3, QueryBuilder queryBuilder) {
        queryBuilder.andCondition(str + str2 + str3);
    }

    public static void x(String str, String str2, boolean z) {
        Log.d(str2, str + z);
    }

    public static void y(Throwable th, StringBuilder sb2, String str) {
        sb2.append(th.getMessage());
        android.util.Log.e(str, sb2.toString());
    }

    public static void z(Object[] objArr, StringBuilder sb2, StringCompat stringCompat) {
        sb2.append(Logger.vt(objArr));
        Log.d(stringCompat, sb2.toString());
    }
}
