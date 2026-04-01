package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import T5.a;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.story.StartHighlightPlayerCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesBasePresenter<V extends IStoriesBaseView> extends BaseListPresenter<V> {
    public StoriesBasePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    public void blockEvent(Object obj, Bundle bundle) {
        ((IStoriesBaseView) this.mView).setEnabledItemView(false);
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command://DeleteStories", new a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command://StoriesViewChanged", new a(this, 1)).setWorkingOnUI());
    }

    public int getCurrentViewDepth() {
        return ((IStoriesBaseView) this.mView).getListView().getDepth();
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(getTitleStringResId());
    }

    public int getTitleStringResId() {
        return R.string.settings_stories_category;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        ListViewHolder listViewHolder;
        int i2 = eventMessage.what;
        if (i2 == 102) {
            forceReloadData();
            return true;
        } else if (i2 != 1077) {
            return super.handleEvent(eventMessage);
        } else {
            GalleryListView listView = ((IStoriesBaseView) this.mView).getListView();
            int intValue = ((Integer) eventMessage.obj).intValue();
            if (listView != null) {
                int childCount = listView.getChildCount();
                int i7 = 0;
                while (true) {
                    if (i7 >= childCount) {
                        break;
                    }
                    View childAt = listView.getChildAt(i7);
                    if (childAt != null && (listViewHolder = (ListViewHolder) listView.findContainingViewHolder(childAt)) != null && listViewHolder.getMediaItem() != null && listViewHolder.getMediaItem().getAlbumID() == intValue) {
                        ViewUtils.setVisibility(listViewHolder.getDecoView(1), 8);
                        break;
                    }
                    i7++;
                }
            }
            return true;
        }
    }

    public boolean isAllowItemClick() {
        if (((IStoriesBaseView) this.mView).useAdvancedMouseDragAndDrop()) {
            Blackboard blackboard = this.mBlackboard;
            Boolean bool = Boolean.FALSE;
            if (((Boolean) blackboard.pop("data://motion_event_tool_type_mouse", bool)).booleanValue()) {
                return ((Boolean) this.mBlackboard.pop("data://gesture_on_double_tapped", bool)).booleanValue();
            }
        }
        return super.isAllowItemClick();
    }

    public void notifyDataChanged(MediaData mediaData) {
        super.notifyDataChanged(mediaData);
        ((IStoriesBaseView) this.mView).setEnabledItemView(true);
    }

    public void onHighlightVideoButtonClick(MediaItem mediaItem) {
        new StartHighlightPlayerCmd().execute(this, Integer.valueOf(mediaItem.getAlbumID()));
    }

    public void onViewChanged(Object obj, Bundle bundle) {
        StringCompat stringCompat = this.TAG;
        Log.v(stringCompat, "onViewChanged " + obj);
        changeViewDepth(((IStoriesBaseView) this.mView).getListView(), ((Integer) obj).intValue());
    }

    public void prepareOptionsMenu(Menu menu) {
        MenuDataBinding menuDataBinding = getMenuDataBinding();
        if (menuDataBinding != null) {
            menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_rename_story_album_in_list) {
                public boolean getDefaultEnabling() {
                    if (((Boolean) Optional.ofNullable(StoriesBasePresenter.this.getBlackboard().read("data://floating_pop_menu")).orElse(Boolean.FALSE)).booleanValue() || StoriesBasePresenter.this.getSelectedItemCount() == 1) {
                        return true;
                    }
                    return false;
                }

                public boolean getDefaultVisibility() {
                    return true;
                }
            });
        }
        super.prepareOptionsMenu(menu);
    }

    public void updateToolbar(Toolbar toolbar) {
        Context context = getContext();
        if (context != null) {
            ((IStoriesBaseView) this.mView).getAppbarLayout().setTitle((CharSequence) context.getString(getTitleStringResId()));
        }
        toolbar.setTitle((CharSequence) null);
        toolbar.setSubtitle((CharSequence) null);
    }
}
