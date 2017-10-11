var table;

$(document).ready(function () {
    // populateTable();
    makeGetRequest("/getRoomList", function (data) {
        var tableData = [];
        for (var i = 0; i < data.length; i++) {
            tableData.push({
                0: data[i].id,
                1: data[i].nameOfRoom,
                2: getType(data[i].typeOfRoom),
                3: getSize(data[i].sizeOfRoom),
                4: parseDate(data[i].createdOn),
                5: getAvailable(data[i].available),
                6: "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>"
            })
        }
        table = $("#dataTable").DataTable({
            searching: false,
            data: tableData,
            aoColumnDefs: [
                { "bSearchable": false, "bVisible": false, "aTargets": [ 0 ] }]
        });
    });
    $("#addButton").click(function () {
        createRoom();
    });
    $('#dataTable tbody').on("click", "#deleteButton", function () {
        var row = table.row($(this).parents("tr"));
        del(row);
    });
});

function createRoom() {
    var json = {
        nameOfRoom: $("#addRoomName").val(),
        typeOfRoom: $("#addRoomType").val(),
        sizeOfRoom: $("#addRoomSize").val()
    };
    makeAjaxRequest("POST", "/addRoom", json, function (room) {
        table.row.add([
            room.id,
            room.nameOfRoom,
            getType(room.typeOfRoom),
            getSize(room.sizeOfRoom),
            parseDate(room.createdOn),
            getAvailable(room.available),
            "<a href=\"javascript:del(" + room.id + ", '" + specialCharReplacer(room.nameOfRoom) + "')\" class=\"btn btn-danger\">Delete</a>"
        ]).draw(false);
    });
}

function specialCharReplacer(roomName) {
    // roomName = roomName.replace(new RegExp("\'", 'g'), "\\'");
    // roomName = roomName.replace(new RegExp("\"", 'g'), "&quot;");
    return roomName;
}

function getSize(size) {
    switch (size) {
        case "ONE_PERSON":
            return "1";
        case "TWO_PERSONS":
            return "2";
        case "THREE_FOUR_PERSONS":
            return "3-4";
        case "FIVE_SIX_PERSONS":
            return "5-6";
        default:
            return null;
    }
}

function getType(type) {
    switch (type) {
        case "BUDGET":
            return "Budget";
        case "NORMAL":
            return "Normal";
        case "LUXURY":
            return "Luxury";
        default:
            return null;
    }
}

function getAvailable(availability) {
    return availability ? "Yes" : "No";
}

function parseDate(date) {
    var year = date[0];
    var month = date[1] - 1; // Month is 0-indexed
    var day = date[2];
    var hours = date[3];
    var minutes = date[4];
    var seconds = date[5];
    return day + "-" + month + "-" + year + " " + hours + ":" + minutes + ":" + seconds;
}

function del(row) {
    console.log(row.data()[0]);
    var r = confirm("Are you sure you want to delete room \"" + row.data()[1] + "\" ?");
    if (r == true) {
        $.ajax({url: "/api/deleteRoom/" + row.data()[0], type: "DELETE"}).done(function () {
            row.remove().draw();
        })
    }
}
