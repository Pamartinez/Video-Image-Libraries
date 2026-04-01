package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.Lifecycle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() {
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        public FragmentState[] newArray(int i2) {
            return new FragmentState[i2];
        }
    };
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final boolean mInDynamicContainer;
    final int mMaxLifecycleState;
    final boolean mRemoving;
    final boolean mRetainInstance;
    final String mTag;
    final int mTargetRequestCode;
    final String mTargetWho;
    final boolean mUserVisibleHint;
    final String mWho;

    public FragmentState(Fragment fragment) {
        this.mClassName = fragment.getClass().getName();
        this.mWho = fragment.mWho;
        this.mFromLayout = fragment.mFromLayout;
        this.mInDynamicContainer = fragment.mInDynamicContainer;
        this.mFragmentId = fragment.mFragmentId;
        this.mContainerId = fragment.mContainerId;
        this.mTag = fragment.mTag;
        this.mRetainInstance = fragment.mRetainInstance;
        this.mRemoving = fragment.mRemoving;
        this.mDetached = fragment.mDetached;
        this.mHidden = fragment.mHidden;
        this.mMaxLifecycleState = fragment.mMaxState.ordinal();
        this.mTargetWho = fragment.mTargetWho;
        this.mTargetRequestCode = fragment.mTargetRequestCode;
        this.mUserVisibleHint = fragment.mUserVisibleHint;
    }

    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentFactory fragmentFactory, ClassLoader classLoader) {
        Fragment instantiate = fragmentFactory.instantiate(classLoader, this.mClassName);
        instantiate.mWho = this.mWho;
        instantiate.mFromLayout = this.mFromLayout;
        instantiate.mInDynamicContainer = this.mInDynamicContainer;
        instantiate.mRestored = true;
        instantiate.mFragmentId = this.mFragmentId;
        instantiate.mContainerId = this.mContainerId;
        instantiate.mTag = this.mTag;
        instantiate.mRetainInstance = this.mRetainInstance;
        instantiate.mRemoving = this.mRemoving;
        instantiate.mDetached = this.mDetached;
        instantiate.mHidden = this.mHidden;
        instantiate.mMaxState = Lifecycle.State.values()[this.mMaxLifecycleState];
        instantiate.mTargetWho = this.mTargetWho;
        instantiate.mTargetRequestCode = this.mTargetRequestCode;
        instantiate.mUserVisibleHint = this.mUserVisibleHint;
        return instantiate;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("FragmentState{");
        sb2.append(this.mClassName);
        sb2.append(" (");
        sb2.append(this.mWho);
        sb2.append(")}:");
        if (this.mFromLayout) {
            sb2.append(" fromLayout");
        }
        if (this.mInDynamicContainer) {
            sb2.append(" dynamicContainer");
        }
        if (this.mContainerId != 0) {
            sb2.append(" id=0x");
            sb2.append(Integer.toHexString(this.mContainerId));
        }
        String str = this.mTag;
        if (str != null && !str.isEmpty()) {
            sb2.append(" tag=");
            sb2.append(this.mTag);
        }
        if (this.mRetainInstance) {
            sb2.append(" retainInstance");
        }
        if (this.mRemoving) {
            sb2.append(" removing");
        }
        if (this.mDetached) {
            sb2.append(" detached");
        }
        if (this.mHidden) {
            sb2.append(" hidden");
        }
        if (this.mTargetWho != null) {
            sb2.append(" targetWho=");
            sb2.append(this.mTargetWho);
            sb2.append(" targetRequestCode=");
            sb2.append(this.mTargetRequestCode);
        }
        if (this.mUserVisibleHint) {
            sb2.append(" userVisibleHint");
        }
        return sb2.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mClassName);
        parcel.writeString(this.mWho);
        parcel.writeInt(this.mFromLayout ? 1 : 0);
        parcel.writeInt(this.mInDynamicContainer ? 1 : 0);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.mRetainInstance ? 1 : 0);
        parcel.writeInt(this.mRemoving ? 1 : 0);
        parcel.writeInt(this.mDetached ? 1 : 0);
        parcel.writeInt(this.mHidden ? 1 : 0);
        parcel.writeInt(this.mMaxLifecycleState);
        parcel.writeString(this.mTargetWho);
        parcel.writeInt(this.mTargetRequestCode);
        parcel.writeInt(this.mUserVisibleHint ? 1 : 0);
    }

    public FragmentState(Parcel parcel) {
        this.mClassName = parcel.readString();
        this.mWho = parcel.readString();
        boolean z = false;
        this.mFromLayout = parcel.readInt() != 0;
        this.mInDynamicContainer = parcel.readInt() != 0;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        this.mRetainInstance = parcel.readInt() != 0;
        this.mRemoving = parcel.readInt() != 0;
        this.mDetached = parcel.readInt() != 0;
        this.mHidden = parcel.readInt() != 0;
        this.mMaxLifecycleState = parcel.readInt();
        this.mTargetWho = parcel.readString();
        this.mTargetRequestCode = parcel.readInt();
        this.mUserVisibleHint = parcel.readInt() != 0 ? true : z;
    }
}
