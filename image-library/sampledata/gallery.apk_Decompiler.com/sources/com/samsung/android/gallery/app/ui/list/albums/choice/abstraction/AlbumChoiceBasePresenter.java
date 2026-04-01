package com.samsung.android.gallery.app.ui.list.albums.choice.abstraction;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.choice.abstraction.IAlbumChoiceBaseView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import i.C0212a;
import java.util.HashMap;
import java.util.Stack;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceBasePresenter<V extends IAlbumChoiceBaseView> extends AlbumsBasePresenter<V> {
    protected final HashMap<String, Integer> mLastScrollPosition = new HashMap<>();
    protected final Stack<String> mLocationKeyStack = new Stack<>();

    public AlbumChoiceBasePresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private String getLocationArgs(String str) {
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            return C0280e.d(1, indexOf, str);
        }
        return "";
    }

    private void refreshViewWithKey(String str) {
        refreshViewWithKey(str, 0);
    }

    public final String getPeekLocationStack() {
        return this.mLocationKeyStack.peek();
    }

    public final boolean isRootLocation() {
        if (this.mLocationKeyStack.size() == 1) {
            return true;
        }
        return false;
    }

    public boolean onBackPressed() {
        int i2 = 0;
        if (this.mLocationKeyStack.size() <= 1) {
            return false;
        }
        this.mLocationKeyStack.pop();
        String peek = this.mLocationKeyStack.peek();
        Integer remove = this.mLastScrollPosition.remove(peek);
        StringBuilder s = C0212a.s(peek);
        s.append(getLocationArgs(((IAlbumChoiceBaseView) this.mView).getLocationKey()));
        String sb2 = s.toString();
        if (remove != null) {
            i2 = remove.intValue();
        }
        refreshViewWithKey(sb2, i2);
        return true;
    }

    public void onFolderClicked(MediaItem mediaItem) {
        String removeArgs = ArgumentsUtil.removeArgs(((IAlbumChoiceBaseView) this.mView).getLocationKey());
        StringBuilder t = C0212a.t(removeArgs, "/");
        t.append(mediaItem.getFolderID());
        String sb2 = t.toString();
        this.mLocationKeyStack.push(sb2);
        this.mLastScrollPosition.put(removeArgs, Integer.valueOf(((IAlbumChoiceBaseView) this.mView).getListView().findFirstCompletelyVisibleItemPositionCompat()));
        refreshViewWithKey(sb2 + getLocationArgs(((IAlbumChoiceBaseView) this.mView).getLocationKey()));
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        String removeArgs = ArgumentsUtil.removeArgs(((IAlbumChoiceBaseView) this.mView).getLocationKey());
        if (this.mLocationKeyStack.isEmpty() || !this.mLocationKeyStack.peek().equals(removeArgs)) {
            this.mLocationKeyStack.push(removeArgs);
        }
    }

    private void refreshViewWithKey(String str, int i2) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        ((IAlbumChoiceBaseView) this.mView).refreshView(ArgumentsUtil.appendArgs(str, "id", removeArgs.substring(removeArgs.lastIndexOf(47) + 1)), i2);
    }
}
