package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IntentAction {
    public static boolean isAlbumPick(String str) {
        return "com.android.gallery.action.ALBUM_PICK".equalsIgnoreCase(str);
    }

    public static boolean isForAddContentsToSharedView(String str) {
        return "com.android.gallery.action.ADD_CONTENTS_TO_SHARED_ALBUM".equals(str);
    }

    public static boolean isForAlbumView(String str) {
        return "com.android.gallery.action.ALBUM_VIEW".equalsIgnoreCase(str);
    }

    public static boolean isForCrop(String str) {
        if ("com.android.camera.action.CROP".equals(str) || "com.sec.android.gallery3d.CROP_SEC_ONLY".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isForFamilyAlbumView(String str) {
        return "com.android.gallery.action.VIEW_FAMILY_ALBUM".equals(str);
    }

    public static boolean isForHideRule(String str) {
        return "com.android.gallery.action.HIDE_RULE".equals(str);
    }

    public static boolean isForPick(String str) {
        if (isPick(str) || isAlbumPick(str) || isItemPick(str) || isMultiplePick(str) || isGetContent(str)) {
            return true;
        }
        return false;
    }

    public static boolean isForPicturesView(String str) {
        return "com.android.gallery.action.PICTURES_VIEW".equalsIgnoreCase(str);
    }

    public static boolean isForSearchView(String str) {
        return "com.android.gallery.action.SEARCH_VIEW".equalsIgnoreCase(str);
    }

    public static boolean isForSharedPicturesView(String str) {
        return "com.android.gallery.action.SHARED_PICTURES_VIEW".equals(str);
    }

    public static boolean isForSharedView(String str) {
        return "com.android.gallery.action.SHARED_VIEW".equalsIgnoreCase(str);
    }

    public static boolean isForShortCut(String str) {
        if (isForShortCutItemView(str) || "com.android.gallery.action.SHORTCUT_ALBUM_VIEW".equals(str) || "com.android.gallery.action.SHORTCUT_VIEW".equals(str) || "com.android.gallery.action.SEARCH_SHORTCUT_VIEW".equals(str) || "com.android.gallery.action.SEARCH_FROM_GALAXY_FINDER_VIEW".equals(str) || "com.android.gallery.action.RECENT_ALBUM_SHORTCUT_VIEW".equals(str) || "com.android.gallery.action.FAVORITE_ALBUM_SHORTCUT_VIEW".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isForShortCutItemView(String str) {
        return "com.android.gallery.action.SHORTCUT_ITEM_VIEW".equals(str);
    }

    public static boolean isForTrash(String str) {
        return "com.android.gallery.action.TRASH_VIEW".equalsIgnoreCase(str);
    }

    public static boolean isForView(String str) {
        if ("android.intent.action.VIEW".equalsIgnoreCase(str) || "android.intent.action.QUICK_VIEW".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    public static boolean isGetContent(String str) {
        return "android.intent.action.GET_CONTENT".equalsIgnoreCase(str);
    }

    public static boolean isItemPick(String str) {
        return "com.android.gallery.action.ITEM_PICK".equalsIgnoreCase(str);
    }

    public static boolean isMultiplePick(String str) {
        return "com.samsung.intent.action.MULTIPLE_PICK".equalsIgnoreCase(str);
    }

    public static boolean isPick(String str) {
        return "android.intent.action.PICK".equalsIgnoreCase(str);
    }
}
