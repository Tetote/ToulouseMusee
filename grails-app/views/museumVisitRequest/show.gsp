
<%@ page import="toulousemusee.MuseumVisitRequest" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'museumVisitRequest.label', default: 'MuseumVisitRequest')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-museumVisitRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-museumVisitRequest" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list museumVisitRequest">
			
				<g:if test="${museumVisitRequestInstance?.requestDate}">
				<li class="fieldcontain">
					<span id="requestDate-label" class="property-label"><g:message code="museumVisitRequest.requestDate.label" default="Request Date" /></span>
					
						<span class="property-value" aria-labelledby="requestDate-label"><g:formatDate date="${museumVisitRequestInstance?.requestDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumVisitRequestInstance?.museum}">
				<li class="fieldcontain">
					<span id="museum-label" class="property-label"><g:message code="museumVisitRequest.museum.label" default="Museum" /></span>
					
						<span class="property-value" aria-labelledby="museum-label"><g:link controller="museum" action="show" id="${museumVisitRequestInstance?.museum?.id}">${museumVisitRequestInstance?.museum?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumVisitRequestInstance?.visitRequest}">
				<li class="fieldcontain">
					<span id="visitRequest-label" class="property-label"><g:message code="museumVisitRequest.visitRequest.label" default="Visit Request" /></span>
					
						<span class="property-value" aria-labelledby="visitRequest-label"><g:link controller="visitRequest" action="show" id="${museumVisitRequestInstance?.visitRequest?.id}">${museumVisitRequestInstance?.visitRequest?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:museumVisitRequestInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${museumVisitRequestInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
