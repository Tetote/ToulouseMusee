
<%@ page import="toulousemusee.Museum" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'museum.label', default: 'Museum')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

		<style>
			body  {
				max-width:1300px;
			}
		</style>
	</head>
	<body>
		<a href="#list-museum" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-museum" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'museum.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="hours" title="${message(code: 'museum.hours.label', default: 'Hours')}" />
					
						<g:sortableColumn property="phoneNumber" title="${message(code: 'museum.phoneNumber.label', default: 'Phone Number')}" />
					
						<g:sortableColumn property="metroAccess" title="${message(code: 'museum.metroAccess.label', default: 'Metro Access')}" />
					
						<g:sortableColumn property="busAccess" title="${message(code: 'museum.busAccess.label', default: 'Bus Access')}" />

						<th><g:message code="museum.address.label" default="Address" /></th>

						<th><g:message code="museum.manager.label" default="Manager" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${museumInstanceList}" status="i" var="museumInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${museumInstance.id}">${fieldValue(bean: museumInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: museumInstance, field: "hours")}</td>
					
						<td>${fieldValue(bean: museumInstance, field: "phoneNumber")}</td>
					
						<td>${fieldValue(bean: museumInstance, field: "metroAccess")}</td>
					
						<td>${fieldValue(bean: museumInstance, field: "busAccess")}</td>
					
						<td>${fieldValue(bean: museumInstance, field: "address")}</td>

						<td>${fieldValue(bean: museumInstance, field: "manager")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${museumInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
