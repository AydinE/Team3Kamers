jQuery.fn.schedulerInit = function(taskies, usies) {

        //var MyFunctionResult = $().GetTasks();
        console.log("scheudlerinit called");
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
//            tasks: [{id: '0'}],
//            users: [{   name: '0',
//                        group: 'Rooms',
//                        tasks: []
//                    },
//            ]
        }
        console.log("taskies en usies");
        console.log("Taskies: " + tasken.length + " usies: " + usen.length );
        for (i = 0; i < tasken.length; i++) {
            console.log(tasken[i]);
            pitObj.tasks.push(tasken[i]);
        }

        for (i = 0; i < usen.length; i++) {
            console.log(usen[i]);
            pitObj.users.push(usen[i]);
        }


        //pitObj.tasks = MyFunctionResult;
        console.log("Pitobj tasks");
        console.log(pitObj.tasks);
        console.log(pitObj.users);

        $("#pit-scheduler").pitScheduler( pitObj );


        //var MyFunctionResult = $().GetTasks();

        //console.log(MyFunctionResult);

};


