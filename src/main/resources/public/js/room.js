$("#createButton").click(function() {
    var roomNumber = $("#roomNumber").val();
    var roomType = $("#roomType").val();
    var roomSize = $("#roomSize").val();
    var url = "/api/addRoom?roomNr=" + roomNumber + "&roomType=" + roomType + "&roomSize=" + roomSize + "&availability=true";
    $.get(url, function(data) {
        console.log(data);
        $("#outputField").html("Room " + data.roomNr + " created")
    });
});

$(document).ready( function () {

    var table = $("#table").DataTable();
    var url = "/api/getAllRooms";
    table.clear();

    $.get(url, function(data) {

        console.log(data);

        for (i = 0; i < data.length; i++) {
            console.log(data[i]);
            table.row.add([
                data[i].roomNr,
                data[i].typeOfRoom,
                data[i].sizeOfRoom,
                data[i].createdOn.dayOfMonth + "-" +  data[i].createdOn.monthValue + "-" + data[i].createdOn.year,
                data[i].available
            ]);
        }

        table.draw();

    });
});


//    $('#table_id').DataTable( {
//        "ajax":  {"url":"/api//getAllRooms", "dataSrc":""},

