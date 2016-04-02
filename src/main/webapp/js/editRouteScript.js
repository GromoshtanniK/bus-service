//var TEMPdata = {
//    name: "",
//    times: [{hours: 1, minutes: 2}, {}],
//    id: 0,
//    cords: [1234, 1234],
//    backWay: true
//};

var deletedStops = [];
var newStops = [];
var changedStops = [];

function trimTime(placeMarkArray) {
    for(var i = 0; i < placeMarkArray.length; i++) {
        placeMarkArray[i].times = trimPlaceMarkTimes(placeMarkArray[i].times);
    }

}

function setUpSaveButton(yMap, route, initPlaceMarks) {
    $("#save_route").click(function () {
        var len = yMap.geoObjects.getLength();

        var placeMarks = [];

        for (var i = 0; i < len; i++) {
            var placeMark = yMap.geoObjects.get(i);
            placeMark.data.cords = placeMark.geometry.getCoordinates();
            placeMarks.push(placeMark.data);
        }

        recognizeChanges(initPlaceMarks, placeMarks);

        trimTime(deletedStops);
        trimTime(newStops);
        trimTime(changedStops);

        console.log("DELETED");
        console.log(deletedStops);

        console.log("NEW");
        console.log(newStops);

        console.log("CHANGED");
        console.log(changedStops);

        $.ajax({
            type: "POST",
            url: "/edit",
            data: JSON.stringify({
                routeNumber: route,
                deleted: deletedStops,
                added: newStops,
                changed: changedStops
            }),

            contentType: "application/json; charset=utf-8",

            success: function() {
                location.reload();
            },
            complete : function() {
                console.log(123123);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        });
    });
}

function findDeletedStops(initPlaceMarks, placeMarks) {
    for(var i = 0; i < initPlaceMarks.length; i++) {
        if(findInArrayById(placeMarks, initPlaceMarks[i].id) == undefined) {
            deletedStops.push(initPlaceMarks[i]);
        }
    }
}

function findInArrayById(array, id) {
    for(var i = 0; i < array.length; i++) {
        if(array[i].id == id) {
            return array[i];
        }
    }
    return undefined;
}

function findNewStops(placeMarks) {
    for (var i = 0; i < placeMarks.length; i++) {
        if(placeMarks[i].id == 0) {
            newStops.push(placeMarks[i]);
        }
    }
}

function timesDiffers(initTimes, currentTimes, currentPlaceMark) {

    var deletedTimes = [];
    var addedTimes = [];
    var changedTimes = [];

    for (var i = 0; i < currentTimes.length; i++) {
        if(currentTimes[i] == undefined && initTimes[i] != undefined) { //время удалено, но возможно в оригинальном объекте ее и не было
            deletedTimes.push(initTimes[i]);
        }

        if(initTimes[i] == undefined && currentTimes[i] != undefined) { //время добавлено, но возможно в дальнейшем удалено
            addedTimes.push(currentTimes[i]);
        }

        if(currentTimes[i] != undefined && initTimes[i] != undefined) {
            if(currentTimes[i].hours != initTimes[i].hours || currentTimes[i].minutes != initTimes[i].minutes) {
                changedTimes.push(currentTimes[i]);
            }
        }

        currentPlaceMark.deletedTime = deletedTimes;
        currentPlaceMark.addedTimes = addedTimes;
        currentPlaceMark.changedTimes = changedTimes;
    }

    if(deletedTimes.length != 0 || addedTimes.length != 0 || changedTimes.length != 0) {
        return true;
    } else {
        return false;
    }
}


function findChangedStops(initPlaceMarks, placeMarks) {
    for (var i = 0; i < initPlaceMarks.length; i++) {
        var mark = findInArrayById(placeMarks, initPlaceMarks[i].id);
        if(mark != undefined) { //точка существовала и не удалена

            var deletedTimes = [];
            var addedTimes = [];
            var changedTimes = [];

            var changedName = false;
            var changedCords = false;
            var changedTime = false;

            if(initPlaceMarks[i].name != mark.name) {
                changedName = true;
            }

            if(initPlaceMarks[i].cords[0] != mark.cords[0] || initPlaceMarks[i].cords[1] != mark.cords[1]) {
                changedCords = true;
            }

            if(changedName || changedCords) {
                mark.deletedTime = deletedTimes;
                mark.addedTimes = addedTimes;
                mark.changedTimes = changedTimes;
            }

            if(timesDiffers(initPlaceMarks[i].times, mark.times, mark)) {
                changedTime = true;
            }

            if(changedName || changedCords || changedTime) {
                mark.changedName = changedName;
                mark.changedCords = changedCords;
                changedStops.push(mark);
            }
        }
    }
}

function recognizeChanges(initPlaceMarks, placeMarks) {
    deletedStops = [];
    newStops = [];
    changedStops = [];

    findDeletedStops(initPlaceMarks, placeMarks);
    findNewStops(placeMarks);
    findChangedStops(initPlaceMarks, placeMarks);
}

function trimPlaceMarkTimes(times) {

    if(times == undefined) {
        return times;
    }

    var timesWithoutUndefined = [];
    for (var i = 0; i < times.length; i++) {
        if (times[i] !== undefined) {
            timesWithoutUndefined[timesWithoutUndefined.length] = times[i];
        }
    }
    return timesWithoutUndefined;
}

function initMap(routeId, c, route, initPlaceMarks) {
    ymaps.ready(function () {
        var yMap = new ymaps.Map('map', {center: c, zoom: 12, controls: ['zoomControl', 'fullscreenControl']});
        setUpSaveButton(yMap, route, initPlaceMarks);
        setUpSavedPlaceMarks(yMap, initPlaceMarks);

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
                        cords: cords,
                        routeId: routeId
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

function setUpSavedPlaceMarks(yMap, initPlaceMarks) {

    for (var i = 0; i < initPlaceMarks.length; i++) {
        var data = $.extend(true, {}, initPlaceMarks[i]);
        var placeMark;

        if (data.backWay) {
            placeMark = createPlaceMark("./images/backward.png", data, yMap);
        } else {
            placeMark = createPlaceMark("./images/forward.png", data, yMap);
        }

        yMap.geoObjects.add(placeMark);
    }
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
                showTimePanel(e);
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

    if (currentPlaceMark == e.get("target")) {
        stops.empty();
    }

    yMap.geoObjects.remove(e.get("target"));
}

function showTimePanel(e) {

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

    add.find(".add-stop-time").click(function () {
        data.times[data.times.length] = {hours: "", minutes: "", id: 0, roteStopId: data.id};
        timeTable.empty();
        showTimes(timeTable, data, e);
    });

    showTimes(timeTable, data, e);
}

function showTimes(timeTable, data) {

    var timePlaces = [];

    for (var i = 0; i < data.times.length; i++) {
        if (data.times[i] !== undefined) {
            var timePlace = $('<div class="stop-time"><input class="time-input hours" data-index="' + i + '" value="' + data.times[i].hours
                + '">:<input class="time-input minutes" data-index="' + i + '" value="' + data.times[i].minutes + '">' +
                '<button class="delete-stop-time" data-index="' + i + '"><span class="glyphicon glyphicon-minus"></span></button></div>');

            timeTable.append(timePlace);
            timePlaces[i] = timePlace;

            timePlace.find(".delete-stop-time").click(function () {
                var deleteIndex = $(this).data("index");
                delete data.times[deleteIndex];
                timePlaces[deleteIndex].remove();
            });

            timePlace.find(".hours").change(function () {
                var index = $(this).data("index");
                data.times[index].hours = $(this).val();
            });

            timePlace.find(".minutes").change(function () {
                var index = $(this).data("index");
                data.times[index].minutes = $(this).val();
            });
        }
    }
}