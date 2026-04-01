package com.samsung.android.gallery.module.search.root;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.type.ExpressionsType;
import com.samsung.android.gallery.support.type.SubCategoryType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VisualSearchLoggerHelper {
    private static final HashMap<String, String> CATEGORY_VIEW_MORE_DETAIL_MAP = new HashMap<String, String>() {
        {
            put("location://search/fileList/Category/MyTag", "1");
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                put("location://search/fileList/Category/PeopleAndPets", "2");
            } else {
                put("location://search/fileList/Category/People", "2");
            }
            put("location://search/fileList/Category/Location", "3");
            put("location://search/fileList/Category/ShotMode", BuddyContract.Email.Type.MOBILE);
            put("location://search/fileList/Category/Documents", "5");
            put("location://search/fileList/Category/Scene", "6");
            put("location://search/fileList/Category/ScreenShot", "7");
            put("location://story/albums", "8");
        }
    };
    private static final Map<String, String> DOCUMENTS_DETAIL_MAP = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER) {
        {
            Object obj = "14";
            Object obj2 = "13";
            Object obj3 = "11";
            Object obj4 = "doc_credit_cards";
            Object obj5 = "10";
            Object obj6 = "doc_passports";
            Object obj7 = "9";
            if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP_V2)) {
                put("doc_business_cards", "1");
                put("doc_ids", "2");
                put("doc_maps", "3");
                put("doc_legal_documents", BuddyContract.Email.Type.MOBILE);
                put("doc_presentations", "5");
                put("doc_receipts", "6");
                put("doc_notes", "7");
                put("doc_books", "8");
                put(obj6, obj7);
                put(obj4, obj5);
                put("doc_barcodes", obj3);
                put("doc_qr_codes", obj2);
                put("doc_other_documents", obj);
                return;
            }
            Object obj8 = obj;
            Object obj9 = obj2;
            Object obj10 = obj3;
            Object obj11 = obj4;
            Object obj12 = obj5;
            Object obj13 = obj6;
            Object obj14 = obj7;
            if (Features.isEnabled(Features.SUPPORT_INFORMATIVE_DOCUMENT_SUBGROUP)) {
                put("doc_business_cards", "1");
                put("doc_ids", "2");
                put("doc_maps", "3");
                put("doc_schedules", BuddyContract.Email.Type.MOBILE);
                put("doc_presentations", "5");
                put("doc_receipts", "6");
                put("doc_notes", "7");
                put("doc_books", "8");
                put(obj13, obj14);
                put(obj11, obj12);
                put("doc_barcodes", obj10);
                put("doc_qr_codes", obj9);
                put("doc_other_documents", obj8);
                return;
            }
            Object obj15 = obj8;
            Object obj16 = obj9;
            Object obj17 = obj10;
            Object obj18 = obj12;
            Object obj19 = obj14;
            if (Features.isEnabled(Features.SUPPORT_VISUAL_SEARCH_NEW_DOCUMENTS)) {
                put("business_cards", "1");
                put("ids", "2");
                put("maps", "3");
                put("schedules", BuddyContract.Email.Type.MOBILE);
                put("presentations", "5");
                put("receipts", "6");
                put(Privacy.KEY_NOTES, "7");
                put("books", "8");
                put("passports", obj19);
                put("credit_cards", obj18);
                put("barcodes", obj17);
                put("qr_codes", obj16);
                put("other_documents", obj15);
                return;
            }
            put("Docs", "1");
            put(SubCategoryType.BUSINESS_CARD, "2");
            put("Barcode", "3");
            put("QR", BuddyContract.Email.Type.MOBILE);
            put("Whiteboard", "5");
            put(SubCategoryType.WEBSITE, "6");
            put(SubCategoryType.CARTOON, "7");
            put(SubCategoryType.MAP, "8");
            put(SubCategoryType.RECEIPT, obj19);
            put(SubCategoryType.SIGNS, obj18);
            put(SubCategoryType.NEWSPAPER, obj17);
        }
    };
    private static final HashMap<String, String> EXPRESSIONS_DETAIL_MAP = new HashMap<String, String>() {
        {
            put(ExpressionsType.EXPRESSION_HAPPY.getSubCategory(), "1");
            put(ExpressionsType.EXPRESSION_NEUTRAL.getSubCategory(), "2");
            put(ExpressionsType.EXPRESSION_DISLIKE.getSubCategory(), "3");
            put(ExpressionsType.EXPRESSION_SURPRISE.getSubCategory(), BuddyContract.Email.Type.MOBILE);
        }
    };
    private static final HashMap<String, String> SHOT_MODE_DETAIL_MAP = new HashMap<String, String>() {
        {
            put("video", "1");
            put("burst_shot", "2");
            put("panorama", BuddyContract.Email.Type.MOBILE);
            put("motion_photo", "5");
            put("slow_motion", "6");
            put("super_slow_mo", "7");
            put("hyperlapse", "8");
            put("sticker", "9");
            put("live_focus", "10");
            put("selfie", "11");
            put("GIF", "12");
            put("360_photo", "13");
            put("360_video", "14");
            put("Single Taken", "15");
            put("directors_view", "16");
            put("pro", "17");
            put("pro_video", "18");
            put("document_scan", "19");
            put("raw", "20");
            put("portrait_video", "21");
            put("Dual_Recording_Info", "23");
            put("log_video", "24");
            put("3d_capture", "25");
            put("apv_video", "26");
        }
    };

    public static String getCategoryViewMoreDetail(String str) {
        return CATEGORY_VIEW_MORE_DETAIL_MAP.getOrDefault(str, (Object) null);
    }

    public static String getDocumentsDetail(String str) {
        return DOCUMENTS_DETAIL_MAP.getOrDefault(str, "");
    }

    public static String getExpressionsDetail(String str) {
        return EXPRESSIONS_DETAIL_MAP.getOrDefault(str, "");
    }

    public static String getShotModeDetail(String str) {
        return SHOT_MODE_DETAIL_MAP.getOrDefault(str, "");
    }

    private static void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId) {
        AnalyticsLogger.getInstance().postLog(str, analyticsEventId.toString());
    }

    public static void postAnalyticsOnClickCategoryItem(String str, String str2, String str3, boolean z) {
        String str4;
        if (str2 == null) {
            Log.w("VisualSearchLoggerHelper", "postAnalyticsOnClickCategoryItem failed!!");
            return;
        }
        char c5 = 65535;
        switch (str2.hashCode()) {
            case -1907941713:
                if (str2.equals("People")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1790835355:
                if (str2.equals("Things")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1591322833:
                if (str2.equals("Activity")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1347456360:
                if (str2.equals("Documents")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1179428371:
                if (str2.equals("My tags")) {
                    c5 = 4;
                    break;
                }
                break;
            case -715666509:
                if (str2.equals("Scenery")) {
                    c5 = 5;
                    break;
                }
                break;
            case -638961672:
                if (str2.equals("Things Scenery")) {
                    c5 = 6;
                    break;
                }
                break;
            case -626311778:
                if (str2.equals("Camera mode")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1576064422:
                if (str2.equals("ScreenShot")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1843423419:
                if (str2.equals("Expressions")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1965687765:
                if (str2.equals("Location")) {
                    c5 = 10;
                    break;
                }
                break;
            case 2090249588:
                if (str2.equals("My query")) {
                    c5 = 11;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_SEARCH_VIEW_PEOPLE_TYPE;
                if (z) {
                    str4 = "1";
                } else {
                    str4 = "0";
                }
                postAnalyticsLog(str, analyticsEventId, str4);
                return;
            case 1:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_THINGS_TYPE);
                return;
            case 2:
                if (TextUtils.equals(str3, "Recently edited")) {
                    postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_ACTIVITY_EDITED_TYPE);
                    return;
                } else if (TextUtils.equals(str3, "Generated")) {
                    postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_ACTIVITY_AI_EDITED_TYPE);
                    return;
                } else {
                    return;
                }
            case 3:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_DOCUMENT_TYPE, getDocumentsDetail(str3));
                return;
            case 4:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_CATEGORY_ITEM);
                return;
            case 5:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_SCENERY_TYPE);
                return;
            case 6:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_THINGS_TYPE);
                return;
            case 7:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_CONTENTS_TYPE, getShotModeDetail(str3));
                return;
            case 8:
                AnalyticsEventId eventId = ScreenShotFilterType.getEventId(str3);
                if (eventId != null) {
                    postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_SCREENSHOT_TYPE, eventId.toString());
                    return;
                }
                return;
            case 9:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_EXPRESSIONS_TYPE, getExpressionsDetail(str3));
                return;
            case 10:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_LOCATION_TYPE);
                return;
            case 11:
                postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_SHORT_CUT_TYPE);
                return;
            default:
                return;
        }
    }

    public static void postAnalyticsOnClickVieMoreArrow(String str, String str2) {
        postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_MORE, getCategoryViewMoreDetail(str2));
    }

    public static void postAnalyticsPeopleCount(String str, int i2, int i7) {
        postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_PEOPLE_TOTAL_COUNT, String.valueOf(i2));
        postAnalyticsLog(str, AnalyticsEventId.EVENT_SEARCH_VIEW_PEOPLE_NAMED_COUNT, String.valueOf(i7));
    }

    private static void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, String str2) {
        AnalyticsLogger.getInstance().postLog(str, analyticsEventId.toString(), str2);
    }
}
