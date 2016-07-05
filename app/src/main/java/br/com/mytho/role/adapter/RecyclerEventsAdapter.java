package br.com.mytho.role.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.model.Event;

/**
 * Created by JLGS on 29/06/2016.
 */
public class RecyclerEventsAdapter extends RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolder>{

    private List<Event> eventsList;



    public RecyclerEventsAdapter(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_main, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if(eventsList.get(position).getImageUri() == null)
                holder.imageView=null;
            else
                holder.imageView.setImageURI(eventsList.get(position).getImageUri());
                //holder.textView.setText(eventsList.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return eventsList.size();
        }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = (CircularImageView) view.findViewById(R.id.list_circular_icon);
            textView = (TextView) view.findViewById(R.id.event_label);
        }
    }
}
