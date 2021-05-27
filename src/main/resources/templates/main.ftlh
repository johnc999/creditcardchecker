<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

	<style>
		#main-div {margin-top: 50px;}
	</style>
    <title>Credit Card Checker</title>
    <script>
      function submitFunction() {
        if (document.getElementById("cardnumber").value == "") {
            alert('Credit Card Number field is Empty.');
        } else{
            fetch('http://localhost:8080/creditcard/validate/' + document.getElementById("cardnumber").value)
             .then(response => response.json())
             .then(json => displayResult(json))
             .catch(err => console.error(err));
        }
      }
      function displayResult(data) {
        document.getElementById("result").innerHTML = data.company + ': ' + data.cardnumber + ' (' + data.result + ')';
      } 
	</script>
  </head>
  <body>
  
    <div class="container" id="main-div">
      <div class="form-inline">
	    <p class="h3" style="margin-left: 15px">Credit Card Checker</p>
	  </div>
	  <div class="form-inline" style="margin-top: 30px;">
	    <label for="cardlabel" class="m-3">Credit Card Number:</label>
	    <input type="text" class="form-control m-3" id="cardnumber" name="cardnumber" placeholder="">
	    <button onclick="submitFunction()" class="btn btn-primary m-3">Submit</button>
	  </div>
	  <div class="form-inline">
	    <label id="result" class="m-3"></label>
	  </div>
	</div>

</body>
</html>