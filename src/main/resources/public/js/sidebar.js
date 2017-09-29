$(document).ready(function () {
    $("#sidebar-icons").find("div").click(function () {
        console.log($(this).attr("class"));
        clickTab($(this));
    });

});

function clickTab(tab) {
    // If this tab is already selected
    if (tab.hasClass("selected")) {
        tab.toggleClass("selected");
        $('#sidebar').toggleClass("inactive");
        $("#sidebar-content").toggle();
        return;
    }

    var flag = false;
    // Find another tab that is selected
    $("#sidebar-icons li div").each(function(k, v) {
        if ($(v).hasClass("selected")) {
            $(v).toggleClass("selected");
            tab.toggleClass("selected");
            flag = true;
            return;
        }
    });
    if (flag) {
        return;
    }

    // No tabs selected
    tab.toggleClass("selected");
    $('#sidebar').toggleClass("inactive");
    $("#sidebar-content").toggle();
 }