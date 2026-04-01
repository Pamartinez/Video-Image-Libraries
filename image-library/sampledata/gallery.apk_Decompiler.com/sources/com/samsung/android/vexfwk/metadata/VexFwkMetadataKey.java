package com.samsung.android.vexfwk.metadata;

import Be.a;
import He.C0748d;
import android.graphics.PointF;
import android.util.Size;
import com.samsung.android.vexfwk.docscan.VexFwkDocRefinerCapabilities;
import com.samsung.android.vexfwk.imageeffect.VexFwkImageEffectCapabilities;
import com.samsung.android.vexfwk.imagestyletransfer.VexFwkImageStyleTransferCapabilities;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrCapabilities;
import com.samsung.android.vexfwk.ocr.VexFwkImageOcrResultVersion;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectFeature;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectParams;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1;
import com.samsung.android.vexfwk.param.VexFwkBokehEffectSupportedFeatures;
import com.samsung.android.vexfwk.param.VexFwkDepthMapCapabilities;
import com.samsung.android.vexfwk.param.VexFwkDepthMapParams;
import com.samsung.android.vexfwk.param.VexFwkDocumentCorners;
import com.samsung.android.vexfwk.param.VexFwkDocumentDetectionSupportedTypes;
import com.samsung.android.vexfwk.param.VexFwkDocumentDetectionType;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerModes;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerParams;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerResult;
import com.samsung.android.vexfwk.param.VexFwkDocumentRefinerSupportedModes;
import com.samsung.android.vexfwk.param.VexFwkDocumentScanRect;
import com.samsung.android.vexfwk.param.VexFwkFocalLengthCapabilities;
import com.samsung.android.vexfwk.param.VexFwkFocalLengthParams;
import com.samsung.android.vexfwk.param.VexFwkFrcStatus;
import com.samsung.android.vexfwk.param.VexFwkIImageTranslatorCapabilities;
import com.samsung.android.vexfwk.param.VexFwkImageClipperInfo;
import com.samsung.android.vexfwk.param.VexFwkImageEffectParams;
import com.samsung.android.vexfwk.param.VexFwkImageEnhancerParams;
import com.samsung.android.vexfwk.param.VexFwkImageTaggerResult;
import com.samsung.android.vexfwk.param.VexFwkImageTaggerV2Result;
import com.samsung.android.vexfwk.param.VexFwkLanguageCapabilities;
import com.samsung.android.vexfwk.param.VexFwkLanguageCodes;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirection;
import com.samsung.android.vexfwk.param.VexFwkLanguageDirections;
import com.samsung.android.vexfwk.param.VexFwkNodeResultCode;
import com.samsung.android.vexfwk.param.VexFwkObjectRemoverMode;
import com.samsung.android.vexfwk.param.VexFwkObjectRemoverSupportedModes;
import com.samsung.android.vexfwk.param.VexFwkOcrAdditionalMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMeta;
import com.samsung.android.vexfwk.param.VexFwkOcrResultMetaV2;
import com.samsung.android.vexfwk.param.VexFwkParamBoolean;
import com.samsung.android.vexfwk.param.VexFwkParamFloat;
import com.samsung.android.vexfwk.param.VexFwkParamInteger;
import com.samsung.android.vexfwk.param.VexFwkParamIntegerArray;
import com.samsung.android.vexfwk.param.VexFwkParamLong;
import com.samsung.android.vexfwk.param.VexFwkParamPointF;
import com.samsung.android.vexfwk.param.VexFwkParamSize;
import com.samsung.android.vexfwk.param.VexFwkParamString;
import com.samsung.android.vexfwk.param.VexFwkRequiredLanguages;
import com.samsung.android.vexfwk.param.VexFwkSegmentMap;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorMode;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorResult;
import com.samsung.android.vexfwk.param.VexFwkUnifiedDetectorSupportedModes;
import com.samsung.android.vexfwk.param.VexFwkWineInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;

