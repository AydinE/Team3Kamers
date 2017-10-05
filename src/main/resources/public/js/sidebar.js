$(document).ready(function () {
    $("#sidebar-icons").find("div").click(function () {
        console.log($(this).attr("class"));
        clickTab($(this));
    });

    $("#inputDropdown").keypress(function(event) {
        filterDropdown();
    });

    $("#myUL li a").click(function() {
        clickListItem($(this));
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
    } else if (tab.attr("id") == "add") {
        $("#sidebar-content-add").show();
        $("#sidebar-content-search").hide();
    }
 }

 function filterDropdown() {
     var input = document.getElementById('inputDropdown');
     var filter = input.value.toUpperCase();
     var ul = document.getElementById("myUL");
     var li = ul.getElementsByTagName('li');

     // Loop through all list items, and hide those who don't match the search query
     for (i = 0; i < li.length; i++) {
         a = li[i].getElementsByTagName("a")[0];
         if (a.innerHTML.length > 0 && a.innerHTML.toUpperCase().indexOf(filter) > -1) {
             li[i].style.display = "";
         } else {
             li[i].style.display = "none";
         }
     }
}

function clickListItem(item) {
     $("#inputDropdown").val(item.html());
     $("#inputDropdown").val(item.val());
     var ul = document.getElementById("myUL");
     var li = ul.getElementsByTagName('li');
     for (i = 0; i < li.length; i++) {
        li[i].style.display = "none";
     }
}
