package com.samsung.android.gallery.app.ui.container.menu;

import M3.C0406b;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartVideoStudioCmd;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuFactory;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem;
import com.samsung.android.gallery.app.ui.container.tablet.bottomtab.BottomTabController;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.badge.UsbBadgeManager;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.ai.language.a;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;
import k4.C0475a;
import k4.C0476b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabMenu {
    private final boolean BOTTOM_TAB_BLUR_GUI = PreferenceFeatures.OneUi8x.IS_ONE_UI_80;
    private AlertDialog mDialog;
    private final EventContext mEventContext;
    private RecyclerView mListView;
    private BottomTabMenuClickListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMenuCircleViewHolder extends BottomMenuViewHolder {
        public BottomMenuCircleViewHolder(View view) {
            super(view);
        }

        public static BottomMenuCircleViewHolder create(Context context) {
            return new BottomMenuCircleViewHolder(LayoutInflater.from(context).inflate(R.layout.bottom_menu_list_circle_view_holder, (ViewGroup) null, false));
        }

        public void bindView(View view) {
            int i2;
            super.bindView(view);
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                View findViewById = view.findViewById(R.id.item_bg);
                if (BottomTabMenu.isBlurEnabled()) {
                    i2 = R.drawable.bottom_menu_list_circle_bg;
                } else {
                    i2 = R.drawable.bottom_menu_list_none_blur_circle_bg;
                }
                findViewById.setBackgroundResource(i2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMenuListAdapter extends RecyclerView.Adapter<BottomMenuViewHolder> {
        private final WeakReference<Context> mContextRef;
        final SubscriberListener mListener;
        private final ArrayList<BottomMenuItem> mMenuList;
        private View.OnClickListener mOnClickListener;
        private UsbBadgeManager mUsbBadgeManager;

        public BottomMenuListAdapter(Context context) {
            c cVar = new c(this);
            this.mListener = cVar;
            this.mContextRef = new WeakReference<>(context);
            this.mMenuList = BottomMenuFactory.create(context);
            if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
                Blackboard.getApplicationInstance().subscribeOnUi("event/UsbStorageVolumeChanged", cVar);
                UsbBadgeManager usbBadgeManager = new UsbBadgeManager();
                this.mUsbBadgeManager = usbBadgeManager;
                usbBadgeManager.loadTable();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onUsbStorageChanged$0(int i2) {
            return this.mMenuList.get(i2) instanceof BottomMenuItem.UsbStorage;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onUsbStorageChanged$1(int i2) {
            return this.mMenuList.get(i2) instanceof BottomMenuItem.UsbStorage;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$updateNewBadge$4(BottomMenuViewHolder bottomMenuViewHolder) {
            if (BadgeHelper.hasNewSuggestions()) {
                ThreadUtil.postOnUiThread(new f(bottomMenuViewHolder, 1));
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: onItemClicked */
        public void lambda$onBindViewHolder$2(BottomMenuItem bottomMenuItem, View view) {
            if (bottomMenuItem instanceof BottomMenuItem.UsbStorage) {
                BottomMenuItem.UsbStorage usbStorage = (BottomMenuItem.UsbStorage) bottomMenuItem;
                UsbBadgeManager usbBadgeManager = this.mUsbBadgeManager;
                if (usbBadgeManager != null) {
                    usbBadgeManager.use(usbStorage.volumeCompat.name);
                }
            }
            View.OnClickListener onClickListener = this.mOnClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        /* access modifiers changed from: private */
        public void onUsbStorageChanged(Object obj, Bundle bundle) {
            Context context = this.mContextRef.get();
            if (context != null) {
                int orElse = IntStream.range(0, this.mMenuList.size()).filter(new e(this, 0)).findFirst().orElse(-1);
                ArrayList<BottomMenuItem> create = BottomMenuFactory.create(context);
                int orElse2 = IntStream.range(0, this.mMenuList.size()).filter(new e(this, 1)).findFirst().orElse(-1);
                if (orElse2 >= 0) {
                    orElse = orElse2;
                }
                this.mMenuList.clear();
                this.mMenuList.addAll(create);
                this.mUsbBadgeManager.loadTable();
                notifyItemRangeChanged(Math.max(0, orElse), this.mMenuList.size());
            }
        }

        private void updateNewBadge(BottomMenuViewHolder bottomMenuViewHolder, BottomMenuItem bottomMenuItem) {
            if (bottomMenuItem == BottomMenuFactory.MenuHolder.SUGGESTIONS) {
                ThreadUtil.postOnBgThread(new f(bottomMenuViewHolder, 0));
            } else if (bottomMenuItem == BottomMenuFactory.MenuHolder.SHARED && BadgeHelper.hasNewSharedAlbums()) {
                bottomMenuViewHolder.updateNewBadge(R.string.new_content_available);
            } else if (bottomMenuItem == BottomMenuFactory.MenuHolder.SETTINGS && BadgeHelper.hasNewSettings()) {
                bottomMenuViewHolder.updateNewBadge(R.string.newly_added);
            } else if ((bottomMenuItem instanceof BottomMenuItem.UsbStorage) && !this.mUsbBadgeManager.isUsed(((BottomMenuItem.UsbStorage) bottomMenuItem).volumeCompat.name)) {
                bottomMenuViewHolder.updateNewBadge(R.string.newly_added);
            }
        }

        public void destroy() {
            this.mContextRef.clear();
            if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
                Blackboard.getApplicationInstance().unsubscribe("event/UsbStorageVolumeChanged", this.mListener);
            }
        }

        public int getItemCount() {
            return this.mMenuList.size();
        }

        public int getItemViewType(int i2) {
            if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                return 0;
            }
            if (i2 >= BottomTabItemDecorationFactory.BOTTOM_MENU_CIRCLE_VIEW_SLOT) {
                return 2;
            }
            if (this.mMenuList.size() <= i2 || !(this.mMenuList.get(i2) instanceof BottomMenuItem.Dummy)) {
                return 1;
            }
            return -5;
        }

        public BottomMenuListAdapter setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            return this;
        }

        public void onBindViewHolder(BottomMenuViewHolder bottomMenuViewHolder, int i2) {
            BottomMenuItem bottomMenuItem = this.mMenuList.get(i2);
            bottomMenuViewHolder.bind(bottomMenuItem);
            bottomMenuViewHolder.itemView.setTag(bottomMenuItem);
            bottomMenuViewHolder.itemView.setOnClickListener(new d(this, bottomMenuItem));
            updateNewBadge(bottomMenuViewHolder, bottomMenuItem);
        }

        public BottomMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                return BottomMenuViewHolder.create(viewGroup.getContext());
            }
            if (i2 == 1) {
                return BottomMenuCircleViewHolder.create(viewGroup.getContext());
            }
            if (i2 == -5) {
                return BottomMenuDummyCircleViewHolder.create(viewGroup.getContext());
            }
            return BottomMenuRoundedViewHolder.create(viewGroup.getContext());
        }

        public void onViewRecycled(BottomMenuViewHolder bottomMenuViewHolder) {
            super.onViewRecycled(bottomMenuViewHolder);
            bottomMenuViewHolder.recycled();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMenuRoundedViewHolder extends BottomMenuViewHolder implements View.OnLayoutChangeListener {
        private int mLayoutOrientation = 0;
        private View mTextLayout;
        private final int minWidth;

        public BottomMenuRoundedViewHolder(View view) {
            super(view);
            this.minWidth = view.getResources().getDimensionPixelSize(R.dimen.bottom_menu_dialog_min_width);
        }

        public static BottomMenuRoundedViewHolder create(Context context) {
            return new BottomMenuRoundedViewHolder(LayoutInflater.from(context).inflate(R.layout.bottom_menu_list_rounded_view_holder, (ViewGroup) null, false));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLayoutChange$0(View view, int i2, int i7) {
            int i8;
            View view2 = (View) view.getParent();
            if (view2 != null) {
                if (view2.getMeasuredWidth() <= this.minWidth) {
                    i8 = 1;
                } else {
                    i8 = 0;
                }
                if (i8 != this.mLayoutOrientation || (i2 == 0 && i7 == 0)) {
                    this.mLayoutOrientation = i8;
                    ((LinearLayout) view).setOrientation(i8);
                    updateLayout();
                }
            }
        }

        private void updateLayout() {
            boolean z;
            int i2 = 0;
            if (this.mLayoutOrientation == 0) {
                z = true;
            } else {
                z = false;
            }
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.bottom_menu_list_rounded_item_horizontal_max_margin);
            int dimensionPixelSize2 = this.itemView.getResources().getDimensionPixelSize(R.dimen.bottom_menu_list_rounded_item_horizontal_min_margin);
            int dimensionPixelSize3 = this.itemView.getResources().getDimensionPixelSize(R.dimen.bottom_menu_list_rounded_item_padding_vertical);
            if (z) {
                ViewMarginUtils.setStartMargin(this.mImageView, dimensionPixelSize);
                ViewMarginUtils.setStartMargin(this.mTextLayout, 0);
                View view = this.mTextLayout;
                if (ViewUtils.isVisible(this.mNewBadge)) {
                    dimensionPixelSize = dimensionPixelSize2;
                }
                ViewMarginUtils.setEndMargin(view, dimensionPixelSize);
                ViewMarginUtils.setVerticalPadding(this.mImageView, dimensionPixelSize3, dimensionPixelSize3);
            } else {
                ViewMarginUtils.setStartMargin(this.mImageView, 0);
                ViewMarginUtils.setHorizontalMargin(this.mTextLayout, dimensionPixelSize2);
                ViewMarginUtils.setVerticalPadding(this.mImageView, dimensionPixelSize3 / 2, 0);
            }
            TextView textView = this.mTextView;
            if (z) {
                i2 = this.itemView.getResources().getDimensionPixelSize(R.dimen.bottom_menu_list_rounded_item_margin_start);
            }
            ViewMarginUtils.setStartMargin(textView, i2);
        }

        public void bind(BottomMenuItem bottomMenuItem) {
            super.bind(bottomMenuItem);
            this.itemView.addOnLayoutChangeListener(this);
        }

        public void bindView(View view) {
            int i2;
            super.bindView(view);
            this.mTextLayout = this.itemView.findViewById(R.id.bottom_menu_list_text_layout);
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                View view2 = this.itemView;
                if (BottomTabMenu.isBlurEnabled()) {
                    i2 = R.drawable.bottom_menu_list_item_rounded_recoil_ripple_bg;
                } else {
                    i2 = R.drawable.bottom_menu_list_none_blur_item_rounded_recoil_ripple_bg;
                }
                view2.setBackgroundResource(i2);
                this.itemView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                        int layoutPosition = BottomMenuRoundedViewHolder.this.getLayoutPosition() - 4;
                        accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain((layoutPosition / 2) + 1, 1, layoutPosition % 2, 1, false, false));
                    }
                });
            }
        }

        public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
            if (i13 - i11 != i8 - i2 && view.getParent() != null) {
                ViewUtils.post((View) view.getParent(), new g(this, view, i13, i11));
            }
        }

        public void recycled() {
            super.recycled();
            this.itemView.removeOnLayoutChangeListener(this);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BottomTabMenuClickListener {
    }

    public BottomTabMenu(EventContext eventContext) {
        this.mEventContext = eventContext;
    }

    private View createDialogView(Context context) {
        View view = getView(context);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottom_menu_list_view);
        this.mListView = recyclerView;
        if (recyclerView != null) {
            BottomMenuListAdapter onClickListener = new BottomMenuListAdapter(context).setOnClickListener(new C0475a(this, 0));
            this.mListView.setLayoutManager(getGridLayoutManager(context, onClickListener));
            this.mListView.setAdapter(onClickListener);
            this.mListView.addItemDecoration(BottomTabItemDecorationFactory.createDecoration());
        }
        if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            createVideoStudioView(view);
        }
        return view;
    }

    private void createVideoStudioView(View view) {
        if (Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO) && PocFeatures.isEnabled(PocFeatures.GotoStudioMenuEnabled)) {
            Optional.ofNullable(view.findViewById(R.id.video_studio_button)).ifPresent(new C0476b(this, 1));
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.function.Consumer, java.lang.Object] */
    private void destroy() {
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            Optional.ofNullable((BottomMenuListAdapter) recyclerView.getAdapter()).ifPresent(new Object());
            this.mListView.setAdapter((RecyclerView.Adapter) null);
            this.mListView = null;
        }
    }

    private GridLayoutManager getGridLayoutManager(Context context, final BottomMenuListAdapter bottomMenuListAdapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        if (this.BOTTOM_TAB_BLUR_GUI) {
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i2) {
                    if (bottomMenuListAdapter.getItemViewType(i2) == 1 || bottomMenuListAdapter.getItemViewType(i2) == -5) {
                        return 1;
                    }
                    return 2;
                }
            });
        }
        return gridLayoutManager;
    }

    private String getScreenId() {
        String currentLocation = LocationKey.getCurrentLocation();
        currentLocation.getClass();
        char c5 = 65535;
        switch (currentLocation.hashCode()) {
            case -212479357:
                if (currentLocation.equals("location://story/albums")) {
                    c5 = 0;
                    break;
                }
                break;
            case -125579287:
                if (currentLocation.equals("location://albums")) {
                    c5 = 1;
                    break;
                }
                break;
            case 263612166:
                if (currentLocation.equals("location://timeline")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return AnalyticsScreenId.SCREEN_EVENT_VIEW_NORMAL.toString();
            case 1:
                return AnalyticsScreenId.SCREEN_ALBUM_VIEW_NORMAL.toString();
            case 2:
                return AnalyticsScreenId.SCREEN_TIME_VIEW_NORMAL.toString();
            default:
                return null;
        }
    }

    private View getView(Context context) {
        int i2;
        LayoutInflater from = LayoutInflater.from(context);
        if (this.BOTTOM_TAB_BLUR_GUI) {
            i2 = R.layout.bottom_menu_list_transparent_dialog;
        } else {
            i2 = R.layout.bottom_menu_list_dialog;
        }
        return from.inflate(i2, (ViewGroup) null);
    }

    /* access modifiers changed from: private */
    public static boolean isBlurEnabled() {
        if (Features.isEnabled(Features.IS_THEME_INSTALLED) || Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY) || !Features.isEnabled(Features.SUPPORT_REALTIME_BLUR)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createVideoStudioView$0(View view) {
        int i2;
        if (this.BOTTOM_TAB_BLUR_GUI) {
            if (isBlurEnabled()) {
                i2 = R.drawable.video_studio_button_recoil_ripple_oneui8;
            } else {
                i2 = R.drawable.video_studio_button_none_blur_recoil_ripple_oneui8;
            }
            view.setBackgroundResource(i2);
        }
        ((TextView) view.findViewById(R.id.video_studio_button_title)).setText(SeApiCompat.naturalizeText(view.getContext().getString(R.string.video_studio_title, new Object[]{view.getContext().getString(R.string.video_studio_app_name)})));
        view.setOnClickListener(new C0475a(this, 1));
        view.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemClicked$1(BottomMenuItem bottomMenuItem) {
        Log.d("BottomTabMenu", "onItemClicked", bottomMenuItem.getClass().getSimpleName());
        startBottomMenu(bottomMenuItem);
    }

    /* access modifiers changed from: private */
    public void onCancel(DialogInterface dialogInterface) {
        this.mDialog = null;
        destroy();
    }

    /* access modifiers changed from: private */
    public void onItemClicked(View view) {
        Optional.ofNullable((BottomMenuItem) view.getTag()).ifPresent(new C0476b(this, 0));
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public void onVideoStudioClicked(View view) {
        if (Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO)) {
            new StartVideoStudioCmd().execute(this.mEventContext, new Object[0]);
        }
        dismissDialog();
    }

    private void startBottomMenu(BottomMenuItem bottomMenuItem) {
        if (bottomMenuItem.eventId != null) {
            AnalyticsLogger.getInstance().postLog(getScreenId(), bottomMenuItem.eventId.toString());
        }
        String str = bottomMenuItem.locationKey;
        if (bottomMenuItem instanceof BottomMenuItem.Studio) {
            bottomMenuItem.onItemClicked(this.mEventContext);
        } else if (str == null) {
            Log.d("BottomTabMenu", "skip start menu");
        } else if (this.mListener != null) {
            bottomMenuItem.publishData(this.mEventContext);
            ((BottomTabController) ((a) this.mListener).e).onBottomTabMenuItemSelected(str);
        } else {
            bottomMenuItem.onItemClicked(this.mEventContext);
        }
    }

    private void updateGravity(Dialog dialog) {
        Window window;
        int i2;
        if (dialog == null) {
            window = null;
        } else {
            window = dialog.getWindow();
        }
        if (window != null) {
            int i7 = 80;
            if (ResourceCompat.isTablet(window.getContext())) {
                if (Features.isEnabled(Features.IS_RTL)) {
                    i2 = 8388611;
                } else {
                    i2 = 8388613;
                }
                window.setGravity(i2 | 80);
                return;
            }
            if (ResourceCompat.isLandscape(dialog.getContext())) {
                i7 = 16;
            }
            window.setGravity(i7);
        }
    }

    public void dismissDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDialog = null;
        }
        destroy();
    }

    public void setBottomTabMenuItemClickListener(BottomTabMenuClickListener bottomTabMenuClickListener) {
        this.mListener = bottomTabMenuClickListener;
    }

    public void showBottomTabDialog() {
        Context context = this.mEventContext.getContext();
        AlertDialog create = new AlertDialog.Builder(context).setView(createDialogView(context)).setOnCancelListener(new C0406b(2, this)).create();
        this.mDialog = create;
        updateGravity(create);
        this.mDialog.seslSetBackgroundBlurEnabled(isBlurEnabled());
        this.mDialog.show();
    }

    public void updateLayout() {
        updateGravity(this.mDialog);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        ImageView mNewBadge;
        TextView mTextView;

        public BottomMenuViewHolder(View view) {
            super(view);
            bindView(view);
        }

        public static BottomMenuViewHolder create(Context context) {
            return new BottomMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.bottom_menu_list_view_holder, (ViewGroup) null, false));
        }

        public void bind(BottomMenuItem bottomMenuItem) {
            String title = bottomMenuItem.getTitle(this.itemView.getContext());
            this.mImageView.setImageDrawable(bottomMenuItem.getIcon(this.itemView.getContext()));
            this.mTextView.setText(title);
            this.itemView.setContentDescription(title);
        }

        public void bindView(View view) {
            this.mImageView = (ImageView) view.findViewById(R.id.bottom_menu_list_image_view);
            this.mTextView = (TextView) view.findViewById(R.id.bottom_menu_list_text_view);
            this.mNewBadge = (ImageView) view.findViewById(R.id.bottom_menu_list_new_badge);
        }

        public void updateNewBadge(int i2) {
            ViewUtils.setVisibility(this.mNewBadge, 0);
            View view = this.itemView;
            view.setContentDescription(this.itemView.getContentDescription() + ArcCommonLog.TAG_COMMA + this.itemView.getContext().getString(i2));
        }

        public void recycled() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BottomMenuDummyCircleViewHolder extends BottomMenuCircleViewHolder {
        public BottomMenuDummyCircleViewHolder(View view) {
            super(view);
        }

        public static BottomMenuDummyCircleViewHolder create(Context context) {
            View view = new View(context);
            ViewUtils.setWidth(view, context.getResources().getDimensionPixelOffset(R.dimen.bottom_menu_list_dummy_circle_item_width));
            return new BottomMenuDummyCircleViewHolder(view);
        }

        public void bind(BottomMenuItem bottomMenuItem) {
        }

        public void bindView(View view) {
        }
    }
}
