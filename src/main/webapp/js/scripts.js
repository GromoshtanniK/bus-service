$(function(){
    $("#save_route").click(function(e) {
        var len = myMap.geoObjects.getLength();

        var placeMarkArray = [];
        for(var i = 0; i < len; i++) {
            var placeMark = myMap.geoObjects.get(i);
            var placeMarkData = {};
            placeMarkData.cords = placeMark.geometry.getCoordinates();
            placeMarkData.name = placeMark.name;
            placeMarkData.times = placeMark.times;
            placeMarkArray.push(placeMarkData);
        }

        $.ajax({
            type: "POST",
            url: "/dispatch",
            data: JSON.stringify(placeMarkArray),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){alert(data);},
            failure: function(errMsg) {
                alert(errMsg);
            }
        });

    });
});

showingMark = null;

function initMap(c) {
    ymaps.ready(function () {

        myMap = new ymaps.Map('map', { center: c, zoom: 12, controls: ['zoomControl', 'fullscreenControl']});

        myMap.events.add('click', function (e) {
            if(!myMap.hint.isOpen()) {
                cords = e.get('coords');
                myMap.hint.open(cords, '<div class="context-btn"><button onclick="forward(cords, myMap)" type="button" class="btn btn-default">Прямое направление</button></div>' +
                    '<div class="context-btn"><button onclick="backward(cords, myMap)" type="button" class="btn btn-default">Обратное направление</button></divdirection-btn>');
            } else {
                myMap.hint.close(true);
            }
        });
    });
}

function forward(cords, map) {
    createPlaceMark("./images/avtstn.png", cords, map);
}

function backward(cords, map) {
   createPlaceMark("./images/trmstn.png", cords, map);
}

function createPlaceMark(image, cords, map) {
    var mark = new ymaps.Placemark(cords,
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
    mark.name = "";
    mark.times = [];
    mark.events.add('contextmenu', markMenu);
    map.geoObjects.add(mark);
    map.hint.close(true);
}

function markMenu(e) {
    e.stopPropagation();
    currentMark = e._cache.target;
    myMap.hint.open(e.get('coords'), '<div class="context-btn"><button onclick="info()" type="button" class="btn btn-default">Редактировать</button></div>'
        + '<div class="context-btn"><button onclick="removeMark()" type="button" class="btn btn-default">Удалить метку</button></div>');
}

function info() {
    showingMark = currentMark;
    var name = currentMark.name;
    var stopName = $('<div class="stop-title"><label for="stop-title">Имя остановки:</label><input id="stop-title" type="text" value="' + name + '"/></div>');
    var add = $('<div class="stop-time"><button class="add-stop-time"><span class="glyphicon glyphicon-plus"></span></button></div>');
    var timeTable = $('<div class="time-table"></div>');
    var stops = $(".stop-times");

    stops.empty();
    stops.append(stopName);
    stops.append(timeTable);
    stops.append(add);

    for(var i = 0; i < currentMark.times.length; i++) {
        timeTable.append($('<div class="stop-time"><input class="time-input hours" value="' + currentMark.times[i].hours
            + '">:<input class="time-input minutes" value="' + currentMark.times[i].minutes + '"></div>'));
    }

    stopName.find("#stop-title").change(function() {
        currentMark.name = this.value;
    });

    add.find(".add-stop-time").click(function(){
        var timeInput = $('<div class="stop-time"><input class="time-input hours">:<input class="time-input minutes"></div>');
        timeTable.append(timeInput);
        var elemIndex = currentMark.times.length;
        currentMark.times[elemIndex] = {};
        var currentTimeObj = currentMark.times[elemIndex];
        timeInput.find(".hours").change(function(){
            currentTimeObj.hours = this.value;
        });
        timeInput.find(".minutes").change(function(){
            currentTimeObj.minutes = this.value;
        });
    });

    myMap.hint.close(true);
}

function removeMark() {
    if(showingMark == currentMark) {
        $(".stop-times").empty();
    }
    myMap.geoObjects.remove(currentMark);
    myMap.hint.close(true);
}