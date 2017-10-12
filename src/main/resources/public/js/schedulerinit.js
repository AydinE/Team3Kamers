jQuery.fn.schedulerInit = function(taskies, usies) {

        //var MyFunctionResult = $().GetTasks();
        //console.log("scheudlerinit called");
//        console.log(taskies);
//        console.log(usies);

        var tasken = taskies;
        var usen = usies;

        //console.log(MyFunctionResult);

        var pitObj = {
            locale: 'en',
            defaultDisplay: 'months',
            hideEmptyLines: false,
            disableLabelsMovement: false,
            defaultGroupName: 'Default group',
            defaultDate: '2017-10-12 16:30',
            disableNotifications: false,
            notificationDuration: 4000,
            hideSpinner: true,
            onChange: '',
            onTaskCreation: '',
            onUserCreation: '',
            onTaskRemoval: '',
            onUserRemoval: '',
            onUserEdition: '',
            onTaskAssignation: '',
            onUserTaskDeletion: '',
            onTaskEdition: '',
            disableUndo: false,
            resizeTask: false,
            tasks: taskies,
            users: usies,
        }

        $("#pit-scheduler").pitScheduler( pitObj );

        $.fn.pitScheduler.default().viewMode('months');

        $('.pts-header').slice(1).remove();

        //Fill the dashboard with data
        var endpoint = "/getGuestList";
        makeGetRequest(endpoint, function (guests) {
            $('#nr-guests').html(guests.length);
        });

        var url = "/api/getBookingList";
        $.get(url, function(data) {
            var checkedIn = 0;

            for (var i = 0; i < data.length; i++) {
                if(data[i].checkedIn == true){
                    checkedIn = checkedIn + 1;
                }
            }

            $('#nr-checkins').html(checkedIn);
        });



        $('#nr-bookings').html(taskies.length);
        $('#nr-rooms').html(usies.length);

        //$("#pit-scheduler").pitScheduler.default().viewMode('months');

};


