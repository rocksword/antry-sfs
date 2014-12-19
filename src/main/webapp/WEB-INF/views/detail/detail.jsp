<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exchange Detail</title>
<!--<link rel="stylesheet"
    href="<%=request.getContextPath()%>/resources/js/jquery-2.1.0.js" />-->
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load('visualization', '1', {
        packages : [ 'corechart' ]
    });
</script>
<script type="text/javascript">
    function showChart() {
        console.log("showChangedRatingCount");
        $.get("/rest/detail/allDetails", function(data, status) {
            console.log("Status: " + status);
            console.log("Data: " + data);
            drawChart(data);
        });
    }

    function drawChart(data) {
        var rows = [];
        var columnNames = [ "Date", "Net money", "Total money" ];
        rows.push(columnNames);
        data.forEach(function addNumber(row) {
            rows.push(row);
        });

        // Create and populate the data table.
        var data = google.visualization.arrayToDataTable(rows);
        // Create and draw the visualization.
        new google.visualization.ColumnChart(document.getElementById('visualization')).draw(data, {
            title : "Daily in out money",
            width : 1200,
            height : 1000,
            hAxis : {
                title : "Date"
            }
        });
    }

    google.setOnLoadCallback(showChart);
</script>
</head>
<body style="font-family: Arial; border: 0 none;">
    <div id="visualization" style="width: 1200px; height: 1000px;"></div>
</body>
</html>