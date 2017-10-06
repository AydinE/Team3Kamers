var table;

$(document).ready( function () {
    populateTable();
    table = $("#dataTable").DataTable({searching: false});
    $("#addButton").click(function() {
        createRoom();
    });
});

function createRoom() {
    var json = {
        typeOfRoom: $("#addRoomType").val(),
        sizeOfRoom: $("#addRoomSize").val()
    };
    makeAjaxRequest("POST", "/addRoom", json, function(room) {
        console.log(room);
        $("#dataTable tbody").append("<tr><td>" + room.id +
            "</td><td>" + getType(room.typeOfRoom) +
            "</td><td>" + getSize(room.sizeOfRoom) +
            "</td><td>" + parseDate(room.createdOn) +
            "</td><td>" + getAvailable(room.available) +
            "</td><td>" + "<a href=\"javascript:del(" + room.id + ")\" class=\"btn btn-danger\">Delete</a>" +
            "</td></tr>");
    });
}

function populateTable() {
    var endpoint = "/getRoomList";
    makeGetRequest(endpoint, function(rooms) {
        $.each(rooms, function(key, room) {
            $("#dataTable tbody").append("<tr><td>" + room.id +
                "</td><td>" + getType(room.typeOfRoom) +
                "</td><td>" + getSize(room.sizeOfRoom) +
                "</td><td>" + parseDate(room.createdOn) +
                "</td><td>" + getAvailable(room.available) +
                "</td><td>" + "<a href=\"javascript:del(" + room.id + ")\" class=\"btn btn-danger\">Delete</a>" +
                "</td></tr>");
        });
    });
}

function refreshTable() {
    table.clear();
    populateTable();
    table.draw();
}

function getSize(size) {
    switch(size) {
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
    switch(type) {
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
    console.log(date);
    var year = date[0];
    var month = date[1] - 1; // Month is 0-indexed
    var day = date[2];
    var hours = date[3];
    var minutes = date[4];
    var seconds = date[5];
    return day + "-" + month + "-" + year + " " + hours + ":" + minutes + ":" + seconds;
}

function del(id) {
    console.log(id);
    $.ajax({url: "/api/deleteRoom/" + id, type: "DELETE"}).done( function() {
        refreshTable();
    })
}