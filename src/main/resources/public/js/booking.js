$(document).ready(function () {
    $("#addGuestList").select2();
    $("#addRoomList").select2();
    populateGuestList();
    populateRoomList();

    $("#addButton").click(function() {
        createBooking();
    });
});

function createBooking() {
    var booking = {
        guest: {id: $("#addGuestList").val()},
        room: {id: $("#addRoomList").val()},
        startDate: $("#addStartDate").val(),
        endDate: $("#addEndDate").val()
    };
    makeAjaxRequest("POST", "/addBooking", booking);
}

 function populateGuestList() {
    var endpoint = "/getGuestList";
    makeGetRequest(endpoint, function(guests) {
        $.each(guests, function(key, guest) {
            var o = new Option(guest.firstName + " " + guest.lastName, guest.id);
            $(o).html(guest.firstName + " " + guest.lastName);
            $("#addGuestList").append(o);
        });
    });
 }

 function populateRoomList() {
    var endpoint = "/getRoomList";
    makeGetRequest(endpoint, function(rooms) {
        $.each(rooms, function(key, room) {
            var o = new Option(room.id, room.id);
            $(o).html(room.id);
            $("#addRoomList").append(o);
        });
    });
 }