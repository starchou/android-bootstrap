package com.donnfelker.android.bootstrap.ui;

import android.view.LayoutInflater;

import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.News;
import com.donnfelker.android.bootstrap.core.Workout;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.List;

public class WorkoutListAdapter extends AlternatingColorListAdapter<Workout> {

    public WorkoutListAdapter(LayoutInflater inflater, List<Workout> items) {
        super(R.layout.workout_list_item, inflater, items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[] { R.id.tv_name, R.id.tv_description };
    }

    @Override
    protected void update(int position, Workout item) {
        super.update(position, item);

        setText(0, item.getName());
        setText(1, item.getDescription());
    }
}
