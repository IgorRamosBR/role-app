package br.com.mytho.role.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.model.Event;

/**
 * Created by JLGS on 29/06/2016.
 */

public class RecyclerEventsAdapter extends RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolder> {

    private List<Event> events;
    private Context context;

    public RecyclerEventsAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView itemImage;
        public TextView itemName;
        public TextView itemDescription;
        public TextView itemId;
        public IMyViewHolderClicks mListener;

        public ViewHolder(View itemView, IMyViewHolderClicks listener) {
            super(itemView);
            mListener = listener;
            itemImage = (ImageView)itemView.findViewById(R.id.eventImage);
            itemName = (TextView)itemView.findViewById(R.id.eventName);
            itemDescription = (TextView)itemView.findViewById(R.id.eventSubtitle);
            itemId = (TextView) itemView.findViewById(R.id.eventId);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    Snackbar.make(v, "Click detected on item ",
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });*/
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onEvent(Long.parseLong(itemId.getText().toString()));
        }

        public interface IMyViewHolderClicks {
            void onEvent(long id);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v, new RecyclerEventsAdapter.ViewHolder.IMyViewHolderClicks() {
            @Override
            public void onEvent(long id) {
                Log.i("CLICK", "id: " + id);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemId.setText(Long.toString(events.get(i).getId()));
        viewHolder.itemName.setText(events.get(i).getTitle());
        viewHolder.itemDescription.setText(events.get(i).getAbout());
//        viewHolder.itemImage.setImageURI(Uri.parse(events.get(i).getImageLink()));
        Picasso.with(context)
               .load(events.get(i).getImageLink())
               .into(viewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}