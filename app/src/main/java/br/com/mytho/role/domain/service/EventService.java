package br.com.mytho.role.domain.service;

import java.io.IOException;
import java.util.List;

import br.com.mytho.role.model.Event;
import br.com.mytho.role.security.model.AccessToken;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by leonardocordeiro on 11/07/16.
 */
public interface EventService {

    @GET("event")
    Call<List<Event>> list();

    class Builder {
        private AccessToken token;
        private String API_BASE_URL = "http://role-lema.rhcloud.com/rolebackend/";

        private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        private Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(JacksonConverterFactory.create());

        public Builder accessToken(AccessToken token) {
            this.token = token;
            return this;
        }

        public EventService build() {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization", "Bearer " + token.getCode())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = builder.client(httpClient.build()).build();
            return retrofit.create(EventService.class);
        }
    }

}
