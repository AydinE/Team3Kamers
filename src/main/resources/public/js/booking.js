$(document).ready(function () {

    $("#addStartDate").valueAsDate = new Date();
    $("#addStartDate").valueAsDate = new Date();
    $("#addStartDate").valueAsDate = new Date();
    $("#addEndDate").valueAsDate = new Date();

    $("#addButton").click(function() {
        var booking = {
            guest: {id: $("#addGuestList").val()},
            room: {id: $("#addRoomList").val()},
            startDate: $("#addStartDate").val(),
            endDate: $("#addEndDate").val()
        };
        makeAjaxRequest("POST", "/addBooking", booking, function(result) {
        });
    });
});