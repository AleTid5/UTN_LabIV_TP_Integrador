$(document).ready(function() {
    $('#datatables').DataTable({
        pagingType: "full_numbers",
        lengthMenu: [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Buscar",
            emptyTable: "No se han encontrado resultados",
            zeroRecords: "No se han encontrado resultados"
        }
    });
});