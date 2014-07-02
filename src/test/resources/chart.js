
function datapoints(id) {
    return d3.selectAll("#"+id+" tr").selectAll("td").map(function(d) {
	return {x: parseFloat(d[0].textContent), y: parseFloat(d[1].textContent)};
    });
}

var history = datapoints("history");
var projection = datapoints("projection");

var chart_data = [{key: "History", values: history}];
if (projection.length > 0) {
    chart_data.push({key: "Projection", values: projection});
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

d3.select('#plot svg')
    .datum(chart_data)
    .call(chart);

//Update the chart when window resizes.
nv.utils.windowResize(function() { chart.update() });
