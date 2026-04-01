package com.samsung.android.sdk.moneta.memory.entity.activity;

import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.Content;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/activity/Activity;", "Landroid/os/Parcelable;", "activityType", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "<init>", "(Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;)V", "getActivityType", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "id", "", "getId", "()Ljava/lang/String;", "contents", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "getContents", "()Ljava/util/List;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Activity implements Parcelable {
    private final ActivityType activityType;

    public Activity(ActivityType activityType2) {
        j.e(activityType2, "activityType");
        this.activityType = activityType2;
    }

    public ActivityType getActivityType() {
        return this.activityType;
    }

    public abstract List<Content> getContents();

    public abstract String getId();
}
