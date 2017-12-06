<#include "base/html-start.ftl" />
<#include "base/body-start.ftl" />
<div class="container-fluid" id="home">
    <h1><@spring.message "home.hello" /></h1>
    <div class="flip-card exp-flip" data-manual="true" data-delay="700">
        <div class="front">
            <img src="/img/e.jpg" class="img-responsive" />
        </div>
        <div class="back">
            <img src="/img/eb.jpg" class="img-responsive" />
        </div>
    </div>
    <a href="/game/" class="btn btn-default"><@spring.message "home.letsplay" /></a>
</div>
<#include "base/body-end.ftl" />
<#include "base/html-end.ftl" />