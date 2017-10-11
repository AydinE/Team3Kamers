function getBooking(bookingNumber) {

    var url = "/api/getBooking?bookingNr=" + bookingNumber ;
    $.get(url, function(data) {

        return data;

    });

}

function deleteBooking(bookingNumber) {

    var url = "/api/getBooking?bookingNr=" + bookingNumber ;
    $.get(url, function(data) {

        //console.log("Return data from get: ");
        //console.log(data);
        //return data;
        $.ajax({
            url: "/api/deleteBooking",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                //console.log(result);
                // Switch the views real quick to refresh the page sneaky beaky like :)
                //$.fn.pitScheduler.default().viewMode(null);
                //$.fn.pitScheduler.default().viewMode('months');
                //window.location.reload();
                $( "#pit-scheduler" ).empty();
                callInit();
            },
            error: function(err) {
                //console.log(err);
            }
        });

    });

}

Number.prototype.pad = function(size) {
    var s = String(this);
    while (s.length < (size || 2)) {s = "0" + s;}
    return s;
}


// Tasks - >      Bookings (Name)
// Users - >      Rooms
// User tasks - > Booking (dates)
function callInit() {

    var tasks = [];
    var users = [];
    //var userTasks = [];

    // Get Tasks
    var url = "/api/getBookingList";
    $.get(url, function(data) {

//        console.log("Bookinglist");
//        console.log(data);

        for (i = 0; i < data.length; i++) {

            var taskObj = {
                //id: "room" + data[i].bookingNr,
                id: data[i].bookingNr.toString(),
                name: data[i].guest.firstName + " " + data[i].guest.lastName,
                description: data[i].startDate + " - " + data[i].endDate,
                color: '#536DFE'
            }
            tasks.push(taskObj);
        }

        getUsers();

    });

    function getUsers() {
      // Get Users
        var url = "/api/getRoomList";
        $.get(url, function(data) {

//            console.log("RoomList");
//            console.log(data);

            for (i = 0; i < data.length; i++) {

                var roomObj = {
                    name: data[i].id.toString(),
                    group: 'Rooms',
                    tasks: []
                }
                users.push(roomObj);
            }

            getUserTasks();

        });
    }

    function getUserTasks() {
          // Get UserTasks
            var url = "/api/getBookingList";
            $.get(url, function(data) {

//                console.log("BookingList");
//                console.log(data);

                for (i = 0; i < data.length; i++) {

                    if (data[i].startDate[1] < 10) {
                        data[i].startDate[1] = data[i].startDate[1].pad();
                    }

                    if (data[i].startDate[2] < 10) {
                        data[i].startDate[2] = data[i].startDate[2].pad();
                    }

                    if (data[i].endDate[1] < 10) {
                        data[i].endDate[1] = data[i].endDate[1].pad();
                    }

                    if (data[i].endDate[2] < 10) {
                        data[i].endDate[2] = data[i].endDate[2].pad();
                    }

                    var taskObj = {
                        // was room.roomnr
                        id: data[i].bookingNr.toString(),
                        start_date: data[i].startDate[0] + "-" + data[i].startDate[1] + "-" + data[i].startDate[2] + " " + "17:00",
                        end_date: data[i].endDate[0] + "-" + data[i].endDate[1] + "-" + data[i].endDate[2] + " " + "11:00"
                    }
                    //tasks.push(taskObj);
                    for (j = 0; j < users.length; j++) {
                        console.log("id en id: " + users[j].name + " " + taskObj.id);
                        if (users[j].name == data[i].room.id) {
                            users[j].tasks.push(taskObj);
                        }
                    }

                }

                console.log(tasks);
                console.log(users);

                $().schedulerInit(tasks, users);

            });

    }

}

$(document).ready(function() {

    callInit()

});