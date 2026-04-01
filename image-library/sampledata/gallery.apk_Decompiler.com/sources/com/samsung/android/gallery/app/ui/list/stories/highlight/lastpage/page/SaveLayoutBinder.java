package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.ISummaryPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;
import o4.a;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SaveLayoutBinder<V extends ISummaryPage> {
    private final Activity mActivity;
    protected final V mCollagePage;
    protected View mContentParent;
    private Consumer<View> mPreparedListener;

    public SaveLayoutBinder(V v) {
        ThreadUtil.assertUiThread("SaveLayoutBinder should run on main thread");
        Log.d("SaveLayoutBinder", "create");
        this.mActivity = v.getActivity();
        this.mCollagePage = v;
        initView();
    }

    private void destroy() {
        ViewUtils.removeSelf(this.mContentParent);
        this.mPreparedListener = null;
        Log.d("SaveLayoutBinder", "destroy");
    }

    private void initCollageContent(View view, PageSpec.Config config) {
        this.mCollagePage.updateContentParentLayout(view, config);
        ViewUtils.setViewSize(view, Integer.valueOf(config.width), Integer.valueOf((int) (((float) config.width) / PageSpec.getCollageContentRatio())));
    }

    private void initTitle(View view, PageSpec.Config config, int i2) {
        View findViewById = view.findViewById(R.id.title_area);
        TextView textView = (TextView) view.findViewById(R.id.title);
        TextView textView2 = (TextView) view.findViewById(R.id.subtitle);
        this.mCollagePage.bindTitle(textView, textView2);
        this.mCollagePage.updateTitleArea(findViewById, config, i2);
        this.mCollagePage.updateTitleLayout(textView, config);
        this.mCollagePage.updateSubTitleLayout(textView2, config);
    }

    private void initView() {
        Log.d("SaveLayoutBinder", "initView");
        View findViewById = PageFactory.createCollagePageView(getDecorView()).findViewById(R.id.content_parent);
        this.mContentParent = findViewById;
        ViewUtils.removeSelf(findViewById);
        ViewUtils.setAlpha(this.mContentParent, 0.0f);
        getDecorView().addView(this.mContentParent);
        PageSpec.Config configForSave = PageSpec.getConfigForSave(1080);
        int sidePadding = this.mCollagePage.getSidePadding(configForSave);
        initCollageContent(this.mContentParent, configForSave);
        initTitle(this.mContentParent, configForSave, sidePadding);
        initViewInternal(this.mContentParent, configForSave, sidePadding);
        ThreadUtil.postOnUiThreadDelayed(new t(19, this), 250);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(Consumer consumer) {
        consumer.accept(this.mContentParent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$1() {
        Optional.ofNullable(this.mPreparedListener).ifPresent(new a(11, this));
        destroy();
    }

    public ViewGroup getDecorView() {
        return (ViewGroup) this.mActivity.getWindow().getDecorView();
    }

    public abstract void initViewInternal(View view, PageSpec.Config config, int i2);

    public void setPreparedListener(Consumer<View> consumer) {
        this.mPreparedListener = consumer;
    }
}
