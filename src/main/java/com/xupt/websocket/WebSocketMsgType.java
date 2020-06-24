package com.xupt.websocket;

public enum WebSocketMsgType {
    INIT(1), LAST(2);
    int value;

    WebSocketMsgType() {
    }

    WebSocketMsgType(int value) {
        this.value = value;
    }
}
