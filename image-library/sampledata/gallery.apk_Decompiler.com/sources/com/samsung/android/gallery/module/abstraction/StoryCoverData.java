package com.samsung.android.gallery.module.abstraction;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryCoverData {
    public int height;
    public MediaType mediaType;
    public int orientation;
    public String path;
    public int width;

    public StoryCoverData(FileItemInterface fileItemInterface) {
        this.path = fileItemInterface.getPath();
        this.orientation = fileItemInterface.getOrientation();
        this.mediaType = fileItemInterface.getMediaType();
        this.width = fileItemInterface.getWidth();
        this.height = fileItemInterface.getHeight();
    }
}
