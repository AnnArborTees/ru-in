package com.annarbortees.ru_in.dashboard;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annarbortees.ru_in.Dashboard;
import com.annarbortees.ru_in.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nigel on 9/3/15.
 */
public class TeamFragment extends Fragment {
    RecyclerView mRecycler;

    public TeamFragment() {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.team_view, container, false);

        mRecycler = (RecyclerView) rootView.findViewById(R.id.recycler);

        mRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecycler.setAdapter(new TeamAdapter());

        return rootView;
    }
}
