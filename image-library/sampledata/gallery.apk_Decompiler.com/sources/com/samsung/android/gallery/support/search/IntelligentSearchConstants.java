package com.samsung.android.gallery.support.search;

import android.net.Uri;
import com.samsung.android.gallery.support.config.Component$SamsungSearch;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class IntelligentSearchConstants {
    public static final Uri HINT_MEDIA_URI;
    public static final Uri MEDIA_OBSERVING_URI;
    public static final Uri MEDIA_URI;
    public static final Uri PROVIDER_URI;
    public static final Uri SUGGEST_MEDIA_URI;
    static final Uri SUGGEST_URI;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Projection {
        final String[] autoComplete = ((String[]) new ArrayList<String>() {
            {
                add("fieldName");
                add("keywords");
                if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
                    add("keyword_translated");
                }
                if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE)) {
                    add("featureName");
                }
            }
        }.toArray(new String[0]));
        final String[] fuzzy = ((String[]) new ArrayList<String>() {
            {
                addAll(Arrays.asList(Projection.this.autoComplete));
                remove("fieldName");
            }
        }.toArray(new String[0]));
        final String[] normal = ((String[]) new ArrayList<String>() {
            {
                add("_id");
                if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
                    add("face_count");
                }
                if (PreferenceFeatures.OneUi8x.VIDEO_SEARCH) {
                    add("frame_id");
                }
            }
        }.toArray(new String[0]));
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProjectionHolder {
        static final Projection instance = new Projection();
    }

    static {
        String str;
        Uri build = new Uri.Builder().scheme("content").authority(Component$SamsungSearch.AUTHORITY).appendPath("v1").build();
        PROVIDER_URI = build;
        MEDIA_URI = Uri.withAppendedPath(build, "media");
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_NEW_CONTENT_OBSERVING_URI)) {
            str = "sec_media";
        } else {
            str = "media";
        }
        MEDIA_OBSERVING_URI = Uri.withAppendedPath(build, str);
        Uri withAppendedPath = Uri.withAppendedPath(build, "suggest");
        SUGGEST_URI = withAppendedPath;
        SUGGEST_MEDIA_URI = Uri.withAppendedPath(withAppendedPath, "media");
        HINT_MEDIA_URI = build.buildUpon().appendPath("hint").appendPath("media").build();
    }

    public static String[] getProjectionForAutocomplete() {
        return ProjectionHolder.instance.autoComplete;
    }

    public static String[] getProjectionForFuzzy() {
        return ProjectionHolder.instance.fuzzy;
    }

    public static String[] getProjectionForNormal() {
        return ProjectionHolder.instance.normal;
    }
}
