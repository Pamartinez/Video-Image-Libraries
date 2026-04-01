package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
interface DragListener {
    void onDragBegin();

    void onDragEnd();

    void onDragOver(int i2, float f, float f5);

    void onDragStart();

    void onDrop(int i2, int i7);

    boolean scrollable();
}
