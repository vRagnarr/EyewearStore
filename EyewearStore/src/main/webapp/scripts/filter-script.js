$(document).ready(function() {
    // Funzione per applicare i filtri
    function applyFilters() {
        var selectedSex = $("#sex-filter").val(); // Ottieni il valore del filtro sesso

        // Effettua una chiamata AJAX per ottenere i prodotti filtrati
        $.ajax({
            url: "../pages/filtro-carosello.jsp", // Specifica l'URL del servlet o della pagina che gestisce la richiesta AJAX
            method: "GET",
            data: { sex: selectedSex }, // Passa il valore del filtro come parametro
            success: function(data) {
                // Aggiorna il contenuto della pagina con i nuovi prodotti filtrati
                $("#product-container").html(data);
            },
            error: function() {
                alert("Si è verificato un errore durante l'applicazione dei filtri.");
            }
        });
    }

    // Aggiungi un gestore di eventi al filtro di sesso per scatenare l'applicazione dei filtri
    $("#sex-filter").change(function() {
        applyFilters();
    });
});
