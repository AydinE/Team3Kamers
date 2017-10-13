var table;
var selectedRow;

$(document).ready(function () {
    initializeTable();
    $("#addButton").click(function () {
        addGuest();
    });
    $('#dataTable tbody').on("click", "#deleteButton", function () {
        var row = table.row($(this).parents("tr"));
        deleteGuest(row);
    });
    $('#dataTable tbody').on("click", "#editButton", function () {
        var row = table.row($(this).parents("tr"));
        selectedRow = row;
        editGuest(row.data()[0]);
    });
    $("#btnUpdateGuest").click(function () {
        saveChanges();
    });
});

function initializeTable() {
    makeGetRequest("/getGuestList", function (data) {
        var tableData = [];
        for (var i = 0; i < data.length; i++) {
            tableData.push({
                0: data[i].id,
                1: data[i].firstName,
                2: data[i].lastName,
                3: data[i].address,
                4: data[i].postalCode,
                5: data[i].city,
                6: data[i].country,
                7: data[i].phoneNumber,
                8: data[i].email,
                9: "<a class=\"btn btn-primary\" id=\"editButton\">Edit</a>",
                10: "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>"
            })
        }
        table = $("#dataTable").DataTable({
            searching: false,
            data: tableData,
            aoColumnDefs: [
                {"aTargets": 0, "bSearchable": false, "bVisible": false},
                {"aTargets": 9, "sorting": false, "orderable": false},
                {"aTargets": 10, "sorting": false, "orderable": false}]
        });
    });
}


function addGuest() {
    var obj = {
        firstName: $("#addFirstName").val(),
        lastName: $("#addLastName").val(),
        address: $("#addAddress").val(),
        postalCode: $("#addPostalCode").val(),
        city: $("#addCity").val(),
        country: $("#addCountry").val(),
        phoneNumber: $("#addPhoneNumber").val(),
        email: $("#addEmail").val()
    };
    makeAjaxRequest("POST", "/addGuest", obj, function (guest) {
        if (guest) {
            table.row.add([
                guest.id,
                guest.firstName,
                guest.lastName,
                guest.address,
                guest.postalCode,
                guest.city,
                guest.country,
                guest.phoneNumber,
                guest.email,
                "<a class=\"btn btn-primary\" id=\"editButton\">Edit</a>",
                "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>"
            ]).draw(false);
            $.alert({
                title: "Guest added!",
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

function deleteGuest(row) {
    var name = row.data()[1] + " " + row.data()[2];
    var r = confirm("Are you sure you want to delete guest: '" + name + "'?");
    if (r == true) {
        $.ajax({url: "/api/removeGuest/" + row.data()[0], type: "DELETE"}).done(function () {
            row.remove().draw();
        })
    }
}

// links nog kloppend maken
function editGuest(id) {
    $("#btnAddGuest").hide();
    $("#btnUpdateGuest").show();

    $.get({url: "/api/getGuest/?id=" + id, type: "GET"}).done(function (result) {
        $("#id").val(result.id);
        $("#editFirstName").val(result.firstName);
        $("#editLastName").val(result.lastName);
        $("#editPhoneNumber").val(result.phoneNumber);
        $("#editEmail").val(result.email);
        $("#editAddress").val(result.address);
        $("#editPostalCode").val(result.postalCode);
        $("#editCity").val(result.city);
        $("#editCountry").val(result.country);
        $("#guestModal").modal("toggle");
    })
}


// de EDIT knop hier werkend krijgen, zie vorige apps
function saveChanges() {
    var obj = {
        id: $("#id").val(),
        firstName: $("#editFirstName").val(),
        lastName: $("#editLastName").val(),
        address: $("#editAddress").val(),
        postalCode: $("#editPostalCode").val(),
        city: $("#editCity").val(),
        country: $("#editCountry").val(),
        phoneNumber: $("#editPhoneNumber").val(),
        email: $("#editEmail").val()
    };

    makeAjaxRequest("PUT", "/changeGuest", obj, function (data) {
        $("#guestModal").modal("toggle");
        $("#guestModal input").val("");
        selectedRow.data({
            0: data.id,
            1: data.firstName,
            2: data.lastName,
            3: data.address,
            4: data.postalCode,
            5: data.city,
            6: data.country,
            7: data.phoneNumber,
            8: data.email,
            9: "<a class=\"btn btn-primary\" id=\"editButton\">Edit</a>",
            10: "<a class=\"btn btn-danger\" id=\"deleteButton\">Delete</a>",
        });
    });
}