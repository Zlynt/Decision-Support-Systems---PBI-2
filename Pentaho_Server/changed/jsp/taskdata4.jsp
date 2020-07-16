<%@ page 
	session="true"
	contentType="text/html;"
	import="java.util.*, sadFase123.*, weka.*"
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TASKDATA4</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
		integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</head>

<body>
	<h3>TASKDATA4 Association Rules</h3>
	<table class="table table-dark">
		<thead>
			<tr>
				<th scope="col">Mined Rule</th>
				<th scope="col">Confidence</th>
			</tr>
		</thead>
		<tbody>

			<%
			PluginDoPentaho pluginDoPentaho = new PluginDoPentaho();

			try {
				String currentTask = pluginDoPentaho.getTASKDATA4Rules().split("Best rules found:\n\n")[1];
				for(int i = 0; i < currentTask.split("\n").length; i++) {
					String currentRule = currentTask.split("\n")[i];
					currentRule = currentRule.split(" lift:")[0];
					
					String currentConfidence = currentRule.split("    ")[currentRule.split("    ").length-1];
					currentRule = currentRule.substring(0, currentRule.length() - (currentConfidence+"    ").length());
					currentConfidence = currentConfidence.replace("<", "");
					currentConfidence = currentConfidence.replace(">", "");
					currentConfidence = currentConfidence.replace("conf:(", "");
					currentConfidence = currentConfidence.substring(0, currentConfidence.length()-1);
					currentRule = currentRule.replace("=Bought", "");
					
					out.print("<tr><td>"+currentRule+"</td><td>"+currentConfidence+"</td></tr>");
				}
			}catch(Exception err){
				out.print(err.toString());
			}

			%>
		</tbody>
	</table>

</body>

</html>