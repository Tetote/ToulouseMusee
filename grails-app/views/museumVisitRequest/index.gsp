
<%@ page import="toulousemusee.MuseumVisitRequest" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'museumVisitRequest.label', default: 'MuseumVisitRequest')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-museumVisitRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-museumVisitRequest" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="requestDate" title="${message(code: 'museumVisitRequest.requestDate.label', default: 'Request Date')}" />
					
						<th><g:message code="museumVisitRequest.museum.label" default="Museum" /></th>
					
						<th><g:message code="museumVisitRequest.visitRequest.label" default="Visit Request" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${museumVisitRequestInstanceList}" status="i" var="museumVisitRequestInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${museumVisitRequestInstance.id}">${fieldValue(bean: museumVisitRequestInstance, field: "requestDate")}</g:link></td>
					
						<td>${fieldValue(bean: museumVisitRequestInstance, field: "museum")}</td>
					
						<td>${fieldValue(bean: museumVisitRequestInstance, field: "visitRequest")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${museumVisitRequestInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
