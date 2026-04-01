package com.samsung.android.sdk.scs.ai.asr;

import Ad.C0720a;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.C0670h;
import com.samsung.android.sdk.scs.ai.asr.ExpiringData;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.LocaleInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerFeature;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerType;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Environment {
    private static final String TAG = "Environment";
    private static final ExpiringData<List<ServerInfo>> asrServerInfos = new ExpiringData.Builder("asrServerInfo", new C0720a(1)).setChecker(new f(23)).build();
    private static final ExpiringData<List<BTCLocaleInfo>> btcLocaleDatas = new ExpiringData.Builder("btc_locale", new C0720a(1)).setChecker(new f(22)).build();
    private static final ExpiringData<String> langpackConfig = new ExpiringData.Builder("langpackConfig", new h(3)).setChecker(new f(24)).build();
    private static final ExpiringData<List<Locale>> localeDatas = new ExpiringData.Builder("locale", new C0720a(1)).setChecker(new f(21)).build();
    private static final Function<Context, List<Locale>> sFuncGetLocales;
    private final int errorCode;
    private final boolean isAvailable;
    private Intent storeLinkIntent;
    private final String targetPackage;

    static {
        f fVar;
        if (Build.VERSION.SDK_INT < 33) {
            fVar = new f(16);
        } else {
            fVar = new f(20);
        }
        sFuncGetLocales = fVar;
    }

    private Environment(boolean z, int i2, String str) {
        this.isAvailable = z;
        this.errorCode = i2;
        this.targetPackage = str;
        if (str != null) {
            Intent intent = new Intent();
            this.storeLinkIntent = intent;
            intent.setData(Uri.parse("samsungapps://ProductDetail/" + str));
            this.storeLinkIntent.putExtra("type", "cover");
            this.storeLinkIntent.addFlags(335544352);
        }
    }

    private static Bundle callContentProvider(Context context, String str, String str2, Bundle bundle, String str3) {
        Exception exc;
        String str4;
        Uri parse;
        try {
            Log.d(TAG, "Call cp " + str);
            if (str3 == null) {
                try {
                    parse = Uri.parse(getURI(context));
                } catch (Exception e) {
                    exc = e;
                    str4 = str;
                    Log.e(TAG, "Failed to call cp " + str4, exc);
                    return Bundle.EMPTY;
                }
            } else {
                parse = Uri.parse(str3);
            }
            Uri uri = parse;
            str4 = str;
            try {
                return (Bundle) Optional.ofNullable(context).map(new f(19)).map(new c(uri, str4, str2, bundle, 0)).orElse(Bundle.EMPTY);
            } catch (Exception e7) {
                e = e7;
                exc = e;
                Log.e(TAG, "Failed to call cp " + str4, exc);
                return Bundle.EMPTY;
            }
        } catch (Exception e8) {
            e = e8;
            str4 = str;
            exc = e;
            Log.e(TAG, "Failed to call cp " + str4, exc);
            return Bundle.EMPTY;
        }
    }

    /* access modifiers changed from: private */
    public static LocaleInfo createLocaleInfo(Locale locale) {
        if (locale == null) {
            return null;
        }
        return new LocaleInfo(locale, (String) null, 0);
    }

    @Deprecated
    public static Environment get(Context context, Locale locale, ConnectionType connectionType) {
        if (!isSupportedSpeechRecognition(context)) {
            return new Environment(false, -1, (String) null);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("connection_type", connectionType.getTypeInt());
        bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.DICTATION_LANGUAGE_INFO));
        bundle.putSerializable("locale", locale);
        return new Environment(callContentProvider(context, "check_availability", (String) null, bundle));
    }

    public static List<Locale> getAvailableLocalesForAudioToTranslation(Context context) {
        return (List) ((ArrayList) Optional.ofNullable(callContentProvider(context, SpeechRecognitionConst.Method.GET_AVAILABLE_AUDIO_TO_TRANSLATION_LOCALE_LIST, (String) null, Bundle.EMPTY)).map(new f(8)).orElseGet(new C0720a(1))).stream().map(new f(7)).collect(Collectors.toList());
    }

    private static String getConfigURI(Context context) {
        if (((Integer) Optional.ofNullable(context).map(new f(18)).orElse(-1)).intValue() == 0) {
            return "content://com.samsung.android.intellivoiceservice.common.config.systemlevel";
        }
        Log.d(TAG, "System permission doesn't have granted.");
        return "content://com.samsung.android.intellivoiceservice.common.config";
    }

    public static Context getContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    @Deprecated
    public static ServerInfo getCurrentServerInfo(ServerFeature serverFeature) {
        ServerType serverType = Repository.settings.getServerType(serverFeature);
        if (serverType != null) {
            return getSupportedServerLists(getContext()).stream().filter(new e(1, serverType)).findFirst().orElse((Object) null);
        }
        return null;
    }

    @Deprecated
    public static ServerType getCurrentServerType(ServerFeature serverFeature) {
        return Repository.settings.getServerType(serverFeature);
    }

    public static Uri getDictationTermsOfServiceUrl(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return (Uri) Optional.ofNullable(callContentProvider(context, "get_server_asr_tos_url", (String) null, Bundle.EMPTY)).map(new f(17)).map(new C0670h(9)).orElse((Object) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLangpackConfigInfo(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return langpackConfig.getOrCompute(new j(4, context));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static String getLangpackConfigInfoFromCP(Context context) {
        try {
            if (Feature.checkFeature(context, Feature.FEATURE_AI_LANGUAGE_PACK_CONFIGURATION_PROVIDER) == 0) {
                return callContentProvider(context, "get_language_pack_configuration", (String) null, (Bundle) null, getConfigURI(context)).getString("language_pack_configuration_json");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.LANGPACK_CONFIG));
            return callContentProvider(context, "get_langpack_config", (String) null, bundle).getString("langpack_config_json");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static List<BTCLocaleInfo> getSupportedBTCLocaleInfos(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return btcLocaleDatas.getOrCompute(new j(1, context));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /* access modifiers changed from: private */
    public static List<BTCLocaleInfo> getSupportedBTCLocaleInfosFromCP(Context context) {
        try {
            Bundle bundle = new Bundle();
            bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.BTC_LANGUAGES_INFO));
            return (List) Optional.ofNullable(callContentProvider(context, "get_btc_locale_list", (String) null, bundle)).map(new f(1)).map(new f(9)).orElseGet(new C0720a(12));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    @Deprecated
    public static List<LocaleInfo> getSupportedLocaleInfos(Context context, ConnectionType connectionType) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                Bundle bundle = new Bundle();
                bundle.putInt("connection_type", connectionType.getTypeInt());
                bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.DICTATION_LANGUAGE_INFO));
                return (List) Optional.ofNullable(callContentProvider(context, "get_locale_list", (String) null, bundle)).map(new f(14)).map(new f(15)).orElseGet(new C0720a(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Deprecated
    public static List<Locale> getSupportedLocales(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return localeDatas.getOrCompute(new j(2, context));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /* access modifiers changed from: private */
    public static List<Locale> getSupportedLocalesFromCP(Context context) {
        try {
            List<Locale> list = (List) EnumSet.allOf(ConnectionType.class).parallelStream().map(new f(4)).map(new a(context)).map(new f(5)).filter(new b(0)).flatMap(new f(6)).map(new f(7)).distinct().collect(Collectors.toList());
            if (list.size() > 0) {
                return list;
            }
            Log.w(TAG, "Failed to get locale from cp");
            return getSupportedLocalesFromRes(context);
        } catch (Exception e) {
            Log.w(TAG, "Error to get locales from cp ", e);
        }
    }

    /* access modifiers changed from: private */
    public static List<Locale> getSupportedLocalesFromRes(Context context) {
        try {
            ResolveInfo resolveService = context.getPackageManager().resolveService(new Intent(SpeechRecognitionConst.SERVICE_BIND_ACTION), 128);
            if (resolveService == null) {
                return Collections.EMPTY_LIST;
            }
            int i2 = resolveService.serviceInfo.metaData.getInt("network_supported_locales");
            int i7 = resolveService.serviceInfo.metaData.getInt("local_supported_locales");
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(resolveService.serviceInfo.packageName);
            HashSet hashSet = new HashSet();
            hashSet.addAll((List) Stream.of(resourcesForApplication.getStringArray(i2)).map(new f(7)).collect(Collectors.toList()));
            hashSet.addAll((List) Stream.of(resourcesForApplication.getStringArray(i7)).map(new f(7)).collect(Collectors.toList()));
            ArrayList arrayList = new ArrayList(hashSet);
            Log.i(TAG, String.valueOf(arrayList));
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Failed to get locales from resources ", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Deprecated
    public static List<ServerInfo> getSupportedServerInfos(Context context, ServerFeature serverFeature) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return (List) asrServerInfos.getOrCompute(new j(3, context)).stream().filter(new e(2, serverFeature)).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /* access modifiers changed from: private */
    public static List<ServerInfo> getSupportedServerLists(Context context) {
        try {
            return (List) Optional.ofNullable(callContentProvider(context, "get_server_list", (String) null, Bundle.EMPTY)).map(new f(11)).map(new f(12)).orElseGet(new C0720a(12));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    private static String getURI(Context context) {
        if (((Integer) Optional.ofNullable(context).map(new f(2)).orElse(-1)).intValue() == 0) {
            return "content://com.samsung.android.intellivoiceservice.ai.speech2";
        }
        Log.d(TAG, "System permission doesn't have granted.");
        return "content://com.samsung.android.intellivoiceservice.ai.speech";
    }

    public static boolean isBilingualAsrSupported(Context context) {
        return ((Boolean) Optional.ofNullable(callContentProvider(context, SpeechRecognitionConst.Method.CHECK_BILINGUAL_ASR_SUPPORT, (String) null, Bundle.EMPTY)).map(new f(25)).orElse(Boolean.FALSE)).booleanValue();
    }

    public static boolean isSpeakerDiarizationSupportForLanguage(Context context, Locale locale) {
        try {
            if (!isSupportedSpeakerDiarization(context)) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("locale", locale);
            return callContentProvider(context, SpeechRecognitionConst.Method.CHECK_SPEAKER_DIARIZATION_LANGUAGE_SUPPORT, (String) null, bundle).getBoolean("is_available");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isSupportedSpeakerDiarization(Context context) {
        if (Feature.checkFeature(context, Feature.FEATURE_SPEAKER_DIARISATION) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isSupportedSpeechRecognition(Context context) {
        if (Feature.checkFeature(context, Feature.FEATURE_SPEECH_RECOGNITION) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getSupportedBTCLocaleInfosFromCP$22(BTCLocaleInfo bTCLocaleInfo) {
        if (bTCLocaleInfo.getDefaultSpeaker() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getSupportedBTCLocaleInfosFromCP$23(ArrayList arrayList) {
        return (List) arrayList.stream().filter(new b(2)).filter(new b(3)).distinct().sorted(Comparator.comparing(new f(13))).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getSupportedLocaleInfos$7(Bundle bundle) {
        bundle.setClassLoader(LocaleInfo.class.getClassLoader());
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("locale_info_list");
        if (parcelableArrayList != null && parcelableArrayList.size() > 0) {
            return parcelableArrayList;
        }
        Log.w(TAG, "Failed to get locale info from scs. so generate based on locales.");
        Collection stringArrayList = bundle.getStringArrayList("locale_list");
        if (stringArrayList == null) {
            stringArrayList = Collections.EMPTY_LIST;
        }
        return (List) stringArrayList.stream().map(new f(7)).map(new f(10)).filter(new b(1)).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getSupportedLocaleInfos$8(List list) {
        return (List) list.stream().distinct().sorted(Comparator.comparing(new f(3))).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Bundle lambda$getSupportedLocalesFromCP$18(ConnectionType connectionType) {
        Bundle bundle = new Bundle();
        bundle.putInt("connection_type", connectionType.getTypeInt());
        bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.DICTATION_LANGUAGE_INFO));
        return bundle;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getSupportedServerInfos$12(ServerFeature serverFeature, ServerInfo serverInfo) {
        if (serverInfo.getFeature() == serverFeature) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getSupportedServerLists$26(ArrayList arrayList) {
        return (List) arrayList.stream().filter(new b(4)).distinct().collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$static$0(ArrayList arrayList) {
        boolean z;
        if (arrayList.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$static$1(ArrayList arrayList) {
        boolean z;
        if (arrayList.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$static$2(ArrayList arrayList) {
        boolean z;
        if (arrayList.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$static$3(String str) {
        boolean z;
        if (str.length() > 0) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    @Deprecated
    public static boolean setServerType(ServerFeature serverFeature, ServerType serverType) {
        return Repository.settings.setServerType(serverFeature, serverType);
    }

    @Deprecated
    public int getErrorCode() {
        return this.errorCode;
    }

    @Deprecated
    public Intent getStoreLinkAction() {
        return this.storeLinkIntent;
    }

    @Deprecated
    public String getTargetResourcePackageName() {
        return this.targetPackage;
    }

    @Deprecated
    public boolean isAvailable() {
        return this.isAvailable;
    }

    @Deprecated
    public String toString() {
        return "Availability{isAvailable=" + this.isAvailable + ", errorCode=" + this.errorCode + ", storeLinkIntent=" + this.storeLinkIntent + '}';
    }

    /* access modifiers changed from: private */
    public static Bundle callContentProvider(Context context, String str, String str2, Bundle bundle) {
        return callContentProvider(context, str, str2, bundle, (String) null);
    }

    private Environment(Bundle bundle) {
        this(bundle.getBoolean("is_available"), bundle.getInt("error_code", 0), bundle.getString("resource_package_name"));
    }
}
