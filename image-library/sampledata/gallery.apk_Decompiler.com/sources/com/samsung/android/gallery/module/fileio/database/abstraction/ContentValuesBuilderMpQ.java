package com.samsung.android.gallery.module.fileio.database.abstraction;

import com.samsung.android.gallery.module.fileio.database.abstraction.ContentValuesBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentValuesBuilderMpQ extends ContentValuesBuilderMp {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MpQColumnNameHolder {
        static final HashMap<ContentValuesBuilder.ColumnName, String> map = new HashMap<ContentValuesBuilder.ColumnName, String>() {
            {
                put(ContentValuesBuilder.ColumnName.DATE_TAKEN, IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME);
                put(ContentValuesBuilder.ColumnName.GROUP_ID, "burst_group_id");
            }
        };
    }

    public String getColumnName(ContentValuesBuilder.ColumnName columnName) {
        String str = MpQColumnNameHolder.map.get(columnName);
        if (str != null) {
            return str;
        }
        return super.getColumnName(columnName);
    }

    public ContentValuesBuilder setGroupInfo(long j2, int i2, int i7) {
        super.setGroupInfo(j2, i2, i7);
        if (i2 > 0) {
            this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.GROUP_TYPE), Integer.valueOf(i2));
        }
        return this;
    }

    public ContentValuesBuilder setRelativePath(String str) {
        this.mValues.put(getColumnName(ContentValuesBuilder.ColumnName.RELATIVE_PATH), FileUtils.getRelativePath(str));
        return this;
    }
}
