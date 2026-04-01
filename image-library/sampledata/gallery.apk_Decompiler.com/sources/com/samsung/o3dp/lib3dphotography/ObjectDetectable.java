package com.samsung.o3dp.lib3dphotography;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface ObjectDetectable {
    void detect(Context context, Bitmap bitmap, List<O3DPObject> list, List<Face> list2);
}
