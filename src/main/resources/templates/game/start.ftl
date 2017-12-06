<#include "../base/html-start.ftl" />
<#include "../base/body-start.ftl" />
<div class="container">
    <div id="gameContainer" data-game-id="${gameId}">
        <div id="gameMessages" class="text-center">
            <h2><@spring.message "game.start.explanation" /></h2>
            <p>
                <a class="btn btn-default" id="startGame"><@spring.message "game.start.loadQuestion" /></a>
            </p>
        </div>
        <p class="text-center text-info">
            <i class="glyphicon glyphicon-info-sign"></i>&nbsp;<@spring.message "game.start.helptrombi" />
        </p>
        <div id="gamePersons">

        </div>
    </div>
</div>
<#include "../base/body-end.ftl" />
<#include "../base/html-end.ftl" />