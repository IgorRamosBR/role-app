package br.com.mytho.role.facade;

import android.content.Context;

import java.util.List;

import br.com.mytho.role.activity.delegate.EventDelegate;
import br.com.mytho.role.domain.service.EventService;
import br.com.mytho.role.model.Event;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by leonardocordeiro on 22/07/16.
 */

public class EventFacade {

    private EventDelegate eventDelegate;

    public EventFacade(EventDelegate delegate) {
        this.eventDelegate = delegate;
    }

    public void getEvents() {
        EventService service = new EventService.Builder().context((Context) eventDelegate).build();
        service.list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Event>>() {
                    @Override
                    public void call(List<Event> events) {
                        EventFacade.this.eventDelegate.onEvents(events);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        eventDelegate.onErrorInRetrievingEvents(throwable);
                    }
                });
    }
}
