package com.samsung.android.sdk.scs.ai.asr;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.scs.ai.asr.RecognitionConfig;
import com.samsung.android.sivs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.LocaleInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1642a;

    public /* synthetic */ f(int i2) {
        this.f1642a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f1642a) {
            case 0:
                return Boolean.TRUE;
            case 1:
                return ((Bundle) obj).setClassLoader(BTCLocaleInfo.class.getClassLoader());
            case 2:
                return Integer.valueOf(((Context) obj).checkSelfPermission(SpeechRecognitionConst.SYSTEM_PERMISSION_QUERY_CP));
            case 3:
                return Integer.valueOf(((LocaleInfo) obj).getOrder());
            case 4:
                return Environment.lambda$getSupportedLocalesFromCP$18((ConnectionType) obj);
            case 5:
                return ((Bundle) obj).getStringArrayList("locale_list");
            case 6:
                return ((ArrayList) obj).stream();
            case 7:
                return Locale.forLanguageTag((String) obj);
            case 8:
                return ((Bundle) obj).getStringArrayList(SpeechRecognitionConst.Key.GET_AUDIO_TO_TRANSLATION_LOCALE_INFO_LIST);
            case 9:
                return Environment.lambda$getSupportedBTCLocaleInfosFromCP$23((ArrayList) obj);
            case 10:
                return Environment.createLocaleInfo((Locale) obj);
            case 11:
                return ((Bundle) obj).setClassLoader(ServerInfo.class.getClassLoader());
            case 12:
                return Environment.lambda$getSupportedServerLists$26((ArrayList) obj);
            case 13:
                return Integer.valueOf(((BTCLocaleInfo) obj).getOrder());
            case 14:
                return Environment.lambda$getSupportedLocaleInfos$7((Bundle) obj);
            case 15:
                return Environment.lambda$getSupportedLocaleInfos$8((List) obj);
            case 16:
                return Environment.getSupportedLocalesFromRes((Context) obj);
            case 17:
                return ((Bundle) obj).getString("server_asr_tos_url");
            case 18:
                return Integer.valueOf(((Context) obj).checkSelfPermission("com.samsung.android.intellivoiceservice.common.permission.SYSTEM_CONFIG_PROVIDER"));
            case 19:
                return ((Context) obj).getContentResolver();
            case 20:
                return Environment.getSupportedLocalesFromCP((Context) obj);
            case 21:
                return Environment.lambda$static$0((ArrayList) obj);
            case 22:
                return Environment.lambda$static$1((ArrayList) obj);
            case 23:
                return Environment.lambda$static$2((ArrayList) obj);
            case 24:
                return Environment.lambda$static$3((String) obj);
            case 25:
                return Boolean.valueOf(((Bundle) obj).getBoolean(SpeechRecognitionConst.Key.IS_BILINGUAL_ASR_SUPPORTED));
            case 26:
                return ((Supplier) obj).get();
            case 27:
                return Arrays.stream((DictationType[]) obj);
            case 28:
                return RecognitionConfig.Builder.lambda$addDictationType$0((Stream) obj);
            default:
                return RecognitionConfig.Builder.lambda$removeDictationType$1((Stream) obj);
        }
    }
}
