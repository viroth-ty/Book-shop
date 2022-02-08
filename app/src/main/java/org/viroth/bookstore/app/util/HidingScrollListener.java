package org.viroth.bookstore.app.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager layoutManager;

    public HidingScrollListener(@NonNull LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int pos = layoutManager.findFirstVisibleItemPosition();

        if(dy < 0) {
            if(layoutManager.findFirstVisibleItemPosition()==0){
                onShow();
            }
        } else {
            onHide();
        }
    }

    public abstract void onHide();

    public abstract void onShow();

}