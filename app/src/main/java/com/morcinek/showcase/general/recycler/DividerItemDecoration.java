package com.morcinek.showcase.general.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.morcinek.showcase.R;


public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Drawable divider;
    private int dividerHeight;
    private int dividerWidth;
    private boolean first = false;
    private boolean last = false;

    public DividerItemDecoration(Context context) {
        this(context, R.drawable.default_divider);
    }

    @SuppressWarnings("deprecation")
    public DividerItemDecoration(Context context, int dividerDrawableId) {
        divider = context.getResources().getDrawable(dividerDrawableId);
        dividerHeight = divider == null ? 0 : divider.getIntrinsicHeight();
        dividerWidth = divider == null ? 0 : divider.getIntrinsicWidth();
        first = true;
        last = false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (divider == null) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }

        final int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        final boolean firstItem = position == 0;
        final boolean lastItem = position == parent.getAdapter().getItemCount() - 1;
        final boolean dividerBefore = first || !firstItem;
        final boolean dividerAfter = last && lastItem;

        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            outRect.top = dividerBefore ? dividerHeight : 0;
            outRect.bottom = dividerAfter ? dividerHeight : 0;
        } else {
            outRect.left = dividerBefore ? dividerWidth : 0;
            outRect.right = dividerAfter ? dividerWidth : 0;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (divider == null) {
            super.onDraw(c, parent, state);
            return;
        }

        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        final int orientation = getOrientation(parent);
        final int childCount = parent.getChildCount();

        final RecyclerView.Adapter adapter = parent.getAdapter();
        final int adapterCount = adapter != null ? adapter.getItemCount() : 0;

        final boolean vertical = orientation == LinearLayoutManager.VERTICAL;
        final int size;
        if (vertical) {
            size = dividerHeight;
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
        } else {
            size = dividerWidth;
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
        }

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int position = params.getViewLayoutPosition();
            if (position == 0 && !first) {
                continue;
            }
            if (vertical) {
                top = child.getTop() - params.topMargin - size;
                bottom = top + size;
            } else {
                left = child.getLeft() - params.leftMargin - size;
                right = left + size;
            }
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }

        if (last && childCount > 0) {
            final View child = parent.getChildAt(childCount - 1);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int position = params.getViewLayoutPosition();
            if (position == adapterCount - 1) {
                if (vertical) {
                    top = child.getBottom() + params.bottomMargin;
                    bottom = top + size;
                } else {
                    left = child.getRight() + params.rightMargin;
                    right = left + size;
                }
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }

    private int getOrientation(RecyclerView parent) {
        final RecyclerView.LayoutManager lm = parent.getLayoutManager();
        if (lm instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) lm).getOrientation();
        } else {
            throw new IllegalStateException("DividerItemDecoration can only be used with a LinearLayoutManager");
        }
    }
}