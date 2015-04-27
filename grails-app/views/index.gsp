<%@ page import="toulousemusee.MuseumController; toulousemusee.Museum" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to ToulouseMusee</title>
		<style type="text/css" media="screen">
			body  {
				max-width:1300px;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			h3 {
				margin:10px 0 5px 10px;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			.center {
				text-align:center;
			}

			#menu {
				padding:5px;
				background:#eee;
			}
			#menu ul {
				list-style:none;
			}
			#menu li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="menu" role="navigation">
			<ul>
				<li><g:link controller="museum" action="index">Home</g:link></li>
				<li><g:link controller="visitRequest" action="index">Create visit request</g:link></li>
			</ul>
		</div>
		<div id="page-body" role="main">
			<div id="list-favorites" class="content scaffold-list" role="main">
				<g:if test="${favoriteMuseumInstanceList && favoriteMuseumInstanceList.size() > 0}">
					<h3>Favorites</h3>
					<table>
						<thead>
							<tr>
								<th>Museum name</th>
								<th>Favorite</th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${favoriteMuseumInstanceList}" status="i" var="museumInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td>${fieldValue(bean: museumInstance, field: "name")}</td>
								<td><g:link action="removeFromFavorite" id="${museumInstance.id}">Remove</g:link></td>
							</tr>
						</g:each>
						</tbody>
					</table>
				</g:if>
			</div>
			<div id="list-museum" class="content scaffold-list" role="main">
				<h3>Search museums</h3>
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:form>
					<fieldset class="form">
						<div class="fieldcontain">
							<label for="name">
								Name of museum contains:
							</label>
							<g:textField name="name"/>
						</div>
						<div class="fieldcontain">
							<label for="zipCode">
								Zipcode of museum:
							</label>
							<g:select name="zipCode" from="${zipCodeInstanceList}" noSelection="['':'-Choose zipCode-']"></g:select>
						</div>
						<div class="fieldcontain">
							<label for="street">
								Street address of museum contains:
							</label>
							<g:textField name="street"/>
						</div>
						<div class="center">
							<g:actionSubmit action="doSearchMuseums" value="Rechercher" />
						</div>
					</fieldset>
				</g:form>
				<g:if test="${museumInstanceList && museumInstanceList.size() > 0}" >
					<h3>List of museums</h3>
					<table>
						<thead>
						<tr>
							<th><g:message code="museum.name.label" default="Name" /></th>
							<th><g:message code="museum.hours.labe" default="Hours" /></th>
							<th><g:message code="museum.phoneNumber.label" default="Phone Number" /></th>
							<th><g:message code="museum.metroAccess.label" default="Metro Access" /></th>
							<th><g:message code="museum.busAccess.label" default="Bus Access}" /></th>
							<th><g:message code="museum.address.label" default="Address" /></th>
							<th><g:message code="museum.manager.label" default="Manager" /></th>
							<th>Favorite</th>
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
								<td>
									<g:if test="${favoriteInstanceList.get(i)}">
										<g:link action="removeFromFavorite" id="${museumInstance.id}">Remove</g:link>
									</g:if>
									<g:else>
										<g:link action="addToFavorite" id="${museumInstance.id}">Add</g:link>
									</g:else>
								</td>
							</tr>
						</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${museumInstanceCount ?: 0}"
									params="${params}" max="5"
									action="doSearchMuseums" />
					</div>
				</g:if>
			</div>
		</div>
	</body>
</html>
