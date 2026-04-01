package com.samsung.android.gallery.module.search.root;

import U9.b;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.ScreenShotHelper;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AllScreenshotFilterResultsEntity extends FilterResultsEntity {
    public AllScreenshotFilterResultsEntity(String str) {
        super(str, "all_screenshot");
        setArguments();
    }

    private void setArguments() {
        addRawLabel(String.valueOf(FileUtils.getBucketId(ScreenShotHelper.getScreenshotFolder(AppResources.getAppContext()))));
        ScreenShotFilterType.sSubCategoryInfo.keySet().forEach(new b(29, this));
    }

    public String getSelection() {
        StringJoiner stringJoiner = new StringJoiner(" =? OR ", "(", " =?)");
        stringJoiner.add("bucket_id");
        int size = ScreenShotFilterType.sSubCategoryInfo.size();
        for (int i2 = 0; i2 < size; i2++) {
            stringJoiner.add("scene_name");
        }
        return stringJoiner.toString();
    }
}
