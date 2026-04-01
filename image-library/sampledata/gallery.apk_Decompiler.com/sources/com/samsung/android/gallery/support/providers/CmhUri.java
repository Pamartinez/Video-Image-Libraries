package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CmhUri {
    private static volatile PrivilegedUri sUris = init();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUri {
        public Uri getAuthority() {
            return unsupported();
        }

        public Uri getAutoItemStatus() {
            return unsupported();
        }

        public Uri getAutoUpdate() {
            return unsupported();
        }

        public Uri getContactsRecommendation() {
            return unsupported();
        }

        public Uri getFaces() {
            return unsupported();
        }

        public Uri getFeature() {
            return unsupported();
        }

        public Uri getFiles() {
            return unsupported();
        }

        public Uri getPersons() {
            return unsupported();
        }

        public Uri getPetFaces() {
            return unsupported();
        }

        public Uri getPetTags() {
            return unsupported();
        }

        public Uri getRawQuery(String str) {
            return unsupported();
        }

        public Uri getSceneries() {
            return unsupported();
        }

        public Uri getStory() {
            return unsupported();
        }

        public Uri getTagView() {
            return unsupported();
        }

        public Uri getUserTags() {
            return unsupported();
        }

        public Uri getVisualArt() {
            return unsupported();
        }

        public final Uri unsupported() {
            return Uri.parse("content://unsupported");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUriFake extends PrivilegedUri {
        private static final Uri AUTHORITY_URI;
        public static final Uri RAW_QUERY_URI;

        static {
            Uri parse = Uri.parse("content://com.sec.android.gallery3d.provider.GalleryMediaProvider");
            AUTHORITY_URI = parse;
            RAW_QUERY_URI = Uri.withAppendedPath(parse, "raw_sql");
        }

        public Uri getAuthority() {
            return AUTHORITY_URI;
        }

        public Uri getAutoItemStatus() {
            return super.getAutoItemStatus();
        }

        public Uri getAutoUpdate() {
            return Uri.withAppendedPath(AUTHORITY_URI, "auto_album");
        }

        public Uri getContactsRecommendation() {
            return super.getContactsRecommendation();
        }

        public Uri getFaces() {
            return Uri.withAppendedPath(AUTHORITY_URI, "faces");
        }

        public Uri getFeature() {
            return super.getFeature();
        }

        public Uri getPersons() {
            return Uri.withAppendedPath(AUTHORITY_URI, "persons");
        }

        public Uri getRawQuery(String str) {
            return RAW_QUERY_URI;
        }

        public Uri getSceneries() {
            return Uri.withAppendedPath(AUTHORITY_URI, "scene");
        }

        public Uri getStory() {
            return Uri.withAppendedPath(AUTHORITY_URI, "story");
        }

        public Uri getTagView() {
            return Uri.withAppendedPath(AUTHORITY_URI, "tagview");
        }

        public Uri getUserTags() {
            return super.getUserTags();
        }

        public Uri getVisualArt() {
            return super.getVisualArt();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUriN extends PrivilegedUri {
        private static final Uri AUTHORITY_URI;
        private static final Uri AUTO_ITEM_STATUS_TABLE_URI;
        private static final Uri FACES_URI = Uri.parse("content://com.samsung.cmh/external/faces");
        private static final Uri FEATURE_TABLE_URI;
        private static final Uri FILES_TABLE_URI;
        private static final Uri PERSONS_URI = Uri.parse("content://com.samsung.cmh/internal/persons");
        private static final Uri SCENE_URI = Uri.parse("content://com.samsung.cmh/scene");
        private static final Uri STORY_ACTIVITY_TABLE_URI;
        private static final Uri STORY_TABLE_URI = Uri.parse("content://com.samsung.cmh/story");
        private static final Uri TAGVIEW_URI = Uri.parse("content://com.samsung.cmh/tagview");
        private static final Uri USER_TAG_URI = Uri.parse("content://com.samsung.cmh/usertag");
        private static final Uri VISUAL_ART_TABLE_URI;

        static {
            Uri parse = Uri.parse("content://com.samsung.cmh");
            AUTHORITY_URI = parse;
            FILES_TABLE_URI = Uri.withAppendedPath(parse, "files");
            FEATURE_TABLE_URI = Uri.withAppendedPath(parse, "feature");
            STORY_ACTIVITY_TABLE_URI = Uri.withAppendedPath(parse, "story_activity");
            VISUAL_ART_TABLE_URI = Uri.withAppendedPath(parse, "visualart");
            AUTO_ITEM_STATUS_TABLE_URI = Uri.withAppendedPath(parse, "autoitemstatus");
        }

        public Uri getAuthority() {
            return AUTHORITY_URI;
        }

        public Uri getAutoItemStatus() {
            return AUTO_ITEM_STATUS_TABLE_URI;
        }

        public Uri getFaces() {
            return FACES_URI;
        }

        public Uri getFeature() {
            return FEATURE_TABLE_URI;
        }

        public Uri getFiles() {
            return FILES_TABLE_URI;
        }

        public Uri getPersons() {
            return PERSONS_URI;
        }

        public Uri getRawQuery(String str) {
            return AUTHORITY_URI.buildUpon().appendPath("rawquery").appendQueryParameter("rawquery", str).build();
        }

        public Uri getSceneries() {
            return SCENE_URI;
        }

        public Uri getStory() {
            return STORY_TABLE_URI;
        }

        public Uri getTagView() {
            return TAGVIEW_URI;
        }

        public Uri getUserTags() {
            return USER_TAG_URI;
        }

        public Uri getVisualArt() {
            return VISUAL_ART_TABLE_URI;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUriP extends PrivilegedUriN {
        private static final Uri FACES_URI = Uri.parse("content://media/external/cmh/faces");
        private static final Uri PERSONS_URI = Uri.parse("content://media/external/cmh/persons");
        private static final Uri SCENE_URI = Uri.parse("content://media/external/cmh/scene");
        private static final Uri TAGVIEW_URI = Uri.parse("content://media/external/cmh/tag");
        private static final Uri USER_TAG_URI = Uri.parse("content://media/external/cmh/usertag");

        public Uri getFaces() {
            return FACES_URI;
        }

        public Uri getPersons() {
            return PERSONS_URI;
        }

        public Uri getSceneries() {
            return SCENE_URI;
        }

        public Uri getTagView() {
            return TAGVIEW_URI;
        }

        public Uri getUserTags() {
            return USER_TAG_URI;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUriQ extends PrivilegedUriP {
        private static final Uri CONTACTS_RECOMENDATION_URI = Uri.parse("content://secmedia/cmh/contactsrec");
        private static final Uri FACES_URI = Uri.parse("content://secmedia/cmh/faces");
        private static final Uri PERSONS_URI = Uri.parse("content://secmedia/cmh/persons");
        private static final Uri SCENE_URI = Uri.parse("content://secmedia/cmh/scene");
        private static final Uri TAGVIEW_URI = Uri.parse("content://secmedia/cmh/tag");
        private static final Uri USER_TAG_URI = Uri.parse("content://secmedia/cmh/usertag");

        public Uri getAutoUpdate() {
            return Uri.parse("content://unsupported");
        }

        public Uri getContactsRecommendation() {
            return CONTACTS_RECOMENDATION_URI;
        }

        public Uri getFaces() {
            return FACES_URI;
        }

        public Uri getPersons() {
            return PERSONS_URI;
        }

        public Uri getSceneries() {
            return SCENE_URI;
        }

        public Uri getTagView() {
            return TAGVIEW_URI;
        }

        public Uri getUserTags() {
            return USER_TAG_URI;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUriT extends PrivilegedUriQ {
        private static final Uri AUTO_UPDATE_TABLE_URI = Uri.parse("content://com.samsung.cmh/auto_album");

        public Uri getAutoUpdate() {
            return AUTO_UPDATE_TABLE_URI;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivilegedUriU_MR extends PrivilegedUriT {
        private static final Uri CLUSTER_INFO_TABLE_URI = Uri.parse("content://secmedia/cmh/cluster_info");
        private static final Uri CLUSTER_RECT_TABLE_URI = Uri.parse("content://secmedia/cmh/cluster_rect");

        public Uri getPetFaces() {
            return CLUSTER_RECT_TABLE_URI;
        }

        public Uri getPetTags() {
            return CLUSTER_INFO_TABLE_URI;
        }
    }

    public static Uri getAuthority() {
        return sUris.getAuthority();
    }

    public static Uri getAutoItemStatus() {
        return sUris.getAutoItemStatus();
    }

    public static Uri getAutoUpdate() {
        return sUris.getAutoUpdate();
    }

    public static Uri getContactsRecommendation() {
        return sUris.getContactsRecommendation();
    }

    public static Uri getFaces() {
        return sUris.getFaces();
    }

    public static Uri getFeature() {
        return sUris.getFeature();
    }

    public static Uri getFiles() {
        return sUris.getFiles();
    }

    public static Uri getPersons() {
        return sUris.getPersons();
    }

    public static Uri getPetFaces() {
        return sUris.getPetFaces();
    }

    public static Uri getPetTags() {
        return sUris.getPetTags();
    }

    public static Uri getRawQuery(String str) {
        return sUris.getRawQuery(str);
    }

    public static Uri getSceneries() {
        return sUris.getSceneries();
    }

    public static Uri getStory() {
        return sUris.getStory();
    }

    public static Uri getTagView() {
        return sUris.getTagView();
    }

    public static Uri getUserTags() {
        return sUris.getUserTags();
    }

    public static Uri getVisualArt() {
        return sUris.getVisualArt();
    }

    public static PrivilegedUri init() {
        try {
            if (SdkConfig.atLeast(SdkConfig.SEM.U_MR1)) {
                return new PrivilegedUriU_MR();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.T)) {
                return new PrivilegedUriT();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.Q)) {
                return new PrivilegedUriQ();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.P)) {
                return new PrivilegedUriP();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.N)) {
                return new PrivilegedUriN();
            }
            if (Features.isEnabled(Features.SUPPORT_ANDROID_EMULATOR)) {
                return new PrivilegedUriFake();
            }
            return new PrivilegedUri();
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("init failed e="), "Uri");
        }
    }
}
