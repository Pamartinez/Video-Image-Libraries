package com.samsung.android.sdk.scs.ai.asr_6_0;

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
import android.util.Log;
import com.samsung.android.gallery.support.utils.C0670h;
import com.samsung.android.scs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.LocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerFeature;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerType;
import com.samsung.android.scs.ai.sdkcommon.asr.SpeechRecognitionConst;
import com.samsung.android.sdk.scs.ai.asr.b;
import com.samsung.android.sdk.scs.ai.asr.c;
import com.samsung.android.sdk.scs.ai.asr.f;
import com.samsung.android.sdk.scs.ai.asr.h;
import com.samsung.android.sdk.scs.ai.asr_6_0.ExpiringData;
import com.samsung.android.sdk.scs.base.feature.Feature;
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
    private static final ExpiringData<List<ServerInfo>> asrServerInfos = new ExpiringData.Builder("asrServerInfo", new C0720a(1)).setChecker(new c(14)).build();
    private static final ExpiringData<List<BTCLocaleInfo>> btcLocaleDatas = new ExpiringData.Builder("btc_locale", new C0720a(1)).setChecker(new c(13)).build();
    private static final ExpiringData<String> langpackConfig = new ExpiringData.Builder("langpackConfig", new h(3)).setChecker(new c(15)).build();
    private static final ExpiringData<List<Locale>> localeDatas = new ExpiringData.Builder("locale", new C0720a(1)).setChecker(new c(12)).build();
    private static final Function<Context, List<Locale>> sFuncGetLocales;
    private final int errorCode;
    private final boolean isAvailable;
    private Intent storeLinkIntent;
    private final String targetPackage;

    static {
        c cVar;
        if (Build.VERSION.SDK_INT < 33) {
            cVar = new c(10);
        } else {
            cVar = new c(11);
        }
        sFuncGetLocales = cVar;
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

    /* access modifiers changed from: private */
    public static Bundle callContentProvider(Context context, String str, String str2, Bundle bundle) {
        String str3;
        try {
            Log.d(TAG, "Call cp " + str);
            Uri parse = Uri.parse(getURI(context));
            str3 = str;
            try {
                return (Bundle) Optional.ofNullable(context).map(new f(19)).map(new c(parse, str3, str2, bundle, 1)).orElse(Bundle.EMPTY);
            } catch (Exception e) {
                e = e;
                Exception exc = e;
                Log.e(TAG, "Failed to call cp " + str3, exc);
                return Bundle.EMPTY;
            }
        } catch (Exception e7) {
            e = e7;
            str3 = str;
            Exception exc2 = e;
            Log.e(TAG, "Failed to call cp " + str3, exc2);
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

    public static Context getContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    public static ServerInfo getCurrentServerInfo(ServerFeature serverFeature) {
        ServerType serverType = Repository.settings.getServerType(serverFeature);
        if (serverType != null) {
            return getSupportedServerLists(getContext()).stream().filter(new e(1, serverType)).findFirst().orElse((Object) null);
        }
        return null;
    }

    public static ServerType getCurrentServerType(ServerFeature serverFeature) {
        return Repository.settings.getServerType(serverFeature);
    }

    public static Uri getDictationTermsOfServiceUrl(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return (Uri) Optional.ofNullable(callContentProvider(context, "get_server_asr_tos_url", (String) null, Bundle.EMPTY)).map(new c(8)).map(new C0670h(9)).orElse((Object) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLangpackConfigInfo(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return langpackConfig.getOrCompute(new h(1, context));
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
            Bundle bundle = new Bundle();
            bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.LANGPACK_CONFIG));
            return callContentProvider(context, "get_langpack_config", (String) null, bundle).getString("langpack_config_json");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<BTCLocaleInfo> getSupportedBTCLocaleInfos(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return btcLocaleDatas.getOrCompute(new h(3, context));
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
            return (List) Optional.ofNullable(callContentProvider(context, "get_btc_locale_list", (String) null, bundle)).map(new c(2)).map(new c(3)).orElseGet(new C0720a(12));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public static List<LocaleInfo> getSupportedLocaleInfos(Context context, ConnectionType connectionType) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                Bundle bundle = new Bundle();
                bundle.putInt("connection_type", connectionType.getTypeInt());
                bundle.putParcelable("server_type", Repository.settings.getServerType(ServerFeature.DICTATION_LANGUAGE_INFO));
                return (List) Optional.ofNullable(callContentProvider(context, "get_locale_list", (String) null, bundle)).map(new c(1)).map(new c(9)).orElseGet(new C0720a(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public static List<Locale> getSupportedLocales(Context context) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return localeDatas.getOrCompute(new h(2, context));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /* access modifiers changed from: private */
    public static List<Locale> getSupportedLocalesFromCP(Context context) {
        try {
            List<Locale> list = (List) EnumSet.allOf(ConnectionType.class).parallelStream().map(new c(18)).map(new b(context)).map(new c(19)).filter(new b(0)).flatMap(new f(6)).map(new f(7)).distinct().collect(Collectors.toList());
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

    public static List<ServerInfo> getSupportedServerInfos(Context context, ServerFeature serverFeature) {
        try {
            if (isSupportedSpeechRecognition(context)) {
                return (List) asrServerInfos.getOrCompute(new h(4, context)).stream().filter(new e(2, serverFeature)).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    /* access modifiers changed from: private */
    public static List<ServerInfo> getSupportedServerLists(Context context) {
        try {
            return (List) Optional.ofNullable(callContentProvider(context, "get_server_list", (String) null, Bundle.EMPTY)).map(new c(6)).map(new c(7)).orElseGet(new C0720a(12));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    private static String getURI(Context context) {
        if (((Integer) Optional.ofNullable(context).map(new c(17)).orElse(-1)).intValue() == 0) {
            return "content://com.samsung.android.scs.ai.speech2";
        }
        Log.d(TAG, "System permission doesn't have granted.");
        return "content://com.samsung.android.scs.ai.speech";
    }

    private static boolean isSupportedSpeechRecognition(Context context) {
        if (Feature.checkFeature(context, Feature.FEATURE_SPEECH_RECOGNITION) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getSupportedBTCLocaleInfosFromCP$19(BTCLocaleInfo bTCLocaleInfo) {
        if (bTCLocaleInfo.getDefaultSpeaker() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getSupportedBTCLocaleInfosFromCP$20(ArrayList arrayList) {
        return (List) arrayList.stream().filter(new a(1)).filter(new a(2)).distinct().sorted(Comparator.comparing(new c(16))).collect(Collectors.toList());
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
        return (List) stringArrayList.stream().map(new f(7)).map(new c(5)).filter(new a(0)).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getSupportedLocaleInfos$8(List list) {
        return (List) list.stream().distinct().sorted(Comparator.comparing(new c(4))).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Bundle lambda$getSupportedLocalesFromCP$15(ConnectionType connectionType) {
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
    public static /* synthetic */ List lambda$getSupportedServerLists$23(ArrayList arrayList) {
        return (List) arrayList.stream().filter(new a(3)).distinct().collect(Collectors.toList());
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

    public static boolean setServerType(ServerFeature serverFeature, ServerType serverType) {
        return Repository.settings.setServerType(serverFeature, serverType);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public Intent getStoreLinkAction() {
        return this.storeLinkIntent;
    }

    public String getTargetResourcePackageName() {
        return this.targetPackage;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public String toString() {
        return "Availability{isAvailable=" + this.isAvailable + ", errorCode=" + this.errorCode + ", storeLinkIntent=" + this.storeLinkIntent + '}';
    }

    private Environment(Bundle bundle) {
        this(bundle.getBoolean("is_available"), bundle.getInt("error_code", 0), bundle.getString("resource_package_name"));
    }
}
