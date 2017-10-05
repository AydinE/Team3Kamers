$("#createButton").click(function() {
    var roomNumber = $("#roomNumber").val();
    var roomType = $("#roomType").val();
    var roomSize = $("#roomSize").val();
    var url = "/api/addRoom?roomNr=" + roomNumber + "&roomType=" + roomType + "&roomSize=" + roomSize + "&availability=true";
    $.get(url, function(data) {
        console.log(data);
        $("#outputField").html("Room " + data.roomNr + " created");
    });
});

$(document).ready( function () {
    populateTable();
});

function populateTable() {
    var endpoint = "/getRoomList";
    makeGetRequest(endpoint, function(rooms) {
        $.each(rooms, function(key, room) {
            $("#dataTable tbody").append("<tr><td>" + room.id + "</td><td>" + room.typeOfRoom + "</td><td>" + room.sizeOfRoom + "</td><td>" + room.createdOn + "</td><td>" + room.availability + "</td></tr>");
        });
        $("#dataTable").DataTable();
    });
}

//function del(id) {
//    $.ajax({url: "/api/deleteRoom"+id+"/", type: "DELETE"}).done( function() {
//    getAll();
//    })
//}