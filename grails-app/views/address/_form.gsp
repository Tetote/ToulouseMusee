<%@ page import="toulousemusee.Address" %>



<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'num', 'error')} required">
	<label for="num">
		<g:message code="address.num.label" default="Num" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="num" required="" value="${addressInstance?.num}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'street', 'error')} required">
	<label for="street">
		<g:message code="address.street.label" default="Street" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="street" required="" value="${addressInstance?.street}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'zipCode', 'error')} required">
	<label for="zipCode">
		<g:message code="address.zipCode.label" default="Zip Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="zipCode" required="" value="${addressInstance?.zipCode}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: addressInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="address.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${addressInstance?.city}"/>

</div>

