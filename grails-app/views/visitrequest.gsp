<%@ page import="toulousemusee.VisitRequestController" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Demande de visite</title>

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
    <div id="menu" role="navigation">
        <ul>
            <li><g:link controller="museum" action="index">Home</g:link></li>
            <li><g:link controller="visitRequest" action="index">Create visit request</g:link></li>
        </ul>
    </div>
    <div id="list-favorites" class="content scaffold-list" role="main">
        <g:if test="${favoriteMuseumInstanceList && favoriteMuseumInstanceList.size() > 0}">
            <h3>Favorites</h3>
            <table>
                <thead>
                <tr>
                    <th>Museums</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${favoriteMuseumInstanceList}" status="i" var="museumInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${fieldValue(bean: museumInstance, field: "name")}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
    </div>

    <h3>Demande de visite</h3>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form>
        <fieldset class="form">
            <div class="fieldcontain">
                <label for="startPeriodDate">
                    Date de début
                </label>
                <g:datePicker name="startPeriodDate"></g:datePicker>
            </div>
            <div class="fieldcontain">
                <label for="endPeriodDate">
                    Date de fin
                </label>
                <g:datePicker name="endPeriodDate"></g:datePicker>
            </div>
            <div class="fieldcontain">
                <label for="nbPeople">
                    Nombre de personnes
                </label>
                <g:textField name="nbPeople"/>
            </div>
            <div class="center">
                <g:actionSubmit action="addVisitRequest" value="Envoyer" />
            </div>
        </fieldset>
    </g:form>

    <g:if test="${codeVisitRequest >= 0}">
        <p>Votre demande de visite a bien été ajoutée, nous vous contacterons</p>
    </g:if>
    <g:elseif test="${codeVisitRequest == -1}">
        <p>Erreur, go appler le SAV</p>
    </g:elseif>
</body>
</html>