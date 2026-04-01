package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator<BackStackRecordState> CREATOR = new Parcelable.Creator<BackStackRecordState>() {
        public BackStackRecordState createFromParcel(Parcel parcel) {
            return new BackStackRecordState(parcel);
        }

        public BackStackRecordState[] newArray(int i2) {
            return new BackStackRecordState[i2];
        }
    };
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int[] mCurrentMaxLifecycleStates;
    final ArrayList<String> mFragmentWhos;
    final int mIndex;
    final String mName;
    final int[] mOldMaxLifecycleStates;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList<String> mSharedElementSourceNames;
    final ArrayList<String> mSharedElementTargetNames;
    final int mTransition;

    public BackStackRecordState(BackStackRecord backStackRecord) {
        int size = backStackRecord.mOps.size();
        this.mOps = new int[(size * 6)];
        if (backStackRecord.mAddToBackStack) {
            this.mFragmentWhos = new ArrayList<>(size);
            this.mOldMaxLifecycleStates = new int[size];
            this.mCurrentMaxLifecycleStates = new int[size];
            int i2 = 0;
            for (int i7 = 0; i7 < size; i7++) {
                FragmentTransaction.Op op = backStackRecord.mOps.get(i7);
                int i8 = i2 + 1;
                this.mOps[i2] = op.mCmd;
                ArrayList<String> arrayList = this.mFragmentWhos;
                Fragment fragment = op.mFragment;
                arrayList.add(fragment != null ? fragment.mWho : null);
                int[] iArr = this.mOps;
                iArr[i8] = op.mFromExpandedOp;
                iArr[i2 + 2] = op.mEnterAnim;
                iArr[i2 + 3] = op.mExitAnim;
                int i10 = i2 + 5;
                iArr[i2 + 4] = op.mPopEnterAnim;
                i2 += 6;
                iArr[i10] = op.mPopExitAnim;
                this.mOldMaxLifecycleStates[i7] = op.mOldMaxState.ordinal();
                this.mCurrentMaxLifecycleStates[i7] = op.mCurrentMaxState.ordinal();
            }
            this.mTransition = backStackRecord.mTransition;
            this.mName = backStackRecord.mName;
            this.mIndex = backStackRecord.mIndex;
            this.mBreadCrumbTitleRes = backStackRecord.mBreadCrumbTitleRes;
            this.mBreadCrumbTitleText = backStackRecord.mBreadCrumbTitleText;
            this.mBreadCrumbShortTitleRes = backStackRecord.mBreadCrumbShortTitleRes;
            this.mBreadCrumbShortTitleText = backStackRecord.mBreadCrumbShortTitleText;
            this.mSharedElementSourceNames = backStackRecord.mSharedElementSourceNames;
            this.mSharedElementTargetNames = backStackRecord.mSharedElementTargetNames;
            this.mReorderingAllowed = backStackRecord.mReorderingAllowed;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    private void fillInBackStackRecord(BackStackRecord backStackRecord) {
        int i2 = 0;
        int i7 = 0;
        while (true) {
            boolean z = true;
            if (i2 < this.mOps.length) {
                FragmentTransaction.Op op = new FragmentTransaction.Op();
                int i8 = i2 + 1;
                op.mCmd = this.mOps[i2];
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + i7 + " base fragment #" + this.mOps[i8]);
                }
                op.mOldMaxState = Lifecycle.State.values()[this.mOldMaxLifecycleStates[i7]];
                op.mCurrentMaxState = Lifecycle.State.values()[this.mCurrentMaxLifecycleStates[i7]];
                int[] iArr = this.mOps;
                int i10 = i2 + 2;
                if (iArr[i8] == 0) {
                    z = false;
                }
                op.mFromExpandedOp = z;
                int i11 = iArr[i10];
                op.mEnterAnim = i11;
                int i12 = iArr[i2 + 3];
                op.mExitAnim = i12;
                int i13 = i2 + 5;
                int i14 = iArr[i2 + 4];
                op.mPopEnterAnim = i14;
                i2 += 6;
                int i15 = iArr[i13];
                op.mPopExitAnim = i15;
                backStackRecord.mEnterAnim = i11;
                backStackRecord.mExitAnim = i12;
                backStackRecord.mPopEnterAnim = i14;
                backStackRecord.mPopExitAnim = i15;
                backStackRecord.addOp(op);
                i7++;
            } else {
                backStackRecord.mTransition = this.mTransition;
                backStackRecord.mName = this.mName;
                backStackRecord.mAddToBackStack = true;
                backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
                backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
                backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
                backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
                backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
                backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
                backStackRecord.mReorderingAllowed = this.mReorderingAllowed;
                return;
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManager fragmentManager) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        fillInBackStackRecord(backStackRecord);
        backStackRecord.mIndex = this.mIndex;
        for (int i2 = 0; i2 < this.mFragmentWhos.size(); i2++) {
            String str = this.mFragmentWhos.get(i2);
            if (str != null) {
                backStackRecord.mOps.get(i2).mFragment = fragmentManager.findActiveFragment(str);
            }
        }
        backStackRecord.bumpBackStackNesting(1);
        return backStackRecord;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.mOps);
        parcel.writeStringList(this.mFragmentWhos);
        parcel.writeIntArray(this.mOldMaxLifecycleStates);
        parcel.writeIntArray(this.mCurrentMaxLifecycleStates);
        parcel.writeInt(this.mTransition);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(this.mSharedElementSourceNames);
        parcel.writeStringList(this.mSharedElementTargetNames);
        parcel.writeInt(this.mReorderingAllowed ? 1 : 0);
    }

    public BackStackRecordState(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mFragmentWhos = parcel.createStringArrayList();
        this.mOldMaxLifecycleStates = parcel.createIntArray();
        this.mCurrentMaxLifecycleStates = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.mBreadCrumbTitleText = (CharSequence) creator.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) creator.createFromParcel(parcel);
        this.mSharedElementSourceNames = parcel.createStringArrayList();
        this.mSharedElementTargetNames = parcel.createStringArrayList();
        this.mReorderingAllowed = parcel.readInt() != 0;
    }
}
