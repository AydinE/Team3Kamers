$(document).ready(function () {
    $("#sidebar-icons").find("div").click(function () {
        clickTab($(this));
    });
});

function clickTab(tab) {
     toggleContentVisibility(tab);
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