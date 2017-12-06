<div id="page">
    <div id="page-header">
        <nav class="navbar navbar-fixed-top" role="navigation" id='nav-menu'>
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nb-menu">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">
                        <img alt="Expertus" src="/img/logo-expertus.png" class="img-responsive" />
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="nb-menu">
                    <ul class="nav navbar-nav navbar-right">
                        <#if .locale == "fr">
                            <li><a href="/?lang=en"><@spring.message "menu.englishVersion" /></a></li>
                        <#else>
                            <li><a href="/?lang=fr"><@spring.message "menu.frenchVersion" /></a></li>
                        </#if>
                        <li><a href="/game/"><@spring.message "menu.presentation" /></a></li>
                        <li><a href="/game/start"><@spring.message "menu.newGame" /></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div id="page-content">