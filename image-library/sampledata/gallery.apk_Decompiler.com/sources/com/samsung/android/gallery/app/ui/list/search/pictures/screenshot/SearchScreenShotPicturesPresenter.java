package com.samsung.android.gallery.app.ui.list.search.pictures.screenshot;

import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.screenshot.ISearchScreenShotPicturesView;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchScreenShotPicturesPresenter<V extends ISearchScreenShotPicturesView> extends SearchPicturesPresenter<V> {
    public SearchScreenShotPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public String getTagName() {
        if (ScreenShotFilterType.All.key().equals(ArgumentsUtil.getArgValue(getLocationKey(), "sub", ""))) {
            return AppResources.getString(R.string.screenshot);
        }
        return super.getTagName();
    }

    public void replaceByLocationKey() {
        setLocationKeyOnly(((ISearchScreenShotPicturesView) this.mView).getLocationKey());
        forceReloadData();
    }
}
