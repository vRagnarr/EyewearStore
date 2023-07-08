function validatePassword() {
  var passwordInput = document.getElementById("password");
  var confirmPasswordInput = document.getElementById("confirm-password");
  var submitButton = document.getElementById("submit-btn");
  var messageElement = document.getElementById("password-message");

  // Espressione regolare per controllare la password (almeno 7 caratteri, una maiuscola e un numero)
  var passwordRegex = /^(?=.*\d)(?=.*[A-Z]).{7,}$/;

  // Controlla la password e conferma password
  var passwordValid = passwordRegex.test(passwordInput.value);
  var passwordsMatch = (passwordInput.value === confirmPasswordInput.value);

  // Creazione di un array per i messaggi di errore
  var errorMessages = [];

  // Controlla i requisiti della password e aggiungi i messaggi di errore appropriati
  if (!/\d/.test(passwordInput.value)) {
    errorMessages.push("La password deve contenere almeno un numero");
  }
  if (!/[A-Z]/.test(passwordInput.value)) {
    errorMessages.push("La password deve contenere almeno una maiuscola");
  }
  if (passwordInput.value.length < 7) {
    errorMessages.push("La password deve essere lunga almeno 7 caratteri");
  }

  // Mostra i messaggi di errore
  if (errorMessages.length > 0) {
    messageElement.textContent = errorMessages.join(", ");
    passwordInput.style.borderColor = "red";
    confirmPasswordInput.style.borderColor = "red";
    submitButton.disabled = true;
  } else if (!passwordsMatch) {
    messageElement.textContent = "Le password non corrispondono";
    passwordInput.style.borderColor = "red";
    confirmPasswordInput.style.borderColor = "red";
    submitButton.disabled = true;
  } else {
    messageElement.textContent = "";
    passwordInput.style.borderColor = "green";
    confirmPasswordInput.style.borderColor = "green";
    submitButton.disabled = false;
  }
}
