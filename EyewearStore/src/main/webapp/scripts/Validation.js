$(document).ready(function() {
  // Disabilita il pulsante di submit all'avvio
  $('#submit-btn').prop('disabled', true);

  // Funzione di validazione delle password
  function validatePassword() {
    var password = $('#password').val();
    var confirmPassword = $('#confirm-password').val();
    var passwordError = $('#passwordError');
    var confirmPasswordError = $('#confirmPasswordError');

    // Resetta i messaggi di errore e i bordi rossi
    passwordError.text('');
    confirmPasswordError.text('');
    $('#password, #confirm-password').removeClass('is-invalid');

    // Validazione della password
    var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{7,}$/;
    if (!passwordRegex.test(password)) {
      passwordError.text('La password deve contenere almeno un numero, una lettera maiuscola e una lunghezza di almeno 7 caratteri.');
      $('#password').addClass('is-invalid');
      return false;
    }

    // Validazione della conferma password
    if (password !== confirmPassword) {
      confirmPasswordError.text('Le password non corrispondono.');
      $('#confirm-password').addClass('is-invalid');
      return false;
    }

    return true;
  }

  // Funzione di controllo della validità dei campi
  function checkValidity() {
    var isPasswordValid = validatePassword();
    var isFormValid = isPasswordValid;

    // Abilita o disabilita il pulsante di submit
    $('#submit-btn').prop('disabled', !isFormValid);
  }

  // Controlla la validità dei campi ad ogni modifica
  $('#password').on('input', checkValidity);
  $('#confirm-password').on('input', checkValidity);

  // Gestione dell'invio del form
  $('form').on('submit', function(e) {
    // Verifica se i campi sono vuoti
    var isEmpty = false;
    $('input[type="text"], input[type="date"], input[type="email"], input[type="password"]').each(function() {
      if ($(this).val().trim() === '') {
        isEmpty = true;
        $(this).addClass('is-invalid');
      }
    });

    // Verifica se la password è valida
    var isPasswordValid = validatePassword();

    // Se ci sono campi vuoti o la password non è valida, impedisce l'invio del form
    if (isEmpty || !isPasswordValid) {
      e.preventDefault();
    }
  });
});
