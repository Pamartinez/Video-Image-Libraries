package com.samsung.android.gallery.app.ui.container.tablet;

import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.samsung.android.gallery.app.ui.container.abstraction.IListContainerView;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import h.C0199b;
import i.C0212a;
import java.util.Iterator;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ListContainerDebugger {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$printChildFragments$0(FragmentManager fragmentManager, Stack stack) {
        String str = "";
        for (Fragment tag : fragmentManager.getFragments()) {
            String tag2 = tag.getTag();
            if (LocationKey.isTimeline(tag2)) {
                str = C0212a.A(str, "TIMELINE, ");
            } else if (LocationKey.isAlbums(tag2)) {
                str = C0212a.A(str, "ALBUMS, ");
            } else if (LocationKey.isStories(tag2)) {
                str = C0212a.A(str, "STORIES, ");
            } else if (LocationKey.isVideoPictures(tag2)) {
                str = C0212a.A(str, "VIDEOS, ");
            } else if (LocationKey.isFavoritePictures(tag2)) {
                str = C0212a.A(str, "FAVOURITES, ");
            } else if (LocationKey.isRecentlyPictures(tag2)) {
                str = C0212a.A(str, "RECENT, ");
            } else if (LocationKey.isSuggests(tag2)) {
                str = C0212a.A(str, "SUGGESTIONS, ");
            } else if (LocationKey.isSearchCategoryLocation(tag2)) {
                str = C0212a.A(str, "LOCATION, ");
            } else if (LocationKey.isSharings(tag2)) {
                str = C0212a.A(str, "SHARED, ");
            } else if (LocationKey.isTrash(tag2)) {
                str = C0212a.A(str, "TRASH, ");
            } else if (LocationKey.isMtp(tag2)) {
                str = C0212a.A(str, "MTP, ");
            }
        }
        if (TextUtils.isEmpty(str)) {
            new InternalException("There is no root back stack.").post();
        }
        printChildFragments((Stack<String>) stack, str);
    }

    public static void printChildFragments(IListContainerView iListContainerView, Stack<String> stack) {
        ThreadUtil.postOnBgThreadDelayed(new C0199b(13, iListContainerView.getChildFragmentManager(), stack), 500);
    }

    private static void printChildFragments(Stack<String> stack, String str) {
        StringBuilder sb2 = new StringBuilder();
        sb2.insert(0, "\n" + str);
        Iterator<String> it = stack.iterator();
        while (it.hasNext()) {
            sb2.insert(0, "\n" + it.next());
        }
        sb2.insert(0, "========== ListContainerFragment BackStackRecord ===========");
        Log.w("ListContainerDebugger", sb2.toString());
    }
}
