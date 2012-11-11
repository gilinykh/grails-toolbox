<div class="container">
    <header class="jumbotron subhead" id="overview">
        <g:if test="${useLargeHeader}">
            <div class="row">
                <div class="span12">
                    <h1>Grails toolbox</h1>
                    <p class="lead">where plugins live...%{--All the news on plugin ecosystem in one place--}%</p>
                </div>
            </div>
        </g:if>

        <div class="subnav">
            <ul class="nav nav-pills">
                <g:if test="${!useLargeHeader}">
                    <tb:navBarItem controller="test" action="main" message="navBar.grailsToolbox"/>
                </g:if>

                <tb:navBarItem controller="resource" action="recent" message="navBar.resources.recent"/>
            </ul>
        </div>
    </header>
</div>