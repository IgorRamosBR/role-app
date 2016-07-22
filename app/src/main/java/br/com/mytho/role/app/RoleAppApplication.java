package br.com.mytho.role.app;

import android.app.Application;
import android.widget.Toast;

import br.com.mytho.role.security.OAuthAccessTokenService;
import br.com.mytho.role.security.model.AccessToken;
import br.com.mytho.role.security.model.repository.AccessTokenRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by leonardocordeiro on 20/07/16.
 */

public class RoleAppApplication extends Application {

    private AccessTokenRepository tokenRepository;

    @Override
    public void onCreate() {
        // if ( internet ) then


        // Toast -> retrieving from cache

    }
}
