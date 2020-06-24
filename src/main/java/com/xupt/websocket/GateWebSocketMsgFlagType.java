package com.xupt.websocket;

public enum GateWebSocketMsgFlagType {
    START(1),END(2);
   public int value;

    GateWebSocketMsgFlagType() {
    }

    GateWebSocketMsgFlagType(int value) {
        this.value = value;
    }
}
