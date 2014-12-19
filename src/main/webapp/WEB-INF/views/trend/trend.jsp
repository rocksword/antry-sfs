<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trend</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load('visualization', '1', {
        packages : [ 'corechart' ]
    });
</script>
<script type="text/javascript">
    function showChart() {
        console.log("showChart");
        $.get("/rest/trend/forecastExchanges", function(data, status) {
            console.log("Status: " + status);
            console.log("Data: " + data);
            drawChart(data);
        });
    }

    function drawChart(data) {
        console.log("drawChart");

        var rows = [];

        var columnNames = [ "Date", "Price", "MA5", "MA10", "MA20" ];
        rows.push(columnNames);

        data.forEach(function addNumber(row) {
            rows.push(row);
        });

        // Create and populate the data table.
        var data = google.visualization.arrayToDataTable(rows);

        // Create and draw the visualization.
        new google.visualization.LineChart(document.getElementById('visualization')).draw(data, {
            curveType : "function",
            width : 1200,
            height : 1000,
            vAxis : {
                maxValue : 10
            }
        });
    }

    google.setOnLoadCallback(showChart);
</script>
</head>
<body>
    Trend:
    <div id="visualization" style="width: 1200px; height: 1000px;"></div>
</body>
</html>