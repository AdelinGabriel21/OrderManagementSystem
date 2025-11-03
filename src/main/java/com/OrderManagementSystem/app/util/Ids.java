package com.OrderManagementSystem.app.util;

import java.util.UUID;

public final class Ids {
    private Ids() {}
    public static String createId() {
        return UUID.randomUUID().toString();
    }
}