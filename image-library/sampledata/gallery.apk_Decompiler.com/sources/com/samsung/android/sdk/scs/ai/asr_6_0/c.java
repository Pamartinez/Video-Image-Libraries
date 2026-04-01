package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.scs.ai.sdkcommon.asr.BTCLocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.LocaleInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.ServerInfo;
import com.samsung.android.scs.ai.sdkcommon.asr.SpeechRecognitionConst;
import com.samsung.android.sdk.scs.ai.asr_6_0.RecognitionConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1652a;

    public /* synthetic */ c(int i2) {
        this.f1652a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f1652a) {
            case 0:
                return Boolean.TRUE;
            case 1:
                return Environment.lambda$getSupportedLocaleInfos$7((Bundle) obj);
            case 2:
                return ((Bundle) obj).setClassLoader(BTCLocaleInfo.class.getClassLoader());
            case 3:
                return Environment.lambda$getSupportedBTCLocaleInfosFromCP$20((ArrayList) obj);
            case 4:
                return Integer.valueOf(((LocaleInfo) obj).getOrder());
            case 5:
                return Environment.createLocaleInfo((Locale) obj);
            case 6:
                return ((Bundle) obj).setClassLoader(ServerInfo.class.getClassLoader());
            case 7:
                return Environment.lambda$getSupportedServerLists$23((ArrayList) obj);
            case 8:
                return ((Bundle) obj).getString("server_asr_tos_url");
            case 9:
                return Environment.lambda$getSupportedLocaleInfos$8((List) obj);
            case 10:
                return Environment.getSupportedLocalesFromRes((Context) obj);
            case 11:
                return Environment.getSupportedLocalesFromCP((Context) obj);
            case 12:
                return Environment.lambda$static$0((ArrayList) obj);
            case 13:
                return Environment.lambda$static$1((ArrayList) obj);
            case 14:
                return Environment.lambda$static$2((ArrayList) obj);
            case 15:
                return Environment.lambda$static$3((String) obj);
            case 16:
                return Integer.valueOf(((BTCLocaleInfo) obj).getOrder());
            case 17:
                return Integer.valueOf(((Context) obj).checkSelfPermission(SpeechRecognitionConst.SYSTEM_PERMISSION_QUERY_CP));
            case 18:
                return Environment.lambda$getSupportedLocalesFromCP$15((ConnectionType) obj);
            case 19:
                return ((Bundle) obj).getStringArrayList("locale_list");
            case 20:
                return Arrays.stream((DictationType[]) obj);
            case 21:
                return RecognitionConfig.Builder.lambda$addDictationType$0((Stream) obj);
            default:
                return RecognitionConfig.Builder.lambda$removeDictationType$1((Stream) obj);
        }
    }
}
