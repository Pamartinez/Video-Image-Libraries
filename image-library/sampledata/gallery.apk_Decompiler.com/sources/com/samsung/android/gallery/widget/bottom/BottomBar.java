package com.samsung.android.gallery.widget.bottom;

import A.a;
import D5.f;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingBottomLayout;
import com.samsung.android.gallery.app.activity.c;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.R$anim;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import g6.g;
import h3.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import k2.q;
import nb.C0700a;
import nb.C0701b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomBar extends BottomNavigationView {
    private boolean mBarVisible = false;
    private BottomBarData mBottomBarData;
    private Animation mFadeInAnim;
    private Animation mFadeOutAnim;
    private Animation mHideAnim;
    /* access modifiers changed from: private */
    public boolean mIsShowCancelled = false;
    private BooleanSupplier mIsTouchBlocked;
    private final Point mLastTouchPoint = new Point();
    private final q mNavigationItemSelectedListener = new C0700a(this, 0);
    /* access modifiers changed from: private */
    public OnStateChangedListener mOnStateChangedListener;
    private final View.OnTouchListener mOverflowMenuTouchListener = new f(2);
    q mPreSelectListener = new C0700a(this, 1);
    private Animation mShowAnim;
    private Animation mSlideDownAnim;
    private Animation mSlideUpAnim;
    private final HashMap<Integer, ArrayList<MenuItem>> mSubMenuMap = new HashMap<>();
    q mTargetSelectListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnStateChangedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum StateType {
        SHOW,
        HIDE,
        CLOSE
    }

    public BottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void addMoreMenuItems(Menu menu, ArrayList<MenuItem> arrayList, ArrayList<MenuItem> arrayList2) {
        int i2;
        this.mSubMenuMap.clear();
        int size = arrayList.size();
        Iterator<MenuItem> it = arrayList2.iterator();
        while (it.hasNext()) {
            MenuItem next = it.next();
            int buildGroupId = buildGroupId(next);
            if (next.hasSubMenu()) {
                ArrayList<MenuItem> subMenu = getSubMenu(next);
                if (subMenu == null || subMenu.isEmpty()) {
                    Log.d("BottomBar", "addMoreMenuItems#SubMenu skip empty");
                } else {
                    this.mSubMenuMap.put(Integer.valueOf(next.getItemId()), subMenu);
                    if (subMenu.size() == 1) {
                        next = subMenu.get(0);
                    }
                    i2 = size + 1;
                    menu.add(buildGroupId, next.getItemId(), size, next.getTitle());
                }
            } else {
                i2 = size + 1;
                menu.add(buildGroupId, next.getItemId(), size, next.getTitle());
            }
            size = i2;
        }
    }

    private int buildGroupId(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        if (groupId == R$id.select_mode_knox || groupId == R$id.select_mode_effects) {
            return groupId;
        }
        return R$id.select_mode_bottom;
    }

    private void cancelShowAnimation() {
        this.mIsShowCancelled = true;
        Animation animation = this.mShowAnim;
        if (animation != null) {
            animation.cancel();
        }
    }

    private boolean containsSubMenu(MenuItem menuItem, MenuItem menuItem2) {
        ArrayList arrayList;
        if (!menuItem.hasSubMenu() || (arrayList = this.mSubMenuMap.get(Integer.valueOf(menuItem.getItemId()))) == null || !arrayList.stream().anyMatch(new b(2, menuItem2))) {
            return false;
        }
        return true;
    }

    private boolean enableBottomItems(ArrayList<MenuItem> arrayList, MenuItem menuItem, boolean z, boolean z3) {
        Iterator<MenuItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MenuItem next = it.next();
            if (menuItem.getItemId() == next.getItemId()) {
                menuItem.setEnabled(next.isEnabled());
                if (menuItem.getItemId() == R$id.action_done) {
                    menuItem.setTitle(next.getTitle());
                    menuItem.setIcon(next.getIcon());
                }
                if (!TextUtils.equals(menuItem.getTitle(), next.getTitle())) {
                    menuItem.setTitle(next.getTitle());
                }
                if (!Objects.equals(menuItem.getIcon(), next.getIcon())) {
                    menuItem.setIcon(next.getIcon());
                }
                if (z3 && isDeleteMenu(menuItem)) {
                    menuItem.setTitle(R$string.delete_all);
                }
                if (!z || menuItem.getItemId() != R$id.action_restore) {
                    return true;
                }
                menuItem.setTitle(R$string.restore_all);
                return true;
            }
        }
        return false;
    }

    private boolean enableBottomMoreItems(ArrayList<MenuItem> arrayList, MenuItem menuItem, boolean z) {
        Iterator<MenuItem> it = arrayList.iterator();
        while (it.hasNext()) {
            MenuItem next = it.next();
            if (menuItem.getItemId() == next.getItemId()) {
                menuItem.setEnabled(next.isEnabled());
                menuItem.setVisible(next.isEnabled());
                return true;
            } else if (containsSubMenu(next, menuItem)) {
                return true;
            }
        }
        return false;
    }

    private void enableMenuItems(Menu menu, ArrayList<MenuItem> arrayList, ArrayList<MenuItem> arrayList2, boolean z, boolean z3) {
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < menu.size(); i2++) {
            MenuItem item = menu.getItem(i2);
            if (!enableBottomItems(arrayList, item, z, z3) && !enableBottomMoreItems(arrayList2, item, z)) {
                arrayList3.add(Integer.valueOf(item.getItemId()));
            }
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            menu.removeItem(((Integer) it.next()).intValue());
        }
    }

    private int getMenuCount() {
        return getMenu().size();
    }

    private View getOverflowMenuButton() {
        return findViewById(R$id.bottom_overflow);
    }

    /* access modifiers changed from: private */
    public View getSlideView() {
        View view = (View) getParent();
        if (view != null) {
            return view;
        }
        return this;
    }

    private ArrayList<MenuItem> getSubMenu(MenuItem menuItem) {
        SubMenu subMenu;
        if (!menuItem.hasSubMenu() || (subMenu = menuItem.getSubMenu()) == null || subMenu.size() <= 0) {
            return null;
        }
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < subMenu.size(); i2++) {
            MenuItem item = subMenu.getItem(i2);
            if (item.isVisible()) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    private boolean inflateSubMenu(MenuItem menuItem) {
        Activity activity = Utils.getActivity((View) this);
        if (activity != null) {
            ArrayList arrayList = this.mSubMenuMap.get(Integer.valueOf(menuItem.getItemId()));
            if (arrayList == null || arrayList.isEmpty()) {
                return false;
            }
            View overflowMenuButton = getOverflowMenuButton();
            if (overflowMenuButton == null) {
                overflowMenuButton = this;
            }
            PopupMenu popupMenu = new PopupMenu(activity, overflowMenuButton, 8388613);
            arrayList.forEach(new g(29, popupMenu.getMenu()));
            popupMenu.setOnMenuItemClickListener(new C0701b(this));
            popupMenu.show();
            Log.d("BottomBar", "inflateSubMenu" + Logger.v(menuItem) + "");
            return true;
        }
        throw new IllegalStateException("inflateSubMenu null activity");
    }

    private void init(Context context) {
        initAnimation(context);
        seslSetGroupDividerEnabled(true);
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            /* access modifiers changed from: private */
            public static /* synthetic */ boolean lambda$onViewAttachedToWindow$0(View view, MotionEvent motionEvent) {
                return true;
            }

            /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, android.view.View$OnTouchListener] */
            public void onViewAttachedToWindow(View view) {
                BottomBar.this.setOnTouchListener(new Object());
            }

            public void onViewDetachedFromWindow(View view) {
            }
        });
    }

    private void initAnimation(Context context) {
        this.mSlideUpAnim = AnimationUtils.loadAnimation(context, R$anim.floating_fade_in);
        this.mSlideDownAnim = AnimationUtils.loadAnimation(context, R$anim.floating_fade_out);
        this.mFadeInAnim = AnimationUtils.loadAnimation(context, R$anim.highlight_fade_in);
        this.mFadeOutAnim = AnimationUtils.loadAnimation(context, R$anim.fade_out_200);
        setAnimationType(true);
    }

    private boolean isDeleteMenu(MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.action_delete || menuItem.getItemId() == R$id.action_clean_contents || menuItem.getItemId() == R$id.action_delete_album_in_list || menuItem.getItemId() == R$id.action_delete_story_album_in_list || menuItem.getItemId() == R$id.action_delete_shared_album_in_list) {
            return true;
        }
        return false;
    }

    private boolean isPopoverDialog(int i2) {
        if (i2 == R$id.action_delete || i2 == R$id.action_delete_album_in_list || i2 == R$id.action_delete_story_album_in_list || i2 == R$id.action_delete_shared_album || i2 == R$id.action_delete_shared_album_in_list || i2 == R$id.action_rename_album || i2 == R$id.action_rename_story_album || i2 == R$id.action_rename_album_folder || i2 == R$id.action_rename_story_album_in_list || i2 == R$id.action_remove_from_story || i2 == R$id.action_folder_grouping || i2 == R$id.action_remove || i2 == R$id.action_create) {
            return true;
        }
        return false;
    }

    private boolean isPopoverShare(int i2) {
        if (i2 == R$id.action_share) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$containsSubMenu$3(MenuItem menuItem, MenuItem menuItem2) {
        if (menuItem2.getItemId() == menuItem.getItemId()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(MenuItem menuItem) {
        BottomBarData bottomBarData;
        ArrayList<MenuItem> arrayList;
        if (SystemUi.supportPopoverUi(getContext(), isPopoverShare(menuItem.getItemId()))) {
            View findViewById = findViewById(menuItem.getItemId());
            if (findViewById == null && (bottomBarData = this.mBottomBarData) != null && (arrayList = bottomBarData.itemsMore) != null) {
                Iterator<MenuItem> it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getItemId() == menuItem.getItemId()) {
                            findViewById = findViewById(R$id.bottom_overflow);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            Activity activity = Utils.getActivity(findViewById);
            if (activity != null) {
                if (isPopoverShare(menuItem.getItemId())) {
                    PopoverHelper.publishPopoverShareInfo(Blackboard.getInstance(activity.toString()), this.mLastTouchPoint);
                } else if (isPopoverDialog(menuItem.getItemId())) {
                    PopoverHelper.publishPopoverInfo(Blackboard.getInstance(activity.toString()), findViewById, 2, menuItem.getItemId());
                }
            }
        }
        return this.mTargetSelectListener.onNavigationItemSelected(menuItem);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$1(View view, MotionEvent motionEvent) {
        Activity activity;
        if (motionEvent.getAction() != 1 || !ViewUtils.isTouchedOnViewRaw(view, motionEvent) || (activity = Utils.getActivity(view)) == null) {
            return false;
        }
        Blackboard.getInstance(activity.toString()).postEvent(EventMessage.obtain(1100));
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        BottomBarData bottomBarData = this.mBottomBarData;
        if (bottomBarData == null || bottomBarData.listener == null) {
            return false;
        }
        ArrayList<MenuItem> arrayList = bottomBarData.items;
        if (arrayList != null) {
            Iterator<MenuItem> it = arrayList.iterator();
            while (it.hasNext()) {
                MenuItem next = it.next();
                if (next.getItemId() == menuItem.getItemId()) {
                    return this.mBottomBarData.listener.onNavigationItemSelected(next);
                }
            }
        }
        ArrayList<MenuItem> arrayList2 = this.mBottomBarData.itemsMore;
        if (arrayList2 == null) {
            return false;
        }
        Iterator<MenuItem> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            MenuItem next2 = it2.next();
            if (next2.getItemId() == menuItem.getItemId()) {
                if (next2.hasSubMenu()) {
                    return inflateSubMenu(next2);
                }
                return this.mBottomBarData.listener.onNavigationItemSelected(next2);
            } else if (containsSubMenu(next2, menuItem)) {
                return this.mBottomBarData.listener.onNavigationItemSelected(menuItem);
            }
        }
        return false;
    }

    private void removeAllMenu() {
        getMenu().removeGroup(R$id.select_mode_knox);
        getMenu().removeGroup(R$id.select_mode_effects);
        getMenu().removeGroup(R$id.select_mode_bottom);
        getMenu().removeGroup(R$id.select_mode_with_done);
    }

    /* access modifiers changed from: private */
    public void setAutoGoToTopOffsetMove(boolean z) {
        View slideView = getSlideView();
        if (slideView instanceof FloatingBottomLayout) {
            FloatingBottomLayout floatingBottomLayout = (FloatingBottomLayout) slideView;
            if (z) {
                floatingBottomLayout.f1894G = Boolean.TRUE;
                floatingBottomLayout.getFloatingScrollableManager$material_release().f = true;
                return;
            }
            floatingBottomLayout.f1894G = Boolean.FALSE;
            floatingBottomLayout.getFloatingScrollableManager$material_release().f = false;
        }
    }

    private void setOnOverflowMenuTouchListener() {
        View overflowMenuButton = getOverflowMenuButton();
        if (overflowMenuButton != null) {
            overflowMenuButton.setOnTouchListener(this.mOverflowMenuTouchListener);
        }
    }

    /* access modifiers changed from: private */
    public void updateState(StateType stateType) {
        int i2;
        if (stateType.equals(StateType.CLOSE)) {
            removeAllMenu();
        }
        View slideView = getSlideView();
        StateType stateType2 = StateType.SHOW;
        if (stateType.equals(stateType2)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        slideView.setVisibility(i2);
        OnStateChangedListener onStateChangedListener = this.mOnStateChangedListener;
        if (onStateChangedListener != null) {
            ((c) onStateChangedListener).a(stateType.equals(stateType2));
        }
    }

    public void close(boolean z) {
        if (this.mBottomBarData != null) {
            this.mBottomBarData = null;
        }
        if (this.mBarVisible) {
            cancelShowAnimation();
            if (z) {
                this.mHideAnim.setAnimationListener(new SimpleAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        BottomBar.this.updateState(StateType.CLOSE);
                        BottomBar.this.setAutoGoToTopOffsetMove(true);
                        SeApiCompat.announceVoiceAssistant(BottomBar.this.getContext(), AppResources.getString(R$string.bottom_bar_hide));
                    }
                });
                setAutoGoToTopOffsetMove(false);
                getSlideView().setVisibility(4);
                getSlideView().startAnimation(this.mHideAnim);
            } else {
                updateState(StateType.CLOSE);
            }
            this.mBarVisible = false;
            return;
        }
        updateState(StateType.CLOSE);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        BooleanSupplier booleanSupplier = this.mIsTouchBlocked;
        if (booleanSupplier == null || !booleanSupplier.getAsBoolean()) {
            if (motionEvent.getAction() == 1 || motionEvent.getAction() == 6) {
                this.mLastTouchPoint.x = (int) motionEvent.getRawX();
                this.mLastTouchPoint.y = (int) motionEvent.getRawY();
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        Log.d("BottomBar", "dispatchTouchEvent blocked");
        return true;
    }

    public void hide(boolean z) {
        if (this.mBarVisible) {
            cancelShowAnimation();
            if (z) {
                this.mHideAnim.setAnimationListener(new SimpleAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        BottomBar.this.updateState(StateType.HIDE);
                        SeApiCompat.announceVoiceAssistant(BottomBar.this.getContext(), AppResources.getString(R$string.bottom_bar_hide));
                    }

                    public void onAnimationStart(Animation animation) {
                        if (BottomBar.this.mOnStateChangedListener != null) {
                            ((c) BottomBar.this.mOnStateChangedListener).a(false);
                        }
                    }
                });
                getSlideView().setVisibility(4);
                getSlideView().startAnimation(this.mHideAnim);
            } else {
                updateState(StateType.HIDE);
            }
            this.mBarVisible = false;
        }
    }

    public boolean isBarVisible() {
        return this.mBarVisible;
    }

    public void setAnimationType(boolean z) {
        Animation animation;
        Animation animation2;
        if (z) {
            animation = this.mSlideUpAnim;
        } else {
            animation = this.mFadeInAnim;
        }
        this.mShowAnim = animation;
        if (z) {
            animation2 = this.mSlideDownAnim;
        } else {
            animation2 = this.mFadeOutAnim;
        }
        this.mHideAnim = animation2;
        if (z) {
            PathInterpolator.Type type = PathInterpolator.Type.TYPE_SINE_IN_OUT_33;
            animation.setInterpolator(PathInterpolator.create(type));
            this.mHideAnim.setInterpolator(PathInterpolator.create(type));
        }
    }

    public void setMenuItems(BottomBarData bottomBarData) {
        if (bottomBarData == null || !bottomBarData.equals(this.mBottomBarData) || !bottomBarData.containsAll(getMenu())) {
            long currentTimeMillis = System.currentTimeMillis();
            removeAllMenu();
            this.mBottomBarData = bottomBarData;
            if (bottomBarData != null) {
                inflateMenu(bottomBarData.menuResId);
                Menu menu = getMenu();
                addMoreMenuItems(menu, bottomBarData.items, bottomBarData.itemsMore);
                enableMenuItems(menu, bottomBarData.items, bottomBarData.itemsMore, bottomBarData.allSelected, bottomBarData.showDeleteAll);
                this.mTargetSelectListener = this.mNavigationItemSelectedListener;
                setOnItemSelectedListener(this.mPreSelectListener);
                setOnOverflowMenuTouchListener();
            }
            StringBuilder sb2 = new StringBuilder("setMenuItems ");
            sb2.append(bottomBarData);
            sb2.append(" +");
            a.x(sb2, currentTimeMillis, "BottomBar");
            return;
        }
        this.mBottomBarData = bottomBarData;
        enableMenuItems(getMenu(), bottomBarData.items, bottomBarData.itemsMore, bottomBarData.allSelected, bottomBarData.showDeleteAll);
    }

    public void setStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.mOnStateChangedListener = onStateChangedListener;
    }

    public void setTouchBlocker(BooleanSupplier booleanSupplier) {
        this.mIsTouchBlocked = booleanSupplier;
    }

    public void show() {
        show(true);
    }

    public void show(boolean z) {
        if (getMenuCount() > 0 && !this.mBarVisible) {
            this.mBarVisible = true;
            this.mIsShowCancelled = false;
            if (z) {
                this.mShowAnim.setAnimationListener(new SimpleAnimationListener() {
                    public void onAnimationEnd(Animation animation) {
                        BottomBar.this.setAutoGoToTopOffsetMove(true);
                        if (!BottomBar.this.mIsShowCancelled) {
                            BottomBar.this.updateState(StateType.SHOW);
                            BottomBar.this.getSlideView().requestLayout();
                            SeApiCompat.announceVoiceAssistant(BottomBar.this.getContext(), AppResources.getString(R$string.bottom_bar_show));
                        }
                    }
                });
                setMinimumHeight(getResources().getDimensionPixelOffset(R$dimen.bottom_bar_floating_height));
                setAutoGoToTopOffsetMove(false);
                getSlideView().setVisibility(0);
                getSlideView().startAnimation(this.mShowAnim);
                return;
            }
            updateState(StateType.SHOW);
        }
    }
}
