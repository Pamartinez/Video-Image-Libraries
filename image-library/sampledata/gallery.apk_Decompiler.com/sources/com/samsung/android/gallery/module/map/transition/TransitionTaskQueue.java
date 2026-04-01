package com.samsung.android.gallery.module.map.transition;

import com.samsung.android.gallery.module.map.transition.AbsTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionTaskQueue {
    private final ArrayList<Queue<AbsTask>> mTasksQueue = new ArrayList<>();

    public TransitionTaskQueue() {
        for (AbsTask.TASK_PRIORITY ordinal : AbsTask.TASK_PRIORITY.values()) {
            this.mTasksQueue.add(ordinal.ordinal(), new LinkedList());
        }
    }

    public void addTask(AbsTask absTask) {
        this.mTasksQueue.get(absTask.mPriority.ordinal()).add(absTask);
    }

    public void clear() {
        Iterator<Queue<AbsTask>> it = this.mTasksQueue.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.mTasksQueue.clear();
    }

    public boolean isEmpty() {
        Iterator<Queue<AbsTask>> it = this.mTasksQueue.iterator();
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void performNextTask(TransitionQueueScheduler transitionQueueScheduler) {
        AbsTask absTask;
        Iterator<Queue<AbsTask>> it = this.mTasksQueue.iterator();
        while (it.hasNext()) {
            Queue next = it.next();
            if (!next.isEmpty() && (absTask = (AbsTask) next.peek()) != null && absTask.isReady()) {
                next.remove(absTask);
                absTask.perform(transitionQueueScheduler);
                absTask.destroy();
            }
        }
    }
}
