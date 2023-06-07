function slide()
				{
  					var checkBox = document.getElementById("check1");
  					var text = document.getElementById("texttochange");
  					if(checkBox.checked == true)
  					{
   						text.innerHTML = "LOGIN";
  					} 
  					else
   				 	text.innerHTML = "SIGN UP";
				}

