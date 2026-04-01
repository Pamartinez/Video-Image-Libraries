package com.samsung.android.vexfwk.sdk.imagetranslator;

import He.F;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.vexfwk.bitmap.VexFwkBitmapHardware;
import com.samsung.android.vexfwk.imagetranslation.LanguagePackInstaller;
import com.samsung.android.vexfwk.imagetranslation.VexFwkLanguagePackInfo;
import com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationCommandType;
import com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataKey;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion;
import com.samsung.android.vexfwk.param.VexFwkIImageTranslatorCapabilities;
import com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirection;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirections;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultConverterKt;
import com.samsung.android.vexfwk.param.VexFwkOcrResultKt;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.param.VexFwkRequiredLanguages;
import com.samsung.android.vexfwk.sdk.common.VexFwkSdkBase;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkProvider;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.sdk.docscan.b;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import me.k;
import me.x;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import vd.a;
import vd.c;
import vd.d;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 A2\u00020\u0001:\u0002ABB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005J=\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\u0010J=\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u0011¢\u0006\u0004\b\u000f\u0010\u0012J#\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u0019\u0010\u001aJ)\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\r2\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\r2\u0006\u0010\u001e\u001a\u00020\b¢\u0006\u0004\b \u0010!J\u001b\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\r2\u0006\u0010\"\u001a\u00020\b¢\u0006\u0004\b#\u0010!J\u0013\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001f0\r¢\u0006\u0004\b$\u0010%JO\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\"\u0004\b\u0000\u0010&2\u0006\u0010'\u001a\u00028\u00002\b\u0010(\u001a\u0004\u0018\u00010\b2\b\u0010)\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b,\u0010-J'\u00101\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u0011002\u0006\u0010/\u001a\u00020.H\u0002¢\u0006\u0004\b1\u00102J\u001f\u00107\u001a\u0002062\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u000203H\u0002¢\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u000206H\u0002¢\u0006\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u0002038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020;8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010=¨\u0006C"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkSdkBase;", "<init>", "()V", "configure", "()Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator;", "Landroid/graphics/Bitmap;", "image", "", "fromLang", "toLang", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "vexFwkOcrResult", "Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;", "translate", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;)Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)Ljava/util/concurrent/CompletableFuture;", "Landroid/content/Context;", "activityContext", "", "Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguagePackInfo;", "requiredLanguagePack", "Lme/x;", "showInstallPopup", "(Landroid/content/Context;Ljava/util/List;)V", "Ljava/lang/Void;", "showInstallPopupAsync", "(Landroid/content/Context;Ljava/util/List;)Ljava/util/concurrent/CompletableFuture;", "sourceLang", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageCapabilities;", "getAvailableTargetLanguageFrom", "(Ljava/lang/String;)Ljava/util/concurrent/CompletableFuture;", "targetLang", "getAvailableSourceLanguageTo", "getAvailableLanguages", "()Ljava/util/concurrent/CompletableFuture;", "T", "buffer", "from", "to", "ocrResult", "ocrResultV2", "translateImpl", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)Ljava/util/concurrent/CompletableFuture;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "resultMetadata", "Lme/i;", "processOcrResponse", "(Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;)Lme/i;", "", "width", "height", "", "isAvailableSize", "(II)Z", "doesSupportV2", "()Z", "Landroid/util/Size;", "defaultAvailableSize", "Landroid/util/Size;", "sizeLimitation", "I", "availableSize", "Companion", "TranslationResult", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTranslator extends VexFwkSdkBase {
    public static final Companion Companion = new Companion((e) null);
    private static final int STREAM_ID_INPUT_IMAGE = 0;
    private static final int STREAM_ID_OUTPUT_IMAGE = 1;
    private static final String TAG = "VexFwkImageTranslator";
    /* access modifiers changed from: private */
    public static final boolean isAvailable = VexFwkSdkBase.Companion.isAvailable(VexFwkUsecase.IMAGE_TRANSLATION);
    private Size availableSize = new Size(0, 0);
    private final Size defaultAvailableSize;
    private int sizeLimitation;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "STREAM_ID_INPUT_IMAGE", "", "STREAM_ID_OUTPUT_IMAGE", "isAvailable", "", "isAvailable$annotations", "()Z", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isAvailable() {
            return VexFwkImageTranslator.isAvailable;
        }

        private Companion() {
        }

        public static /* synthetic */ void isAvailable$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;", "", "<init>", "()V", "Error", "Success", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Success;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class TranslationResult {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;", "<init>", "()V", "InvalidDirection", "UnsupportedImageSize", "LanguagePackRequired", "Others", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$InvalidDirection;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$LanguagePackRequired;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$Others;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$UnsupportedImageSize;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static abstract class Error extends TranslationResult {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$InvalidDirection;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$LanguagePackRequired;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error;", "requiredLanguages", "", "Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguagePackInfo;", "<init>", "(Ljava/util/List;)V", "getRequiredLanguages", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$Others;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error$UnsupportedImageSize;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\nHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult$Success;", "Lcom/samsung/android/vexfwk/sdk/imagetranslator/VexFwkImageTranslator$TranslationResult;", "image", "Landroid/graphics/Bitmap;", "translatedDirections", "", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection;", "vexFwkOcrResult", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "vexFwkOcrResultV2", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "<init>", "(Landroid/graphics/Bitmap;Ljava/util/List;Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;)V", "getImage", "()Landroid/graphics/Bitmap;", "getTranslatedDirections", "()Ljava/util/List;", "getVexFwkOcrResult", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResult;", "getVexFwkOcrResultV2", "()Lcom/samsung/android/vexfwk/param/VexFwkOcrResultV2;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|(2:1|2)|3|5|6|(2:7|8)|9|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0019 */
        static {
            /*
                com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode[] r0 = com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode r2 = com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode r3 = com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode.INVALID_DIRECTION     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode r3 = com.samsung.android.vexfwk.imagetranslation.VexFwkTranslationResultCode.LANGUAGE_PACK_REQUIRED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r4 = 3
                r0[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion[] r0 = com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion r3 = com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion.RESULT_V1     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion r1 = com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion.RESULT_V2     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator.WhenMappings.<clinit>():void");
        }
    }

    public VexFwkImageTranslator() {
        Size size = new Size(5000, 5000);
        this.defaultAvailableSize = size;
        this.sizeLimitation = size.getHeight() * size.getWidth();
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2(VexFwkImageTranslator vexFwkImageTranslator) {
        j.e(vexFwkImageTranslator, "$this$configureWith");
        vexFwkImageTranslator.createSession(VexFwkUsecase.IMAGE_TRANSLATION, new d(1));
        vexFwkImageTranslator.createSession(VexFwkUsecase.TRANSLATION_UTIL, new b(27));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2$lambda$0(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        VexFwkStreamType vexFwkStreamType = VexFwkStreamType.BUFFER;
        VexFwkStreamUsage vexFwkStreamUsage = VexFwkStreamUsage.IMAGE;
        VexFwkSessionConfigRequest.Builder builder2 = builder;
        VexFwkSessionConfigRequest.Builder.addInputStream$default(builder2, 0, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        VexFwkSessionConfigRequest.Builder.addOutputStream$default(builder2, 1, vexFwkStreamType, vexFwkStreamUsage, 0, 0, 0, (Surface) null, 120, (Object) null);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x configure$lambda$2$lambda$1(VexFwkSessionConfigRequest.Builder builder) {
        j.e(builder, "$this$createSession");
        return x.f4917a;
    }

    private final boolean doesSupportV2() {
        VexFwkImageOcrResultVersion vexFwkImageOcrResultVersion = (VexFwkImageOcrResultVersion) VexFwkProvider.fetchProperties(VexFwkUsecase.IMAGE_TRANSLATION).getOrElse(VexFwkMetadataKey.OCR_RESULT_VERSION.INSTANCE, new e5.d(16));
        String str = TAG;
        Log.d(str, "ocrResultType : " + vexFwkImageOcrResultVersion);
        if (vexFwkImageOcrResultVersion == VexFwkImageOcrResultVersion.RESULT_V2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final VexFwkImageOcrResultVersion doesSupportV2$lambda$61$lambda$59() {
        return VexFwkImageOcrResultVersion.RESULT_V1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        He.F.u(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005c, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0065, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        He.F.u(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities getAvailableLanguages$lambda$55(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r4) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "get available languages E"
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            com.samsung.android.vexfwk.sdk.docscan.b r2 = new com.samsung.android.vexfwk.sdk.docscan.b
            r3 = 28
            r2.<init>(r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r1 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r1, r2)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r1 = r1.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r2 = com.samsung.android.vexfwk.session.VexFwkUsecase.TRANSLATION_UTIL     // Catch:{ all -> 0x0063 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r4 = r4.getSession(r2)     // Catch:{ all -> 0x0063 }
            java.lang.Object r4 = r4.m60processRequestIoAF18A(r1)     // Catch:{ all -> 0x0063 }
            r2 = 0
            He.F.u(r1, r2)
            java.lang.Throwable r1 = me.k.a(r4)
            if (r1 != 0) goto L_0x005d
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r4
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r1 = r4.getResultMetadata()     // Catch:{ all -> 0x004c }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$TRANSLATION_CAPABILITIES r3 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.TRANSLATION_CAPABILITIES.INSTANCE     // Catch:{ all -> 0x004c }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x004c }
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r1 = (com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities) r1     // Catch:{ all -> 0x004c }
            if (r1 != 0) goto L_0x004e
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r1 = new com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities     // Catch:{ all -> 0x004c }
            java.util.LinkedList r3 = new java.util.LinkedList     // Catch:{ all -> 0x004c }
            r3.<init>()     // Catch:{ all -> 0x004c }
            r1.<init>(r3)     // Catch:{ all -> 0x004c }
            goto L_0x004e
        L_0x004c:
            r0 = move-exception
            goto L_0x0057
        L_0x004e:
            He.F.u(r4, r2)
            java.lang.String r4 = "get available languages X"
            android.util.Log.i(r0, r4)
            return r1
        L_0x0057:
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r1 = move-exception
            He.F.u(r4, r0)
            throw r1
        L_0x005d:
            java.lang.String r4 = "Failed to process request : "
            c0.C0086a.x(r4, r0, r1)
            throw r1
        L_0x0063:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0065 }
        L_0x0065:
            r0 = move-exception
            He.F.u(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator.getAvailableLanguages$lambda$55(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator):com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities");
    }

    /* access modifiers changed from: private */
    public static final x getAvailableLanguages$lambda$55$lambda$48(VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_LANGUAGE_LIST.getValue()));
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bb, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bc, code lost:
        He.F.u(r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bf, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities getAvailableSourceLanguageTo$lambda$47(java.lang.String r6, com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r7) {
        /*
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "get available languages to "
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r3 = " E"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            h3.a r3 = new h3.a
            r4 = 5
            r3.<init>(r6, r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r1 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r1, r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r1 = r1.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r3 = com.samsung.android.vexfwk.session.VexFwkUsecase.TRANSLATION_UTIL     // Catch:{ all -> 0x00b9 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = r7.getSession(r3)     // Catch:{ all -> 0x00b9 }
            java.lang.Object r7 = r7.m60processRequestIoAF18A(r1)     // Catch:{ all -> 0x00b9 }
            r3 = 0
            He.F.u(r1, r3)
            java.lang.Throwable r1 = me.k.a(r7)
            if (r1 != 0) goto L_0x00b3
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r7
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r0 = r7.getResultMetadata()     // Catch:{ all -> 0x005c }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$TRANSLATION_CAPABILITIES r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.TRANSLATION_CAPABILITIES.INSTANCE     // Catch:{ all -> 0x005c }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x005c }
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r0 = (com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities) r0     // Catch:{ all -> 0x005c }
            if (r0 != 0) goto L_0x005e
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r0 = new com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities     // Catch:{ all -> 0x005c }
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ all -> 0x005c }
            r1.<init>()     // Catch:{ all -> 0x005c }
            r0.<init>(r1)     // Catch:{ all -> 0x005c }
            goto L_0x0093
        L_0x005c:
            r6 = move-exception
            goto L_0x00ad
        L_0x005e:
            java.util.List r0 = ne.C1194l.H0(r0)     // Catch:{ all -> 0x005c }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x005c }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x005c }
            r1.<init>()     // Catch:{ all -> 0x005c }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x005c }
        L_0x006d:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x005c }
            if (r4 == 0) goto L_0x0088
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x005c }
            r5 = r4
            com.samsung.android.vexfwk.imagetranslation.VexFwkAvailableLanguage r5 = (com.samsung.android.vexfwk.imagetranslation.VexFwkAvailableLanguage) r5     // Catch:{ all -> 0x005c }
            java.lang.String r5 = r5.getLang()     // Catch:{ all -> 0x005c }
            boolean r5 = kotlin.jvm.internal.j.a(r5, r6)     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x006d
            r1.add(r4)     // Catch:{ all -> 0x005c }
            goto L_0x006d
        L_0x0088:
            java.util.LinkedList r0 = new java.util.LinkedList     // Catch:{ all -> 0x005c }
            r0.<init>(r1)     // Catch:{ all -> 0x005c }
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r1 = new com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities     // Catch:{ all -> 0x005c }
            r1.<init>(r0)     // Catch:{ all -> 0x005c }
            r0 = r1
        L_0x0093:
            He.F.u(r7, r3)
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = " X"
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            android.util.Log.i(r7, r6)
            return r0
        L_0x00ad:
            throw r6     // Catch:{ all -> 0x00ae }
        L_0x00ae:
            r0 = move-exception
            He.F.u(r7, r6)
            throw r0
        L_0x00b3:
            java.lang.String r6 = "Failed to process request : "
            c0.C0086a.x(r6, r0, r1)
            throw r1
        L_0x00b9:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r7 = move-exception
            He.F.u(r1, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator.getAvailableSourceLanguageTo$lambda$47(java.lang.String, com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator):com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities");
    }

    /* access modifiers changed from: private */
    public static final x getAvailableSourceLanguageTo$lambda$47$lambda$39(String str, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_LANGUAGE_LIST.getValue()));
        vexFwkMetadataNative.set(VexFwkMetadataKey.OUTPUT_PATH.INSTANCE, str);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bb, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bc, code lost:
        He.F.u(r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bf, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities getAvailableTargetLanguageFrom$lambda$38(java.lang.String r6, com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r7) {
        /*
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "get available languages from "
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r3 = " E"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            h3.a r3 = new h3.a
            r4 = 6
            r3.<init>(r6, r4)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r1 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r1, r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r1 = r1.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r3 = com.samsung.android.vexfwk.session.VexFwkUsecase.TRANSLATION_UTIL     // Catch:{ all -> 0x00b9 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r7 = r7.getSession(r3)     // Catch:{ all -> 0x00b9 }
            java.lang.Object r7 = r7.m60processRequestIoAF18A(r1)     // Catch:{ all -> 0x00b9 }
            r3 = 0
            He.F.u(r1, r3)
            java.lang.Throwable r1 = me.k.a(r7)
            if (r1 != 0) goto L_0x00b3
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r7 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r7
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r0 = r7.getResultMetadata()     // Catch:{ all -> 0x005c }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$TRANSLATION_CAPABILITIES r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.TRANSLATION_CAPABILITIES.INSTANCE     // Catch:{ all -> 0x005c }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x005c }
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r0 = (com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities) r0     // Catch:{ all -> 0x005c }
            if (r0 != 0) goto L_0x005e
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r0 = new com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities     // Catch:{ all -> 0x005c }
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ all -> 0x005c }
            r1.<init>()     // Catch:{ all -> 0x005c }
            r0.<init>(r1)     // Catch:{ all -> 0x005c }
            goto L_0x0093
        L_0x005c:
            r6 = move-exception
            goto L_0x00ad
        L_0x005e:
            java.util.List r0 = ne.C1194l.H0(r0)     // Catch:{ all -> 0x005c }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ all -> 0x005c }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x005c }
            r1.<init>()     // Catch:{ all -> 0x005c }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x005c }
        L_0x006d:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x005c }
            if (r4 == 0) goto L_0x0088
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x005c }
            r5 = r4
            com.samsung.android.vexfwk.imagetranslation.VexFwkAvailableLanguage r5 = (com.samsung.android.vexfwk.imagetranslation.VexFwkAvailableLanguage) r5     // Catch:{ all -> 0x005c }
            java.lang.String r5 = r5.getLang()     // Catch:{ all -> 0x005c }
            boolean r5 = kotlin.jvm.internal.j.a(r5, r6)     // Catch:{ all -> 0x005c }
            if (r5 != 0) goto L_0x006d
            r1.add(r4)     // Catch:{ all -> 0x005c }
            goto L_0x006d
        L_0x0088:
            java.util.LinkedList r0 = new java.util.LinkedList     // Catch:{ all -> 0x005c }
            r0.<init>(r1)     // Catch:{ all -> 0x005c }
            com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities r1 = new com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities     // Catch:{ all -> 0x005c }
            r1.<init>(r0)     // Catch:{ all -> 0x005c }
            r0 = r1
        L_0x0093:
            He.F.u(r7, r3)
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = " X"
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            android.util.Log.i(r7, r6)
            return r0
        L_0x00ad:
            throw r6     // Catch:{ all -> 0x00ae }
        L_0x00ae:
            r0 = move-exception
            He.F.u(r7, r6)
            throw r0
        L_0x00b3:
            java.lang.String r6 = "Failed to process request : "
            c0.C0086a.x(r6, r0, r1)
            throw r1
        L_0x00b9:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r7 = move-exception
            He.F.u(r1, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator.getAvailableTargetLanguageFrom$lambda$38(java.lang.String, com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator):com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities");
    }

    /* access modifiers changed from: private */
    public static final x getAvailableTargetLanguageFrom$lambda$38$lambda$30(String str, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_LANGUAGE_LIST.getValue()));
        vexFwkMetadataNative.set(VexFwkMetadataKey.INPUT_PATH.INSTANCE, str);
        return x.f4917a;
    }

    public static final boolean isAvailable() {
        return Companion.isAvailable();
    }

    private final boolean isAvailableSize(int i2, int i7) {
        if (this.availableSize.getWidth() == 0 || this.availableSize.getHeight() == 0) {
            VexFwkIImageTranslatorCapabilities vexFwkIImageTranslatorCapabilities = (VexFwkIImageTranslatorCapabilities) VexFwkProvider.fetchProperties(VexFwkUsecase.IMAGE_TRANSLATION).getOrElse(VexFwkMetadataKey.PROPERTY_IMAGE_TRANSLATION_CAPABILITIES.INSTANCE, new e5.d(17));
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
    public static final VexFwkIImageTranslatorCapabilities isAvailableSize$lambda$58$lambda$56() {
        return new VexFwkIImageTranslatorCapabilities(new int[]{0, 0});
    }

    private final i processOcrResponse(VexFwkMetadataNative vexFwkMetadataNative) {
        VexFwkOcrResult vexFwkOcrResult;
        VexFwkOcrResultV2 vexFwkOcrResultV2;
        VexFwkImageOcrResultVersion vexFwkImageOcrResultVersion = (VexFwkImageOcrResultVersion) vexFwkMetadataNative.get(VexFwkMetadataKey.OCR_RESULT_VERSION.INSTANCE);
        if (vexFwkImageOcrResultVersion == null) {
            vexFwkImageOcrResultVersion = VexFwkImageOcrResultVersion.RESULT_V1;
        }
        Log.i(TAG, "ocr result version : " + vexFwkImageOcrResultVersion);
        int i2 = WhenMappings.$EnumSwitchMapping$1[vexFwkImageOcrResultVersion.ordinal()];
        if (i2 == 1) {
            VexFwkOcrResultMeta vexFwkOcrResultMeta = (VexFwkOcrResultMeta) vexFwkMetadataNative.get(VexFwkMetadataKey.OCR_RESULT.INSTANCE);
            if (vexFwkOcrResultMeta != null) {
                VexFwkOcrAdditionalMeta vexFwkOcrAdditionalMeta = (VexFwkOcrAdditionalMeta) vexFwkMetadataNative.get(VexFwkMetadataKey.OCR_ADDITIONAL_RESULT.INSTANCE);
                if (vexFwkOcrAdditionalMeta != null) {
                    vexFwkOcrResult = new VexFwkOcrResult(vexFwkOcrResultMeta, vexFwkOcrAdditionalMeta);
                } else {
                    vexFwkOcrResult = new VexFwkOcrResult(vexFwkOcrResultMeta);
                }
            } else {
                vexFwkOcrResult = null;
            }
            return new i(vexFwkOcrResult, (Object) null);
        } else if (i2 == 2) {
            VexFwkOcrResultMetaV2 vexFwkOcrResultMetaV2 = (VexFwkOcrResultMetaV2) vexFwkMetadataNative.get(VexFwkMetadataKey.OCR_RESULT_V2.INSTANCE);
            if (vexFwkOcrResultMetaV2 != null) {
                vexFwkOcrResultV2 = vexFwkOcrResultMetaV2.toResult();
            } else {
                vexFwkOcrResultV2 = null;
            }
            if (vexFwkOcrResultV2 != null) {
                VexFwkOcrResultConverterKt.toV1(vexFwkOcrResultV2);
            }
            return new i((Object) null, vexFwkOcrResultV2);
        } else {
            throw new RuntimeException();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        He.F.u(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        He.F.u(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String showInstallPopupAsync$lambda$26(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator r4) {
        /*
            java.lang.String r0 = TAG
            java.lang.String r1 = "showInstallPopupAsync E"
            android.util.Log.i(r0, r1)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = new com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder
            r1.<init>()
            vd.d r2 = new vd.d
            r3 = 0
            r2.<init>(r3)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Builder r1 = com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequestKt.setSettingMetadata(r1, r2)
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync$Builder r1 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest.Sync.Builder) r1
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionRequest$Sync r1 = r1.build()
            com.samsung.android.vexfwk.session.VexFwkUsecase r2 = com.samsung.android.vexfwk.session.VexFwkUsecase.TRANSLATION_UTIL     // Catch:{ all -> 0x0053 }
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSession r4 = r4.getSession(r2)     // Catch:{ all -> 0x0053 }
            java.lang.Object r4 = r4.m60processRequestIoAF18A(r1)     // Catch:{ all -> 0x0053 }
            r2 = 0
            He.F.u(r1, r2)
            java.lang.Throwable r1 = me.k.a(r4)
            if (r1 != 0) goto L_0x004d
            com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult r4 = (com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult) r4
            com.samsung.android.vexfwk.metadata.VexFwkMetadataNative r0 = r4.getResultMetadata()     // Catch:{ all -> 0x0046 }
            com.samsung.android.vexfwk.metadata.VexFwkMetadataKey$STRING_RESOURCE r1 = com.samsung.android.vexfwk.metadata.VexFwkMetadataKey.STRING_RESOURCE.INSTANCE     // Catch:{ all -> 0x0046 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0046 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0046 }
            if (r0 != 0) goto L_0x0042
            java.lang.String r0 = "translate"
        L_0x0042:
            He.F.u(r4, r2)
            return r0
        L_0x0046:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r1 = move-exception
            He.F.u(r4, r0)
            throw r1
        L_0x004d:
            java.lang.String r4 = "Failed to process request : "
            c0.C0086a.x(r4, r0, r1)
            throw r1
        L_0x0053:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            He.F.u(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator.showInstallPopupAsync$lambda$26(com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static final x showInstallPopupAsync$lambda$26$lambda$20(String str, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        vexFwkMetadataNative.set(VexFwkMetadataKey.TRANSLATION_COMMAND_TYPE.INSTANCE, Integer.valueOf(VexFwkTranslationCommandType.COMMAND_TYPE_STRING_RESOURCE.getValue()));
        vexFwkMetadataNative.set(VexFwkMetadataKey.STRING_RESOURCE_NAME.INSTANCE, str);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x showInstallPopupAsync$lambda$28(List list, Context context, String str) {
        Iterable<VexFwkLanguagePackInfo> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (VexFwkLanguagePackInfo languagePackCode : iterable) {
            arrayList.add(languagePackCode.getLanguagePackCode());
        }
        List H02 = C1194l.H0(arrayList);
        if (H02.size() > 2) {
            LanguagePackInstaller languagePackInstaller = new LanguagePackInstaller(context);
            ArrayList arrayList2 = new ArrayList();
            C1194l.i1(H02, arrayList2);
            languagePackInstaller.goToLangPackDownload(arrayList2);
        } else {
            LanguagePackInstaller languagePackInstaller2 = new LanguagePackInstaller(context);
            String R02 = C1194l.R0(H02, GlobalPostProcInternalPPInterface.SPLIT_REGEX, (String) null, (String) null, (Ae.b) null, 62);
            j.b(str);
            languagePackInstaller2.requestLangPackDownload(R02, str);
        }
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final void showInstallPopupAsync$lambda$29(Ae.b bVar, Object obj) {
        bVar.invoke(obj);
    }

    public static /* synthetic */ CompletableFuture translate$default(VexFwkImageTranslator vexFwkImageTranslator, Bitmap bitmap, String str, String str2, VexFwkOcrResult vexFwkOcrResult, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        if ((i2 & 8) != 0) {
            vexFwkOcrResult = new VexFwkOcrResult(C1202t.d, (List) null, false, 6, (e) null);
        }
        return vexFwkImageTranslator.translate(bitmap, str, str2, vexFwkOcrResult);
    }

    private final <T> CompletableFuture<TranslationResult> translateImpl(T t, String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2) {
        CompletableFuture<TranslationResult> supplyAsync = CompletableFuture.supplyAsync(new vd.e(t, this, str, str2, vexFwkOcrResult, vexFwkOcrResultV2));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public static /* synthetic */ CompletableFuture translateImpl$default(VexFwkImageTranslator vexFwkImageTranslator, Object obj, String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2, int i2, Object obj2) {
        if ((i2 & 8) != 0) {
            vexFwkOcrResult = null;
        }
        if ((i2 & 16) != 0) {
            vexFwkOcrResultV2 = null;
        }
        return vexFwkImageTranslator.translateImpl(obj, str, str2, vexFwkOcrResult, vexFwkOcrResultV2);
    }

    /* access modifiers changed from: private */
    public static final TranslationResult translateImpl$lambda$16(Object obj, VexFwkImageTranslator vexFwkImageTranslator, String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2) {
        Size size;
        TranslationResult translationResult;
        VexFwkBitmapHardware vexFwkBitmapHardware;
        Throwable th;
        Throwable th2;
        TranslationResult.Success success;
        String str3 = TAG;
        Log.i(str3, "image translation E");
        if (obj instanceof Image) {
            Image image = (Image) obj;
            size = new Size(image.getWidth(), image.getHeight());
        } else if (obj instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) obj;
            size = new Size(bitmap.getWidth(), bitmap.getHeight());
        } else {
            throw new IllegalArgumentException("Unsupported image type");
        }
        VexFwkBitmapHardware vexFwkBitmapHardware2 = new VexFwkBitmapHardware(size.getWidth(), size.getHeight(), 1, (int[]) null);
        boolean isAvailableSize = vexFwkImageTranslator.isAvailableSize(vexFwkBitmapHardware2.getWidth(), vexFwkBitmapHardware2.getHeight());
        if (!isAvailableSize) {
            int i2 = vexFwkImageTranslator.sizeLimitation;
            Log.w(str3, "Maximum pixel size: " + i2);
            translationResult = new TranslationResult.Error.UnsupportedImageSize(C0086a.i(vexFwkImageTranslator.sizeLimitation, "Maximum pixel size: "));
            vexFwkBitmapHardware = vexFwkBitmapHardware2;
        } else if (isAvailableSize) {
            VexFwkSessionRequest.Sync build = ((VexFwkSessionRequest.Sync.Builder) VexFwkSessionRequestKt.setSettingMetadata(VexFwkSessionRequestKt.addOutputBuffer(VexFwkSessionRequestKt.addInputBuffer(new VexFwkSessionRequest.Sync.Builder(), 0, obj), 1, vexFwkBitmapHardware2.getBitmap()), new c(str, str2, vexFwkOcrResult, vexFwkOcrResultV2, vexFwkImageTranslator, 0))).build();
            try {
                Object r1 = vexFwkImageTranslator.getSession(VexFwkUsecase.IMAGE_TRANSLATION).m60processRequestIoAF18A(build);
                F.u(build, (Throwable) null);
                Throwable a7 = k.a(r1);
                if (a7 == null) {
                    VexFwkSessionTotalResult vexFwkSessionTotalResult = (VexFwkSessionTotalResult) r1;
                    try {
                        Object obj2 = vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_RESULT_CODE.INSTANCE);
                        j.b(obj2);
                        int i7 = WhenMappings.$EnumSwitchMapping$0[VexFwkTranslationResultCode.Companion.fromInt(((Number) obj2).intValue()).ordinal()];
                        List list = C1202t.d;
                        if (i7 != 1) {
                            String str4 = "Unknown error";
                            if (i7 == 2) {
                                String str5 = (String) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_ERROR_MESSAGE.INSTANCE);
                                if (str5 != null) {
                                    str4 = str5;
                                }
                                success = new TranslationResult.Error.InvalidDirection(str4);
                            } else if (i7 != 3) {
                                String str6 = (String) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.TRANSLATION_ERROR_MESSAGE.INSTANCE);
                                if (str6 != null) {
                                    str4 = str6;
                                }
                                success = new TranslationResult.Error.Others(str4);
                            } else {
                                List list2 = (VexFwkRequiredLanguages) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.REQUIRED_LANGUAGES.INSTANCE);
                                if (list2 != null) {
                                    list = list2;
                                }
                                success = new TranslationResult.Error.LanguagePackRequired(list);
                            }
                            vexFwkBitmapHardware = vexFwkBitmapHardware2;
                        } else {
                            vexFwkBitmapHardware = vexFwkBitmapHardware2;
                            Bitmap bitmapCropped = vexFwkBitmapHardware.toBitmapCropped(0, 0, vexFwkBitmapHardware2.getWidth(), vexFwkBitmapHardware2.getHeight(), Bitmap.Config.ARGB_8888);
                            i processOcrResponse = vexFwkImageTranslator.processOcrResponse(vexFwkSessionTotalResult.getResultMetadata());
                            VexFwkOcrResult vexFwkOcrResult2 = (VexFwkOcrResult) processOcrResponse.d;
                            VexFwkOcrResultV2 vexFwkOcrResultV22 = (VexFwkOcrResultV2) processOcrResponse.e;
                            List list3 = (VexFwkLanguageDirections) vexFwkSessionTotalResult.getResultMetadata().get(VexFwkMetadataKey.LANGUAGE_DIRECTIONS.INSTANCE);
                            if (list3 != null) {
                                list = list3;
                            }
                            success = new TranslationResult.Success(bitmapCropped, list, vexFwkOcrResult2, vexFwkOcrResultV22);
                        }
                        F.u(vexFwkSessionTotalResult, (Throwable) null);
                        Log.i(str3, "image translation X");
                        translationResult = success;
                    } catch (Throwable th3) {
                        F.u(vexFwkSessionTotalResult, th2);
                        throw th3;
                    }
                } else {
                    C0086a.x("Failed to process request : ", str3, a7);
                    throw a7;
                }
            } catch (Throwable th4) {
                F.u(build, th);
                throw th4;
            }
        } else {
            throw new RuntimeException();
        }
        vexFwkBitmapHardware.close();
        return translationResult;
    }

    /* access modifiers changed from: private */
    public static final x translateImpl$lambda$16$lambda$8(String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2, VexFwkImageTranslator vexFwkImageTranslator, VexFwkMetadataNative vexFwkMetadataNative) {
        j.e(vexFwkMetadataNative, "$this$setSettingMetadata");
        if (str != null) {
            vexFwkMetadataNative.set(VexFwkMetadataKey.INPUT_PATH.INSTANCE, str);
        }
        if (str2 != null) {
            vexFwkMetadataNative.set(VexFwkMetadataKey.OUTPUT_PATH.INSTANCE, str2);
        }
        if (vexFwkOcrResult != null) {
            vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT.INSTANCE, VexFwkOcrResultKt.toResultMeta(vexFwkOcrResult));
            vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_ADDITIONAL_RESULT.INSTANCE, VexFwkOcrResultKt.toAdditionalMeta(vexFwkOcrResult));
            vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT_VERSION.INSTANCE, VexFwkImageOcrResultVersion.RESULT_V1);
        }
        if (vexFwkOcrResultV2 != null) {
            if (vexFwkImageTranslator.doesSupportV2()) {
                vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT_V2.INSTANCE, vexFwkOcrResultV2.toResultMeta());
                vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT_VERSION.INSTANCE, VexFwkImageOcrResultVersion.RESULT_V2);
            } else {
                vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT.INSTANCE, VexFwkOcrResultKt.toResultMeta(VexFwkOcrResultConverterKt.toV1(vexFwkOcrResultV2)));
                vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_ADDITIONAL_RESULT.INSTANCE, VexFwkOcrResultKt.toAdditionalMeta(VexFwkOcrResultConverterKt.toV1(vexFwkOcrResultV2)));
                vexFwkMetadataNative.set(VexFwkMetadataKey.OCR_RESULT_VERSION.INSTANCE, VexFwkImageOcrResultVersion.RESULT_V1);
            }
        }
        return x.f4917a;
    }

    public final VexFwkImageTranslator configure() {
        return (VexFwkImageTranslator) configureWith(this, new b(29));
    }

    public final CompletableFuture<VexFwkLanguageCapabilities> getAvailableLanguages() {
        CompletableFuture<VexFwkLanguageCapabilities> supplyAsync = CompletableFuture.supplyAsync(new vd.b(this, 1));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public final CompletableFuture<VexFwkLanguageCapabilities> getAvailableSourceLanguageTo(String str) {
        j.e(str, "targetLang");
        CompletableFuture<VexFwkLanguageCapabilities> supplyAsync = CompletableFuture.supplyAsync(new a(str, this, 0));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public final CompletableFuture<VexFwkLanguageCapabilities> getAvailableTargetLanguageFrom(String str) {
        j.e(str, "sourceLang");
        CompletableFuture<VexFwkLanguageCapabilities> supplyAsync = CompletableFuture.supplyAsync(new a(str, this, 1));
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    public final void showInstallPopup(Context context, List<VexFwkLanguagePackInfo> list) {
        j.e(context, "activityContext");
        j.e(list, "requiredLanguagePack");
        try {
            showInstallPopupAsync(context, list).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            Log.w(TAG, "showInstallPopup: ", e);
        }
    }

    public final CompletableFuture<Void> showInstallPopupAsync(Context context, List<VexFwkLanguagePackInfo> list) {
        j.e(context, "activityContext");
        j.e(list, "requiredLanguagePack");
        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(new vd.b(this, 0)).thenAccept(new o4.a(25, new Wf.c(14, list, context)));
        j.d(thenAccept, "thenAccept(...)");
        return thenAccept;
    }

    public final CompletableFuture<TranslationResult> translate(Bitmap bitmap, String str, String str2, VexFwkOcrResult vexFwkOcrResult) {
        j.e(bitmap, "image");
        j.e(vexFwkOcrResult, "vexFwkOcrResult");
        return translateImpl$default(this, bitmap, str, str2, vexFwkOcrResult, (VexFwkOcrResultV2) null, 16, (Object) null);
    }

    public final CompletableFuture<TranslationResult> translate(Bitmap bitmap, String str, String str2, VexFwkOcrResultV2 vexFwkOcrResultV2) {
        j.e(bitmap, "image");
        j.e(vexFwkOcrResultV2, "vexFwkOcrResult");
        return translateImpl$default(this, bitmap, str, str2, (VexFwkOcrResult) null, vexFwkOcrResultV2, 8, (Object) null);
    }

    public static /* synthetic */ CompletableFuture translate$default(VexFwkImageTranslator vexFwkImageTranslator, Bitmap bitmap, String str, String str2, VexFwkOcrResultV2 vexFwkOcrResultV2, int i2, Object obj) {
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
        return vexFwkImageTranslator.translate(bitmap, str, str2, vexFwkOcrResultV2);
    }
}
