function initMap(c, initPlaceMarks) {
    ymaps.ready(function () {
        myMap = new ymaps.Map('map', { center: c, zoom: 12, controls: ['zoomControl', 'fullscreenControl']});
        showPlaceMarks(initPlaceMarks);
        myMap.events.add('click', clear);
    });
}

function showPlaceMarks(initPlaceMarks) {
    for(var i = 0; i < initPlaceMarks.length; i++) {
        var placeMarkData = initPlaceMarks[i];

        var mark;
        if(!placeMarkData.backWay) {
            mark = createPlaceMark("./images/avtstn.png", placeMarkData.coordinates);
        } else {
            mark = createPlaceMark("./images/trmstn.png", placeMarkData.coordinates);
        }
        mark.name = placeMarkData.name;
        mark.times = placeMarkData.times;
    }
}

function createPlaceMark(image, cords) {
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
            }
        });
    mark.events.add('click', showTime, mark);
    myMap.geoObjects.add(mark);
    return mark;
}

function showTime() {
    var mark = this;
    clear();

    var timesBlock = $(".stop-times");
    if(mark.name != "") {
        timesBlock.append('<div class="stop-title">Остановка: ' + mark.name + '</div>')
    } else {
        timesBlock.append('<div class="stop-title">Остановка без имени</div>')
    }

    for(var i = 0; i < mark.times.length; i++) {
        var time = mark.times[i];
        timesBlock.append('<div class="stop-time">' + time.hours + ' : ' + time.minutes + '</div>')
    }
}

function clear() {
    $(".stop-times").empty();
}