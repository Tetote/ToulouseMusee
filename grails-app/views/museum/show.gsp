
<%@ page import="toulousemusee.Museum" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'museum.label', default: 'Museum')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-museum" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-museum" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list museum">
			
				<g:if test="${museumInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="museum.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${museumInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.hours}">
				<li class="fieldcontain">
					<span id="hours-label" class="property-label"><g:message code="museum.hours.label" default="Hours" /></span>
					
						<span class="property-value" aria-labelledby="hours-label"><g:fieldValue bean="${museumInstance}" field="hours"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.phoneNumber}">
				<li class="fieldcontain">
					<span id="phoneNumber-label" class="property-label"><g:message code="museum.phoneNumber.label" default="Phone Number" /></span>
					
						<span class="property-value" aria-labelledby="phoneNumber-label"><g:fieldValue bean="${museumInstance}" field="phoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.metroAccess}">
				<li class="fieldcontain">
					<span id="metroAccess-label" class="property-label"><g:message code="museum.metroAccess.label" default="Metro Access" /></span>
					
						<span class="property-value" aria-labelledby="metroAccess-label"><g:fieldValue bean="${museumInstance}" field="metroAccess"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.busAccess}">
				<li class="fieldcontain">
					<span id="busAccess-label" class="property-label"><g:message code="museum.busAccess.label" default="Bus Access" /></span>
					
						<span class="property-value" aria-labelledby="busAccess-label"><g:fieldValue bean="${museumInstance}" field="busAccess"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.manager}">
				<li class="fieldcontain">
					<span id="manager-label" class="property-label"><g:message code="museum.manager.label" default="Manager" /></span>
					
						<span class="property-value" aria-labelledby="manager-label"><g:link controller="manager" action="show" id="${museumInstance?.manager?.id}">${museumInstance?.manager?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="museum.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:link controller="address" action="show" id="${museumInstance?.address?.id}">${museumInstance?.address?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${museumInstance?.museumVisitRequest}">
				<li class="fieldcontain">
					<span id="museumVisitRequest-label" class="property-label"><g:message code="museum.museumVisitRequest.label" default="Museum Visit Request" /></span>
					
						<g:each in="${museumInstance.museumVisitRequest}" var="m">
						<span class="property-value" aria-labelledby="museumVisitRequest-label"><g:link controller="museumVisitRequest" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:museumInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${museumInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
