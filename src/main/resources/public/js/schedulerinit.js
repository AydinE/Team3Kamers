jQuery.fn.schedulerInit = function(taskies, usies) {

        //var MyFunctionResult = $().GetTasks();
        //console.log("scheudlerinit called");
        console.log(taskies);
        console.log(usies);

        var tasken = taskies;
        var usen = usies;

        //console.log(MyFunctionResult);

        var pitObj = {
            locale: 'en',
            defaultDisplay: 'months',
            hideEmptyLines: false,
            disableLabelsMovement: false,
            defaultGroupName: 'Default group',
            defaultDate: '2017-10-01 16:30',
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

        //$("#pit-scheduler").pitScheduler.default().viewMode('months');

};


