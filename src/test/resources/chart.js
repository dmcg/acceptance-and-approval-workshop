function datapoints(id) {
//    var result = [];
//    var rows = document.getElementById(id).getElementsByTagName('tr')
//    for (var i = 0; i < rows.length; i++) {
//        var d = rows[i].getElementsByTagName("td");
//        result.push({x: parseFloat(d[0].textContent), y: parseFloat(d[1].textContent)})
//    }
//    return rows;
    var result = d3.selectAll("#" + id + " tr").selectAll("td").map(
        function (d) {
            return {
                x: parseFloat(d[0].textContent),
                y: parseFloat(d[1].textContent)};
        });
    return result;
}

var historyPoints = datapoints("history");
var projectionPoints = datapoints("projection");

var chart_data = [{key: "History", values: historyPoints}];
if (projection.length > 0) {
    chart_data.push({key: "Projection", values: projectionPoints});
}

var chart = nv.models.lineChart()
    .margin({left: 100})
    .useInteractiveGuideline(true)
    .transitionDuration(350)
    .showLegend(true)
    .showYAxis(true)
    .showXAxis(true)
    .forceY(0);

chart.xAxis
    .axisLabel('Year');

chart.yAxis
    .axisLabel('Parts per Million')
    .tickFormat(d3.format('f'));

d3.select('#svg')
    .datum(chart_data)
    .call(chart);

//Update the chart when window resizes.
nv.utils.windowResize(function() { chart.update() });
