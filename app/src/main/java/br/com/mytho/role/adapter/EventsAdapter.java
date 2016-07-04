package br.com.mytho.role.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.mytho.role.R;
import br.com.mytho.role.model.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leonardocordeiro on 24/06/16.
 */
public class EventsAdapter extends BaseAdapter {

    private Activity activity;
    private List<Event> eventos;

    @BindView(R.id.event_label) TextView eventTitle;

    public EventsAdapter(Activity activity, List<Event> eventos) {
        this.activity = activity;
        this.eventos = eventos;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Event getItem(int i) {
        return eventos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        Event event = getItem(i);
        ButterKnife.bind(this, row);

        eventTitle.setText(event.getTitle());

        return row;
    }
}
