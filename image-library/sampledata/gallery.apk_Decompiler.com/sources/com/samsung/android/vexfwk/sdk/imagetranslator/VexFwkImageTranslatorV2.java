package com.samsung.android.vexfwk.sdk.imagetranslator;

import Ad.l;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.Size;
import c0.C0086a;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.u;
import com.samsung.android.vexfwk.imagetranslation.VexFwkLanguagePackInfo;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.param.VexFwkIImageTranslatorCapabilities;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirection;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import i.C0212a;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;
import ne.C1202t;
import qe.C1227c;
import ud.d;
import vd.p;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 ;2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002;<B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JI\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u000f¢\u0006\u0004\b\u0019\u0010\u001aJ=\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u001b\u001a\u00020\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b¢\u0006\u0004\b\u001f\u0010 J=\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u001b\u001a\u00020\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001e\u001a\u00020\r¢\u0006\u0004\b\u001f\u0010!J#\u0010'\u001a\u00020&2\u0006\u0010\"\u001a\u00020\u00022\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#¢\u0006\u0004\b'\u0010(J)\u0010)\u001a\b\u0012\u0004\u0012\u00020&0\u000f2\u0006\u0010\"\u001a\u00020\u00022\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#¢\u0006\u0004\b)\u0010*J\u001b\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u000f2\u0006\u0010+\u001a\u00020\b¢\u0006\u0004\b-\u0010.J\u001b\u00100\u001a\b\u0012\u0004\u0012\u00020,0\u000f2\u0006\u0010/\u001a\u00020\b¢\u0006\u0004\b0\u0010.J\u0013\u00101\u001a\b\u0012\u0004\u0012\u00020,0\u000f¢\u0006\u0004\b1\u0010\u001aR\u001a\u0010\u0003\u001a\u00020\u00028\u0014X\u0004¢\u0006\f\n\u0004\b\u0003\u00102\u001a\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u0002058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b:\u00107¨\u0006="}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBase;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Bitmap;", "buffer", "", "from", "to", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "ocrResult", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "ocrResultV2", "Lcom/google/common/util/concurrent/ListenableFuture;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult;", "translateImpl", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)Lcom/google/common/util/concurrent/ListenableFuture;", "", "width", "height", "", "isAvailableSize", "(II)Z", "configure", "()Lcom/google/common/util/concurrent/ListenableFuture;", "image", "fromLang", "toLang", "vexFwkOcrResult", "translate", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;)Lcom/google/common/util/concurrent/ListenableFuture;", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)Lcom/google/common/util/concurrent/ListenableFuture;", "activityContext", "", "Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguagePackInfo;", "requiredLanguagePack", "Lme/x;", "showInstallPopup", "(Landroid/content/Context;Ljava/util/List;)V", "showInstallPopupAsync", "(Landroid/content/Context;Ljava/util/List;)Lcom/google/common/util/concurrent/ListenableFuture;", "sourceLang", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageCapabilities;", "getAvailableTargetLanguageFrom", "(Ljava/lang/String;)Lcom/google/common/util/concurrent/ListenableFuture;", "targetLang", "getAvailableSourceLanguageTo", "getAvailableLanguages", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/util/Size;", "defaultAvailableSize", "Landroid/util/Size;", "sizeLimitation", "I", "availableSize", "Companion", "TranslationResult", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTranslatorV2 extends VexFwkHelperBase<VexFwkImageTranslatorV2> {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    /* access modifiers changed from: private */
    public static final String TAG = "VexFwkImageTranslatorV2";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkHelperBase.Companion.isAvailable(VexFwkUsecase.IMAGE_TRANSLATION);
    private Size availableSize = new Size(0, 0);
    private final Context context;
    private final Size defaultAvailableSize;
    private int sizeLimitation;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkImageTranslatorV2.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult;", "", "<init>", "()V", "Error", "Success", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class TranslationResult {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult;", "<init>", "()V", "InvalidDirection", "UnsupportedImageSize", "LanguagePackRequired", "Others", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$InvalidDirection;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$LanguagePackRequired;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$Others;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$UnsupportedImageSize;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static abstract class Error extends TranslationResult {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$InvalidDirection;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class InvalidDirection extends Error {
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public InvalidDirection(String str) {
                    super((e) null);
                    j.e(str, "message");
                    this.message = str;
                }

                public static /* synthetic */ InvalidDirection copy$default(InvalidDirection invalidDirection, String str, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        str = invalidDirection.message;
                    }
                    return invalidDirection.copy(str);
                }

                public final String component1() {
                    return this.message;
                }

                public final InvalidDirection copy(String str) {
                    j.e(str, "message");
                    return new InvalidDirection(str);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if ((obj instanceof InvalidDirection) && j.a(this.message, ((InvalidDirection) obj).message)) {
                        return true;
                    }
                    return false;
                }

                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                public String toString() {
                    return C0212a.m("InvalidDirection(message=", this.message, ")");
                }
            }

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$LanguagePackRequired;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error;", "requiredLanguages", "", "Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguagePackInfo;", "<init>", "(Ljava/util/List;)V", "getRequiredLanguages", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class LanguagePackRequired extends Error {
                private final List<VexFwkLanguagePackInfo> requiredLanguages;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public LanguagePackRequired(List<VexFwkLanguagePackInfo> list) {
                    super((e) null);
                    j.e(list, "requiredLanguages");
                    this.requiredLanguages = list;
                }

                public static /* synthetic */ LanguagePackRequired copy$default(LanguagePackRequired languagePackRequired, List<VexFwkLanguagePackInfo> list, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        list = languagePackRequired.requiredLanguages;
                    }
                    return languagePackRequired.copy(list);
                }

                public final List<VexFwkLanguagePackInfo> component1() {
                    return this.requiredLanguages;
                }

                public final LanguagePackRequired copy(List<VexFwkLanguagePackInfo> list) {
                    j.e(list, "requiredLanguages");
                    return new LanguagePackRequired(list);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if ((obj instanceof LanguagePackRequired) && j.a(this.requiredLanguages, ((LanguagePackRequired) obj).requiredLanguages)) {
                        return true;
                    }
                    return false;
                }

                public final List<VexFwkLanguagePackInfo> getRequiredLanguages() {
                    return this.requiredLanguages;
                }

                public int hashCode() {
                    return this.requiredLanguages.hashCode();
                }

                public String toString() {
                    List<VexFwkLanguagePackInfo> list = this.requiredLanguages;
                    return "LanguagePackRequired(requiredLanguages=" + list + ")";
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$Others;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Others extends Error {
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Others(String str) {
                    super((e) null);
                    j.e(str, "message");
                    this.message = str;
                }

                public static /* synthetic */ Others copy$default(Others others, String str, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        str = others.message;
                    }
                    return others.copy(str);
                }

                public final String component1() {
                    return this.message;
                }

                public final Others copy(String str) {
                    j.e(str, "message");
                    return new Others(str);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if ((obj instanceof Others) && j.a(this.message, ((Others) obj).message)) {
                        return true;
                    }
                    return false;
                }

                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                public String toString() {
                    return C0212a.m("Others(message=", this.message, ")");
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error$UnsupportedImageSize;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class UnsupportedImageSize extends Error {
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public UnsupportedImageSize(String str) {
                    super((e) null);
                    j.e(str, "message");
                    this.message = str;
                }

                public static /* synthetic */ UnsupportedImageSize copy$default(UnsupportedImageSize unsupportedImageSize, String str, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        str = unsupportedImageSize.message;
                    }
                    return unsupportedImageSize.copy(str);
                }

                public final String component1() {
                    return this.message;
                }

                public final UnsupportedImageSize copy(String str) {
                    j.e(str, "message");
                    return new UnsupportedImageSize(str);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if ((obj instanceof UnsupportedImageSize) && j.a(this.message, ((UnsupportedImageSize) obj).message)) {
                        return true;
                    }
                    return false;
                }

                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                public String toString() {
                    return C0212a.m("UnsupportedImageSize(message=", this.message, ")");
                }
            }

            public /* synthetic */ Error(e eVar) {
                this();
            }

            private Error() {
                super((e) null);
            }
        }

        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\nHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult$Success;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslatorV2$TranslationResult;", "image", "Landroid/graphics/Bitmap;", "translatedDirections", "", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection;", "vexFwkOcrResult", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "vexFwkOcrResultV2", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "<init>", "(Landroid/graphics/Bitmap;Ljava/util/List;Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)V", "getImage", "()Landroid/graphics/Bitmap;", "getTranslatedDirections", "()Ljava/util/List;", "getVexFwkOcrResult", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "getVexFwkOcrResultV2", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class Success extends TranslationResult {
            private final Bitmap image;
            private final List<VexFwkLanguageDirection> translatedDirections;
            private final VexFwkOcrResult vexFwkOcrResult;
            private final VexFwkOcrResultV2 vexFwkOcrResultV2;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Success(Bitmap bitmap, List list, VexFwkOcrResult vexFwkOcrResult2, VexFwkOcrResultV2 vexFwkOcrResultV22, int i2, e eVar) {
                this(bitmap, list, (i2 & 4) != 0 ? null : vexFwkOcrResult2, (i2 & 8) != 0 ? null : vexFwkOcrResultV22);
            }

            public static /* synthetic */ Success copy$default(Success success, Bitmap bitmap, List<VexFwkLanguageDirection> list, VexFwkOcrResult vexFwkOcrResult2, VexFwkOcrResultV2 vexFwkOcrResultV22, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    bitmap = success.image;
                }
                if ((i2 & 2) != 0) {
                    list = success.translatedDirections;
                }
                if ((i2 & 4) != 0) {
                    vexFwkOcrResult2 = success.vexFwkOcrResult;
                }
                if ((i2 & 8) != 0) {
                    vexFwkOcrResultV22 = success.vexFwkOcrResultV2;
                }
                return success.copy(bitmap, list, vexFwkOcrResult2, vexFwkOcrResultV22);
            }

            public final Bitmap component1() {
                return this.image;
            }

            public final List<VexFwkLanguageDirection> component2() {
                return this.translatedDirections;
            }

            public final VexFwkOcrResult component3() {
                return this.vexFwkOcrResult;
            }

            public final VexFwkOcrResultV2 component4() {
                return this.vexFwkOcrResultV2;
            }

            public final Success copy(Bitmap bitmap, List<VexFwkLanguageDirection> list, VexFwkOcrResult vexFwkOcrResult2, VexFwkOcrResultV2 vexFwkOcrResultV22) {
                j.e(bitmap, "image");
                j.e(list, "translatedDirections");
                return new Success(bitmap, list, vexFwkOcrResult2, vexFwkOcrResultV22);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Success)) {
                    return false;
                }
                Success success = (Success) obj;
                if (j.a(this.image, success.image) && j.a(this.translatedDirections, success.translatedDirections) && j.a(this.vexFwkOcrResult, success.vexFwkOcrResult) && j.a(this.vexFwkOcrResultV2, success.vexFwkOcrResultV2)) {
                    return true;
                }
                return false;
            }

            public final Bitmap getImage() {
                return this.image;
            }

            public final List<VexFwkLanguageDirection> getTranslatedDirections() {
                return this.translatedDirections;
            }

            public final VexFwkOcrResult getVexFwkOcrResult() {
                return this.vexFwkOcrResult;
            }

            public final VexFwkOcrResultV2 getVexFwkOcrResultV2() {
                return this.vexFwkOcrResultV2;
            }

            public int hashCode() {
                int i2;
                int f = C0212a.f(this.translatedDirections, this.image.hashCode() * 31, 31);
                VexFwkOcrResult vexFwkOcrResult2 = this.vexFwkOcrResult;
                int i7 = 0;
                if (vexFwkOcrResult2 == null) {
                    i2 = 0;
                } else {
                    i2 = vexFwkOcrResult2.hashCode();
                }
                int i8 = (f + i2) * 31;
                VexFwkOcrResultV2 vexFwkOcrResultV22 = this.vexFwkOcrResultV2;
                if (vexFwkOcrResultV22 != null) {
                    i7 = vexFwkOcrResultV22.hashCode();
                }
                return i8 + i7;
            }

            public String toString() {
                Bitmap bitmap = this.image;
                List<VexFwkLanguageDirection> list = this.translatedDirections;
                VexFwkOcrResult vexFwkOcrResult2 = this.vexFwkOcrResult;
                VexFwkOcrResultV2 vexFwkOcrResultV22 = this.vexFwkOcrResultV2;
                return "Success(image=" + bitmap + ", translatedDirections=" + list + ", vexFwkOcrResult=" + vexFwkOcrResult2 + ", vexFwkOcrResultV2=" + vexFwkOcrResultV22 + ")";
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Success(Bitmap bitmap, List<VexFwkLanguageDirection> list, VexFwkOcrResult vexFwkOcrResult2, VexFwkOcrResultV2 vexFwkOcrResultV22) {
                super((e) null);
                j.e(bitmap, "image");
                j.e(list, "translatedDirections");
                this.image = bitmap;
                this.translatedDirections = list;
                this.vexFwkOcrResult = vexFwkOcrResult2;
                this.vexFwkOcrResultV2 = vexFwkOcrResultV22;
            }
        }

        public /* synthetic */ TranslationResult(e eVar) {
            this();
        }

        private TranslationResult() {
        }
    }

    public VexFwkImageTranslatorV2(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        Size size = new Size(5000, 5000);
        this.defaultAvailableSize = size;
        this.sizeLimitation = size.getHeight() * size.getWidth();
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    private final boolean isAvailableSize(int i2, int i7) {
        if (this.availableSize.getWidth() == 0 || this.availableSize.getHeight() == 0) {
            VexFwkIImageTranslatorCapabilities vexFwkIImageTranslatorCapabilities = (VexFwkIImageTranslatorCapabilities) VexFwkHelperBase.Companion.getProperties(VexFwkUsecase.IMAGE_TRANSLATION, VexFwkMetadataKey.PROPERTY_IMAGE_TRANSLATION_CAPABILITIES.INSTANCE, new b(8));
            String str = TAG;
            Log.d(str, "imageTranslatorCapabilities : " + vexFwkIImageTranslatorCapabilities);
            Object obj = vexFwkIImageTranslatorCapabilities.get(0);
            j.d(obj, "get(...)");
            int intValue = ((Number) obj).intValue();
            Object obj2 = vexFwkIImageTranslatorCapabilities.get(1);
            j.d(obj2, "get(...)");
            Size size = new Size(intValue, ((Number) obj2).intValue());
            this.availableSize = size;
            if (!(size.getWidth() == 0 || this.availableSize.getHeight() == 0)) {
                this.sizeLimitation = this.availableSize.getHeight() * this.availableSize.getWidth();
            }
        }
        if (i2 * i7 <= this.sizeLimitation) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final VexFwkIImageTranslatorCapabilities isAvailableSize$lambda$0() {
        return new VexFwkIImageTranslatorCapabilities(new int[]{0, 0});
    }

    public static /* synthetic */ ListenableFuture translate$default(VexFwkImageTranslatorV2 vexFwkImageTranslatorV2, Bitmap bitmap, String str, String str2, VexFwkOcrResult vexFwkOcrResult, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        if ((i2 & 8) != 0) {
            vexFwkOcrResult = new VexFwkOcrResult(C1202t.d, (List) null, false, 6, (e) null);
        }
        return vexFwkImageTranslatorV2.translate(bitmap, str, str2, vexFwkOcrResult);
    }

    private final ListenableFuture translateImpl(Bitmap bitmap, String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2) {
        if (!isAvailableSize(bitmap.getWidth(), bitmap.getHeight())) {
            String str3 = TAG;
            int i2 = this.sizeLimitation;
            Log.w(str3, "Maximum pixel size: " + i2);
            return new u(new TranslationResult.Error.UnsupportedImageSize(C0086a.i(this.sizeLimitation, "Maximum pixel size: ")));
        }
        return supplyAsyncProcess(new b(str, str2, vexFwkOcrResult, vexFwkOcrResultV2, bitmap, (C1227c) null));
    }

    public static /* synthetic */ ListenableFuture translateImpl$default(VexFwkImageTranslatorV2 vexFwkImageTranslatorV2, Bitmap bitmap, String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            vexFwkOcrResult = null;
        }
        if ((i2 & 16) != 0) {
            vexFwkOcrResultV2 = null;
        }
        return vexFwkImageTranslatorV2.translateImpl(bitmap, str, str2, vexFwkOcrResult, vexFwkOcrResultV2);
    }

    public final ListenableFuture configure() {
        return supplyAsyncConfiguration(new d(2, (C1227c) null, 1));
    }

    public final ListenableFuture getAvailableLanguages() {
        return supplyAsyncProcess(new d(2, (C1227c) null, 2));
    }

    public final ListenableFuture getAvailableSourceLanguageTo(String str) {
        j.e(str, "targetLang");
        return supplyAsyncProcess(new p(str, (C1227c) null, 0));
    }

    public final ListenableFuture getAvailableTargetLanguageFrom(String str) {
        j.e(str, "sourceLang");
        return supplyAsyncProcess(new p(str, (C1227c) null, 1));
    }

    public Context getContext() {
        return this.context;
    }

    public final void showInstallPopup(Context context2, List<VexFwkLanguagePackInfo> list) {
        j.e(context2, "activityContext");
        j.e(list, "requiredLanguagePack");
        try {
            showInstallPopupAsync(context2, list).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Log.w(TAG, "showInstallPopup: ", e);
        }
    }

    public final ListenableFuture showInstallPopupAsync(Context context2, List<VexFwkLanguagePackInfo> list) {
        j.e(context2, "activityContext");
        j.e(list, "requiredLanguagePack");
        return supplyAsyncProcess(new l((List) list, context2, (C1227c) null));
    }

    public final ListenableFuture translate(Bitmap bitmap, String str, String str2, VexFwkOcrResult vexFwkOcrResult) {
        j.e(bitmap, "image");
        j.e(vexFwkOcrResult, "vexFwkOcrResult");
        return translateImpl$default(this, bitmap, str, str2, vexFwkOcrResult, (VexFwkOcrResultV2) null, 16, (Object) null);
    }

    public final ListenableFuture translate(Bitmap bitmap, String str, String str2, VexFwkOcrResultV2 vexFwkOcrResultV2) {
        j.e(bitmap, "image");
        j.e(vexFwkOcrResultV2, "vexFwkOcrResult");
        return translateImpl$default(this, bitmap, str, str2, (VexFwkOcrResult) null, vexFwkOcrResultV2, 8, (Object) null);
    }

    public static /* synthetic */ ListenableFuture translate$default(VexFwkImageTranslatorV2 vexFwkImageTranslatorV2, Bitmap bitmap, String str, String str2, VexFwkOcrResultV2 vexFwkOcrResultV2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        if ((i2 & 8) != 0) {
            C1202t tVar = C1202t.d;
            vexFwkOcrResultV2 = new VexFwkOcrResultV2(tVar, tVar, false);
        }
        return vexFwkImageTranslatorV2.translate(bitmap, str, str2, vexFwkOcrResultV2);
    }
}
