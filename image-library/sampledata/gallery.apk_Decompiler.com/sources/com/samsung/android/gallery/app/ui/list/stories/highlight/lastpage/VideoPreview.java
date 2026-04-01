package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import D9.e;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageHolder;
import java.util.Optional;
import java.util.function.Consumer;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoPreview {
    public RecyclerView mRecyclerView;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute$0(Consumer consumer, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            Optional.ofNullable((PageHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2))).ifPresent(consumer);
        }
    }

    public void execute(Consumer<PageHolder> consumer) {
        Optional.ofNullable(this.mRecyclerView).ifPresent(new e(1, consumer));
    }

    public void pause() {
        execute(new B(25));
    }

    public void resume() {
        execute(new B(23));
    }

    public void setListView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public void startPreview() {
        execute(new B(26));
    }

    public void stopPreview() {
        execute(new B(24));
    }
}
