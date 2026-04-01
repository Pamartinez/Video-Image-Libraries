package com.samsung.o3dp.lib3dphotography;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum O3DPObjType {
    HUMAN(0),
    PET(1),
    OBJECT(2),
    DEFORMABLE_OBJECT(3);
    
    private final int type;

    private O3DPObjType(int i2) {
        this.type = i2;
    }

    public int getType() {
        return this.type;
    }
}
