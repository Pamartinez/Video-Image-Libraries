package com.samsung.android.gallery.module.search.root;

import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.type.ColorDetectedType;
import com.samsung.android.gallery.support.type.SubCategoryType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class IntelligentSearchIndexFieldIconUtil {
    public static final ArrayList<String> DOCUMENTS_GROUP = new ArrayList<String>() {
        {
            add("documents");
            if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2)) {
                addAll(SubCategoryType.INFORMATIVE_DOCUMENTS_SUB_GROUP_V2);
                add("doc_documents");
                add("doc_ids");
                add("doc_passports");
                add("doc_business_cards");
                add("doc_credit_cards");
                add("doc_legal_documents");
                add("doc_notes");
                add("doc_books");
                add("doc_presentations");
                add("doc_maps");
                add("doc_schedules");
                add("doc_receipts");
                add("doc_barcodes");
                add("doc_qr_codes");
            } else if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP)) {
                addAll(SubCategoryType.INFORMATIVE_DOCUMENTS_SUB_GROUP);
                add("doc_ids");
                add("doc_passports");
                add("doc_business_cards");
                add("doc_credit_cards");
                add("doc_notes");
                add("doc_books");
                add("doc_presentations");
                add("doc_maps");
                add("doc_schedules");
                add("doc_receipts");
                add("doc_barcodes");
                add("doc_qr_codes");
            } else if (Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
                addAll(SubCategoryType.DOCUMENTS_SUB_GROUP_ONEUI_61);
                add("ids");
                add("passports");
                add("business_cards");
                add("credit_cards");
                add(Privacy.KEY_NOTES);
                add("books");
                add("presentations");
                add("maps");
                add("schedules");
                add("receipts");
                add("barcodes");
                add("qr_codes");
            }
        }
    };
    private static final LinkedHashMap<String, Integer> FILTER_TYPE_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            int i2;
            put("bucket_display_name", Integer.valueOf(R$drawable.gallery_ic_search_filtered_album));
            put("usertag", Integer.valueOf(R$drawable.gallery_ic_search_filtered_tag));
            int i7 = R$drawable.gallery_ic_search_filtered_person;
            put("persontag", Integer.valueOf(i7));
            put("expressionstag", Integer.valueOf(R$drawable.gallery_ic_search_filtered_expression));
            put("storytag", Integer.valueOf(R$drawable.gallery_ic_search_filtered_story));
            int i8 = R$drawable.gallery_ic_search_filtered_shot_type;
            put("sef_file_type", Integer.valueOf(i8));
            put("mime_type", Integer.valueOf(i8));
            put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(i8));
            put("recording_mode", Integer.valueOf(i8));
            int i10 = R$drawable.gallery_ic_search_filtered_location;
            put("poitag", Integer.valueOf(i10));
            put("locationtag", Integer.valueOf(i10));
            put("addr", Integer.valueOf(i10));
            put("facet_location", Integer.valueOf(i10));
            put("sub_location", Integer.valueOf(i10));
            int i11 = R$drawable.gallery_ic_search_filtered_date;
            put(IParameterKey.DATE_TAKEN, Integer.valueOf(i11));
            put("date_suggestion", Integer.valueOf(i11));
            put("ocrtext", Integer.valueOf(R$drawable.gallery_ic_search_filtered_text));
            put("color_detected", Integer.valueOf(R$drawable.gallery_ic_search_filtered_color));
            put("action_detected", Integer.valueOf(R$drawable.gallery_ic_search_filtered_action));
            put("only_them", Integer.valueOf(R$drawable.gallery_ic_search_filtered_onlythem));
            int i12 = R$drawable.gallery_ic_search_filtered_special_day;
            put("special_day", Integer.valueOf(i12));
            put("date_nlu", Integer.valueOf(i12));
            int i13 = R$drawable.gallery_ic_search_filtered_pets;
            put("pet_tag", Integer.valueOf(i13));
            put("relationship", Integer.valueOf(R$drawable.gallery_ic_search_filtered_relationship));
            put("recommended_id", Integer.valueOf(i7));
            put("pet_recommended_id", Integer.valueOf(i13));
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                i2 = R$drawable.ic_people_and_pet_face_smiling_cat;
            } else {
                i2 = R$drawable.ic_emoticon_emoji_face_smiling;
            }
            put("faces", Integer.valueOf(i2));
            put("facet_event", Integer.valueOf(R$drawable.gallery_ic_search_suggested_special_day));
        }
    };
    private static final LinkedHashMap<String, Integer> LINE_TYPE_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            put("bucket_display_name", Integer.valueOf(R$drawable.gallery_ic_search_suggested_album));
            put("usertag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_tag));
            put("persontag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_person));
            put("expressionstag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_expression));
            put("storytag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_story));
            int i2 = R$drawable.gallery_ic_search_suggested_shot_type;
            put("sef_file_type", Integer.valueOf(i2));
            put("mime_type", Integer.valueOf(i2));
            put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(i2));
            put("recording_mode", Integer.valueOf(i2));
            int i7 = R$drawable.gallery_ic_search_suggested_location;
            put("poitag", Integer.valueOf(i7));
            put("locationtag", Integer.valueOf(i7));
            put("addr", Integer.valueOf(i7));
            put("facet_location", Integer.valueOf(i7));
            put("sub_location", Integer.valueOf(i7));
            int i8 = R$drawable.gallery_ic_search_suggested_date;
            put(IParameterKey.DATE_TAKEN, Integer.valueOf(i8));
            put("date_suggestion", Integer.valueOf(i8));
            put("ocrtext", Integer.valueOf(R$drawable.gallery_ic_search_suggested_text));
            put("color_detected", Integer.valueOf(R$drawable.gallery_ic_search_suggested_color));
            put("action_detected", Integer.valueOf(R$drawable.gallery_ic_search_suggested_action));
            int i10 = R$drawable.gallery_ic_search_suggested_special_day;
            put("special_day", Integer.valueOf(i10));
            put("date_nlu", Integer.valueOf(i10));
            put("pet_tag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_pets));
            put("relationship", Integer.valueOf(R$drawable.gallery_ic_search_suggested_relationship));
        }
    };
    private static final LinkedHashMap<String, Integer> SCENE_TAG_FILTER_TYPE_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            put("documents", Integer.valueOf(R$drawable.gallery_ic_search_filtered_documents));
            put("scenery", Integer.valueOf(R$drawable.gallery_ic_search_filtered_scenes));
        }
    };
    private static final LinkedHashMap<String, Integer> SCENE_TAG_LINE_TYPE_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            put("documents", Integer.valueOf(R$drawable.gallery_ic_search_suggested_documents));
            put("scenery", Integer.valueOf(R$drawable.gallery_ic_search_suggested_scenes));
        }
    };
    private static final LinkedHashMap<String, Integer> SCENE_TAG_SEMANTIC_SEARCH_TYPE_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            int i2 = R$drawable.gallery_ic_search_suggested_keyword;
            put("documents", Integer.valueOf(i2));
            put("scenery", Integer.valueOf(i2));
        }
    };
    private static final LinkedHashMap<String, Integer> SEMANTIC_SEARCH_ICON_TYPE = new LinkedHashMap<String, Integer>() {
        {
            put("bucket_display_name", Integer.valueOf(R$drawable.gallery_ic_search_suggested_album));
            put("usertag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_tag));
            put("persontag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_person));
            int i2 = R$drawable.gallery_ic_search_suggested_keyword;
            put("expressionstag", Integer.valueOf(i2));
            put("storytag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_story));
            int i7 = R$drawable.gallery_ic_search_suggested_shot_type;
            put("sef_file_type", Integer.valueOf(i7));
            put("mime_type", Integer.valueOf(i7));
            put(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, Integer.valueOf(i7));
            put("recording_mode", Integer.valueOf(i7));
            int i8 = R$drawable.gallery_ic_search_suggested_location;
            put("poitag", Integer.valueOf(i8));
            put("locationtag", Integer.valueOf(i8));
            put("addr", Integer.valueOf(i8));
            put("facet_location", Integer.valueOf(i8));
            put("sub_location", Integer.valueOf(i8));
            int i10 = R$drawable.gallery_ic_search_suggested_date;
            put(IParameterKey.DATE_TAKEN, Integer.valueOf(i10));
            put("date_suggestion", Integer.valueOf(i10));
            put("ocrtext", Integer.valueOf(R$drawable.gallery_ic_search_suggested_text));
            put("color_detected", Integer.valueOf(i2));
            put("action_detected", Integer.valueOf(i2));
            put("special_day", Integer.valueOf(i2));
            put("date_nlu", Integer.valueOf(i2));
            put("pet_tag", Integer.valueOf(R$drawable.gallery_ic_search_suggested_pets));
        }
    };

    public static Integer findColorTintResource(String str, String str2) {
        if ("color_detected".equals(str)) {
            return ColorDetectedType.getColorResId(str2);
        }
        return null;
    }

    private static Integer findDrawableFromIconMap(String str, IntelligentSearchIndexFieldIcon.Type type) {
        if (type.isLineType()) {
            return LINE_TYPE_ICON_MAP.getOrDefault(str, (Object) null);
        }
        if (type.isSemanticSearchType()) {
            return SEMANTIC_SEARCH_ICON_TYPE.getOrDefault(str, (Object) null);
        }
        return FILTER_TYPE_ICON_MAP.getOrDefault(str, (Object) null);
    }

    private static Integer findDrawableFromSceneTagIconMap(String str, IntelligentSearchIndexFieldIcon.Type type) {
        int i2;
        int i7;
        if (str != null) {
            str = str.toLowerCase();
        }
        if (type.isLineType()) {
            if (DOCUMENTS_GROUP.contains(str)) {
                i7 = R$drawable.gallery_ic_search_suggested_documents;
            } else {
                i7 = SCENE_TAG_LINE_TYPE_ICON_MAP.getOrDefault(str, Integer.valueOf(R$drawable.gallery_ic_search_suggested_things)).intValue();
            }
            return Integer.valueOf(i7);
        } else if (type.isSemanticSearchType()) {
            return SCENE_TAG_SEMANTIC_SEARCH_TYPE_ICON_MAP.getOrDefault(str, Integer.valueOf(R$drawable.gallery_ic_search_suggested_keyword));
        } else {
            if (DOCUMENTS_GROUP.contains(str)) {
                i2 = R$drawable.gallery_ic_search_filtered_documents;
            } else {
                i2 = SCENE_TAG_FILTER_TYPE_ICON_MAP.getOrDefault(str, Integer.valueOf(R$drawable.gallery_ic_search_filtered_things)).intValue();
            }
            return Integer.valueOf(i2);
        }
    }

    public static Integer findDrawableResource(String str, String str2, IntelligentSearchIndexFieldIcon.Type type) {
        if ("scenetag".equals(str)) {
            return findDrawableFromSceneTagIconMap(str2, type);
        }
        return findDrawableFromIconMap(str, type);
    }
}
