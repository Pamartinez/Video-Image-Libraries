package N2;

import Q2.a;
import Q2.b;
import android.content.ActivityNotFoundException;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.remote.v2.GalleryBasePresentation;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.support.utils.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class j {
    public static void A(Throwable th, StringBuilder sb2, String str, ObjectCaptureHelper objectCaptureHelper, String str2) {
        sb2.append(th.getMessage());
        sb2.append(str);
        sb2.append(objectCaptureHelper);
        Log.e(str2, sb2.toString());
    }

    public static void B(Throwable th, StringBuilder sb2, String str, TextExtractionHelper textExtractionHelper, String str2) {
        sb2.append(th.getMessage());
        sb2.append(str);
        sb2.append(textExtractionHelper);
        Log.e(str2, sb2.toString());
    }

    public static void C(Exception exc, StringBuilder sb2, String str) {
        sb2.append(exc.getMessage());
        android.util.Log.w(str, sb2.toString());
    }

    public static void D(Exception exc, StringBuilder sb2, String str) {
        sb2.append(exc.getMessage());
        android.util.Log.e(str, sb2.toString());
    }

    public static int a(float f, int i2, int i7) {
        return (Float.hashCode(f) + i2) * i7;
    }

    public static String b(int i2, int i7, String str, String str2) {
        return str + i2 + str2 + i7;
    }

    public static String c(Fragment fragment, String str, String str2) {
        return str + fragment + str2;
    }

    public static String d(String str, String str2, String str3, String str4, String str5) {
        return str + str2 + str3 + str4 + str5;
    }

    public static String e(StringBuilder sb2, int i2, char c5) {
        sb2.append(i2);
        sb2.append(c5);
        return sb2.toString();
    }

    public static String f(StringBuilder sb2, String str, String str2, String str3) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        return sb2.toString();
    }

    public static String g(StringBuilder sb2, ArrayList arrayList) {
        sb2.append(arrayList.size());
        return sb2.toString();
    }

    public static String h(StringBuilder sb2, boolean z, String str) {
        sb2.append(z);
        sb2.append(str);
        return sb2.toString();
    }

    public static String i(Throwable th, StringBuilder sb2) {
        sb2.append(th.getMessage());
        return sb2.toString();
    }

    public static StringBuilder j(long j2, String str, String str2) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(j2);
        sb2.append(str2);
        return sb2;
    }

    public static StringBuilder k(String str, String str2, String str3) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(str2);
        sb2.append(str3);
        return sb2;
    }

    public static void l(int i2, HashMap hashMap, String str, int i7, String str2) {
        hashMap.put(str, Integer.valueOf(i2));
        hashMap.put(str2, Integer.valueOf(i7));
    }

    public static void m(long j2, String str, String str2, StringBuilder sb2) {
        sb2.append(System.currentTimeMillis() - j2);
        sb2.append(str);
        Log.d(str2, sb2.toString());
    }

    public static void n(i iVar, i iVar2, i iVar3, i iVar4, i iVar5) {
        k.a(iVar);
        k.a(iVar2);
        k.a(iVar3);
        k.a(iVar4);
        k.a(iVar5);
    }

    public static void o(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        b.a(aVar);
        b.a(aVar2);
        b.a(aVar3);
        b.a(aVar4);
        b.a(aVar5);
    }

    public static void p(ActivityNotFoundException activityNotFoundException, StringBuilder sb2, String str) {
        sb2.append(activityNotFoundException.getMessage());
        Log.e(str, sb2.toString());
    }

    public static /* synthetic */ void q(GalleryBasePresentation galleryBasePresentation) {
        boolean isTerminated;
        if (galleryBasePresentation instanceof AutoCloseable) {
            galleryBasePresentation.close();
        } else if (galleryBasePresentation instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) galleryBasePresentation;
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
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void r(IOException iOException, StringBuilder sb2, String str) {
        sb2.append(iOException.getMessage());
        Log.e(str, sb2.toString());
    }

    public static void s(Exception exc, StringBuilder sb2, String str) {
        sb2.append(exc.getMessage());
        Log.w(str, sb2.toString());
    }

    public static void t(IllegalStateException illegalStateException, StringBuilder sb2, String str) {
        sb2.append(illegalStateException.getMessage());
        android.util.Log.e(str, sb2.toString());
    }

    public static void u(RuntimeException runtimeException, StringBuilder sb2, String str) {
        sb2.append(runtimeException.getMessage());
        Log.e(str, sb2.toString());
    }

    public static void v(String str, Exception exc, String str2) {
        Log.e(str2, str + exc);
    }

    public static void w(String str, String str2, String str3) {
        android.util.Log.d(str3, str + str2);
    }

    public static void x(StringBuilder sb2, int i2, String str, int i7, String str2) {
        sb2.append(i2);
        sb2.append(str);
        sb2.append(i7);
        sb2.append(str2);
    }

    public static void y(StringBuilder sb2, String str, String str2) {
        sb2.append(str);
        Log.d(str2, sb2.toString());
    }

    public static void z(StringBuilder sb2, String str, String str2, String str3) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
    }
}
