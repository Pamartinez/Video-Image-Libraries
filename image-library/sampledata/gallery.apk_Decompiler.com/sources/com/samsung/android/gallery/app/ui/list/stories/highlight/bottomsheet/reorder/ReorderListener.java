package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ReorderListener {
    void begin();

    void dragStart();

    void drop(int i2, int i7, boolean z);

    void end();

    void reorder(int i2, int i7);
}
