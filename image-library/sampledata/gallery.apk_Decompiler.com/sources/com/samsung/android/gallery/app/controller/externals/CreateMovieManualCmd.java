package com.samsung.android.gallery.app.controller.externals;

import android.content.ComponentName;
import android.content.Intent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMovieManualCmd extends CreateMovieCmd {
    private static final String CLASS_NAME;
    private static final String PACKAGE_NAME;

    static {
        String str;
        String str2;
        SdkConfig.SEM sem = SdkConfig.SEM.S;
        if (SdkConfig.atLeast(sem)) {
            str = "com.sec.android.app.vepreload";
        } else {
            str = "com.samsung.app.highlightplayer";
        }
        PACKAGE_NAME = str;
        if (SdkConfig.atLeast(sem)) {
            str2 = "com.sec.android.app.vepreload.createmovie.activity.CreateMovieMainActivity";
        } else {
            str2 = "com.samsung.app.highlightplayer.activity.CreateMovieMainActivity";
        }
        CLASS_NAME = str2;
    }

    public String getCreateMovieMode() {
        return "MV";
    }

    public Intent getMovieMakerIntent(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (isSupported(mediaItem)) {
                arrayList.add(ContentUri.getUri(mediaItem));
            }
            if (arrayList.size() == 60) {
                break;
            }
        }
        Intent intent = new Intent("android.intent.action.EDIT");
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.putExtra("create_app", "create_movie");
        intent.putExtra("launchapplication", getCreateMovieMode());
        intent.putParcelableArrayListExtra("selectedItems", arrayList);
        setIntentWithCommonExtra(intent);
        return intent;
    }
}
