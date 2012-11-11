<div class="container">
    <header class="jumbotron subhead" id="overview">
        <g:if test="${useLargeHeader}">
            <div class="row">
                <div class="span12">
                    <h1>Grails toolbox</h1>
                    <p class="lead">where plugins live...</p>
                </div>
            </div>
        </g:if>

        <div class="subnav">
            <ul class="nav nav-pills">
                <g:if test="${!useLargeHeader}">
                    <li><a class="brand" href="#">Grails Toolbox</a></li>
                </g:if>

                <li class="active"><a href="#typography">Typography</a></li>
                <li class=""><a href="#navbar">Navbar</a></li>
                <li class=""><a href="#buttons">Buttons</a></li>
                <li class=""><a href="#forms">Forms</a></li>
                <li class=""><a href="#miscellaneous">Miscellaneous</a></li>
            </ul>
        </div>
    </header>
</div>