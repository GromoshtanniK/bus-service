$(function () {
    $("#save_route").click(function (e) {
        var len = myMap.geoObjects.getLength();

        var placeMarkArray = [];
        for (var i = 0; i < len; i++) {
            var placeMark = myMap.geoObjects.get(i);
            var placeMarkData = {};
            placeMarkData.cords = placeMark.geometry.getCoordinates();
            placeMarkData.name = placeMark.name;
            placeMarkData.backWay = placeMark.backWay;
            placeMarkData.times = placeMark.times;
            placeMarkArray.push(placeMarkData);
        }


        $.ajax({
            type: "POST",
            url: "/edit",
            data: JSON.stringify({
                routeNumber: routeNumber,
                placeMarks: placeMarkArray
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                alert(data);
            }
        });

    });
});


var TEMPdata = {
    name: "",
    times: [{hours: 1, minutes: 2}, {}],
    id: 0,
    cords: [1234, 1234],
    backWay: true
};

function initMap(c, route, initPlaceMarks) {

    function setUpSavedPlaceMarks(yMap) {

        for (var i = 0; i < initPlaceMarks.length; i++) {
            var data = initPlaceMarks[i];

            var placeMark;

            if (data.backWay) {
                placeMark = createPlaceMark("./images/backward.png", data, yMap);
            } else {
                placeMark = createPlaceMark("./images/forward.png", data, yMap);
            }

            yMap.geoObjects.add(placeMark);
        }
    }

    ymaps.ready(function () {
        var yMap = new ymaps.Map('map', {center: c, zoom: 12, controls: ['zoomControl', 'fullscreenControl']});

        setUpSavedPlaceMarks(yMap);

        yMap.events.add('click', function (e) {

            var mapElement = $('#map');

            if (!yMap.hint.isOpen()) {
                var cords = e.get('coords');

                yMap.hint.open(cords, '<div class="context-btn"><button data-direction="forward" type="button" class="btn btn-default direction_btn">Прямое направление</button></div>' +
                    '<div class="context-btn"><button data-direction="backward" type="button" class="btn btn-default direction_btn">Обратное направление</button></div>');

                mapElement.one('click', '.direction_btn', function () {
                    var isBackWay = $(this).data("direction") == "backward";
                    var placeMark;

                    var data = {
                        name: "",
                        times: [],
                        id: 0,
                        cords: cords
                    };

                    if (isBackWay) {
                        placeMark = createPlaceMark("./images/backward.png", data, yMap);
                        placeMark.data.backWay = true;
                    } else {
                        placeMark = createPlaceMark("./images/forward.png", data, yMap);
                        placeMark.data.backWay = false;
                    }

                    yMap.geoObjects.add(placeMark);
                    yMap.hint.close(true);
                });

            } else {
                mapElement.off();
                yMap.hint.close(true);
            }
        });

    });
}

function createPlaceMark(image, data, yMap) {

    var placeMark = new ymaps.Placemark(data.cords,
        null,
        {
            iconLayout: 'default#image',
            iconImageHref: image,
            iconImageSize: [10, 10],
            iconImageOffset: [-3, -3],
            iconShape: {
                type: 'Circle',
                coordinates: [0, 0],
                radius: 10
            },
            draggable: true
        });

    placeMark.data = data;
    placeMark.events.add('contextmenu', contextMenu, yMap);
    return placeMark;
}


function contextMenu(e) {

    var yMap = this;

    e.stopPropagation();
    var mapElement = $('#map');

    if (!yMap.hint.isOpen()) {
        yMap.hint.open(e.get('coords'), '<div class="context-btn"><button data-edit="edit" type="button" class="btn btn-default edit_btn">Редактировать</button></div>'
            + '<div class="context-btn"><button data-edit="delete" type="button" class="btn btn-default edit_btn">Удалить метку</button></div>');

        mapElement.one('click', '.edit_btn', function () {
            var isDelete = $(this).data("edit") == "delete";
            if (isDelete) {
                deletePlaceMark(yMap, e);
            } else {
                showTimePanel(yMap, e);
            }
            yMap.hint.close(true);
        });

    } else {
        yMap.hint.close(true);
    }
}

var currentPlaceMark;

function deletePlaceMark(yMap, e) {
    var stops = $(".stop-times");

    if(currentPlaceMark == e.get("target")) {
        stops.empty();
    }

    yMap.geoObjects.remove(e.get("target"));
}

function showTimePanel(yMap, e) {

    currentPlaceMark = e.get("target");
    var data = currentPlaceMark.data;

    var stopName = $('<div class="stop-title"><label for="stop-title">Имя остановки:</label><input id="stop-title" type="text" value="' + data.name + '"/></div>');
    var timeTable = $('<div class="time-table"></div>');
    var add = $('<div class="stop-time"><button class="add-stop-time"><span class="glyphicon glyphicon-plus"></span></button></div>');
    var stops = $(".stop-times");

    stops.empty();
    stops.append(stopName);
    stops.append(timeTable);
    stops.append(add);

    stopName.find("#stop-title").change(function () {
        currentPlaceMark.data.name = this.value;
    });

    for (var i = 0; i < data.times.length; i++) {
        timeTable.append($('<div class="stop-time"><input class="time-input hours" value="' + data.times[i].hours
            + '">:<input class="time-input minutes" value="' + data.times[i].minutes + '">' +
            '<button class="delete-stop-time"><span class="glyphicon glyphicon-minus"></span></button></div>'));
    }
}

/*
 placeMark.events.add('contextmenu', function(e){
 e.stopPropagation();

 var mapElement = $('#map');

 if(!yMap.hint.isOpen()) {

 yMap.hint.open(e.get('coords'), '<div class="context-btn"><button data-edit="edit" type="button" class="btn btn-default edit_btn">Редактировать</button></div>'
 + '<div class="context-btn"><button data-edit="delete" type="button" class="btn btn-default edit_btn">Удалить метку</button></div>');

 //mapElement.one('click', '.edit_btn', function(){
 //    var isDelete = $(this).data("edit") == "delete";
 //
 //    if(isDelete) {
 //        for(var i = 0; i < placeMarksDataArray.length; i++) {
 //            if(placeMarksDataArray[i] == data) {
 //                yMap.geoObjects.remove(data.mark);
 //                delete placeMarksDataArray[i];
 //            }
 //        }
 //    } else {
 //        var stopName = $('<div class="stop-title"><label for="stop-title">Имя остановки:</label><input id="stop-title" type="text" value="' + data.name + '"/></div>');
 //        var timeTable = $('<div class="time-table"></div>');
 //        var add = $('<div class="stop-time"><button class="add-stop-time"><span class="glyphicon glyphicon-plus"></span></button></div>');
 //        var stops = $(".stop-times");
 //
 //        stops.empty();
 //        stops.append(stopName);
 //        stops.append(timeTable);
 //        stops.append(add);
 //
 //        stopName.find("#stop-title").change(function() {
 //            data.changed = 1;
 //            data.name = this.value;
 //        });
 //
 //
 //        for(var j = 0; j < data.times.length; j++) {
 //            timeTable.append($('<div class="stop-time"><input class="time-input hours" value="' + data.times[j].hours
 //                + '">:<input class="time-input minutes" value="' + data.times[j].minutes + '">' +
 //                '<button class="delete-stop-time"><span class="glyphicon glyphicon-minus"></span></button></div>'));
 //        }
 //
 //        $(".delete-stop-time").click(function(){
 //            $(this).parent().remove();
 //            currentTimeObj.deleted = true;
 //        });
 //
 //        add.find(".add-stop-time").click(function(){
 //            var timeInput = $('<div class="stop-time"><input class="time-input hours">:<input class="time-input minutes">' +
 //                '<button class="delete-stop-time"><span class="glyphicon glyphicon-minus"></span></button></div>');
 //            timeTable.append(timeInput);
 //
 //            var elemIndex = data.times.length;
 //            data.times[elemIndex] = {};
 //            var currentTimeObj = data.times[elemIndex];
 //
 //            timeInput.find(".hours").change(function(){
 //                currentTimeObj.hours = this.value;
 //            });
 //            timeInput.find(".minutes").change(function(){
 //                currentTimeObj.minutes = this.value;
 //            });
 //
 //            timeInput.find(".delete-stop-time").click(function(){
 //                $(this).parent().remove();
 //                currentTimeObj.deleted = true;
 //            });
 //
 //        });
 //
 //    }
 //
 //    yMap.hint.close(true);
 //});

 } else {
 mapElement.off();
 yMap.hint.close(true);
 }

 });
 */