package com.annarbortees.ru_in.dashboard;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.annarbortees.ru_in.R;
import com.annarbortees.ru_in.Team;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nigel on 9/3/15.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    ViewGroup mParent;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)
                parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mParent = parent;
        View view = inflater.inflate(R.layout.team_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = Team.getTeamList()[position];
        holder.mTextView.setText(team.nickname);
        Picasso.with(mParent.getContext()).load(team.logoAddress).into(holder.mTeamLogo);
    }

    @Override
    public int getItemCount() {
        return Team.getTeamList().length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.info_text) public TextView mTextView;
        @Bind(R.id.card) public CardView mCardView;
        @Bind(R.id.team_logo) public ImageView mTeamLogo;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
