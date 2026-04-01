package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.module.c2pa.C2paInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemC2pa extends DetailsItem implements DetailsViewUpdater {
    private static Boolean sShowDotIcon;
    private C2paInfo mC2paData;
    private View mCrView;
    private View mDotIconView;
    private boolean mLoaded = false;

    public DetailsItemC2pa(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    private ArrayAdapter<C2paInfo.C2paActionWrapper> createC2paEditActionAdapter(Context context, List<C2paInfo.C2paActionWrapper> list, LayoutInflater layoutInflater) {
        final Context context2 = context;
        final LayoutInflater layoutInflater2 = layoutInflater;
        return new ArrayAdapter<C2paInfo.C2paActionWrapper>(context, R.layout.c2pa_item_detail, list) {
            public View getView(int i2, View view, ViewGroup viewGroup) {
                boolean z;
                String str;
                String str2;
                int i7;
                C2paInfo.C2paActionWrapper c2paActionWrapper = (C2paInfo.C2paActionWrapper) getItem(i2);
                StringCompat stringCompat = DetailsItemC2pa.this.TAG;
                StringBuilder sb2 = new StringBuilder("getView pos ");
                sb2.append(i2);
                sb2.append("/");
                sb2.append(view);
                sb2.append("=");
                if (c2paActionWrapper != null) {
                    z = true;
                } else {
                    z = false;
                }
                sb2.append(z);
                Log.d(stringCompat, sb2.toString());
                String str3 = null;
                if (view == null) {
                    view = layoutInflater2.inflate(R.layout.c2pa_item_detail, (ViewGroup) null);
                }
                if (c2paActionWrapper == null || c2paActionWrapper.isInvalid) {
                    view.findViewById(R.id.detail_c2pa_valid).setVisibility(8);
                    view.findViewById(R.id.detail_c2pa_invalid).setVisibility(0);
                    return view;
                }
                view.findViewById(R.id.detail_c2pa_valid).setVisibility(0);
                view.findViewById(R.id.detail_c2pa_invalid).setVisibility(8);
                TextView textView = (TextView) view.findViewById(R.id.detail_c2pa_title);
                TextView textView2 = (TextView) view.findViewById(R.id.detail_c2pa_time);
                TextView textView3 = (TextView) view.findViewById(R.id.detail_c2pa_tool);
                TextView textView4 = (TextView) view.findViewById(R.id.detail_c2pa_issuer);
                TextView textView5 = (TextView) view.findViewById(R.id.detail_c2pa_exif);
                DetailsItemC2pa detailsItemC2pa = DetailsItemC2pa.this;
                String str4 = c2paActionWrapper.claimGenerator;
                if (str4 != null) {
                    Context context = context2;
                    if (!c2paActionWrapper.isCreated) {
                        i7 = R.string.c2pa_edited_with;
                    } else if (c2paActionWrapper.exif != null) {
                        i7 = R.string.c2pa_shot_on;
                    } else {
                        i7 = R.string.c2pa_create_with;
                    }
                    str = SeApiCompat.naturalizeText(context.getString(i7, new Object[]{str4}));
                } else {
                    str = null;
                }
                detailsItemC2pa.setTextAndVisibility(textView, str);
                DetailsItemC2pa.this.setTextAndVisibility(textView2, c2paActionWrapper.time);
                DetailsItemC2pa detailsItemC2pa2 = DetailsItemC2pa.this;
                if (!TextUtils.isEmpty(c2paActionWrapper.softwareAgent)) {
                    str2 = context2.getString(R.string.c2pa_tool_used, new Object[]{c2paActionWrapper.softwareAgent});
                } else {
                    str2 = null;
                }
                detailsItemC2pa2.setTextAndVisibility(textView3, str2);
                DetailsItemC2pa detailsItemC2pa3 = DetailsItemC2pa.this;
                String str5 = c2paActionWrapper.issuer;
                if (str5 != null) {
                    str3 = context2.getString(R.string.c2pa_issuer, new Object[]{str5});
                }
                detailsItemC2pa3.setTextAndVisibility(textView4, str3);
                DetailsItemC2pa.this.setTextAndVisibility(textView5, c2paActionWrapper.getExif());
                return view;
            }
        };
    }

    /* access modifiers changed from: private */
    public View getC2paDetailOverlayView() {
        List<C2paInfo.C2paActionWrapper> actionList = this.mC2paData.getActionList();
        if (actionList.isEmpty()) {
            return null;
        }
        Context context = this.mEventContext.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        View inflate = layoutInflater.inflate(R.layout.c2pa_detail_view_layout, (ViewGroup) null);
        ((ListView) inflate.findViewById(R.id.c2pa_edit_list)).setAdapter(createC2paEditActionAdapter(context, actionList, layoutInflater));
        setToolbar(inflate);
        return inflate;
    }

    private void initC2paDotIcon() {
        if (sShowDotIcon == null) {
            sShowDotIcon = Boolean.valueOf(PreferenceCache.C2paBadge.getBoolean());
        }
        if (!sShowDotIcon.booleanValue() || !ViewUtils.isVisible(this.mCrView)) {
            Optional.ofNullable(this.mDotIconView).ifPresent(new e(2));
            this.mDotIconView = null;
        } else if (this.mDotIconView == null) {
            View view = getView(this.mItemView, (int) R.id.moreinfo_more_c2pa_noti_dot);
            this.mDotIconView = view;
            view.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewInflate$0(View view) {
        onMoreC2paInfoClicked();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setToolbar$2(View view) {
        BlackboardUtils.publishBackKeyEvent(this.mBlackboard);
    }

    private void onMoreC2paInfoClicked() {
        removeC2paDotIcon();
        if (!this.mC2paData.getActionList().isEmpty()) {
            this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(3071, new Object[]{new C0450d(this), OverlayViewState.show, Boolean.FALSE}));
        }
    }

    private void removeC2paDotIcon() {
        if (sShowDotIcon.booleanValue()) {
            PreferenceCache.C2paBadge.setBoolean(false);
            sShowDotIcon = Boolean.FALSE;
            Optional.ofNullable(this.mDotIconView).ifPresent(new e(1));
            this.mDotIconView = null;
        }
    }

    private void setToolbar(View view) {
        GalleryToolbar galleryToolbar = (GalleryToolbar) view.findViewById(R.id.overlay_toolbar);
        if (galleryToolbar != null) {
            galleryToolbar.setNavigationOnClickListener(new f(this, 0));
            ViewUtils.resetAccessibilityFocus(galleryToolbar.getNaviUpButton());
        }
    }

    public int getLayoutId() {
        return R.id.moreinfo_c2pa;
    }

    public void onRecycled() {
        this.mLoaded = false;
        this.mC2paData = null;
        super.onRecycled();
    }

    public void onResume() {
        super.onResume();
        initC2paDotIcon();
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        View view2 = getView(view, (int) R.id.moreinfo_more_c2pa);
        this.mCrView = view2;
        view2.setOnClickListener(new f(this, 1));
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.C2PA, this);
    }

    public void show() {
        if (this.mLoaded) {
            C2paInfo c2paInfo = this.mC2paData;
            if (c2paInfo == null || c2paInfo.getRepresentActionString(this.mEventContext.getContext()).isEmpty()) {
                ViewUtils.setVisibility(this.mCrView, 8);
                Optional.ofNullable(this.mDotIconView).ifPresent(new e(0));
            } else {
                ViewUtils.setVisibility(this.mCrView, 0);
            }
            super.show();
        }
    }

    public boolean supportItem(MediaItem mediaItem) {
        return true;
    }

    public void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        Object obj;
        this.mLoaded = true;
        Object[] objArr = detailsLoadResult.extras;
        if (objArr == null || (obj = objArr[0]) == null) {
            setTextAndVisibility((int) R.id.moreinfo_c2pa_title, (int) R.string.c2pa_contains_ai_generated_content);
            show();
            return;
        }
        C2paInfo c2paInfo = (C2paInfo) obj;
        this.mC2paData = c2paInfo;
        String representActionString = c2paInfo.getRepresentActionString(this.mEventContext.getContext());
        if (!representActionString.isEmpty()) {
            setTextAndVisibility((int) R.id.moreinfo_c2pa_title, (CharSequence) representActionString);
            show();
        }
    }
}
