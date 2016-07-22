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
 * Created by IGOR on 16/07/2016.
 */
public class RecyclerMyEventsAdapter extends RecyclerView.Adapter<RecyclerMyEventsAdapter.ViewHolder> {

    private List<Event> events;

    public RecyclerMyEventsAdapter(List<Event> events) {
        this.events = events;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemImage;
        public TextView itemName;
        public TextView itemDescription;
        public TextView itemDate;
        public TextView itemButtonAvaliate;

        public ViewHolder(View itemView) {
            super(itemView);

            itemImage = (ImageView) itemView.findViewById(R.id.iv_myevent);
            itemName = (TextView) itemView.findViewById(R.id.tv_myevent_name);
            itemDescription = (TextView) itemView.findViewById(R.id.tv_myevent_about);

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myevents, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemName.setText(events.get(position).getTitle());
        holder.itemDescription.setText(events.get(position).getAbout());
        holder.itemImage.setImageURI(Uri.parse(events.get(position).getImageLink()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
