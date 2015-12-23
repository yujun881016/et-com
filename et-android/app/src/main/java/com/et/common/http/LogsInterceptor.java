package com.et.common.http;

import com.et.common.util.L;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSource;

/**
 * Description: 日志interceptor
 * Created by Wangchao on 15/11/16 17:22.
 * Updated by Wangchao on 15/11/19 18:22.
 * Author: Wangchao
 * Email: super0086@qq.com
 * Version: V1.0
 */
public final class LogsInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /** No logs. */
        NONE,
        /**
         * Logs request and response lines.
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1 (3-byte body)
         *
         * <-- HTTP/1.1 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END GET
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    public interface Logger {
        void log(String message);

        /** A {@link Logger} defaults output appropriate for the current platform. */
        Logger DEFAULT = new Logger() {
            @Override public void log(String message) {

                L.writeLog(message);
                L.i("LogsInterceptor", message);
            }
        };
    }

    public LogsInterceptor() {
        this(Logger.DEFAULT);
    }

    public LogsInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

    private volatile Level level = Level.NONE;

    /** Change the level at which this interceptor logs. */
    public void setLevel(Level level) {
        this.level = level;
    }
    @Override public Response intercept(Chain chain) throws IOException {
        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        String requestStartMessage =
                "--——> START:" + request.method() + ' ' + requestPath(request.httpUrl());
        logger.log(requestStartMessage);
        if (logHeaders) {
            if (logBody && hasRequestBody) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null&&contentType.toString().startsWith("application/json")) {
                    contentType.charset(UTF8);
                    logger.log("request:"+buffer.readString(charset));
                }else {
                    logger.log("request:"+"Multiple different charsets");
                }


            }
        }
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        if (logHeaders) {
            if (logBody) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null&&contentType.toString().startsWith("application/json")) {
                    charset = contentType.charset(UTF8);
                    if (responseBody.contentLength() != 0) {
                        logger.log("response("+response.code()+"):"+buffer.clone().readString(charset));
                    }else {
                        logger.log("response("+response.code()+"):");
                    }
                }else {
                    logger.log("response("+response.code()+"):"+"Multiple different charsets");
                }
            }
            logger.log("<--—— END HTTP");
        }
        return response;
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }

    private static String requestPath(HttpUrl url) {
        String host=url.host();
        String path = url.encodedPath();
        String query = url.encodedQuery();
        return host+(query != null ? (path + '?' + query) : path);
    }
    /*public LogsInterceptor() {
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        EventMainEntity event;
        long timeBefore = 0;
        long timeAfter = 0;
        if (LogUtil.isOpen) {
            event = new EventMainEntity();
            RequestBody body = request.body();
            String bodyStr = body==null?"body is null":body.toString();
            String url_conn_header_body = String.format("Sending request %s%n (Connection) %s%n%s%s%n",
                    request.url(), chain.connection(), request.headers(), bodyStr);
            event.setType(EventMainEntity.TYPE_FLAG_LOG);
            event.setLogs(url_conn_header_body);
            EventBus.getDefault().post(event);
            LogUtil.writeLog(url_conn_header_body);
            timeBefore = System.nanoTime();
        }
        Response response = chain.proceed(request);
        if (LogUtil.isOpen) {
            timeAfter = System.nanoTime();
            event = new EventMainEntity();
            ResponseBody body = response.body();
            String bodyStr = "";
            if (body!=null) {
                RealResponseBody realBody = (RealResponseBody) body;
                InputStream inStream = realBody.source().inputStream();
                *//*ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = inStream.read(buffer)) != -1) {
                    outSteam.write(buffer, 0, len);
                }
                outSteam.close();
                bodyStr = new String(outSteam.toByteArray(), "utf-8");*//*
            } else {
                bodyStr = "body is null";
            }
            event.setType(EventMainEntity.TYPE_FLAG_LOG);
            String isOk = response.message()+" "+response.code();
            String resp = String.format("Received response is %s 耗时(%.1fms) %s%n%s---body begin---%n%s%n---body end---%n",
                    isOk, (timeAfter - timeBefore) / 1e6d, request.url(), response.headers(),bodyStr);
            event.setLogs(resp);
            EventBus.getDefault().post(event);
        }

        return response;
    }*/
}
