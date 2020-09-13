var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/chat-app');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe("/user/" + $("#sender").val() + "/queue/messages", message => {
            const payload = JSON.parse(message.body);
            showMessage('from: ' + payload.sender + ' to:' + payload.destination + ' message:' + payload.message);
        });
        stompClient.subscribe("/user/" + $("#destination").val() + "/queue/messages", message => {
            const payload = JSON.parse(message.body);
            showMessage('to: ' + payload.sender + ' from:' + payload.destination + ' message:' + payload.message);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    const body = {
        'message': $("#messageText").val(),
        'sender': $("#sender").val(),
        'destination': $("#destination").val(),
    };

    stompClient.send("/app/room", {}, JSON.stringify(body));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function() { connect(); });
    $("#disconnect").click(function() { disconnect(); });
    $("#send").click(function() { sendMessage(); });
});