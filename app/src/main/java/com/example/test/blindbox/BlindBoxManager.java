package com.example.test.blindbox;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlindBoxManager {
    private static final BlindBoxManager mInstance = new BlindBoxManager();
    private IBlindBox mToyBlindBox;
    private final List<IEventListener> mEventListenerList = new CopyOnWriteArrayList<>();

    public interface IEventListener {
        void onToyBlindBoxChanged(IBlindBox toyBlindBox);
    }

    private BlindBoxManager() {

    }

    public static BlindBoxManager getInstance() {
        return mInstance;
    }

    public void addEventListener(IEventListener eventListener) {
        if (!mEventListenerList.contains(eventListener)) {
            mEventListenerList.add(eventListener);
        }
    }

    public void removeEventListener(IEventListener eventListener) {
        mEventListenerList.remove(eventListener);
    }

    public void setToyBlindBox(IBlindBox toyBlindBox) {
        IBlindBox oldToyBlindBox = mToyBlindBox;
        mToyBlindBox = toyBlindBox;
        if (oldToyBlindBox != toyBlindBox) {
            notifyToyBlindBoxChanged(toyBlindBox);
        }
    }

    private void notifyToyBlindBoxChanged(IBlindBox toyBlindBox) {
        for (IEventListener eventListener : mEventListenerList) {
            eventListener.onToyBlindBoxChanged(toyBlindBox);
        }
    }
}
