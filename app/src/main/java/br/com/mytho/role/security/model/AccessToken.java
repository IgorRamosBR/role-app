package br.com.mytho.role.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by leonardocordeiro on 11/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessToken {

    @JsonProperty("access_token")
    private String code;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
