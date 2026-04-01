package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.A;
import A4.B;
import A4.C0366a;
import A4.C0384t;
import A4.C0388x;
import A4.C0389y;
import A4.C0390z;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandler;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandlerV2;
import com.samsung.android.gallery.widget.listview.handler.DisHandler;
import com.samsung.android.gallery.widget.listview.handler.DisHandlerComposite;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BaseListKeyHandler {
    private final CharSequence TAG;
    private DisHandler mAdvMouseSelectionComposite;
    private DisHandler mPenSelectionComposite;
    private final IBaseListView mView;

    public BaseListKeyHandler(IBaseListView iBaseListView) {
        this.TAG = iBaseListView.tag();
        this.mView = iBaseListView;
    }

    private void clearClipboard() {
        if (!this.mView.isSelectionMode()) {
            Optional.ofNullable(this.mView.getBlackboard()).ifPresent(new C0366a(11));
        }
    }

    private void eraseFromBlackboard(String str) {
        Optional.ofNullable(this.mView.getBlackboard()).ifPresent(new B(str, 0));
    }

    private int getAppBarVisibleHeight() {
        return Math.max(this.mView.getAppBarVisibleHeight(), 0);
    }

    private Integer getNextDepth(float f) {
        int i2;
        BaseListPresenter presenter = this.mView.getPresenter();
        if (presenter != null) {
            int currentViewDepth = presenter.getCurrentViewDepth();
            if (f > 0.0f) {
                i2 = Math.min(currentViewDepth + 1, presenter.getMaxDepth());
            } else {
                i2 = Math.max(currentViewDepth - 1, 0);
            }
            if (currentViewDepth != i2) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void invalidateOptionsMenu(Void voidR) {
        this.mView.invalidateOptionsMenu();
    }

    private boolean isOnlyCtrlPressed(KeyEvent keyEvent) {
        if (keyEvent.getMetaState() <= 0 || (keyEvent.getMetaState() & KeyEvent.getModifierMetaStateMask() & -28673) != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$onGenericMotionEvent$5(Blackboard blackboard) {
        return (Integer) blackboard.read("data://key_combination_ctrl");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMouseEvent$6() {
        setVirtualCtrlKeyPressed(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMouseEvent$7() {
        setVirtualCtrlKeyPressed(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPenEvent$10(List list) {
        list.forEach(new C0389y(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPenEvent$9(DisHandler disHandler) {
        this.mPenSelectionComposite.addHandler(disHandler);
    }

    private boolean onKeyDownMove(int i2) {
        if (i2 == 92) {
            return ((Boolean) Optional.ofNullable(this.mView.getListView()).map(new C0384t(5)).orElse(Boolean.FALSE)).booleanValue();
        }
        if (i2 == 93) {
            return ((Boolean) Optional.ofNullable(this.mView.getListView()).map(new C0384t(6)).orElse(Boolean.FALSE)).booleanValue();
        }
        if (i2 == 122) {
            return ((Boolean) Optional.ofNullable(this.mView.getListView()).map(new C0384t(7)).orElse(Boolean.FALSE)).booleanValue();
        }
        if (i2 != 123) {
            return false;
        }
        return ((Boolean) Optional.ofNullable(this.mView.getListView()).map(new C0384t(8)).orElse(Boolean.FALSE)).booleanValue();
    }

    private boolean onKeyDownWithCtrl(int i2, KeyEvent keyEvent) {
        BaseListViewAdapter adapter;
        if (!isOnlyCtrlPressed(keyEvent)) {
            return false;
        }
        if (i2 != 29) {
            if (i2 == 31) {
                setMouseDragRectRange(false);
                return ((Boolean) Optional.ofNullable(this.mView.getPresenter()).map(new C0384t(9)).orElse(Boolean.FALSE)).booleanValue();
            } else if (i2 != 33) {
                if (i2 != 41) {
                    if (i2 == 42) {
                        return ((Boolean) Optional.ofNullable(this.mView.getPresenter()).map(new C0384t(10)).orElse(Boolean.FALSE)).booleanValue();
                    }
                } else if (!this.mView.isSelectionMode() || this.mView.getSelectedItemCount() <= 0) {
                    this.mView.getToolbar().showOverflowMenu();
                    return true;
                } else if (this.mView.getBottomBar() instanceof BottomBar) {
                    ((BottomBar) this.mView.getBottomBar()).seslShowOverflowMenu();
                    return true;
                }
            } else if (!this.mView.isSelectionMode()) {
                this.mView.enterSelectionMode(0);
                return true;
            }
        } else if (this.mView.isSelectionMode() && ((adapter = this.mView.getAdapter()) == null || adapter.getSelectableMaxCount() <= 0)) {
            this.mView.selectAll();
            this.mView.updateSelectionToolBar();
            return true;
        }
        return false;
    }

    private void publishToBlackboard(String str, Object obj) {
        Optional.ofNullable(this.mView.getBlackboard()).ifPresent(new A(0, (Object) str, obj));
    }

    private void saveLastSelectedViewPositionOnAdvancedMouseEvent(MotionEvent motionEvent) {
        int i2;
        BaseListViewAdapter adapter;
        GalleryListView listView = this.mView.getListView();
        if (listView != null && motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            listView.getGlobalVisibleRect(rect);
            if (this.mView.isAppBarPartiallyVisible()) {
                rect.bottom -= ViewMarginUtils.getBottomMargin(listView);
            }
            View findChildViewUnder = listView.findChildViewUnder(motionEvent.getX() - ((float) rect.left), motionEvent.getY() - ((float) rect.top));
            if (findChildViewUnder != null) {
                i2 = listView.getChildAdapterPosition(findChildViewUnder);
            } else {
                i2 = -1;
            }
            if (this.mView.isAllowAdvancedMouseEvent() && (adapter = this.mView.getAdapter()) != null) {
                if (!adapter.isOnKeyCombination() && rect.contains((int) motionEvent.getX(), (int) motionEvent.getY()) && isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2)) {
                    setVirtualCtrlKeyPressed(true);
                }
                adapter.setLastSelectedViewPositionOnMotionEventDown(i2);
            }
        }
    }

    private boolean supportCombinationKeys() {
        if (PreferenceFeatures.OneUi7x.SUPPORT_MOUSE_USABILITY_V2 || this.mView.isDexMode()) {
            return true;
        }
        return false;
    }

    public void bindMouseHandler() {
        GalleryListView listView;
        DisHandler disHandler;
        if (this.mAdvMouseSelectionComposite == null && (listView = this.mView.getListView()) != null) {
            if (PreferenceFeatures.OneUi7x.SUPPORT_MOUSE_USABILITY_V2) {
                disHandler = new AdvancedMouseSelectionHandlerV2(listView);
            } else {
                disHandler = new AdvancedMouseSelectionHandler(listView);
            }
            DisHandlerComposite disHandlerComposite = new DisHandlerComposite(disHandler);
            this.mAdvMouseSelectionComposite = disHandlerComposite;
            disHandlerComposite.setOnUpdateSelectionListener(new C0389y(this, 1));
            DisHandler disHandler2 = this.mAdvMouseSelectionComposite;
            IBaseListView iBaseListView = this.mView;
            Objects.requireNonNull(iBaseListView);
            disHandler2.useAdvMouseDnd(new C0390z(iBaseListView, 0));
            IBaseListView iBaseListView2 = this.mView;
            Objects.requireNonNull(iBaseListView2);
            listView.setIsAllowAdvancedMouseEvent(new C0390z(iBaseListView2, 1));
        }
    }

    public void bindViewHolder(ListViewHolder listViewHolder, int i2) {
        DisHandler disHandler = this.mPenSelectionComposite;
        if (disHandler != null) {
            disHandler.onBindViewHolder(listViewHolder, i2);
        }
        DisHandler disHandler2 = this.mAdvMouseSelectionComposite;
        if (disHandler2 != null) {
            disHandler2.onBindViewHolder(listViewHolder, i2);
        }
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent, int i2) {
        return this.mView.isVirtualCtrlKeyPressedAllowablePoint(motionEvent, i2);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        Integer num;
        if (!supportCombinationKeys() || motionEvent.getAction() != 8 || this.mView.getListView() == null || (num = (Integer) Optional.ofNullable(this.mView.getBlackboard()).map(new C0384t(11)).orElse((Object) null)) == null) {
            return false;
        }
        int intValue = num.intValue();
        if (intValue != 113 && intValue != 114) {
            return false;
        }
        Integer nextDepth = getNextDepth(motionEvent.getAxisValue(9));
        if (nextDepth == null) {
            return true;
        }
        this.mView.changeViewDepthByWheelScroll(nextDepth.intValue());
        return true;
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 29) {
            if (i2 != 67) {
                if (i2 == 84) {
                    Optional.ofNullable(this.mView.getPresenter()).ifPresent(new C0366a(9));
                    return true;
                } else if (!(i2 == 41 || i2 == 42)) {
                    if (i2 == 59 || i2 == 60) {
                        if (supportCombinationKeys()) {
                            publishToBlackboard("data://key_combination_shift", Integer.valueOf(i2));
                            if (this.mView.isAllowAdvancedMouseEvent()) {
                                setMouseDragRectRange(true);
                            }
                        }
                        return true;
                    }
                    switch (i2) {
                        case 31:
                        case 33:
                            break;
                        case 32:
                            if (!isOnlyCtrlPressed(keyEvent)) {
                                return false;
                            }
                            break;
                        case 34:
                            if (isOnlyCtrlPressed(keyEvent)) {
                                setMouseDragRectRange(false);
                                Optional.ofNullable(this.mView.getPresenter()).ifPresent(new C0366a(9));
                            }
                            return true;
                        default:
                            switch (i2) {
                                case 111:
                                    return ((Boolean) Optional.ofNullable(this.mView.getPresenter()).map(new C0384t(4)).orElse(Boolean.FALSE)).booleanValue();
                                case 112:
                                    break;
                                case 113:
                                case 114:
                                    if (supportCombinationKeys()) {
                                        publishToBlackboard("data://key_combination_ctrl", Integer.valueOf(i2));
                                        if (this.mView.isAllowAdvancedMouseEvent()) {
                                            setMouseDragRectRange(true);
                                        }
                                    }
                                    return true;
                                default:
                                    return onKeyDownMove(i2);
                            }
                    }
                }
            }
            return ((Boolean) Optional.ofNullable(this.mView.getPresenter()).map(new C0384t(3)).orElse(Boolean.FALSE)).booleanValue();
        }
        return onKeyDownWithCtrl(i2, keyEvent);
    }

    public boolean onKeyLongPress(int i2) {
        if (i2 != 62) {
            return false;
        }
        Optional.ofNullable(this.mView.getPresenter()).ifPresent(new C0366a(10));
        return true;
    }

    public boolean onKeyUp(int i2) {
        if (i2 == 59 || i2 == 60) {
            if (supportCombinationKeys()) {
                eraseFromBlackboard("data://key_combination_shift");
                setMouseDragRectRange(false);
            }
            return true;
        } else if (i2 != 113 && i2 != 114) {
            return false;
        } else {
            if (supportCombinationKeys()) {
                eraseFromBlackboard("data://key_combination_ctrl");
                setMouseDragRectRange(false);
            }
            return true;
        }
    }

    public boolean onMouseEvent(MotionEvent motionEvent) {
        saveLastSelectedViewPositionOnAdvancedMouseEvent(motionEvent);
        BaseListViewAdapter adapter = this.mView.getAdapter();
        if ((adapter == null || adapter.isOnKeyCombination()) && this.mView.isAllowAdvancedMouseEvent()) {
            int action = motionEvent.getAction();
            if (action == 0) {
                Log.d(this.TAG, "Advanced Mouse ACTION : down");
                GalleryListView listView = this.mView.getListView();
                if (listView == null || !listView.isListSelectable()) {
                    Log.w(this.TAG, "list is not selectable");
                    return false;
                }
                clearClipboard();
                DisHandler disHandler = this.mAdvMouseSelectionComposite;
                if (disHandler != null) {
                    disHandler.handleDown(motionEvent, getAppBarVisibleHeight());
                }
            } else if (action == 1) {
                Log.d(this.TAG, "Advanced Mouse ACTION : up");
                DisHandler disHandler2 = this.mAdvMouseSelectionComposite;
                if (disHandler2 != null) {
                    disHandler2.handleUp((int) motionEvent.getX(), (int) motionEvent.getY());
                }
                ThreadUtil.postOnUiThread(new C0388x(this, 0));
                return false;
            } else if (action == 2) {
                DisHandler disHandler3 = this.mAdvMouseSelectionComposite;
                if (disHandler3 != null) {
                    disHandler3.handleMove((int) motionEvent.getX(), (int) motionEvent.getY());
                    return false;
                }
            } else if (action == 3) {
                Log.d(this.TAG, "Advanced Mouse ACTION : cancel");
                DisHandler disHandler4 = this.mAdvMouseSelectionComposite;
                if (disHandler4 != null) {
                    disHandler4.handleCancel();
                }
                ThreadUtil.postOnUiThread(new C0388x(this, 1));
                return false;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r0 != 212) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPenEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 0
            if (r0 == 0) goto L_0x0051
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L_0x0031
            r2 = 3
            if (r0 == r2) goto L_0x001b
            r2 = 214(0xd6, float:3.0E-43)
            if (r0 == r2) goto L_0x001b
            r2 = 211(0xd3, float:2.96E-43)
            if (r0 == r2) goto L_0x0051
            r2 = 212(0xd4, float:2.97E-43)
            if (r0 == r2) goto L_0x0031
            goto L_0x0059
        L_0x001b:
            java.lang.CharSequence r5 = r4.TAG
            java.lang.String r0 = "PEN ACTION : cancel"
            com.samsung.android.gallery.support.utils.Log.d(r5, r0)
            com.samsung.android.gallery.widget.listview.handler.DisHandler r5 = r4.mPenSelectionComposite
            if (r5 == 0) goto L_0x0059
            r5.handleCancel()
            com.samsung.android.gallery.widget.listview.handler.DisHandler r5 = r4.mPenSelectionComposite
            r5.release()
            r4.mPenSelectionComposite = r3
            return r1
        L_0x0031:
            java.lang.CharSequence r0 = r4.TAG
            java.lang.String r2 = "PEN ACTION : up"
            com.samsung.android.gallery.support.utils.Log.d(r0, r2)
            com.samsung.android.gallery.widget.listview.handler.DisHandler r0 = r4.mPenSelectionComposite
            if (r0 == 0) goto L_0x0059
            float r2 = r5.getX()
            int r2 = (int) r2
            float r5 = r5.getY()
            int r5 = (int) r5
            r0.handleUp(r2, r5)
            com.samsung.android.gallery.widget.listview.handler.DisHandler r5 = r4.mPenSelectionComposite
            r5.release()
            r4.mPenSelectionComposite = r3
            return r1
        L_0x0051:
            r0 = 32
            boolean r0 = r5.isButtonPressed(r0)
            if (r0 != 0) goto L_0x005a
        L_0x0059:
            return r1
        L_0x005a:
            java.lang.CharSequence r0 = r4.TAG
            java.lang.String r2 = "PEN ACTION : down"
            com.samsung.android.gallery.support.utils.Log.d(r0, r2)
            com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView r0 = r4.mView
            com.samsung.android.gallery.widget.listview.GalleryListView r0 = r0.getListView()
            if (r0 == 0) goto L_0x009a
            boolean r2 = r0.isListSelectable()
            if (r2 != 0) goto L_0x0070
            goto L_0x009a
        L_0x0070:
            r4.clearClipboard()
            com.samsung.android.gallery.widget.listview.handler.DisHandlerComposite r2 = new com.samsung.android.gallery.widget.listview.handler.DisHandlerComposite
            com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler r3 = new com.samsung.android.gallery.widget.listview.handler.PenSelectionHandler
            r3.<init>(r0)
            r2.<init>(r3)
            r4.mPenSelectionComposite = r2
            java.util.List r0 = r2.getChildDisHandler()
            java.util.Optional r0 = java.util.Optional.ofNullable(r0)
            A4.y r2 = new A4.y
            r3 = 2
            r2.<init>(r4, r3)
            r0.ifPresent(r2)
            com.samsung.android.gallery.widget.listview.handler.DisHandler r0 = r4.mPenSelectionComposite
            int r4 = r4.getAppBarVisibleHeight()
            r0.handleDown(r5, r4)
            return r1
        L_0x009a:
            java.lang.CharSequence r4 = r4.TAG
            java.lang.String r5 = "list is not selectable"
            com.samsung.android.gallery.support.utils.Log.w(r4, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.BaseListKeyHandler.onPenEvent(android.view.MotionEvent):boolean");
    }

    public void rebindMouseHandler() {
        unbindMouseHandler();
        bindMouseHandler();
    }

    public void setMouseDragRectRange(boolean z) {
        GalleryListView listView = this.mView.getListView();
        if (listView != null) {
            listView.seslSetCtrlkeyPressed(z);
        }
    }

    public void setVirtualCtrlKeyPressed(boolean z) {
        if (this.mView.useAdvancedMouseDragAndDrop() && this.mView.getListView() != null) {
            CharSequence charSequence = this.TAG;
            Log.d(charSequence, "setVirtualCtrlKeyPressed : " + z);
            publishToBlackboard("data://virtual_ctrl_pressed_on_dex_live", Boolean.valueOf(z));
            setMouseDragRectRange(z);
        }
    }

    public void unbindMouseHandler() {
        DisHandler disHandler = this.mAdvMouseSelectionComposite;
        if (disHandler != null) {
            disHandler.release();
            this.mAdvMouseSelectionComposite = null;
            Optional.ofNullable(this.mView.getListView()).ifPresent(new C0366a(12));
        }
    }
}
