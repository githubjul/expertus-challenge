<#include "../base/html-start.ftl" />
<#include "../base/body-start.ftl" />
<div class="container">
    <h1 class="text-center"><@spring.message "game.title" /></h1>
    <p>
        <@spring.message "game.explanation" />
    </p>
    <p>
        <b><@spring.message "game.explanationChallenge" /></b>
    </p>
    <p class="text-center">
        <a href="/game/start" class="btn btn-default"><@spring.message "game.start" /></a>
    </p>
</div>
<#include "../base/body-end.ftl" />
<#include "../base/html-end.ftl" />