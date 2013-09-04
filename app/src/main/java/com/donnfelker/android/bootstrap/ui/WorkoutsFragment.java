package com.donnfelker.android.bootstrap.ui;


import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.donnfelker.android.bootstrap.BootstrapServiceProvider;
import com.donnfelker.android.bootstrap.Injector;
import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.authenticator.LogoutService;
import com.donnfelker.android.bootstrap.core.Workout;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class WorkoutsFragment extends ItemListFragment<Workout> {

    @Inject protected BootstrapServiceProvider serviceProvider;
    @Inject protected LogoutService logoutService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_workouts);
    }


    @Override
    LogoutService getLogoutService() {
        return logoutService;
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        return R.string.error_loading_workouts;
    }

    @Override
    protected SingleTypeAdapter<Workout> createAdapter(List<Workout> items) {
        return new WorkoutListAdapter(getActivity().getLayoutInflater(), items);
    }

    @Override
    public Loader<List<Workout>> onCreateLoader(int id, Bundle args) {
        final List<Workout> initialItems = items;
        return new ThrowableLoader<List<Workout>>(getActivity(), items) {

            @Override
            public List<Workout> loadData() throws Exception {
                try {
                    if(getActivity() != null) {
                        return serviceProvider.getService(getActivity()).getWorkouts();
                    } else {
                        return Collections.emptyList();
                    }

                } catch (OperationCanceledException e) {
                    Activity activity = getActivity();
                    if (activity != null)
                        activity.finish();
                    return initialItems;
                }
            }
        };
    }
}
