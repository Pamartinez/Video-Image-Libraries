package com.samsung.o3dp.lib3dphotography;

import android.graphics.Bitmap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MeshUtils {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MeshInfo {
        public List<MeshLayerInfo> layers;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MeshLayerInfo {
        public Bitmap bitmap;
        public float[] colors;
        public int[] indices;
        public int primitive;
        public float[] texCoords;
        public float[] vertices;
    }
}
