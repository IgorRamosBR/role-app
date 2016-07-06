package br.com.mytho.role.adapter;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.model.Event;

/**
 * Created by JLGS on 29/06/2016.
 */

public class RecyclerEventsAdapter extends RecyclerView.Adapter<RecyclerEventsAdapter.ViewHolder> {

    private List<Event> eventsList;
    private String[] titles = {"Rock Nacional - MC GUI Nova Geração",
            "Arraia Brasileiro",
            "24 horas - Just Dance Day Live",
            "Show Guns N Roses Live - Rock in Rio" };

    private String[] details = {"Evento comunitário da Igreja São Bartolomeu, entrada grátis com 1kg de alimento opcional",
            "Item two details", "Item three details",
            "Item four details" };

    private int[] images = { R.drawable.event_image4,
            R.drawable.event_image2,
            R.drawable.event_image4,
            R.drawable.event_image1 };

    public RecyclerEventsAdapter(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.eventImage);
            itemTitle = (TextView)itemView.findViewById(R.id.eventTittle);
            itemDetail = (TextView)itemView.findViewById(R.id.eventSubtittle);

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
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}