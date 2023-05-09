package de.anshaana.playground.infrastructure.configuration;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.nio.charset.Charset.defaultCharset;

public class WebClientLoggingHandler extends ChannelDuplexHandler {
    private static Logger logger = LogManager.getLogger(WebClientLoggingHandler.class);
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof FullHttpRequest ) {
            FullHttpRequest  request = (FullHttpRequest) msg;
            String requestBody = request.content().toString(defaultCharset());
            logger.debug("SENT -> METHOD: {}, URI: {}, BODY: {}",
                    request.method(), request.uri(), requestBody);
        } else if (msg instanceof FullHttpMessage ) {
            FullHttpMessage  message = (FullHttpMessage) msg;
            logger.debug("SENT BODY: {}",
                    message.content().toString(defaultCharset()));
        }
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            String responseBody = response.content().toString(defaultCharset());
            logger.debug("RECEIVED -> STATUS: {}, BODY: {}",
                    response.status().code(), responseBody);
        } else if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            logger.debug("RECEIVED -> STATUS: {}", response.status().code());
        } else if (!(msg instanceof LastHttpContent) && msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            logger.debug("RECEIVED -> BODY: {}",
                    httpContent.content().toString(defaultCharset()));
        }
        super.channelRead(ctx, msg);
    }
}
