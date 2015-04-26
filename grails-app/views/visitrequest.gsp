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

    <div class="container">

        <div class="row">
            <h1>Demande de visite</h1>
        </div>

        <div class="row>
            <div class="form-group">
                <label>Date de début</label>
                <input name="startPeriodDate" id="startPeriodDate" class="form-control input-lg" placeholder="JJ/MM/AAAA" tabindex="1" value="" type="text">

                <label>Date de fin</label>
                <input name="endPeriodDate" id="endPeriodDate" class="form-control input-lg" placeholder="JJ/MM/AAAA" tabindex="2" value="" type="text">

                <label>Nombre de personnes</label>
                <input name="nbPeople" id="nbPeople" class="form-control input-lg" placeholder="Max 6 Personnes" tabindex="3" value="" type="text">
            </div>
        </div>
    </div>

</body>
</html>