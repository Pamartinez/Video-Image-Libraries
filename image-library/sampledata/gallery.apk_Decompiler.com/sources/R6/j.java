package r6;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.SaveWithRewriteStoryPage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SaveWithRewriteStoryPage e;

    public /* synthetic */ j(SaveWithRewriteStoryPage saveWithRewriteStoryPage, int i2) {
        this.d = i2;
        this.e = saveWithRewriteStoryPage;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        SaveWithRewriteStoryPage saveWithRewriteStoryPage = this.e;
        switch (i2) {
            case 0:
                saveWithRewriteStoryPage.onSaveAsClicked(view);
                return;
            default:
                saveWithRewriteStoryPage.onRewritePromptClicked(view);
                return;
        }
    }
}
