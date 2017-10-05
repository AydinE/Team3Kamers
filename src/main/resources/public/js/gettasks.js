jQuery.fn.GetTasks = function() {

    id: '1',
    start_date: '2015-09-10 00:00',
    end_date: '2016-09-20 00:00'

    var tasks = [];

    var url = "/api/getAllBookings";
    $.get(url, function(data) {
            console.log(data);
            console.log(data[0].room.sizeOfRoom);
    });

};