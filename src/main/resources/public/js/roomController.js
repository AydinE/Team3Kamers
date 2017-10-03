$("#checkButton").click(function() {
    var roomNumber = $("#roomNumber").val();
    var url = "/api/getRoom?roomNr=" + roomNumber;
    $.get(url, function(data) {
        console.log(data);
        $("#outputField").html("Room " + data.roomNr + " can be changed");
        $("#roomID").val(data.roomNr);
        $("#typeOfRoom").val(data.);
        $("#sizeOfRoom").val(data.);
        $("#availability").val(data.);
    });
});

$("#changeButton").click(function() {
    var obj = {
        roomNr: $("#roomNumber").val(),
        roomType: $("#roomType").val(),
        roomSize: $("#roomSize").val(),
        availability: $("#availability").val(),
    };

    $.ajax({
        url: "/api/changeRoom",
        type: "POST",
        data: JSON.stringify(obj),
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            console.log(result);
        },
        error: function(err) {
            console.log(err);
        }
        });
});

