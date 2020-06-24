package com.xupt.websocket;

public enum GateWebSocketMsgType {
    DATA(1), SHIPIN(3);

    int value;

    GateWebSocketMsgType() {
    }

    GateWebSocketMsgType(int value) {
        this.value = value;
    }
}
