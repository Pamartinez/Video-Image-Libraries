package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
        public SavedState[] newArray(int i2) {
            return new SavedState[i2];
        }

        public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new SavedState(parcel, classLoader);
        }

        public SavedState createFromParcel(Parcel parcel) {
            return new SavedState(parcel, (ClassLoader) null);
        }
    };
    boolean fitToContents;
    int peekHeight;
    final int state;

    public SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.state = parcel.readInt();
        this.peekHeight = parcel.readInt();
        this.fitToContents = parcel.readInt() != 1 ? false : true;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.state);
        parcel.writeInt(this.peekHeight);
        parcel.writeInt(this.fitToContents ? 1 : 0);
    }

    public SavedState(Parcelable parcelable, StoryHighlightBehavior<?> storyHighlightBehavior) {
        super(parcelable);
        this.state = storyHighlightBehavior.state;
        this.peekHeight = storyHighlightBehavior.peekHeight;
        this.fitToContents = storyHighlightBehavior.fitToContents;
    }
}
