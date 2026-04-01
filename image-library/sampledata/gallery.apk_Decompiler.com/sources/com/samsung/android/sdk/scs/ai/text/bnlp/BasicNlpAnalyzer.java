package com.samsung.android.sdk.scs.ai.text.bnlp;

import G.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import c4.C0431a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasicNlpAnalyzer {
    private static final Set<String> SUPPORTED_LANGUAGE = ((Set) Stream.of(new String[]{"EN", "DE", "ES", "FR", "IT", "KO", "ZH"}).collect(Collectors.collectingAndThen(Collectors.toSet(), new C0431a(25))));
    private static final String TAG = "ScsApi@BasicNlpAnalyzer";
    private final boolean isBnlpSupported;
    private final boolean isBnlpTokenSupported;
    private TextServiceExecutor mServiceExecutor;

    public BasicNlpAnalyzer(Context context) {
        boolean z;
        boolean z3 = false;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_BNLP) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isBnlpSupported = z;
        this.isBnlpTokenSupported = Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_BNLP_TOKEN) == 0 ? true : z3;
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    private List<Sentence> analyzeCore(String str, String str2, boolean z) {
        ArrayList arrayList = new ArrayList();
        Bundle requestAnalyze = requestAnalyze(str, str2, z, false, false);
        if (requestAnalyze == null) {
            Log.e(TAG, "BasicNlpAnalyzer.analyze(). ContentResolver result is null!");
            return arrayList;
        }
        int i2 = requestAnalyze.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 != 1) {
            C0086a.u(i2, "unexpected resultCode!!! resultCode: ", TAG);
            return arrayList;
        }
        ArrayList<String> stringArrayList = requestAnalyze.getStringArrayList("sentenceRawTextList");
        ArrayList<Integer> integerArrayList = requestAnalyze.getIntegerArrayList("sentencePositionList");
        ArrayList arrayList2 = (ArrayList) requestAnalyze.getSerializable("wordRawTextList");
        ArrayList arrayList3 = (ArrayList) requestAnalyze.getSerializable("wordPositionList");
        ArrayList arrayList4 = (ArrayList) requestAnalyze.getSerializable("tokenRawTextList");
        ArrayList arrayList5 = (ArrayList) requestAnalyze.getSerializable("tokenPositionList");
        ArrayList arrayList6 = (ArrayList) requestAnalyze.getSerializable("tokenPosTagList");
        ArrayList arrayList7 = (ArrayList) requestAnalyze.getSerializable("tokenStemList");
        ArrayList arrayList8 = (ArrayList) requestAnalyze.getSerializable("tokenLemmaList");
        ArrayList arrayList9 = (ArrayList) requestAnalyze.getSerializable("tokenMpTagsList");
        if (stringArrayList == null || integerArrayList == null || arrayList2 == null || arrayList3 == null || arrayList4 == null || arrayList5 == null || arrayList6 == null) {
            ArrayList arrayList10 = arrayList;
            ArrayList arrayList11 = arrayList2;
            ArrayList arrayList12 = arrayList3;
            ArrayList arrayList13 = arrayList4;
            ArrayList arrayList14 = arrayList5;
            ArrayList arrayList15 = arrayList6;
            Log.e(TAG, "null!! sentenceRawTextList: " + stringArrayList + ", sentencePositionList: " + integerArrayList + ", wordRawTextList: " + arrayList11 + ", wordPositionList: " + arrayList12 + ", tokenRawTextList: " + arrayList13 + ", tokenPositionList: " + arrayList14 + ", tokenPosTagList: " + arrayList15);
            return arrayList10;
        }
        int size = integerArrayList.size();
        int i7 = 0;
        while (i7 < size) {
            ArrayList arrayList16 = (ArrayList) arrayList2.get(i7);
            ArrayList arrayList17 = (ArrayList) arrayList3.get(i7);
            ArrayList arrayList18 = (ArrayList) arrayList4.get(i7);
            int i8 = size;
            ArrayList arrayList19 = (ArrayList) arrayList5.get(i7);
            ArrayList arrayList20 = arrayList6;
            ArrayList arrayList21 = (ArrayList) arrayList6.get(i7);
            ArrayList arrayList22 = arrayList7;
            ArrayList arrayList23 = (ArrayList) arrayList7.get(i7);
            ArrayList arrayList24 = arrayList8;
            ArrayList arrayList25 = (ArrayList) arrayList8.get(i7);
            ArrayList arrayList26 = arrayList9;
            ArrayList arrayList27 = (ArrayList) arrayList9.get(i7);
            ArrayList arrayList28 = arrayList5;
            ArrayList arrayList29 = new ArrayList();
            ArrayList arrayList30 = arrayList4;
            int size2 = arrayList17.size();
            ArrayList arrayList31 = arrayList3;
            int i10 = 0;
            while (i10 < size2) {
                int i11 = size2;
                ArrayList arrayList32 = (ArrayList) arrayList18.get(i10);
                ArrayList arrayList33 = arrayList19;
                ArrayList arrayList34 = (ArrayList) arrayList19.get(i10);
                ArrayList arrayList35 = arrayList21;
                ArrayList arrayList36 = (ArrayList) arrayList21.get(i10);
                ArrayList arrayList37 = arrayList23;
                ArrayList arrayList38 = (ArrayList) arrayList23.get(i10);
                ArrayList arrayList39 = arrayList25;
                ArrayList arrayList40 = (ArrayList) arrayList25.get(i10);
                ArrayList arrayList41 = arrayList27;
                ArrayList arrayList42 = (ArrayList) arrayList27.get(i10);
                ArrayList arrayList43 = arrayList18;
                ArrayList arrayList44 = new ArrayList();
                ArrayList arrayList45 = arrayList2;
                int size3 = arrayList34.size();
                ArrayList arrayList46 = arrayList;
                for (int i12 = 0; i12 < size3; i12++) {
                    arrayList44.add(new Token((String) arrayList32.get(i12), ((Integer) arrayList34.get(i12)).intValue(), (String) arrayList36.get(i12), (String) arrayList38.get(i12), (String) arrayList40.get(i12), (String) arrayList42.get(i12)));
                }
                arrayList29.add(new Word((String) arrayList16.get(i10), ((Integer) arrayList17.get(i10)).intValue(), arrayList44));
                i10++;
                arrayList18 = arrayList43;
                size2 = i11;
                arrayList19 = arrayList33;
                arrayList21 = arrayList35;
                arrayList23 = arrayList37;
                arrayList25 = arrayList39;
                arrayList27 = arrayList41;
                arrayList2 = arrayList45;
                arrayList = arrayList46;
            }
            ArrayList arrayList47 = arrayList;
            ArrayList arrayList48 = arrayList2;
            ArrayList arrayList49 = arrayList47;
            arrayList49.add(new Sentence(stringArrayList.get(i7), integerArrayList.get(i7).intValue(), arrayList29));
            i7++;
            size = i8;
            arrayList6 = arrayList20;
            arrayList7 = arrayList22;
            arrayList = arrayList49;
            arrayList5 = arrayList28;
            arrayList8 = arrayList24;
            arrayList9 = arrayList26;
            arrayList4 = arrayList30;
            arrayList3 = arrayList31;
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isSupportedLanguage$0(String str) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("language", str);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getBasicNlpSupported", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "BasicNlpAnalyzer.isSupported(). ContentResolver result is null!!");
                return Boolean.FALSE;
            } else if (call.isEmpty()) {
                Log.e(TAG, "BasicNlpAnalyzer.isSupported(). result is empty!! App version is lower than Sdk so just check in static Array");
                return Boolean.valueOf(SUPPORTED_LANGUAGE.contains(str.toUpperCase()));
            } else {
                int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
                if (i2 == 1) {
                    return Boolean.valueOf(call.getBoolean("textSupportedBoolean"));
                }
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception :: isSupportedLanguage", e);
            return Boolean.FALSE;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Bundle lambda$requestAnalyze$1(String str, String str2, boolean z, boolean z3, boolean z7) {
        try {
            int length = str.length();
            if (length > 100000) {
                Log.i(TAG, String.format("BasicNLP input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length), 100000, 100000}));
                str = str.substring(0, 100000);
            }
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("language", str2);
            bundle.putString("string", str);
            bundle.putBoolean("keepSyllable", z);
            bundle.putBoolean("lightModel", z3);
            Uri parse = Uri.parse("content://com.samsung.android.scs.ai.text");
            if (z7) {
                return textContentResolver.call(parse, "getBasicNlpToken", (String) null, bundle);
            }
            return textContentResolver.call(parse, "getBasicNlp", (String) null, bundle);
        } catch (Exception e) {
            Log.e(TAG, "Exception :: requestAnalyze", e);
            return null;
        }
    }

    private Bundle requestAnalyze(String str, String str2, boolean z, boolean z3, boolean z7) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a(this, str2, str, z, z7, z3));
        try {
            Bundle bundle = (Bundle) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            newSingleThreadExecutor.shutdownNow();
            return bundle;
        } catch (TimeoutException e) {
            TimeoutException timeoutException = e;
            submit.cancel(true);
            Log.e(TAG, "Timeout in requestAnalyze : " + timeoutException.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Exception e7) {
            Exception exc = e7;
            Log.e(TAG, "Exception occurred in requestAnalyze : " + exc.getMessage());
            newSingleThreadExecutor.shutdownNow();
            return null;
        } catch (Throwable th) {
            Throwable th2 = th;
            newSingleThreadExecutor.shutdownNow();
            throw th2;
        }
    }

    public List<Sentence> analyze(String str, String str2) {
        Log.d(TAG, "BasicNLP analyze - language : " + str);
        if (this.isBnlpSupported) {
            return analyzeCore(str, str2, false);
        }
        Log.e(TAG, "Feature.FEATURE_TEXT_GET_BNLP not supported!");
        return new ArrayList();
    }

    public List<Token> analyzeToken(String str, String str2) {
        return analyzeToken(str, str2, false);
    }

    public boolean isSupportedLanguage(String str) {
        Boolean bool;
        Log.d(TAG, "BasicNlpAnalyzer isSupportedLanguage : " + str);
        if (!this.isBnlpSupported) {
            return false;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a(3, this, str));
        try {
            bool = (Boolean) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in isSupportedLanguage : " + e.getMessage());
            bool = Boolean.FALSE;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in isSupportedLanguage : " + e7.getMessage());
            bool = Boolean.FALSE;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
        newSingleThreadExecutor.shutdownNow();
        return bool.booleanValue();
    }

    public List<Token> analyzeToken(String str, String str2, boolean z) {
        return analyzeToken(str, str2, z, false);
    }

    public List<Token> analyzeToken(String str, String str2, boolean z, boolean z3) {
        Log.d(TAG, "BasicNLP analyzeToken - language : " + str);
        ArrayList arrayList = new ArrayList();
        if (!this.isBnlpTokenSupported) {
            Log.e(TAG, "Feature.FEATURE_TEXT_GET_BNLP_TOKEN not supported!");
            return arrayList;
        }
        Bundle requestAnalyze = requestAnalyze(str, str2, z, true, z3);
        if (requestAnalyze == null) {
            Log.e(TAG, "BasicNlpAnalyzer.analyzeToken(). ContentResolver result is null!");
            return arrayList;
        }
        int i2 = requestAnalyze.getInt(OCRServiceConstant.KEY_RESULT_CODE);
        if (i2 != 1) {
            C0086a.u(i2, "unexpected resultCode!!! resultCode: ", TAG);
            return arrayList;
        }
        ArrayList<String> stringArrayList = requestAnalyze.getStringArrayList("tokenRawTextList");
        ArrayList<Integer> integerArrayList = requestAnalyze.getIntegerArrayList("tokenPositionList");
        ArrayList<String> stringArrayList2 = requestAnalyze.getStringArrayList("tokenPosTagList");
        ArrayList<String> stringArrayList3 = requestAnalyze.getStringArrayList("tokenStemList");
        ArrayList<String> stringArrayList4 = requestAnalyze.getStringArrayList("tokenLemmaList");
        ArrayList<String> stringArrayList5 = requestAnalyze.getStringArrayList("tokenMpTagsList");
        if (stringArrayList == null || integerArrayList == null || stringArrayList2 == null || stringArrayList3 == null || stringArrayList4 == null || stringArrayList5 == null) {
            Log.e(TAG, "null!! tokenRawTextList: " + stringArrayList + ", tokenPositionList: " + integerArrayList + ", tokenPosTagList: " + stringArrayList2 + ", tokenStemList: " + stringArrayList3 + ", tokenLemmaList: " + stringArrayList4 + ", tokenMpTagsList: " + stringArrayList5);
            return arrayList;
        }
        int size = integerArrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(new Token(stringArrayList.get(i7), integerArrayList.get(i7).intValue(), stringArrayList2.get(i7), stringArrayList3.get(i7), stringArrayList4.get(i7), stringArrayList5.get(i7)));
        }
        return arrayList;
    }

    public List<Sentence> analyze(String str, String str2, boolean z) {
        Log.d(TAG, "BasicNLP analyze keepSyllable - language : " + str);
        if (this.isBnlpTokenSupported) {
            return analyzeCore(str, str2, z);
        }
        Log.e(TAG, "Feature.FEATURE_TEXT_GET_BNLP_TOKEN not supported!");
        return new ArrayList();
    }
}
