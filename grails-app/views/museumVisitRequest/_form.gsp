<%@ page import="toulousemusee.MuseumVisitRequest" %>



<div class="fieldcontain ${hasErrors(bean: museumVisitRequestInstance, field: 'requestDate', 'error')} required">
	<label for="requestDate">
		<g:message code="museumVisitRequest.requestDate.label" default="Request Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="requestDate" precision="day"  value="${museumVisitRequestInstance?.requestDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: museumVisitRequestInstance, field: 'museum', 'error')} required">
	<label for="museum">
		<g:message code="museumVisitRequest.museum.label" default="Museum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="museum" name="museum.id" from="${toulousemusee.Museum.list()}" optionKey="id" required="" value="${museumVisitRequestInstance?.museum?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museumVisitRequestInstance, field: 'visitRequest', 'error')} required">
	<label for="visitRequest">
		<g:message code="museumVisitRequest.visitRequest.label" default="Visit Request" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="visitRequest" name="visitRequest.id" from="${toulousemusee.VisitRequest.list()}" optionKey="id" required="" value="${museumVisitRequestInstance?.visitRequest?.id}" class="many-to-one"/>

</div>

