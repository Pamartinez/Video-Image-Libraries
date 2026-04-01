package com.samsung.android.gallery.app.controller.externals;

import android.content.ComponentName;
import android.content.Intent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMovieMultiEditCmd extends CreateMovieCmd {
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
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.sec.android.app.vepreload", "com.sec.android.app.vepreload.common.activity.MultiEditActivity"));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("launch_from", "From_Gallery");
        intent.putParcelableArrayListExtra("selectedItems", arrayList);
        setIntentWithCommonExtra(intent);
        return intent;
    }
}
