
<%@ page import="toulousemusee.VisitRequest" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'visitRequest.label', default: 'VisitRequest')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-visitRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-visitRequest" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'visitRequest.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="startPeriodDate" title="${message(code: 'visitRequest.startPeriodDate.label', default: 'Start Period Date')}" />
					
						<g:sortableColumn property="endPeriodDate" title="${message(code: 'visitRequest.endPeriodDate.label', default: 'End Period Date')}" />
					
						<g:sortableColumn property="nbPeople" title="${message(code: 'visitRequest.nbPeople.label', default: 'Nb People')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'visitRequest.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${visitRequestInstanceList}" status="i" var="visitRequestInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${visitRequestInstance.id}">${fieldValue(bean: visitRequestInstance, field: "code")}</g:link></td>
					
						<td><g:formatDate date="${visitRequestInstance.startPeriodDate}" /></td>
					
						<td><g:formatDate date="${visitRequestInstance.endPeriodDate}" /></td>
					
						<td>${fieldValue(bean: visitRequestInstance, field: "nbPeople")}</td>
					
						<td>${fieldValue(bean: visitRequestInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${visitRequestInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
