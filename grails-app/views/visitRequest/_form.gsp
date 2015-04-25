<%@ page import="toulousemusee.VisitRequest" %>



<div class="fieldcontain ${hasErrors(bean: visitRequestInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="visitRequest.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" value="${visitRequestInstance.code}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitRequestInstance, field: 'startPeriodDate', 'error')} required">
	<label for="startPeriodDate">
		<g:message code="visitRequest.startPeriodDate.label" default="Start Period Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startPeriodDate" precision="day"  value="${visitRequestInstance?.startPeriodDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: visitRequestInstance, field: 'endPeriodDate', 'error')} required">
	<label for="endPeriodDate">
		<g:message code="visitRequest.endPeriodDate.label" default="End Period Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endPeriodDate" precision="day"  value="${visitRequestInstance?.endPeriodDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: visitRequestInstance, field: 'nbPeople', 'error')} required">
	<label for="nbPeople">
		<g:message code="visitRequest.nbPeople.label" default="Nb People" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nbPeople" type="number" min="1" value="${visitRequestInstance.nbPeople}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: visitRequestInstance, field: 'museumVisitRequest', 'error')} ">
	<label for="museumVisitRequest">
		<g:message code="visitRequest.museumVisitRequest.label" default="Museum Visit Request" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${visitRequestInstance?.museumVisitRequest?}" var="m">
    <li><g:link controller="museumVisitRequest" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="museumVisitRequest" action="create" params="['visitRequest.id': visitRequestInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'museumVisitRequest.label', default: 'MuseumVisitRequest')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: visitRequestInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="visitRequest.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${toulousemusee.VisitRequest$Status?.values()}" keys="${toulousemusee.VisitRequest$Status.values()*.name()}" required="" value="${visitRequestInstance?.status?.name()}" />

</div>

