package com.ericwyn.leablog.controller;

import com.ericwyn.ezerver.SimpleHttpServer;

/**
 * Created by Ericwyn on 18-5-6.
 */
public interface Controller {
    void loadHandMethod(SimpleHttpServer.Builder builder);
}
