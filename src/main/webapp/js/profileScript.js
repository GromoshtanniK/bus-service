$(function(){
    $(".watching-dropdown-menu").on("click", "li", function(){
        var routeId = $(this).find("span").data("route");
        $.post({
            url: "/profile",
            data: {
                add: routeId
            },
            complete: function() {
                location.reload();
            }
        });
    });

    $(".delete-route-btn").click(function(){
        var routeId = $(this).data("route");
        $.post({
            url: "/profile",
            data: {
                delete: routeId
            },
            complete: function() {
                location.reload();
            }
        });
    })
});