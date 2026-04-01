package t1;

import android.content.Intent;
import android.content.res.TypedArray;
import android.util.Log;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapDataVars;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTemplateScene;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.ocr.MOCRLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* renamed from: t1.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract /* synthetic */ class C0280e {
    public static Intent a(String str, String str2) {
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        return intent;
    }

    public static RecapTemplateScene b(String str, RecapDataVars recapDataVars, RecapTemplateScene recapTemplateScene) {
        return recapTemplateScene.addText(new RecapTextLayer(str).setVar(recapDataVars));
    }

    public static RecapTemplateScene c(String str, RecapTemplateScene recapTemplateScene) {
        return recapTemplateScene.addImage(new RecapImage(str));
    }

    public static String d(int i2, int i7, String str) {
        return str.substring(i7, str.length() - i2);
    }

    public static StringBuilder e(String str, String str2, String str3, String str4) {
        MOCRLog.d(str3, str + str2);
        return new StringBuilder(str4);
    }

    public static /* synthetic */ void f(AutoCloseable autoCloseable) {
        boolean isTerminated;
        if (autoCloseable instanceof AutoCloseable) {
            autoCloseable.close();
        } else if (autoCloseable instanceof ExecutorService) {
            ExecutorService executorService = (ExecutorService) autoCloseable;
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
        } else if (autoCloseable instanceof TypedArray) {
            ((TypedArray) autoCloseable).recycle();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void g(String str, String str2, String str3, String str4, boolean z) {
        Log.d(str2, str + z);
        Log.d(str3, str4);
    }

    public static RecapTemplateScene h(String str, RecapTemplateScene recapTemplateScene) {
        return recapTemplateScene.addImage(new RecapImage(str).hidden());
    }
}
