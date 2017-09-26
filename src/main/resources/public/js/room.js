$("#createButton").click(function() {
    var roomNumber = $("#roomNumber").val();
    var roomType = $("#roomType").val();
    var roomSize = $("#roomSize").val();
    var url = "/api/addRoom?roomNr=" + roomNumber + "&roomType=" + roomType + "&roomSize=" + roomSize;
    $.get(url, function(data) {
        var json = JSON.stringify(data);
        console.log(json);
        $("#outputField").html("Room created")
    });
});