package ru.pingvn.trevalcalc.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import ru.pingvn.trevalcalc.Adapters.AdapterTouristList;

import ru.pingvn.trevalcalc.R;

public class SwipeToDeleteTourist extends ItemTouchHelper.SimpleCallback {
    private AdapterTouristList mAdapter;
    private Drawable mIcon;
    private final ColorDrawable mBackground;

//--------------------------------------------------------------------------------------------------

    public SwipeToDeleteTourist(AdapterTouristList mTAdapter, Context mContex) {
        //первый параметр добавляет возможность перетаскивать, не перетаскевает установленно 0
        //второй параметр говорит что можно свайпать в лево и в право
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        //присваиваю адаптер от recyclerview
        this.mAdapter = mTAdapter;
        //привязываю иконку и добавляю цвет заднего фона
        mIcon = ContextCompat.getDrawable(mContex, R.drawable.ic_delete_button);
        mBackground = new ColorDrawable(Color.RED);
    }
//--------------------------------------------------------------------------------------------------


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View mItemView = viewHolder.itemView;
        int mBackgroundCornerOffset = 20;
        int mIconMArign = (mItemView.getHeight() - mIcon.getIntrinsicHeight()) / 2;
        int mIconTop = mItemView.getTop() + (mItemView.getHeight() - mIcon.getIntrinsicHeight()) / 2;
        int mIconBottom = mIconTop + mIcon.getIntrinsicHeight();
        //------------------------------------------------------------------------------------------

        //свайп в право
        if (dX > 0) {
            int mIconLeft = mItemView.getLeft() + mIconMArign;
            int mIconRight =mIconLeft + mIcon.getIntrinsicWidth();
            mIcon.setBounds(mIconLeft, mIconTop, mIconRight, mIconBottom);
            mBackground.setBounds(mItemView.getLeft(), mItemView.getTop(), mItemView.getLeft()+((int)dX)-mBackgroundCornerOffset, mItemView.getBottom());
        } else if (dX < 0) {
            int mIconLeft = mItemView.getRight()-mIconMArign-mIcon.getIntrinsicWidth();
            int mIconRight =mItemView.getRight()-mIconMArign;
            mIcon.setBounds(mIconLeft, mIconTop, mIconRight, mIconBottom);
            mBackground.setBounds(mItemView.getRight()+((int)dX)-mBackgroundCornerOffset,mItemView.getTop(),mItemView.getRight(),mItemView.getBottom());
        }else{
            mBackground.setBounds(0,0,0,0);
        }
        mBackground.draw(c);
        mIcon.draw(c);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //добавляем действие при свайпе, у меня удаление элемента
        //получаю позицию элемента на котором свайпнули в списке
        int mPosition = viewHolder.getAdapterPosition();
        //удаление из адаптера
        mAdapter.removeItem(mPosition);
    }
}
