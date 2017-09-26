$("#createButton").click(function() {
    var roomNumber = $("#roomNumber").val();
    var roomType = $("#roomType").val();
    var roomSize = $("#roomSize").val();
    var url = "/api/addRoom?roomNr=" + roomNumber + "&roomType=" + roomType + "&roomSize=" + roomSize;
    $.get(url, function(data) {
        console.log(data);
        $("#outputField").html("Room " + data.roomNr + " created")
    });
});