package com.samsung.android.gallery.module.deepsky;

import A4.C0385u;
import V3.b;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import b9.a;
import com.samsung.android.app.sdk.deepsky.DeepSky;
import com.samsung.android.app.sdk.deepsky.donation.ActionDonation;
import com.samsung.android.app.sdk.deepsky.donation.Donation;
import com.samsung.android.app.sdk.deepsky.objectcapture.ObjectCaptureProvider;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCapture;
import com.samsung.android.app.sdk.deepsky.suggestion.SuggestionRequest;
import com.samsung.android.app.sdk.deepsky.textextraction.Recognizer;
import com.samsung.android.app.sdk.deepsky.textextraction.TextExtraction;
import com.samsung.android.app.sdk.deepsky.textextraction.TextExtractionProvider;
import com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.TextExtractionOCRLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeepSkyHelper {
    protected static volatile Donation sDonation;
    static volatile Boolean sDonationEnabled;
    private static volatile ObjectCapture sObjectCapture;
    private static final AtomicInteger sObjectCaptureRefCount = new AtomicInteger(0);
    static volatile Boolean sObjectCaptureSupported;
    private static volatile Recognizer sRecognizer;
    private static volatile SuggestionRequest sSuggestionRequest;
    private static volatile TextExtraction sTextExtraction;
    private static final HashMap<String, TextExtractionDrawHelper> sTextExtractionDrawHelperMap = new HashMap<>();
    private static final AtomicInteger sTextExtractionRefCount = new AtomicInteger(0);
    static volatile Boolean sTextExtractionSupported;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ClipHandler {
        static final Handler instance = new Handler(ThreadUtil.createBackgroundThreadLooper("Clip"));
    }

    public static void closeTextExtractionDrawHelper(String str) {
        post(new a(str, 0));
    }

    public static void closeTextTranslationWindow(Context context) {
        try {
            context.sendBroadcast(new Intent("com.samsung.android.app.interpreter.action.TRANSLATION_FINISH_NEEDED"));
        } catch (Exception unused) {
        }
    }

    public static void donate(Context context, String str, String str2) {
        String str3;
        int i2;
        if (sDonationEnabled == null) {
            sDonationEnabled = Boolean.valueOf(isDonationEnabled(context));
        }
        if (sDonationEnabled.booleanValue()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!(context == null || str == null)) {
                if (str2 != null) {
                    try {
                        if (!str2.startsWith("image/")) {
                            i2 = R$string.video;
                            getDonation(context).donateForResult(new ActionDonation.Builder("sec.actions.intent.OPEN_IMAGE").addParamDetails("imageObject.name", context.getString(i2)).addParamDetails("imageObject.contentUrl", str).build());
                        }
                    } catch (Error | Exception e) {
                        Log.e((CharSequence) "DeepSkyHelper", "donate failed {" + str2 + ',' + str + "} +" + (System.currentTimeMillis() - currentTimeMillis), e);
                        return;
                    }
                }
                i2 = R$string.image;
                getDonation(context).donateForResult(new ActionDonation.Builder("sec.actions.intent.OPEN_IMAGE").addParamDetails("imageObject.name", context.getString(i2)).addParamDetails("imageObject.contentUrl", str).build());
            }
            StringBuilder sb2 = new StringBuilder("donate");
            if (str != null) {
                str3 = ArgumentsUtil.getLastSegment(str);
            } else {
                str3 = null;
            }
            sb2.append(Logger.vt(str2, str3, Long.valueOf(currentTimeMillis)));
            Log.d("DeepSkyHelper", sb2.toString());
        }
    }

    public static Donation getDonation(Context context) {
        if (sDonation == null) {
            sDonation = DeepSky.with(context).getDonation();
        }
        return sDonation;
    }

    public static ObjectCapture getObjectCapture() {
        if (sObjectCapture == null) {
            synchronized (ObjectCaptureProvider.class) {
                try {
                    if (sObjectCapture == null) {
                        sObjectCapture = ObjectCaptureProvider.getInstance(AppResources.getAppContext());
                    }
                } catch (Error | Exception e) {
                    Log.e((CharSequence) "DeepSkyHelper", "unable to get object capture", e.getMessage());
                }
            }
        }
        return sObjectCapture;
    }

    public static Recognizer getRecognizer(Context context) {
        TextExtraction textExtraction;
        if (sRecognizer == null) {
            synchronized (DeepSky.class) {
                try {
                    if (!(sRecognizer != null || context == null || (textExtraction = getTextExtraction(context.getApplicationContext())) == null)) {
                        sRecognizer = textExtraction.getRecognizer();
                        sRecognizer.initialize(TextExtractionOCRLanguage.All.getId());
                    }
                } catch (Error | Exception e) {
                    Log.e((CharSequence) "DeepSkyHelper", "unable to get recognizer", e.getMessage());
                }
            }
        }
        return sRecognizer;
    }

    public static SuggestionRequest getSuggestionRequest(Context context) {
        if (sSuggestionRequest == null) {
            synchronized (DeepSky.class) {
                try {
                    if (sSuggestionRequest == null && context != null) {
                        sSuggestionRequest = DeepSky.with(context).getSuggestionRequest();
                    }
                } catch (Error | Exception e) {
                    Log.e((CharSequence) "DeepSkyHelper", "unable to get suggestion request", e.getMessage());
                }
            }
        }
        return sSuggestionRequest;
    }

    public static TextExtraction getTextExtraction(Context context) {
        if (sTextExtraction == null) {
            synchronized (DeepSky.class) {
                try {
                    if (sTextExtraction == null && isTextExtractionSupported()) {
                        sTextExtraction = TextExtractionProvider.with(context).getTextExtraction();
                    }
                } catch (Error | Exception e) {
                    Log.e((CharSequence) "DeepSkyHelper", "unable to get text extraction", e.getMessage());
                }
            }
        }
        return sTextExtraction;
    }

    public static TextExtractionDrawHelper getTextExtractionDrawHelper(Context context, String str) {
        if (context != null) {
            return sTextExtractionDrawHelperMap.computeIfAbsent(str, new G9.a(context, 2));
        }
        return null;
    }

    public static boolean hasCallbacks(Runnable runnable) {
        return ClipHandler.instance.hasCallbacks(runnable);
    }

    public static boolean isDonationEnabled(Context context) {
        GalleryPreference instanceCache = GalleryPreference.getInstanceCache();
        return instanceCache.computeBooleanIfAbsent("DeepSkyDonation#" + SdkConfig.SEM_VER, new C0385u(13, context));
    }

    public static boolean isDonationSupported() {
        if (sDonationEnabled == null || sDonationEnabled.booleanValue()) {
            return true;
        }
        return false;
    }

    public static boolean isObjectCaptureReleased() {
        if (sObjectCaptureRefCount.get() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isObjectCaptureSupported() {
        ObjectCapture objectCapture;
        boolean z;
        if (sObjectCaptureSupported == null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                if (sObjectCapture != null) {
                    objectCapture = sObjectCapture;
                } else {
                    objectCapture = ObjectCaptureProvider.getInstance(AppResources.getAppContext());
                }
                if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) || objectCapture == null || !objectCapture.isObjectCaptureSupported()) {
                    z = false;
                } else {
                    z = true;
                }
                sObjectCaptureSupported = Boolean.valueOf(z);
                Log.d("DeepSkyHelper", "object capture support" + Logger.vt(sObjectCaptureSupported, Long.valueOf(currentTimeMillis)));
            } catch (Error | Exception e) {
                sObjectCaptureSupported = Boolean.FALSE;
                A.a.z(e, new StringBuilder("object capture support failed. e="), "DeepSkyHelper");
            }
        }
        return sObjectCaptureSupported.booleanValue();
    }

    public static boolean isTextExtractionReleased() {
        if (sTextExtractionRefCount.get() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isTextExtractionSupported() {
        boolean z;
        if (sTextExtractionSupported == null) {
            if (Features.isEnabled(Features.SUPPORT_TEXT_EXTRACTION_S_OS)) {
                sTextExtractionSupported = Boolean.TRUE;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    Context appContext = AppResources.getAppContext();
                    if (!SdkConfig.atLeast(SdkConfig.GED.T) || appContext == null || !TextExtractionProvider.isTextExtractionSupported(appContext)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    sTextExtractionSupported = Boolean.valueOf(z);
                    Log.d("DeepSkyHelper", "text extraction support" + Logger.vt(sTextExtractionSupported, Long.valueOf(currentTimeMillis)));
                } catch (Error | Exception e) {
                    sTextExtractionSupported = Boolean.FALSE;
                    Log.e("DeepSkyHelper", "text extraction support" + Logger.vt(sTextExtractionSupported, e.getMessage(), Long.valueOf(currentTimeMillis)));
                }
            }
        }
        return sTextExtractionSupported.booleanValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TextExtractionDrawHelper lambda$getTextExtractionDrawHelper$1(Context context, String str) {
        TextExtraction textExtraction = getTextExtraction(context.getApplicationContext());
        if (textExtraction != null) {
            return textExtraction.getTextExtractionDrawHelper(context);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isDonationEnabled$0(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            boolean isSupported = DeepSky.isSupported(context);
            Log.d("DeepSkyHelper", "isDonationEnabled" + Logger.vt(Integer.valueOf(SdkConfig.SEM_VER), Boolean.valueOf(isSupported), Long.valueOf(currentTimeMillis)));
            return isSupported;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("isDonationEnabled failed. e="), "DeepSkyHelper");
            return false;
        }
    }

    public static void openObjectCapture() {
        if (sObjectCaptureRefCount.getAndIncrement() == 0) {
            Log.d("DeepSkyHelper", "open object capture");
        }
    }

    public static void openTextExtraction() {
        if (sTextExtractionRefCount.getAndIncrement() == 0) {
            Log.d("DeepSkyHelper", "open text extraction");
        }
    }

    public static void post(Runnable runnable) {
        ClipHandler.instance.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long j2) {
        ClipHandler.instance.postDelayed(runnable, j2);
    }

    public static void releaseObjectCapture() {
        if (sObjectCaptureRefCount.decrementAndGet() <= 0) {
            Log.d("DeepSkyHelper", "release object capture");
            sObjectCapture = null;
        }
    }

    public static void releaseTextExtraction() {
        if (sTextExtractionRefCount.decrementAndGet() <= 0) {
            Log.d("DeepSkyHelper", "release text extraction");
            Recognizer recognizer = sRecognizer;
            sRecognizer = null;
            sTextExtraction = null;
            sSuggestionRequest = null;
            if (recognizer != null) {
                post(new b(29, recognizer));
            }
        }
    }

    public static void removeCallbacks(Runnable runnable) {
        ClipHandler.instance.removeCallbacks(runnable);
    }
}
