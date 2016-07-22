package br.com.mytho.role.facade;

import android.content.Context;

import br.com.mytho.role.activity.delegate.AccessTokenDelegate;
import br.com.mytho.role.security.OAuthAccessTokenService;
import br.com.mytho.role.security.model.AccessToken;
import br.com.mytho.role.security.model.repository.AccessTokenRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by leonardocordeiro on 21/07/16.
 */

public class AccessTokenFacade {

    private AccessTokenRepository tokenRepository;
    private AccessTokenDelegate accessTokenDelegate;

    public AccessTokenFacade(AccessTokenDelegate accessTokenDelegate) {
        this.accessTokenDelegate = accessTokenDelegate;
        this.tokenRepository = new AccessTokenRepository((Context) accessTokenDelegate);
    }

    public void getAccessToken() {
        if (tokenRepository.empty()) {
            OAuthAccessTokenService oAuthAccessTokenService = new OAuthAccessTokenService.Builder().build();

            oAuthAccessTokenService.getAccessToken(OAuthAccessTokenService.PUBLIC_SCOPE)
                                    .retry(500)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<AccessToken>() {
                                        @Override
                                        public void call(AccessToken accessToken) {
                                            tokenRepository.save(accessToken);
                                            accessTokenDelegate.onReceiveAccessToken();

                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {
                                            accessTokenDelegate.onErrorInRetrievingAccessToken(throwable);
                                        }
                                    });
        } else {
            accessTokenDelegate.onReceiveAccessToken();
        }
    }

}
