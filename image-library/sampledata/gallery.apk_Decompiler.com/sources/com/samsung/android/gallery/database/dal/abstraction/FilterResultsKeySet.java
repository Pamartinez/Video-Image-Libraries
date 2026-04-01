package com.samsung.android.gallery.database.dal.abstraction;

import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.database.dbtype.TagType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FilterResultsKeySet {
    public static String getField(String str, String str2, int i2) {
        if (str == null) {
            return "key_word";
        }
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1907941713:
                if (str.equals("People")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1790835355:
                if (str.equals("Things")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1347456360:
                if (str.equals("Documents")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1179428371:
                if (str.equals("My tags")) {
                    c5 = 3;
                    break;
                }
                break;
            case -715666509:
                if (str.equals("Scenery")) {
                    c5 = 4;
                    break;
                }
                break;
            case -638961672:
                if (str.equals("Things Scenery")) {
                    c5 = 5;
                    break;
                }
                break;
            case -626311778:
                if (str.equals("Camera mode")) {
                    c5 = 6;
                    break;
                }
                break;
            case 80127:
                if (str.equals("Pet")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1576064422:
                if (str.equals("ScreenShot")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1843423419:
                if (str.equals("Expressions")) {
                    c5 = 9;
                    break;
                }
                break;
            case 1965687765:
                if (str.equals("Location")) {
                    c5 = 10;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
                    return "recommended_id";
                }
                return "persontag";
            case 1:
            case 2:
            case 4:
            case 5:
                return "scenetag";
            case 3:
                return "usertag";
            case 6:
                return getShotModeField(str2);
            case 7:
                return "pet_recommended_id";
            case 8:
                if (ScreenShotFilterType.All.key().equals(str2)) {
                    return "all_screenshot";
                }
                return "scene_name";
            case 9:
                return "expressionstag";
            case 10:
                if (TagType.get(i2) == TagType.POI) {
                    return "poitag";
                }
                return "facet_location";
            default:
                return "key_word";
        }
    }

    public static String getShotModeField(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1997563968:
                if (str.equals("log_video")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1890252483:
                if (str.equals("sticker")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1597639371:
                if (str.equals("selective_focus")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1419937693:
                if (str.equals("apv_video")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1318821169:
                if (str.equals("video_collage")) {
                    c5 = 4;
                    break;
                }
                break;
            case -1193888663:
                if (str.equals("pro_video")) {
                    c5 = 5;
                    break;
                }
                break;
            case -986890135:
                if (str.equals("motion_photo")) {
                    c5 = 6;
                    break;
                }
                break;
            case -930808931:
                if (str.equals("Similar photo")) {
                    c5 = 7;
                    break;
                }
                break;
            case -906020504:
                if (str.equals("selfie")) {
                    c5 = 8;
                    break;
                }
                break;
            case -666360914:
                if (str.equals("virtual_shot")) {
                    c5 = 9;
                    break;
                }
                break;
            case -422395980:
                if (str.equals("slow_motion")) {
                    c5 = 10;
                    break;
                }
                break;
            case -243530503:
                if (str.equals("fast_motion")) {
                    c5 = 11;
                    break;
                }
                break;
            case 70564:
                if (str.equals("GIF")) {
                    c5 = 12;
                    break;
                }
                break;
            case 111277:
                if (str.equals("pro")) {
                    c5 = 13;
                    break;
                }
                break;
            case 112680:
                if (str.equals("raw")) {
                    c5 = 14;
                    break;
                }
                break;
            case 100313435:
                if (str.equals("image")) {
                    c5 = 15;
                    break;
                }
                break;
            case 112202875:
                if (str.equals("video")) {
                    c5 = 16;
                    break;
                }
                break;
            case 113529248:
                if (str.equals("360_photo")) {
                    c5 = 17;
                    break;
                }
                break;
            case 119089129:
                if (str.equals("360_video")) {
                    c5 = 18;
                    break;
                }
                break;
            case 363874368:
                if (str.equals("Selfie focus")) {
                    c5 = 19;
                    break;
                }
                break;
            case 410607289:
                if (str.equals("burst_shot")) {
                    c5 = 20;
                    break;
                }
                break;
            case 481952495:
                if (str.equals("Single Taken")) {
                    c5 = 21;
                    break;
                }
                break;
            case 1069983349:
                if (str.equals("panorama")) {
                    c5 = 22;
                    break;
                }
                break;
            case 1121735576:
                if (str.equals("3d_capture")) {
                    c5 = 23;
                    break;
                }
                break;
            case 1126770652:
                if (str.equals("super_slow_mo")) {
                    c5 = 24;
                    break;
                }
                break;
            case 1140309373:
                if (str.equals("directors_view")) {
                    c5 = 25;
                    break;
                }
                break;
            case 1152607359:
                if (str.equals("Dual_Recording_Info")) {
                    c5 = 26;
                    break;
                }
                break;
            case 1201342405:
                if (str.equals("live_focus")) {
                    c5 = 27;
                    break;
                }
                break;
            case 1367192514:
                if (str.equals("Dual capture")) {
                    c5 = 28;
                    break;
                }
                break;
            case 1378818146:
                if (str.equals("shot_and_more")) {
                    c5 = 29;
                    break;
                }
                break;
            case 1404162775:
                if (str.equals("portrait_video")) {
                    c5 = 30;
                    break;
                }
                break;
            case 1585522465:
                if (str.equals("document_scan")) {
                    c5 = 31;
                    break;
                }
                break;
            case 1675382260:
                if (str.equals("3d_capture_image")) {
                    c5 = ' ';
                    break;
                }
                break;
            case 1687271700:
                if (str.equals("3d_capture_video")) {
                    c5 = '!';
                    break;
                }
                break;
            case 1815059009:
                if (str.equals("hyperlapse")) {
                    c5 = '\"';
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 10:
            case 11:
            case 18:
            case 24:
            case 30:
            case '!':
            case '\"':
                return "recording_mode";
            case 1:
            case 2:
            case 6:
            case 7:
            case 8:
            case 9:
            case 13:
            case 14:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 31:
            case ' ':
                return "sef_file_type";
            case 12:
                return "mime_type";
            case 15:
            case 16:
                return ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE;
            default:
                return "key_word";
        }
    }

    public static String getShotModeValue(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1419937693:
                if (str.equals("apv_video")) {
                    c5 = 0;
                    break;
                }
                break;
            case 70564:
                if (str.equals("GIF")) {
                    c5 = 1;
                    break;
                }
                break;
            case 113529248:
                if (str.equals("360_photo")) {
                    c5 = 2;
                    break;
                }
                break;
            case 119089129:
                if (str.equals("360_video")) {
                    c5 = 3;
                    break;
                }
                break;
            case 481952495:
                if (str.equals("Single Taken")) {
                    c5 = 4;
                    break;
                }
                break;
            case 1152607359:
                if (str.equals("Dual_Recording_Info")) {
                    c5 = 5;
                    break;
                }
                break;
            case 1201342405:
                if (str.equals("live_focus")) {
                    c5 = 6;
                    break;
                }
                break;
            case 1585522465:
                if (str.equals("document_scan")) {
                    c5 = 7;
                    break;
                }
                break;
            case 1675382260:
                if (str.equals("3d_capture_image")) {
                    c5 = 8;
                    break;
                }
                break;
            case 1687271700:
                if (str.equals("3d_capture_video")) {
                    c5 = 9;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "camcorder_apv";
            case 1:
                return "image/gif";
            case 2:
                return "_360_photo";
            case 3:
                return "_360_video";
            case 4:
                return "single_take";
            case 5:
                return "dual_rec";
            case 6:
                return "portrait";
            case 7:
                return "scan";
            case 8:
            case 9:
                return "3d_capture";
            default:
                return str;
        }
    }
}
