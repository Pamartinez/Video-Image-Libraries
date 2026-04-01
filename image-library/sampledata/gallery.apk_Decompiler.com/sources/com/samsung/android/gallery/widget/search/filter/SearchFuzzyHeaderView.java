package com.samsung.android.gallery.widget.search.filter;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFuzzyHeaderView extends SearchHeaderView {
    private final Blackboard mBlackboard;
    private ViewGroup mFuzzyResultContainer;
    private TextView mFuzzyResultView;
    private String mFuzzyStr;

    public SearchFuzzyHeaderView(Context context, ViewGroup viewGroup, Blackboard blackboard) {
        super(context, viewGroup);
        this.mBlackboard = blackboard;
    }

    public static SearchFuzzyHeaderView cloneInstance(Context context, SearchFuzzyHeaderView searchFuzzyHeaderView) {
        return new SearchFuzzyHeaderView(context, searchFuzzyHeaderView);
    }

    private Spannable getSpannableFuzzyString(String str) {
        Resources resources = this.mFuzzyResultView.getResources();
        int i2 = R$string.fuzzy_showing_result_for;
        int indexOf = resources.getString(i2, new Object[]{Log.TAG_SEPARATOR}).indexOf(Log.TAG_SEPARATOR);
        String h5 = C0086a.h('\"', "\"", str);
        String string = this.mFuzzyResultView.getResources().getString(i2, new Object[]{h5});
        int length = h5.length() + indexOf;
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R$color.search_matched_text_color)), indexOf, length, 33);
        spannableString.setSpan(new TypefaceSpan(FontTypefaceUtils.TextFont.ROBOTO_SEMI_BOLD.getTypeface()), indexOf, length, 33);
        return spannableString;
    }

    private boolean isFuzzyResultShow(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static SearchFuzzyHeaderView newInstance(Context context, ViewGroup viewGroup, Blackboard blackboard) {
        return new SearchFuzzyHeaderView(context, viewGroup, blackboard);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mFuzzyResultContainer = (ViewGroup) view.findViewById(R$id.filter_results_fuzzy_container);
        this.mFuzzyResultView = (TextView) view.findViewById(R$id.filter_results_fuzzy);
    }

    public int getLayoutId() {
        return R$layout.search_fuzzy_header_layout;
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        return false;
    }

    public void showFuzzyTextOnly() {
        ViewUtils.setVisibleOrGone(getRootView(), true);
    }

    public void updateFuzzyResult(String str) {
        if (isFuzzyResultShow(str)) {
            this.mFuzzyStr = str;
            this.mFuzzyResultView.setText(getSpannableFuzzyString(str));
            ViewUtils.setVisibility(this.mFuzzyResultContainer, 0);
            return;
        }
        this.mFuzzyStr = null;
        ViewUtils.setVisibility(this.mFuzzyResultContainer, 8);
    }

    private SearchFuzzyHeaderView(Context context, SearchFuzzyHeaderView searchFuzzyHeaderView) {
        super(context, searchFuzzyHeaderView.getRootView());
        this.mBlackboard = searchFuzzyHeaderView.mBlackboard;
        if (ViewUtils.isVisible(searchFuzzyHeaderView.mFuzzyResultContainer) && !TextUtils.isEmpty(searchFuzzyHeaderView.mFuzzyStr)) {
            updateFuzzyResult(searchFuzzyHeaderView.mFuzzyStr);
            showFuzzyTextOnly();
        }
    }

    public void initHeaderItem() {
    }

    public void recycle() {
    }

    public void setEnabled(boolean z) {
    }
}
