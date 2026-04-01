package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeListView {
    private ThemeListAdapter mAdapter;
    RecyclerView mRecyclerView;

    public ThemeListView(View view, Consumer<EffectTheme> consumer) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.theme_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
        ThemeListAdapter themeListAdapter = new ThemeListAdapter();
        this.mAdapter = themeListAdapter;
        themeListAdapter.setOnClickListener(consumer);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    public int getFocusedPosition() {
        ThemeListAdapter themeListAdapter = this.mAdapter;
        if (themeListAdapter != null) {
            return themeListAdapter.getFocusedPosition();
        }
        return 0;
    }

    public void handleResolutionChange() {
        ThemeListAdapter themeListAdapter = this.mAdapter;
        themeListAdapter.notifyItemRangeChanged(0, themeListAdapter.getItemCount(), "configuration_changed");
    }

    public void setFocus(String str) {
        this.mAdapter.setFocus(this.mRecyclerView, EffectTheme.findTheme(str));
    }

    public void setFocus(int i2) {
        if (i2 >= 0 && i2 < EffectTheme.values().length) {
            this.mAdapter.setFocus(this.mRecyclerView, EffectTheme.values()[i2]);
            this.mRecyclerView.scrollToPosition(i2);
        }
    }
}
