package com.samsung.android.gallery.database.dbtype;

import Ad.C0720a;
import S3.d;
import U5.b;
import ba.C0582a;
import c4.C0431a;
import com.samsung.android.gallery.database.R$drawable;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ScreenShotFilterType {
    All("cap_all", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_ALL),
    BoardingPass("cap_boarding_pass", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_BOARDING_PASS),
    Coupon("cap_coupon", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_COUPON),
    Shopping("cap_shopping", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_SHOPPING),
    Location("cap_location", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_LOCATION),
    BarcodeQR("cap_barcode_qr", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_BARCODE_QR),
    Event("cap_event", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_EVENTS),
    Conversation("cap_conversation"),
    Music("cap_music"),
    Movie("cap_movie"),
    Book("cap_book"),
    Food("cap_food"),
    Communication("cap_communication", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_CHATS),
    SocialMedia("cap_social_media", AnalyticsEventId.EVENT_SCREEN_SHOT_FILTER_SOCIAL_MEDIA);
    
    public static final String CAPTURE = "capture";
    private static ArrayList<String> sKeySet;
    static final HashMap<String, ScreenShotFilterType> sMap = null;
    private static String sProjection;
    public static final Map<String, String> sSubCategoryFilesScreenId = null;
    public static final Map<String, Integer> sSubCategoryInfo = null;
    public static final Map<String, Integer> sVirtualSubCategoryInfo = null;
    /* access modifiers changed from: private */
    public final AnalyticsEventId mEventId;
    /* access modifiers changed from: private */
    public final String mKey;

    static {
        int i2;
        sMap = new HashMap<>();
        for (ScreenShotFilterType screenShotFilterType : values()) {
            sMap.put(screenShotFilterType.key(), screenShotFilterType);
        }
        sVirtualSubCategoryInfo = new HashMap<String, Integer>() {
            {
                put("cap_all", Integer.valueOf(R$drawable.ic_actions_square_grid_2x2));
                put("cap_ask", Integer.valueOf(R$drawable.tab_ic_search));
            }
        };
        sSubCategoryInfo = new HashMap<String, Integer>() {
            {
                put("cap_shopping", Integer.valueOf(R$drawable.ic_shopping_cart));
                int i2 = R$drawable.ic_coupon_ticket;
                put("cap_boarding_pass", Integer.valueOf(i2));
                put("cap_coupon", Integer.valueOf(i2));
                put("cap_barcode_qr", Integer.valueOf(R$drawable.ic_qrcode_generator));
                put("cap_location", Integer.valueOf(R$drawable.ic_location));
                put("cap_social_media", Integer.valueOf(R$drawable.ic_internet_web_globe));
                put("cap_communication", Integer.valueOf(R$drawable.ic_group_message_on_message));
                put("cap_event", Integer.valueOf(R$drawable.ic_event_calendar_line));
            }
        };
        sSubCategoryFilesScreenId = new HashMap<String, String>() {
            {
                put(ScreenShotFilterType.All.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_ALL.toString());
                put(ScreenShotFilterType.BoardingPass.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_BOARDING_PASS.toString());
                put(ScreenShotFilterType.Coupon.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_COUPON.toString());
                put(ScreenShotFilterType.Shopping.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_SHOPPING.toString());
                put(ScreenShotFilterType.Location.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_LOCATION.toString());
                put(ScreenShotFilterType.BarcodeQR.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_BARCODE_QR.toString());
                put(ScreenShotFilterType.Event.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_EVENTS.toString());
                put(ScreenShotFilterType.Conversation.mKey, (Object) null);
                put(ScreenShotFilterType.Music.mKey, (Object) null);
                put(ScreenShotFilterType.Movie.mKey, (Object) null);
                put(ScreenShotFilterType.Book.mKey, (Object) null);
                put(ScreenShotFilterType.Food.mKey, (Object) null);
                put(ScreenShotFilterType.Communication.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_CHATS.toString());
                put(ScreenShotFilterType.SocialMedia.mKey, AnalyticsScreenId.SCREEN_VISUAL_SEARCH_SCREENSHOT_CATEGORY_FILES_SOCIAL_MEDIA.toString());
            }
        };
    }

    private ScreenShotFilterType(String str, AnalyticsEventId analyticsEventId) {
        this.mKey = str;
        this.mEventId = analyticsEventId;
    }

    public static AnalyticsEventId getEventId(String str) {
        return (AnalyticsEventId) Optional.ofNullable(sMap.get(str)).map(new C0431a(12)).orElse((Object) null);
    }

    public static String getSubCategoryFilesScreenId(String str) {
        if (str != null) {
            return sSubCategoryFilesScreenId.get(str);
        }
        return null;
    }

    public static Integer getTypeIcon(String str) {
        Integer num = sVirtualSubCategoryInfo.get(str);
        if (num == null) {
            return (Integer) Optional.ofNullable(sSubCategoryInfo.get(str)).orElse(Integer.valueOf(R$drawable.ic_coupon_ticket));
        }
        return num;
    }

    public static boolean isSubCategory(String str) {
        return sSubCategoryInfo.containsKey(str);
    }

    public static ArrayList<String> keySet() {
        if (sKeySet == null) {
            sKeySet = (ArrayList) Arrays.stream(values()).filter(new d(28)).map(new C0431a(13)).collect(Collectors.toCollection(new C0720a(1)));
        }
        return sKeySet;
    }

    public static String projection() {
        if (sProjection == null) {
            SqliteCaseBuilder sqliteCaseBuilder = new SqliteCaseBuilder();
            Arrays.stream(values()).filter(new d(27)).forEach(new C0582a(7, sqliteCaseBuilder));
            sProjection = sqliteCaseBuilder.elseThen(100).build();
        }
        return sProjection;
    }

    public String key() {
        return this.mKey;
    }

    private ScreenShotFilterType(String str) {
        this(r2, r3, str, (AnalyticsEventId) null);
    }

    public static String projection(String str) {
        SqliteCaseBuilder sqliteCaseBuilder = new SqliteCaseBuilder();
        Arrays.stream(values()).filter(new d(26)).forEach(new b(26, sqliteCaseBuilder, str));
        return sqliteCaseBuilder.elseThen(100).build();
    }
}
