<html>

<head>
    <title><g:message code="default.welcome.title" args="[meta(name: 'app.name')]"/></title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

<g:render template="navbarFromJetstrap"/>

<div class="hero-unit">
  <div>
    <h1>
      Grails Database Migrations Plugin
    </h1>

    <p>
      by Burt Beckwith
    </p>
  </div>
</div>

<h3>Recent updates</h3>

<div class="span9" style="width: 1170px; margin-left: 0;">

  <table class="table table-striped">
    <thead>
    <tr>

      <th class="sortable"><a href="http://localhost:8080/feedEntry/list?sort=publishedDate&max=10&order=asc">Date</a></th>

      <th class="sortable"><a href="http://localhost:8080/feedEntry/list?sort=author&max=10&order=asc">Author</a></th>

      <th class="header">Feed</th>

      <th class="sortable"><a href="http://localhost:8080/feedEntry/list?sort=title&max=10&order=asc">Title</a></th>

      <th class="sortable"><a href="http://localhost:8080/feedEntry/list?sort=link&max=10&order=asc">Link</a></th>

      <th></th>
    </tr>
    </thead>
    <tbody>


    <tr>
      <td>11/20/12 9:53 PM</td>

      <td>Burt Beckwith</td>

      <td>grails.ru</td>

      <td>Title10</td>

      <td>Link10</td>

      <td class="link">
        <a href="http://localhost:8080/feedEntry/show/10" class="btn btn-small">Show »</a>
      </td>
    </tr>

    </tbody>
  </table>

  <div class="pagination">
    <ul><li class="active"><a href="http://localhost:8080/feedEntry/list?offset=0&max=10" class="step">1</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=10&max=10" class="step">2</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=20&max=10" class="step">3</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=30&max=10" class="step">4</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=40&max=10" class="step">5</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=50&max=10" class="step">6</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=60&max=10" class="step">7</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=70&max=10" class="step">8</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=80&max=10" class="step">9</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=90&max=10" class="step">10</a></li><li><a href="http://localhost:8080/feedEntry/list?offset=10&max=10" class="nextLink" title="Next"><i class="icon-chevron-right"></i></a></li></ul>
  </div>
</div>

</body>

</html>
