
<%@ page import="toulousemusee.VisitRequest" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'visitRequest.label', default: 'VisitRequest')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-visitRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-visitRequest" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list visitRequest">
			
				<g:if test="${visitRequestInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="visitRequest.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${visitRequestInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitRequestInstance?.startPeriodDate}">
				<li class="fieldcontain">
					<span id="startPeriodDate-label" class="property-label"><g:message code="visitRequest.startPeriodDate.label" default="Start Period Date" /></span>
					
						<span class="property-value" aria-labelledby="startPeriodDate-label"><g:formatDate date="${visitRequestInstance?.startPeriodDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitRequestInstance?.endPeriodDate}">
				<li class="fieldcontain">
					<span id="endPeriodDate-label" class="property-label"><g:message code="visitRequest.endPeriodDate.label" default="End Period Date" /></span>
					
						<span class="property-value" aria-labelledby="endPeriodDate-label"><g:formatDate date="${visitRequestInstance?.endPeriodDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitRequestInstance?.nbPeople}">
				<li class="fieldcontain">
					<span id="nbPeople-label" class="property-label"><g:message code="visitRequest.nbPeople.label" default="Nb People" /></span>
					
						<span class="property-value" aria-labelledby="nbPeople-label"><g:fieldValue bean="${visitRequestInstance}" field="nbPeople"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${visitRequestInstance?.museumVisitRequest}">
				<li class="fieldcontain">
					<span id="museumVisitRequest-label" class="property-label"><g:message code="visitRequest.museumVisitRequest.label" default="Museum Visit Request" /></span>
					
						<g:each in="${visitRequestInstance.museumVisitRequest}" var="m">
						<span class="property-value" aria-labelledby="museumVisitRequest-label"><g:link controller="museumVisitRequest" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${visitRequestInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="visitRequest.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${visitRequestInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:visitRequestInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${visitRequestInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
