package com.samsung.android.gallery.app.ui.viewer2.container.delegate.film;

import B7.d;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.widget.filmstrip3.BitmapPreLoaderByFilmSnap;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripSnapHelper;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripSnapDelegate extends AbsVuDelegate<IVuContainerView> {
    protected FilmStripView mFilmStripView;

    public FilmStripSnapDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void bindFilmStripView(View view) {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            filmStripView.setOnFindTargetSnapPosition((FilmStripSnapHelper.OnFindTargetSnapPosition) null);
        }
        FilmStripView filmStripView2 = (FilmStripView) view.findViewById(R.id.film_strip_view);
        this.mFilmStripView = filmStripView2;
        filmStripView2.setOnFindTargetSnapPosition(new BitmapPreLoaderByFilmSnap(this.mBlackboard, filmStripView2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        bindFilmStripView(objArr[0]);
    }

    public void onBindView(View view) {
        bindFilmStripView(view);
    }

    public void onDestroy() {
        this.mFilmStripView.setOnFindTargetSnapPosition((FilmStripSnapHelper.OnFindTargetSnapPosition) null);
        this.mFilmStripView = null;
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.FILM_STRIP_LAYOUT_REPLACED, new d(22, this));
    }
}
