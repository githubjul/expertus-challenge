var expertus = expertus || {};

expertus.flip = {
    init: function (elem) {
        elem.flip(elem.data());
        if(elem.data("delay") !== undefined) {
            setInterval(function() {elem.flip('toggle');}, elem.data("delay"));
        }
    }
};