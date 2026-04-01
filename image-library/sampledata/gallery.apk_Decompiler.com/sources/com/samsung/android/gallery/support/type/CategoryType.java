package com.samsung.android.gallery.support.type;

import com.samsung.android.gallery.support.R$drawable;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CategoryType {
    private static final LinkedHashMap<String, Integer> ACTIVITY_NAME;
    public static final LinkedHashMap<String, Integer> CATEGORY_SHOT_MODE_STRING_MAP = new LinkedHashMap<String, Integer>() {
        {
            putAll(CategoryType.IMAGE_SHOT_MODE_NAME);
            putAll(CategoryType.VIDEO_SHOT_MODE_NAME);
            int i2 = R$string.capture_3d;
            put("3d_capture", Integer.valueOf(i2));
            put("3d_capture_image", Integer.valueOf(i2));
            put("Single Taken", Integer.valueOf(R$string.single_take));
        }
    };
    private static final LinkedHashMap<String, Integer> EXPRESSIONS_NAME;
    /* access modifiers changed from: private */
    public static final LinkedHashMap<String, Integer> IMAGE_SHOT_MODE_NAME;
    private static final LinkedHashMap<String, Integer> SUGGESTION_KEYWORDS_NAME;
    public static final ArrayList<String> TOP_LEVEL_SCENE_TAG = new ArrayList<String>() {
        {
            add("Accessories");
            add("Animals");
            add("Bags");
            add("Bathroom_accessories");
            add("Bathroom_fixtures");
            add("Beauty_products");
            add("Birds");
            add("Board_games");
            add(SubCategoryType.BUILDING);
            add("Car_accessories");
            add("Cars");
            add("Cats");
            add("Desserts");
            add("Dogs");
            add("Drinks");
            add("Electronics");
            add("Fish");
            add(SubCategoryType.FLOWER);
            add("Food");
            add("Fruits");
            add("Full_body_outfits");
            add("Furniture");
            add("Glasses");
            add("Hats");
            add("Holidays");
            add("Home_appliances");
            add("Kitchen_utensils");
            add("Musical_instruments");
            add("Office_supplies");
            add("Packages");
            add("Pants_and_skirts");
            add("People");
            add("Plants");
            add("Things Scenery");
            add("Sculptures");
            add("Shirts_and_tops");
            add("Shoes");
            add(SubCategoryType.SPORT);
            add("Tools");
            add("Toys");
            add("Vegetables");
            add("Vehicles");
            add(SubCategoryType.WATCH);
        }
    };
    public static final LinkedHashMap<String, Integer> VIDEO_SHOT_MODE_NAME;
    public static final String[] VISUAL_SEARCH_ALL_OTHER_CATEGORY;
    public static final String[] VISUAL_SEARCH_DOCUMENT_CATEGORY;
    public static final LinkedHashMap<String, Integer> VISUAL_SEARCH_SUB_CATEGORY_ACTIVITY_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            put("Favorites", Integer.valueOf(R$drawable.gallery_ic_menu_favorite));
            put("Generated", Integer.valueOf(R$drawable.gallery_ic_detail_intelligence));
            put("Recently edited", Integer.valueOf(R$drawable.gallery_ic_detail_edit));
            put("Duplicates", Integer.valueOf(R$drawable.ic_suggestions_clean_gallery));
            put("Trash", Integer.valueOf(R$drawable.gallery_ic_drawer_trash));
            put("PrivateAlbum", Integer.valueOf(R$drawable.gallery_ic_menu_locked));
            put("OldDocuments", Integer.valueOf(R$drawable.gallery_ic_search_document_scan));
        }
    };
    public static final LinkedHashMap<String, Integer> VISUAL_SEARCH_SUB_CATEGORY_SHOT_MODE_ICON_MAP = new LinkedHashMap<String, Integer>() {
        {
            put("panorama", Integer.valueOf(R$drawable.gallery_ic_search_panorama));
            put("selfie", Integer.valueOf(R$drawable.gallery_ic_search_selfie));
            put("GIF", Integer.valueOf(R$drawable.gallery_ic_search_gif));
            put("virtual_shot", Integer.valueOf(R$drawable.gallery_ic_search_virtual_shot));
            put("360_photo", Integer.valueOf(R$drawable.gallery_ic_search_360photo));
            put("motion_photo", Integer.valueOf(R$drawable.gallery_ic_search_motion_photo));
            put("Dual capture", Integer.valueOf(R$drawable.gallery_ic_search_dual_camera));
            put("live_focus", Integer.valueOf(R$drawable.gallery_ic_search_live_focus));
            put("selective_focus", Integer.valueOf(R$drawable.gallery_ic_search_selective_focus));
            put("sticker", Integer.valueOf(R$drawable.gallery_ic_search_sticker));
            put("burst_shot", Integer.valueOf(R$drawable.gallery_ic_search_burst_shot));
            put("slow_motion", Integer.valueOf(R$drawable.gallery_ic_search_slow_motion));
            put("hyperlapse", Integer.valueOf(R$drawable.gallery_ic_search_hyperlaps));
            put("fast_motion", Integer.valueOf(R$drawable.gallery_ic_search_fast_motion));
            put("super_slow_mo", Integer.valueOf(R$drawable.gallery_ic_search_super_slow_motion));
            put("360_video", Integer.valueOf(R$drawable.gallery_ic_search_360video));
            put("video", Integer.valueOf(R$drawable.gallery_ic_search_video));
            put("Single Taken", Integer.valueOf(R$drawable.gallery_ic_search_single_take));
            put("directors_view", Integer.valueOf(R$drawable.gallery_ic_search_directors_view));
            put("pro", Integer.valueOf(R$drawable.gallery_ic_search_pro));
            put("pro_video", Integer.valueOf(R$drawable.gallery_ic_search_provideo));
            put("portrait_video", Integer.valueOf(R$drawable.gallery_ic_search_portraitvideo));
            put("document_scan", Integer.valueOf(R$drawable.gallery_ic_search_document_scan));
            put("raw", Integer.valueOf(R$drawable.gallery_ic_search_raw_light));
            if (Features.isEnabled(Features.SUPPORT_DUAL_REC)) {
                put("Dual_Recording_Info", Integer.valueOf(R$drawable.gallery_ic_search_dualrec));
            }
            put("log_video", Integer.valueOf(R$drawable.gallery_ic_search_log_video));
            put("3d_capture", Integer.valueOf(R$drawable.gallery_ic_search_3dcapture_vst));
            put("apv_video", Integer.valueOf(R$drawable.gallery_ic_search_apv_video));
        }
    };
    public static final LinkedHashMap<String, Integer> VISUAL_SEARCH_SUB_CATEGORY_STRING_MAP;

    static {
        String str = SubCategoryType.BUSINESS_CARD;
        String str2 = SubCategoryType.WEBSITE;
        String str3 = SubCategoryType.CARTOON;
        String str4 = SubCategoryType.MAP;
        String str5 = SubCategoryType.RECEIPT;
        String str6 = SubCategoryType.SIGNS;
        String str7 = SubCategoryType.NEWSPAPER;
        VISUAL_SEARCH_DOCUMENT_CATEGORY = new String[]{str, "Crosswords", "Logos", "Scoreboards", "Screenshots", "Writing_boards", "Barcode", "QR", str2, str3, str4, str5, str6, str7, "Docs", "Whiteboard"};
        String str8 = str7;
        String str9 = str6;
        String str10 = str5;
        String str11 = str4;
        VISUAL_SEARCH_ALL_OTHER_CATEGORY = new String[]{"People", str, "Crosswords", "Logos", "Scoreboards", "Screenshots", "Writing_boards", "Barcode", "QR", str2, str3, str11, str10, str9, str8, "Docs", "Whiteboard", "Documents"};
        AnonymousClass1 r0 = new LinkedHashMap<String, Integer>() {
            {
                put("Documents", Integer.valueOf(R$string.documents));
                put("People", Integer.valueOf(R$string.people));
                int i2 = R$string.scenery;
                put("Scenery", Integer.valueOf(i2));
                put("Things Scenery", Integer.valueOf(i2));
                put("Fashion", Integer.valueOf(R$string.fashion));
                put("Food", Integer.valueOf(R$string.food));
                put(SubCategoryType.FLOWER, Integer.valueOf(R$string.flowers));
                put("Pets", Integer.valueOf(R$string.pets));
                put("Vehicles", Integer.valueOf(R$string.vehicles));
                put("Airplanes", Integer.valueOf(R$string.airplanes));
                put("Animals", Integer.valueOf(R$string.animals));
                put("Anniversaries", Integer.valueOf(R$string.anniversaries));
                put("Bags", Integer.valueOf(R$string.bags));
                put("Barcode", Integer.valueOf(R$string.barcode));
                put("Bikes", Integer.valueOf(R$string.bikes));
                put("Boats", Integer.valueOf(R$string.boats));
                put(SubCategoryType.BUILDING, Integer.valueOf(R$string.buildings));
                put(SubCategoryType.BUSINESS_CARD, Integer.valueOf(R$string.businesscard));
                put("Cars", Integer.valueOf(R$string.cars));
                put(SubCategoryType.CARTOON, Integer.valueOf(R$string.cartoon));
                put("Cats", Integer.valueOf(R$string.cats));
                put("Christmas", Integer.valueOf(R$string.christmas));
                put("City overlook", Integer.valueOf(R$string.cityscapes));
                put("Clouds", Integer.valueOf(R$string.clouds));
                put("Coast", Integer.valueOf(R$string.beach));
                put("Deserts", Integer.valueOf(R$string.deserts));
                put("Docs", Integer.valueOf(R$string.docs));
                put("Dogs", Integer.valueOf(R$string.dogs));
                put("Eye-glasses", Integer.valueOf(R$string.eyeglasses));
                put("Forest", Integer.valueOf(R$string.forest));
                put("Fountains", Integer.valueOf(R$string.fountains));
                put("Hats", Integer.valueOf(R$string.hats));
                put("Holidays", Integer.valueOf(R$string.holidays));
                put(SubCategoryType.MAP, Integer.valueOf(R$string.maps));
                put("Mountain", Integer.valueOf(R$string.mountain));
                put(SubCategoryType.NEWSPAPER, Integer.valueOf(R$string.newspaper));
                put("Night scene", Integer.valueOf(R$string.night_scene));
                put("Open country", Integer.valueOf(R$string.open_country));
                put("QR", Integer.valueOf(R$string.qrcode));
                put("Railways", Integer.valueOf(R$string.railways));
                put(SubCategoryType.RECEIPT, Integer.valueOf(R$string.receipt));
                put("Shoes", Integer.valueOf(R$string.shoes));
                put(SubCategoryType.SIGNS, Integer.valueOf(R$string.signage));
                put("Snow", Integer.valueOf(R$string.snow));
                put(SubCategoryType.SPORT, Integer.valueOf(R$string.sport));
                put("Street", Integer.valueOf(R$string.street));
                put("Sunsets", Integer.valueOf(R$string.sunsets));
                put("Trains", Integer.valueOf(R$string.trains));
                put(SubCategoryType.WATCH, Integer.valueOf(R$string.watch));
                put("Waterfall", Integer.valueOf(R$string.waterfall));
                put(SubCategoryType.WEBSITE, Integer.valueOf(R$string.website));
                put("Wedding", Integer.valueOf(R$string.wedding));
                put("Whiteboard", Integer.valueOf(R$string.whiteboard));
                put("Today", Integer.valueOf(R$string.today));
                put("Last 7 days", Integer.valueOf(R$string.category_time_past_week));
                put("Last 30 days", Integer.valueOf(R$string.category_time_past_month));
                put("Last 6 months", Integer.valueOf(R$string.category_time_past_six_month));
                put("Last 12 months", Integer.valueOf(R$string.category_time_last_year));
                put("Other Documents", Integer.valueOf(R$string.others));
            }
        };
        VISUAL_SEARCH_SUB_CATEGORY_STRING_MAP = r0;
        AnonymousClass2 r1 = new LinkedHashMap<String, Integer>() {
            {
                put("panorama", Integer.valueOf(R$string.panorama));
                put("selfie", Integer.valueOf(R$string.selfie));
                put("GIF", Integer.valueOf(R$string.gif));
                put("virtual_shot", Integer.valueOf(R$string.virtual_shot));
                put("360_photo", Integer.valueOf(R$string.image_360));
                put("motion_photo", Integer.valueOf(R$string.speak_motion_photo));
                put("Dual capture", Integer.valueOf(R$string.dual_capture));
                put("live_focus", Integer.valueOf(CategoryType.getPortraitShotTitle()));
                put("selective_focus", Integer.valueOf(CategoryType.getPortraitShotTitle()));
                put("sticker", Integer.valueOf(R$string.camera_mode_stickers));
                put("burst_shot", Integer.valueOf(StringResources.getBurstShotStringId(R$string.burst_shot)));
                put("document_scan", Integer.valueOf(R$string.scan));
                put("pro", Integer.valueOf(R$string.pro));
                put("raw", Integer.valueOf(R$string.raw_shot));
            }
        };
        IMAGE_SHOT_MODE_NAME = r1;
        AnonymousClass3 r22 = new LinkedHashMap<String, Integer>() {
            {
                put("slow_motion", Integer.valueOf(R$string.slow_motion));
                put("hyperlapse", Integer.valueOf(R$string.hyper_motion));
                put("fast_motion", Integer.valueOf(R$string.fast_motion));
                put("super_slow_mo", Integer.valueOf(R$string.super_slow_mo));
                put("360_video", Integer.valueOf(R$string.video_360));
                put("directors_view", Integer.valueOf(R$string.directors_view));
                put("video", Integer.valueOf(R$string.video));
                put("pro_video", Integer.valueOf(R$string.pro_video));
                put("portrait_video", Integer.valueOf(R$string.portrait_video));
                if (Features.isEnabled(Features.SUPPORT_DUAL_REC)) {
                    put("Dual_Recording_Info", Integer.valueOf(R$string.dual_rec));
                }
                put("log_video", Integer.valueOf(R$string.camera_capture_mode_log_video));
                put("3d_capture_video", Integer.valueOf(R$string.capture_3d));
                put("apv_video", Integer.valueOf(R$string.apv_video));
            }
        };
        VIDEO_SHOT_MODE_NAME = r22;
        AnonymousClass5 r32 = new LinkedHashMap<String, Integer>() {
            {
                put(ExpressionsType.EXPRESSION_HAPPY.getSubCategory(), Integer.valueOf(R$string.expressions_happy));
                put(ExpressionsType.EXPRESSION_NEUTRAL.getSubCategory(), Integer.valueOf(R$string.expressions_neutral));
                put(ExpressionsType.EXPRESSION_DISLIKE.getSubCategory(), Integer.valueOf(R$string.expressions_dislike));
                put(ExpressionsType.EXPRESSION_SURPRISE.getSubCategory(), Integer.valueOf(R$string.expressions_surprise));
            }
        };
        EXPRESSIONS_NAME = r32;
        AnonymousClass6 r42 = new LinkedHashMap<String, Integer>() {
            {
                SuggestionKeyword suggestionKeyword = SuggestionKeyword.BLURRY;
                put(suggestionKeyword.name(), Integer.valueOf(suggestionKeyword.getTitleResId()));
                SuggestionKeyword suggestionKeyword2 = SuggestionKeyword.RECENTLY_ADDED;
                put(suggestionKeyword2.name(), Integer.valueOf(suggestionKeyword2.getTitleResId()));
            }
        };
        SUGGESTION_KEYWORDS_NAME = r42;
        AnonymousClass7 r5 = new LinkedHashMap<String, Integer>() {
            {
                put("Favorites", Integer.valueOf(R$string.favorite));
                put("Generated", Integer.valueOf(R$string.ai_edited));
                put("Recently edited", Integer.valueOf(R$string.edited));
                put("Duplicates", Integer.valueOf(R$string.duplicates));
                put("Trash", Integer.valueOf(R$string.trash));
            }
        };
        ACTIVITY_NAME = r5;
        r0.putAll(r1);
        r0.putAll(r22);
        r0.putAll(r32);
        r0.putAll(r42);
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            r0.putAll(r5);
        }
    }

    /* access modifiers changed from: private */
    public static int getPortraitShotTitle() {
        if (SdkConfig.atLeast(SdkConfig.SEM.R_MR1)) {
            return R$string.shot_mode_portrait;
        }
        return R$string.live_focus;
    }
}
