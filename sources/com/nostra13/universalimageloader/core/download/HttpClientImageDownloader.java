package com.nostra13.universalimageloader.core.download;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;

public class HttpClientImageDownloader extends BaseImageDownloader {
    private HttpClient httpClient;

    public HttpClientImageDownloader(Context context, HttpClient httpClient2) {
        super(context);
        this.httpClient = httpClient2;
    }

    /* access modifiers changed from: protected */
    public InputStream getStreamFromNetwork(URI imageUri, Object extra) throws IOException {
        return new BufferedHttpEntity(this.httpClient.execute(new HttpGet(imageUri.toString())).getEntity()).getContent();
    }
}
