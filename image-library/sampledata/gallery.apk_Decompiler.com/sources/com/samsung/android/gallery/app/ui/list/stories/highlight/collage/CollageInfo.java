package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import Ad.C0720a;
import E5.b;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageInfo {
    private ArrayList<Long> fileIds = new ArrayList<>();
    private ArrayList<MediaItem> items = new ArrayList<>();
    CollageType type;
    int typeValue;

    private ArrayList<Long> convertIdStringFrom(String str) {
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        ArrayList<Long> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < Math.min(split.length, getCollageSize()); i2++) {
            String str2 = split[i2];
            if (!TextUtils.isEmpty(str2)) {
                arrayList.add(Long.valueOf(Long.parseLong(str2)));
            }
        }
        return arrayList;
    }

    private CollageType readCollageType(int i2) {
        return CollageType.getType(i2 - 1);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CollageInfo) || hashCode() != obj.hashCode()) {
            return false;
        }
        return true;
    }

    public int getCollageSize() {
        CollageType collageType = this.type;
        if (collageType != null) {
            return collageType.getSize();
        }
        return 0;
    }

    public int getFileCount() {
        return this.fileIds.size();
    }

    public ArrayList<Long> getFileIds() {
        return this.fileIds;
    }

    public ArrayList<MediaItem> getItems() {
        return this.items;
    }

    public CollageType getType() {
        return this.type;
    }

    public int getTypeValue() {
        return this.typeValue;
    }

    public int hashCode() {
        return (this.type + this.fileIds.toString()).hashCode();
    }

    public boolean isGridType() {
        if (this.typeValue < 6) {
            return true;
        }
        return false;
    }

    public void load(Cursor cursor) {
        int i2 = cursor.getInt(cursor.getColumnIndex("collage_type"));
        this.typeValue = i2;
        this.type = readCollageType(i2);
        String string = cursor.getString(cursor.getColumnIndex("collage_sec_media_ids"));
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        this.fileIds = convertIdStringFrom(string);
    }

    public void reset() {
        this.fileIds.clear();
    }

    public String toString() {
        return "CollageInfo@" + Integer.toHexString(hashCode()) + "{" + this.type + "/" + this.fileIds + "}";
    }

    public void updateInfo(ArrayList<MediaItem> arrayList) {
        this.items = arrayList;
        this.fileIds = (ArrayList) arrayList.stream().mapToLong(new b(10)).boxed().collect(Collectors.toCollection(new C0720a(1)));
    }

    public void updateType(CollageType collageType) {
        this.typeValue = CollageType.getTypeValue(collageType);
        this.type = collageType;
    }

    public boolean valid() {
        return this.type.isValid() && getFileCount() == getCollageSize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r1 = r1.type;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean valid(java.util.ArrayList<java.lang.Long> r2) {
        /*
            r1 = this;
            java.util.ArrayList<java.lang.Long> r0 = r1.fileIds
            boolean r2 = r2.containsAll(r0)
            if (r2 == 0) goto L_0x0014
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageType r1 = r1.type
            if (r1 == 0) goto L_0x0014
            boolean r1 = r1.isValid()
            if (r1 == 0) goto L_0x0014
            r1 = 1
            return r1
        L_0x0014:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo.valid(java.util.ArrayList):boolean");
    }
}
