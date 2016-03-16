$(function(){
    $("#add_route_button").click(function(){

        $.post({
            url: "/add_delete",
            data: {
                add: $("#route_number").val()
            },
            complete: function() {
                location.reload();
            }
        });
    });

    $(".delete-route-btn").click(function(){

        var route = $(this).data("route");
        $.post({
            url: "/add_delete",
            data: {
                delete: route
            },
            complete: function() {
                location.reload();
            }
        });
    })
});