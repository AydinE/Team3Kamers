$("#createButton").click(function() {
    var roomNumber = $("#roomNumber").val();
    var roomType = $("#roomType").val();
    var roomSize = $("#roomSize").val();
    var url = "/api/addRoom?roomNr=" + roomNumber + "&roomType=" + roomType + "&roomSize=" + roomSize + "&availability=true";
    $.get(url, function(data) {
        console.log(data);
        $("#outputField").html("Room " + data.roomNr + " created")
    });
});

$(document).ready( function () {

    var table = $("#table").DataTable();
    var url = "/api/getAllRooms";
    table.clear();

    $.get(url, function(data) {

        console.log(data);

        for (i = 0; i < data.length; i++) {
            console.log(data[i]);
            table.row.add([
                "<a href=\"javascript:del(" + data[i].roomNr + ")\"><i class='fa fa-trash-o' aria-hidden='true'></i></a>",
                data[i].roomNr,
                data[i].typeOfRoom,
                data[i].sizeOfRoom,
                data[i].createdOn.dayOfMonth + "-" +  data[i].createdOn.monthValue + "-" + data[i].createdOn.year,
                data[i].available,
            ]);
        }

        table.draw();

    });
});
function del(id) {
    $.ajax({url: "/api/deleteRoom"+id+"/", type: "DELETE"}).done( function() {
    getAll();
    })
}