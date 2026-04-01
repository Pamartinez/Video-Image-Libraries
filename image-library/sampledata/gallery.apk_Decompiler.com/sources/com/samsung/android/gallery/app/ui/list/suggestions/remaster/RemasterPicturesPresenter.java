package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.IRemasterView;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterPicturesPresenter<V extends IRemasterView> extends BaseListPresenter<V> {
    public RemasterPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) null);
        setNavigationUpButton(toolbar);
    }
}
