
<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<html>
<body>
<script type="text/javascript">

    var  ws;
    var  target="ws://182.92.194.165:8081/gatewebSocket/13";
    function onOpen() {
        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }

        ws.onmessage=function (ev) {
            // alert(ev.data);
            // var  dv=document.getElementById("dv");
            // dv.innerHTML+=ev.data;
            // 将接收到的二进制数据转为字符串
            var  dv=document.getElementById("dv");
            dv.innerHTML+=ev.data;
        };

    }

    function webSend() {

        // var  msg=document.getElementById("msg").value;
        // ws.send(msg);
        // document.getElementById("msg").value=" ";
        var message = document.getElementById('msg').value;

        // //将字符串转换为byte数组
        // var bytesArr= stringToByte(message);
        // var bytes =new Uint8Array(bytesArr.length) ;
        // for (var i = 0; i < bytes.length; i++) {
        //     bytes[i]=bytesArr[i];
        // }
        // console.log(bytes);
        // ws.send(bytes);
        ws.send(message);
        document.getElementById("msg").value=" ";
    }
    //将字符串转为 Array byte数组
    function stringToByte(str) {
        var bytes = new Array();
        var len, c;
        len = str.length;
        for(var i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if(c >= 0x010000 && c <= 0x10FFFF) {
                bytes.push(((c >> 18) & 0x07) | 0xF0);
                bytes.push(((c >> 12) & 0x3F) | 0x80);
                bytes.push(((c >> 6) & 0x3F) | 0x80);
                bytes.push((c & 0x3F) | 0x80);
            } else if(c >= 0x000800 && c <= 0x00FFFF) {
                bytes.push(((c >> 12) & 0x0F) | 0xE0);
                bytes.push(((c >> 6) & 0x3F) | 0x80);
                bytes.push((c & 0x3F) | 0x80);
            } else if(c >= 0x000080 && c <= 0x0007FF) {
                bytes.push(((c >> 6) & 0x1F) | 0xC0);
                bytes.push((c & 0x3F) | 0x80);
            } else {
                bytes.push(c & 0xFF);
            }
        }
        return bytes;


    }

</script>
<button onclick="onOpen();">登录</button>
<input id="msg"/> <button onclick="webSend();">发送</button>
<div id="dv"></div>
</body>
</html>
