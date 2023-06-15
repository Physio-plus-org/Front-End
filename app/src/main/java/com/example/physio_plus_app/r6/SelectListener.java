package com.example.physio_plus_app.r6;

public interface SelectListener {
    default void onRemoveItemClicked(int eventID) {}
    default void onCompleteItemClicked(int eventID){}
}
