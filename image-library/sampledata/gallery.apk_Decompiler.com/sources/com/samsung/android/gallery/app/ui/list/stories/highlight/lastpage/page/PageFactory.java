package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PageFactory {
    public static View createCollagePageView(ViewGroup viewGroup) {
        return createPageView(viewGroup, R.layout.story_highlight_lastpage_collage_layout);
    }

    public static PageHolder createPage(IStoryHighlightView iStoryHighlightView, ViewGroup viewGroup, View view, int i2) {
        switch (i2) {
            case 0:
                return new GridCollagePage(createCollagePageView(viewGroup), iStoryHighlightView, view);
            case 1:
                return new IrregularCollagePage(createCollagePageView(viewGroup), iStoryHighlightView, view);
            case 2:
                return new RelatedPage(createPageView(viewGroup, R.layout.story_highlight_lastpage_related_layout), iStoryHighlightView, view);
            case 3:
                return new MapPage(createPageView(viewGroup, R.layout.story_highlight_lastpage_map_layout), iStoryHighlightView, view);
            case 4:
                return new SaveStoryPage(createPageView(viewGroup, R.layout.story_highlight_lastpage_save_story_layout), iStoryHighlightView, view);
            case 5:
                return new OnDemandPage(createPageView(viewGroup, R.layout.story_highlight_lastpage_on_demand_layout), iStoryHighlightView, view);
            case 6:
                return new SaveWithRewriteStoryPage(createPageView(viewGroup, R.layout.story_highlight_lastpage_save_with_rewrite_story_layout), iStoryHighlightView, view);
            case 7:
                return new RecapCoverPage(createPageView(viewGroup, R.layout.story_recap_lastpage_cover_layout), iStoryHighlightView, view);
            case 8:
                return new SaveWithReplayPage(createPageView(viewGroup, R.layout.story_lastpage_save_with_replay_layout), iStoryHighlightView, view);
            default:
                return new PageHolder(createPageView(viewGroup, R.layout.story_highlight_lastpage_empty_layout), iStoryHighlightView, view);
        }
    }

    private static View createPageView(ViewGroup viewGroup, int i2) {
        return C0086a.d(viewGroup, i2, viewGroup, false);
    }
}
