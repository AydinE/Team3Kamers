$(document).ready(function() {

    // Tasks - >      Bookings (Name)
    // Users - >      Rooms
    // User tasks - > Booking (dates)

    console.log("Gettasks was called");

    var tasks = [];
    var users = [];
    //var userTasks = [];

    // Get Tasks
    var url = "/api/getAllBookings";
    $.get(url, function(data) {

        for (i = 0; i < data.length; i++) {

            var taskObj = {
                //id: "room" + data[i].bookingNr,
                id: data[i].bookingNr,
                name: data[i].guest.firstName + " " + data[i].guest.lastName,
                description: data[i].startDate + " - " + data[i].endDate,
                color: '#536DFE'
            }
            tasks.push(taskObj);
        }

    });

    // Get Users
    var url = "/api/getAllRooms";
    $.get(url, function(data) {

        for (i = 0; i < data.length; i++) {

            var roomObj = {
                name: data[i].roomNr,
                group: 'Rooms',
                tasks: []
            }
            users.push(roomObj);
        }

    });

    // Get UserTasks
    var url = "/api/getAllBookings";
    $.get(url, function(data) {

        for (i = 0; i < data.length; i++) {

            console.log("length " + data[i].startDate[1]);

            if (data[i].startDate[1] < 10) {
                String.fromCharCode(48) + String.fromCharCode(data[i].startDate[1] + 49);
            }

            if (data[i].startDate[2] < 10) {
                String.fromCharCode(48) + String.fromCharCode(data[i].startDate[2] + 49);
            }

            var taskObj = {
                id: data[i].room.roomNr,
                start_date: data[i].startDate[0] + "-" + data[i].startDate[1] + "-" + data[i].startDate[2] + " " + "17:00",
                end_date: data[i].endDate[0] + "-" + data[i].endDate[1] + "-" + data[i].endDate[2] + " " + "11:00"
            }
            //tasks.push(taskObj);
            for (i = 0; i < users.length; i++) {
                if (users[i].name == taskObj.id) {
                    users[i].tasks.push(taskObj);
                }
            }

        }

    });

    console.log(tasks);
    console.log(users);

    $().schedulerInit(tasks, users)

});