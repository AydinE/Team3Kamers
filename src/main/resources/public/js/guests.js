$(document).ready( function () {
    populateTable();
    $("#addButton").click(function() {
        createRoom();
    });
});

function createRoom() {
    var json = {
        typeOfRoom: $("#addRoomType").val(),
        sizeOfRoom: $("#addRoomSize").val()
    };
    makeAjaxRequest("POST", "/addRoom", json, function(room) {
        console.log(room);
        $("#dataTable tbody").append("<tr><td>" + room.id +
            "</td><td>" + getType(room.typeOfRoom) +
            "</td><td>" + getSize(room.sizeOfRoom) +
            "</td><td>" + parseDate(room.createdOn) +
            "</td><td>" + getAvailable(room.available) + "</td></tr>");
    });
}

function populateTable() {
    var endpoint = "/getGuestList";
    makeGetRequest(endpoint, function(guests) {
        $.each(guests, function(key, guest) {
            $("#dataTable tbody").append("<tr><td>" + guest.id +
                "</td><td>" + guest.firstName +
                "</td><td>" + guest.lastName +
                "</td><td>" + guest.address +
                "</td><td>" + guest.postalCode +
                "</td><td>" + guest.city +
                "</td><td>" + guest.country +
                "</td><td>" + guest.phoneNumber +
                "</td><td>" + guest.email + "</td></tr>");
        });
        $("#dataTable").DataTable({searching: false});
    });
}

//function del(id) {
//    $.ajax({url: "/api/deleteRoom"+id+"/", type: "DELETE"}).done( function() {
//    getAll();
//    })
//}