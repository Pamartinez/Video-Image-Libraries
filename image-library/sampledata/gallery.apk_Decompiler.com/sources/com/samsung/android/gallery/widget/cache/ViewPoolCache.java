package com.samsung.android.gallery.widget.cache;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPoolCache {
    private static volatile ViewPoolCache sInstance;
    protected ConcurrentLinkedQueue<View> mContentViewTypePool;
    protected RecyclerView.RecycledViewPool mViewPool;

    private ViewPoolCache() {
    }

    public static ViewPoolCache getInstance() {
        if (sInstance == null) {
            synchronized (ViewPoolCache.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ViewPoolCache();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static void releaseInstance() {
        synchronized (ViewPoolCache.class) {
            try {
                if (sInstance != null) {
                    sInstance.clearViewPool();
                    sInstance = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void cacheContentTypeView(View view) {
        ConcurrentLinkedQueue<View> concurrentLinkedQueue = this.mContentViewTypePool;
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.add(view);
        }
    }

    public void clearViewPool() {
        Log.d("ViewPoolCache", "clearViewPool");
        RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
        if (recycledViewPool != null) {
            recycledViewPool.clear();
            this.mViewPool = null;
        }
        ConcurrentLinkedQueue<View> concurrentLinkedQueue = this.mContentViewTypePool;
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.clear();
            this.mContentViewTypePool = null;
        }
    }

    public void closeContentTypeViewPool() {
        ConcurrentLinkedQueue<View> concurrentLinkedQueue = this.mContentViewTypePool;
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.clear();
            this.mContentViewTypePool = null;
        }
    }

    public View getCachedContentTypeView() {
        ConcurrentLinkedQueue<View> concurrentLinkedQueue = this.mContentViewTypePool;
        if (concurrentLinkedQueue != null) {
            return concurrentLinkedQueue.poll();
        }
        return null;
    }

    public int getViewPoolCount() {
        RecyclerView.RecycledViewPool recycledViewPool = this.mViewPool;
        if (recycledViewPool != null) {
            return recycledViewPool.getRecycledViewCount(0);
        }
        return 0;
    }

    public void openContentTypeViewPool() {
        if (this.mContentViewTypePool == null) {
            this.mContentViewTypePool = new ConcurrentLinkedQueue<>();
        }
    }

    public void init() {
    }
}
