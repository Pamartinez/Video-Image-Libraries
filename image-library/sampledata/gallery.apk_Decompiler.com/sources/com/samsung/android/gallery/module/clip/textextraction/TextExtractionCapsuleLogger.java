package com.samsung.android.gallery.module.clip.textextraction;

import O8.C0576a;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionCapsuleLogger implements CapsuleHelper.CapsuleListener {
    private static final HashMap<CapsuleActionType, String> DETAILS_MAP = new HashMap<CapsuleActionType, String>() {
        {
            put(CapsuleActionType.ADD_TO_NOTE, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ADD_TO_NOTE.toString());
            put(CapsuleActionType.COPY_ALL, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_COPY_ALL.toString());
            put(CapsuleActionType.ENTITY_ADD_EVENT, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ENTITY_ADD_EVENT.toString());
            put(CapsuleActionType.ENTITY_CALL, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ENTITY_CALL.toString());
            put(CapsuleActionType.ENTITY_CONVERT_UNIT, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ENTITY_CONVERT.toString());
            put(CapsuleActionType.ENTITY_EMAIL, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ENTITY_EMAIL.toString());
            put(CapsuleActionType.ENTITY_MAP, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ENTITY_MAP.toString());
            put(CapsuleActionType.ENTITY_WEBSITE, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_ENTITY_WEBSITE.toString());
            put(CapsuleActionType.TRANSLATE, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_TRANSLATE.toString());
            put(CapsuleActionType.WALLET_BOARDING_PASS, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_WALLET_BOARDING_PASS.toString());
            put(CapsuleActionType.WALLET_COUPON, AnalyticsDetail.EVENT_DETAIL_TEXT_EXTRACTION_CAPSULE_TYPE_WALLET_COUPON.toString());
        }
    };
    private BiConsumer<String, String> mAnalyticsDetailLogCallback;

    public void onAdd(CapsuleActionType capsuleActionType) {
        String str = DETAILS_MAP.get(capsuleActionType);
        if (str != null) {
            postAnalyticsDetailLog(AnalyticsEventId.EVENT_DETAIL_VIEW_CAPSULE_APPEAR.toString(), str);
        }
    }

    public void onClick(CapsuleActionType capsuleActionType) {
        String str = DETAILS_MAP.get(capsuleActionType);
        if (str != null) {
            postAnalyticsDetailLog(AnalyticsEventId.EVENT_DETAIL_VIEW_CAPSULE_TOUCH.toString(), str);
        }
    }

    public void postAnalyticsDetailLog(String str, String str2) {
        try {
            Optional.ofNullable(this.mAnalyticsDetailLogCallback).ifPresent(new C0576a(str, str2, 0));
        } catch (Exception e) {
            Log.e((CharSequence) "TextExtractionCapsuleLogger", "failed to post log", (Throwable) e);
        }
    }

    public void setAnalyticsDetailLogCallback(BiConsumer<String, String> biConsumer) {
        this.mAnalyticsDetailLogCallback = biConsumer;
    }
}
