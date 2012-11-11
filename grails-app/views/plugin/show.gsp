<html>

<head>
    <title><g:message code="default.welcome.title" args="[meta(name: 'app.name')]"/></title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

<g:render template="/landing/navbarFromJetstrap"/>

<div class="hero-unit">
  <div>
    <h1>
      ${pluginInstance.name}
    </h1>

    <p>
      by ${pluginInstance.author}
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

      <th></th>
    </tr>
    </thead>
    <tbody>

    <tr>
    <td>11/20/12 9:53 PM</td>

    <td>Burt Beckwith</td>

    <td>grails.ru</td>

    <td>Title10</td>

    <td class="link">
      <a href="http://localhost:8080/feedEntry/show/10" class="btn btn-small">Show »</a>
    </td>
    </tr>

    <tr>
      <g:each in="${updates}" var="update">
        <td>${update.publishedDate}</td>
        <td>${update.author}</td>
        <td>${update.feed.url}</td>
        <td>${update.title}</td>
        <td class="link">
          <a href="${update.feed.url}" class="btn btn-small">Show »</a>
        </td>
      </g:each>
    </tr>

    </tbody>
  </table>

  <div class="pagination">
    <bootstrap:paginate total="${updatesTotal}"/>
  </div>
</div>

</body>

</html>
