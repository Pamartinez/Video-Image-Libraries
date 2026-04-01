package com.samsung.android.gallery.app.ui.viewer2.container.delegate.input;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.ActionMenuView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.CopyToClipboardMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.DeleteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.EditMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.GroupShotDeleteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.PasteClipboardMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o7.e;
import o7.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyboardMouseDelegate extends AbsVuDelegate<IVuContainerView> {
    private boolean mCtrlPressed;
    private KeyboardFocusHandler mFocusHandler;
    private final boolean mIsRtl = Features.isEnabled(Features.IS_RTL);

    public KeyboardMouseDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private boolean hasChildViewFocusableNext() {
        View view;
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar == null || !toolbar.hasFocus()) {
            FastOptionView fastOptionView = ((ContainerModel) this.mModel).getFastOptionView();
            if (fastOptionView == null || !fastOptionView.hasFocus()) {
                return false;
            }
            return true;
        }
        ActionMenuView seslGetMenuView = toolbar.seslGetMenuView();
        if (seslGetMenuView != null) {
            view = seslGetMenuView.getChildAt(seslGetMenuView.getChildCount() - 1);
        } else {
            view = null;
        }
        if (view == null || view.hasFocus()) {
            return false;
        }
        return true;
    }

    private boolean isDetailsExpanded() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || !BottomSheetState.Details.isExpanded(currentViewer.getModel()) || !OverlayViewState.isHide((OverlayViewState.StateListener) currentViewer.getModel())) {
            return false;
        }
        return true;
    }

    private boolean isDetailsVisible() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null) {
            return false;
        }
        if (currentViewer.getModel().getDetailsState() != 4 || OverlayViewState.isShow((OverlayViewState.StateListener) currentViewer.getModel())) {
            return true;
        }
        return false;
    }

    private boolean isEditDetailsVisible() {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || currentViewer.getModel().getOverlayViewState() != OverlayViewState.edit_details) {
            return false;
        }
        return true;
    }

    private boolean isPlayingVP() {
        return "com.samsung.android.video".equals(SeApiCompat.getAudioFocusedPackageName(getContext()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onKeyDownAirGesture$0(ViewPager2 viewPager2, RecyclerView.Adapter adapter) {
        toastPageUnmovable(viewPager2.getCurrentItem() - 1, adapter.getItemCount(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onKeyDownAirGesture$1(ViewPager2 viewPager2, RecyclerView.Adapter adapter) {
        toastPageUnmovable(viewPager2.getCurrentItem() + 1, adapter.getItemCount(), true);
    }

    private boolean onDelete() {
        ViewerMenuDelegate viewerMenuDelegate = (ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class);
        if (viewerMenuDelegate != null && !isDetailsVisible()) {
            if (viewerMenuDelegate.isMenuEnabled(GroupShotDeleteMenuItem.class)) {
                return new GroupShotDeleteMenuItem(((IVuContainerView) this.mView).getEventContext(), this.mActionInvoker, false).onMenuSelectInternal(((IVuContainerView) this.mView).getView());
            }
            if (viewerMenuDelegate.isMenuEnabled(DeleteMenuItem.class)) {
                return new DeleteMenuItem(((IVuContainerView) this.mView).getEventContext(), this.mActionInvoker).onMenuSelectInternal(((IVuContainerView) this.mView).getView());
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onGenericMotion(View view, MotionEvent motionEvent) {
        float f;
        boolean z = false;
        if (!motionEvent.isFromSource(2) || motionEvent.getAction() != 8) {
            return false;
        }
        float axisValue = motionEvent.getAxisValue(9);
        float axisValue2 = motionEvent.getAxisValue(10);
        if (this.mCtrlPressed) {
            ActionInvoker actionInvoker = this.mActionInvoker;
            ViewerAction viewerAction = ViewerAction.UPDATE_SCALE_RELATIVE;
            if (axisValue > 0.0f) {
                f = 1.25f;
            } else {
                f = 0.75f;
            }
            actionInvoker.invoke(viewerAction, Float.valueOf(f));
        } else {
            ActionInvoker actionInvoker2 = this.mActionInvoker;
            ViewerAction viewerAction2 = ViewerAction.REQUEST_FLING_MOVE;
            if (axisValue < 0.0f || axisValue2 > 0.0f) {
                z = true;
            }
            actionInvoker2.invoke(viewerAction2, Boolean.valueOf(z));
        }
        return true;
    }

    private boolean onKeyDownAirGesture(int i2, KeyEvent keyEvent) {
        boolean z = true;
        if (i2 != 41) {
            RecyclerView.Adapter adapter = null;
            if (i2 != 42) {
                if (i2 == 44 && keyEvent.isCtrlPressed() && keyEvent.isShiftPressed()) {
                    ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
                    this.mActionInvoker.invoke(ViewerAction.REQUEST_FLING_MOVE, Boolean.FALSE);
                    if (viewPager != null) {
                        adapter = viewPager.getAdapter();
                    }
                    Optional.ofNullable(adapter).ifPresent(new e(this, viewPager, 0));
                    return true;
                }
            } else if (keyEvent.isCtrlPressed() && keyEvent.isShiftPressed()) {
                ViewPager2 viewPager2 = ((ContainerModel) this.mModel).getViewPager();
                this.mActionInvoker.invoke(ViewerAction.REQUEST_FLING_MOVE, Boolean.TRUE);
                if (viewPager2 != null) {
                    adapter = viewPager2.getAdapter();
                }
                Optional.ofNullable(adapter).ifPresent(new e(this, viewPager2, 1));
                return true;
            }
        } else if (keyEvent.isCtrlPressed()) {
            if (keyEvent.isShiftPressed()) {
                ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
                if (currentViewer != null) {
                    if (currentViewer.getModel().getDetailsState() != 4) {
                        z = false;
                    }
                    this.mActionInvoker.invoke(ViewerAction.REQUEST_DETAILS_SHOW_OR_HIDE, Boolean.valueOf(z), Boolean.TRUE);
                    return false;
                }
            } else if (((ContainerModel) this.mModel).isOsdVisible()) {
                ((IVuContainerView) this.mView).getToolbar().showOverflowMenu();
            }
        }
        return false;
    }

    private boolean onKeyDownDpad(int i2, KeyEvent keyEvent) {
        switch (i2) {
            case 19:
                if (isDetailsExpanded()) {
                    return requestFocusToDetails(true);
                }
                if (isEditDetailsVisible()) {
                    return requestFocusToEditDetails();
                }
                return this.mFocusHandler.prev();
            case 20:
                if (isDetailsExpanded()) {
                    return requestFocusToDetails(false);
                }
                if (isEditDetailsVisible()) {
                    return requestFocusToEditDetails();
                }
                return this.mFocusHandler.next();
            case 21:
                if (hasFocusToScroll()) {
                    this.mActionInvoker.invoke(ViewerAction.SCROLL_BY_DIRECTION, Boolean.valueOf(this.mIsRtl));
                    return true;
                }
                break;
            case 22:
                if (hasFocusToScroll()) {
                    this.mActionInvoker.invoke(ViewerAction.SCROLL_BY_DIRECTION, Boolean.valueOf(!this.mIsRtl));
                    return true;
                }
                break;
        }
        return false;
    }

    private boolean onKeyDownMove(int i2, KeyEvent keyEvent) {
        if (i2 == 92) {
            this.mActionInvoker.invoke(ViewerAction.SCROLL_BY_DIRECTION, Boolean.FALSE);
            return true;
        } else if (i2 != 93) {
            int i7 = 0;
            if (i2 != 122 && i2 != 123) {
                return false;
            }
            ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
            if (viewPager == null || viewPager.isUserInputEnabled()) {
                if (i2 != 122) {
                    i7 = ((ContainerModel) this.mModel).getMediaData().getCount() - 1;
                }
                this.mActionInvoker.invoke(ViewerAction.SCROLL_TO, Integer.valueOf(i7), Boolean.FALSE);
            }
            return true;
        } else {
            this.mActionInvoker.invoke(ViewerAction.SCROLL_BY_DIRECTION, Boolean.TRUE);
            return true;
        }
    }

    private boolean requestFocusToDetails(boolean z) {
        int i2;
        View view;
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        View findViewById = viewPager.findViewById(R.id.more_info_root_container);
        if (PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS) {
            i2 = R.id.tag_view_layout;
        } else {
            i2 = R.id.ai_edit_layout;
        }
        View findViewById2 = viewPager.findViewById(i2);
        if (z) {
            view = findViewById;
        } else {
            view = findViewById2;
        }
        if (z) {
            findViewById = findViewById2;
        }
        if (ViewUtils.isVisible(view) && !ViewUtils.hasFocus(view)) {
            return view.requestFocus();
        }
        if (!ViewUtils.isVisible(findViewById) || ViewUtils.hasFocus(findViewById)) {
            return false;
        }
        return findViewById.requestFocus();
    }

    private boolean requestFocusToEditDetails() {
        boolean z;
        GalleryToolbar toolbar = ((IVuContainerView) this.mView).getToolbar();
        if (toolbar == null) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) ((ContainerModel) this.mModel).getViewPager().findViewWithTag("edit_details_view");
        if (viewGroup == null || viewGroup.getFocusedChild() == null) {
            z = false;
        } else {
            z = true;
        }
        if (toolbar.hasFocus() || z) {
            return false;
        }
        return toolbar.requestFocus();
    }

    private void runMenuItem(Class<? extends ViewerMenuItem> cls) {
        Integer num;
        ViewerMenuDelegate viewerMenuDelegate = (ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class);
        if (viewerMenuDelegate != null) {
            num = viewerMenuDelegate.getMenuId(cls);
        } else {
            num = null;
        }
        if (num != null) {
            ((IVuContainerView) this.mView).releaseInputBlocking();
            Log.w(this.TAG, "runMenuItem ".concat(cls.getSimpleName()));
            viewerMenuDelegate.onMenuItemSelected(viewerMenuDelegate.getMenuId(cls).intValue(), (View) null);
            return;
        }
        Log.w(this.TAG, "runMenuItem " + cls.getSimpleName() + " is not enabled");
    }

    private void toastPageUnmovable(int i2, int i7, boolean z) {
        int i8;
        if (i2 < 0 || (i2 >= i7 && ((IVuContainerView) this.mView).getContext() != null)) {
            if (z) {
                i8 = R.string.last_item;
            } else {
                i8 = R.string.first_item;
            }
            Toast.makeText(((IVuContainerView) this.mView).getContext(), i8, 0).show();
        }
    }

    public boolean hasFocusToScroll() {
        View findFocus = ((IVuContainerView) this.mView).getView().findFocus();
        if (findFocus == null) {
            return false;
        }
        if (findFocus.getId() == R.id.view_pager || findFocus.getId() == R.id.content_container || findFocus.getId() == R.id.film_strip_view_container) {
            return true;
        }
        if (!(findFocus instanceof RecyclerView) || findFocus.getParent() != ((ContainerModel) this.mModel).getViewPager()) {
            return false;
        }
        return true;
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 24) {
            if (i2 != 44) {
                if (i2 != 50) {
                    if (i2 != 61) {
                        if (i2 != 67) {
                            if (i2 == 84) {
                                return true;
                            }
                            if (!(i2 == 41 || i2 == 42)) {
                                if (i2 == 92 || i2 == 93 || i2 == 122 || i2 == 123) {
                                    return onKeyDownMove(i2, keyEvent);
                                }
                                switch (i2) {
                                    case 19:
                                    case 20:
                                    case 21:
                                    case 22:
                                        return onKeyDownDpad(i2, keyEvent);
                                    default:
                                        switch (i2) {
                                            case 31:
                                                if (keyEvent.isCtrlPressed()) {
                                                    runMenuItem(CopyToClipboardMenuItem.class);
                                                    break;
                                                }
                                                break;
                                            case 32:
                                                if (!keyEvent.isCtrlPressed()) {
                                                    return false;
                                                }
                                                break;
                                            case 33:
                                                if (keyEvent.isCtrlPressed()) {
                                                    runMenuItem(EditMenuItem.class);
                                                    break;
                                                }
                                                break;
                                            default:
                                                switch (i2) {
                                                    case 111:
                                                        this.mActionInvoker.invoke(ViewerAction.ESCAPE_KEY_PRESSED, new Object[0]);
                                                        break;
                                                    case 112:
                                                        break;
                                                    case 113:
                                                    case 114:
                                                        this.mCtrlPressed = true;
                                                        return true;
                                                }
                                        }
                                }
                            }
                        }
                        return onDelete();
                    } else if (hasFocusToScroll()) {
                        return this.mFocusHandler.forceFocusToTopView();
                    } else {
                        if (!hasChildViewFocusableNext()) {
                            return this.mFocusHandler.next();
                        }
                    }
                } else if (keyEvent.isCtrlPressed()) {
                    runMenuItem(PasteClipboardMenuItem.class);
                }
            }
            return onKeyDownAirGesture(i2, keyEvent);
        } else if (((ContainerModel) this.mModel).isAudioMute() && !isPlayingVP()) {
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_SOUND, new Object[0]);
            return !((ContainerModel) this.mModel).isAudioMute();
        }
        return false;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 66) {
            ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
            if (isDetailsVisible() || viewPager == null || !viewPager.hasFocus()) {
                return false;
            }
            this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
            return true;
        } else if (i2 != 113 && i2 != 114) {
            return false;
        } else {
            this.mCtrlPressed = false;
            return true;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (viewPager != null) {
            viewPager.setOnGenericMotionListener(new f(this));
        }
        this.mFocusHandler = new KeyboardFocusHandler((IVuContainerView) this.mView);
    }
}
