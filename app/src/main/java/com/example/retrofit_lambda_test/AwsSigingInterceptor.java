package com.example.retrofit_lambda_test;

import okhttp3.Interceptor;
import okhttp3.Response;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

class AwsSigingInterceptor implements Interceptor {
    private final OkHttpAwsV4Signer signer;
    private final ThreadLocal<SimpleDateFormat> dateFormat;

    public AwsSigingInterceptor(OkHttpAwsV4Signer signer) {
        this.signer = signer;
        this.dateFormat = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                SimpleDateFormat localFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.US);
                localFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return localFormat;
            }
        };
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();

        okhttp3.Request newRequest = request.newBuilder()
                .addHeader("x-amz-date", dateFormat.get().format(clock.now()))
                .addHeader("host", request.url().host())
                .build();

        okhttp3.Request signed = signer.sign(newRequest, "<accessKeyId>", "<secretAccessKey>");

        return chain.proceed(signed);
    }
}