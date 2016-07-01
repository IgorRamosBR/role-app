package br.com.mytho.role.model;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by leonardocordeiro on 26/06/16.
 */
public class Event {
    private String title;
    private Uri imageUri;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getImageUri() { return imageUri; }

    public void setImageUri(Uri imageUri) { this.imageUri = imageUri; }
}
