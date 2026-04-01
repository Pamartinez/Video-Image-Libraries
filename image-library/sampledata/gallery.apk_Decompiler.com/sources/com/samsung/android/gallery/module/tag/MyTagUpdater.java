package com.samsung.android.gallery.module.tag;

import A.a;
import android.content.Context;
import android.database.ContentObserver;
import android.os.AsyncTask;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MyTagUpdater extends AsyncTask<Void, Void, Void> {
    private final ArrayList<String> mAdded;
    private final Blackboard mBlackboard;
    private final ArrayList<String> mDeleted;
    private final MediaItem mItem;
    private final WeakReference<Context> mRef;

    public MyTagUpdater(Context context, Blackboard blackboard, MediaItem mediaItem, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        this.mRef = new WeakReference<>(context);
        this.mBlackboard = blackboard;
        this.mItem = mediaItem;
        this.mAdded = arrayList;
        this.mDeleted = arrayList2;
    }

    private void notifyChange() {
        Context context = this.mRef.get();
        if (context != null) {
            if (!PocFeatures.isEnabled(PocFeatures.GmpAll)) {
                context.getContentResolver().notifyChange(MediaUri.getInstance().getImageWatchUri(), (ContentObserver) null);
                BlackboardUtils.publishDataChangedToOtherActivities(this.mBlackboard, true);
            }
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3052));
        }
    }

    private void update() {
        updateTagDb(this.mItem.getFileId(), this.mAdded, this.mDeleted);
    }

    private void updateTagDb(long j2, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        ArrayList<String> arrayList3;
        long j3;
        ArrayList<String> arrayList4;
        IntelligentSearchIndex instance = IntelligentSearchIndex.getInstance();
        if (!arrayList.isEmpty()) {
            new TagEditApi().insertMyTagArray(arrayList, j2, true);
            j3 = j2;
            ArrayList<String> arrayList5 = arrayList;
            instance.indexing(j3, (Collection<String>) arrayList5, IntelligentSearchIndex.TagType.USER_TAG, IntelligentSearchIndex.IndexMode.APPEND);
            arrayList3 = arrayList5;
        } else {
            j3 = j2;
            arrayList3 = arrayList;
        }
        if (!arrayList2.isEmpty()) {
            new TagEditApi().bulkDeleteMyTag(arrayList2, j3, true);
            arrayList4 = arrayList2;
            instance.indexing(j3, (Collection<String>) arrayList4, IntelligentSearchIndex.TagType.USER_TAG, IntelligentSearchIndex.IndexMode.REMOVE);
        } else {
            arrayList4 = arrayList2;
        }
        if ((!arrayList3.isEmpty() || !arrayList4.isEmpty()) && LocationKey.isSearchPictures(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard))) {
            this.mBlackboard.postBroadcastEvent(EventMessage.obtain(103));
        }
    }

    public Void doInBackground(Void... voidArr) {
        try {
            if (!this.mAdded.isEmpty() || !this.mDeleted.isEmpty()) {
                update();
                notifyChange();
                return null;
            }
            Log.w("MyTagUpdater", "skip add tag, nothing changed.");
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to update tag, e="), "MyTagUpdater");
        }
    }
}
