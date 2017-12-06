var expertus = expertus || {};


$(document).ready(function () {
    expertus.functions.init();
});


expertus.functions = {
    init: function () {
        this.initBody();
        this.initComponents();
    },
    initBody: function () {
        var fonctions = $("body").data("onload");
        if (fonctions !== undefined && typeof fonctions === "string") {
            fonctions = fonctions.split(",");
            var i = 0, length = fonctions.length;
            for (i; i < length; ++i) {
                expertus.functions.executeFunctionByName(fonctions[i], window);
            }
        }
    },
    initComponents: function (parent) {
        if (parent === null || parent === undefined) {
            parent = $("body");
        }

        parent.find(".exp-flip").each(function () {
            expertus.flip.init($(this));
        });
    },
    executeFunctionByName: function (functionName, context /*, args */) {
        if (functionName === undefined || functionName === "") {
            return;
        }

        var args = [].slice.call(arguments).splice(2);
        var namespaces = functionName.split(".");
        var func = namespaces.pop();
        var i = 0, length = namespaces.length;
        for (i; i < length; i++) {
            context = context[namespaces[i]];
        }
        if (context !== undefined) {
            return context[func].apply(context, args);
        } else {
            console.log("executeFunctionByName context undefined : " + functionName);
        }
    }
};



