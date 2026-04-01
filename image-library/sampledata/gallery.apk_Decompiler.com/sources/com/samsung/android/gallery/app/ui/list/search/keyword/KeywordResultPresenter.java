package com.samsung.android.gallery.app.ui.list.search.keyword;

import com.samsung.android.gallery.app.ui.list.search.keyword.IKeywordResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.stream.Collectors;
import q8.a;
import qa.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeywordResultPresenter<V extends IKeywordResultView> extends SearchPicturesPresenter<V> {
    public KeywordResultPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private boolean hasVisibleMenuItems() {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding == null || !menuDataBinding.hasVisibleItems()) {
            return false;
        }
        return true;
    }

    private void onFilterResultChanged(FilterResultsEntry filterResultsEntry) {
        if (filterResultsEntry.isEmpty() || !supportLoadStoryData()) {
            ((IKeywordResultView) this.mView).storyDataLoaded("");
        } else {
            ((IKeywordResultView) this.mView).storyDataLoaded((String) filterResultsEntry.getAllItems().stream().filter(new e(1)).map(new a(2)).collect(Collectors.joining("','", "'", "'")));
        }
    }

    private boolean supportLoadStoryData() {
        return "key_word".equals(ArgumentsUtil.getArgValue(getLocationKey(), "term"));
    }

    public boolean checkOptionMenuEnabled() {
        if (!super.checkOptionMenuEnabled() || !((IKeywordResultView) this.mView).supportMenu() || !hasVisibleMenuItems()) {
            return false;
        }
        return true;
    }

    public void clearMenu() {
        super.clearMenu();
        getActivity().removeMenuProvider(this);
    }

    public MenuDataBinding createMenuDataBinding() {
        if (((IKeywordResultView) this.mView).supportMenu()) {
            return super.createMenuDataBinding();
        }
        return null;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1065) {
            onFilterResultChanged((FilterResultsEntry) eventMessage.obj);
            return true;
        } else if (i2 != 8004) {
            return super.handleEvent(eventMessage);
        } else {
            handleFilterChanged();
            return true;
        }
    }

    public void handleFilterChanged() {
        if (((IKeywordResultView) this.mView).isViewActive()) {
            ((IKeywordResultView) this.mView).setPendingLayoutChange();
        }
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        V v = this.mView;
        ((IKeywordResultView) v).setContainerVisibility(!((IKeywordResultView) v).isEmptyViewShowing());
    }

    public void onPicturesViewAllClicked() {
        getBlackboard().post("command://MoveURL", new UriBuilder(((IKeywordResultView) this.mView).getLocationKey().replace("location://search/fileList/Keyword", "location://search/fileList/pictures_only/Keyword")).appendArg("search_keyword_pictures_only", true).build());
    }

    public void reopenData() {
        reopenData(getLocationKey());
    }

    public void updateMenu() {
        clearMenu();
        if (((IKeywordResultView) this.mView).supportMenu()) {
            initMenu();
        }
    }
}
