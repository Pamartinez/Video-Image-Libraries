package com.samsung.o3dp.lib3dphotography;

import android.graphics.Rect;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPObject extends O3DPRect {
    public O3DPObject(O3DPObjType o3DPObjType) {
        super(o3DPObjType);
    }

    public O3DPObject copyInstance() {
        O3DPObject o3DPObject = new O3DPObject(this.mObjectType);
        Rect rect = this.mBound;
        o3DPObject.setBound(new Rect(rect.left, rect.top, rect.right, rect.bottom));
        return o3DPObject;
    }
}
