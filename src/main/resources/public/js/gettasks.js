jQuery.fn.GetTasks = function() {

    var tasks = [];

    var url = "/api/getAllBookings";
    $.get(url, function(data) {

            for (i = 0; i < data.length; i++) {

                var obj = {
                    id: data[i].bookingNr,
                    name: data[i].guest.firstName + " " + data[i].guest.lastName,
                    description: data[i].startDate + " " + data[i].endDate,
                    color: '#536DFE'
                }

                tasks.push(obj);
            }

    });

    return tasks;

};