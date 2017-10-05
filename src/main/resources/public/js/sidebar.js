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
        console.log("Showing-search");
    } else if (tab.attr("id") == "add") {
        $("#sidebar-content-add").show();
        $("#sidebar-content-search").hide();
        console.log("Showing add");
    }
 }