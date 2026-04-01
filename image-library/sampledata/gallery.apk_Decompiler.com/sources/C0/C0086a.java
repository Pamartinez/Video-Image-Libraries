package c0;

import android.content.ContentValues;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.appfunctions.schema.internal.dependencies.h0;
import com.google.protobuf.CodedOutputStream;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.scs.base.ResultException;
import com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource;
import com.samsung.scsp.framework.core.api.ApiContext;
import com.samsung.scsp.framework.core.api.ApiControl;
import com.samsung.scsp.framework.core.listeners.ListenersHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* renamed from: c0.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0086a {
    public static void A(StringBuilder sb2, ArrayList arrayList, String str) {
        sb2.append(arrayList.size());
        sb2.append(str);
    }

    public static int B(int i2, int i7, int i8, int i10) {
        return CodedOutputStream.b0(i2) + i7 + i8 + i10;
    }

    public static void C(int i2, String str, String str2) {
        Log.d(str2, str + i2);
    }

    public static int D(int i2, int i7, int i8, int i10) {
        return ((i2 - i7) / i8) + i10;
    }

    public static int a(int i2, int i7, int i8) {
        return h0.l0(i2) + i7 + i8;
    }

    public static int b(int i2, int i7, int i8, int i10) {
        return h0.l0(i2) + i7 + i8 + i10;
    }

    public static ContentValues c(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        return contentValues;
    }

    public static View d(ViewGroup viewGroup, int i2, ViewGroup viewGroup2, boolean z) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup2, z);
    }

    public static IllegalStateException e(Logger logger, String str, String str2, String str3) {
        logger.e$pde_sdk_1_0_40_release(str, str2);
        return new IllegalStateException(str3);
    }

    public static Object f(int i2, List list) {
        return list.get(list.size() - i2);
    }

    public static Object g(ListenersHolder listenersHolder, ApiControl apiControl, ApiContext apiContext) {
        apiControl.execute(apiContext, listenersHolder.getListeners());
        return listenersHolder.getResult();
    }

    public static String h(char c5, String str, String str2) {
        return str + str2 + c5;
    }

    public static String i(int i2, String str) {
        return str + i2;
    }

    public static String j(long j2, String str, String str2, StringBuilder sb2) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(System.currentTimeMillis() - j2);
        return sb2.toString();
    }

    public static String k(RecyclerView recyclerView, StringBuilder sb2) {
        sb2.append(recyclerView.exceptionLabel());
        return sb2.toString();
    }

    public static String l(StringBuilder sb2, int i2, String str) {
        sb2.append(i2);
        sb2.append(str);
        return sb2.toString();
    }

    public static String m(StringBuilder sb2, String str, char c5) {
        sb2.append(str);
        sb2.append(c5);
        return sb2.toString();
    }

    public static String n(StringBuilder sb2, boolean z, char c5) {
        sb2.append(z);
        sb2.append(c5);
        return sb2.toString();
    }

    public static StringBuilder o(int i2, String str, String str2) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(i2);
        sb2.append(str2);
        return sb2;
    }

    public static StringBuilder p(long j2, String str, String str2, String str3) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(j2);
        sb2.append(str2);
        sb2.append(str3);
        return sb2;
    }

    public static StringBuilder q(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        sb2.append(str5);
        return sb2;
    }

    public static void r(int i2, MOCRLang.AnonymousClass1 r1, String str, int i7, String str2) {
        r1.put(str, Integer.valueOf(i2));
        r1.put(str2, Integer.valueOf(i7));
    }

    public static void s(int i2, TaskCompletionSource taskCompletionSource) {
        taskCompletionSource.setException(new ResultException(i2));
    }

    public static void t(int i2, String str, TaskCompletionSource taskCompletionSource) {
        taskCompletionSource.setException(new ResultException(i2, str));
    }

    public static void u(int i2, String str, String str2) {
        com.samsung.android.sdk.scs.base.utils.Log.e(str2, str + i2);
    }

    public static /* synthetic */ void v(Object obj) {
        boolean isTerminated;
        if (obj instanceof AutoCloseable) {
            ((AutoCloseable) obj).close();
        } else if (obj instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) obj;
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
        } else if (obj instanceof TypedArray) {
            ((TypedArray) obj).recycle();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void w(Object obj, StringBuilder sb2, char c5) {
        sb2.append(com.samsung.android.gallery.support.utils.Logger.toString(obj));
        sb2.append(c5);
    }

    public static void x(String str, String str2, Throwable th) {
        Log.e(str2, str + th);
    }

    public static void y(StringBuilder sb2, float f, String str, float f5, String str2) {
        sb2.append(f);
        sb2.append(str);
        sb2.append(f5);
        sb2.append(str2);
    }

    public static void z(StringBuilder sb2, String str, String str2, String str3, String str4) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
    }
}
