var table;
var selectedRow;

$(document).ready(function () {
    initializeTable();
    $("#addButton").click(function () {
        addRoom();
    });
    $('#dataTable tbody').on("click", "#deleteButton", function () {
        var row = table.row($(this).parents("tr"));
        deleteRoom(row);
    });
    $('#dataTable tbody').on("click", "#editButton", function () {
        var row = table.row($(this).parents("tr"));
        selectedRow = row;
        editRoom(row.data()[0]);
    });
    $("#btnUpdateRoom").click(function () {
        saveChanges();
    });
});

function initializeTable() {
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
                6: "<a class=\"btn btn-primary\" id=\"editButton\">Edit</a>",
                7: "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>"
            })
        }
        table = $("#dataTable").DataTable({
            searching: false,
            data: tableData,
            aoColumnDefs: [
                {"bSearchable": false, "bVisible": false, "aTargets": [0]},
                {"aTargets": 6, "sorting": false, "orderable": false},
                {"aTargets": 7, "sorting": false, "orderable": false}]
        });
    });
}

function addRoom() {
    var json = {
        nameOfRoom: $("#addRoomName").val(),
        typeOfRoom: $("#addRoomType").val(),
        sizeOfRoom: $("#addRoomSize").val()
    };
    makeAjaxRequest("POST", "/addRoom", json, function (room) {
        if (room) {
            table.row.add([
                room.id,
                room.nameOfRoom,
                getType(room.typeOfRoom),
                getSize(room.sizeOfRoom),
                parseDate(room.createdOn),
                getAvailable(room.available),
                "<a class=\"btn btn-primary\" id=\"editButton\">Edit</a>",
                "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>"
            ]).draw(false);
            $.alert({
                title: "Room added!",
                content: "",
            });
        } else {
            $.alert({
                title: "Error!",
                content: "Some of your input is not correct, please verify your input.",
            });
        }
    });
}

function deleteRoom(row) {
    var r = confirm("Are you sure you want to delete room '" + row.data()[1] + "' ?");
    if (r == true) {
        $.ajax({url: "/api/deleteRoom/" + row.data()[0], type: "DELETE"}).done(function () {
            row.remove().draw();
        })
    }
}

function editRoom(id) {
    $("#btnAddRoom").hide();
    $("#btnUpdateRoom").show();
    $.get({url: "/api/getRoom/?id=" + id, type: "GET"}).done(function (result) {
        $("#id").val(id);
        $("#editNameOfRoom").val(result.nameOfRoom);
        $("#editTypeOfRoom").val(getTypeIndex(result.typeOfRoom));
        $("#editSizeOfRoom").val(getSizeIndex(result.sizeOfRoom));
        $("#editAvailability").val(getAvailableIndex(result.available));
        $("#roomModal").modal("toggle");
    })
}


// de EDIT knop hier werkend krijgen, zie vorige apps
function saveChanges() {
    var obj = {
        id: $("#id").val(),
        nameOfRoom: $("#editNameOfRoom").val(),
        typeOfRoom: $("#editTypeOfRoom").val(),
        sizeOfRoom: $("#editSizeOfRoom").val(),
        availability: $("#editAvailability").val()
    };
    makeAjaxRequest("PUT", "/changeRoom", obj, function (data) {
        $("#roomModal").modal("toggle");
        $("#roomModal input").val("");
        selectedRow.data({
            0: data.id,
            1: data.nameOfRoom,
            2: getType(data.typeOfRoom),
            3: getSize(data.sizeOfRoom),
            4: parseDate(data.createdOn),
            5: getAvailable(data.available),
            6: "<a class=\"btn btn-primary\" id=\"editButton\">Edit</a>",
            7: "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>",
        });
    });
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

function getTypeIndex(type) {
    switch (type) {
        case "LUXURY":
            return 0;
        case "NORMAL":
            return 1;
        case "BUDGET":
            return 2;
        default:
            return null;
    }
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

function getSizeIndex(size) {
    switch (size) {
        case "ONE_PERSON":
            return 0;
        case "TWO_PERSONS":
            return 1;
        case "THREE_FOUR_PERSONS":
            return 2;
        case "FIVE_SIX_PERSONS":
            return 3;
        default:
            return null;
    }
}

function getAvailable(availability) {
    return availability ? "Yes" : "No";
}

function getAvailableIndex(availability) {
    return availability ? "true" : "false";
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