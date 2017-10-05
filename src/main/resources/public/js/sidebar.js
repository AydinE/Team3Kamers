$(document).ready(function () {
    $("#sidebar-icons").find("div").click(function () {
        clickTab($(this));
    });

    $("#addGuestList").select2();
    $("#addRoomList").select2();
    populateGuestList();
    populateRoomList();
});

function clickTab(tab) {
    // If this tab is already selected
    if (tab.hasClass("selected")) {
        tab.toggleClass("selected");
        $("#sidebar-content").toggle();
        $('#sidebar').toggleClass("inactive");
        return;
    }
    changeTab(tab);
 }

 function changeTab(tab) {
     var flag = false;
     // Find another tab that is selected
     $("#sidebar-icons li div").each(function(k, v) {
         if ($(v).hasClass("selected")) {
             $(v).toggleClass("selected");
             toggleContentVisibility($(v));
             tab.toggleClass("selected");
             toggleContentVisibility(tab);
             flag = true;
             return;
         }
     });
     if (flag) {
         return;
     }

     // No tabs selected
     tab.toggleClass("selected");
     $("#sidebar-content").toggle();
     $('#sidebar').toggleClass("inactive");
 }

 function toggleContentVisibility(tab) {
    if (tab.attr("id") == "search") {
        $("#sidebar-content-search").show();
        $("#sidebar-content-add").hide();
    } else if (tab.attr("id") == "add") {
        $("#sidebar-content-add").show();
        $("#sidebar-content-search").hide();
    }
 }

 function populateGuestList() {
    var endpoint = "/getGuestList";
    makeGetRequest  (endpoint, function(guests) {
        $.each(guests, function(key, guest) {
            var o = new Option(guest.firstName + " " + guest.lastName, guest.id);
            $(o).html(guest.firstName + " " + guest.lastName);
            $("#addGuestList").append(o);
        });
    });
 }

 function populateRoomList() {
    var endpoint = "/getRoomList";
    makeGetRequest  (endpoint, function(rooms) {
        $.each(rooms, function(key, room) {
            var o = new Option(room.id, room.id);
            $(o).html(room.id);
            $("#addRoomList").append(o);
        });
    });
 }



