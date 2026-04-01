package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISummaryPage {
    void bindTitle(TextView textView, TextView textView2);

    Activity getActivity();

    int getSidePadding(PageSpec.Config config);

    void updateContentParentLayout(View view, PageSpec.Config config);

    void updateSubTitleLayout(TextView textView, PageSpec.Config config);

    void updateTitleArea(View view, PageSpec.Config config, int i2);

    void updateTitleLayout(TextView textView, PageSpec.Config config);

    void updateViewSize(View view, PageSpec.Config config, int i2);
}
