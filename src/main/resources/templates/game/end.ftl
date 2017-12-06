<#include "../base/global.ftl" />
<div class="text-center">
<h2><@spring.message "game.end.title" /></h2>

<#if gameInfo.succeeded>
    <p class="text-success">
        <@spring.message "game.end.win" />
        <br />
        <b>${personName}</b>
    </p>
<#else>
    <p class="text-danger">
        <@spring.message "game.end.loose" />
    </p>
</#if>
    <p>
        <a class="btn btn-default" href="/game/start"><@spring.message "game.end.newGame" /></a>
    </p>
</div>