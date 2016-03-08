function forward(cords, map) {
    var mark = new ymaps.Placemark(cords,
        null,
        {
            iconLayout: 'default#image',
            iconImageHref: "./images/avtstn.png",
            iconImageSize: [10, 10],
            iconImageOffset: [-3, -3],
            iconShape: {
                type: 'Circle',
                coordinates: [0, 0],
                radius: 10
            },
            draggable: true
        });
    map.geoObjects.add(mark);
    map.hint.close(true);
}

function backward(cords, map) {
    var mark = new ymaps.Placemark(cords,
        null,
        {
            iconLayout: 'default#image',
            iconImageHref: "./images/trmstn.png",
            iconImageSize: [10, 10],
            iconImageOffset: [-3, -3],
            iconShape: {
                type: 'Circle',
                coordinates: [0, 0],
                radius: 10
            },
            draggable: true
        });
    map.geoObjects.add(mark);
    map.hint.close(true);
}