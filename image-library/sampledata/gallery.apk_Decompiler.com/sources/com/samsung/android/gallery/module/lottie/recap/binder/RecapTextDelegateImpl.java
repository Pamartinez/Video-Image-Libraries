package com.samsung.android.gallery.module.lottie.recap.binder;

import Ba.h;
import N2.j;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.module.lottie.recap.data.parser.AnalyzedData;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import i.C0212a;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapTextDelegateImpl {
    AnalyzedData d;
    HashMap<String, String> mCache = new HashMap<>();
    HashMap<String, RecapTextLayer> mTextLayers;

    public RecapTextDelegateImpl(HashMap<String, RecapTextLayer> hashMap, AnalyzedData analyzedData) {
        this.mTextLayers = hashMap;
        this.d = analyzedData;
        preload();
    }

    private String getString(int i2, Object... objArr) {
        return AppResources.getString(i2, objArr);
    }

    private String getTextInner(String str, RecapTextLayer recapTextLayer) {
        String str2;
        if (recapTextLayer != null && !recapTextLayer.isEngOnly) {
            if (recapTextLayer.variables != null) {
                setVarValue(recapTextLayer);
            }
            int i2 = recapTextLayer.stringResourceId;
            if (i2 > 0 && recapTextLayer.variables == null) {
                recapTextLayer.targetValue = getString(i2, new Object[0]);
            } else if (i2 > 0 && !TextUtils.isEmpty(recapTextLayer.targetValue)) {
                recapTextLayer.targetValue = getString(recapTextLayer.stringResourceId, recapTextLayer.targetValue);
            }
            if (recapTextLayer.defaultTargetValue != null && ((str2 = recapTextLayer.targetValue) == null || TextUtils.isEmpty(str2.trim()))) {
                recapTextLayer.targetValue = recapTextLayer.defaultTargetValue;
            }
            if (((float) recapTextLayer.repeatMaxWidth) > 0.0f) {
                String str3 = recapTextLayer.targetValue;
                if (recapTextLayer.addSpaceForRepeat) {
                    str3 = C0212a.l(" ", str3);
                }
                StringBuilder sb2 = new StringBuilder();
                C0086a.z(sb2, recapTextLayer.targetValue, str3, str3, str3);
                C0086a.z(sb2, str3, str3, str3, str3);
                recapTextLayer.targetValue = StringCompat.subStringByDrawWidth(j.f(sb2, str3, str3, str3), recapTextLayer.fontTypeFace, (float) recapTextLayer.fontSp, (float) recapTextLayer.repeatMaxWidth, true);
            }
            String str4 = recapTextLayer.targetValue;
            if (str4 != null) {
                return str4;
            }
        }
        return str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preload$0(String str, RecapTextLayer recapTextLayer) {
        String textInner = getTextInner("fail", recapTextLayer);
        Log.i("RecapTextDelegate", C0212a.l("preloadText ", str), Logger.getEncodedString(textInner));
        if (!"fail".equals(textInner)) {
            if (recapTextLayer.useUpperCaseOnly) {
                textInner = textInner.toUpperCase(Locale.getDefault());
                recapTextLayer.targetValue = textInner;
            }
            if (recapTextLayer.minWidthDpForLongText <= 0) {
                this.mCache.put(str, textInner);
            } else if (recapTextLayer.needToUseLongLangLayer()) {
                this.mCache.put(str, " ");
                this.mCache.put(recapTextLayer.layerNameForLongText, textInner);
                Log.i("RecapTextDelegate", "preloadText For layerNameForLongText" + str, Logger.getEncodedString(textInner));
            } else {
                this.mCache.put(recapTextLayer.layerNameForLongText, " ");
            }
        }
    }

    private void preload() {
        this.mTextLayers.forEach(new h(26, this));
    }

    private void setVarValue(RecapTextLayer recapTextLayer) {
        RecapDataVars recapDataVars = recapTextLayer.variables;
        Function<AnalyzedData, String> function = recapDataVars.supplier;
        if (function != null) {
            recapTextLayer.targetValue = function.apply(this.d);
            return;
        }
        BiFunction<AnalyzedData, RecapTextLayer, String> biFunction = recapDataVars.supplier2;
        if (biFunction != null) {
            recapTextLayer.targetValue = biFunction.apply(this.d, recapTextLayer);
        }
    }

    public String getText(String str, String str2) {
        if (this.mCache.containsKey(str)) {
            return this.mCache.get(str);
        }
        RecapTextLayer recapTextLayer = this.mTextLayers.get(str);
        if (recapTextLayer == null) {
            return str2;
        }
        String textInner = getTextInner(str2, recapTextLayer);
        if (recapTextLayer.useUpperCaseOnly) {
            textInner = textInner.toUpperCase(Locale.getDefault());
        }
        Log.i("RecapTextDelegate", C0212a.l("getText ", str), Logger.getEncodedString(textInner));
        this.mCache.put(str, textInner);
        return textInner;
    }
}
