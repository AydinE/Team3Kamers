 $(document).ready(function () {
	$("#pit-scheduler").pitScheduler({
		locale: 'en',
		defaultDisplay: 'months',
		hideEmptyLines: true,
		disableLabelsMovement: false,
		defaultGroupName: 'Default group',
		defaultDate: '2017-09-01 16:30',
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
		resizeTask: true,
		tasks:  [
			{
				id: '1',
				name: 'Jan Janssen',
				description: '',
				color: '#50d371',
			},
			{
				id: '2',
				name: 'Berta Bertus',
				color: '#50d371'
			},
			{
				id: '3',
				name: 'Juan Percent',
				color: '#50d371'
			},
			{
				id: '4',
				name: 'Charlie Sheen',
				color: '#50d371',
			}
		],
		users: [
			   {
				name: '1',
				group: 'Rooms',
				tasks: [
					{
						id: '1',
						start_date: '2015-09-10 00:00',
						end_date: '2016-09-20 00:00'
					},
					{
						id: '2',
						start_date: '2016-09-28 6:00',
						end_date: '2017-09-28 18:30'
					},
					{
						id: '3',
						start_date: '2016-09-08 10:40',
						end_date: '2016-09-21 16:00'
					},
					{
						id: '4',
						start_date: '2016-09-23 10:10',
						end_date: '2016-09-29 16:50'
					},
					{
						id: '1',
						start_date: '2016-09-10 08:00',
						end_date: '2016-09-12 20:00'
					}
				]
			},
			{
				name: '2',
				group: 'Rooms',
				tasks: [
					{
						id: '2',
						start_date: '2016-09-01 06:00',
						end_date: '2016-09-04 00:00'
					},
					{
						id: '2',
						start_date: '2016-09-04 11:00',
						end_date: '2016-09-08 18:00'
					},
					{
						id: '3',
						start_date: '2016-09-07 17:00',
						end_date: '2016-09-11 20:00'
					}
				]
			},
			{
				name: '3',
				group: 'Rooms',
				tasks: [
					{
						id: '2',
						start_date: '2017-09-01 02:30',
						end_date: '2017-09-03 09:00'
					},
					{
						id: '2',
						start_date: '2017-09-04 17:30',
						end_date: '2017-09-24 07:42'
					},
					{
						id: '4',
						start_date: '2017-09-02 17:30',
						end_date: '2017-09-11 07:42'
					},
					{
						id: '1',
						start_date: '2017-09-13 10:00',
						end_date: '2017-09-26 16:00'
					},
					{
						id: '2',
						start_date: '2017-09-30 10:00',
						end_date: '2017-10-05 16:00'
					},
					{
						id: '1',
						start_date: '2017-10-23 10:00',
						end_date: '2017-11-04 16:00'
					},
					{
						id: '3',
						start_date: '2017-11-06 10:00',
						end_date: '2017-11-09 16:00'
					},
					{
						id: '4',
						start_date: '2017-11-10 10:00',
						end_date: '2017-11-21 16:00'
					},
					{
						id: '1',
						start_date: '2017-09-01 08:00',
						end_date: '2017-09-10 18:00'
					},
					{
						id: '2',
						start_date: '2017-09-12 08:00',
						end_date: '2017-09-21 18:00'
					}
				]
			}
		]
	});

});