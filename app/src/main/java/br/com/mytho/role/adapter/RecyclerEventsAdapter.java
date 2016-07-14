package br.com.mytho.role.adapter;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.model.Event;

/**
 * Created by JLGS on 29/06/2016.
 */

public class RecyclerEventsAdapter extends RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolder> {

    private List<Event> events;

    public RecyclerEventsAdapter(List<Event> events) {
        this.events = events;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemImage;
        public TextView itemName;
        public TextView itemDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            itemImage = (ImageView)itemView.findViewById(R.id.eventImage);
            itemName = (TextView)itemView.findViewById(R.id.eventName);
            itemDescription = (TextView)itemView.findViewById(R.id.eventSubtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    Snackbar.make(v, "Click detected on item ",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(events.get(i).getTitle());
        viewHolder.itemDescription.setText(events.get(i).getAbout());
        viewHolder.itemImage.setImageURI(Uri.parse(events.get(i).getImageLink()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}