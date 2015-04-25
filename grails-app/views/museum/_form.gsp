<%@ page import="toulousemusee.Museum" %>



<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="museum.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${museumInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'hours', 'error')} required">
	<label for="hours">
		<g:message code="museum.hours.label" default="Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="hours" required="" value="${museumInstance?.hours}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'phoneNumber', 'error')} required">
	<label for="phoneNumber">
		<g:message code="museum.phoneNumber.label" default="Phone Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phoneNumber" required="" value="${museumInstance?.phoneNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'metroAccess', 'error')} ">
	<label for="metroAccess">
		<g:message code="museum.metroAccess.label" default="Metro Access" />
		
	</label>
	<g:textField name="metroAccess" value="${museumInstance?.metroAccess}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'busAccess', 'error')} ">
	<label for="busAccess">
		<g:message code="museum.busAccess.label" default="Bus Access" />
		
	</label>
	<g:textField name="busAccess" value="${museumInstance?.busAccess}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'manager', 'error')} required">
	<label for="manager">
		<g:message code="museum.manager.label" default="Manager" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="manager" name="manager.id" from="${toulousemusee.Manager.list()}" optionKey="id" required="" value="${museumInstance?.manager?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="museum.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="address" name="address.id" from="${toulousemusee.Address.list()}" optionKey="id" required="" value="${museumInstance?.address?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumInstance, field: 'museumVisitRequest', 'error')} ">
	<label for="museumVisitRequest">
		<g:message code="museum.museumVisitRequest.label" default="Museum Visit Request" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${museumInstance?.museumVisitRequest?}" var="m">
    <li><g:link controller="museumVisitRequest" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="museumVisitRequest" action="create" params="['museum.id': museumInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'museumVisitRequest.label', default: 'MuseumVisitRequest')])}</g:link>
</li>
</ul>


</div>

