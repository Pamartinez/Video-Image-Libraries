package com.samsung.android.gallery.app.ui.dialog;

import android.content.DialogInterface;
import android.view.View;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.module.dialog.ErrorType;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.widget.TextInputLayoutCompat;
import com.samsung.android.gallery.widget.flexboxlist.FlexBoxListView;
import com.samsung.android.gallery.widget.search.filter.FilterSubListViewAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMyQueryDialog extends CreateNameDialog {
    private FlexBoxListView mListView;

    private void initListView() {
        ArrayList arrayList = (ArrayList) getBlackboard().read("data://user/SearchToolbarSelectedFilters");
        if (arrayList != null) {
            this.mListView.setLayoutManager(new FlexboxLayoutManager(getContext()));
            AnonymousClass1 r1 = new FilterSubListViewAdapter(getBlackboard(), false) {
                public void initViewHolderMargin(SearchFiltersViewHolder searchFiltersViewHolder) {
                    super.initViewHolderMargin(searchFiltersViewHolder);
                    ViewMarginUtils.setEndMargin(searchFiltersViewHolder.itemView, searchFiltersViewHolder.itemView.getResources().getDimensionPixelOffset(R.dimen.search_category_shortcut_dialog_keyword_margin_end));
                }
            };
            r1.setData(arrayList);
            this.mListView.setAdapter(r1);
        }
    }

    private void publishInternal(String str) {
        getBlackboard().post("data://user/dialog/MyQueryName", new Object[]{str, Boolean.TRUE});
    }

    public void bindViews(View view) {
        super.bindViews(view);
        this.mListView = (FlexBoxListView) view.findViewById(R.id.list_view);
    }

    public String getHint() {
        return getString(R.string.enter_shortcut_name);
    }

    public int getLayoutId() {
        return R.layout.alert_dialog_create_my_query;
    }

    public int getMaxLength() {
        return 30;
    }

    public int getPositiveButtonResource() {
        return R.string.add;
    }

    public String getTitle() {
        return getString(R.string.add_quick_search);
    }

    public void initDialog() {
        super.initDialog();
        initListView();
        TextInputLayoutCompat textInputLayoutCompat = this.mTextInputCompat;
        if (textInputLayoutCompat != null) {
            textInputLayoutCompat.setEnabledInputLayoutHint(false);
        }
    }

    public ErrorType isValid(String str) {
        if (str.contains("\n")) {
            str = str.replaceAll("\n", " ");
        }
        if (MyQueryUtil.getMyQueryName().contains(str)) {
            return ErrorType.SHORTCUT_NAME_ALREADY_IN_USE;
        }
        return ErrorType.NONE;
    }

    public void onCancel(DialogInterface dialogInterface) {
        getBlackboard().post("data://user/dialog/MyQueryName", (Object) null);
    }

    public void publishData(String str) {
        publishInternal(str);
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_ADD_SEARCH_SHORTCUTS);
    }

    public void publishCancel() {
    }
}
