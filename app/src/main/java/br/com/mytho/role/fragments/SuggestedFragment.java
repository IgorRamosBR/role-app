package br.com.mytho.role.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.adapter.RecyclerEventsAdapter;
import br.com.mytho.role.model.Event;

public class SuggestedFragment extends Fragment {
    private List<Event> mEvents;
    private RecyclerView mRecyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();

        this.mEvents = (List<Event>) arguments.getSerializable("events");

        View view = inflater.inflate(R.layout.fragment_suggested, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.events);

        prepareRecyclerView();

        return view;
    }

    public void prepareRecyclerView() {
        mRecyclerView.setAdapter(new RecyclerEventsAdapter(getActivity(), mEvents));
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}