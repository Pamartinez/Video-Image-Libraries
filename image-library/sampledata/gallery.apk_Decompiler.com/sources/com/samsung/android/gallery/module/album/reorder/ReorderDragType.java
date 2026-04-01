package com.samsung.android.gallery.module.album.reorder;

import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReorderDragType {
    private Type mType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        REORDER,
        FOLDER,
        REORDER_AND_FOLDER,
        NONE
    }

    public ReorderDragType(Type type) {
        this.mType = type;
    }

    private Type getDragType(boolean z, boolean z3) {
        if (z && z3) {
            return Type.REORDER_AND_FOLDER;
        }
        if (z) {
            return Type.FOLDER;
        }
        if (z3) {
            return Type.REORDER;
        }
        return Type.NONE;
    }

    public Type get() {
        return this.mType;
    }

    public void initDragType(BooleanSupplier booleanSupplier, BooleanSupplier booleanSupplier2) {
        this.mType = getDragType(booleanSupplier.getAsBoolean(), booleanSupplier2.getAsBoolean());
    }
}
