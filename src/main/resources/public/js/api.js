function makeGetRequest(endpoint, onResult) {
    var url = "/api" + endpoint;
    $.get(url, function(result) {
        if (onResult != null && jQuery.isFunction(onResult)) {
            onResult(result);
        }
    });
}

function makeAjaxRequest(requestType, endpoint, json, onSuccess) {
    $.ajax({
        type: requestType,
        url: "/api" + endpoint,
        data: JSON.stringify(json),
        contentType: "application/json; charset=utf-8",
        success: onSuccess,
    });
}