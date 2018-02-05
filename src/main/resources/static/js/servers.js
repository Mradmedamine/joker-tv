$(function() {

    initServersDataTables();

    function initServersDataTables() {
	var serversDataTable = $('#servers-datatables').DataTable(dataTablesConfig);
	return serversDataTable.rows().count();
    }

});