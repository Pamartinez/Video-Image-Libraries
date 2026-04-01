package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.DefaultThemeDelegate;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DefaultThemeDelegate e;

    public /* synthetic */ l(DefaultThemeDelegate defaultThemeDelegate, int i2) {
        this.d = i2;
        this.e = defaultThemeDelegate;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        DefaultThemeDelegate defaultThemeDelegate = this.e;
        Object[] objArr = (Object[]) obj;
        switch (i2) {
            case 0:
                defaultThemeDelegate.checkAndUpdateTheme(objArr);
                return;
            case 1:
                defaultThemeDelegate.updateLastUsedBgm(objArr);
                return;
            case 2:
                defaultThemeDelegate.notifyGlobalThemeChanged(objArr);
                return;
            case 3:
                defaultThemeDelegate.recoverStoryBgm(objArr);
                return;
            case 4:
                defaultThemeDelegate.resetTheme(objArr);
                return;
            case 5:
                defaultThemeDelegate.changeTheme(objArr);
                return;
            case 6:
                defaultThemeDelegate.changeFilter(objArr);
                return;
            default:
                defaultThemeDelegate.changeBgm(objArr);
                return;
        }
    }
}
