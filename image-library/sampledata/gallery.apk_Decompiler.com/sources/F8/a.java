package F8;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.controller.album.AlbumMoveCmd;
import com.samsung.android.gallery.app.controller.internals.SavePortraitEffectCmd;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItemViewHolder;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.ThemeUpdater;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.shotmode.ShotModeHandler;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.settings.activity.BasePreferenceActivity;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import com.samsung.android.gallery.widget.dialog.MissingPackage;
import com.samsung.android.gallery.widget.search.filter.AbsFiltersAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewHolder;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2800h;

    public /* synthetic */ a(BasePreferenceActivity basePreferenceActivity, Consumer consumer, boolean z, String str) {
        this.d = 8;
        this.g = basePreferenceActivity;
        this.f2800h = consumer;
        this.f = z;
        this.e = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BgmUriService) this.g).lambda$onDownloaded$3(this.f, (String) this.e, (ArrayList) this.f2800h);
                return;
            case 1:
                ((SettingFragment) this.g).lambda$updateStoryFeatureState$33((String) this.e, this.f, (Consumer) this.f2800h);
                return;
            case 2:
                ((ShotModeHandler) this.g).lambda$inflateExtraButtonView$11(this.f, (View) this.e, (View) this.f2800h);
                return;
            case 3:
                ((AlbumMoveCmd) this.g).lambda$onExecute$0(this.f, (MediaItem[]) this.e, (MediaItem) this.f2800h);
                return;
            case 4:
                ((SavePortraitEffectCmd) this.g).lambda$onExecute$1((MediaItem) this.e, (Bundle) this.f2800h, this.f);
                return;
            case 5:
                ((AbsFiltersAdapter) this.g).lambda$bindThumbnailTypeViewHolder$1((FilterResultsEntity) this.e, (SearchFiltersViewHolder) this.f2800h, this.f);
                return;
            case 6:
                ((StoriesOnDemandDelegate) this.g).lambda$startOnDemand$2((String) this.e, this.f, (String) this.f2800h);
                return;
            case 7:
                CollageItemPicker.lambda$pick$0((Consumer) this.g, this.f, (CollageInfo) this.e, (CollageInfo) this.f2800h);
                return;
            case 8:
                ((BasePreferenceActivity) this.g).lambda$checkValidPreference$4((Consumer) this.f2800h, this.f, (String) this.e);
                return;
            case 9:
                ((DrawerTabViewAdapter) this.g).lambda$setSuggestionBadge$7((DrawerTabItem) this.e, this.f, (DrawerTabItemViewHolder) this.f2800h);
                return;
            case 10:
                new MissingPackage.DownloadBuilder().setPackage((String) this.e, (String) this.g).setUpdateRequired(this.f).build((Context) this.f2800h).show();
                return;
            default:
                ThemeUpdater.lambda$updateFilterToDb$2((MediaItem) this.g, this.f, (String) this.e, (Filter) this.f2800h);
                return;
        }
    }

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, boolean z, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = obj2;
        this.f2800h = obj3;
        this.f = z;
    }

    public /* synthetic */ a(Object obj, Object obj2, boolean z, Object obj3, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = obj2;
        this.f = z;
        this.f2800h = obj3;
    }

    public /* synthetic */ a(Object obj, boolean z, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.g = obj;
        this.f = z;
        this.e = obj2;
        this.f2800h = obj3;
    }

    public /* synthetic */ a(String str, String str2, boolean z, Context context) {
        this.d = 10;
        this.e = str;
        this.g = str2;
        this.f = z;
        this.f2800h = context;
    }
}
