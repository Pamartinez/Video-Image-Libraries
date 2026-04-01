package com.samsung.android.imagetranslation.util;

import android.content.Context;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.text.bnlp.BasicNlpAnalyzer;
import com.samsung.android.sdk.scs.ai.text.bnlp.Token;
import com.samsung.android.sdk.scs.ai.text.language.LanguageDetector;
import com.samsung.android.sdk.scs.base.feature.Feature;
import java.util.List;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NLPAnalyzer {
    private static final String TAG = "NLPAnalyzer";
    private static Boolean isBNLPSupported;
    private final BasicNlpAnalyzer mBasicNlpAnalyzer;
    private final LanguageDetector mLanguageDetector;

    public NLPAnalyzer(Context context) {
        if (isBNLPSupported == null) {
            isBNLPSupported = Boolean.valueOf(Stream.of(new String[]{Feature.FEATURE_TEXT_GET_BNLP_TOKEN, Feature.FEATURE_TEXT_DETECT_LANGUAGE}).allMatch(new h(1, context)));
            String str = TAG;
            LTTLogger.d(str, "check BNLP supported : " + isBNLPSupported);
        }
        this.mBasicNlpAnalyzer = AiServices.getBasicNlpAnalyzer(context.getApplicationContext());
        this.mLanguageDetector = AiServices.getLanguageDetector(context.getApplicationContext());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isSentenceBlock$1(Token token) {
        String posTag = token.getPosTag();
        if (posTag.equals("pron")) {
            return true;
        }
        if (!posTag.equals("interp") || !"?!".contains(token.getRawText())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(Context context, String str) {
        if (Feature.checkFeature(context, str) == 0) {
            return true;
        }
        return false;
    }

    public boolean isSentenceBlock(LttOcrResult.BlockInfo blockInfo) {
        Boolean bool = isBNLPSupported;
        if (!(bool == null || !bool.booleanValue() || this.mBasicNlpAnalyzer == null || this.mLanguageDetector == null)) {
            String string = blockInfo.getString();
            if (string.isEmpty()) {
                return false;
            }
            String detect = this.mLanguageDetector.detect(string);
            if (detect == null || !this.mBasicNlpAnalyzer.isSupportedLanguage(detect)) {
                String str = TAG;
                LTTLogger.d(str, "Not supported language: " + detect);
            } else {
                List<Token> analyzeToken = this.mBasicNlpAnalyzer.analyzeToken(detect, string);
                if (analyzeToken == null) {
                    return false;
                }
                boolean anyMatch = analyzeToken.stream().anyMatch(new j(1));
                String str2 = TAG;
                LTTLogger.d(str2, "sentence block: " + anyMatch);
                return anyMatch;
            }
        }
        return false;
    }
}
