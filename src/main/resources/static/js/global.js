var expertus = expertus || {};

expertus.flip = {
    init: function (elem) {
        elem.flip(elem.data());
        if(elem.data("delay") !== undefined) {
            setInterval(function() {elem.flip('toggle');}, elem.data("delay"));
        }
    }
};

var expertus = expertus || {};

expertus.game = {
    gameId: undefined,
    start: function() {
        this.gameId = $("#gameContainer").data("gameId");
        $("#startGame").click(function() {
            expertus.game.loadQuestion();
        });

        this.loadPersons();

        //Bind gameAnswer event
        $("#gameMessages").on("click", ".gameAnswer", expertus.game.sendAnswer);
    },
    loadPersons:function(callback) {
        $.ajax({
            method: 'GET',
            contentType: 'application/json; charset=utf-8',
            url: "/game/listPersons/"+this.gameId,
            success: function (datas) {
                if(callback !== undefined) {
                    callback(datas);
                } else {
                    expertus.game.addPersons(datas);
                }
            },
            dataType: "json"
        });
    },
    loadQuestion:function() {
        $.ajax({
            method: 'GET',
            contentType: 'application/json; charset=utf-8',
            url: "/game/loadQuestion/"+this.gameId,
            success: function (datas) {
                expertus.game.addQuestion(datas);
            },
            dataType: "json"
        });
    },
    loadEnd:function() {
        $("#gameMessages").load("/game/end");
    },
    sendAnswer:function() {
        $.ajax({
            method: 'GET',
            contentType: 'application/json; charset=utf-8',
            data:{correct:$(this).data("value")},
            url: "/game/answer/"+expertus.game.gameId+"/"+$(this).data("question"),
            success: function (datas) {
                if(datas.finished) {
                    expertus.game.loadEnd();
                } else {
                    expertus.game.loadQuestion();
                }
                expertus.game.loadPersons(expertus.game.updatePersons);

            },
            dataType: "json"
        });
    },
    addPersons: function(persons) {
        var container = $("#gamePersons");
        container.hide();
        container.html("");
        var cpt = 0;
        persons.forEach(function(person)  {
            container.append("<div id=\"person-"+person.id+"\" class=\"game-card exp-flip\" data-trigger=\"manual\" data-axis=\"x\"'>\n" +
                "                <div class=\"front\">\n" +
                "                <img src=\"/img/e.jpg\" class=\"img-responsive\" />\n" +
                "                </div>\n" +
                "                <div class=\"back\">\n" +
                "                <a href=\"/img/trombi/"+person.picto+".jpg\" data-lightbox=\""+person.picto+"\" data-title=\""+person.firstname+" "+person.lastname+"\">\n" +
                "                <img src=\"/img/trombi/"+person.picto+".jpg\" class=\"img-responsive\" />\n" +
                "                </a></div>\n" +
                "                </div>");
                cpt++;
                if(cpt%6 == 0) {
                    container.append("<div class='clearfix'></div>")
                }
        });
        expertus.functions.initComponents(container);
        container.show();
        setTimeout(function() {
            var i = 0;
            $(".game-card").each(function() {
                var id = $(this).attr("id");
                setTimeout(function() {$("#"+id).flip('toggle');},i*200);
                i++;
            });
            },1000);
    },
    updatePersons: function(persons) {
        var i = 0;
        $(".game-card").each(function() {
            var id = $(this).attr("id");
            var personId = id.replace("person-","");
            var found = false;
            for(var cpt = 0;cpt < persons.length;cpt++) {
                if(persons[cpt].id == personId) {
                    found = true;
                    i++;
                    break;
                }
            }
            if(!found) {
                setTimeout(function() {
                    $("#"+id).flip('toggle');
                    $("#"+id).addClass("game-card-suppressed");
                    },
                    i*500);
            }
        });
        setTimeout(function() {
            $(".game-card-suppressed").fadeOut("slow", function() {$(this).remove();});
            },
            (i+1)*500);
    },
    addQuestion: function(question) {
        var container = $("#gameMessages");
        container.html("");
        container.append("<div class='text-center gameQuestion'>" +
            "<h2 class='text-center'>"+question.label+"</h2>"+
            "<a data-question='"+question.id+"' data-value='true' class='btn btn-success gameAnswer'><i class='glyphicon glyphicon-ok'></i></a>"+
            "<a data-question='"+question.id+"' data-value='false' class='btn btn-danger gameAnswer'><i class='glyphicon glyphicon-remove'></i></a>"+
            "</div>");
    }
};

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



