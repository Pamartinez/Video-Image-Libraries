package com.samsung.android.gallery.app.ui.container.clipboard;

import N2.j;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.container.clipboard.IClipboardView;
import com.samsung.android.gallery.app.ui.list.picker.PickerMenuFactory;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.PickerMenuHandler;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ClipboardPresenter<V extends IClipboardView> extends MvpBasePresenter<V> {
    private ClipboardViewAdapter mClipboardViewAdapter;
    private final RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            ClipboardPresenter.this.updateClipboardViewVisible(true);
        }

        public void onItemRangeInserted(int i2, int i7) {
            ClipboardPresenter.this.updateClipboardViewVisible(true);
        }

        public void onItemRangeRemoved(int i2, int i7) {
            ClipboardPresenter.this.updateClipboardViewVisible(false);
        }
    };

    public ClipboardPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        Clipboard.getInstance(blackboard).clear();
    }

    private void addItems(ArrayList<MediaItem> arrayList) {
        if (!arrayList.isEmpty()) {
            if (isSelectionWithDragging() || arrayList.size() != 1) {
                Iterator<MediaItem> it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mClipboardViewAdapter.add(it.next());
                }
                this.mClipboardViewAdapter.notifyDataSetChanged();
            } else if (this.mClipboardViewAdapter.add(arrayList.get(0))) {
                ClipboardViewAdapter clipboardViewAdapter = this.mClipboardViewAdapter;
                clipboardViewAdapter.notifyItemInserted(clipboardViewAdapter.getItemCount() - 1);
            }
            StringCompat stringCompat = this.TAG;
            Log.pk(stringCompat, "addItems" + Logger.v(Integer.valueOf(this.mClipboardViewAdapter.getItemCount()), j.g(new StringBuilder("+"), arrayList)));
        }
    }

    private boolean isSelectionWithDragging() {
        Object read = this.mBlackboard.read("data://dragging_selection");
        if (read == null || !((Boolean) read).booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void operateClipboard(Object obj, Bundle bundle) {
        boolean z;
        IClipboardView iClipboardView = (IClipboardView) this.mView;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (bundle.getString("clipboard_no_animation") == null) {
            z = true;
        } else {
            z = false;
        }
        iClipboardView.operateClipboard(booleanValue, z);
    }

    private void removeItems(ArrayList<MediaItem> arrayList) {
        if (!arrayList.isEmpty()) {
            this.mClipboardViewAdapter.removeItems(arrayList, isSelectionWithDragging());
            StringCompat stringCompat = this.TAG;
            Log.pk(stringCompat, "removeItems" + Logger.v(Integer.valueOf(this.mClipboardViewAdapter.getItemCount()), j.g(new StringBuilder("-"), arrayList)));
        }
    }

    private void updateClipboardViewItemOnDataChanged() {
        MediaItem[] allItems = this.mClipboardViewAdapter.getAllItems();
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : allItems) {
            if (Clipboard.getInstance(this.mBlackboard).contains(Long.valueOf(mediaItem.getFileId()))) {
                arrayList.add(mediaItem);
            }
        }
        int size = arrayList.size();
        if (allItems.length != size) {
            this.mClipboardViewAdapter.clear();
            if (size > 0) {
                this.mClipboardViewAdapter.addAll((Collection<MediaItem>) arrayList);
            }
            this.mClipboardViewAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void updateClipboardViewVisible(boolean z) {
        if (this.mClipboardViewAdapter.getItemCount() <= 0) {
            ((IClipboardView) this.mView).setClipboardViewVisibility(8);
        } else if (!PickerUtil.supportSearch() || ((IClipboardView) this.mView).isOpened()) {
            ((IClipboardView) this.mView).setClipboardViewVisibility(0);
            if (((IClipboardView) this.mView).getClipboardView() != null && z) {
                ((IClipboardView) this.mView).getClipboardView().scrollToPosition(this.mClipboardViewAdapter.getItemCount() - 1);
            }
        }
    }

    private void updateDoneMenu(Menu menu) {
        boolean z;
        MenuItem findItem = menu.findItem(R.id.action_done);
        if (findItem != null) {
            int totalCount = Clipboard.getInstance(this.mBlackboard).getTotalCount();
            boolean z3 = false;
            if (totalCount > 0) {
                z = true;
            } else {
                z = false;
            }
            findItem.setVisible(z);
            if (totalCount > 0) {
                z3 = true;
            }
            findItem.setEnabled(z3);
        }
    }

    /* access modifiers changed from: private */
    public void updateSearchMenu(Object obj, Bundle bundle) {
        ((IClipboardView) this.mView).invalidateOptionsMenu();
    }

    public MenuDataBinding createMenuDataBinding() {
        return PickerMenuFactory.create(this.mBlackboard, (String) null);
    }

    public MenuHandler createMenuHandler() {
        return new PickerMenuHandler();
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command://event/UpdatePickerSearchMenu", new c(this, 0)));
        arrayList.add(new SubscriberInfo("command://OperateClipboardArea", new c(this, 1)).setWorkingOnUI());
    }

    public void destroy() {
        Clipboard.getInstance(this.mBlackboard).clear();
    }

    public int getSelectedItemCount() {
        return this.mClipboardViewAdapter.getItemCount();
    }

    public MediaItem[] getSelectedItems() {
        return this.mClipboardViewAdapter.getAllItems();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1020) {
            addItems((ArrayList) eventMessage.obj);
            return true;
        } else if (i2 == 1021) {
            removeItems((ArrayList) eventMessage.obj);
            return true;
        } else if (i2 == 1028) {
            ((IClipboardView) this.mView).setScreenId((String) eventMessage.obj);
            return true;
        } else if (i2 == 1073) {
            updateClipboardViewItemOnDataChanged();
            return true;
        } else if (i2 != 1086) {
            return false;
        } else {
            ((IClipboardView) this.mView).updateClipboardBackground(((Boolean) eventMessage.obj).booleanValue());
            return true;
        }
    }

    public boolean handleTouchEvent(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            this.mClipboardViewAdapter.setTouchEffectSwitch(false);
        }
        return false;
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        ClipboardViewAdapter clipboardViewAdapter = new ClipboardViewAdapter(this.mBlackboard);
        this.mClipboardViewAdapter = clipboardViewAdapter;
        clipboardViewAdapter.registerAdapterDataObserver(this.mDataObserver);
        ((IClipboardView) this.mView).getClipboardView().setAdapter(this.mClipboardViewAdapter);
    }

    public void onViewDestroy() {
        ClipboardViewAdapter clipboardViewAdapter = this.mClipboardViewAdapter;
        if (clipboardViewAdapter != null) {
            clipboardViewAdapter.unregisterAdapterDataObserver(this.mDataObserver);
        }
        super.onViewDestroy();
    }

    public void prepareOptionsMenu(Menu menu) {
        super.prepareOptionsMenu(menu);
        updateDoneMenu(menu);
    }

    public void restore() {
        MediaItem[] allItems = this.mClipboardViewAdapter.getAllItems();
        this.mClipboardViewAdapter.clear();
        ClipboardViewAdapter clipboardViewAdapter = new ClipboardViewAdapter(this.mBlackboard);
        this.mClipboardViewAdapter = clipboardViewAdapter;
        clipboardViewAdapter.registerAdapterDataObserver(this.mDataObserver);
        ((IClipboardView) this.mView).getClipboardView().setAdapter(this.mClipboardViewAdapter);
        if (allItems.length > 0) {
            this.mClipboardViewAdapter.addAll(allItems);
        }
        updateClipboardViewVisible(true);
        this.mClipboardViewAdapter.notifyDataSetChanged();
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setNavigationIcon((Drawable) null);
    }
}
