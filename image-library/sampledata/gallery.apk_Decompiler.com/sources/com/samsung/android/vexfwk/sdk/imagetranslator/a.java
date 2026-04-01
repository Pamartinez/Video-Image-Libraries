package com.samsung.android.vexfwk.sdk.imagetranslator;

import Ae.c;
import android.graphics.Bitmap;
import c0.C0086a;
import com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirections;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.param.VexFwkRequiredLanguages;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionExtensionsKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslatorV2;
import java.util.List;
import me.k;
import me.x;
import ne.C1202t;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends i implements c {
    public /* synthetic */ Object d;
    public final /* synthetic */ VexFwkOcrResultV2 e;
    public final /* synthetic */ VexFwkHelperProcess f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(VexFwkOcrResultV2 vexFwkOcrResultV2, VexFwkHelperProcess vexFwkHelperProcess, C1227c cVar) {
        super(2, cVar);
        this.e = vexFwkOcrResultV2;
        this.f = vexFwkHelperProcess;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        a aVar = new a(this.e, this.f, cVar);
        aVar.d = obj;
        return aVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((a) create(new k(((k) obj).d), (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        VexFwkOcrResult vexFwkOcrResult;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        L2.a.A(obj);
        Object obj2 = ((k) this.d).d;
        Throwable a7 = k.a(obj2);
        if (a7 == null) {
            VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) obj2;
            int i2 = VexFwkImageTranslatorV2$translateImpl$1$2$WhenMappings.$EnumSwitchMapping$0[VexFwkTranslationResultCode.Companion.fromInt(((Number) vexFwkSessionTotalResult.getResultMetadata().getOrThrow(VexFwkMetadataKey.TRANSLATION_RESULT_CODE.INSTANCE)).intValue()).ordinal()];
            List list = C1202t.d;
            if (i2 != 1) {
                String str = "Unknown error";
                if (i2 == 2) {
                    String str2 = (String) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_ERROR_MESSAGE.INSTANCE);
                    if (str2 != null) {
                        str = str2;
                    }
                    return new VexFwkImageTranslatorV2.TranslationResult.Error.InvalidDirection(str);
                } else if (i2 != 3) {
                    String str3 = (String) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_ERROR_MESSAGE.INSTANCE);
                    if (str3 != null) {
                        str = str3;
                    }
                    return new VexFwkImageTranslatorV2.TranslationResult.Error.Others(str);
                } else {
                    List list2 = (VexFwkRequiredLanguages) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.REQUIRED_LANGUAGES.INSTANCE);
                    if (list2 != null) {
                        list = list2;
                    }
                    return new VexFwkImageTranslatorV2.TranslationResult.Error.LanguagePackRequired(list);
                }
            } else {
                VexFwkOcrResultMeta vexFwkOcrResultMeta = (VexFwkOcrResultMeta) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.OCR_RESULT.INSTANCE);
                if (vexFwkOcrResultMeta != null) {
                    VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta = (VexFwkOcrAdditionalMeta) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.OCR_ADDITIONAL_RESULT.INSTANCE);
                    if (vexFwkOcrAdditionalMeta != null) {
                        vexFwkOcrResult = new VexFwkOcrResult(vexFwkOcrResultMeta, vexFwkOcrAdditionalMeta);
                    } else {
                        vexFwkOcrResult = new VexFwkOcrResult(vexFwkOcrResultMeta);
                    }
                } else {
                    vexFwkOcrResult = null;
                }
                Bitmap bitmap = VexFwkSessionExtensionsKt.getOutputBufferOrThrow(vexFwkSessionTotalResult, 1).getBitmap();
                List list3 = (VexFwkLanguageDirections) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.LANGUAGE_DIRECTIONS.INSTANCE);
                if (list3 != null) {
                    list = list3;
                }
                return new VexFwkImageTranslatorV2.TranslationResult.Success(bitmap, list, vexFwkOcrResult, this.e);
            }
        } else {
            C0086a.x("Failed to process request : ", VexFwkImageTranslatorV2.TAG, a7);
            throw a7;
        }
    }
}
