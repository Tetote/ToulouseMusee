<%--
  Created by IntelliJ IDEA.
  User: MetalDiamond
  Date: 25/04/2015
  Time: 15:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Demande de visite</title>
</head>

<body>

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
                <g:textField name="startPeriodDate"/>
            </div>
            <div class="fieldcontain">
                <label for="endPeriodDate">
                    Date de fin
                </label>
                <g:textField name="endPeriodDate"/>
            </div>
            <div class="fieldcontain">
                <label for="nbPeople">
                    Nombre de personnes
                </label>
                <g:textField name="nbPeople"/>
            </div>
            <div class="center">
                <g:actionSubmit action="" value="Envoyer" />
            </div>
        </fieldset>
    </g:form>

</body>
</html>