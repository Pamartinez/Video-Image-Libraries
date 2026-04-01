package androidx.lifecycle;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData(T t) {
        super(t);
    }

    public void postValue(T t) {
        super.postValue(t);
    }

    public void setValue(T t) {
        super.setValue(t);
    }

    public MutableLiveData() {
    }
}