@Metadata(d1 = {"\u0000Æ\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\bR\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 Z*\u0004\b\u0000\u0010\u00012\u00020\u0002:J\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZB+\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0001m[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001 \u0001¡\u0001¢\u0001£\u0001¨\u0006¤\u0001"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "T", "", "name", "", "converter", "Lcom/samsung/android/vexfwk/metadata/IVexFwkMetadataConverter;", "tag", "", "<init>", "(Ljava/lang/String;Lcom/samsung/android/vexfwk/metadata/IVexFwkMetadataConverter;Ljava/lang/Integer;)V", "getName", "()Ljava/lang/String;", "getConverter", "()Lcom/samsung/android/vexfwk/metadata/IVexFwkMetadataConverter;", "getTag", "()I", "DOCUMENT_RECT", "DOCUMENT_SCAN_RECT", "FRAME_TIMESTAMP_NS", "UPSAMPLE_FACTOR", "ROTATION_DEGREE", "WINE_INFO", "DOCUMENT_REFINE_ENHANCE", "DOCUMENT_REFINE_COLOR_FILTER_TYPE", "DOCUMENT_SCAN_STABILITY", "IMAGE_SIZE", "DOCUMENT_DEWARP_MODE", "AILASSO_TOUCH_COORDINATE", "VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS", "RESULT_CODE", "INPUT_PATH", "OUTPUT_PATH", "MEDIA_SCAN", "LANGUAGE_DIRECTION", "LANGUAGE_DIRECTIONS", "TRANSLATION_CAPABILITIES", "LANGUAGE_CODES", "STRING_RESOURCE_NAME", "TRANSLATION_COMMAND_TYPE", "REQUIRED_LANGUAGES", "OCR_RESULT", "STRING_RESOURCE", "TRANSLATION_RESULT_CODE", "TRANSLATION_ERROR_MESSAGE", "IMAGE_CLIPPER_INFO", "SEGMENT_CATEGORY_MAP", "IMAGE_TAGGER_RESULT", "PORTRAIT_BOKEH_EFFECT", "IMAGE_EFFECT_PARAMS", "FRAME_RATE", "FRC_STATUS", "STYLE_TRANSFER_TYPE", "PORTRAIT_BOKEH_EFFECT_V1", "IMAGE_ENHANCER_PARAMS", "OCR_HAS_TEXT", "OCR_FUNCTION", "PIPELINE_NAME", "OCR_ADDITIONAL_RESULT", "IMAGE_FORMAT", "OCR_RESULT_V2", "OCR_RESULT_VERSION", "DEPTHMAP_PARAMS", "FOCALLENGTH_PARAMS", "FOCALLENGTH_RESULT", "DOCUMENT_REFINE_PARAM", "DOCUMENT_REFINE_RESULT", "UNIFIED_DETECTOR_RESULT", "IMAGE_TAGGER_RESULT_V2", "SESSION_OPERATION_TYPE", "SESSION_SUPPORT_OPERATION_TYPES", "SESSION_DOCUMENT_DETECTION_TYPE", "OBJECT_REMOVER_MODE", "DOCUMENT_REFINER_MODE", "UNIFIED_DETECTOR_MODE", "PROPERTY_IS_AVAILABLE", "PROPERTY_DOC_REFINER_CAPABILITIES", "PROPERTY_IMAGE_EFFECT_CAPABILITIES", "PROPERTY_IMAGE_TRANSLATION_CAPABILITIES", "PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES", "PROPERTY_BOKEH_EFFECT_VERSION", "PROPERTY_IMAGE_OCR_CAPABILITIES", "PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES", "PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES", "PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES", "PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS", "PROPERTY_DEPTHMAP_CAPABILITIES", "PROPERTY_FOCAL_LENGTH_CAPABILITIES", "PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES", "PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES", "Companion", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$AILASSO_TOUCH_COORDINATE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DEPTHMAP_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_DEWARP_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_RECT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINER_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_COLOR_FILTER_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_ENHANCE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_PARAM;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_SCAN_RECT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_SCAN_STABILITY;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FOCALLENGTH_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FOCALLENGTH_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FRAME_RATE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FRAME_TIMESTAMP_NS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FRC_STATUS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_CLIPPER_INFO;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_EFFECT_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_ENHANCER_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_FORMAT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_SIZE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_TAGGER_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_TAGGER_RESULT_V2;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$INPUT_PATH;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$LANGUAGE_CODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$LANGUAGE_DIRECTION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$LANGUAGE_DIRECTIONS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$MEDIA_SCAN;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OBJECT_REMOVER_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_ADDITIONAL_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_FUNCTION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_HAS_TEXT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_RESULT_V2;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_RESULT_VERSION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OUTPUT_PATH;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PIPELINE_NAME;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PORTRAIT_BOKEH_EFFECT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PORTRAIT_BOKEH_EFFECT_V1;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_BOKEH_EFFECT_VERSION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DEPTHMAP_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DOC_REFINER_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_FOCAL_LENGTH_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_EFFECT_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_OCR_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_TRANSLATION_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IS_AVAILABLE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$REQUIRED_LANGUAGES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$RESULT_CODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$ROTATION_DEGREE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SEGMENT_CATEGORY_MAP;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SESSION_DOCUMENT_DETECTION_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SESSION_OPERATION_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SESSION_SUPPORT_OPERATION_TYPES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$STRING_RESOURCE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$STRING_RESOURCE_NAME;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$STYLE_TRANSFER_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_COMMAND_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_ERROR_MESSAGE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_RESULT_CODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$UNIFIED_DETECTOR_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$UNIFIED_DETECTOR_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$UPSAMPLE_FACTOR;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$WINE_INFO;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkMetadataKey<T> {
    public static final Companion Companion = new Companion((e) null);
    private static final int TAG_MESSAGE_START = 0;
    private static final int TAG_PROPERTY_START = 131072;
    private static final int TAG_SESSION_START = 65536;
    private final IVexFwkMetadataConverter<T> converter;
    private final String name;
    private final int tag;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$AILASSO_TOUCH_COORDINATE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Landroid/graphics/PointF;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AILASSO_TOUCH_COORDINATE extends VexFwkMetadataKey<PointF> {
        public static final AILASSO_TOUCH_COORDINATE INSTANCE = new AILASSO_TOUCH_COORDINATE();

        private AILASSO_TOUCH_COORDINATE() {
            super("vexfwk.message.touchCoordinate", VexFwkParamPointF.Companion, 14, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof AILASSO_TOUCH_COORDINATE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1486647658;
        }

        public String toString() {
            return "AILASSO_TOUCH_COORDINATE";
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0003\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$Companion;", "", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "<init>", "()V", "TAG_MESSAGE_START", "", "TAG_SESSION_START", "TAG_PROPERTY_START", "iterator", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion implements Iterable<VexFwkMetadataKey<?>>, a {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public Iterator<VexFwkMetadataKey<?>> iterator() {
            w wVar = v.f4727a;
            ArrayList arrayList = new ArrayList();
            for (C0748d m : wVar.b(VexFwkMetadataKey.class).e()) {
                VexFwkMetadataKey vexFwkMetadataKey = (VexFwkMetadataKey) m.m();
                if (vexFwkMetadataKey != null) {
                    arrayList.add(vexFwkMetadataKey);
                }
            }
            return arrayList.iterator();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DEPTHMAP_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDepthMapParams;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DEPTHMAP_PARAMS extends VexFwkMetadataKey<VexFwkDepthMapParams> {
        public static final DEPTHMAP_PARAMS INSTANCE = new DEPTHMAP_PARAMS();

        private DEPTHMAP_PARAMS() {
            super("vexfwk.message.depthmapParams", VexFwkDepthMapParams.Companion, 48, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DEPTHMAP_PARAMS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 550219035;
        }

        public String toString() {
            return "DEPTHMAP_PARAMS";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_DEWARP_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_DEWARP_MODE extends VexFwkMetadataKey<Integer> {
        public static final DOCUMENT_DEWARP_MODE INSTANCE = new DOCUMENT_DEWARP_MODE();

        private DOCUMENT_DEWARP_MODE() {
            super("vexfwk.message.documentDewarpMode", VexFwkParamInteger.Companion, 13, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_DEWARP_MODE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -129699450;
        }

        public String toString() {
            return "DOCUMENT_DEWARP_MODE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_RECT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentCorners;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_RECT extends VexFwkMetadataKey<VexFwkDocumentCorners> {
        public static final DOCUMENT_RECT INSTANCE = new DOCUMENT_RECT();

        private DOCUMENT_RECT() {
            super("vexfwk.message.documentRect", VexFwkDocumentCorners.Companion, 0, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_RECT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -2058948969;
        }

        public String toString() {
            return "DOCUMENT_RECT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINER_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerModes;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_REFINER_MODE extends VexFwkMetadataKey<VexFwkDocumentRefinerModes> {
        public static final DOCUMENT_REFINER_MODE INSTANCE = new DOCUMENT_REFINER_MODE();

        private DOCUMENT_REFINER_MODE() {
            super("vexfwk.session.documentRefinerMode", VexFwkDocumentRefinerModes.Companion, 65540, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_REFINER_MODE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -357046320;
        }

        public String toString() {
            return "DOCUMENT_REFINER_MODE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_COLOR_FILTER_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_REFINE_COLOR_FILTER_TYPE extends VexFwkMetadataKey<Integer> {
        public static final DOCUMENT_REFINE_COLOR_FILTER_TYPE INSTANCE = new DOCUMENT_REFINE_COLOR_FILTER_TYPE();

        private DOCUMENT_REFINE_COLOR_FILTER_TYPE() {
            super("vexfwk.message.documentRefineColorFilterType", VexFwkParamInteger.Companion, 10, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_REFINE_COLOR_FILTER_TYPE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1830234246;
        }

        public String toString() {
            return "DOCUMENT_REFINE_COLOR_FILTER_TYPE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_ENHANCE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_REFINE_ENHANCE extends VexFwkMetadataKey<Integer> {
        public static final DOCUMENT_REFINE_ENHANCE INSTANCE = new DOCUMENT_REFINE_ENHANCE();

        private DOCUMENT_REFINE_ENHANCE() {
            super("vexfwk.message.documentRefineEnhance", VexFwkParamInteger.Companion, 9, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_REFINE_ENHANCE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 79394735;
        }

        public String toString() {
            return "DOCUMENT_REFINE_ENHANCE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_PARAM;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerParams;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_REFINE_PARAM extends VexFwkMetadataKey<VexFwkDocumentRefinerParams> {
        public static final DOCUMENT_REFINE_PARAM INSTANCE = new DOCUMENT_REFINE_PARAM();

        private DOCUMENT_REFINE_PARAM() {
            super("vexfwk.message.documentRefineParam", VexFwkDocumentRefinerParams.Companion, 51, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_REFINE_PARAM)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 925134;
        }

        public String toString() {
            return "DOCUMENT_REFINE_PARAM";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_REFINE_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_REFINE_RESULT extends VexFwkMetadataKey<VexFwkDocumentRefinerResult> {
        public static final DOCUMENT_REFINE_RESULT INSTANCE = new DOCUMENT_REFINE_RESULT();

        private DOCUMENT_REFINE_RESULT() {
            super("vexfwk.message.documentRefineResult", VexFwkDocumentRefinerResult.Companion, 52, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_REFINE_RESULT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 89680604;
        }

        public String toString() {
            return "DOCUMENT_REFINE_RESULT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_SCAN_RECT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentScanRect;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_SCAN_RECT extends VexFwkMetadataKey<VexFwkDocumentScanRect> {
        public static final DOCUMENT_SCAN_RECT INSTANCE = new DOCUMENT_SCAN_RECT();

        private DOCUMENT_SCAN_RECT() {
            super("vexfwk.message.documentScanRect", VexFwkDocumentScanRect.Companion, 1, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_SCAN_RECT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1331899795;
        }

        public String toString() {
            return "DOCUMENT_SCAN_RECT";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$DOCUMENT_SCAN_STABILITY;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DOCUMENT_SCAN_STABILITY extends VexFwkMetadataKey<Integer> {
        public static final DOCUMENT_SCAN_STABILITY INSTANCE = new DOCUMENT_SCAN_STABILITY();

        private DOCUMENT_SCAN_STABILITY() {
            super("vexfwk.message.documentScanStability", VexFwkParamInteger.Companion, 11, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof DOCUMENT_SCAN_STABILITY)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1048309446;
        }

        public String toString() {
            return "DOCUMENT_SCAN_STABILITY";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FOCALLENGTH_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkFocalLengthParams;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FOCALLENGTH_PARAMS extends VexFwkMetadataKey<VexFwkFocalLengthParams> {
        public static final FOCALLENGTH_PARAMS INSTANCE = new FOCALLENGTH_PARAMS();

        private FOCALLENGTH_PARAMS() {
            super("vexfwk.message.focalLengthParams", VexFwkFocalLengthParams.Companion, 49, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof FOCALLENGTH_PARAMS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1643868117;
        }

        public String toString() {
            return "FOCALLENGTH_PARAMS";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FOCALLENGTH_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FOCALLENGTH_RESULT extends VexFwkMetadataKey<Float> {
        public static final FOCALLENGTH_RESULT INSTANCE = new FOCALLENGTH_RESULT();

        private FOCALLENGTH_RESULT() {
            super("vexfwk.message.focalLengthResult", VexFwkParamFloat.Companion, 50, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof FOCALLENGTH_RESULT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1582866750;
        }

        public String toString() {
            return "FOCALLENGTH_RESULT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FRAME_RATE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FRAME_RATE extends VexFwkMetadataKey<Float> {
        public static final FRAME_RATE INSTANCE = new FRAME_RATE();

        private FRAME_RATE() {
            super("vexfwk.message.frameRate", VexFwkParamFloat.Companion, 36, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof FRAME_RATE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1289353955;
        }

        public String toString() {
            return "FRAME_RATE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FRAME_TIMESTAMP_NS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FRAME_TIMESTAMP_NS extends VexFwkMetadataKey<Long> {
        public static final FRAME_TIMESTAMP_NS INSTANCE = new FRAME_TIMESTAMP_NS();

        private FRAME_TIMESTAMP_NS() {
            super("vexfwk.message.frameTimestampNs", VexFwkParamLong.Companion, 2, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof FRAME_TIMESTAMP_NS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 911215793;
        }

        public String toString() {
            return "FRAME_TIMESTAMP_NS";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$FRC_STATUS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkFrcStatus;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FRC_STATUS extends VexFwkMetadataKey<VexFwkFrcStatus> {
        public static final FRC_STATUS INSTANCE = new FRC_STATUS();

        private FRC_STATUS() {
            super("vexfwk.message.frcStatus", VexFwkFrcStatus.Companion, 37, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof FRC_STATUS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -334634357;
        }

        public String toString() {
            return "FRC_STATUS";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_CLIPPER_INFO;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkImageClipperInfo;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_CLIPPER_INFO extends VexFwkMetadataKey<VexFwkImageClipperInfo> {
        public static final IMAGE_CLIPPER_INFO INSTANCE = new IMAGE_CLIPPER_INFO();

        private IMAGE_CLIPPER_INFO() {
            super("vexfwk.message.imageClipperInfo", VexFwkImageClipperInfo.Companion, 31, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_CLIPPER_INFO)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1813551061;
        }

        public String toString() {
            return "IMAGE_CLIPPER_INFO";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_EFFECT_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkImageEffectParams;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_EFFECT_PARAMS extends VexFwkMetadataKey<VexFwkImageEffectParams> {
        public static final IMAGE_EFFECT_PARAMS INSTANCE = new IMAGE_EFFECT_PARAMS();

        private IMAGE_EFFECT_PARAMS() {
            super("vexfwk.property.imageEffectParams", VexFwkImageEffectParams.Companion, 35, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_EFFECT_PARAMS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1402868545;
        }

        public String toString() {
            return "IMAGE_EFFECT_PARAMS";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_ENHANCER_PARAMS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkImageEnhancerParams;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_ENHANCER_PARAMS extends VexFwkMetadataKey<VexFwkImageEnhancerParams> {
        public static final IMAGE_ENHANCER_PARAMS INSTANCE = new IMAGE_ENHANCER_PARAMS();

        private IMAGE_ENHANCER_PARAMS() {
            super("vexfwk.property.imageEnhancerParams", VexFwkImageEnhancerParams.Companion, 40, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_ENHANCER_PARAMS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 102566060;
        }

        public String toString() {
            return "IMAGE_ENHANCER_PARAMS";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_FORMAT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_FORMAT extends VexFwkMetadataKey<Integer> {
        public static final IMAGE_FORMAT INSTANCE = new IMAGE_FORMAT();

        private IMAGE_FORMAT() {
            super("vexfwk.message.imageFormat", VexFwkParamInteger.Companion, 45, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_FORMAT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 529794444;
        }

        public String toString() {
            return "IMAGE_FORMAT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_SIZE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Landroid/util/Size;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_SIZE extends VexFwkMetadataKey<Size> {
        public static final IMAGE_SIZE INSTANCE = new IMAGE_SIZE();

        private IMAGE_SIZE() {
            super("vexfwk.message.imageSize", VexFwkParamSize.Companion, 12, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_SIZE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 117134038;
        }

        public String toString() {
            return "IMAGE_SIZE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_TAGGER_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_TAGGER_RESULT extends VexFwkMetadataKey<VexFwkImageTaggerResult> {
        public static final IMAGE_TAGGER_RESULT INSTANCE = new IMAGE_TAGGER_RESULT();

        private IMAGE_TAGGER_RESULT() {
            super("vexfwk.message.imageTaggerResult", VexFwkImageTaggerResult.Companion, 33, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_TAGGER_RESULT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1501035341;
        }

        public String toString() {
            return "IMAGE_TAGGER_RESULT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$IMAGE_TAGGER_RESULT_V2;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerV2Result;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IMAGE_TAGGER_RESULT_V2 extends VexFwkMetadataKey<VexFwkImageTaggerV2Result> {
        public static final IMAGE_TAGGER_RESULT_V2 INSTANCE = new IMAGE_TAGGER_RESULT_V2();

        private IMAGE_TAGGER_RESULT_V2() {
            super("vexfwk.message.imageTaggerResultV2", VexFwkImageTaggerV2Result.Companion, 54, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof IMAGE_TAGGER_RESULT_V2)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1855548210;
        }

        public String toString() {
            return "IMAGE_TAGGER_RESULT_V2";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$INPUT_PATH;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class INPUT_PATH extends VexFwkMetadataKey<String> {
        public static final INPUT_PATH INSTANCE = new INPUT_PATH();

        private INPUT_PATH() {
            super("vexfwk.message.inputPath", VexFwkParamString.Companion, 17, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof INPUT_PATH)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1348479509;
        }

        public String toString() {
            return "INPUT_PATH";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$LANGUAGE_CODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageCodes;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LANGUAGE_CODES extends VexFwkMetadataKey<VexFwkLanguageCodes> {
        public static final LANGUAGE_CODES INSTANCE = new LANGUAGE_CODES();

        private LANGUAGE_CODES() {
            super("vexfwk.message.languageCodes", VexFwkLanguageCodes.Companion, 23, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof LANGUAGE_CODES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1220513232;
        }

        public String toString() {
            return "LANGUAGE_CODES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$LANGUAGE_DIRECTION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LANGUAGE_DIRECTION extends VexFwkMetadataKey<VexFwkLanguageDirection> {
        public static final LANGUAGE_DIRECTION INSTANCE = new LANGUAGE_DIRECTION();

        private LANGUAGE_DIRECTION() {
            super("vexfwk.message.languageDirection", VexFwkLanguageDirection.Companion, 20, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof LANGUAGE_DIRECTION)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1765166217;
        }

        public String toString() {
            return "LANGUAGE_DIRECTION";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$LANGUAGE_DIRECTIONS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirections;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class LANGUAGE_DIRECTIONS extends VexFwkMetadataKey<VexFwkLanguageDirections> {
        public static final LANGUAGE_DIRECTIONS INSTANCE = new LANGUAGE_DIRECTIONS();

        private LANGUAGE_DIRECTIONS() {
            super("vexfwk.message.languageDirections", VexFwkLanguageDirections.Companion, 21, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof LANGUAGE_DIRECTIONS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1114422038;
        }

        public String toString() {
            return "LANGUAGE_DIRECTIONS";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$MEDIA_SCAN;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MEDIA_SCAN extends VexFwkMetadataKey<Integer> {
        public static final MEDIA_SCAN INSTANCE = new MEDIA_SCAN();

        private MEDIA_SCAN() {
            super("vexfwk.message.mediaScan", VexFwkParamInteger.Companion, 19, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof MEDIA_SCAN)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -795205527;
        }

        public String toString() {
            return "MEDIA_SCAN";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OBJECT_REMOVER_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OBJECT_REMOVER_MODE extends VexFwkMetadataKey<VexFwkObjectRemoverMode> {
        public static final OBJECT_REMOVER_MODE INSTANCE = new OBJECT_REMOVER_MODE();

        private OBJECT_REMOVER_MODE() {
            super("vexfwk.session.objectRemoverMode", VexFwkObjectRemoverMode.Companion, 65539, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OBJECT_REMOVER_MODE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1033945917;
        }

        public String toString() {
            return "OBJECT_REMOVER_MODE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_ADDITIONAL_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrAdditionalMeta;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OCR_ADDITIONAL_RESULT extends VexFwkMetadataKey<VexFwkOcrAdditionalMeta> {
        public static final OCR_ADDITIONAL_RESULT INSTANCE = new OCR_ADDITIONAL_RESULT();

        private OCR_ADDITIONAL_RESULT() {
            super("vexfwk.message.ocrAdditionalMeta", VexFwkOcrAdditionalMeta.Companion, 44, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OCR_ADDITIONAL_RESULT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1311502147;
        }

        public String toString() {
            return "OCR_ADDITIONAL_RESULT";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_FUNCTION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OCR_FUNCTION extends VexFwkMetadataKey<Integer> {
        public static final OCR_FUNCTION INSTANCE = new OCR_FUNCTION();

        private OCR_FUNCTION() {
            super("vexfwk.message.ocrFunction", VexFwkParamInteger.Companion, 42, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OCR_FUNCTION)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -313334;
        }

        public String toString() {
            return "OCR_FUNCTION";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_HAS_TEXT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OCR_HAS_TEXT extends VexFwkMetadataKey<Boolean> {
        public static final OCR_HAS_TEXT INSTANCE = new OCR_HAS_TEXT();

        private OCR_HAS_TEXT() {
            super("vexfwk.message.ocrHasText", VexFwkParamBoolean.Companion, 41, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OCR_HAS_TEXT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1210863612;
        }

        public String toString() {
            return "OCR_HAS_TEXT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMeta;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OCR_RESULT extends VexFwkMetadataKey<VexFwkOcrResultMeta> {
        public static final OCR_RESULT INSTANCE = new OCR_RESULT();

        private OCR_RESULT() {
            super("vexfwk.message.ocrResult", VexFwkOcrResultMeta.Companion, 27, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OCR_RESULT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -430836529;
        }

        public String toString() {
            return "OCR_RESULT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_RESULT_V2;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkOcrResultMetaV2;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OCR_RESULT_V2 extends VexFwkMetadataKey<VexFwkOcrResultMetaV2> {
        public static final OCR_RESULT_V2 INSTANCE = new OCR_RESULT_V2();

        private OCR_RESULT_V2() {
            super("vexfwk.message.ocrResultV2", VexFwkOcrResultMetaV2.Companion, 46, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OCR_RESULT_V2)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1688660980;
        }

        public String toString() {
            return "OCR_RESULT_V2";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OCR_RESULT_VERSION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrResultVersion;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OCR_RESULT_VERSION extends VexFwkMetadataKey<VexFwkImageOcrResultVersion> {
        public static final OCR_RESULT_VERSION INSTANCE = new OCR_RESULT_VERSION();

        private OCR_RESULT_VERSION() {
            super("vexfwk.message.ocrResultVersion", VexFwkImageOcrResultVersion.Companion, 47, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OCR_RESULT_VERSION)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -289143480;
        }

        public String toString() {
            return "OCR_RESULT_VERSION";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$OUTPUT_PATH;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class OUTPUT_PATH extends VexFwkMetadataKey<String> {
        public static final OUTPUT_PATH INSTANCE = new OUTPUT_PATH();

        private OUTPUT_PATH() {
            super("vexfwk.message.outputPath", VexFwkParamString.Companion, 18, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof OUTPUT_PATH)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -597695374;
        }

        public String toString() {
            return "OUTPUT_PATH";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PIPELINE_NAME;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PIPELINE_NAME extends VexFwkMetadataKey<String> {
        public static final PIPELINE_NAME INSTANCE = new PIPELINE_NAME();

        private PIPELINE_NAME() {
            super("vexfwk.message.pipelineName", VexFwkParamString.Companion, 43, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PIPELINE_NAME)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1041427945;
        }

        public String toString() {
            return "PIPELINE_NAME";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PORTRAIT_BOKEH_EFFECT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PORTRAIT_BOKEH_EFFECT extends VexFwkMetadataKey<VexFwkBokehEffectParams> {
        public static final PORTRAIT_BOKEH_EFFECT INSTANCE = new PORTRAIT_BOKEH_EFFECT();

        private PORTRAIT_BOKEH_EFFECT() {
            super("vexfwk.message.portraitEffectParams", VexFwkBokehEffectParams.Companion, 34, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PORTRAIT_BOKEH_EFFECT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1194818558;
        }

        public String toString() {
            return "PORTRAIT_BOKEH_EFFECT";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PORTRAIT_BOKEH_EFFECT_V1;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParamsV1;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PORTRAIT_BOKEH_EFFECT_V1 extends VexFwkMetadataKey<VexFwkBokehEffectParamsV1> {
        public static final PORTRAIT_BOKEH_EFFECT_V1 INSTANCE = new PORTRAIT_BOKEH_EFFECT_V1();

        private PORTRAIT_BOKEH_EFFECT_V1() {
            super("vexfwk.message.bokehEffectParamsV1", VexFwkBokehEffectParamsV1.Companion, 39, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PORTRAIT_BOKEH_EFFECT_V1)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1849381880;
        }

        public String toString() {
            return "PORTRAIT_BOKEH_EFFECT_V1";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectFeature;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES extends VexFwkMetadataKey<List<? extends VexFwkBokehEffectFeature>> {
        public static final PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES INSTANCE = new PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES();

        private PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES() {
            super("vexfwk.property.bokehEffectSupportedFeatures", VexFwkBokehEffectSupportedFeatures.Companion, 131081, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 517540453;
        }

        public String toString() {
            return "PROPERTY_BOKEH_EFFECT_SUPPORTED_FEATURES";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_BOKEH_EFFECT_VERSION;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_BOKEH_EFFECT_VERSION extends VexFwkMetadataKey<String> {
        public static final PROPERTY_BOKEH_EFFECT_VERSION INSTANCE = new PROPERTY_BOKEH_EFFECT_VERSION();

        private PROPERTY_BOKEH_EFFECT_VERSION() {
            super("vexfwk.property.bokehEffectVersion", VexFwkParamString.Companion, 131077, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_BOKEH_EFFECT_VERSION)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1537403007;
        }

        public String toString() {
            return "PROPERTY_BOKEH_EFFECT_VERSION";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DEPTHMAP_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDepthMapCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_DEPTHMAP_CAPABILITIES extends VexFwkMetadataKey<VexFwkDepthMapCapabilities> {
        public static final PROPERTY_DEPTHMAP_CAPABILITIES INSTANCE = new PROPERTY_DEPTHMAP_CAPABILITIES();

        private PROPERTY_DEPTHMAP_CAPABILITIES() {
            super("vexfwk.property.depthmapCapabilities", VexFwkDepthMapCapabilities.Companion, 131083, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_DEPTHMAP_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1750029469;
        }

        public String toString() {
            return "PROPERTY_DEPTHMAP_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES extends VexFwkMetadataKey<List<? extends VexFwkDocumentDetectionType>> {
        public static final PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES INSTANCE = new PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES();

        private PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES() {
            super("vexfwk.property.documentDetectionSupportedTypes", VexFwkDocumentDetectionSupportedTypes.Companion, 131080, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1702851555;
        }

        public String toString() {
            return "PROPERTY_DOCUMENT_DETECTION_SUPPORTED_TYPES";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentRefinerModes;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES extends VexFwkMetadataKey<List<? extends VexFwkDocumentRefinerModes>> {
        public static final PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES INSTANCE = new PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES();

        private PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES() {
            super("vexfwk.property.documentRefinerSupportedModes", VexFwkDocumentRefinerSupportedModes.Companion, 131085, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1555838202;
        }

        public String toString() {
            return "PROPERTY_DOCUMENT_REFINER_SUPPORTED_MODES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_DOC_REFINER_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefinerCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_DOC_REFINER_CAPABILITIES extends VexFwkMetadataKey<VexFwkDocRefinerCapabilities> {
        public static final PROPERTY_DOC_REFINER_CAPABILITIES INSTANCE = new PROPERTY_DOC_REFINER_CAPABILITIES();

        private PROPERTY_DOC_REFINER_CAPABILITIES() {
            super("vexfwk.property.docRefinerCapabilities", VexFwkDocRefinerCapabilities.Companion, 131073, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_DOC_REFINER_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1720801104;
        }

        public String toString() {
            return "PROPERTY_DOC_REFINER_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_FOCAL_LENGTH_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkFocalLengthCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_FOCAL_LENGTH_CAPABILITIES extends VexFwkMetadataKey<VexFwkFocalLengthCapabilities> {
        public static final PROPERTY_FOCAL_LENGTH_CAPABILITIES INSTANCE = new PROPERTY_FOCAL_LENGTH_CAPABILITIES();

        private PROPERTY_FOCAL_LENGTH_CAPABILITIES() {
            super("vexfwk.property.focalLengthCapabilities", VexFwkFocalLengthCapabilities.Companion, 131084, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_FOCAL_LENGTH_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1230912292;
        }

        public String toString() {
            return "PROPERTY_FOCAL_LENGTH_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS extends VexFwkMetadataKey<int[]> {
        public static final PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS INSTANCE = new PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS();

        private PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS() {
            super("vexfwk.property.frcAvailableUpsampleFactors", VexFwkParamIntegerArray.Companion, 131082, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1428740927;
        }

        public String toString() {
            return "PROPERTY_FRC_AVAILABLE_UPSAMPLE_FACTORS";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_EFFECT_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/imageeffect/VexFwkImageEffectCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_IMAGE_EFFECT_CAPABILITIES extends VexFwkMetadataKey<VexFwkImageEffectCapabilities> {
        public static final PROPERTY_IMAGE_EFFECT_CAPABILITIES INSTANCE = new PROPERTY_IMAGE_EFFECT_CAPABILITIES();

        private PROPERTY_IMAGE_EFFECT_CAPABILITIES() {
            super("vexfwk.property.imageEffectCapabilities", VexFwkImageEffectCapabilities.Companion, 131074, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_IMAGE_EFFECT_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 17429511;
        }

        public String toString() {
            return "PROPERTY_IMAGE_EFFECT_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_OCR_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_IMAGE_OCR_CAPABILITIES extends VexFwkMetadataKey<VexFwkImageOcrCapabilities> {
        public static final PROPERTY_IMAGE_OCR_CAPABILITIES INSTANCE = new PROPERTY_IMAGE_OCR_CAPABILITIES();

        private PROPERTY_IMAGE_OCR_CAPABILITIES() {
            super("vexfwk.property.imageOcrCapabilities", VexFwkImageOcrCapabilities.Companion, 131078, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_IMAGE_OCR_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1031969836;
        }

        public String toString() {
            return "PROPERTY_IMAGE_OCR_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/imagestyletransfer/VexFwkImageStyleTransferCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES extends VexFwkMetadataKey<VexFwkImageStyleTransferCapabilities> {
        public static final PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES INSTANCE = new PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES();

        private PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES() {
            super("vexfwk.property.imageStyleTransferCapabilities", VexFwkImageStyleTransferCapabilities.Companion, 131076, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -607748929;
        }

        public String toString() {
            return "PROPERTY_IMAGE_STYLE_TRANSFER_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IMAGE_TRANSLATION_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkIImageTranslatorCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_IMAGE_TRANSLATION_CAPABILITIES extends VexFwkMetadataKey<VexFwkIImageTranslatorCapabilities> {
        public static final PROPERTY_IMAGE_TRANSLATION_CAPABILITIES INSTANCE = new PROPERTY_IMAGE_TRANSLATION_CAPABILITIES();

        private PROPERTY_IMAGE_TRANSLATION_CAPABILITIES() {
            super("vexfwk.property.imageTranslatorCapabilities", VexFwkIImageTranslatorCapabilities.Companion, 131075, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_IMAGE_TRANSLATION_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1385310241;
        }

        public String toString() {
            return "PROPERTY_IMAGE_TRANSLATION_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_IS_AVAILABLE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_IS_AVAILABLE extends VexFwkMetadataKey<Boolean> {
        public static final PROPERTY_IS_AVAILABLE INSTANCE = new PROPERTY_IS_AVAILABLE();

        private PROPERTY_IS_AVAILABLE() {
            super("vexfwk.property.isAvailable", VexFwkParamBoolean.Companion, Integer.valueOf(VexFwkMetadataKey.TAG_PROPERTY_START), (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_IS_AVAILABLE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -322052851;
        }

        public String toString() {
            return "PROPERTY_IS_AVAILABLE";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "Lcom/samsung/android/vexfwk/param/VexFwkObjectRemoverMode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES extends VexFwkMetadataKey<List<? extends VexFwkObjectRemoverMode>> {
        public static final PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES INSTANCE = new PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES();

        private PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES() {
            super("vexfwk.property.objectRemoverSupportedModes", VexFwkObjectRemoverSupportedModes.Companion, 131079, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1595048999;
        }

        public String toString() {
            return "PROPERTY_OBJECT_REMOVER_SUPPORTED_MODES";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES extends VexFwkMetadataKey<List<? extends VexFwkUnifiedDetectorMode>> {
        public static final PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES INSTANCE = new PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES();

        private PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES() {
            super("vexfwk.property.unifiedDetectorSupportedModes", VexFwkUnifiedDetectorSupportedModes.Companion, 131086, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1378580620;
        }

        public String toString() {
            return "PROPERTY_UNIFIED_DETECTOR_SUPPORTED_MODES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$REQUIRED_LANGUAGES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkRequiredLanguages;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class REQUIRED_LANGUAGES extends VexFwkMetadataKey<VexFwkRequiredLanguages> {
        public static final REQUIRED_LANGUAGES INSTANCE = new REQUIRED_LANGUAGES();

        private REQUIRED_LANGUAGES() {
            super("vexfwk.message.requiredLanguages", VexFwkRequiredLanguages.Companion, 26, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof REQUIRED_LANGUAGES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 98846892;
        }

        public String toString() {
            return "REQUIRED_LANGUAGES";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$RESULT_CODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkNodeResultCode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RESULT_CODE extends VexFwkMetadataKey<VexFwkNodeResultCode> {
        public static final RESULT_CODE INSTANCE = new RESULT_CODE();

        private RESULT_CODE() {
            super("vexfwk.message.resultCode", VexFwkNodeResultCode.Companion, 16, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof RESULT_CODE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -310734690;
        }

        public String toString() {
            return "RESULT_CODE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$ROTATION_DEGREE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ROTATION_DEGREE extends VexFwkMetadataKey<Integer> {
        public static final ROTATION_DEGREE INSTANCE = new ROTATION_DEGREE();

        private ROTATION_DEGREE() {
            super("vexfwk.message.rotationDegree", VexFwkParamInteger.Companion, 4, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof ROTATION_DEGREE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1721367292;
        }

        public String toString() {
            return "ROTATION_DEGREE";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\n\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÖ\u0001J\t\u0010\f\u001a\u00020\u0004HÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SEGMENT_CATEGORY_MAP;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SEGMENT_CATEGORY_MAP extends VexFwkMetadataKey<Map<Integer, ? extends String>> {
        public static final SEGMENT_CATEGORY_MAP INSTANCE = new SEGMENT_CATEGORY_MAP();

        private SEGMENT_CATEGORY_MAP() {
            super("vexfwk.message.segmentCategoryMap", VexFwkSegmentMap.Companion, 32, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof SEGMENT_CATEGORY_MAP)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -685743816;
        }

        public String toString() {
            return "SEGMENT_CATEGORY_MAP";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SESSION_DOCUMENT_DETECTION_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SESSION_DOCUMENT_DETECTION_TYPE extends VexFwkMetadataKey<VexFwkDocumentDetectionType> {
        public static final SESSION_DOCUMENT_DETECTION_TYPE INSTANCE = new SESSION_DOCUMENT_DETECTION_TYPE();

        private SESSION_DOCUMENT_DETECTION_TYPE() {
            super("vexfwk.session.documentDetectionType", VexFwkDocumentDetectionType.Companion, 65538, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof SESSION_DOCUMENT_DETECTION_TYPE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1252161538;
        }

        public String toString() {
            return "SESSION_DOCUMENT_DETECTION_TYPE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SESSION_OPERATION_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SESSION_OPERATION_TYPE extends VexFwkMetadataKey<Integer> {
        public static final SESSION_OPERATION_TYPE INSTANCE = new SESSION_OPERATION_TYPE();

        private SESSION_OPERATION_TYPE() {
            super("vexfwk.session.operationType", VexFwkParamInteger.Companion, Integer.valueOf(VexFwkMetadataKey.TAG_SESSION_START), (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof SESSION_OPERATION_TYPE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1537698028;
        }

        public String toString() {
            return "SESSION_OPERATION_TYPE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$SESSION_SUPPORT_OPERATION_TYPES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SESSION_SUPPORT_OPERATION_TYPES extends VexFwkMetadataKey<int[]> {
        public static final SESSION_SUPPORT_OPERATION_TYPES INSTANCE = new SESSION_SUPPORT_OPERATION_TYPES();

        private SESSION_SUPPORT_OPERATION_TYPES() {
            super("vexfwk.session.supportOperationTypes", VexFwkParamIntegerArray.Companion, 65537, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof SESSION_SUPPORT_OPERATION_TYPES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1165161047;
        }

        public String toString() {
            return "SESSION_SUPPORT_OPERATION_TYPES";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$STRING_RESOURCE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class STRING_RESOURCE extends VexFwkMetadataKey<String> {
        public static final STRING_RESOURCE INSTANCE = new STRING_RESOURCE();

        private STRING_RESOURCE() {
            super("vexfwk.message.stringResource", VexFwkParamString.Companion, 28, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof STRING_RESOURCE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1023936309;
        }

        public String toString() {
            return "STRING_RESOURCE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$STRING_RESOURCE_NAME;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class STRING_RESOURCE_NAME extends VexFwkMetadataKey<String> {
        public static final STRING_RESOURCE_NAME INSTANCE = new STRING_RESOURCE_NAME();

        private STRING_RESOURCE_NAME() {
            super("vexfwk.message.stringResourceName", VexFwkParamString.Companion, 24, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof STRING_RESOURCE_NAME)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 285866367;
        }

        public String toString() {
            return "STRING_RESOURCE_NAME";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$STYLE_TRANSFER_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class STYLE_TRANSFER_TYPE extends VexFwkMetadataKey<Integer> {
        public static final STYLE_TRANSFER_TYPE INSTANCE = new STYLE_TRANSFER_TYPE();

        private STYLE_TRANSFER_TYPE() {
            super("vexfwk.message.styleTransferType", VexFwkParamInteger.Companion, 38, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof STYLE_TRANSFER_TYPE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1284627887;
        }

        public String toString() {
            return "STYLE_TRANSFER_TYPE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_CAPABILITIES;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageCapabilities;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TRANSLATION_CAPABILITIES extends VexFwkMetadataKey<VexFwkLanguageCapabilities> {
        public static final TRANSLATION_CAPABILITIES INSTANCE = new TRANSLATION_CAPABILITIES();

        private TRANSLATION_CAPABILITIES() {
            super("vexfwk.message.translationCapabilities", VexFwkLanguageCapabilities.Companion, 22, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof TRANSLATION_CAPABILITIES)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -768683211;
        }

        public String toString() {
            return "TRANSLATION_CAPABILITIES";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_COMMAND_TYPE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TRANSLATION_COMMAND_TYPE extends VexFwkMetadataKey<Integer> {
        public static final TRANSLATION_COMMAND_TYPE INSTANCE = new TRANSLATION_COMMAND_TYPE();

        private TRANSLATION_COMMAND_TYPE() {
            super("vexfwk.message.translationCommandType", VexFwkParamInteger.Companion, 25, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof TRANSLATION_COMMAND_TYPE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1590234291;
        }

        public String toString() {
            return "TRANSLATION_COMMAND_TYPE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_ERROR_MESSAGE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TRANSLATION_ERROR_MESSAGE extends VexFwkMetadataKey<String> {
        public static final TRANSLATION_ERROR_MESSAGE INSTANCE = new TRANSLATION_ERROR_MESSAGE();

        private TRANSLATION_ERROR_MESSAGE() {
            super("vexfwk.message.errorMessage", VexFwkParamString.Companion, 30, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof TRANSLATION_ERROR_MESSAGE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1191888655;
        }

        public String toString() {
            return "TRANSLATION_ERROR_MESSAGE";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$TRANSLATION_RESULT_CODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TRANSLATION_RESULT_CODE extends VexFwkMetadataKey<Integer> {
        public static final TRANSLATION_RESULT_CODE INSTANCE = new TRANSLATION_RESULT_CODE();

        private TRANSLATION_RESULT_CODE() {
            super("vexfwk.message.translationResultCode", VexFwkParamInteger.Companion, 29, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof TRANSLATION_RESULT_CODE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -623385232;
        }

        public String toString() {
            return "TRANSLATION_RESULT_CODE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$UNIFIED_DETECTOR_MODE;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorMode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UNIFIED_DETECTOR_MODE extends VexFwkMetadataKey<VexFwkUnifiedDetectorMode> {
        public static final UNIFIED_DETECTOR_MODE INSTANCE = new UNIFIED_DETECTOR_MODE();

        private UNIFIED_DETECTOR_MODE() {
            super("vexfwk.session.unifiedDetectorMode", VexFwkUnifiedDetectorMode.Companion, 65541, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof UNIFIED_DETECTOR_MODE)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1441737598;
        }

        public String toString() {
            return "UNIFIED_DETECTOR_MODE";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$UNIFIED_DETECTOR_RESULT;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UNIFIED_DETECTOR_RESULT extends VexFwkMetadataKey<VexFwkUnifiedDetectorResult> {
        public static final UNIFIED_DETECTOR_RESULT INSTANCE = new UNIFIED_DETECTOR_RESULT();

        private UNIFIED_DETECTOR_RESULT() {
            super("vexfwk.message.unifiedDetectorResult", VexFwkUnifiedDetectorResult.Companion, 53, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof UNIFIED_DETECTOR_RESULT)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1630229704;
        }

        public String toString() {
            return "UNIFIED_DETECTOR_RESULT";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$UPSAMPLE_FACTOR;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UPSAMPLE_FACTOR extends VexFwkMetadataKey<Integer> {
        public static final UPSAMPLE_FACTOR INSTANCE = new UPSAMPLE_FACTOR();

        private UPSAMPLE_FACTOR() {
            super("vexfwk.message.upsampleFactor", VexFwkParamInteger.Companion, 3, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof UPSAMPLE_FACTOR)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1564484840;
        }

        public String toString() {
            return "UPSAMPLE_FACTOR";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS extends VexFwkMetadataKey<Long> {
        public static final VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS INSTANCE = new VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS();

        private VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS() {
            super("vexfwk.message.maskFrameTimestampMs", VexFwkParamLong.Companion, 15, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1297462264;
        }

        public String toString() {
            return "VIDEO_OBJECT_REMOVER_MASK_FRAME_TIMESTAMP_MS";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey$WINE_INFO;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataKey;", "Lcom/samsung/android/vexfwk/param/VexFwkWineInfo;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WINE_INFO extends VexFwkMetadataKey<VexFwkWineInfo> {
        public static final WINE_INFO INSTANCE = new WINE_INFO();

        private WINE_INFO() {
            super("vexfwk.message.wineInfo", VexFwkWineInfo.Companion, 5, (e) null);
        }

        public boolean equals(Object obj) {
            if (this != obj && !(obj instanceof WINE_INFO)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 568104627;
        }

        public String toString() {
            return "WINE_INFO";
        }
    }

    public /* synthetic */ VexFwkMetadataKey(String str, IVexFwkMetadataConverter iVexFwkMetadataConverter, Integer num, e eVar) {
        this(str, iVexFwkMetadataConverter, num);
    }

    public final IVexFwkMetadataConverter<T> getConverter() {
        return this.converter;
    }

    public final String getName() {
        return this.name;
    }

    public final int getTag() {
        return this.tag;
    }

    private VexFwkMetadataKey(String str, IVexFwkMetadataConverter<T> iVexFwkMetadataConverter, Integer num) {
        this.name = str;
        this.converter = iVexFwkMetadataConverter;
        int intValue = num != null ? num.intValue() : VexFwkMetadataNative.Companion.getTag(str);
        this.tag = intValue;
        if (intValue < 0) {
            throw new IllegalStateException(("Invalid tag(" + num + ") for " + str).toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VexFwkMetadataKey(String str, IVexFwkMetadataConverter iVexFwkMetadataConverter, Integer num, int i2, e eVar) {
        this(str, iVexFwkMetadataConverter, (i2 & 4) != 0 ? null : num, (e) null);
    }
}
