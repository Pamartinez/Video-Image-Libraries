package com.samsung.android.gallery.support.type;

import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActivityType {
    static final List<String> valueList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TitleHolder {
        static final HashMap<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("Favorites", Integer.valueOf(R$string.favorites));
                int i2 = R$string.ai_edited;
                put("Genarated", Integer.valueOf(i2));
                put("Generated", Integer.valueOf(i2));
                put("Recently edited", Integer.valueOf(R$string.edited));
                put("Duplicates", Integer.valueOf(R$string.duplicate));
                put("Trash", Integer.valueOf(R$string.trash));
                if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
                    put("PrivateAlbum", Integer.valueOf(R$string.private_album_header));
                }
                put("OldDocuments", Integer.valueOf(R$string.old_documents));
            }
        };
    }

    static {
        List<String> list;
        if (SdkConfig.lessThan(SdkConfig.GED.U)) {
            list = List.of("Recently edited");
        } else if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU) {
            list = List.of("Generated", "Recently edited");
        } else {
            list = List.of("Favorites", "OldDocuments", "Generated", "Recently edited", "Duplicates", "Trash");
        }
        valueList = list;
    }

    public static int getTitleRes(String str) {
        return TitleHolder.map.getOrDefault(str, Integer.valueOf(R$string.unknown)).intValue();
    }

    public static List<String> listOf() {
        PocFeatures pocFeatures = PocFeatures.LockedAlbum;
        return valueList;
    }
}
