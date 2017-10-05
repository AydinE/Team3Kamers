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

    var table = $("#table_id").DataTable();
    $.get(url, function(data) {



//    table.row.add([roomNr, typeOfRoom, sizeOfRoom, createdOn, availability])
    });
});


//    $('#table_id').DataTable( {
//        "ajax":  {"url":"/api//getAllRooms", "dataSrc":""},

