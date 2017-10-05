$(document).ready(function () {

    $("#addStartDate").valueAsDate = new Date();
    $("#addEndDate").valueAsDate = new Date();

    $("#addButton").click(function() {
        var firstName = $("#addFirstName").val();
        var lastName = $("#addLastName").val();
        var endpoint = "/getGuestByName?firstName=" + firstName + "&lastName=" + lastName;
        makeGetRequest  (endpoint, function(guest) {
            console.log(guest);
            roomNumber = $("#addRoom").val();
            var endpoint = "/getRoom?roomNr=" + roomNumber;
            makeGetRequest(endpoint, function(room) {
                console.log(room);
                var booking = {
                    guest: guest,
                    room: room,
                    startDate: $("#addStartDate").val(),
                    endDate: $("#addEndDate").val()
                };
                makeAjaxRequest("POST", "/addBooking", booking, function(result) {
                    console.log("Result: " + result)
                });
                console.log("Done");
            });
        });

    });
});