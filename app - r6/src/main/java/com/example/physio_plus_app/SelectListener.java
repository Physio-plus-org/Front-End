package com.example.physio_plus_app;

public interface SelectListener {
    default void onRemoveItemClicked(int eventID) {}
    default void onCompleteItemClicked(int eventID){}
}
